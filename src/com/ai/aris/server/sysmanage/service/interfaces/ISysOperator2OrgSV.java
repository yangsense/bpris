package com.ai.aris.server.sysmanage.service.interfaces;

import com.ai.aris.server.sysmanage.bean.QueryOrgTreeBean;
import com.ai.aris.server.sysmanage.bean.QuerySysStationBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.common.domain.ResultDTO;

public interface ISysOperator2OrgSV {

    /**
     * 查询当前操作员拥有的机构
     * @param operatorCode
     * @param orgId
     * @param stationCode
     * @return
     * @throws Exception
     */
	public QueryOrgTreeBean[] queryOperatorOrgs(String orgName, String operatorCode, String orgId,
												String stationCode, boolean isFilterParentId)throws Exception;

	public QueryOrgTreeBean[] queryOperatorOrgsByCurrentOrgId(String orgName, String operatorCode, String orgId,
												String stationCode, boolean isFilterParentId,String currentOrgId)throws Exception;

	SysOrgBean[] queryOrgsByStationCode2(String operatorCode, String stationCode/*,String orgName*/) throws Exception ;

	QuerySysStationBean[] queryOperatorStations(String operatorCode,
												String stationCode, String sysType)throws Exception;
	ResultDTO queryOperator2orgInfo(String operatorCode,ResultDTO resultDTO )throws Exception;

	SysOperator2OrgBean queryOperatorBelongOrg(String operatorCode) throws Exception ;
}
