package com.ai.aris.server.statanalysis.service.impl;

 
import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.statanalysis.bean.QryMedicalCaseWorkloadBean;
import com.ai.aris.server.statanalysis.bean.QryMedicalCaseWorkloadEngine;
import com.ai.aris.server.statanalysis.model.QryMedicalCaseWorkloadModel;
import com.ai.aris.server.statanalysis.service.interfaces.IMedicalCaseWorkloadListSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;

public class MedicalCaseWorkloadListSVImpl implements IMedicalCaseWorkloadListSV{
	
	//列表查询 
    public ResultDTO queryPageList(QryMedicalCaseWorkloadModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append("1=1");
        if(isNotBlank(model.getStartTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')>=to_date('"+model.getStartTime()+ "','yyyy-mm-dd') ");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')<=to_date('"+model.getEndTime()+"','yyyy-mm-dd') ");
		}   
        if(isNotBlank(model.getReportDoctorid()) && !"-1".equals(model.getReportDoctorid())) {
            condition.append(" and REPORT_DOCTORID = " + model.getReportDoctorid() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        if(isNotBlank(model.getCategories())) {
            condition.append(" and (REPORT_EXAM like '%" + model.getCategories() + "%' or REPORT_RESULT like '%" + model.getCategories() + "%' )" );
        }
        if(model.getReportIspositive() != -1) {
        	condition.append(" and REPORT_ISPOSITIVE = " + model.getReportIspositive() );
        }
        if(isNotBlank(model.getOrgId())) {
        	condition.append(" and org_id = '" + model.getOrgId()+"'" );
        }
        if(isNotBlank(model.getStartAge())){ 
			condition.append(" and substr(PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) >= " + model.getStartAge());
		}
        if(isNotBlank(model.getEndAge())){ 
			condition.append(" and substr(PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) <= " + model.getEndAge());
		}
        condition.append(" and studystatus_code not in ('Cancel') " );
        int total = QryMedicalCaseWorkloadEngine.getBeansCount(condition.toString(), null);
        QryMedicalCaseWorkloadBean[] beans = null;
        if (total > 0) {
            condition.append(" ORDER BY REPORT_DATETIME DESC");
            beans = QryMedicalCaseWorkloadEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            
        } 
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryMedicalCaseWorkloadModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
  
    public  Map<String, String> getTotalInfo(QryMedicalCaseWorkloadModel model) throws Exception{
    	Map<String, String> map = new HashMap<String, String>();
		StringBuffer condition = new StringBuffer();
		condition.append(" select count(distinct c.report_id) report_id,sum(decode(c.report_ispositive, 1, 0, 1)) report_ispositive,to_char(round(sum(decode(c.report_ispositive,1,0,1)) / ");
		condition.append("   count(distinct c.report_id) * 100, 1)) || '%' study_itemdesc from ais_patientinfo a, AIS_StudyInfo b, AIS_StudyReport c");
		condition.append("   where a.patient_globalid = b.patient_globalid ");
		condition.append("   and b.studyinfo_id = c.studyinfo_id ");
		
		if(isNotBlank(model.getStartTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')>=to_date('"+model.getStartTime()+ "','yyyy-mm-dd') ");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')<to_date('"+model.getEndTime()+"','yyyy-mm-dd')+1 ");
		}   
        if(isNotBlank(model.getReportDoctorid()) && !"-1".equals(model.getReportDoctorid())) {
            condition.append(" and REPORT_DOCTORID = " + model.getReportDoctorid() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        if(isNotBlank(model.getCategories())) {
            condition.append(" and (REPORT_EXAM like '%" + model.getCategories() + "%' or REPORT_RESULT like '%" + model.getCategories() + "%' )" );
        }
        if(model.getReportIspositive() != -1) {
        	condition.append(" and REPORT_ISPOSITIVE = " + model.getReportIspositive() );
        }
        if(isNotBlank(model.getOrgId())) {
        	condition.append(" and b.org_id = '" + model.getOrgId()+"'" );
        }
        if(isNotBlank(model.getStartAge())){ 
			condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) >= " + model.getStartAge());
		}
        if(isNotBlank(model.getEndAge())){ 
			condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) <= " + model.getEndAge());
		}
        condition.append(" and studystatus_code not in ('Cancel') " );
        QryMedicalCaseWorkloadBean[] beans = QryMedicalCaseWorkloadEngine.getBeansFromSql(condition.toString(), null);
        if (beans!=null && beans.length>0) {
			map.put("total", beans[0].getReportId()+"");
			map.put("reportIspositive", beans[0].getReportIspositive()+"");
			map.put("studyItemdesc", beans[0].getStudyItemdesc()+"");
		}
        return map;
    }
    
    public List<Map<String, Object>> getEexportMedicalCase(QryMedicalCaseWorkloadModel model) throws Exception {
    	StringBuffer condition = new StringBuffer("select distinct c.Report_DateTime,\n"+
    			"                c.Report_DoctorID,\n"+
    			"                (select cp.careprov_name\n"+
    			"                 from AISC_CareProv cp\n"+
    			"                  where cp.careprov_id = c.Report_DoctorID) Report_Doctorname,\n"+
    			"                decode(c.Report_IsPositive,0,'阳性',1,'阴性','未知') Report_IsPositive,\n"+
    			"                b.Patient_Age,\n"+
    			"                a.Patient_Name,\n"+
    			"                decode(a.patient_sex,1,'男',2,'女','未知') patient_sex,\n"+
    			"                b.Study_ItemDesc,\n"+
    			"                a.patient_id,\n"+
    			"                b.org_id,\n"+
    			"                b.Study_HaveImage,\n"+
    			"                b.Study_HaveReport,\n"+
    			"                b.study_Accnumber,\n"+
    			"                b.StudyInfo_ID,\n"+
    			"                b.patient_globalid,\n"+
    			"                b.loc_id,\n"+
    			"                c.report_id,\n"+
    			"                c.report_finaldoctorid,\n"+
    			"                c.Report_VerifyDoctorID,\n"+
    			"                to_char(c.report_exam) report_exam,\n"+
    			"                to_char(c.report_result) report_result,\n"+
    			"                (select cp.careprov_name\n"+
    			"                   from AISC_CareProv cp\n"+
    			"                  where cp.careprov_id = c.Report_VerifyDoctorID) verifyDoctor_name,\n"+
    			"                b.studystatus_code\n"+
    			"  from ais_patientinfo a, AIS_StudyInfo b, AIS_StudyReport c\n"+
    			"  where a.patient_globalid = b.patient_globalid\n"+
    			"   and b.studyinfo_id = c.studyinfo_id\n");
    	if(isNotBlank(model.getStartTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')>=to_date('"+model.getStartTime()+ "','yyyy-mm-dd') ");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and to_date(to_char(REPORT_DATETIME,'yyyy-mm-dd'),'yyyy-mm-dd')<=to_date('"+model.getEndTime()+"','yyyy-mm-dd') ");
		}   
        if(isNotBlank(model.getReportDoctorid()) && !"-1".equals(model.getReportDoctorid())) {
            condition.append(" and REPORT_DOCTORID = " + model.getReportDoctorid() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        if(isNotBlank(model.getCategories())) {
            condition.append(" and (REPORT_EXAM like '%" + model.getCategories() + "%' or REPORT_RESULT like '%" + model.getCategories() + "%' )" );
        }
        if(model.getReportIspositive() != -1) {
        	condition.append(" and REPORT_ISPOSITIVE = " + model.getReportIspositive() );
        }
        if(isNotBlank(model.getOrgId())) {
        	condition.append(" and b.org_id = '" + model.getOrgId()+"'" );
        }
        if(isNotBlank(model.getStartAge())){ 
			condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) >= " + model.getStartAge());
		}
        if(isNotBlank(model.getEndAge())){ 
			condition.append(" and substr(b.PATIENT_AGE,1,LENGTH(PATIENT_AGE) - 1) <= " + model.getEndAge());
		}
        condition.append(" and studystatus_code not in ('Cancel') " );
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet rs = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        ResultSet result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String PATIENT_NAME = result.getString("PATIENT_NAME");
	            String PATIENT_SEX = result.getString("PATIENT_SEX");
	            String PATIENT_AGE = result.getString("PATIENT_AGE");
	            String STUDY_ITEMDESC = result.getString("STUDY_ITEMDESC");
	            String REPORT_ISPOSITIVE = result.getString("REPORT_ISPOSITIVE");
	            String REPORT_EXAM = result.getString("REPORT_EXAM");
	            String REPORT_RESULT = result.getString("REPORT_RESULT");
	            String REPORT_DATETIME = result.getString("REPORT_DATETIME");
	            String REPORT_DOCTORNAME = result.getString("REPORT_DOCTORNAME");
	            String VERIFYDOCTOR_NAME = result.getString("VERIFYDOCTOR_NAME");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("PATIENT_NAME", PATIENT_NAME);
	            map.put("PATIENT_SEX", PATIENT_SEX);
	            map.put("PATIENT_AGE", PATIENT_AGE);
	            map.put("STUDY_ITEMDESC", STUDY_ITEMDESC);
	            map.put("REPORT_ISPOSITIVE", REPORT_ISPOSITIVE);
	            map.put("REPORT_EXAM", REPORT_EXAM);
	            map.put("REPORT_RESULT", REPORT_RESULT);
	            map.put("REPORT_DATETIME", REPORT_DATETIME);
	            map.put("REPORT_DOCTORNAME", REPORT_DOCTORNAME);
	            map.put("VERIFYDOCTOR_NAME", VERIFYDOCTOR_NAME);
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
