package com.ai.aris.server.sysmanage.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.appframe2.util.criteria.Criteria;
import com.ai.aris.server.basecode.service.interfaces.IAiscBoardroomSV;
import com.ai.aris.server.common.util.MD5;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.ISysOperator2OrgValue;
import com.ai.aris.server.sysmanage.bean.ISysOperatorValue;
import com.ai.aris.server.sysmanage.bean.ISysRoleValue;
import com.ai.aris.server.sysmanage.bean.QueryOfficeNameBean;
import com.ai.aris.server.sysmanage.bean.QueryOfficeNameEngine;
import com.ai.aris.server.sysmanage.bean.SysOfficeBean;
import com.ai.aris.server.sysmanage.bean.SysOfficeEngine;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgEngine;
import com.ai.aris.server.sysmanage.bean.SysOperator2roleBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2roleEngine;
import com.ai.aris.server.sysmanage.bean.SysOperatorBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorEngine;
import com.ai.aris.server.sysmanage.bean.SysOperatorOfficeBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorOfficeEngine;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgEngine;
import com.ai.aris.server.sysmanage.bean.SysRoleEngine;
import com.ai.aris.server.sysmanage.model.SysOperatorModel;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorManageSV;
import com.ai.common.util.ServiceUtil;

public class OperatorManageSVImpl implements IOperatorManageSV {
	private static Logger logger = LoggerFactory.getLogger(OperatorManageSVImpl.class);
	IAiscBoardroomSV boardSv = (IAiscBoardroomSV) ServiceFactory.getService(IAiscBoardroomSV.class);
	public void deleteOperator(String operatorCode, String operatorCodes) throws Exception {
		String[] operatorCodeArray = operatorCodes.split(",");
		if (ArrayUtils.contains(operatorCodeArray, "ADMIN")) {
			throw new Exception("超级管理员不能删除！");
		}
		//操作日志保存
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);

		for (String  operatorCodeDelete: operatorCodeArray) {
			//操作员删除
			StringBuffer condition = new StringBuffer(" 1=1 ");
			Map parameter = new HashMap();
			parameter.put("operatorCode", operatorCodeDelete);
			condition.append(" and operator_code = :operatorCode");
			SysOperatorBean optBean = SysOperatorEngine.getBeans(condition.toString(), parameter)[0];
			optBean.setOperatorState(0);//禁用
			//optBean.delete();
			SysOperatorEngine.save(optBean);
			/*//删除权限关联表
			this.deleteOperator2Role(operatorCodeDelete);
			//删除所属机构
			this.deleteOperator2Org(operatorCodeDelete);*/
			logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeStopUser,
					"operator code=" + operatorCodeDelete + ",name=" + optBean.getOperatorName());
		}
	}
	/**
	 * 删除操作员角色关联关系
	 * */
	public void deleteOperator2Role(String operatorCode) throws Exception {
		Criteria sql = new Criteria();
		sql.addEqual("operator_code", operatorCode);
		SysOperator2roleBean[] sysOperator2roleBeans = SysOperator2roleEngine.getBeans(sql);
		if (null != sysOperator2roleBeans && sysOperator2roleBeans.length > 0) {
			for (SysOperator2roleBean sysOperator2roleBean : sysOperator2roleBeans) {
				sysOperator2roleBean.delete();
			}
			SysOperator2roleEngine.saveBatch(sysOperator2roleBeans);
		}
	}
	/**
	 * 删除操作员机构关联关系
	 * */
	public void deleteOperator2Org(String operatorCode) throws Exception {
		Criteria sql = new Criteria();
		sql.addEqual("operator_code", operatorCode);
		SysOperator2OrgBean[] sysOperator2OrgBeans = SysOperator2OrgEngine.getBeans(sql);
		if (null != sysOperator2OrgBeans && sysOperator2OrgBeans.length > 0) {
			for (SysOperator2OrgBean sysOperator2OrgBean : sysOperator2OrgBeans) {
				sysOperator2OrgBean.delete();
			}
			SysOperator2OrgEngine.saveBatch(sysOperator2OrgBeans);
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

		ISysOperatorValue[] sysOperatorValue = SysOperatorEngine.getBeans(condition.toString(), parameter);
		if(sysOperatorValue.length > 0){
			return sysOperatorValue[0];
		}else{
			return null;
		}

	}
	//取操作员归属机构
	public ISysOperator2OrgValue getBelongOrg(String operatorCode)	{
		String stationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
		String condition = " operator_code = :operatorCode and station_code='"+stationCode+"'";
		Map<String, String> map = new HashMap<String, String>();
		map.put("operatorCode", operatorCode);
		ISysOperator2OrgValue[] iqr;
		try {
			iqr = SysOperator2OrgEngine.getBeans(condition, map);
			return iqr[0];
		} catch (Exception e) {
			return null;
		}

	}

	public List<Map> initOfficeSelect(String orgId) throws Exception{
		StringBuffer condition2 = new StringBuffer(" 1=1 AND STATE='1' ");
		condition2.append(" AND ORG_ID='"+orgId+"'");
		SysOrgBean [] beans2 = null;
		int total2 = SysOrgEngine.getBeansCount(condition2.toString(), null);
		List<Map> list = new ArrayList();
		if (total2 > 0) {
			beans2 = SysOrgEngine.getBeans(condition2.toString(), null);
			SysOrgBean  orgBean = new SysOrgBean();
			orgBean = beans2[0];
			StringBuffer condition = new StringBuffer(" 1=1 AND STATE='0' ");
			condition.append(" AND ORG_CODE='"+orgBean.getOrgCode()+"'");
			int total = SysOfficeEngine.getBeansCount(condition.toString(), null);
			SysOfficeBean[] beans = null;
			if (total > 0) {
				beans = SysOfficeEngine.getBeans(condition.toString(), null);
				for(SysOfficeBean sysOrgBean:beans){
					list.add(ServiceUtil.transerBeanToMap(sysOrgBean));
				}
			}
		}
		return list;
	}

	public List<Map> getOfficeInit(String operatorCode) throws Exception{
		StringBuffer condition2 = new StringBuffer(" 1=1");
		condition2.append(" AND OPERATOR_CODE='"+operatorCode+"'");
		QueryOfficeNameBean [] beans2 = null;
		List<Map> list = new ArrayList();
		beans2 = QueryOfficeNameEngine.getBeans(condition2.toString(), null);
		if(beans2 != null && beans2.length>0){
			for(QueryOfficeNameBean officeBean:beans2){
				list.add(ServiceUtil.transerBeanToMap(officeBean));
			}
			return list;
		}else {
			return null;
		}
	}

	public boolean isExistOperator(String operatorCode) throws Exception {
		StringBuffer condition = new StringBuffer();
		Map<String, Object> para = new HashMap<String, Object>();
		condition.append(" 1 = 1 \n");
		//终端IMEI号
		if (StringUtils.isNotEmpty(operatorCode)) {
			condition.append(" and upper(operator_code) = :operatorCode \n");
			para.put("operatorCode", operatorCode.toUpperCase().trim());
		}
		boolean isExists = SysOperatorEngine.getBeansCount(condition.toString(), para) > 0 ? true : false;
		return isExists;
	}

	public void saveOperator(String operatorCode, SysOperatorModel searchModel)
			throws Exception {
		//判断操作员工号是否重复
		String newOperatorCode = searchModel.getOperatorCode().trim().toUpperCase();
		if (this.isExistOperator(newOperatorCode)) {
			throw new Exception("该工号已存在,请重输！");
		}

		//操作员信息保存操作
		SysOperatorBean optBean = new SysOperatorBean();
		optBean.setOperatorId(SysOperatorEngine.getNewId().longValue());
		optBean.setOperatorCode(newOperatorCode);
		optBean.setOperatorName(searchModel.getOperatorName().trim());
		optBean.setOperatorPsw(getDecodePsw(searchModel.getOperatorPsw()));
		optBean.setOperatorState(searchModel.getOperatorState());
		optBean.setRemarks(searchModel.getRemarks().trim());
		optBean.setChangePswDate(SysdateManager.getSysTimestamp());
		optBean.setCreateDate(SysdateManager.getSysTimestamp());
		optBean.setTelNo(searchModel.getTelNo());
		//所属机构保存
//		String belong2OrgId = searchModel.getOperator2OrgList();
		String belong2OrgId = searchModel.getOrgId();
		if (StringUtils.isNotEmpty(belong2OrgId)) {
			//冗余保存
			optBean.setOrgId(belong2OrgId);
			this.saveOperator2Org(newOperatorCode, belong2OrgId);
		}
		SysOperatorEngine.save(optBean);
		//保存操作员所属科室
		if(StringUtils.isNotBlank(searchModel.getOfficeId())){
			String []officeIds = searchModel.getOfficeId().split(",");
			for(int i=0;i<officeIds.length;i++){
				SysOperatorOfficeBean officeOperatorBean = new SysOperatorOfficeBean();
				long id = ServiceUtil.getSequence("SEQ_SYS_OPERATOR_OFFICE");
				officeOperatorBean.setId(Long.toString(id));
				officeOperatorBean.setRelationId("1");
				officeOperatorBean.setOperatorCode(searchModel.getOperatorCode().toUpperCase());
				officeOperatorBean.setOfficeId(officeIds[i].trim());
				Date dt = new Date();
				Timestamp time=new Timestamp(dt.getTime());
				officeOperatorBean.setCreateTimes(time);
				officeOperatorBean.setState("0");
				officeOperatorBean.setOrgId(searchModel.getOrgId());
				SysOperatorOfficeEngine.save(officeOperatorBean);
			}
		}
		//if(true)throw new Exception();
		//操作日志保存
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
		logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeAddUser,
				"operator code=" + newOperatorCode + ",name=" + searchModel.getOperatorName().trim());
	}
	/**
	 * 修改操作员
	 */
	public void updateOperator(String operatorCode,
							   SysOperatorModel searchModel) throws Exception {

		//根据操作员登陆编码获取操作员信息
		String newOperatorCode = searchModel.getOperatorCode().trim();
		String  condition = " operator_code = :operatorCode ";
		Map parameter = new HashMap();
		parameter.put("operatorCode", newOperatorCode);

		SysOperatorBean optBeans[] = SysOperatorEngine.getBeans(condition, parameter);
		if(optBeans == null || optBeans.length==0){
			return ;
		}

		//修改操作员信息
		SysOperatorBean optBean = optBeans[0];
		optBean.setOperatorName(searchModel.getOperatorName().trim());
		optBean.setOperatorState(searchModel.getOperatorState());
		optBean.setRemarks(searchModel.getRemarks().trim());
		optBean.setTelNo(searchModel.getTelNo());


		//所属机构保存 先删后增
		ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), "delete from SYS_OPERATOR2ORG where station_code='STAFF_BELONG_ORG' and operator_code='"+newOperatorCode+"'",null);
		String belong2OrgId = searchModel.getOrgId();
		if (StringUtils.isNotEmpty(belong2OrgId)) {
			this.saveOperator2Org(newOperatorCode, belong2OrgId);
			optBean.setOrgId(belong2OrgId);
		}

		SysOperatorEngine.save(optBean);
		if(StringUtils.isNotBlank(searchModel.getOfficeId())){
			String []officeIds = searchModel.getOfficeId().split(",");
			StringBuffer condition2 = new StringBuffer();
			condition2.append(" delete from  sys_operator_office t where t.operator_code='"+searchModel.getOperatorCode()+"'");
			ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), condition2.toString(),null);
			SysOperatorOfficeBean officeOperatorBeans = new SysOperatorOfficeBean();
			for(int i=0;i<officeIds.length;i++){
				if(StringUtils.isNotBlank(searchModel.getOperatorCode())&& StringUtils.isNotBlank(officeIds[i])){
					officeOperatorBeans = selectOperatorOffice(searchModel.getOperatorCode(),officeIds[i]);
					if(officeOperatorBeans == null){
						SysOperatorOfficeBean officeOperatorBean = new SysOperatorOfficeBean();
						long id = ServiceUtil.getSequence("SEQ_SYS_OPERATOR_OFFICE");
						officeOperatorBean.setId(Long.toString(id));
						officeOperatorBean.setRelationId("1");
						officeOperatorBean.setOperatorCode(searchModel.getOperatorCode());
						officeOperatorBean.setOfficeId(officeIds[i].trim());
						Date dt = new Date();
						Timestamp time=new Timestamp(dt.getTime());
						officeOperatorBean.setCreateTimes(time);
						officeOperatorBean.setState("0");
						officeOperatorBean.setOrgId(searchModel.getOrgId());
						SysOperatorOfficeEngine.save(officeOperatorBean);
					}
				}
			}
		}else{
			StringBuffer condition2 = new StringBuffer();
			condition2.append(" delete from  sys_operator_office t where t.operator_code='"+searchModel.getOperatorCode()+"'");
			ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), condition2.toString(),null);
		}
		//操作日志保存
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
		logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeUpdateUser,
				"operator code=" + newOperatorCode + ",name=" + searchModel.getOperatorName().trim());

	}
	private String getDecodePsw(String psw) {
		return MD5.toMD5(psw).toLowerCase();
	}

	//新建员工使用
	public void saveOperator2Org(String operatorCode, String orgIds) throws Exception {
		SysOperator2OrgBean sysOperator2OrgBean = new SysOperator2OrgBean();
		sysOperator2OrgBean.setOrgId(orgIds);
		sysOperator2OrgBean.setOperatorCode(operatorCode);
		sysOperator2OrgBean.setOrgType("0");
		sysOperator2OrgBean.setRemarks("所属机构分配");
		sysOperator2OrgBean.setStationCode(ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG);
		SysOperator2OrgEngine.save(sysOperator2OrgBean);
	}

	public SysOperatorOfficeBean selectOperatorOffice(String operatorCode,String officeId)throws Exception{
		StringBuffer condition = new StringBuffer(" 1=1");
		condition.append(" AND OPERATOR_CODE='"+operatorCode+"'");
		condition.append(" AND OFFICE_ID='"+officeId+"'");
		SysOperatorOfficeBean bean[] = null;
		bean = SysOperatorOfficeEngine.getBeans(condition.toString(), null);
		if(bean.length > 0 && bean!=null){
			return bean[0];
		}
		return  null;
	}

    public void setRoles(String operatorCode,String roleIds,String operatorCodeParam) throws Exception {


        //1.删除该用户已存在的角色
        //String delSql = "delete from sys_operator2role  where OPERATOR_CODE='"+searchModel.getOperatorCode()+"' AND ROLE_ID IN (SELECT B.ROLE_ID FROM SYS_ROLE B WHERE B.SYS_TYPE="+searchModel.getSysTypeRole()+")  " ;

        String delSql = "delete from sys_operator2role  where OPERATOR_CODE='"+operatorCodeParam+"' " ;
        //delSql+=" and role_id in(select tmp.role_id from (select * from sys_operator2role aa ) tmp where tmp.OPERATOR_CODE='"+operatorCode+"')";


        ServiceManager.getDataStore().execute(
                ServiceManager.getSession().getConnection(),
                delSql, null);

        if(!"".equals(roleIds) && null != roleIds){
            String[] ids = roleIds.split(",");

            SysOperator2roleBean[] beans = new SysOperator2roleBean[ids.length];
            for (int i = 0; i < ids.length; i++) {
                beans[i] = new SysOperator2roleBean();
                beans[i].setOperatorCode(operatorCodeParam);
                beans[i].setRoleId(Long.valueOf(ids[i]));

                //判断角色ID是否是pacs系统角色
                if(getRoleById(ids[i],"25")!=null){
                    ISysOperatorValue sysOper = getOperatorBeanByCode(operatorCodeParam);
                    //调用pacs好视通接口
                    sysPacsHstInterface(operatorCodeParam,sysOper.getOperatorName().trim(),sysOper.getOrgId());
                }
            }
            SysOperator2roleEngine.save(beans);
        }

//		 记录操作日志
        IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                .getService(IOperatorLogSV.class);
        logSV.saveOperatorLog(
                operatorCode,
                IOperatorLogSV.optTypeUpdateUserRole,"update operator code=" + operatorCode +
                        "operator code=" + operatorCodeParam + ",roleIds="
                        + roleIds);
    }

    public boolean sysPacsHstInterface(String userCode,String userName,String orgId) throws Exception{
    	boardSv.boardUserAdd(userCode,userName,orgId);
        return true;
    }

    public ISysRoleValue getRoleById(String roleId,String sysType) throws Exception {
        StringBuffer condition = new StringBuffer(" 1=1 ");
        Map parameter = new HashMap();
        condition.append(" and role_id = :roleId");
        condition.append(" and sys_type = :sysType");
        parameter.put("roleId", roleId);
        parameter.put("sysType", sysType);

        ISysRoleValue[] sysRoleValue = SysRoleEngine.getBeans(condition.toString(), parameter);
        if(sysRoleValue!=null&&sysRoleValue.length > 0){
            return sysRoleValue[0];
        }else{
            return null;
        }

    }

	public void deleteOperator2org(String operatorCodes, String operatorCode2)
			throws Exception {
		// TODO Auto-generated method stub
		String[] operatorCodeArray = operatorCode2.split(",");

//		if (ArrayUtils.contains(operatorCodeArray, "ADMIN")) {
//			throw new Exception("超级管理员不能删除！");
//		}
//		;
		//操作日志保存
		IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);

		for (String operatorCodeDelete : operatorCodeArray) {
			//操作员组织删除
			String[] idArray =  operatorCodeDelete.split("-");
			StringBuffer condition = new StringBuffer(" delete from sys_operator2org where 1=1 ");
			condition.append(" and operator_code = '"+idArray[0]+"'");
			condition.append(" and station_code = '"+idArray[1]+"'");
			condition.append(" and org_id = '"+idArray[2]+"'");
			ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(),
					condition.toString(), null);
//			SysOperator2orgBean optBean = SysOperator2orgEngine.getBeans(condition.toString(), parameter)[0];
//			SysOperatorBean optBean = SysOperatorEngine.getBean(operatorCodeDelete);
//			optBean.delete();
//			SysOperatorEngine.save(optBean);
		}
	}

	//不迭代子孙节点
	public String saveOperator2Org(String operatorCode, String orgIDS,String stationCode)
			throws Exception {
		String[] opt2OrgArray = orgIDS.split(",");
		for (String orgId : opt2OrgArray) {
			SysOperator2OrgBean sysOperator2OrgBean = new SysOperator2OrgBean();
			sysOperator2OrgBean.setOrgId(orgId);
			sysOperator2OrgBean.setOperatorCode(operatorCode);
			sysOperator2OrgBean.setRemarks("所属机构分配");
			sysOperator2OrgBean.setStationCode(stationCode);
			sysOperator2OrgBean.setOrgType("0");
			//检查是主键是否重复了
			boolean flag = this.checkDuplicate(sysOperator2OrgBean);
			if(flag != true){
				SysOperator2OrgEngine.save(sysOperator2OrgBean);
			}
		}
		return null;
	}

	private boolean checkDuplicate(SysOperator2OrgBean sysOperator2OrgBean) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer condition = new StringBuffer(" 1=1 ");
		Map param = new HashedMap();
		if(!StringUtils.isBlank(sysOperator2OrgBean.getOperatorCode())){
			condition.append(" and operator_code = :operator_code");
			param.put("operator_code", sysOperator2OrgBean.getOperatorCode());
		}
		if(!StringUtils.isBlank(sysOperator2OrgBean.getStationCode())){
			condition.append(" and station_code = :station_code");
			param.put("station_code", sysOperator2OrgBean.getStationCode());
		}
		if(!StringUtils.isBlank(sysOperator2OrgBean.getOrgId())){
			condition.append(" and org_id = :org_id");
			param.put("org_id", sysOperator2OrgBean.getOrgId());
		}

		int count = SysOperator2OrgEngine.getBeansCount(condition.toString(), param);
		if(count>0){
			return true;
		}
		return false;
	}

}
