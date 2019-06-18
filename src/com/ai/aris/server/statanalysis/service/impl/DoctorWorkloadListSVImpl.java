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
import com.ai.aris.server.statanalysis.bean.QryDoctorWorkloadBean;
import com.ai.aris.server.statanalysis.bean.QryDoctorWorkloadEngine;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsEngine;
import com.ai.aris.server.statanalysis.bean.QryPatientInfoBean;
import com.ai.aris.server.statanalysis.bean.QryPatientInfoEngine;
import com.ai.aris.server.statanalysis.model.QryDoctorWorkloadModel;
import com.ai.aris.server.statanalysis.model.QryStudyInfoModel;
import com.ai.aris.server.statanalysis.service.interfaces.IDoctorWorkloadListSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;

public class DoctorWorkloadListSVImpl implements IDoctorWorkloadListSV{
	
	//列表查询 
    public ResultDTO queryPageList(QryDoctorWorkloadModel model, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append("1=1");
        if(isNotBlank(model.getStartTime())){ 
			condition.append(" and REPORT_DATE >= '"+model.getStartTime()+ "'");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and REPORT_DATE <= '"+model.getEndTime()+"'");
		}   
        if(isNotBlank(model.getDoctorId()) && !"-1".equals(model.getDoctorId())) {
            condition.append(" and REPORT_DOCTORID = " + model.getDoctorId() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        int total = QryDoctorWorkloadEngine.getBeansCount(condition.toString(), null);
        QryDoctorWorkloadBean[] beans = null;
        if (total > 0) {
            condition.append(" ORDER BY REPORT_DATE");
            beans = QryDoctorWorkloadEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
            
        }
        //EQUIPMENT_ID：2   STUDYITEM_NUMBER：6   EQUIPMENT_DESC：DR   STUDYITEMPRICE：263.5
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCodeDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QryDoctorWorkloadModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
  
    public QryEquiWorkChartsBean[] getDocWorkCharts(QryDoctorWorkloadModel model)throws Exception{
    	QryEquiWorkChartsBean[] charsBeans = null;
    	StringBuffer sql = new StringBuffer();
    	Map<String, Object> param = new HashMap<String, Object>();
    	
		sql.append(" select to_char(report_datetime, 'yyyy-mm-dd') report_date,b.loc_id,");
		sql.append("        (select cp.careprov_name ");
		sql.append("        from AISC_CareProv cp ");
		sql.append("        where cp.careprov_id = a.REPORT_VERIFYDOCTORID) careprov_name, ");
		sql.append("        count(distinct b.studyinfo_id) Report_ID, ");
		sql.append("         sum(c.studyitem_price) studyitem_price  ");
		sql.append("   from AIS_StudyReport a, AIS_StudyInfo b, AIS_StudyItemInfo c ");	
		sql.append("          where a.studyinfo_id = b.studyinfo_id  ");
		sql.append("          and b.studyinfo_id = c.studyinfo_id ");		 
		sql.append("        and b.studystatus_code not in ('Cancel')  ");
		if(isNotBlank(String.valueOf(model.getOrgId()))){
			sql.append("   and b.org_id = '"+model.getOrgId()+"'");
		}
		if (model != null) {
			if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
				sql.append(" and REPORT_DOCTORID = " + model.getReportVerifydoctorid() );
	        }
			if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
				sql.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
	        } 
			if(isNotBlank(String.valueOf(model.getStartTime()))){
				sql.append("   and to_char(report_datetime,'yyyy-mm-dd') >='"+model.getStartTime()+"' ");
			}
			if(isNotBlank(String.valueOf(model.getEndTime()))){
				sql.append("   and to_char(report_datetime,'yyyy-mm-dd') <= '"+model.getEndTime()+"' ");
			}
			if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))){
				sql.append("  and LOC_ID = "+model.getLocId());
			}
		}
		sql.append("    group by   REPORT_VERIFYDOCTORID, org_id, b.loc_id,to_char(report_datetime, 'yyyy-mm-dd')");
		charsBeans = QryEquiWorkChartsEngine.getBeansFromSql(sql.toString(), param);
		return charsBeans;
    }
    
    /**
	 * 获取合计信息
	 */
	public  Map<String, String> getTotalInfo(QryDoctorWorkloadModel model) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		StringBuffer condition = new StringBuffer();
		condition.append(" select ");
		condition.append("   sum(Report_ID) as Report_ID,sum(StudyItem_Price) studyitem_price");
		condition.append("   from (select to_char(report_datetime, 'yyyy-mm-dd') report_date,a.report_doctorid,");
		condition.append("   b.loc_id,count(distinct b.studyinfo_id) Report_ID,b.org_id,a.report_verifydoctorid,");
		condition.append("   sum((select sum(StudyItem_Price) from AIS_StudyITEMInfo e where e.studyinfo_id = a.studyinfo_id)) studyitem_price ");
		condition.append(" from AIS_StudyReport a, AIS_StudyInfo b where a.studyinfo_id = b.studyinfo_id and b.studystatus_code not in ('Cancel') group by report_doctorid,  ");
		condition.append(" REPORT_VERIFYDOCTORID,studystatus_code,org_id,b.loc_id,to_char(report_datetime, 'yyyy-mm-dd')) where 1=1 ");
        if(isNotBlank(model.getStartTime())){  
			condition.append(" and to_date(REPORT_DATE,'yyyy-mm-dd') >= to_date('"+model.getStartTime()+ "','yyyy-mm-dd')");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and to_date(REPORT_DATE,'yyyy-mm-dd') < to_date('"+model.getEndTime()+"','yyyy-mm-dd')+1");
		}   
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        
        if(isNotBlank(model.getDoctorId()) && !"-1".equals(model.getDoctorId())) {
            condition.append(" and REPORT_DOCTORID = " + model.getDoctorId() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        
        QryDoctorWorkloadBean[] beans = QryDoctorWorkloadEngine.getBeansFromSql(condition.toString(), null);
        if (beans!=null && beans.length>0) {
			map.put("studyitemNumber", beans[0].getReportId()+"");
			map.put("studyitemPrice", beans[0].getStudyitemPrice()+"");
		}
        return map;
	}
	/**
	 * 获取检查明细
	 */
	public ResultDTO queryStudyInfoList(QryDoctorWorkloadModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append("1=1");
		if(isNotBlank(model.getReportDate())){
			condition.append(" and trunc(report_datetime) = to_date('"+model.getReportDate()+ "','yyyy-mm-dd')");
		}
		if (isNotBlank(String.valueOf(model.getReportVerifydoctorid())) ) {
			condition.append(" and REPORT_VERIFYDOCTORID = " + model.getReportVerifydoctorid() );
		}
		if (isNotBlank(String.valueOf(model.getReportDoctorid())) ) {
			condition.append(" and report_doctorid = " + model.getReportDoctorid() );
		}
		if(isNotBlank(String.valueOf(model.getStudystatusCode()))){
			condition.append("   and studystatus_code = '"+model.getStudystatusCode()+"'");
		}
		int total = QryPatientInfoEngine.getBeansCount(condition.toString(), null);
		QryPatientInfoBean[] beans = null;
		if (total > 0) {
			condition.append(" ORDER BY report_datetime");
			beans = QryPatientInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;

		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
		dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
		dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
		resultDTO.setRows(BeanUtils.beanToList(beans,QryStudyInfoModel.class,dicts));
		resultDTO.setRecords(total);
		return resultDTO;
	}
	
	public List<Map<String, Object>> getExportDcotorWork(QryDoctorWorkloadModel model) throws Exception {
    	StringBuffer condition = new StringBuffer("select to_char(report_datetime, 'yyyy-mm-dd') report_date,\n"+
    			"       b.loc_id,\n"+
    			"       a.report_doctorid,\n"+
    			"       a.REPORT_VERIFYDOCTORID,\n"+
    			"       (select cp.careprov_name\n"+
    			"          from AISC_CareProv cp\n"+
    			"         where cp.careprov_id = a.report_doctorid) REPORT_DOCTORNAME,\n"+
    			"       (select cp.careprov_name\n"+
    			"          from AISC_CareProv cp\n"+
    			"         where cp.careprov_id = a.REPORT_VERIFYDOCTORID) REPORT_VERIFYDOCTORNAME,\n"+
    			"       count(distinct b.studyinfo_id) Report_ID,\n"+
    			"       sum(decode(a.report_ispositive, 1, 0, 1)) report_ispositive， sum((select sum(StudyItem_Price)\n"+
    			"                                                                           from AIS_StudyITEMInfo e\n"+
    			"                                                                          where e.studyinfo_id =\n"+
    			"                                                                                a.studyinfo_id)) studyitem_price,\n"+
    			"       to_char(round(sum(decode(a.report_ispositive, 1, 0, 1)) /\n"+
    			"                     count(distinct a.studyinfo_id) * 100,\n"+
    			"                     1)) || '%' zb,\n"+
    			"       (select item_name from sys_dictitem where dict_name='STUDY_STATUS' and item_no=b.studystatus_code) studystatus_code,\n"+
    			"       b.org_id\n"+
    			"  from AIS_StudyReport a, AIS_StudyInfo b\n"+
    			" where a.studyinfo_id = b.studyinfo_id\n"+
    			"   and b.studystatus_code not in ('Cancel')\n");
    			
    	if(isNotBlank(model.getStartTime())){ 
			condition.append(" and REPORT_DATE >= '"+model.getStartTime()+ "'");
		}
        if(isNotBlank(model.getEndTime())){ 
			condition.append(" and REPORT_DATE <= '"+model.getEndTime()+"'");
		}   
        if(isNotBlank(model.getDoctorId()) && !"-1".equals(model.getDoctorId())) {
            condition.append(" and REPORT_DOCTORID = " + model.getDoctorId() );
        } 
        if(isNotBlank(model.getReportVerifydoctorid()) && !"-1".equals(model.getReportVerifydoctorid())) {
            condition.append(" and REPORT_VERIFYDOCTORID = '" + model.getReportVerifydoctorid()+"'" );
        } 
        if (model.getLocId()!= -1 && isNotBlank(String.valueOf(model.getLocId())) ) {
            condition.append(" and LOC_ID = " + model.getLocId() );
        }  
        if(isNotBlank(String.valueOf(model.getOrgId()))){
        	condition.append("   and org_id = '"+model.getOrgId()+"'");
		}
        condition.append(" \n" +
				"      group by report_doctorid,REPORT_VERIFYDOCTORID,studystatus_code,org_id,b.loc_id,to_char(report_datetime, 'yyyy-mm-dd')");
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		 Statement stmt = null;
	     ResultSet rs = null;
	 	 try {
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        ResultSet result = stmt.executeQuery(condition.toString());
	        while (result.next()) {
	            String REPORT_DATE = result.getString("REPORT_DATE");
	            String REPORT_DOCTORNAME = result.getString("REPORT_DOCTORNAME");
	            String REPORT_VERIFYDOCTORNAME = result.getString("REPORT_VERIFYDOCTORNAME");
	            String STUDYSTATUS_CODE = result.getString("STUDYSTATUS_CODE");
	            String REPORT_ID = result.getString("REPORT_ID");
	            String STUDYITEM_PRICE = result.getString("STUDYITEM_PRICE");
	            String ZB = result.getString("ZB");
	            Map<String, Object> map = new HashMap<String, Object>();
	            map.put("REPORT_DATE", REPORT_DATE);
	            map.put("REPORT_DOCTORNAME", REPORT_DOCTORNAME);
	            map.put("REPORT_VERIFYDOCTORNAME", REPORT_VERIFYDOCTORNAME);
	            map.put("STUDYSTATUS_CODE", STUDYSTATUS_CODE);
	            map.put("REPORT_ID", REPORT_ID);
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
} 
