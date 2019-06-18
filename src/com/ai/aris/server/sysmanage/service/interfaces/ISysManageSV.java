package com.ai.aris.server.sysmanage.service.interfaces;
  
import com.ai.aris.server.sysmanage.bean.ISysOperatorValue;
import com.ai.aris.server.sysmanage.bean.ISysOrgValue;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;

/**
 * 
 * @author wuliangyong
 */
public interface ISysManageSV {
	 /**
	 * 提供外部系统调用权限管理，返回menuBean
	 * @param 
	 * @return SysMenuBean[]
	 * @throws
	 */
	public SysMenuBean[] queryMenuList(String operatorCode, String appType)throws Exception;
	
	/**
	 * 提供外部系统调用权限管理(按钮权限)，返回menuBean
	 * @param 
	 * @return SysMenuBean[]
	 * @throws
	 */
	public SysMenuBean[] queryButtonList(String operatorCode, String appType)throws Exception;

	//获取工号归属组织
	public ISysOrgValue[] getBelongOrgByOprCode(String operatorCode) throws Exception;

	public SysOperator2OrgBean[]  queryOperator2OrgBeans(String operatorCode) throws Exception ;

	/**
	 * 根据组织ID，获取组织对应的信息
	 */
	public SysOrgBean getOrgBeanByOrgId(String orgId) throws Exception;

	/**
	 * 用户登陆接口，如果登陆失败，以异常的方式将错误抛出
	 * @param operatorCode 登陆工号
	 * @param psw   操作员密码，明文传递
	 * @param clientIP   客户端IP
	 * @throws Exception
	 */
	public ISysOperatorValue login(String operatorCode , String psw , String clientIP )throws Exception;
	public ISysOperatorValue login(String operatorCode, String clientIP) throws Exception;


	public SysOrgBean[] querySysOrgMangeBeans(String operatorCode,String sysType,String filterInfo) throws Exception ;

	//取操作员信息
	public SysOperatorBean[] queryOperatorBeans(String orgId) throws Exception ;


	/**
	 * 根据编码获取组织信息
	 *
	 * @return
	 * @throws Exception
	 */
	public SysOrgBean getOrgBeanByOrgCode(String orgCode) throws Exception;
	
	
	public void changeUserPasswordWithoutCheck(String operatorCode,
            String newPassword, String currentOpt) throws Exception;
	
	public String changeMyPasswordWithCheck(String operatorCode,
            String oldPassword, String newPassword) throws Exception;
	
}
