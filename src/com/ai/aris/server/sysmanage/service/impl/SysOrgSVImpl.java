package com.ai.aris.server.sysmanage.service.impl;


import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.server.sysmanage.model.OrgManageSearchModel;
import com.ai.aris.server.sysmanage.model.SysOrgModel;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;
import com.ai.aris.server.sysmanage.service.interfaces.ISysOrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.sql.Timestamp;
import java.util.*;

public class SysOrgSVImpl implements ISysOrgSV {
	private IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);

	@Override
	public QueryOrgTreeBean[] getOrgTreeData(String pId) throws Exception {
		// TODO Auto-generated method stub
		/***/
		StringBuffer condition = new StringBuffer(" 1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		if(pId != null){
			condition.append(" AND PARENT_ORG_ID =:PARENT_ORG_ID");
			condition.append(" ORDER BY TO_NUMBER(ORG_ID) DESC ");
			map.put("PARENT_ORG_ID", pId);
		}
		return QueryOrgTreeEngine.getBeans(condition.toString(), map);
	}
	public QueryOrgTreeBean[] getOrgTreeDataById(String pId)throws Exception{
		StringBuffer condition = new StringBuffer(" 1=1");
		Map<String, Object> map = new HashMap<String, Object>();
		if(pId != null){
			condition.append(" AND ORG_ID =:ORG_ID");
			map.put("ORG_ID", pId);
		}
		return QueryOrgTreeEngine.getBeans(condition.toString(), map);
	}
	public static class OrgNode implements Comparable<OrgNode> {
		private String  pid, id;
		private String name;
		private List<OrgNode> children;
		private boolean isParent;

		private OrgNode(String pid, String id, String name, boolean isParent) {
			this.pid = pid;
			this.id = id;
			this.name = name;
			this.isParent = isParent;
		}


		public String toString() {
			return "{" +
					"'pid':" + pid +
					", 'id':" + id +
					", 'isParent':" + isParent +
					", 'name':'" + name.replaceAll("'", "\\'") + "'" +
					(children != null ? ", 'children':" + children : "") +
					'}';
		}

		private void addChild(OrgNode node) {
			if (this.children == null)
				this.children = new ArrayList<OrgNode>();
			this.children.add(node);
			Collections.sort(this.children);
		}


		public int compareTo(OrgNode orgNode) {
			return this.name.compareTo(orgNode.name);
		}

		public String getId() {
			return id;
		}

		public List<OrgNode> getChildren() {
			return children;
		}
	}
	public JSONArray getOperatorOrgTreeJson(Set<String> opOrgIds, String pId) throws Exception {
		List<OrgNode> opRoot = filterOrgTreeByOrgIds(opOrgIds, pId);
		Collections.sort(opRoot);
		JSONArray js = new JSONArray();
		JSONObject obj = null;
		for(int i=0;i<opRoot.size();i++){
			obj = new JSONObject();
			obj.put("id", opRoot.get(i).id);
			obj.put("name", opRoot.get(i).name);
			obj.put("isParent", opRoot.get(i).isParent);
			js.add(obj);
		}
		return js;
	}
	//查顶级节点
	public List<OrgNode> filterOrgTreeByOrgIds(Set<String> opOrgIds, String pId) throws Exception {
		List<OrgNode> rootArray = buildOrgTree(pId);

		List<OrgNode> opRoot = new ArrayList<OrgNode>();

		//遍历树
		Queue<OrgNode> nodes = new LinkedList<OrgNode>(rootArray);
		while (nodes.size() > 0) {
			OrgNode node = nodes.remove();
			if (opOrgIds.contains(node.id) ) {
				opRoot.add(node);
			} else {
				if (node.children != null) {
					nodes.addAll(node.children);
				}
			}

		}

		return opRoot;
	}
	private List<OrgNode> buildOrgTree(String pId) throws Exception {
		QueryOrgTreeBean[] orgs = this.getOrgTreeData(pId);
		Map<String, OrgNode> orgMap = new HashMap<String, OrgNode>();
		List<OrgNode> orgNodes = new ArrayList<OrgNode>();
		for (QueryOrgTreeBean org : orgs) {
			OrgNode orgJson = new OrgNode(org.getParentOrgId(), org.getOrgId(), org.getOrgName(), org.getChildren()==0? false : true);
			orgMap.put(org.getOrgId(), orgJson);
			orgNodes.add(orgJson);
		}
		List<OrgNode> rootArray = new ArrayList<OrgNode>();
		// 装配根节点
		for (OrgNode orgNode : orgNodes) {
			rootArray.add(orgNode);
		}
		return rootArray;
	}
	public ResultDTO queryPageList(String orgName, ResultDTO resultDTO, String operatorCode) throws Exception{
		StringBuffer condition = new StringBuffer();
		Map<String, Object> para = new HashMap<String, Object>();
		condition.append(" org_id != -1 \n");
		if (StringUtils.isNotEmpty(orgName)) {
			condition.append(" and ORG_NAME like :ORG_NAME \n");
			para.put("ORG_NAME", "%" + orgName + "%");
		}

		if (StringUtils.isNotEmpty(operatorCode)) {
			//权限过滤
			String stationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
			condition.append(" start with org_id in (select t.org_id "
					+"	                         from sys_operator2org t "
					+"	                        where t.operator_code = '"+operatorCode+"' "
					+"	                          and t.org_type = 0 "
					+"	                          and t.station_code = '"+stationCode+"') "
					+"	connect by prior org_id = parent_org_id ");
		}
		condition.append(" and state = 1 ");
		int total = SysOrgEngine.getBeansCount(condition.toString(), para);
		SysOrgBean[] beans = null;
		//有数据；在总记录数 >0 的时候，再去获取分页记录数
		if (total > 0) {
			condition.append(" order by ORG_ID");
			beans = SysOrgEngine.getBeans(null, condition.toString(), para, resultDTO.getStart(), resultDTO.getEnd(), false);
			resultDTO.setRows(BeanUtils.beanToList(beans, SysOrgModel.class));
			resultDTO.setRecords(total);
		}
		return resultDTO;
	}
	public SysOrgBean getOrgBeanByOrgId(String orgId) throws Exception {
		return SysOrgEngine.getBean(orgId);
	}
	/**
	 * 修改组织信息
	 */
	public void updateOrgInfo(String operatorCode, SysOrgBean bean, OrgManageSearchModel searchModel) throws Exception {
		String optType = IOperatorLogSV.optTypeUpdateOrg;
		Timestamp dateTime=new Timestamp(new Date().getTime());
		SysOrgBean oldBean = new SysOrgBean();
		oldBean.setOrgId(bean.getOrgId());
		oldBean.setStsToOld();
		DataContainerFactory.copyNoClearData(bean, oldBean);
		oldBean.setUpdateTime(dateTime);
		oldBean.setUpdateUserId(operatorCode);
		//设置结构唯一码
		oldBean.setDuns(bean.getDuns());

		//政府办机构隶属行政区划代码
		String sdCode = "";
		if(!"".equals(searchModel.getVillageCode()) && searchModel.getVillageCode() != null){
			sdCode = searchModel.getVillageCode().split(",")[0];
		}else if(!"".equals(searchModel.getStreetCode()) && searchModel.getStreetCode() != null){
			sdCode = searchModel.getStreetCode().split(",")[0];
		}else if(!"".equals(searchModel.getDistrictCode()) && searchModel.getDistrictCode() != null){
			sdCode = searchModel.getDistrictCode().split(",")[0];
		}else if(!"".equals(searchModel.getCityCode()) && searchModel.getCityCode() != null){
			sdCode = searchModel.getCityCode().split(",")[0];
		}else if(!"".equals(searchModel.getProvinceCode()) && searchModel.getProvinceCode() != null){
			sdCode = searchModel.getProvinceCode().split(",")[0];
		}
		oldBean.setSubordinateDivisionCode(sdCode);

		//所属区域处理
		if(bean.getProvinceCode() != null && !"".equals(bean.getProvinceCode())){
			oldBean.setProvinceCode(bean.getProvinceCode().split(",")[0]);
		}
		if(bean.getCityCode() != null && !"".equals(bean.getCityCode())){
			oldBean.setCityCode(bean.getCityCode().split(",")[0]);
		}
		if(bean.getDistrictCode() != null && !"".equals(bean.getDistrictCode())){
			oldBean.setDistrictCode(bean.getDistrictCode().split(",")[0]);
		}
		if(bean.getStreetCode() != null && !"".equals(bean.getStreetCode())){
			oldBean.setStreetCode(bean.getStreetCode().split(",")[0]);
		}
		if(bean.getVillageCode() != null && !"".equals(bean.getVillageCode())){
			oldBean.setVillageCode(bean.getVillageCode().split(",")[0]);
		}

		SysOrgEngine.save(oldBean);
		logSV.saveOperatorLog(operatorCode, optType, String.valueOf(bean.getOrgId()));
	}
	/** 校验组织编码或组织名称是否重复*/
	public SysOrgBean checkOrgBean(SysOrgBean bean) throws Exception{

		StringBuffer condition = new StringBuffer(" 1=1");
		condition.append(" AND ( ORG_CODE = '" + bean.getOrgCode().trim()+"'");
		condition.append(" OR ORG_NAME = '" + bean.getOrgName().trim()+"' )");
		condition.append(" AND STATE = 1 ");

		SysOrgBean[] beans =  SysOrgEngine.getBeans(condition.toString(), null);

		if(beans.length != 0 && beans != null){
			return beans[0];
		}else{
			return null;
		}
	}
	public String insertOrgInfo(String operatorCode, SysOrgBean bean, OrgManageSearchModel searchModel) throws Exception {
		String optType = IOperatorLogSV.optTypeAddOrg;
		//添加组织机构
		Timestamp dateTime=new Timestamp(new Date().getTime());
		String orgId = String.valueOf(SysOrgEngine.getNewId());
		bean.setOrgId(orgId);
		//bean.setOrgCode(orgId);
		bean.setCreateTime(dateTime);
		bean.setUpdateTime(dateTime);
		bean.setCreateUserId(operatorCode);
		bean.setOrgLevel(bean.getOrgLevel()+1);
		bean.setOrgType("0");
		//设置结构唯一码
		bean.setDuns(bean.getDuns());
		//所属区域处理
		if(bean.getProvinceCode() != null && !"".equals(bean.getProvinceCode())){
			bean.setProvinceCode(bean.getProvinceCode().split(",")[0]);
		}
		if(bean.getCityCode() != null && !"".equals(bean.getCityCode())){
			bean.setCityCode(bean.getCityCode().split(",")[0]);
		}
		if(bean.getDistrictCode() != null && !"".equals(bean.getDistrictCode())){
			bean.setDistrictCode(bean.getDistrictCode().split(",")[0]);
		}
		if(bean.getStreetCode() != null && !"".equals(bean.getStreetCode())){
			bean.setStreetCode(bean.getStreetCode().split(",")[0]);
		}
		if(bean.getVillageCode() != null && !"".equals(bean.getVillageCode())){
			bean.setVillageCode(bean.getVillageCode().split(",")[0]);
		}
		//政府办机构隶属行政区划代码
		String sdCode = "";
		if(StringUtils.isNotBlank(searchModel.getVillageCode())){
			sdCode = searchModel.getVillageCode().split(",")[0];
		}else if(StringUtils.isNotBlank(searchModel.getStreetCode())){
			sdCode = searchModel.getStreetCode().split(",")[0];
		}else if(StringUtils.isNotBlank(searchModel.getDistrictCode())){
			sdCode = searchModel.getDistrictCode().split(",")[0];
		}else if(StringUtils.isNotBlank(searchModel.getCityCode())){
			sdCode = searchModel.getCityCode().split(",")[0];
		}else if(StringUtils.isNotBlank(searchModel.getProvinceCode())){
			sdCode = searchModel.getProvinceCode().split(",")[0];
		}
		bean.setSubordinateDivisionCode(sdCode);
		SysOrgEngine.save(bean);
		logSV.saveOperatorLog(operatorCode, optType, String.valueOf(bean.getOrgId()));
		return orgId;
	}
	@Override
	public Boolean checkDunsUID(String duns,String type) throws Exception {
		StringBuffer condition = new StringBuffer();
		condition.append("  duns = '" + duns.trim()+"'");
		QuerySysOrgDunsBean[] beans =  QuerySysOrgDunsEngine.getBeans(condition.toString(), null);
		if("0".equals(type)&&beans.length >0){
			return true;
		}else if ("1".equals(type)&&beans.length >1) {
			return  true;
		}else{
			return false;
		}
	}
	public int getSubOrgCountByOrgId(String orgId) throws Exception {
		String sql = " PARENT_ORG_ID = :S_PARENT_ORG_ID ";
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("S_PARENT_ORG_ID", orgId);
		int total = SysOrgEngine.getBeansCount(sql, p);

		return total;
	}
	public void deleteOrg(String operatorCode, String orgIdsToDel) throws Exception {
		String[] ids = orgIdsToDel.split(",");
		Set<String> idSet = new HashSet<String>();
		for (String id : ids) {
			idSet.add(id);
		}
		Queue<OrgNode> orgNodes = new LinkedList<OrgNode>(filterOrgTreeByOrgIds(idSet, null));

		while (orgNodes.size() > 0) {
			OrgNode org = orgNodes.remove();
			//变更一下，存在子孙机构进，不能删除
			//if (org.children != null) orgNodes.addAll(org.children);
			if(org.children == null){
				// 删除组织
				SysOrgBean bean = new SysOrgBean();
				bean.setOrgId(org.id);
				bean.setStsToOld();
				bean.delete();
				SysOrgEngine.save(bean);
				//删除权限
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("org_id", bean.getOrgId());
				SysOperator2OrgBean[] o2oBeans = SysOperator2OrgEngine.getBeans(" org_id = :org_id ", p);
				for (SysOperator2OrgBean o2oBean : o2oBeans) {
					o2oBean.delete();
				}
				SysOperator2OrgEngine.saveBatch(o2oBeans);
			}
		}
		logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeDelOrg, orgIdsToDel);
	}
}

