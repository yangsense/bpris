package com.ai.aris.server.sysmanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.constants.SysManageSqlUtil;
import com.ai.aris.server.common.util.MD5;
import com.ai.aris.server.sysmanage.bean.ISysOperatorValue;
import com.ai.aris.server.sysmanage.bean.ISysOrgValue;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysMenuEngine;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgEngine;
import com.ai.aris.server.sysmanage.bean.SysOperatorBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorEngine;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgEngine;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;
import com.ai.aris.server.sysmanage.service.interfaces.ISysManageSV;
import com.borland.enterprise.util.StrUtil;

/**
 * 
 * @author wuliangyong
 */
public class SysManageSVImpl implements ISysManageSV {
	private static Logger logger = LoggerFactory.getLogger(SysManageSVImpl.class);


	@Override
	public SysMenuBean[] queryMenuList(String operatorCode, String appType)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select menu_id, menu_name, sys_type, menu_type, menu_url, parent_menu_id from sys_menu where STATE=1 and menu_type !=2 start with menu_id in(");
		sql.append("		  select  menu_id from sys_menu where  menu_url='"+appType+"'  ");
		sql.append("		) CONNECT BY PRIOR menu_id= parent_menu_id ");
		sql.append(SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode,false,null,true));
		sql.append(" ORDER BY PARENT_MENU_ID,MENU_ORDER");
		SysMenuBean[] menuBeans = SysMenuEngine.getBeansFromSql(sql.toString(), null);
		List<SysMenuBean> list = java.util.Arrays.asList(menuBeans);
		return menuBeans;
	}

	//取当前系统所有按钮权限
	public SysMenuBean[] queryButtonList(String operatorCode, String appType)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select menu_id, menu_name, sys_type, menu_type, menu_url, parent_menu_id from sys_menu where STATE=1 and menu_type =2 start with menu_id in(");
		sql.append("		  select  menu_id from sys_menu where  menu_url='"+appType+"'  ");
		sql.append("		) CONNECT BY PRIOR menu_id= parent_menu_id ");
		sql.append(SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode,false,null,true));
		sql.append(" ORDER BY PARENT_MENU_ID,MENU_ORDER");
		SysMenuBean[] menuBeans = SysMenuEngine.getBeansFromSql(sql.toString(), null);
		List<SysMenuBean> list = java.util.Arrays.asList(menuBeans);
		return menuBeans;
	}

	//获取工号归属组织
	public ISysOrgValue[] getBelongOrgByOprCode(String operatorCode) throws Exception {
		String stationCode = "STAFF_BELONG_ORG";
		String sql = "select d.*,so.operator_code,so.remarks as remarks2 " +
				" from sys_operator2org so,sys_org d where so.org_id = d.org_id " +
				" and operator_code = :operatorCode and station_code = '"+stationCode+"'";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("operatorCode", operatorCode);
		ISysOrgValue[] beans = SysOrgEngine.getBeansFromSql(sql, map);
		List<ISysOrgValue> list = java.util.Arrays.asList(beans);
		return beans;
	}

	@Override
	public SysOperator2OrgBean[] queryOperator2OrgBeans(String operatorCode)
			throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sqlBuf = new StringBuilder(" 1=1 ");
		Map<String, String> params = new HashMap<String, String>();
		if (!StrUtil.isBlank(operatorCode)) {
			sqlBuf.append(" and operator_code = :operator_code" );
			params.put("operator_code", operatorCode);
		}else{
			return null;
		}
		SysOperator2OrgBean[] beans = SysOperator2OrgEngine.getBeans(sqlBuf.toString(), params);
		List<SysOperator2OrgBean> list = java.util.Arrays.asList(beans);
		return beans;
	}

	/**
	 * 根据组织ID，获取组织对应的信息
	 */
	public SysOrgBean getOrgBeanByOrgId(String orgId) throws Exception {
		return SysOrgEngine.getBean(orgId);
	}

	/**
	 * 用户登陆接口，如果登陆失败，以异常的方式将错误抛出
	 *
	 * @param operatorCode 登陆工号
	 * @param psw          操作员密码，明文传递
	 * @throws Exception
	 */
	public ISysOperatorValue login(String operatorCode, String psw, String clientIP) throws Exception {
		ISysOperatorValue bean = this.getOperatorBeanByCode(operatorCode.toUpperCase());

		if (bean == null || bean.isNew()) {//isNew() 为找不到用户
			return null;
		} else {

			//验证通过,注意,md5加密算法有的字母为大写,有的为小写,这里统一转为小写再比较;
			String pwd = getDecodePsw(psw);
			if (bean.getOperatorPsw().toLowerCase().equals(pwd)) {
				//如果登陆成功则记录登陆日志
				IOperatorLogSV operatorLogSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
				operatorLogSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeUserLogin, clientIP);

				return bean;
			}
		}
		return null;
	}

	/**
	 * 用户登陆接口，如果登陆失败，以异常的方式将错误抛出
	 *
	 * @param operatorCode 登陆工号
	 * @throws Exception
	 */
	public ISysOperatorValue login(String operatorCode, String clientIP) throws Exception {
		ISysOperatorValue bean = this.getOperatorBeanByCode(operatorCode.toUpperCase());

		if (bean == null || bean.isNew()) {//isNew() 为找不到用户
			return null;
		} else {
			IOperatorLogSV operatorLogSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
			operatorLogSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeUserLogin, clientIP);

			return bean;
		}
	}

	/**
	 * 功能描述:根据operatorCode 取operatorManage对象
	 *
	 * @param operatorCode
	 * @throws Exception fangll 2012-9-14下午02:40:30
	 */
	public ISysOperatorValue getOperatorBeanByCode(String operatorCode) throws Exception {
		StringBuffer condition = new StringBuffer(" 1=1 ");
		Map parameter = new HashMap();
		condition.append(" and operator_code = :operatorCode");
		parameter.put("operatorCode", operatorCode);

		ISysOperatorValue[] sysOperatorValue = (ISysOperatorValue[]) SysOperatorEngine.getBeans(condition.toString(), parameter);
		if(sysOperatorValue.length > 0){
			return sysOperatorValue[0];
		}else{
			return null;
		}
	}

	/**
	 * 密码加密算法
	 *
	 * @param psw
	 * @return
	 */
	private String getDecodePsw(String psw) {
		return MD5.toMD5(psw).toLowerCase();
	}


	public SysOrgBean[] querySysOrgMangeBeans(
			String operatorCode, String sysType,String filterInfo) throws Exception {
		StringBuffer condition = new StringBuffer();
		Map<String, Object> para = new HashMap<String, Object>();

		//跌代滤重，sql只能这样写了
		condition.append(" SELECT DISTINCT * FROM SYS_ORG WHERE 1=1 ");

		if(!StrUtil.isBlank(filterInfo)){
			condition.append(" and (org_code like '%"+filterInfo+"%' or org_name like '%"+filterInfo+"%') ");
		}

		if(!StrUtil.isBlank(operatorCode)){
			condition.append(" AND ORG_ID IN (SELECT T.ORG_ID FROM SYS_OPERATOR2ORG T ,SYS_STATION F WHERE T.STATION_CODE=F.STATION_CODE AND T.OPERATOR_CODE = '"+operatorCode+"' AND T.ORG_TYPE = 0 AND F.SYS_TYPE = '"+sysType+"' )");
		}else{
			return null;
		}

		SysOrgBean[] beans = SysOrgEngine.getBeansFromSql(condition.toString(),para);

		return beans;
	}

	@Override
	public SysOperatorBean[] queryOperatorBeans(String orgId)
			throws Exception {
		StringBuilder sqlBuf = new StringBuilder(" 1=1 ");
		Map<String, String> params = new HashMap<String, String>();
		if (!StrUtil.isBlank(orgId)) {
			sqlBuf.append(" and org_id = :org_id" );
			params.put("org_id", orgId);
		}else{
			return null;
		}
		SysOperatorBean[] beans = SysOperatorEngine.getBeans(sqlBuf.toString(), params);
		return beans;

	}


	/**
	 * 根据 组织编码获取该组织对应的信息
	 */
	public SysOrgBean getOrgBeanByOrgCode(String orgCode) throws Exception{
		SysOrgBean[] beans=SysOrgEngine.getBeans(" ORG_CODE ='"+orgCode+"'",null);
		if(beans.length!=0&&beans!=null){
			return beans[0];
		}else{
			return null;
		}
	}

	/**
	 * 修改密码（不需要校验老密码）
	 *
	 * @param operatorCode 被修改密码的操作员
	 * @param newPassword  新密码
	 * @param currentOpt   当前操作员
	 * @throws Exception
	 */
	public void changeUserPasswordWithoutCheck(String operatorCode,
	                                           String newPassword, String currentOpt) throws Exception {
		if (newPassword == null || newPassword.equals("")) {
			throw new Exception("新密码不能为空!");
		}

		
		StringBuffer condition = new StringBuffer(" 1=1 ");
		Map parameter = new HashMap();
		parameter.put("operatorCode", operatorCode);
		condition.append(" and operator_code = :operatorCode");
		SysOperatorBean optBean = SysOperatorEngine.getBeans(condition.toString(), parameter)[0];
//		SysOperatorBean optBean = SysOperatorEngine.getBean(operatorCode);

		//如果操作员不存在，则返回错误
		if (optBean.isNew()) {
			throw new Exception("根据操作员工号：" + operatorCode + "找不到操作员");
		}

		//设置新密码和更新时间
		optBean.setOperatorPsw(getDecodePsw(newPassword));
		optBean.setChangePswDate(SysdateManager.getSysTimestamp());
		SysOperatorEngine.save(optBean);

		//记录操作日志 
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
		logSV.saveOperatorLog(currentOpt, IOperatorLogSV.optTypeUpdatePSW,
						"operator code=" + optBean.getOperatorCode() + ",name=" + optBean.getOperatorName());
	}

	/**
	 * 操作员修改自己密码（需要校验老密码）
	 *
	 * @param operatorCode 当前操作员
	 * @param oldPassword  老密码
	 * @param newPassword  新密码
	 * @throws Exception
	 */
	public String changeMyPasswordWithCheck(String operatorCode,
	                                      String oldPassword, String newPassword) throws Exception {
		if (newPassword == null || newPassword.equals("")) {
			return "-1";
			//throw new Exception("新密码不能为空!");
		}

		StringBuffer condition = new StringBuffer(" 1=1 ");
		Map parameter = new HashMap();
		parameter.put("operatorCode", operatorCode);
		condition.append(" and operator_code = :operatorCode");
		SysOperatorBean optBean = SysOperatorEngine.getBeans(condition.toString(), parameter)[0];
//		SysOperatorBean optBean = SysOperatorEngine.getBean(operatorCode);

		//如果操作员不存在，则返回错误
		if (optBean.isNew()) {
			return "-2";
//			throw new Exception("根据操作员工号：" + operatorCode + "找不到操作员");
		}
		//校验老密码
		if (!optBean.getOperatorPsw().equals(getDecodePsw(oldPassword))) {
			return "-3";
//			throw new Exception("原密码不正确,请重输!");
		}


		//设置新密码和更新时间
		optBean.setOperatorPsw(getDecodePsw(newPassword));
		optBean.setChangePswDate(SysdateManager.getSysTimestamp());
		SysOperatorEngine.save(optBean);

		//记录操作日志 
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
		logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeUpdatePSW,
						"operator code=" + optBean.getOperatorCode() + ",name=" + optBean.getOperatorName());
		return "0";
	}
}
