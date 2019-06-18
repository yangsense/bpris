package com.ai.aris.server.statanalysis.service.impl;

 
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

import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsEngine;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkloadBean;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkloadEngine;
import com.ai.aris.server.statanalysis.bean.QryStudyInfoBean;
import com.ai.aris.server.statanalysis.bean.QryStudyInfoEngine;
import com.ai.aris.server.statanalysis.bean.WorkListCountBean;
import com.ai.aris.server.statanalysis.bean.WorkListCountEngine;
import com.ai.aris.server.statanalysis.model.QryEquiWorkloadModel;
import com.ai.aris.server.statanalysis.model.QryMedicalCaseWorkloadModel;
import com.ai.aris.server.statanalysis.model.QryStudyInfoModel;
import com.ai.aris.server.statanalysis.service.interfaces.IEquiWorkloadListSV;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;

public class EquiWorkloadListSVImpl implements IEquiWorkloadListSV{
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	
	//列表查询 
    public ResultDTO queryPageList(QryEquiWorkloadModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append("1=1");
        if(isNotBlank(model.getStudyStarttime())){  
			condition.append(" and c_Study_DateTime >= '"+model.getStudyStarttime()+ "'");
		}
        if(isNotBlank(model.getStudyEndtime())){ 
			condition.append(" and c_Study_DateTime <= '"+model.getStudyEndtime()+"'");
		}   
        if (model.getModalityId()!= -1 && isNotBlank(String.valueOf(model.getModalityId())) ) {
            condition.append(" and modality_id = " + model.getModalityId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        int total = QryEquiWorkloadEngine.getBeansCount(condition.toString(), null);
        QryEquiWorkloadBean[] beans = null;
        if (total > 0) {
            condition.append(" ORDER BY c_Study_DateTime");
            beans = QryEquiWorkloadEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            for(QryEquiWorkloadBean bean :beans){
            	DictItemModel dict =  dictSV.getDictItem("AISC_MODALITY",String.valueOf(bean.getModalityId()));
            	bean.setEquipmentDesc(dict.getItemName());
            }
        }
        //EQUIPMENT_ID：2   STUDYITEM_NUMBER：6   EQUIPMENT_DESC：DR   STUDYITEMPRICE：263.5
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>(); 
        resultDTO.setRows(BeanUtils.beanToList(beans,QryEquiWorkloadModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    
    public QryEquiWorkChartsBean[] getEquiWorkCharts(QryEquiWorkloadModel model)throws Exception{
    	QryEquiWorkChartsBean[] charsBeans = null;
    	StringBuffer sql = new StringBuffer();
    	Map<String, Object> param = new HashMap<String, Object>();
    	
		sql.append(" select equipment_desc,count(distinct studyinfo_id) StudyItem_Number,sum(StudyItem_Price) StudyItem_Price from (select ");
		sql.append("        to_char(a.Study_startTime, 'yyyy-mm-dd') c_Study_DateTime,");
		sql.append("        a.Study_DateTime,");
		sql.append("        c.modality_id, ");
		sql.append("        a.loc_id, ");
		sql.append("        (select item_name from sys_dictitem where dict_name='AISC_MODALITY' and item_no=c.modality_id )  equipment_desc, ");
		sql.append("        a.studyinfo_id, ");
		sql.append("        StudyItem_Number as StudyItem_Number, ");
		sql.append("         StudyItem_Price as StudyItem_Price ");
		sql.append("   from AIS_StudyInfo a, AIS_StudyITEMInfo b, aisc_equipment c ");	
		sql.append("          where a.studyinfo_id = b.studyinfo_id  ");
		sql.append("          and a.equipment_id = c.equipment_id ");		 
		sql.append("        and a.studystatus_code not in ('Cancel')  ");
		if(isNotBlank(String.valueOf(model.getOrgId()))){
			sql.append("   and a.org_id = '"+model.getOrgId()+"'");
		}
		sql.append(")  where 1=1 ");
		if (model != null) {
			if(model.getModalityId()!=-1&&isNotBlank(String.valueOf(model.getModalityId()))){
				sql.append("  and modality_id = "+model.getModalityId());
			}
			if(isNotBlank(String.valueOf(model.getStudyStarttime()))){
				sql.append("   and c_Study_DateTime >= '"+model.getStudyStarttime()+"'");
			}
			if(isNotBlank(String.valueOf(model.getStudyEndtime()))){
				sql.append("   and c_Study_DateTime <= '"+model.getStudyEndtime()+"'");
			}
			if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))){
				sql.append("  and LOC_ID = "+model.getLocId());
			}
		}
		sql.append("   group by equipment_desc");
		charsBeans = QryEquiWorkChartsEngine.getBeansFromSql(sql.toString(), param);
		return charsBeans;
    }
    
    public List<Map<String, Object>> getExportEquiWork(QryEquiWorkloadModel model) throws Exception {
    	StringBuffer condition = new StringBuffer("select to_char(a.Study_startTime, 'yyyy-mm-dd') c_Study_DateTime,\n"+
    			"     a.loc_id,\n"+
    			  "     a.org_id,\n"+
    			  "     c.modality_id,\n"+
    			  "     (select item_name from sys_dictitem where dict_name='AISC_MODALITY' and item_no=c.modality_id) modality_type,\n"+
    			  "     '' equipment_desc,\n"+
    			  "     count(distinct a.studyinfo_id) as StudyItem_Number,\n"+
    			  "     sum(decode(d.report_ispositive, 1, 0, 1)) report_ispositive， sum((select sum(StudyItem_Price)\n"+
    			  "                                                                         from AIS_StudyITEMInfo e\n"+
    			  "                                                                        where e.studyinfo_id =\n"+
    			  "                                                                              a.studyinfo_id)) StudyItem_Price,\n"+
    			  "     to_char(round(sum(decode(d.report_ispositive, 1, 0, 1)) /\n"+
    			  "                   count(distinct a.studyinfo_id) * 100,\n"+
    			  "                   1)) || '%' zb\n"+
    			  " from AIS_StudyInfo a, aisc_equipment c, ais_studyreport d\n"+
    			  " where a.equipment_id = c.equipment_id\n"+
    			  " and a.studyinfo_id = d.studyinfo_id\n"+
    			  " and a.studystatus_code not in ('Cancel')\n");
    			
        if(isNotBlank(model.getStudyStarttime())){  
			condition.append(" and c_Study_DateTime >= '"+model.getStudyStarttime()+ "'");
		}
        if(isNotBlank(model.getStudyEndtime())){ 
			condition.append(" and c_Study_DateTime <= '"+model.getStudyEndtime()+"'");
		}   
        if (model.getModalityId()!= -1 && isNotBlank(String.valueOf(model.getModalityId())) ) {
            condition.append(" and modality_id = " + model.getModalityId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append(" and a.org_id = '"+model.getOrgId()+"'");
		}
        condition.append(" \n" +
				"           group by a.loc_id,\n" +
				"           a.org_id,to_char(a.Study_startTime, 'yyyy-mm-dd'),c.modality_id");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet rs = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        ResultSet result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String C_STUDY_DATETIME = result.getString("C_STUDY_DATETIME");
	            String MODALITY_TYPE = result.getString("MODALITY_TYPE");
	            String STUDYITEM_NUMBER = result.getString("STUDYITEM_NUMBER");
	            String STUDYITEM_PRICE = result.getString("STUDYITEM_PRICE");
	            String ZB = result.getString("ZB");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("C_STUDY_DATETIME", C_STUDY_DATETIME);
	            map.put("MODALITY_TYPE", MODALITY_TYPE);
	            map.put("STUDYITEM_NUMBER", STUDYITEM_NUMBER);
	            map.put("STUDYITEM_PRICE", STUDYITEM_PRICE);
	            map.put("ZB", ZB);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(rs, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
    }
    /**
	 * 获取合计信息
	 */
	public  Map<String, String> getTotalInfo(QryEquiWorkloadModel model) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer condition = new StringBuffer();
		condition.append(" select ");
		condition.append("        count(distinct b.studyinfo_id) as StudyItem_Number,");
		condition.append("        sum(StudyItem_Price) as StudyItem_Price");
		condition.append("   from AIS_StudyInfo a, AIS_StudyITEMInfo b, aisc_equipment c,ais_studyreport d ");
		condition.append("  where a.studyinfo_id = b.studyinfo_id");
		condition.append("    and a.equipment_id = c.equipment_id  and a.studyinfo_id = d.studyinfo_id");
		condition.append("    and a.studystatus_code not in ('Cancel')");
        if(isNotBlank(model.getStudyStarttime())){  
			condition.append(" and Study_startTime >= to_date('"+model.getStudyStarttime()+ "','yyyy-mm-dd')");
		}
        if(isNotBlank(model.getStudyEndtime())){ 
			condition.append(" and Study_startTime < to_date('"+model.getStudyEndtime()+"','yyyy-mm-dd')+1");
		}   
        if (model.getModalityId()!= -1 && isNotBlank(String.valueOf(model.getModalityId())) ) {
            condition.append(" and c.modality_id = " + model.getModalityId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and a.LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and a.org_id = '"+model.getOrgId()+"'");
		}
        QryEquiWorkloadBean[] beans = beans = QryEquiWorkloadEngine.getBeansFromSql(condition.toString(), null);
        if (beans!=null && beans.length>0) {
			map.put("studyitemNumber", beans[0].getStudyitemNumber()+"");
			map.put("studyitemPrice", beans[0].getStudyitemPrice()+"");
		}
        return map;
	}
	/**
	 * 获取检查明细
	 */
	public ResultDTO queryStudyInfoList(QryEquiWorkloadModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append("1=1");
        if(isNotBlank(model.getStudyDatetime())){  
			condition.append(" and trunc(Study_startTime) = to_date('"+model.getStudyDatetime()+ "','yyyy-mm-dd')");
		} 
        if (model.getModalityId()!= -1 && isNotBlank(String.valueOf(model.getModalityId())) ) {
            condition.append(" and modality_id = " + model.getModalityId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        int total = QryStudyInfoEngine.getBeansCount(condition.toString(), null);
        QryStudyInfoBean[] beans = null;
        if (total > 0) {
            condition.append(" ORDER BY Study_startTime");
            beans = QryStudyInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>(); 
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
	}
	
	public ResultDTO queryWorkListCountList(QryStudyInfoListModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append("1=1");
        if (!"-1".equals(model.getEquipmentId()) && isNotBlank(model.getEquipmentId())) {
            condition.append(" and equipment_id = " + model.getEquipmentId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(model.getOrgId())){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        if(!"-1".equals(model.getPatienttypeCode())&&isNotBlank(String.valueOf(model.getPatienttypeCode()))){
        	condition.append("   and patienttype_code = '"+model.getPatienttypeCode()+"'");
		}
        if( -1!= model.getStudyApplocid()&& isNotBlank(String.valueOf(model.getStudyApplocid()))){
        	condition.append("   and study_applocid = '"+model.getStudyApplocid()+"'");
		}
        if(!"-1".equals(model.getStudyAppdoc())&&isNotBlank(model.getStudyAppdoc())){
        	condition.append("   and study_appdoc = '"+model.getStudyAppdoc()+"'");
		}
        if(isNotBlank(model.getStartTime())){ 
			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getEndTime())){
			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        
        int total = WorkListCountEngine.getBeansCount(condition.toString(), null);
        WorkListCountBean[] beans = null;
        if (total > 0) {
            //condition.append(" ORDER BY cStudyOperationtime desc");
            beans = WorkListCountEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>(); 
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
        System.out.println(new DictTranslator("modalityType","AISC_MODALITY","modalityType"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoListModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
	}
	
	public List<Map<String, Object>> getEexportWorklistCount(QryStudyInfoListModel model) throws Exception {
    	StringBuffer condition = new StringBuffer("select a.studyinfo_id,\n"+
    			"       b.patient_name,\n"+
    			"       a.Study_appdate,\n"+
    			"       a.loc_id,\n"+
    			"       a.patient_age,\n"+
    			"       decode(b.patient_sex,1,'男',2,'女','未知') patient_sex,\n"+
    			"       a.equipment_id,\n"+
    			"       (select equipment_desc\n"+
    			"          from aisc_equipment\n"+
    			"         where equipment_id = a.equipment_id) equipment_desc,\n"+
    			"       (select item_name from sys_dictitem where dict_name='PATIENT_TYPE' and item_no=a.patienttype_code) patienttype_code,\n"+
    			"       decode(a.patienttype_code,\n"+
    			"              'INP',\n"+
    			"              patient_inpatientid,\n"+
    			"              'OP',\n"+
    			"              a.patient_outpatientid) patient_inpatientid,\n"+
    			"       a.study_applocid,\n"+
    			"       a.org_id,\n"+
    			"       a.Study_ItemDesc,\n"+
    			"       (select loc_desc from AISC_Loc al where al.loc_id = a.Study_AppLocID) loc_desc,\n"+
    			"       a.study_appdoc,\n"+
    			"       (select careprov_name\n"+
    			"          from aisc_careprov ac\n"+
    			"         where ac.careprov_id = a.Study_AppDoc) study_AppDocname,\n"+
    			"       to_char(a.Study_operationTime, 'yyyy-mm-dd hh24:mi:ss') c_Study_OperationTime,\n"+
    			"       a.study_bedno,\n"+
    			"       (select to_char(wm_concat(studyitem_bodyinfo)) studyitem_bodyinfo\n"+
    			"          from ais_studyiteminfo\n"+
    			"         where studyinfo_id = a.studyinfo_id) bodyItem,\n"+
    			"       a.study_accnumber\n"+
    			"  from ais_studyinfo a, ais_patientinfo b\n"+
    			" where a.patient_globalid = b.patient_globalid\n");
    	if (!"-1".equals(model.getEquipmentId()) && isNotBlank(model.getEquipmentId())) {
            condition.append(" and equipment_id = " + model.getEquipmentId() );
        }    
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(model.getOrgId())){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        if(!"-1".equals(model.getPatienttypeCode())&&isNotBlank(String.valueOf(model.getPatienttypeCode()))){
        	condition.append("   and patienttype_code = '"+model.getPatienttypeCode()+"'");
		}
        if( -1!= model.getStudyApplocid()&& isNotBlank(String.valueOf(model.getStudyApplocid()))){
        	condition.append("   and study_applocid = '"+model.getStudyApplocid()+"'");
		}
        if(!"-1".equals(model.getStudyAppdoc())&&isNotBlank(model.getStudyAppdoc())){
        	condition.append("   and study_appdoc = '"+model.getStudyAppdoc()+"'");
		}
        if(isNotBlank(model.getStartTime())){ 
			condition.append(" and Study_AppDate >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getEndTime())){
			condition.append(" and Study_AppDate <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet rs = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        ResultSet result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	        	String STUDYINFO_ID = result.getString("STUDYINFO_ID");
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String PATIENTTYPE_CODE = result.getString("PATIENTTYPE_CODE");
	            String PATIENT_INPATIENTID = result.getString("PATIENT_INPATIENTID");
	            String EQUIPMENT_DESC = result.getString("EQUIPMENT_DESC");
	            String LOC_DESC = result.getString("LOC_DESC");
	            String STUDY_APPDOCNAME = result.getString("STUDY_APPDOCNAME");
	            String STUDY_ACCNUMBER = result.getString("STUDY_ACCNUMBER");
	            String STUDY_BEDNO = result.getString("STUDY_BEDNO");
	            String BODYITEM = result.getString("BODYITEM");
	            String C_STUDY_OPERATIONTIME = result.getString("C_STUDY_OPERATIONTIME");
	            String STUDY_ITEMDESC = result.getString("STUDY_ITEMDESC");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("STUDYINFO_ID", STUDYINFO_ID);
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("PATIENTTYPE_CODE", PATIENTTYPE_CODE);
	            map.put("PATIENT_INPATIENTID", PATIENT_INPATIENTID);
	            map.put("EQUIPMENT_DESC", EQUIPMENT_DESC);
	            map.put("LOC_DESC", LOC_DESC);
	            map.put("STUDY_APPDOCNAME", STUDY_APPDOCNAME);
	            map.put("STUDY_ACCNUMBER", STUDY_ACCNUMBER);
	            map.put("STUDY_BEDNO", STUDY_BEDNO);
	            map.put("BODYITEM", BODYITEM);
	            map.put("C_STUDY_OPERATIONTIME", C_STUDY_OPERATIONTIME);
	            map.put("STUDY_ITEMDESC", STUDY_ITEMDESC);
	            list.add(map);
	        }
	     }catch (SQLException se) {
	         throw new com.ai.aris.util.DAOException(se.getMessage(), se);
	     }catch (Exception ex) {
	         throw ex;
	     }finally {
	    	 DBUtil.release(rs, stmt, null);
	         ServiceManager.getSession().getConnection().close();
	     }
	 	 return list;
    }
}
