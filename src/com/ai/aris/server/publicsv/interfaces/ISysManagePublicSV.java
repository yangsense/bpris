package com.ai.aris.server.publicsv.interfaces;

import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;

/**
 * 系统管理对外提供的公共服务
 * @author Administrator
 *
 */
public interface ISysManagePublicSV {
	
	//系统自带岗位：员工属于某个组织
	public String STATIONCODE_STAFF_BELONG_ORG ="STAFF_BELONG_ORG";
	//系统自带岗位：健康档案管理员
	public String STATIONCODE_HEALTH_MANAGER ="HEALTH_MANAGER";

	SysOrgBean[] getAllOrgBeans()throws Exception;

	SysOperator2OrgBean getSysOperator2org(String operatorCode , String stationCode)throws Exception;

	String getOrgNameByOrgId(String orgId)throws Exception;

	public SysOrgBean[] getOperatorRelateOrgInfoByOperatorCode(String operatorCode, String stationCode, boolean isInclueSubOrg) throws Exception;

	public SysOrgBean getOperatorBelongOrg(String operatorCode)	throws Exception;

}
