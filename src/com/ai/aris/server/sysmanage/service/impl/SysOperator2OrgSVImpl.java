package com.ai.aris.server.sysmanage.service.impl;

import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.server.sysmanage.model.QuerySysOperator2OrgModel;
import com.ai.aris.server.sysmanage.service.interfaces.ISysOperator2OrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SysOperator2OrgSVImpl implements ISysOperator2OrgSV {



	//操作员-岗位授权管理-新增组织权限树
	public QueryOrgTreeBean[] queryOperatorOrgs(String orgName, String operatorCode, String orgId,
												String station_code, boolean isFilterParentId) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer condition = new StringBuffer();		 
		Map<String, Object> para = new HashMap<String, Object>();
		
		//跌代滤重，sql只能这样写了
		condition.append(" SELECT t.*,(select count(1) from SYS_ORG a where a.parent_org_id = t.org_id) children  FROM SYS_ORG t WHERE ORG_ID IN (SELECT DISTINCT ORG_ID FROM SYS_ORG WHERE  1 = 1 ");
		
		if(StringUtils.isNotBlank(orgId)){
			if(null !=orgName && !"".equals(orgName)){ 
			}else if(isFilterParentId){
				condition.append(" and parent_org_id=:org_id ");
				para.put("org_id", orgId);
			}else{
				condition.append(" and org_id=:org_id ");
				para.put("org_id", orgId);
			}
			
		}

		if(StringUtils.isNotBlank(operatorCode)){
			if(isFilterParentId){
				condition.append(" start with ORG_ID in (select t.org_id from sys_operator2org t where t.operator_code = '"+operatorCode+"' and t.ORG_TYPE = 0 AND t.station_code='"+station_code+"' ) connect by prior ORG_ID = PARENT_ORG_ID ");
			}else{
				condition.append(" AND ORG_ID in (select t.org_id from sys_operator2org t where t.operator_code = '"+operatorCode+"' and t.ORG_TYPE = 0 AND t.station_code='"+station_code+"' )  ");
			}			
		}else{
			return null;
		}		 
		condition.append(") and state = 1 ");

		if(null !=orgName && !"".equals(orgName)){
			condition.append(" and org_name like '%"+orgName+"%'");
		}
		condition.append(" order by org_id ");
		QueryOrgTreeBean[] beans = 	QueryOrgTreeEngine.getBeansFromSql(condition.toString(), para);
		
		return beans;
	}
	public QueryOrgTreeBean[] queryOperatorOrgsByCurrentOrgId(String orgName, String operatorCode, String orgId,
												String station_code, boolean isFilterParentId,String currentOrgId)throws Exception{
		StringBuffer condition = new StringBuffer();
		Map<String, Object> para = new HashMap<String, Object>();

		//跌代滤重，sql只能这样写了
		condition.append(" SELECT t.*,(select count(1) from SYS_ORG a where a.parent_org_id = t.org_id) children  FROM SYS_ORG t WHERE ORG_ID IN (SELECT DISTINCT ORG_ID FROM SYS_ORG WHERE  1 = 1 ");

		if(StringUtils.isNotBlank(orgId)){
			if(null !=orgName && !"".equals(orgName)){
			}else if(isFilterParentId){
				condition.append(" and parent_org_id=:org_id ");
				para.put("org_id", orgId);
			}else{
				condition.append(" and org_id=:org_id ");
				para.put("org_id", orgId);
			}

		}

		if(StringUtils.isNotBlank(operatorCode)){
			if(isFilterParentId){
				condition.append(" start with ORG_ID in (select t.org_id from sys_operator2org t where t.operator_code = '"+operatorCode+"' and t.ORG_TYPE = 0 AND t.station_code='"+station_code+"' ) connect by prior ORG_ID = PARENT_ORG_ID ");
			}else{
				condition.append(" AND ORG_ID in (select t.org_id from sys_operator2org t where t.operator_code = '"+operatorCode+"' and t.ORG_TYPE = 0 AND t.station_code='"+station_code+"' )  ");
			}
		}else{
			return null;
		}
		condition.append(") and state = 1 ");

		if(null !=orgName && !"".equals(orgName)){
			condition.append(" and org_name like '%"+orgName+"%'");
		}
		if(StringUtils.isNotBlank(currentOrgId) && !"-1".equals(currentOrgId)){
			condition.append(" and org_id=:currentOrgId");
			para.put("currentOrgId", currentOrgId);
		}
		condition.append(" order by org_id ");
		QueryOrgTreeBean[] beans = 	QueryOrgTreeEngine.getBeansFromSql(condition.toString(), para);
		if(StringUtils.isNotBlank(currentOrgId) && !"-1".equals(currentOrgId)){
			for(QueryOrgTreeBean queryOrgTreeBean:beans){
				queryOrgTreeBean.setChildren(0);
			}
		}


		return beans;

	}
	public SysOrgBean[]  queryOrgsByStationCode2(String operatorCode, String stationCode/*,String orgName*/) throws Exception {
		StringBuffer condition = new StringBuffer();
		Map<String, Object> para = new HashMap<String, Object>();

		//跌代滤重，sql只能这样写了
		condition.append(" SELECT DISTINCT * FROM SYS_ORG WHERE  1 = 1 ");

		condition.append(" and org_TYPE = 0 ");
		if(StringUtils.isNotBlank(operatorCode)){
			condition.append("and ORG_ID in (select t.org_id from sys_operator2org t where t.operator_code = '"+operatorCode+"' and t.ORG_TYPE = 0 and t.station_code = '"+stationCode+"') ");
			/*if(StringUtils.isNotBlank(orgName)){
				condition.append(" and org_name like '%"+orgName+"%'");
			}else{
				return null;
			}*/
		}else{
			return null;
		}

		condition.append(" order by org_id");

		SysOrgBean[] beans = SysOrgEngine.getBeansFromSql(condition.toString(),para);

		return beans;
	}

	public QuerySysStationBean[] queryOperatorStations(String operatorCode,
													   String stationCode, String sysType) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1 ");
		Map<String, Object> para = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(stationCode)){
			condition.append(" and STATION_CODE=:stationCode ");
			para.put("stationCode", stationCode);
		}
		if(StringUtils.isNotBlank(operatorCode)){
			condition.append(" and station_code in(select t.station_code from sys_operator2org t where t.operator_code='"+operatorCode+"' and t.org_type='0' and station_code != '"+ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG+"' )  ");
		}
		if(StringUtils.isNotBlank(sysType)){
			condition.append(" and SYS_TYPE=:SYS_TYPE ");
			para.put("SYS_TYPE", sysType);
		}
		condition.append(" order by station_code");

		return QuerySysStationEngine.getBeans(condition.toString(), para);
//		return SysRoleEngine.getBeans(condition.toString(), para);
	}

	//操作员组织机构列表查询
	public ResultDTO queryOperator2orgInfo(String operatorCode,
										ResultDTO resultDTO ) throws Exception {

		String statoinCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
		StringBuilder sqlBuf = new StringBuilder(" 1=1 ");
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(operatorCode)) {
			sqlBuf.append(" AND OPERATOR_CODE = :S_OPERATOR_CODE");
			params.put("S_OPERATOR_CODE", operatorCode);
			sqlBuf.append(" AND STATION_CODE != :S_STATION_CODE");
			params.put("S_STATION_CODE", statoinCode);
		}else{
			return null;
		}

		int total = QuerySysOperator2OrgEngine.getBeansCount(sqlBuf.toString(), params);
		QuerySysOperator2OrgBean[] beans = null;
		//有数据；在总记录数 >0 的时候，再去获取分页记录数，
		if (total > 0) {
			beans = QuerySysOperator2OrgEngine.getBeans(null, sqlBuf.toString(), params, resultDTO.getStart(), resultDTO.getEnd(), false);
		}

		resultDTO.setRows(BeanUtils.beanToList(beans,QuerySysOperator2OrgModel.class));
		resultDTO.setRecords(total);
		return resultDTO;
	}

	//操作员归属机构查询-用于新增岗位中的岗位赋权使用
	public SysOperator2OrgBean  queryOperatorBelongOrg(String operatorCode) throws Exception {
		StringBuilder sqlBuf = new StringBuilder(" 1=1 ");
		Map<String, String> params = new HashMap<String, String>();
		if (!StringUtils.isBlank(operatorCode)) {
			sqlBuf.append(" and operator_code = :operator_code and station_code = :station_code" );
			params.put("operator_code", operatorCode);
			params.put("station_code", ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG);
		}else{
			return null;
		}
		SysOperator2OrgBean[] beans = SysOperator2OrgEngine.getBeans(sqlBuf.toString(), params);
		if(beans!=null){
			return beans[0];
		}else{
			return null;
		}
	}
}
