package com.ai.aris.server.sysmanage.service.interfaces;



import com.ai.aris.server.sysmanage.bean.QueryOrgTreeBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.model.OrgManageSearchModel;
import com.ai.common.domain.ResultDTO;
import net.sf.json.JSONArray;

import java.util.Set;

public interface ISysOrgSV {
	public QueryOrgTreeBean[] getOrgTreeData(String pId)throws Exception;
	public QueryOrgTreeBean[] getOrgTreeDataById(String pId)throws Exception;
	JSONArray getOperatorOrgTreeJson(Set<String> opOrgIds, String pId) throws Exception;
	public ResultDTO queryPageList(String orgName, ResultDTO resultDTO,String operatorCode) throws Exception;
	SysOrgBean getOrgBeanByOrgId(String orgId) throws Exception;
	public void updateOrgInfo(String operatorCode, SysOrgBean bean, OrgManageSearchModel searchModel) throws Exception;
	public SysOrgBean checkOrgBean(SysOrgBean bean) throws Exception;
	public String insertOrgInfo(String operatorCode, SysOrgBean bean,OrgManageSearchModel searchModel) throws Exception;
	public Boolean checkDunsUID(String duns,String type)throws Exception;
	public int getSubOrgCountByOrgId(String orgId) throws Exception;
	void deleteOrg(String operatorCode, String orgIdsToDel) throws Exception;
}
