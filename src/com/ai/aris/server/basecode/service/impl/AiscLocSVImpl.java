package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscConorganizationBean;
import com.ai.aris.server.basecode.bean.AiscConorganizationEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaBean;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaEngine;
import com.ai.aris.server.basecode.bean.AiscServerInfoEngine;
import com.ai.aris.server.basecode.bean.QryLocStudyStatusBean;
import com.ai.aris.server.basecode.bean.QryLocStudyStatusEngine;
import com.ai.aris.server.basecode.bean.QueryAiscOrdcategoryBean;
import com.ai.aris.server.basecode.bean.QueryAiscOrdcategoryEngine;
import com.ai.aris.server.basecode.bean.QueryOrgLocBean;
import com.ai.aris.server.basecode.bean.QueryOrgLocEngine;
import com.ai.aris.server.basecode.bean.queryAiscConorganizationBean;
import com.ai.aris.server.basecode.bean.queryAiscConorganizationEngine;
import com.ai.aris.server.basecode.model.AiscConsult;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.aris.server.basecode.model.AiscServerInfo;
import com.ai.aris.server.basecode.service.interfaces.IAiscLocSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.webservice.bean.SysOrg;
import com.ai.aris.web.controller.basecode.AiscLocController;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
import com.jcraft.jsch.Logger;

public class AiscLocSVImpl implements IAiscLocSV {
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
            .getService(IDictItemSV.class);
    private Log logger = LogFactory.getLog(AiscLocController.class);
    @Override
    public ResultDTO queryPageList(AiscLoc model, ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount = null;
    	try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = " select * from (select B.*,rownum as row_index from ( select LOC_ID,LOC_CODE,LOC_INDEX,LOC_DESC," +
	                " LOC_ENDESC,LOC_PHONE,LOC_ADDRESS,SERVER_NAME,ORG_NAME,a.ORG_ID," +
	                "(select item_name from sys_dictitem where dict_name='LOC_TYPE' and item_no=a.loc_type) LOC_TYPE_DESC" +
	                " from aisc_loc a,aisc_severinfo b,sys_org c where a.server_id=b.server_id(+) and a.org_id=c.org_id ";
	        String sqlParam = "";
	        if (isNotBlank(String.valueOf(model.getLocCode()))) {
	            sqlParam += " and a.Loc_Code like '%" + model.getLocCode() + "%' ";
	        }
	        if (isNotBlank(String.valueOf(model.getLocDesc()))) {
	            sqlParam += " and a.Loc_Desc like '%" + model.getLocDesc() + "%' ";
	        }
	        if (!model.getLocType().equals("-1") && isNotBlank(String.valueOf(model.getLocType()))) {
	            sqlParam += " and a.Loc_Type = '" + model.getLocType() + "' ";
	        }
	        if (!model.getOrgId().equals("-1") && isNotBlank(model.getOrgId())) {
	            sqlParam += " and c.org_id = '" + model.getOrgId() + "' ";
	        }
	        int number = resultDTO.getLimit() * resultDTO.getPage();
	        int rowindex = 1 + (number - resultDTO.getLimit());
	        String sqlOrder = "ORDER BY a.Loc_ID,a.Loc_Code desc ) B where rownum<=" + number + ") where  row_index >=" + rowindex + " and row_index <= " + number + " ";
	        rs = stmt.executeQuery(sql + sqlParam + sqlOrder);
	        List<AiscLoc> list = new ArrayList();
	        AiscLoc aiscLoc = null;
	        while (rs.next()) {
	            aiscLoc = new AiscLoc();
	            aiscLoc.setLocId(rs.getLong("LOC_ID"));
	            aiscLoc.setHzlocId(rs.getLong("LOC_ID"));
	            aiscLoc.setLocCode(rs.getString("LOC_CODE") == null ? "" : rs.getString("LOC_CODE"));
	            aiscLoc.setLocIndex(rs.getString("LOC_INDEX") == null ? "" : rs.getString("LOC_INDEX"));
	            aiscLoc.setLocDesc(rs.getString("LOC_DESC") == null ? "" : rs.getString("LOC_DESC"));
	            aiscLoc.setLocEndesc(rs.getString("LOC_ENDESC") == null ? "" : rs.getString("LOC_ENDESC"));
	            aiscLoc.setLocPhone(rs.getString("LOC_PHONE") == null ? "" : rs.getString("LOC_PHONE"));
	            aiscLoc.setLocAddress(rs.getString("LOC_ADDRESS") == null ? "" : rs.getString("LOC_ADDRESS"));
	            aiscLoc.setServerName(rs.getString("SERVER_NAME") == null ? "" : rs.getString("SERVER_NAME"));
	            aiscLoc.setOrgName(rs.getString("ORG_NAME") == null ? "" : rs.getString("ORG_NAME"));
	            aiscLoc.setOrgId(rs.getString("ORG_ID") == null ? "" : rs.getString("ORG_ID"));
	            aiscLoc.setLocTypeDesc(rs.getString("LOC_TYPE_DESC") == null ? "" : rs.getString("LOC_TYPE_DESC"));
	            list.add(aiscLoc);
	        }
	        String sqlCount = "select count(*) COUNT from aisc_loc a,aisc_severinfo b,sys_org c where a.server_id=b.server_id(+) and a.org_id=c.org_id" + sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if (rsCount.next()) {
	            total = rsCount.getInt("COUNT");
	        }
	        AiscLoc[] loclist = new AiscLoc[list.size()];
	        if (list != null && list.size() > 0) {
	            for (int i = 0; i < list.size(); i++) {
	                loclist[i] = list.get(i);
	            }
	        }
    	
	        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist, AiscLoc.class, dicts));
	//        dicts.put("locType", new DictTranslator("locType","LOC_TYPE","locTypeDesc"));
	        resultDTO.setRecords(total);
    	}catch (Exception e) {
			logger.error(e.getMessage());
		}
        finally{
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return resultDTO;

    }

    @Override
    public ResultDTO queryStudyStaLocList(AiscLoc model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        Map<String, String> map = new HashMap();
        condition.append(" 1=1 ");
        if (!"-1".equals(model.getStudyStatus()) && StringUtils.isNotEmpty(model.getStudyStatus())) {
            condition.append(" and STUDY_STATUS = :STUDY_STATUS");
            map.put("STUDY_STATUS", model.getStudyStatus());
        }
        if (model.getLocId() != -1 && isNotBlank(String.valueOf(model.getLocId()))) {
            condition.append(" and LOC_ID = " + model.getLocId());
        }
        if (!model.getOrgId().equals("-1") && isNotBlank(model.getOrgId())) {
            condition.append(" and ORG_ID = :ORG_ID");
            map.put("ORG_ID", model.getOrgId());
        }
//        int count = QryLocStudyStatusEngine.getBeansCount(condition.toString(), map);
//        if (count == 0) return null;
//        beans = QryLocStudyStatusEngine.getBeans(condition.toString(), map);
//        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
//        resultDTO.setRows(BeanUtils.beanToList(beans, AiscLoc.class, dicts));
//        resultDTO.setRecords(beans.length);
        int total = QryLocStudyStatusEngine.getBeansCount(condition.toString(),map);
        QryLocStudyStatusBean[] beans = null;
        if (total > 0) {
            beans = QryLocStudyStatusEngine.getBeans(null, condition.toString(), map, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscLoc.class));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public List<Map> queryDict(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName", map);
        List<Map> list = new ArrayList();
        for (DictItemBean dictItemBean : dictItemBeans) {
            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
        }
        return list;
    }

    @Override
    public AiscLocBean getAiscLoc(long id) throws Exception {
        return AiscLocEngine.getBean(id);
    }

    @Override
    public boolean deleteAiscLoc(long id) throws Exception {
        AiscLocBean bean = getAiscLoc(id);
        bean.setStsToOld();
        bean.delete();
        try {
            AiscLocEngine.save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void saveAiscLoc(AiscLocBean bean) throws Exception {
        if (bean.getLocId() == 0) {
            long id = ServiceUtil.getSequence("SEQLOCID");
            bean.setLocId(id);
            AiscLocEngine.save(bean);
        } else {
            AiscLocBean oldBean = AiscLocEngine.getBean(bean.getLocId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscLocEngine.save(oldBean);
        }
    }

    public Boolean checkIsExist(AiscLocBean bean) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(String.valueOf(bean.getLocId())) && bean.getLocId() != 0) {
            condition.append(" and loc_id <> " + bean.getLocId());
        }
        if (isNotBlank(bean.getLocCode())) {
            condition.append(" and loc_Code = '" + bean.getLocCode() + "'");
        }
        if (isNotBlank(bean.getOrgId()) && !"-1".equals(bean.getOrgId())) {
            condition.append(" and org_Id = " + bean.getOrgId());
        }

        int count = AiscLocEngine.getBeansCount(condition.toString(), null);
        if (count > 0) {
            return true;
        } else return false;
    }


    @Override
    public List<Map> getLocNameSelect(String orgId) throws Exception {
        Map map = new HashMap();
        map.put("orgId", orgId);
        AiscLocBean[] beans = AiscLocEngine.getBeans(" org_id = :orgId ORDER BY loc_desc ASC", map);
        List<Map> list = new ArrayList();
        for (AiscLocBean aiscLocBean : beans) {
            list.add(ServiceUtil.transerBeanToMap(aiscLocBean));
        }
        return list;
    }

    @Override
    public List<Map> getAiscLocByType(String orgId, String locType) throws Exception {
        Map map = new HashMap();
        map.put("orgId", orgId);
        map.put("locType", locType);
        AiscLocBean[] beans = AiscLocEngine.getBeans(" org_id = :orgId and loc_type=:locType ORDER BY loc_desc ASC", map);
        List<Map> list = new ArrayList();
        for (AiscLocBean aiscLocBean : beans) {
            list.add(ServiceUtil.transerBeanToMap(aiscLocBean));
        }
        return list;
    }

    @Override
    public boolean checkLocCode(long locId, String locCode, String orgId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (locId != 0) {
            condition.append(" and loc_id <> " + locId);
        }
        if (isNotBlank(locCode)) {
            condition.append(" and loc_code = '" + locCode + "' ");
        }
        if (isNotBlank(orgId)) {
            condition.append(" and org_id = '" + orgId + "' ");
        }
        return AiscLocEngine.getBeansCount(condition.toString(), null) == 0;
    }

    public AiscLocBean getAiscLoc(String locCode) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" loc_code = '" + locCode + "' ");
        AiscLocBean[] beans = AiscLocEngine.getBeans(condition.toString(), null);
        AiscLocBean bean;
        if (beans != null && beans.length > 0) {
            bean = beans[0];
        } else {
            bean = new AiscLocBean();
        }
        return bean;
    }

    @Override
    public AiscLocBean getAiscLoc(String orgId, String locCode)
            throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" and org_id = " + orgId);
        }
        if (isNotBlank(locCode)) {
            condition.append(" and loc_code = '" + locCode + "' ");
        }
        AiscLocBean[] beans = AiscLocEngine.getBeans(condition.toString(), null);
        if (beans.length > 0) {
            return beans[0];
        } else {
            return new AiscLocBean();
        }
    }

    @Override
    public AiscCareProvBean getCareprovId(String orgId, String careprovCode) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" and org_id = " + orgId);
        }
        if (isNotBlank(careprovCode)) {
            condition.append(" and careprov_code = '" + careprovCode + "' ");
        }
        AiscCareProvBean[] beans = AiscCareProvEngine.getBeans(condition.toString(), null);
        if (beans.length > 0) {
            return beans[0];
        } else {
            return new AiscCareProvBean();
        }
    }

    @Override
    public AiscLocBean getAiscLocById(String orgId, String locId)
            throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" and org_id = " + orgId);
        }
        if (isNotBlank(locId)) {
            condition.append(" and loc_id = '" + locId + "' ");
        }
        AiscLocBean[] beans = AiscLocEngine.getBeans(condition.toString(), null);
        if (beans.length > 0) {
            return beans[0];
        } else {
            return new AiscLocBean();
        }
    }

    public String getOrgDuns(String orgId) throws Exception {

        Statement stmt = null;
        ResultSet rs = null;
        String duns = "";
        try {
            stmt = ServiceManager.getSession().getConnection()
                    .createStatement();
            rs = stmt.executeQuery("select * from sys_org where org_id = "
                    + orgId + "");
            if (rs.next()) {
                duns = rs.getString("DUNS");
            }
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return duns;
    }

    /**
     * 导入科室
     */
    public String importLoc(String orgId) throws Exception {
    	String result = "0";
        try {
            List<Map<String, Object>> list = getHisLocList(orgId);
            if(list!=null&&list.size()>0){
            	for (Map<String, Object> map : list) {
                    String locCode = map.get("locCode").toString();
                    String locDesc = map.get("locDesc").toString();
                    String locType = map.get("locType").toString();
                    AiscLocBean bean = getAiscLoc(orgId, locCode);
                    bean.setOrgId(orgId);
                    bean.setLocCode(locCode);
                    bean.setLocDesc(locDesc);
                    bean.setLocType(locType);
                    bean.setServerId(-1);
                    saveAiscLoc(bean);
                }
            	result = "0";
            }else{
            	result = "-2";
            }
        } catch (Exception ex) {
        	result = "-1";
            throw ex;
        }
        return result;
    }

    /**
     * 获取his科室
     */
    public List<Map<String, Object>> getHisLocList(String orgId) throws Exception {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;
        String sql = "";
        try {
            DictItemBean[] dictBean = dictSV.queryDictItem("HIS_PREFIX");
            String duns = getOrgDuns(orgId);
            sql = "select * from " + dictBean[0].getItemNo() + "pacs_ksjgxx_view where jgdm='" + duns + "'";
            logger.debug("sql:"+sql);
            conn = com.ai.aris.util.DAOUtils.getDBConnection(orgId, "");
            pstmt = conn.prepareStatement(sql);
            result = pstmt.executeQuery();
            while (result.next()) {
                String locCode = result.getString("ksdm");
                String locDesc = result.getString("ksmc");
                String orgCode = result.getString("jgdm");
                String locType = result.getString("kslx");
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("locCode", locCode);
                map.put("locDesc", locDesc);
                map.put("orgCode", orgCode);
                map.put("locType", locType);
                list.add(map);
            }
        } catch (SQLException se) {
            throw new com.ai.aris.util.DAOException(se.getMessage(), se);
        } catch (Exception ex) {
            throw ex;
        } finally {
            com.ai.aris.util.DAOUtils.closeResultSet(result);
            com.ai.aris.util.DAOUtils.closeStatement(pstmt);
            com.ai.aris.util.DAOUtils.closeConnection(conn);
        }
        return list;
    }

    @Override
    public QueryOrgLocBean getOrgLoc(String orgId, String locId)
            throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(orgId)) {
            condition.append(" and org_id =  '" + orgId + "'");
        }
        if (isNotBlank(locId)) {
            condition.append(" and loc_id = '" + locId + "' ");
        }
        QueryOrgLocBean[] beans = QueryOrgLocEngine.getBeans(condition.toString(), null);
        if (beans.length > 0) {
            return beans[0];
        } else {
            return new QueryOrgLocBean();
        }
    }

    @Override
    public ResultDTO queryAiscConsultList(AiscConsult model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (!model.getOrgId().equals("-1") && isNotBlank(model.getOrgId())) {
            condition.append(" and org_id = '" + model.getOrgId() + "' ");
        }
        if (model.getLocId() != -1 && isNotBlank(String.valueOf(model.getLocId()))) {
            condition.append(" and loc_id = '" + model.getLocId() + "' ");
        }

        int total = queryAiscConorganizationEngine.getBeansCount(condition.toString(), null);
        queryAiscConorganizationBean[] beans = null;

        if (total > 0) {
            beans = queryAiscConorganizationEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false);
        }
        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans, AiscConsult.class, dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public List<SysOrg> getHuizhenOrg(String orgId, String locId, String keyword) throws Exception {
    	List<SysOrg> list = new ArrayList();
    	Statement stmt = null;
    	ResultSet rsCount = null;
    	try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = " select count(*) COUNT from (select * from sys_org WHERE ORG_TYPE_FIRST IN ('A','B','C','D','E') " +
	                " and org_id not in (select conorg_id from AISC_Conorganization where org_id='" + orgId + "' and loc_id='" + locId + "')" +
	                " and state='1'  " +
	                " and org_class_level in (2,3) \n" +
	                " and duns is not null)\n";
	        if (isNotBlank(keyword)) {
	            sql += " where org_name like '%" + keyword + "%'  OR org_id LIKE '%" + keyword + "%' ";
	        }
	        sql += " order by org_name";
	        int total = 0;
	        rsCount = stmt.executeQuery(sql);
	        if (rsCount.next()) {
	            total = rsCount.getInt("COUNT");
	        }
	        String sql1 = " select * from(select * from sys_org WHERE ORG_TYPE_FIRST IN ('A','B','C','D','E') " +
	                " and org_id not in (select conorg_id from AISC_Conorganization where org_id='" + orgId + "' and loc_id='" + locId + "')" +
	                " and state='1' " +
	                " and org_class_level in (2,3) \n" +
	                " and duns is not null) where 1=1 \n";
	        if (isNotBlank(keyword)) {
	            sql1 += " and org_name like '%" + keyword + "%' OR org_id LIKE '%" + keyword + "%' ";
	        }
	        if (total > 20) {
	            sql1 += " and rownum<=20 ";
	        }
	        sql1 += " order by org_name";
	
	        ResultSet rs = stmt.executeQuery(sql1);
	        SysOrg sysOrg = null;
	        while (rs.next()) {
	            sysOrg = new SysOrg();
	            sysOrg.setOrgId(rs.getString("ORG_ID"));
	            sysOrg.setOrgName(rs.getString("ORG_NAME"));
	            list.add(sysOrg);
	        }
    	}catch (Exception e) {
			logger.error(e.getMessage());
		}
        finally{
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}   
        return list;
    }


    @Override
    public List<Map> getLocForOrg(String orgId) throws Exception {
        Map map = new HashMap();
        map.put("orgId", orgId);
        AiscLocBean[] beans = AiscLocEngine.getBeans(" org_id = :orgId and loc_type='C' ORDER BY loc_desc ASC", map);
        List<Map> list = new ArrayList();
        for (AiscLocBean aiscLocBean : beans) {
            list.add(ServiceUtil.transerBeanToMap(aiscLocBean));
        }
        return list;
    }

    @Override
    public void saveConorganization(AiscConorganizationBean bean) throws Exception {
        if (bean.getConorganizatId() == 0) {
            long id = ServiceUtil.getSequence("SEQ_Conorganization");
            bean.setConorganizatId(id);
            AiscConorganizationEngine.save(bean);
        }
    }

    @Override
    public boolean deleteAisConorg(long id) throws Exception {
        AiscConorganizationBean bean = AiscConorganizationEngine.getBean(id);
        bean.setStsToOld();
        bean.delete();
        try {
            AiscConorganizationEngine.save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void deleLocStudySta(String locId, String orgId) throws Exception {
        Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   delete from AISC_LOC_STUDYSTA where loc_id=" + locId + " and org_Id='" + orgId + "'";
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }

    @Override
    public void saveLocStudySta(String locId, String orgId, String status, String staName) throws Exception {
        long id = ServiceUtil.getSequence("SEQloc_studysta_id");
        boolean flag = getLocStudySta(locId, orgId, status);
        if (flag) {
            AiscLocStudyStaBean bean = new AiscLocStudyStaBean();
            bean.setLocId(Long.parseLong(locId));
            bean.setStatusName(staName);
            bean.setOrgId(orgId);
            bean.setStudyStatus(status);
            bean.setLocStudystaId(id);
            AiscLocStudyStaEngine.save(bean);
        }
    }

    public boolean getLocStudySta(String locId, String orgId, String status) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "   select count(*) COUNT from AISC_LOC_STUDYSTA where loc_id=" + locId + " and org_id='" + orgId + "' and study_status='" + status + "'";
            rs = stmt.executeQuery(sql);
            int total = 0;
            if (rs.next()) {
                total = rs.getInt("COUNT");
            }
            if (total > 0) {
                return false;
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return true;
    }

    @Override
    public AiscLocStudyStaBean getAiscLocStatus(long id) throws Exception {
        return AiscLocStudyStaEngine.getBean(id);
    }

    @Override
    public boolean deleteAisLocStudySta(long id) throws Exception {
        AiscLocStudyStaBean bean = getAiscLocStatus(id);
        bean.setStsToOld();
        bean.delete();
        try {
            AiscLocStudyStaEngine.save(bean);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<AiscLoc> getNewLocListStatus(String locId, String orgId) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        List<AiscLoc> list = new ArrayList();
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "  select item_no,item_name from sys_dictitem where dict_name='STUDY_STATUS' and item_no in (select study_status from AISC_LOC_STUDYSTA a where a.org_id='" + orgId + "' and loc_id=" + locId + ")";
            rs = stmt.executeQuery(sql);
            AiscLoc aiscLoc = null;
            while (rs.next()) {
                aiscLoc = new AiscLoc();
                aiscLoc.setStudyStatus(rs.getString("ITEM_NO") == null ? "" : rs.getString("ITEM_NO"));
                aiscLoc.setStatusName(rs.getString("ITEM_NAME") == null ? "" : rs.getString("ITEM_NAME"));
                list.add(aiscLoc);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return list;
    }

    @Override
    public List<AiscLoc> getNotJoinLocStatus(String locId, String orgId) throws Exception {
        Statement stmt = null;
        ResultSet rs = null;
        List<AiscLoc> list = new ArrayList();
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = "  select item_no,item_name from sys_dictitem where dict_name='STUDY_STATUS' and item_no not in (select study_status from AISC_LOC_STUDYSTA a where a.org_id='" + orgId + "' and loc_id=" + locId + ")";
            rs = stmt.executeQuery(sql);
            AiscLoc aiscLoc = null;
            while (rs.next()) {
                aiscLoc = new AiscLoc();
                aiscLoc.setStudyStatus(rs.getString("ITEM_NO") == null ? "" : rs.getString("ITEM_NO"));
                aiscLoc.setStatusName(rs.getString("ITEM_NAME") == null ? "" : rs.getString("ITEM_NAME"));
                list.add(aiscLoc);
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
        return list;
    }
}

