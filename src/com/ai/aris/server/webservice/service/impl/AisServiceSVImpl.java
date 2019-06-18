package com.ai.aris.server.webservice.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AisPatientInfoHisBean;
import com.ai.aris.server.basecode.bean.AisPatientInfoHisEngine;
import com.ai.aris.server.basecode.bean.AisPixInfoBean;
import com.ai.aris.server.basecode.bean.AisPixInfoEngine;
import com.ai.aris.server.basecode.bean.AisServiceLogBean;
import com.ai.aris.server.basecode.bean.AisServiceLogEngine;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemEngine;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.bean.AiscBodyPartEngine;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscConorganizationBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscItemMastEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscLoginLocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocEngine;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryEngine;
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.bean.AiscRoomEngine;
import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.basecode.bean.AiscServerInfoEngine;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.interfacereal.bean.AisPatientRealBean;
import com.ai.aris.server.interfacereal.bean.AisPatientRealEngine;
import com.ai.aris.server.interfacereal.bean.AisStudyinfoRealBean;
import com.ai.aris.server.interfacereal.bean.AisStudyinfoRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscBodyPart2ItemRealBean;
import com.ai.aris.server.interfacereal.bean.AiscBodypartRealBean;
import com.ai.aris.server.interfacereal.bean.AiscBodypartRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscCareprovRealBean;
import com.ai.aris.server.interfacereal.bean.AiscCareprovRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscEquipmentRealBean;
import com.ai.aris.server.interfacereal.bean.AiscEquipmentRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscItemmastRealBean;
import com.ai.aris.server.interfacereal.bean.AiscItemmastRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscLocRealBean;
import com.ai.aris.server.interfacereal.bean.AiscLocRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscOrdCat2LocRealBean;
import com.ai.aris.server.interfacereal.bean.AiscOrdcategoryRealBean;
import com.ai.aris.server.interfacereal.bean.AiscOrdcategoryRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscOrdsubcategoryRealBean;
import com.ai.aris.server.interfacereal.bean.AiscOrdsubcategoryRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscRoomRealBean;
import com.ai.aris.server.interfacereal.bean.AiscRoomRealEngine;
import com.ai.aris.server.interfacereal.bean.AiscSeverinfoRealBean;
import com.ai.aris.server.interfacereal.bean.AiscSeverinfoRealEngine;
import com.ai.aris.server.interfacereal.bean.QryStudyItemInterfaceBean;
import com.ai.aris.server.interfacereal.bean.QryordsubcateINFBean;
import com.ai.aris.server.interfacereal.bean.QryordsubcateINFEngine;
import com.ai.aris.server.webservice.BusiDataEnum;
import com.ai.aris.server.webservice.DataEnum;
import com.ai.aris.server.webservice.bean.AisPatientInfoData;
import com.ai.aris.server.webservice.bean.AisStudyReportData;
import com.ai.aris.server.webservice.service.interfaces.IAisServiceSV;
import com.ai.aris.server.workstation.bean.AisFilesInfoBean;
import com.ai.aris.server.workstation.bean.AisFilesInfoEngine;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoEngine;
import com.ai.aris.server.workstation.bean.AisReportFilesBean;
import com.ai.aris.server.workstation.bean.AisReportFilesEngine;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyReportBean;
import com.ai.aris.server.workstation.bean.AisStudyReportEngine;
import com.ai.aris.server.workstation.bean.QryReportHBean;
import com.ai.aris.server.workstation.bean.QryReportHEngine;
import com.ai.aris.server.workstation.bean.QryServerMediumBean;
import com.ai.aris.server.workstation.bean.QryServerMediumEngine;
import com.ai.aris.server.workstation.model.AisFilesInfoModel;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.common.json.TimestampMorpher;
import com.ai.common.util.DateUtils;
import com.ai.common.util.ServiceUtil;

public class AisServiceSVImpl implements IAisServiceSV {
	
	IPatientRegSV patientSv = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);
	ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);
	
	public <T extends DataContainerInterface> void save(T aBean) throws Exception{
		CommonEngine.save(aBean);
	}
	
	public void updateSynMark(List<AisStudyReportData> list)throws Exception{
		if(list!=null&&list.size()>0){
			for(AisStudyReportData bean :list){
				try {
					String delSql2 = "update ais_studyreport set REPORT_TOARCHIVE=1 where report_id="+bean.getReportId()+" and studyinfo_id="+bean.getStudyinfoId()+"";
					commonSV.execute(delSql2,null);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public AisPixInfoBean[] getAisPixInfoBean(String patientId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(patientId)) {
	    	condition.append(" AND PATIENT_ID = '" + patientId+"'" ); 
        }  
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND ORG_ID = '" + orgId+"'" ); 
        }
	    AisPixInfoBean[] pixBean = AisPixInfoEngine.getBeans(condition.toString(), null); 
	    if(pixBean.length > 0){
	    	return pixBean; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public AisPatientInfoBean[] getAisPatientBean(String idNumber) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(idNumber)) {
	    	condition.append(" AND PATIENT_IDNUMBER = '" + idNumber+"'" ); 
        }  
	    AisPatientInfoBean[] patientBean = AisPatientInfoEngine.getBeans(condition.toString(), null); 
	    if(patientBean.length > 0){
	    	return patientBean; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public boolean insertPatientBean(long id,String patientId,Map map) throws Exception{
		try {
			String patientDob = map.get("Patient_DOB").toString().equals("")?"":map.get("Patient_DOB").toString();
			
			AisPatientInfoBean bean = new AisPatientInfoBean();
			bean.setPatientGlobalid(id);
			bean.setPatientId(patientId);
			bean.setPatientName(map.get("Patient_Name").toString());
			bean.setPatientNamepy(map.get("Patient_NamePy").toString());
			bean.setPatientDob(Timestamp.valueOf(patientDob));
			bean.setPatientAge(map.get("Patient_Age").toString());
			bean.setPatientSex(map.get("Patient_Sex").toString());
			bean.setPatientIdnumber(map.get("Patient_IDNumber").toString());
			bean.setPatientCardid(map.get("Patient_CardID").toString());
			bean.setPatientMedicareid(map.get("Patient_MedicareID").toString());
			bean.setPatientMobile(map.get("Patient_Mobile").toString());
			bean.setPatientPhone(map.get("Patient_Phone").toString());
			bean.setPatientVocation(map.get("Patient_Vocation").toString());
			bean.setPatientNation(map.get("Patient_Nation").toString());
			bean.setPatientStation(map.get("Patient_Station").toString());
			bean.setPatientRemark(map.get("Patient _Remark").toString());
			bean.setPatientStatus(map.get("Patient_Status").toString());
			bean.setPatientAddress(map.get("Patient_Address").toString());
			AisPatientInfoEngine.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean insertPixBean(long id,Map map) throws Exception{
		try {
			AisPixInfoBean zbean = new AisPixInfoBean();
			zbean.setPatientGlobalid(id);
			zbean.setOrgId(map.get("Org_Id").toString());
			zbean.setPatientId(map.get("Patient_HID").toString());
			AisPixInfoEngine.save(zbean);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean updatePatient(long id,Map map) throws Exception{
		try {
			String patientDob = map.get("Patient_DOB").toString().equals("")?"":map.get("Patient_DOB").toString();
			//修改前保存原始记录
			AisPatientInfoBean oldBean = AisPatientInfoEngine.getBean(id);
			AisPatientInfoHisBean hisBean = new AisPatientInfoHisBean();
            DataContainerFactory.copyNoClearData(oldBean, hisBean);
            AisPatientInfoHisEngine.save(hisBean);
			
			AisPatientInfoBean bean = new AisPatientInfoBean();
			bean.setPatientGlobalid(id);
			bean.setStsToOld();
			bean.setPatientName(map.get("Patient_Name").toString());
			bean.setPatientNamepy(map.get("Patient_NamePy").toString());
			bean.setPatientDob(Timestamp.valueOf(patientDob));
			bean.setPatientAge(map.get("Patient_Age").toString());
			bean.setPatientSex(map.get("Patient_Sex").toString());
			bean.setPatientIdnumber(map.get("Patient_IDNumber").toString());
			bean.setPatientCardid(map.get("Patient_CardID").toString());
			bean.setPatientMedicareid(map.get("Patient_MedicareID").toString());
			bean.setPatientMobile(map.get("Patient_Mobile").toString());
			bean.setPatientPhone(map.get("Patient_Phone").toString());
			bean.setPatientVocation(map.get("Patient_Vocation").toString());
			bean.setPatientNation(map.get("Patient_Nation").toString());
			bean.setPatientStation(map.get("Patient_Station").toString());
			bean.setPatientRemark(map.get("Patient _Remark").toString());
			bean.setPatientStatus(map.get("Patient_Status").toString());
			bean.setPatientAddress(map.get("Patient_Address").toString());
			AisPatientInfoEngine.save(bean);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	@Override
	public boolean deleteBusiData(String orgId) throws Exception{
		Statement stmt = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();   
			String sql = "delete from ais_studyreport where studyinfo_id  in (select studyinfo_id from ais_studyinfo where org_id='"+orgId+"' and studystatus_code='VERIFY')";
			String sql1 = "delete from ais_studyiteminfo where studyinfo_id  in (select studyinfo_id from ais_studyinfo where org_id='"+orgId+"' and studystatus_code='VERIFY')";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql1);
			return true;
	    }catch (Exception e){
	    	e.printStackTrace(); 
	    	return false;
	    }finally{
	    	com.ai.aris.server.common.util.DBUtil.release(null, stmt, null);
		}
	}
	@Override
	public void deleteStudyinfoReal(String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND org_id = '" + orgId+"'" ); 
        }  
	    AisStudyinfoRealBean[] realBeans = AisStudyinfoRealEngine.getBeans(condition.toString(), null); 
	    if(realBeans.length > 0){
	    	for(AisStudyinfoRealBean bean :realBeans){
	    		bean.delete();
	    		AisStudyinfoRealEngine.save(bean);
	    	}
	    }
	}
	
	@Override
	public boolean insertStudyInfo(Map map) throws Exception{
		Statement stmt = null;
		try {
			stmt = ServiceManager.getSession().getConnection().createStatement();   
			long studyId = ServiceUtil.getSequence("SEQSTUDYINFOID");
			String cloumn = "STUDYINFO_ID,ORG_ID,LOC_ID,PATIENT_OUTPATIENTID,PATIENT_INPATIENTID,STUDY_EPSODEID,PATIENTTYPE_CODE,"
					+ "STUDY_APPLOCID,STUDY_APPDOC,STUDY_WARD,STUDY_BEDNO,STUDY_AIM,STUDY_CLINIC,STUDY_ITEMDESC,STUDY_APPDATE,STUDY_ACCNUMBER,STUDYSTATUS_CODE,PATIENT_GLOBALID";
			
			String studyapptime = map.get("Study_AppDateTime").toString().equals("")?"":map.get("Study_AppDateTime").toString();
			
			String cloumnValue = ""+studyId+",'"+map.get("ORG_ID")+"','"+map.get("Loc_code")+"','"+map.get("Patient_OutpatientID")+"'," +
			"'"+map.get("Patient_InpatientID")+"','"+map.get("Study_EpsodeID")+"','"+map.get("PatientType_Code")+"','"+map.get("Study_AppLoc")+"','"+map.get("Study_AppDoc")+"'," +
			"'"+map.get("Study_Ward")+"','"+map.get("Study_bedNo")+"','"+map.get("Study_Aim")+"','"+map.get("Study_Clinic")+"','"+map.get("Study_ItemDesc")+"' "
			+ "to_date('"+studyapptime+"','yyyy-MM-dd hh24:mi:ss'),'"+map.get("Study_Accnumber")+"','"+map.get("StudyStatus_Code")+"',"+map.get("PATIENT_GLOBALID")+" ";
			//
			String sql = "insert into ais_studyinfo ("+cloumn+") values ("+cloumnValue+")";
			stmt.executeUpdate(sql);
			
//			Timestamp dateTime=new Timestamp(new Date().getTime());
			String reportTime = map.get("Report_ReportDateTime").toString().equals("")?"":map.get("Report_ReportDateTime").toString();
			long newReportId = ServiceUtil.getSequence("SEQREPORTID");
			AisStudyReportBean reportBean = new AisStudyReportBean();
			reportBean.setReportId(newReportId);			 
			reportBean.setStudyinfoId(studyId);
			reportBean.setReportDatetime(Timestamp.valueOf(reportTime));
			reportBean.setReportDoctorid(map.get("Study_ReportDoctorCode").toString());
			reportBean.setReportVerifydoctorid(map.get("Report_ReportDoctor").toString());
			reportBean.setReportExammethod(map.get("Report_ExamMethod").toString());
			reportBean.setReportIspositive(Integer.parseInt(map.get("Report_IsPositive").toString()));
			reportBean.setReportRemark(map.get("Study_Remark").toString());
			
			reportBean.setReportExam(map.get("Report_Exam").toString());
			reportBean.setReportResult(map.get("Report_Result").toString());
			
			AisStudyReportEngine.save(reportBean);
			
			return true;
	    }catch (Exception e){
	    	e.printStackTrace(); 
	    	return false;
	    }finally{
	    	com.ai.aris.server.common.util.DBUtil.release(null, stmt, null);
		}
	}
	
	@Override
	public String insertPatientBean(String orgId,AisPatientInfoData model) throws Exception{
		Map seqs = patientSv.getSeq(orgId,"-999");	
		long id = Long.parseLong(seqs.get("patientGlobalid").toString());
		String pacsPatientId  = seqs.get("patientId").toString();
		//pix保存医院的病人ID
		AisPixInfoBean zbean = new AisPixInfoBean();
		zbean.setPatientGlobalid(id);
		zbean.setOrgId(orgId);
		zbean.setPatientId(model.getPatientId());
		AisPixInfoEngine.save(zbean);
		//
		AisPatientRealBean real = new AisPatientRealBean();
		real.setPatientRealId(ServiceUtil.getSequence("SEQPATIENT_REAL"));
		real.setGlobalId(id);
		real.setOldGlobalId(model.getPatientGlobalid());
		real.setOrgId(orgId);
		AisPatientRealEngine.save(real);
		//中心病人ID保存为中心规则病人ID
		AisPatientInfoBean bean = new AisPatientInfoBean();
		bean.setPatientId(pacsPatientId);
		bean.setPatientGlobalid(id);
		bean.setPatientName(model.getPatientName());
		bean.setPatientNamepy(model.getPatientNamepy());
		bean.setPatientDob(model.getPatientDob());
		bean.setPatientAge(model.getPatientAge());
		bean.setPatientSex(model.getPatientSex());
		bean.setPatientIdnumber(model.getPatientIdnumber());
		bean.setPatientCardid(model.getPatientCardid());
		bean.setPatientMedicareid(model.getPatientMedicareid());
		bean.setPatientMobile(model.getPatientMobile());
		bean.setPatientPhone(model.getPatientPhone());
		bean.setPatientVocation(model.getPatientVocation());
		bean.setPatientNation(model.getPatientNation());
		bean.setPatientStation(model.getPatientStation());
		bean.setPatientStatus(model.getPatientStatus());
		bean.setPatientRemark(model.getPatientRemark());
		bean.setPatientAddress(model.getPatientAddress());
		bean.setOrgId(orgId);
		AisPatientInfoEngine.save(bean);
		return String.valueOf(id);
	}
	
	@Override
	public void insertPixBean(long id,String orgId,AisPatientInfoData model) throws Exception{
		try {
			//pix保存医院的病人ID
			AisPixInfoBean zbean = new AisPixInfoBean();
			zbean.setPatientGlobalid(id);
			zbean.setOrgId(orgId);
			zbean.setPatientId(model.getPatientId());
			AisPixInfoEngine.save(zbean);
			//
			AisPatientRealBean real = new AisPatientRealBean();
			real.setPatientRealId(ServiceUtil.getSequence("SEQPATIENT_REAL"));
			real.setGlobalId(id);
			real.setOldGlobalId(model.getPatientGlobalid());
			real.setOrgId(orgId);
			AisPatientRealEngine.save(real);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void updatePatient(long id,AisPatientInfoData model) throws Exception{
		AisPatientInfoBean oldBean = AisPatientInfoEngine.getBean(id);
		oldBean.setStsToOld();
		oldBean.setPatientName(model.getPatientName());
		oldBean.setPatientNamepy(model.getPatientNamepy());
		oldBean.setPatientDob(model.getPatientDob());
		oldBean.setPatientAge(model.getPatientAge());
		oldBean.setPatientSex(model.getPatientSex());
		oldBean.setPatientIdnumber(model.getPatientIdnumber());
		oldBean.setPatientCardid(model.getPatientCardid());
		oldBean.setPatientMedicareid(model.getPatientMedicareid());
		oldBean.setPatientMobile(model.getPatientMobile());
		oldBean.setPatientPhone(model.getPatientPhone());
		oldBean.setPatientAddress(model.getPatientAddress());
        AisPatientInfoEngine.save(oldBean);
	}
	@Override
	public AiscLocRealBean getLocRealBean(long locId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and old_loc_id="+locId ); 
	    AiscLocRealBean[] realBean = AiscLocRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public AisStudyItemInfoBean[] getStudyItem(long studyinfoId) throws Exception{
		String sql = "SELECT * FROM AIS_STUDYITEMINFO WHERE STUDYINFO_ID = " + studyinfoId;
		AisStudyItemInfoBean[] studyItemInfoBeans = AisStudyItemInfoEngine.getBeansFromSql(sql, null); 
		if(studyItemInfoBeans.length > 0){
	    	return studyItemInfoBeans; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public AisPatientRealBean getRealGlobalId(long globalId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_GLOBAL_ID ="+globalId ); 
	    AisPatientRealBean[] realBean = AisPatientRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	@Override
	public AiscItemmastRealBean getStudyItemRealBean(long oldmastId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_ITEMMAST_ID ="+oldmastId ); 
	    AiscItemmastRealBean[] realBean = AiscItemmastRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	@Override
	public AiscBodypartRealBean getBodypartRealBean(long oldmastId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_BODYPART_CODE ="+oldmastId ); 
	    AiscBodypartRealBean[] realBean = AiscBodypartRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public AisStudyinfoRealBean getStudyinfoRealBean(long studyinfoId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_STUDYINFO_ID ="+studyinfoId ); 
	    AisStudyinfoRealBean[] realBean = AisStudyinfoRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	@Override
	public AiscCareprovRealBean getCareBean(long careId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_CAREPROV_ID ="+careId ); 
	    AiscCareprovRealBean[] realBean = AiscCareprovRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	@Override
	public AiscEquipmentRealBean getEquipmentBean(long equipId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_EQUIPMENT_ID ="+equipId ); 
	    AiscEquipmentRealBean[] realBean = AiscEquipmentRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	@Override
	public AiscRoomRealBean getRoomReal(long roomId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and OLD_ROOM_ID ="+roomId ); 
	    AiscRoomRealBean[] realBean = AiscRoomRealEngine.getBeans(condition.toString(), null); 
	    if(realBean.length > 0){
	    	return realBean[0]; 
	    }else{
	    	return null;
	    }
	}
	
	@Override
	public void insertStudyItem(QryStudyItemInterfaceBean bean)throws Exception{
		AisStudyItemInfoBean sbean = new AisStudyItemInfoBean();
		sbean.setStudyinfoId(bean.getStudyinfoId());
		sbean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
		sbean.setStudyitemHisid(bean.getStudyitemHisid());
		sbean.setStudyitemCode(bean.getStudyitemCode());
		sbean.setStudyitemDesc(bean.getStudyitemDesc());
		sbean.setStudyitemEndesc(bean.getStudyitemEndesc());
		sbean.setStudyitemBodyinfo(bean.getStudyitemBodyinfo());
		sbean.setStudyitemPrice(bean.getStudyitemPrice());
		sbean.setStudyitemNumber(Integer.parseInt(String.valueOf(bean.getStudyitemNumber())));
		sbean.setStudyitemStatus(bean.getStudyitemStatus());
		sbean.setStudyitemItemno(bean.getStudyitemItemno());
		sbean.setStudyitemBodypart(bean.getStudyitemBodypart());
		AisStudyItemInfoEngine.save(sbean);
	}
	
	@Override
	public void insertStudyinfoReal(long newStudyInfoId,AisStudyInfoBean bean)throws Exception{
		AisStudyinfoRealBean realbean = new AisStudyinfoRealBean();
		realbean.setStudyinfoRealId(ServiceUtil.getSequence("SEQSTUDYTEMPID"));
		realbean.setStudyinfoId(newStudyInfoId);
		realbean.setOldStudyinfoId(bean.getStudyinfoId());
		realbean.setOrgId(bean.getOrgId());
		AisStudyinfoRealEngine.save(realbean);
	}
	
	@Override
    public AiscServerInfoBean getServerInfo(AiscServerInfoBean bean) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and server_ip='"+bean.getServerIp()+"'");
        condition.append(" and server_type = '"+bean.getServerType()+"' ");
        AiscServerInfoBean[] oldBean = AiscServerInfoEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
    }
	@Override
	public AiscLocBean getLocInfo(AiscLocBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and loc_code='"+bean.getLocCode()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscLocBean[] oldBean = AiscLocEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public AiscRoomBean getRoomInfo(AiscRoomBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        AiscLocRealBean[] realBeen = commonSV.getBeans(" ORG_ID = "+bean.getOrgId()+" and old_loc_id="+bean.getLocId(),null,AiscLocRealBean.class);
        if(realBeen!=null&&realBeen.length>0){
        	condition.append(" and loc_id="+realBeen[0].getLocId()+"");
        }else{
        	condition.append(" and loc_id="+bean.getLocId()+"");
        }
        condition.append(" and room_desc='"+bean.getRoomDesc()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscRoomBean[] oldBean = AiscRoomEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public AiscEquipmentBean getEquipment(AiscEquipmentBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and equipment_code='"+bean.getEquipmentCode()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscEquipmentBean[] oldBean = AiscEquipmentEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public AiscOrdCategoryBean getCategory(AiscOrdCategoryBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and ordcategory_code='"+bean.getOrdcategoryCode()+"'");
        condition.append(" and org_id='"+bean.getOrgId()+"'");
        AiscOrdCategoryBean[] oldBean = AiscOrdCategoryEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public QryordsubcateINFBean getAiscOrdSubCategory(AiscOrdSubCategoryBean bean,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and ordsubcategory_code='"+bean.getOrdsubcategoryCode()+"'");
        condition.append(" and org_id='"+orgId+"'");
        QryordsubcateINFBean[] oldBean = QryordsubcateINFEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public long getRealOrdcategoryId(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_ordcategory_id="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscOrdcategoryRealBean[] oldBean = AiscOrdcategoryRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getOrdcategoryId();
        }else{
        	return id;
        }
	}
	
	public long getItemmastId(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_itemmast_id="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscItemmastRealBean[] oldBean = AiscItemmastRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getItemmastId();
        }else{
        	return id;
        }
	}
	
	public long getLocId(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_loc_id="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscLocRealBean[] oldBean = AiscLocRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getLocId();
        }else{
        	return id;
        }
	}
	
	public long getRoomId(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_room_id="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscRoomRealBean[] oldBean = AiscRoomRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getRoomId();
        }else{
        	return id;
        }
	}
	
	public long getServerId(long id) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_server_id="+id);
        AiscSeverinfoRealBean[] oldBean = AiscSeverinfoRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getServerId();
        }else{
        	return id;
        }
	}
	
	public long getBodyCode(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_bodypart_code="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscBodypartRealBean[] oldBean = AiscBodypartRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getBodypartCode();
        }else{
        	return id;
        }
	}
	
	public long getRealOrdSubcategoryId(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and old_subcate_id="+id);
        condition.append(" and org_id='"+orgId+"'");
        AiscOrdsubcategoryRealBean[] oldBean = AiscOrdsubcategoryRealEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0].getSubcateId();
        }else{
        	return id;
        }
	}
	
	public AiscItemMastBean getAiscItemMast(AiscItemMastBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and itemmast_code='"+bean.getItemmastCode()+"'");
        condition.append(" and itemmast_desc='"+bean.getItemmastDesc()+"'");
        condition.append(" and org_id='"+bean.getOrgId()+"'");
        AiscItemMastBean[] oldBean = AiscItemMastEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public AiscBodyPartBean getAiscBodyPart(AiscBodyPartBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and bodypart_desc='"+bean.getBodypartDesc()+"'");
        condition.append(" and org_id='"+bean.getOrgId()+"'");
        condition.append(" and bodypart_type='"+bean.getBodypartType()+"'");
        AiscBodyPartBean[] oldBean = AiscBodyPartEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }	
	}
	
	public AiscBodyPart2ItemBean getAiscBodyPartItem(AiscBodyPart2ItemBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        AiscItemmastRealBean[] realBeen = commonSV.getBeans(" ORG_ID = "+bean.getOrgId()+" and old_itemmast_id="+bean.getItemmastId(),null,AiscItemmastRealBean.class);
        if(realBeen!=null&&realBeen.length>0){
        	condition.append(" and itemmast_id="+realBeen[0].getItemmastId()+"");
        }else{
        	condition.append(" and itemmast_id="+bean.getItemmastId()+"");
        }
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscBodypartRealBean[] partBeen = commonSV.getBeans(" ORG_ID = "+bean.getOrgId()+" and old_bodypart_code="+bean.getBodypartCode(),null,AiscBodypartRealBean.class);
        if(partBeen!=null&&partBeen.length>0){
        	condition.append(" and bodypart_code="+partBeen[0].getBodypartCode()+"");
        }else{
        	condition.append(" and bodypart_code="+bean.getBodypartCode()+"");
        }
        condition.append(" and org_id='"+bean.getOrgId()+"'");
        AiscBodyPart2ItemBean[] oldBean = AiscBodyPart2ItemEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public AiscOrdCat2LocBean getAiscOrdcat2loc(AiscOrdCat2LocBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        AiscOrdcategoryRealBean[] ordcat = commonSV.getBeans(" ORG_ID = "+bean.getOrgId()+" and old_ordcategory_id="+bean.getOrdcatId(),null,AiscOrdcategoryRealBean.class);
        if(ordcat!=null&&ordcat.length>0){
        	condition.append(" and ordcat_id="+ordcat[0].getOrdcategoryId()+"");
        }else{
        	condition.append(" and ordcat_id="+bean.getOrdcatId()+"");
        }
        AiscLocRealBean[] realBeen = commonSV.getBeans(" ORG_ID = "+bean.getOrgId()+" and old_loc_id="+bean.getLocId(),null,AiscLocRealBean.class);
        if(realBeen!=null&&realBeen.length>0){
        	condition.append(" and loc_id="+realBeen[0].getLocId()+"");
        }else{
        	condition.append(" and loc_id="+bean.getLocId()+"");
        }
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscOrdCat2LocBean[] oldBean = AiscOrdCat2LocEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }	
	}
	
	public AiscCareProvBean getAiscCareprov(AiscCareProvBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and careprov_code='"+bean.getCareprovCode()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        AiscCareProvBean[] oldBean = AiscCareProvEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }	
	}
	
	public String sendData(String body,String orgId) throws Exception{
		Date currentTime = SysdateManager.getSysDate();
		JSONObject jsonbody = JSONObject.fromObject(body); 
		String result = "success";
//		try{
			for (DataEnum c : DataEnum.values()) {
				if("SEVERINFO".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("SEVERINFO"));
					List<AiscServerInfoBean> list = (List<AiscServerInfoBean>) JSONArray.toCollection(jsonArray,AiscServerInfoBean.class);
					for(AiscServerInfoBean serverBean :list){
						AiscServerInfoBean yBean = getServerInfo(serverBean);
						if(yBean==null){
							AiscSeverinfoRealBean newBean =new AiscSeverinfoRealBean();
							//插入新服务器ID与源服务器ID对应关系
							newBean.setServerRealId(commonSV.getSequence("SEQ_AISC_SEVERINFO_REAL"));
							newBean.setOldServerId(serverBean.getServerId());
							newBean.setOrgId(orgId);
							serverBean.setServerId(commonSV.getSequence("SEQSERVERINFOID"));
							newBean.setServerId(serverBean.getServerId());
							commonSV.save(serverBean);
							commonSV.save(newBean);
						}else{
							AiscSeverinfoRealBean qryBean = commonSV.getBean(" OLD_SERVER_ID = "+serverBean.getServerId()+" and org_id='"+orgId+"'",null,AiscSeverinfoRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_SEVERINFO_REAL t set SERVER_ID= "+yBean.getServerId()+",CREATE_TIME =to_date( '"+currentTime+"', 'yyyy-mm-dd hh24:mi:ss')\n" +
										"  where t.OLD_SERVER_ID =  "+serverBean.getServerId()+" and t.org_id= '"+orgId+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscSeverinfoRealBean realBean = new AiscSeverinfoRealBean();
								realBean.setServerRealId(commonSV.getSequence("SEQ_AISC_SEVERINFO_REAL"));
								realBean.setOldServerId(serverBean.getServerId());
								realBean.setOrgId(orgId);
								realBean.setServerId(yBean.getServerId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("ROOM".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("ROOM"));
					List<AiscRoomBean> list = (List<AiscRoomBean>) JSONArray.toCollection(jsonArray,AiscRoomBean.class);
					for(AiscRoomBean roomBean :list){
						//判断房间是否存在
						AiscRoomBean ybean = getRoomInfo(roomBean);
						if(ybean==null){
							AiscRoomRealBean realBean =new AiscRoomRealBean();
							realBean.setRoomRealId(commonSV.getSequence("SEQ_AISC_ROOM_REAL"));
							realBean.setOldRoomId(roomBean.getRoomId());
							realBean.setOrgId(String.valueOf(roomBean.getOrgId()));
							roomBean.setRoomId(commonSV.getSequence("SEQROOMID"));
							long locId = getLocId(roomBean.getLocId(),orgId);
							roomBean.setLocId(locId);
							realBean.setRoomId(roomBean.getRoomId());
							commonSV.save(roomBean);
							commonSV.save(realBean);
						}else{
							AiscRoomRealBean qryBean = commonSV.getBean(" OLD_ROOM_ID = "+roomBean.getRoomId()+" and org_id='"+ybean.getOrgId()+"'",null,AiscRoomRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_ROOM_REAL t set ROOM_ID= "+ybean.getRoomId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_ROOM_ID =  "+roomBean.getRoomId()+" and t.org_id= "+ybean.getOrgId() ;
								commonSV.execute(upSql,null);
							}else{
								AiscRoomRealBean realBean =new AiscRoomRealBean();
								realBean.setRoomRealId(commonSV.getSequence("SEQ_AISC_ROOM_REAL"));
								realBean.setOldRoomId(roomBean.getRoomId());
								realBean.setOrgId(String.valueOf(roomBean.getOrgId()));
								realBean.setRoomId(ybean.getRoomId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("LOC".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("LOC"));
					List<AiscLocBean> list = (List<AiscLocBean>) JSONArray.toCollection(jsonArray,AiscLocBean.class);
					for(AiscLocBean locBeen :list){
						AiscLocBean oldBean = getLocInfo(locBeen);
						if(oldBean==null){
							//插入新科室及关系对应
							AiscLocRealBean realBean = new AiscLocRealBean();
							realBean.setLocRealId(commonSV.getSequence("SEQ_AISC_LOC_REAL"));
							realBean.setOrgId(locBeen.getOrgId());
							realBean.setOldLocId(locBeen.getLocId());
							locBeen.setLocId(commonSV.getSequence("SEQLOCID"));
							long serverId = getServerId(locBeen.getServerId());
							locBeen.setServerId(serverId);
							realBean.setLocId(locBeen.getLocId());
							commonSV.save(locBeen);
							commonSV.save(realBean);
						}else{
							AiscLocRealBean qryBean = commonSV.getBean(" OLD_LOC_ID = "+locBeen.getLocId()+" and org_id='"+oldBean.getOrgId()+"'",null,AiscLocRealBean.class);
							if (qryBean!=null){
								String upSql= " update aisc_loc_real t set loc_id= "+oldBean.getLocId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_LOC_ID =  "+locBeen.getLocId()+" and t.org_id= "+oldBean.getOrgId() ;
								commonSV.execute(upSql,null);
							}else{
								AiscLocRealBean realBean = new AiscLocRealBean();
								realBean.setLocRealId(commonSV.getSequence("SEQ_AISC_LOC_REAL"));
								realBean.setOrgId(locBeen.getOrgId());
								realBean.setOldLocId(locBeen.getLocId());
								realBean.setLocId(oldBean.getLocId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("EQUIPMENT".equals(c.getInterfaceName())){
					JsonConfig jsonConfig = new JsonConfig();
					String[] formats={"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};  
					JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats)); 
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("EQUIPMENT"),jsonConfig);
					List<AiscEquipmentBean> list = (List<AiscEquipmentBean>) JSONArray.toCollection(jsonArray,AiscEquipmentBean.class);
					for(AiscEquipmentBean equipmentBeen:list){
						AiscEquipmentBean oldBean = getEquipment(equipmentBeen);
						if(oldBean==null){
							AiscEquipmentRealBean realBean  =new AiscEquipmentRealBean();
							realBean.setEquipmentRealId(commonSV.getSequence("SEQ_AISC_EQUIPMENT_REAL"));
							realBean.setOrgId(equipmentBeen.getOrgId());
							realBean.setOldEquipmentId(equipmentBeen.getEquipmentId());
							equipmentBeen.setEquipmentId(commonSV.getSequence("SEQEQUIPMENTID"));
							long locId = getLocId(equipmentBeen.getLocId(),orgId);
							equipmentBeen.setLocId(locId);
							long roomId = getRoomId(equipmentBeen.getRoomId(),orgId);
							equipmentBeen.setRoomId(roomId);
							realBean.setEquipmentId(equipmentBeen.getEquipmentId());
//							long ordsubcategoryId = getRealOrdSubcategoryId(equipmentBeen.getOrdsubcategoryId(),orgId);
//							equipmentBeen.setOrdsubcategoryId(ordsubcategoryId);
							commonSV.save(equipmentBeen);
							commonSV.save(realBean);
						}else{
							AiscEquipmentRealBean qryBean = commonSV.getBean(" OLD_EQUIPMENT_ID = "+equipmentBeen.getEquipmentId()+" and org_id='"+oldBean.getOrgId()+"'",null,AiscEquipmentRealBean.class);
							if (qryBean!=null){
								String upSql= " update aisc_equipment_real t set EQUIPMENT_ID= "+oldBean.getEquipmentId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_EQUIPMENT_ID =  "+equipmentBeen.getEquipmentId()+" and t.org_id= "+oldBean.getOrgId() ;
								commonSV.execute(upSql,null);
							}else{
								AiscEquipmentRealBean realBean = new AiscEquipmentRealBean();
								realBean.setEquipmentRealId(commonSV.getSequence("SEQ_AISC_EQUIPMENT_REAL"));
								realBean.setOrgId(equipmentBeen.getOrgId());
								realBean.setOldEquipmentId(equipmentBeen.getEquipmentId());
								realBean.setEquipmentId(oldBean.getEquipmentId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("ORDCATEGORY".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("ORDCATEGORY"));
					List<AiscOrdCategoryBean> list = (List<AiscOrdCategoryBean>) JSONArray.toCollection(jsonArray,AiscOrdCategoryBean.class);
					for (AiscOrdCategoryBean ordCategoryBeen:list){
						AiscOrdCategoryBean oldBean = getCategory(ordCategoryBeen);
						if(oldBean==null){
							AiscOrdcategoryRealBean realBean =new AiscOrdcategoryRealBean();
							realBean.setOrdcategoryRealId(commonSV.getSequence("SEQ_AISC_ORDCATEGORY_REAL"));
							realBean.setOldOrdcategoryId(ordCategoryBeen.getOrdcategoryId());
							realBean.setOrgId(orgId);
							ordCategoryBeen.setOrdcategoryId(commonSV.getSequence("SEQ_ORDCATEGORY"));
							realBean.setOrdcategoryId(ordCategoryBeen.getOrdcategoryId());
							commonSV.save(ordCategoryBeen);
							commonSV.save(realBean);
						}else{
							AiscOrdcategoryRealBean qryBean = commonSV.getBean(" OLD_ORDCATEGORY_ID = "+ordCategoryBeen.getOrdcategoryId()+" and org_id='"+orgId+"'",null,AiscOrdcategoryRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_ORDCATEGORY_REAL t set ORDCATEGORY_ID= "+oldBean.getOrdcategoryId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_ORDCATEGORY_ID =  "+ordCategoryBeen.getOrdcategoryId()+" and t.org_id= '"+orgId+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscOrdcategoryRealBean realBean =new AiscOrdcategoryRealBean();
								realBean.setOrdcategoryRealId(commonSV.getSequence("SEQ_AISC_ORDCATEGORY_REAL"));
								realBean.setOldOrdcategoryId(ordCategoryBeen.getOrdcategoryId());
								realBean.setOrgId(orgId);
								realBean.setOrdcategoryId(oldBean.getOrdcategoryId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("ORDSUBCATEGORY".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("ORDSUBCATEGORY"));
					List<AiscOrdSubCategoryBean> list = (List<AiscOrdSubCategoryBean>) JSONArray.toCollection(jsonArray,AiscOrdSubCategoryBean.class);
					for (AiscOrdSubCategoryBean ordSubCategoryBeen:list){
						QryordsubcateINFBean oldBean = getAiscOrdSubCategory(ordSubCategoryBeen,orgId);
						if(oldBean==null){
							AiscOrdsubcategoryRealBean realBean =new AiscOrdsubcategoryRealBean();
							realBean.setSubcateRealId(commonSV.getSequence("SEQ_aisc_ordsubcategory_real"));
							realBean.setOldSubcateId (ordSubCategoryBeen.getOrdsubcategoryId());
							realBean.setOrgId(orgId);
							ordSubCategoryBeen.setOrdsubcategoryId(commonSV.getSequence("SEQ_ORDSUBCATEGORY"));
							realBean.setSubcateId(ordSubCategoryBeen.getOrdsubcategoryId());
							long id = getRealOrdcategoryId(ordSubCategoryBeen.getOrdcategoryId(),orgId);
							ordSubCategoryBeen.setOrdcategoryId(id);
							commonSV.save(ordSubCategoryBeen);
							commonSV.save(realBean);
						}else{
							AiscOrdsubcategoryRealBean qryBean = commonSV.getBean(" old_subcate_id = "+ordSubCategoryBeen.getOrdsubcategoryId()+" and org_id='"+orgId+"'",null,AiscOrdsubcategoryRealBean.class);
							if (qryBean!=null){
								String upSql= " update aisc_ordsubcategory_real t set subcate_id= "+oldBean.getOrdsubcategoryId()+",CREATE_TIME =sysdate" +
										"  where t.old_subcate_id =  "+ordSubCategoryBeen.getOrdsubcategoryId()+" and t.org_id= '"+orgId+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscOrdsubcategoryRealBean realBean =new AiscOrdsubcategoryRealBean();
								realBean.setSubcateRealId(commonSV.getSequence("SEQ_aisc_ordsubcategory_real"));
								realBean.setOldSubcateId (ordSubCategoryBeen.getOrdsubcategoryId());
								realBean.setOrgId(orgId);
								realBean.setSubcateId(oldBean.getOrdsubcategoryId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("ITEMMAST".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("ITEMMAST"));
					List<AiscItemMastBean> list = (List<AiscItemMastBean>) JSONArray.toCollection(jsonArray,AiscItemMastBean.class);
					for (AiscItemMastBean itemMastBeen:list){
						AiscItemMastBean oldBean =  getAiscItemMast(itemMastBeen);
						if(oldBean==null){
							AiscItemmastRealBean newBean =new AiscItemmastRealBean();
							newBean.setItemmastRealId(commonSV.getSequence("SEQ_AISC_ITEMMAST_REAL"));
							newBean.setOldItemmastId(itemMastBeen.getItemmastId());
							newBean.setOrgId(orgId);
							itemMastBeen.setItemmastId(commonSV.getSequence("SEQ_ITEMMAST"));
							newBean.setItemmastId(itemMastBeen.getItemmastId());
							long id = getRealOrdcategoryId(itemMastBeen.getOrdcategoryId(),orgId);
							itemMastBeen.setOrdcategoryId(id);
							long subid = getRealOrdSubcategoryId(itemMastBeen.getOrdsubcategoryId(),orgId);
							itemMastBeen.setOrdsubcategoryId(subid);
							commonSV.save(itemMastBeen);
							commonSV.save(newBean);
						}else{
							AiscItemmastRealBean qryBean = commonSV.getBean(" OLD_ITEMMAST_ID = "+itemMastBeen.getItemmastId()+" and org_id='"+orgId+"'",null,AiscItemmastRealBean.class);
							if (qryBean!=null){
								String upSql= " update aisc_itemmast_real t set ITEMMAST_ID= "+oldBean.getItemmastId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_ITEMMAST_ID =  "+itemMastBeen.getItemmastId()+" and t.org_id= '"+orgId+"'";
								commonSV.execute(upSql,null);
							}else{
								AiscItemmastRealBean newBean =new AiscItemmastRealBean();
								newBean.setItemmastRealId(commonSV.getSequence("SEQ_AISC_ITEMMAST_REAL"));
								newBean.setOldItemmastId(itemMastBeen.getItemmastId());
								newBean.setOrgId(orgId);
								newBean.setItemmastId(oldBean.getItemmastId());
								commonSV.save(newBean);
							}
						}
					}
				}
				if("BODYPART".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("BODYPART"));
					List<AiscBodyPartBean> list = (List<AiscBodyPartBean>) JSONArray.toCollection(jsonArray,AiscBodyPartBean.class);
					for (AiscBodyPartBean bodyPartBeen:list){
						AiscBodyPartBean oldBean =  getAiscBodyPart(bodyPartBeen);
						if(oldBean==null){
							AiscBodypartRealBean newBean =new AiscBodypartRealBean();
							newBean.setBodypartRealId(commonSV.getSequence("SEQ_AISC_BODYPART_REAL"));
							newBean.setOldBodypartCode(Long.parseLong(bodyPartBeen.getBodypartCode()));
							newBean.setOrgId(orgId);
							bodyPartBeen.setBodypartCode(String.valueOf(commonSV.getSequence("SEQBODYPARTID")));
							if(bodyPartBeen.getBodypartPid()!=-1){
								long id = getBodyCode(bodyPartBeen.getBodypartPid(),orgId);
								bodyPartBeen.setBodypartPid(id);
							}
							newBean.setBodypartCode(Long.parseLong(bodyPartBeen.getBodypartCode()));
							commonSV.save(bodyPartBeen);
							commonSV.save(newBean);
						}else{
							AiscBodypartRealBean qryBean = commonSV.getBean(" OLD_BODYPART_CODE = "+bodyPartBeen.getBodypartCode()+" and org_id='"+orgId+"'",null,AiscBodypartRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_BODYPART_REAL t set BODYPART_CODE= "+oldBean.getBodypartCode()+",CREATE_TIME =sysdate" +
										"  where t.OLD_BODYPART_CODE =  "+bodyPartBeen.getBodypartCode()+" and t.org_id= '"+orgId+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscBodypartRealBean newBean =new AiscBodypartRealBean();
								newBean.setBodypartRealId(commonSV.getSequence("SEQ_AISC_BODYPART_REAL"));
								newBean.setOldBodypartCode(Long.parseLong(bodyPartBeen.getBodypartCode()));
								newBean.setOrgId(orgId);
								newBean.setBodypartCode(Long.parseLong(oldBean.getBodypartCode()));
								commonSV.save(newBean);
							}
						}
					}
				}
				if("BODYPART2ITEM".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("BODYPART2ITEM"));
					List<AiscBodyPart2ItemBean> list = (List<AiscBodyPart2ItemBean>) JSONArray.toCollection(jsonArray,AiscBodyPart2ItemBean.class);
					for (AiscBodyPart2ItemBean bodyPart2ItemBeen:list){
						AiscBodyPart2ItemBean oldBean = getAiscBodyPartItem(bodyPart2ItemBeen);
						if(oldBean==null){
							AiscBodyPart2ItemRealBean realBean =new AiscBodyPart2ItemRealBean();
							realBean.setItemRealId(commonSV.getSequence("SEQ_AISC_BODYPART2ITEM_REAL"));
							realBean.setOldItemId(bodyPart2ItemBeen.getBodypart2Id());
							realBean.setOrgId(String.valueOf(bodyPart2ItemBeen.getOrgId()));
							bodyPart2ItemBeen.setBodypart2Id(commonSV.getSequence("SEQBODYPARTITEMID"));
							realBean.setItemId(bodyPart2ItemBeen.getBodypart2Id());
							long mastid = getItemmastId(bodyPart2ItemBeen.getItemmastId(),orgId);
							bodyPart2ItemBeen.setItemmastId(mastid);
							long bodyCode = getBodyCode(Long.parseLong(bodyPart2ItemBeen.getBodypartCode()),orgId);
							bodyPart2ItemBeen.setBodypartCode(String.valueOf(bodyCode));
							commonSV.save(bodyPart2ItemBeen);
							commonSV.save(realBean);
						}else{
							AiscBodyPart2ItemRealBean qryBean = commonSV.getBean(" OLD_ITEM_ID = "+bodyPart2ItemBeen.getBodypart2Id()+" and org_id='"+String.valueOf(bodyPart2ItemBeen.getOrgId())+"'",null,AiscBodyPart2ItemRealBean.class);
							if (qryBean!=null){
								String upSql= " update aisc_bodypart2item_real t set ITEM_ID= "+oldBean.getBodypart2Id()+",CREATE_TIME =sysdate" +
										"  where t.OLD_ITEM_ID =  "+bodyPart2ItemBeen.getBodypart2Id()+" and t.org_id= '"+String.valueOf(bodyPart2ItemBeen.getOrgId())+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscBodyPart2ItemRealBean realBean =new AiscBodyPart2ItemRealBean();
								realBean.setItemRealId(commonSV.getSequence("SEQ_AISC_BODYPART2ITEM_REAL"));
								realBean.setOldItemId(bodyPart2ItemBeen.getBodypart2Id());
								realBean.setOrgId(String.valueOf(bodyPart2ItemBeen.getOrgId()));
								realBean.setItemId(oldBean.getBodypart2Id());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("ORDCAT2LOC".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("ORDCAT2LOC"));
					List<AiscOrdCat2LocBean> list = (List<AiscOrdCat2LocBean>) JSONArray.toCollection(jsonArray,AiscOrdCat2LocBean.class);
					for (AiscOrdCat2LocBean ordCat2LocBeen:list){
						AiscOrdCat2LocBean oldBean = getAiscOrdcat2loc(ordCat2LocBeen);
						if(oldBean==null){
							AiscOrdCat2LocRealBean realBean = new AiscOrdCat2LocRealBean();
							realBean.setOrdcat2locRealId(commonSV.getSequence("SEQ_AISC_ORDCAT2LOC_REAL"));
							realBean.setOldOrdcat2locId(ordCat2LocBeen.getOrdcat2locId());
							realBean.setOrgId(String.valueOf(ordCat2LocBeen.getOrgId()));
							ordCat2LocBeen.setOrdcat2locId(commonSV.getSequence("SEQORDCAT2LOC"));
							long locId = getLocId(ordCat2LocBeen.getLocId(),orgId);
							ordCat2LocBeen.setLocId(locId);
							long id = getRealOrdcategoryId(ordCat2LocBeen.getOrdcatId(),orgId);
							ordCat2LocBeen.setOrdcatId(id);;
							realBean.setOrdcat2locId(ordCat2LocBeen.getOrdcat2locId());
							commonSV.save(ordCat2LocBeen);
							commonSV.save(realBean);
						}else{
							AiscOrdCat2LocRealBean qryBean = commonSV.getBean(" old_ORDCAT2LOC_id = "+ordCat2LocBeen.getOrdcat2locId()+" and org_id='"+String.valueOf(ordCat2LocBeen.getOrgId())+"'",null,AiscOrdCat2LocRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_ORDCAT2LOC_REAL t set ORDCAT2LOC_id= "+oldBean.getOrdcat2locId()+",CREATE_TIME =sysdate" +
										"  where t.old_ORDCAT2LOC_id =  "+ordCat2LocBeen.getOrdcat2locId()+" and t.org_id= '"+String.valueOf(ordCat2LocBeen.getOrgId())+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscOrdCat2LocRealBean realBean = new AiscOrdCat2LocRealBean();
								realBean.setOrdcat2locRealId(commonSV.getSequence("SEQ_AISC_ORDCAT2LOC_REAL"));
								realBean.setOldOrdcat2locId(ordCat2LocBeen.getOrdcat2locId());
								realBean.setOrgId(String.valueOf(ordCat2LocBeen.getOrgId()));
								realBean.setOrdcat2locId(oldBean.getOrdcat2locId());
								commonSV.save(realBean);
							}
						}
					}
				}
				if("CAREPROV".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("CAREPROV"));
					List<AiscCareProvBean> list = (List<AiscCareProvBean>) JSONArray.toCollection(jsonArray,AiscCareProvBean.class);
					for (AiscCareProvBean careProvBeen:list){
						AiscCareProvBean oldBean = getAiscCareprov(careProvBeen);
						if(oldBean==null){
							AiscCareprovRealBean realBean =new AiscCareprovRealBean();
							realBean.setCareprovRealId(commonSV.getSequence("SEQ_AISC_CAREPROV_REAL"));
							realBean.setOldCareprovId(careProvBeen.getCareprovId());
							realBean.setOrgId(String.valueOf(careProvBeen.getOrgId()));
							careProvBeen.setCareprovId(commonSV.getSequence("SEQ_AISC_CAREPROV"));
							realBean.setCareprovId(careProvBeen.getCareprovId());
							long locId = getLocId(careProvBeen.getLocId(),orgId);
							careProvBeen.setLocId(locId);
							commonSV.save(careProvBeen);
							commonSV.save(realBean);
						}else{
							AiscCareprovRealBean qryBean = commonSV.getBean(" OLD_CAREPROV_ID = "+careProvBeen.getCareprovId()+" and org_id='"+String.valueOf(careProvBeen.getOrgId())+"'",null,AiscCareprovRealBean.class);
							if (qryBean!=null){
								String upSql= " update AISC_CAREPROV_REAL t set CAREPROV_ID= "+oldBean.getCareprovId()+",CREATE_TIME =sysdate" +
										"  where t.OLD_CAREPROV_ID =  "+careProvBeen.getCareprovId()+" and t.org_id= '"+String.valueOf(careProvBeen.getOrgId())+"'" ;
								commonSV.execute(upSql,null);
							}else{
								AiscCareprovRealBean realBean =new AiscCareprovRealBean();
								realBean.setCareprovRealId(commonSV.getSequence("SEQ_AISC_CAREPROV_REAL"));
								realBean.setOldCareprovId(careProvBeen.getCareprovId());
								realBean.setOrgId(String.valueOf(careProvBeen.getOrgId()));
								realBean.setCareprovId(oldBean.getCareprovId());
								commonSV.save(realBean);
							}
						}
					}
				}
			}
			translateBatch(orgId);
//		}catch(Exception e){
//			result = "faild";
//			e.printStackTrace();
//		}
		return result;
	}
	
	public String sendBusiData(String body,String orgId) throws Exception{
		Date currentTime = SysdateManager.getSysDate();
		JSONObject jsonbody = JSONObject.fromObject(body); 
		String result = "success";
			for (BusiDataEnum c : BusiDataEnum.values()) {
				if("PATIENTINFO".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("PATIENTINFO"));
					List<AisPatientInfoData> list = (List<AisPatientInfoData>) JSONArray.toCollection(jsonArray,AisPatientInfoData.class);
					//首先根据身份证号判断
					for(AisPatientInfoData model:list){
						AisPatientInfoBean[] patientBean = null;
						if(isNotBlank(model.getPatientIdnumber())){
							patientBean = getAisPatientBean(model.getPatientIdnumber());
						}
						//查询ais_pixinfo表中是否有记录
						AisPixInfoBean[] pixBean = getAisPixInfoBean(model.getPatientId(),orgId);
	
						//该情况只有人为修改数据的时候才会出现
						if(pixBean!=null&&patientBean==null){
							//pixBean 更新
	
							//patient 插入
						}else if(pixBean==null&&patientBean==null){
							//patient 插入&pixBean 插入
							insertPatientBean(orgId,model);
						}else if(pixBean==null&&patientBean!=null){
							//查询pacs已有病人信息
							if(patientBean!=null&&patientBean.length>0){
								for(int hh=0;hh<patientBean.length;hh++){
									//插入pixBean
									if(isFlag(patientBean[0].getPatientGlobalid(),orgId)){
										insertPixBean(patientBean[hh].getPatientGlobalid(),orgId,model);
									}
									//更新中心pacs已有病人信息
									updatePatient(patientBean[hh].getPatientGlobalid(),model);
								}
							}
						}else{
							//若pix和中心patient都有信息，则只更新病人信息
							if(pixBean!=null&&pixBean.length>0){
								for(int hh=0;hh<pixBean.length;hh++){
									//更新中心pacs已有病人信息
									updatePatient(pixBean[hh].getPatientGlobalid(),model);
								}
							}
						}
					}
				}
				if("STUDYINFO".equals(c.getInterfaceName())){
					JsonConfig jsonConfig = new JsonConfig();
					String[] formats={"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd"};  
					JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher(formats)); 
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("STUDYINFO"),jsonConfig);
					List<AisStudyInfoBean> list = (List<AisStudyInfoBean>) JSONArray.toCollection(jsonArray,AisStudyInfoBean.class);
					for (AisStudyInfoBean bean : list){
						long studyinfoId= Long.parseLong(orgId+bean.getStudyinfoId());
						AisStudyinfoRealBean oldBean = getStudyinfoRealBean(studyinfoId,bean.getOrgId());
						if(oldBean==null){
							AisStudyinfoRealBean realBean =new AisStudyinfoRealBean();
							realBean.setStudyinfoRealId(commonSV.getSequence("SEQSTUDYTEMPID"));
							realBean.setOldStudyinfoId(studyinfoId);
							realBean.setOrgId(String.valueOf(bean.getOrgId()));
							bean.setStudyinfoId(Long.parseLong(orgId+commonSV.getSequence("SEQSTUDYINFOID")));
							realBean.setStudyinfoId(bean.getStudyinfoId());
							//转义
							translateStudyinfo(bean);
							//插入关系表及的登记记录
							commonSV.save(bean);
							commonSV.save(realBean);
						}else{
							//修改登记信息
							AisStudyInfoBean oldstudyInfoBean = new AisStudyInfoBean();
							oldstudyInfoBean.setStudyinfoId(oldBean.getStudyinfoId());
							oldstudyInfoBean.setStsToOld();
							bean.setStudyinfoId(oldBean.getStudyinfoId());
							DataContainerFactory.copyNoClearData(bean, oldstudyInfoBean);		
							translateStudyinfo(oldstudyInfoBean);
							AisStudyInfoEngine.save(oldstudyInfoBean);
						}
					}
				}
				if("STUDYITEMINFO".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("STUDYITEMINFO"));
					List<AisStudyItemInfoBean> list = (List<AisStudyItemInfoBean>) JSONArray.toCollection(jsonArray,AisStudyItemInfoBean.class);
					for (AisStudyItemInfoBean bean : list){
						AisStudyItemInfoBean studyitemBeen = new AisStudyItemInfoBean();
						AisStudyinfoRealBean  oldBean = getStudyinfoRealBean(Long.parseLong(orgId+bean.getStudyinfoId()),orgId);
						//studyinfoId转义
						if(oldBean!=null){
							deleteStudyIteminfo(oldBean.getStudyinfoId(),orgId);
							studyitemBeen.setStudyinfoId(oldBean.getStudyinfoId());
						}
						//检查项目code转义
						AiscItemmastRealBean studyitem = getStudyItemRealBean(Long.parseLong(bean.getStudyitemCode()),orgId);
						if(studyitem!=null){
							studyitemBeen.setStudyitemCode(String.valueOf(studyitem.getItemmastId()));
						}
						studyitemBeen.setStudyitemId(commonSV.getSequence("SEQSTUDYITEMID"));
						studyitemBeen.setStudyitemHisid(bean.getStudyitemHisid());
						studyitemBeen.setStudyitemCode(bean.getStudyitemCode());
						studyitemBeen.setStudyitemDesc(bean.getStudyitemDesc());
						studyitemBeen.setStudyitemEndesc(bean.getStudyitemEndesc());
						studyitemBeen.setStudyitemBodyinfo(bean.getStudyitemBodyinfo());
						studyitemBeen.setStudyitemPrice(bean.getStudyitemPrice());
						studyitemBeen.setStudyitemNumber(Integer.parseInt(String.valueOf(bean.getStudyitemNumber())));
						studyitemBeen.setStudyitemStatus(bean.getStudyitemStatus());
						studyitemBeen.setStudyitemItemno(bean.getStudyitemItemno());
						if(bean.getStudyitemBodypart()!=null&&!"".equals(bean.getStudyitemBodypart())){
							String arr[] = bean.getStudyitemBodypart().split(",");
							String parts = "";
							boolean task = false;
							for(int i=0;i<arr.length;i++){
								AiscBodypartRealBean partBean = getBodypartRealBean(Long.parseLong(arr[i]),orgId);
								if(partBean!=null){
									parts+=partBean.getBodypartCode()+",";
								}else{
									parts = bean.getStudyitemBodypart();
									task = true;
								}
							}
							if(task){
								studyitemBeen.setStudyitemBodypart(parts);
							}else{
								studyitemBeen.setStudyitemBodypart(parts.substring(0,parts.length()-1));
							}
						}
						commonSV.save(studyitemBeen);
					}
				}
				
				if("STUDYREPORT".equals(c.getInterfaceName())){
					JSONArray jsonArray = JSONArray.fromObject(jsonbody.getString("STUDYREPORT"));
					List<AisStudyReportBean> list = (List<AisStudyReportBean>) JSONArray.toCollection(jsonArray,AisStudyReportBean.class);
					for (AisStudyReportBean bean : list){
						AisStudyReportBean studyreport = new AisStudyReportBean();
						//studyinfoId转义
						AisStudyinfoRealBean  realbean = getStudyinfoRealBean(Long.parseLong(orgId+bean.getStudyinfoId()),orgId);
						if(realbean!=null){
							deleteStudyReport(realbean.getStudyinfoId(),orgId);
							studyreport.setStudyinfoId(realbean.getStudyinfoId());
						}else{
							studyreport.setStudyinfoId(Long.parseLong(orgId+bean.getStudyinfoId()));
						}
						studyreport.setReportId(commonSV.getSequence("SEQREPORTID"));
						studyreport.setReportDatetime(bean.getReportDatetime());
						if (isNotBlank(bean.getReportDoctorid())) {
							AiscCareprovRealBean reportdoc = getCareBean(Long.parseLong(bean.getReportDoctorid()),orgId);
							if(reportdoc!=null){
								studyreport.setReportDoctorid(String.valueOf(reportdoc.getCareprovId()));
							}else{
								studyreport.setReportDoctorid(bean.getReportDoctorid());
							}
						}
						studyreport.setReportVerifydatetime(bean.getReportVerifydatetime());
						if (isNotBlank(bean.getReportVerifydoctorid())) {
							AiscCareprovRealBean verifydoc = getCareBean(Long.parseLong(bean.getReportVerifydoctorid()),orgId);
							if(verifydoc!=null){
								studyreport.setReportVerifydoctorid(String.valueOf(verifydoc.getCareprovId()));
							}else{
								studyreport.setReportVerifydoctorid(bean.getReportVerifydoctorid());
							}
						}
						studyreport.setReportFinaldatetime(bean.getReportFinaldatetime());
						if (isNotBlank(bean.getReportFinaldoctorid())) {
							AiscCareprovRealBean finaldoc = getCareBean(Long.parseLong(bean.getReportFinaldoctorid()),orgId);
							if(finaldoc!=null){
								studyreport.setReportFinaldoctorid(String.valueOf(finaldoc.getCareprovId()));
							}else{
								studyreport.setReportFinaldoctorid(bean.getReportFinaldoctorid());
							}
						}
						if (isNotBlank(bean.getReportRecord())) {
							AiscCareprovRealBean writedoc = getCareBean(Long.parseLong(bean.getReportRecord()),orgId);
							if(writedoc!=null){
								studyreport.setReportRecord(String.valueOf(writedoc.getCareprovId()));
							}else{
								studyreport.setReportRecord(bean.getReportRecord());
							}
						}
						studyreport.setReportIscompleted(Integer.parseInt(String.valueOf(bean.getReportIscompleted())));
						studyreport.setReportIspositive(Integer.parseInt(String.valueOf(bean.getReportIspositive())));
						studyreport.setReportIsprinted(Integer.parseInt(String.valueOf(bean.getReportIsprinted())));
						studyreport.setReportIssent(Integer.parseInt(String.valueOf(bean.getReportIssent())));
						studyreport.setReportPrintcareprovid(bean.getReportPrintcareprovid());
						studyreport.setReportPrintdatetime(bean.getReportPrintdatetime());
						studyreport.setReportUncompletedreason(bean.getReportUncompletedreason());
						studyreport.setReportExam(bean.getReportExam());
						studyreport.setReportExammethod(bean.getReportExammethod());
						studyreport.setReportResult(bean.getReportResult());
						studyreport.setReportIcd10(bean.getReportIcd10());
						studyreport.setReportAcrcode(bean.getReportAcrcode());
						studyreport.setReportRemark(bean.getReportRemark());
						studyreport.setReportLock(Integer.parseInt(String.valueOf(bean.getReportLock())));
						commonSV.save(studyreport);
					}
				}
			}
		
		return result;
	}
	
	public void deleteStudyReport(long studyinfoId,String orgId){
		try {
			String delSql2 = "delete from ais_studyreport where report_id=(select "
					+ " report_id from ais_studyreport a,ais_studyinfo b where a.studyinfo_id=b.studyinfo_id "
					+ " and b.org_id=:ORG_ID and a.studyinfo_id=:studyinfoId and b.studystatus_code = 'VERIFY')";
			Map delMap2 = new HashMap();
			delMap2.put("ORG_ID",orgId);
			delMap2.put("studyinfoId",studyinfoId);
			commonSV.execute(delSql2,delMap2);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void deleteStudyIteminfo(long studyinfoId,String orgId){
		try {
			String delSql2 = "delete from ais_studyiteminfo where studyitem_id=(select "
					+ " studyitem_id from ais_studyiteminfo a,ais_studyinfo b where a.studyinfo_id=b.studyinfo_id "
					+ " and b.org_id=:ORG_ID and a.studyinfo_id=:studyinfoId and b.studystatus_code='VERIFY') ";
			Map delMap2 = new HashMap();
			delMap2.put("ORG_ID",orgId);
			delMap2.put("studyinfoId",studyinfoId);
			commonSV.execute(delSql2,delMap2);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	private AisStudyInfoBean translateStudyinfo(AisStudyInfoBean bean) throws Exception{
		AiscLocRealBean  realbean = getLocRealBean(bean.getLocId(),bean.getOrgId());
		if(realbean!=null){
			bean.setLocId(realbean.getLocId());
		}
		//申请科室转义
		if (bean.getStudyApplocid()!=-1&&isNotBlank(String.valueOf(bean.getStudyApplocid()))) {
			AiscLocRealBean  applocbean = getLocRealBean(bean.getStudyApplocid(),bean.getOrgId());
			if(applocbean!=null){
				bean.setStudyApplocid(applocbean.getLocId());
			}
		}
		//房间转义
		if (bean.getRoomId()!=-1&&isNotBlank(String.valueOf(bean.getRoomId()))) {
			AiscRoomRealBean roomreal = getRoomReal(bean.getRoomId(),bean.getOrgId());
			if(roomreal!=null){
				bean.setRoomId(roomreal.getRoomId());
			}
		}
		//申请医生转义
		if (isNotBlank(bean.getStudyAppdoc())) {
			AiscCareprovRealBean carebean = getCareBean(Long.parseLong(bean.getStudyAppdoc()),bean.getOrgId());
			if(carebean!=null){
				bean.setStudyAppdoc(String.valueOf(carebean.getCareprovId()));
			}
		}
		//设备转义
		if (bean.getEquipmentId()!=-1&&isNotBlank(String.valueOf(bean.getEquipmentId()))) {
			AiscEquipmentRealBean equipreal = getEquipmentBean(bean.getEquipmentId(),bean.getOrgId());
			if(equipreal!=null){
				bean.setEquipmentId(equipreal.getEquipmentId());
			}
		}
		//操作员转义
		if (isNotBlank(bean.getStudyOperationid())) {
//			AiscCareprovRealBean operbean = getCareBean(Long.parseLong(bean.getStudyOperationid()),bean.getOrgId());
//			bean.setStudyOperationid(String.valueOf(operbean.get));
		}
		//检查医生转义
		if (isNotBlank(bean.getStudyDoctorid())) {
			AiscCareprovRealBean docbean = getCareBean(Long.parseLong(bean.getStudyDoctorid()),bean.getOrgId());
			if(docbean!=null){
				bean.setStudyDoctorid(String.valueOf(docbean.getCareprovId()));
			}
		}
		//辅助技师转义
		if (isNotBlank(bean.getAidDoctorid())) {
			AiscCareprovRealBean jsbean = getCareBean(Long.parseLong(bean.getAidDoctorid()),bean.getOrgId());
			if(jsbean!=null){
				bean.setStudyDoctorid(String.valueOf(jsbean.getCareprovId()));
			}
		}
		//病人ID转义
		AisPatientRealBean paBean = getRealGlobalId(bean.getPatientGlobalid(),bean.getOrgId());
		if(paBean!=null){
			bean.setPatientGlobalid(paBean.getGlobalId());
		}
		return bean;
	}
	
	private Boolean translateBatch(String orgId){
		Boolean flag =false ;
		try {
			//---------科室关系表-----------------
			AiscLocRealBean[] realBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscLocRealBean.class);
			for (AiscLocRealBean realBean:realBeen){
				//设备信息中科室转义
				AiscEquipmentBean[] equipmentBeens = commonSV.getBeans(" LOC_ID = "+realBean.getOldLocId()+" and ORG_ID = "+orgId,null,AiscEquipmentBean.class);
				for (AiscEquipmentBean bean:equipmentBeens) {
					bean.setStsToOld();
					bean.setLocId(realBean.getLocId());
					commonSV.save(bean);
				}
				//房间信息中科室转义
				AiscRoomBean[] roomBeen = commonSV.getBeans(" LOC_ID = "+realBean.getOldLocId()+" and ORG_ID = "+orgId,null,AiscRoomBean.class);
				for (AiscRoomBean bean:roomBeen) {
					bean.setStsToOld();
					bean.setLocId(realBean.getLocId());
					commonSV.save(bean);
				}
				//检查大类与科室关联中科室ID转义
				AiscOrdCat2LocBean[] ordCat2LocBeen = commonSV.getBeans(" LOC_ID = "+realBean.getOldLocId()+" and ORG_ID = "+orgId,null,AiscOrdCat2LocBean.class);
				for (AiscOrdCat2LocBean bean:ordCat2LocBeen) {
					bean.setStsToOld();
					bean.setLocId(realBean.getLocId());
					commonSV.save(bean);
				}
				//医护人员维护中科室ID转义
				AiscCareProvBean[] careProvBeen = commonSV.getBeans(" LOC_ID = "+realBean.getOldLocId()+" and ORG_ID = "+orgId,null,AiscCareProvBean.class);
				for (AiscCareProvBean bean:careProvBeen) {
					bean.setStsToOld();
					bean.setLocId(realBean.getLocId());
					commonSV.save(bean);
				}
				//操作员与登录科室登录中科室ID转义
				AiscLoginLocBean[] loginLocBeen = commonSV.getBeans(" LOC_ID = "+realBean.getOldLocId()+" and ORG_ID = "+orgId,null,AiscLoginLocBean.class);
				for (AiscLoginLocBean bean:loginLocBeen) {
					bean.setStsToOld();
					bean.setLocId(realBean.getLocId());
					commonSV.save(bean);
				}

			}
			//---------服务器关系表-----------------
		   AiscSeverinfoRealBean[] severinfoRealBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscSeverinfoRealBean.class);
           for (AiscSeverinfoRealBean  realBean :severinfoRealBeen){
			   //科室表中服务器ID转义
			   AiscLocBean[] loginLocBeen = commonSV.getBeans(" SERVER_ID = "+realBean.getOldServerId(),null,AiscLocBean.class);
			   for (AiscLocBean bean:loginLocBeen) {
				   bean.setStsToOld();
				   bean.setServerId(realBean.getServerId());
				   commonSV.save(bean);
			   }
		   }


			//---------房间关系表-----------------
			AiscRoomRealBean[] roomRealBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscRoomRealBean.class);
			for (AiscRoomRealBean  realBean :roomRealBeen){
				//设备信息中房间转义
				AiscEquipmentBean[] equipmentBeen = commonSV.getBeans(" ROOM_ID = "+realBean.getOldRoomId()+" and ORG_ID = "+orgId,null,AiscEquipmentBean.class);
				for (AiscEquipmentBean bean:equipmentBeen) {
					bean.setStsToOld();
					bean.setRoomId(realBean.getRoomId());
					commonSV.save(bean);
				}
			}

			//---------检查项目关系表-----------------
			AiscItemmastRealBean[] itemMastBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscItemmastRealBean.class);
			for (AiscItemmastRealBean  realBean :itemMastBeen){
				//检查项目与部位关系中itemmast_id转义
				AiscBodyPart2ItemBean[] bodypart2itemBeen = commonSV.getBeans(" ITEMMAST_ID = "+realBean.getOldItemmastId()+" and ORG_ID = "+orgId,null,AiscBodyPart2ItemBean.class);
				for (AiscBodyPart2ItemBean bean:bodypart2itemBeen) {
					bean.setStsToOld();
					bean.setItemmastId(realBean.getItemmastId());
					commonSV.save(bean);
				}
			}

			//---------检查部位关系表-----------------
			AiscBodypartRealBean[] bodyPartBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscBodypartRealBean.class);
			for (AiscBodypartRealBean  realBean :bodyPartBeen){
				//检查项目与部位关系中bodypart_code转义
				AiscBodyPart2ItemBean[] loginLocBeen = commonSV.getBeans(" BODYPART_CODE = "+realBean.getOldBodypartCode()+" and ORG_ID = "+orgId,null,AiscBodyPart2ItemBean.class);
				for (AiscBodyPart2ItemBean bean:loginLocBeen) {
					bean.setStsToOld();
					bean.setBodypartCode(String.valueOf(realBean.getBodypartCode()));
					commonSV.save(bean);
				}
			}

			//---------检查大类关系表-----------------
			AiscOrdcategoryRealBean[] ordcategoryRealBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscOrdcategoryRealBean.class);
			for (AiscOrdcategoryRealBean  realBean :ordcategoryRealBeen){
				//检查大类与科室关联中检查大类转义
				AiscOrdCat2LocBean[] ordCat2LocBeen = commonSV.getBeans(" ORDCAT_ID = "+realBean.getOldOrdcategoryId(),null,AiscOrdCat2LocBean.class);
				for (AiscOrdCat2LocBean bean:ordCat2LocBeen) {
					bean.setStsToOld();
					bean.setOrdcatId(realBean.getOrdcategoryId());
					commonSV.save(bean);
				}
				//检查子类中检查大类转义
				AiscOrdSubCategoryBean[] ordSubCategoryBeen = commonSV.getBeans(" ORDCATEGORY_ID = "+realBean.getOldOrdcategoryId(),null,AiscOrdSubCategoryBean.class);
				for (AiscOrdSubCategoryBean bean:ordSubCategoryBeen) {
					bean.setStsToOld();
					bean.setOrdcategoryId(realBean.getOrdcategoryId());
					commonSV.save(bean);
				}
				//检查项目中检查大类转义
				AiscItemMastBean[] itemmstBeen = commonSV.getBeans(" ORDCATEGORY_ID = "+realBean.getOldOrdcategoryId()+" and ORG_ID = "+orgId,null,AiscItemMastBean.class);
				for (AiscItemMastBean bean:itemmstBeen) {
					bean.setStsToOld();
					bean.setOrdcategoryId(realBean.getOrdcategoryId());
					commonSV.save(bean);
				}
			}
			
			//---------检查子类关系表-----------------
			AiscOrdsubcategoryRealBean[] ordsubcateRealBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscOrdsubcategoryRealBean.class);
			for (AiscOrdsubcategoryRealBean realBean :ordsubcateRealBeen){
				//设备中检查子类转义
				AiscEquipmentBean[] equipBeen = commonSV.getBeans(" ordsubcategory_id = "+realBean.getOldSubcateId(),null,AiscEquipmentBean.class);
				for(AiscEquipmentBean bean:equipBeen){
					bean.setStsToOld();
					bean.setOrdsubcategoryId(realBean.getSubcateId());
					commonSV.save(bean);
				}
			}
			//---------医护人员关系表-----------------
			AiscCareprovRealBean[] careprovRealBeen = commonSV.getBeans(" ORG_ID = "+orgId,null,AiscCareprovRealBean.class);
			for (AiscCareprovRealBean  realBean :careprovRealBeen){
				//操作员与医护人员关联中医护人员ID转义
				AiscUser2CareProvBean[] user2CareProvBeen = commonSV.getBeans(" CAREPROV_ID = "+realBean.getOldCareprovId(),null,AiscUser2CareProvBean.class);
				for (AiscUser2CareProvBean bean:user2CareProvBeen) {
					bean.setStsToOld();
					bean.setCareprovId(realBean.getCareprovId());
					commonSV.save(bean);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return  flag ;
		}

		return flag ;
	}
	
	/**
	 * 写入接口日志表
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public void writeServiceLog(AisServiceLogBean bean) throws Exception{
		long id =ServiceUtil.getSequence("SEQ_AIS_SERVICE_LOG");
		bean.setServiceId(id);
		AisServiceLogEngine.save(bean);
	}
	
	public String upPatientInfo(AisPatientInfoData patient,String orgId) throws Exception{
		AisPatientInfoBean[] patientBean = null;
		if(isNotBlank(patient.getPatientIdnumber())){
			patientBean = getAisPatientBean(patient.getPatientIdnumber());
		}
		//查询ais_pixinfo表中是否有记录
		AisPixInfoBean[] pixBean = getAisPixInfoBean(patient.getPatientId(),orgId);

		//该情况只有人为修改数据的时候才会出现
		if(pixBean!=null&&patientBean==null){
			//pixBean 更新
			return String.valueOf(pixBean[0].getPatientGlobalid());
			//patient 插入
		}else if(pixBean==null&&patientBean==null){
			//patient 插入&pixBean 插入
			return insertPatientBean(orgId,patient);
		}else if(pixBean==null&&patientBean!=null){
			//查询pacs已有病人信息
			if(patientBean!=null&&patientBean.length>0){
				//插入pixBean
				if(isFlag(patientBean[0].getPatientGlobalid(),orgId)){
					insertPixBean(patientBean[0].getPatientGlobalid(),orgId,patient);
				}
				//更新中心pacs已有病人信息
				updatePatient(patientBean[0].getPatientGlobalid(),patient);
				return String.valueOf(patientBean[0].getPatientGlobalid());
			}
		}else{
			//若pix和中心patient都有信息，则只更新病人信息
			if(pixBean!=null&&pixBean.length>0){
				//更新中心pacs已有病人信息
				updatePatient(pixBean[0].getPatientGlobalid(),patient);
				return String.valueOf(pixBean[0].getPatientGlobalid());
			}
		}
		return "";
	}
	
	public String upStudyInfo(AisStudyInfoBean bean,String orgId) throws Exception{
		long studyinfoId= Long.parseLong(orgId+bean.getStudyinfoId());
		AisStudyinfoRealBean oldBean = getStudyinfoRealBean(studyinfoId,bean.getOrgId());
		if(oldBean==null){
			long id = Long.parseLong(orgId+commonSV.getSequence("SEQSTUDYINFOID"));
			AisStudyinfoRealBean realBean =new AisStudyinfoRealBean();
			realBean.setStudyinfoRealId(commonSV.getSequence("SEQSTUDYTEMPID"));
			realBean.setOldStudyinfoId(studyinfoId);
			realBean.setOrgId(String.valueOf(bean.getOrgId()));
			bean.setStudyinfoId(id);
			realBean.setStudyinfoId(id);
			//转义
			translateStudyinfo(bean);
			//插入关系表及的登记记录
			commonSV.save(bean);
			commonSV.save(realBean);
			return String.valueOf(id);
		}else{
			//修改登记信息
			AisStudyInfoBean oldstudyInfoBean = new AisStudyInfoBean();
			oldstudyInfoBean.setStudyinfoId(oldBean.getStudyinfoId());
			oldstudyInfoBean.setStsToOld();
			bean.setStudyinfoId(oldBean.getStudyinfoId());
			DataContainerFactory.copyNoClearData(bean, oldstudyInfoBean);		
			translateStudyinfo(oldstudyInfoBean);
			AisStudyInfoEngine.save(oldstudyInfoBean);
			return String.valueOf(oldstudyInfoBean.getStudyinfoId());
		}
	}
	
	public void upStudyItemInfo(AisStudyItemInfoBean bean,String orgId) throws Exception{
		AisStudyItemInfoBean studyitemBeen = new AisStudyItemInfoBean();
		AisStudyinfoRealBean  oldBean = getStudyinfoRealBean(Long.parseLong(orgId+bean.getStudyinfoId()),orgId);
		//studyinfoId转义
		if(oldBean!=null){
			deleteStudyIteminfo(oldBean.getStudyinfoId(),orgId);
			studyitemBeen.setStudyinfoId(oldBean.getStudyinfoId());
		}
		//检查项目code转义
		AiscItemmastRealBean studyitem = getStudyItemRealBean(Long.parseLong(bean.getStudyitemCode()),orgId);
		if(studyitem!=null){
			studyitemBeen.setStudyitemCode(String.valueOf(studyitem.getItemmastId()));
		}
		studyitemBeen.setStudyitemId(commonSV.getSequence("SEQSTUDYITEMID"));
		studyitemBeen.setStudyitemHisid(bean.getStudyitemHisid());
		studyitemBeen.setStudyitemCode(bean.getStudyitemCode());
		studyitemBeen.setStudyitemDesc(bean.getStudyitemDesc());
		studyitemBeen.setStudyitemEndesc(bean.getStudyitemEndesc());
		studyitemBeen.setStudyitemBodyinfo(bean.getStudyitemBodyinfo());
		studyitemBeen.setStudyitemPrice(bean.getStudyitemPrice());
		studyitemBeen.setStudyitemNumber(Integer.parseInt(String.valueOf(bean.getStudyitemNumber())));
		studyitemBeen.setStudyitemStatus(bean.getStudyitemStatus());
		studyitemBeen.setStudyitemItemno(bean.getStudyitemItemno());
		if(bean.getStudyitemBodypart()!=null&&!"".equals(bean.getStudyitemBodypart())){
			String arr[] = bean.getStudyitemBodypart().split(",");
			String parts = "";
			boolean task = false;
			for(int i=0;i<arr.length;i++){
				AiscBodypartRealBean partBean = getBodypartRealBean(Long.parseLong(arr[i]),orgId);
				if(partBean!=null){
					parts+=partBean.getBodypartCode()+",";
				}else{
					parts = bean.getStudyitemBodypart();
					task = true;
				}
			}
			if(task){
				studyitemBeen.setStudyitemBodypart(parts);
			}else{
				studyitemBeen.setStudyitemBodypart(parts.substring(0,parts.length()-1));
			}
		}
		commonSV.save(studyitemBeen);
	}
	
	public AiscLocBean getHzLocInfo(AiscLocBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and loc_code='"+bean.getLocCode()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"' ");
        condition.append(" and loc_type = 'C' ");
        AiscLocBean[] oldBean = AiscLocEngine.getBeans(condition.toString(), null);
        if(oldBean!=null&&oldBean.length>0){
        	return oldBean[0];
        }else{
        	return null;
        }
	}
	
	public String upHzloc(String studyinfoId,String orgId,String conlocId,String conorgId,String locId,AiscLocBean aiscloc) throws Exception{
		AiscLocBean oldBean = getHzLocInfo(aiscloc);
		long newlocId = commonSV.getSequence("SEQLOCID");
		if(oldBean==null){
			//插入新会诊科室及关系对应
			AiscLocRealBean realBean = new AiscLocRealBean();
			realBean.setLocRealId(commonSV.getSequence("SEQ_AISC_LOC_REAL"));
			realBean.setOrgId(aiscloc.getOrgId());
			realBean.setOldLocId(aiscloc.getLocId());
			aiscloc.setLocId(newlocId);
			long serverId = getServerId(aiscloc.getServerId());
			aiscloc.setServerId(serverId);
			realBean.setLocId(aiscloc.getLocId());
			commonSV.save(aiscloc);
			commonSV.save(realBean);
		}else{
			AiscLocRealBean qryBean = commonSV.getBean(" OLD_LOC_ID = "+conlocId+" and org_id='"+oldBean.getOrgId()+"'",null,AiscLocRealBean.class);
			if (qryBean==null){
				AiscLocRealBean realBean = new AiscLocRealBean();
				realBean.setLocRealId(commonSV.getSequence("SEQ_AISC_LOC_REAL"));
				realBean.setOrgId(oldBean.getOrgId());
				realBean.setOldLocId(Long.parseLong(conlocId));
				realBean.setLocId(oldBean.getLocId());
				commonSV.save(realBean);
			}
		}
		//查询中心申请科室ID
		AiscLocRealBean sqBean = commonSV.getBean(" OLD_LOC_ID = "+locId+" and org_id='"+orgId+"'",null,AiscLocRealBean.class);
		//查询中心会诊科室ID
		AiscLocRealBean hzBean = commonSV.getBean(" OLD_LOC_ID = "+conlocId+" and org_id='"+conorgId+"'",null,AiscLocRealBean.class);
		
		AiscConorganizationBean conorgBean = commonSV.getBean(" conloc_id='"+hzBean.getLocId()+"' and org_id='"+sqBean.getOrgId()+"' and loc_id="+sqBean.getLocId()+" and conorg_id='"+hzBean.getOrgId()+"'",null,AiscConorganizationBean.class);
		if(conorgBean==null){
			//插入登录科室与会诊科室关联
			AiscConorganizationBean newconorg = new AiscConorganizationBean();
			newconorg.setConorganizatId(ServiceUtil.getSequence("SEQ_Conorganization"));
			newconorg.setConlocId(String.valueOf(hzBean.getLocId()));
			newconorg.setConorgId(hzBean.getOrgId());
			newconorg.setOrgId(sqBean.getOrgId());
			newconorg.setLocId(sqBean.getLocId());
			commonSV.save(newconorg);
			updateConLoc(studyinfoId,hzBean.getLocId());
		}else{
			updateConLoc(studyinfoId,Long.parseLong(conorgBean.getConlocId()));
		}
		return "0";
	}
	
	public void updateConLoc(String studyinfoId,long locId) throws Exception{
		AisStudyInfoBean bean = AisStudyInfoEngine.getBean(Long.parseLong(studyinfoId));
		bean.setStsToOld();
	    bean.setStudyConsultloc(locId);
	    bean.setStudystatusCode("APPConsult");
	    AisStudyInfoEngine.save(bean);
	}
	
	
	public String saveFilePDFUrl(AisFilesInfoModel filemodel) throws Exception{
		AisFilesInfoBean fileBean = new AisFilesInfoBean();
        long fileId = ServiceUtil.getSequence("SEQFILEID");
        fileBean.setFileId(fileId);
        fileBean.setStudyinfoId(filemodel.getStudyinfoId());
        fileBean.setFileType(filemodel.getFileType());
        fileBean.setFilePath(filemodel.getFilePath());
        fileBean.setMiId(-1);
        AisFilesInfoEngine.save(fileBean);
		return "0";
	}
	
	public boolean isFlag(long id,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" ORG_ID = '" + orgId+"' and patient_globalid="+id ); 
	    AisPixInfoBean[] pixBean = AisPixInfoEngine.getBeans(condition.toString(), null); 
	    if(pixBean!=null&&pixBean.length > 0){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	public AisStudyInfoBean getStudyInfoByAccnumber(String studyAccnumber) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyAccnumber)) {
            condition.append(" AND study_accnumber = '" + studyAccnumber + "'");
        }
        AisStudyInfoBean[] studyBean = AisStudyInfoEngine.getBeans(condition.toString(), null);
        if (studyBean.length > 0) {
            return studyBean[0];
        } else {
            return null;
        }
    }
	
	public Map reportBack(String hzOrgId,String studyAccnumber,String hzReportExam,String hzReportResult,String ipport,String reportDoc,String verifyDoc,String studyTime) throws Exception{
		Statement stmt = null;
        ResultSet rs = null;
        Map result = new HashMap();
        String code = "0";
        String message = "回传报告成功";
		Timestamp dateTime = new Timestamp(new Date().getTime());
		AisStudyInfoBean sbean = getStudyInfoByAccnumber(studyAccnumber);
		sbean.setStudystatusCode("Consulted"); 
		sbean.setStudyHavereport(1);
		AisStudyInfoEngine.save(sbean);
		
		AisStudyReportBean reportBean = getOldReport(sbean.getStudyinfoId());
		if(reportBean ==null){
			reportBean = new AisStudyReportBean();
			long newReportId = ServiceUtil.getSequence("SEQREPORTID");
	        reportBean.setReportId(newReportId);
	        reportBean.setReportDatetime(dateTime);
	        reportBean.setReportVerifydatetime(dateTime);
	        reportBean.setStudyinfoId(sbean.getStudyinfoId());
//	        reportBean.setReportDoctorid(user.getCareProvId());
//	        reportBean.setReportRecord(user.getCareProvId());
	        reportBean.setReportExam(hzReportExam);
	        reportBean.setReportResult(hzReportExam);
	        reportBean.setReportIsprinted(0);//未打印
	        reportBean.setReportIscompleted(1);
	        AisStudyReportEngine.save(reportBean);
		}else{
			reportBean.setStsToOld();
			reportBean.setReportExam(hzReportExam);
			reportBean.setReportResult(hzReportExam);
	        AisStudyReportEngine.save(reportBean);
		}
      //3.生成html文件上传服务器
        //3.1首先取到设备类型ID
        String template = "";
        //按设备大类查模板现在不用
        AiscEquipmentBean eBean = AiscEquipmentEngine.getBean(sbean.getEquipmentId());
        //3.2查html模板         //模板应该取当前登录所用的组织机构及科室标识查，还是直接用检查单中的组织机构和科室查
        try{
	        stmt = ServiceManager.getSession().getConnection().createStatement();
	        String sql = "  SELECT A.REPORT_FORMAT, B.ORG_ID, B.LOC_ID, B.MODALITY_ID "
	                + "  FROM AISC_REPORTFORMAT A, AISC_REPORTFORMAT2LOC B "
	                + "  WHERE A.FORMAT_ID = B.FORMAT_ID ";
                	sql += "  AND ORG_ID = " + sbean.getOrgId() + " ";
                	sql += "  AND LOC_ID = " + sbean.getLocId()+""
                			+ " AND B.MODEL_TYPE = 3 ";
	        rs = stmt.executeQuery(sql);
	
	        if (rs.next()) {
	            Clob reportFormat = rs.getClob("REPORT_FORMAT");
	            if (reportFormat != null) {
	                template = reportFormat.getSubString(1, (int) reportFormat.length());
	            }
	        }
	        if ("".equals(template)) {
	        	code = "-1";
	        	message = "回传报告失败：打印模板文件不存在，请确认！";
	        	throw new Exception("上传报告失败：打印模板文件不存在，请确认！");
	        }
        }catch (Exception e) {
			// TODO: handle exception
		}
        finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }

        //3.3组装html文件
        //3.3.1获取填充数据
        Map map = getTemplateFilling(String.valueOf(reportBean.getStudyinfoId()));
        //剔除strike中的记录 留痕处理 -- 正则表达式
        String reportExam = String.valueOf(map.get("REPORT_EXAM"));
        String reportResult = String.valueOf(map.get("REPORT_RESULT"));
        Pattern p = Pattern.compile("<strike>.*</strike>");
        Matcher m1 = p.matcher(reportExam);
        Matcher m2 = p.matcher(reportResult);
        //去除删除标签
        String newReportExam = m1.replaceAll("");
        String newReportResult = m2.replaceAll("");

        //图片内容填充
//        String patientImg = "";
//        if (!"".equals(imgAddrArray)) {
//            String[] imgArray = imgAddrArray.split("\\|");
//            String html = "<tr>";
//            for (int i = 0; i < imgArray.length; i++) {
//                html += "<td align='center' height='1' valign='bottom' width='33%'><img border='0' height='172' src='" + imgArray[i] + "' width='230' /><p align='center'>&nbsp;</p></td>";
//                if (i != 0 && (i + 1) % 2 == 0) {
//                    html += "</tr><tr>";
//                }
//            }
//            html += "</tr>";
//            patientImg = html;
//        } else {
//            patientImg = "无";
//        }

        String id = "";
        String cardId = "";
        String cardNo = "";
        if ("INP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "住院号";
        } else if ("HP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "体检号";
            cardId = "身份证号：";
            cardNo = String.valueOf(map.get("PATIENT_IDNUMBER"));
        } else if ("OP".equals(String.valueOf(map.get("PATIENTTYPE_CODE")))) {
            id = "门诊号";
        } else {
            id = "住院号";
        }
        //内容填充
        String studyReprotMsg = template.replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#ORG_NAME#", String.valueOf(map.get("ORG_NAME")))
                .replaceAll("#TEMP#", "")
                .replaceAll("#姓名#", String.valueOf(map.get("PATIENT_NAME")))
                .replaceAll("#性别#", String.valueOf(map.get("PATIENT_SEX")))
                .replaceAll("#年龄#", String.valueOf(map.get("PATIENT_AGE")))
                .replaceAll("#检查号#", String.valueOf(map.get("STUDY_ACCNUMBER")))
                .replaceAll("#仪器型号#", String.valueOf(map.get("EQUIPMENT_ID")))
                .replaceAll("#来源#", String.valueOf(map.get("PATIENTTYPE")))
                .replaceAll("#PATIENT_TYPEID#", id)
                .replaceAll("#AUDITORFINAL_DOC#", "审核医师")
                .replaceAll("#住院号#", String.valueOf(map.get("PATIENT_INPATIENTID")))
                .replaceAll("#检查项目#", String.valueOf(map.get("STUDY_ITEMDESC")))
                .replaceAll("#检查部位#", String.valueOf(map.get("STUDYITEM_BODYINFO")))
//                .replaceAll("#图像#", String.valueOf(patientImg))
                .replaceAll("#审核日期#", String.valueOf(map.get("REPORT_VERIFYDATETIME")))
                .replaceAll("#终审医生#", String.valueOf(map.get("REPORT_FINALDOCTORNAME")))
                .replaceAll("#终审核期#", String.valueOf(map.get("REPORT_FINALDATETIME")))
                .replaceAll("#报告医生#", URLDecoder.decode(reportDoc, "UTF-8"))
                .replaceAll("#报告时间#", String.valueOf(map.get("REPORT_DATETIME")))
                .replaceAll("#报告日期#", String.valueOf(map.get("REPORT_DATE")))
                .replaceAll("#患者ID#", String.valueOf(map.get("PATIENT_ID")))
                .replaceAll("#申请科室#", String.valueOf(map.get("STUDY_APPLOCNAME")))
                .replaceAll("#检查日期#", studyTime)
                .replaceAll("#门诊号#", String.valueOf(map.get("PATIENT_OUTPATIENTID")))
                .replaceAll("#病区#", String.valueOf(map.get("STUDY_WARDNAME")))
                .replaceAll("#床号#", String.valueOf(map.get("STUDY_BEDNO")))
                .replaceAll("#房间#", String.valueOf(map.get("ROOM_ID")))
                .replaceAll("#CARD_ID#", cardId)
                .replaceAll("#身份证号#", cardNo)
                .replaceAll("#检查所见#", newReportExam)
                .replaceAll("#诊断意见#", newReportResult);
        	studyReprotMsg = studyReprotMsg.replaceAll("#审核医生#", URLDecoder.decode(verifyDoc, "UTF-8"));
        //3.3.2生成文件上传服务器-保存文件表路径
        String fileAddr = "";
        long miId = 0l;	
        // 生成html文件
        String htmlHead = "<html><meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
        String htmlFoot = "</html>";
        String script = "<link type='text/css' rel='stylesheet' href='"+ipport+"/aris/statics/pages/css/index/css/print.css'/><script type='text/javascript' src='"+ipport+"/aris/statics/common/js/jquery-1.11.2.js'></script><script type='text/javascript' language='javascript'>"
                + "    function logout(){   "
                + " var browserName=navigator.appName;	"
                + " if (browserName=='Netscape'){	"
                + "       window.open('', '_self', '');	"
                + "       window.close();	"
                + " }	"
                + " if (browserName=='Microsoft Internet Explorer') {	"
                + "       window.parent.opener = 'whocares'; 	"
                + "       window.close(); 	"
                + " }	"
                + "}	"
                + " function goBack(){ "
                + " window.location.href = '"+ipport+"/workList/init.html'; "
                + "if(!$('#menuTree',parent.document).is(':hidden')){ "
                + "	parent.switchBarl(); "
                + " } "
                + " } "
                + "function print(){	"
                + " window.opener.document.getElementById('workbtnback').click();"
                + "WebBrowser.ExecWB(6, 6);	"
                //记录打印次数及打印状态
                + "$.ajax({	"
                + "    type: 'POST',	"
                + "    async: true,	"
                + "    url: '"+ipport+"/studyReport/setReportPrintInfo.ajax?reportId=" + reportBean.getReportId() + "',"
                + "    success: function (data) {setTimeout(function(){WebBrowser.execwb(45,1);},5000);	"
                + "   "
                + "    }"
                + "  });"
                + " }  "
                + "   </script><style media=print>.page-content{display:none;}</style><OBJECT classid='CLSID:8856F961-340A-11D0-A96B-00C04FD705A2' height='0' id='WebBrowser' width='0'></OBJECT><div class='page-content' style='text-align:right'> 	"
                + "<button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
        String footBtn = "<div class='page-content' style='text-align:right'><button type='button' class='btn btn-primary' onclick='print()'>打 印</button><button type='button' class='btn btn-pink'  onclick='logout()'>关 闭</button></div>";
        studyReprotMsg = htmlHead + script + studyReprotMsg + footBtn + htmlFoot;


        if (studyReprotMsg != null && !"".equals(studyReprotMsg)) {

            Timestamp t = SysdateManager.getSysTimestamp();
            String date = DateUtils.getDateyyyymm(t);
            String day = getDateday(t);
            String time = DateUtils.getDateddmmmiss(t);

            //按设备类型标识分目录存放
            String mtype = eBean.getModalityId() + "";

            // 生成一个随机数
            Random rand = new Random();
            String part2 = "";
            for (int i = 0; i < 3; i++) {
                part2 += rand.nextInt(10);
            }

            //文件存放服务器及路径处理
            //1. 保存在哪个服务器，哪个介质下,根据科室取存储服务器IP及介质信息
            QryServerMediumBean serverMedium = new QryServerMediumBean();
            QryServerMediumBean[] serverMediums= null;
            serverMediums = getServerMedium(String.valueOf(sbean.getLocId()));
            if (serverMediums.length > 0) {
                for (QryServerMediumBean bean : serverMediums) {
                    if (bean.getMediumIsfull() == 0 || bean.getMediumIsonline() == 1) {
                        serverMedium = bean;
                        break;
                    }
                }
            } else {
            	code = "-2";
	        	message = "回传报告失败：存储介质异常！";
            	
            }
            if ("".equals(serverMedium.getServerIp()) || "".equals(serverMedium.getMediumPath())) {
            	code = "-2";
	        	message = "回传报告失败：存储介质异常！";
            }
            //介质标识
            miId = serverMedium.getMediumId();
            //组织文件名
            String fileName = date + "_" + time + "_" + part2;
            //将本地文件写至fileServer服务器
            String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
            File targetFile = new File(reportUpPath + fileName + ".html");

            if (!copy(studyReprotMsg, targetFile, reportUpPath)) {
            	throw new Exception("上传报告失败：存储介质异常，写HTML文件失败!！");
            }
            fileAddr = fileName + ".html";
          //记录文件地址到库表中   ----删除临时报告文件记录
            AisReportFilesBean[] fileBeans = getfileBeans(reportBean.getReportId(), "0");
            if (fileBeans != null && fileBeans.length > 0) {
                for (int i = 0; i < fileBeans.length; i++) {
                    AisReportFilesBean realBean = AisReportFilesEngine.getBean(fileBeans[i].getReportfileId());
                    realBean.setStsToOld();
                    realBean.delete();
                    AisReportFilesEngine.save(realBean);
                    AisFilesInfoBean oldFileBean = AisFilesInfoEngine.getBean(fileBeans[i].getFileId());
                    oldFileBean.setStsToOld();
                    oldFileBean.delete();
                    AisFilesInfoEngine.save(oldFileBean);
                }
            }

            AisFilesInfoBean fileBean = new AisFilesInfoBean();
            long fileId = ServiceUtil.getSequence("SEQFILEID");
            fileBean.setFileId(fileId);
            fileBean.setStudyinfoId(reportBean.getStudyinfoId());
            fileBean.setFileType("html");
            fileBean.setFilePath(fileAddr);
            fileBean.setMiId(miId);
            AisFilesInfoEngine.save(fileBean);

            AisReportFilesBean reportFileBeqn = new AisReportFilesBean();
            reportFileBeqn.setReportfileId(ServiceUtil.getSequence("SEQREPORTFILEID"));
            reportFileBeqn.setFileId(fileId);
            reportFileBeqn.setReportId(reportBean.getReportId());
            reportFileBeqn.setStatus("0");
            AisReportFilesEngine.save(reportFileBeqn);
        }
        result.put("code", code);
        result.put("message", message);
        return result;
	}
	
	public AisReportFilesBean[] getfileBeans(long reportId, String status) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (reportId != 0) {
            condition.append(" AND report_Id = " + reportId);
        }
        if (isNotBlank(status)) {
            condition.append(" AND status = '" + status + "' ");
        }

        AisReportFilesBean[] reportFile = AisReportFilesEngine.getBeans(condition.toString(), null);
        if (reportFile.length > 0) {
            return reportFile;
        } else {
            return null;
        }
    }
        
   //将模板内容copy到对应目录文件中
    public static boolean copy(String src, File dest, String filePath) {
        try {
            FileOutputStream out = null;
            try {
                File fileDir = new File(filePath);
                if (!fileDir.isDirectory()) {
                    //fileDir.mkdirs();
                }
                out = new FileOutputStream(dest);
                out.write(src.getBytes());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            } finally {
                if (out != null) {
                    out.close();
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        
    public Map getTemplateFilling(String studyinfoId) throws Exception {
        Map map = new HashMap();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();

            String sql = "   SELECT B.STUDYINFO_ID, "
                    + "    B.ORG_ID, "
                    + "    B.LOC_ID, "
                    + "    A.patient_idnumber, "
                    + "    (SELECT SO.ORG_NAME FROM SYS_ORG SO WHERE SO.ORG_ID = B.ORG_ID) AS ORG_NAME, "
                    + "    B.STUDY_ACCNUMBER, "
                    + "    B.PATIENT_GLOBALID, "
                    + "    A.PATIENT_NAME, "
                    + "    DECODE(A.PATIENT_SEX, '1', '男', '2', '女', '未知') PATIENT_SEX, "
                    + "    B.PATIENT_AGE, "
                    + "    B.STUDY_WARD, "
                    + "    B.STUDY_BEDNO, "
                    + "    B.STUDY_ITEMDESC STUDY_ITEMDESC,  "
                    + "    (SELECT WM_CONCAT(C.STUDYITEM_BODYINFO) "
                    + "      FROM AIS_STUDYITEMINFO C "
                    + "      WHERE B.STUDYINFO_ID = C.STUDYINFO_ID) STUDYITEM_BODYINFO, "
                    + "    B.STUDY_REMARK, "
                    + "    B.STUDY_CLINIC, "
                    + "    B.PATIENT_INPATIENTID, "
                    + "    B.STUDY_DOCTORID, "
                    + "    B.AID_DOCTORID, "
                    + "    B.STUDY_EXPOSURECOUNT, "
                    + "    B.STUDY_FILMCOUNT, "
                    + "    B.STUDY_HAVEREPORT, "
                    + "    B.STUDY_HAVEIMAGE, "
                    + "    TO_CHAR(B.STUDY_STARTTIME, 'YYYY-MM-DD hh24:mi:ss') STUDY_TIME, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = B.STUDY_DOCTORID) STUDY_DOCTORNAME, "
                    + "    B.EQUIPMENT_ID, "
                    + "    (SELECT SD.ITEM_NAME FROM SYS_DICTITEM SD WHERE SD.ITEM_NO = B.PATIENTTYPE_CODE AND DICT_NAME='PATIENT_TYPE') PATIENTTYPE, "
                    + "    B.PATIENTTYPE_CODE, "
                    + "    C.REPORT_EXAM, "
                    + "    C.REPORT_RESULT, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_DOCTORID) REPORT_DOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_DATETIME, 'YYYY-MM-DD hh24:mi:ss') REPORT_DATETIME, "
                    + "    TO_CHAR(C.REPORT_DATETIME, 'YYYY-MM-DD hh24:mi:ss') REPORT_DATE, "
                    + "    B.STUDY_ITEMDESC, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_VERIFYDOCTORID) REPORT_VERIFYDOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_VERIFYDATETIME, 'YYYY-MM-DD') REPORT_VERIFYDATETIME, "
                    + "    (SELECT CAREPROV_NAME FROM AISC_CAREPROV AC WHERE AC.CAREPROV_ID = C.REPORT_FINALDOCTORID) REPORT_FINALDOCTORNAME, "
                    + "    TO_CHAR(C.REPORT_FINALDATETIME, 'YYYY-MM-DD') REPORT_FINALDATETIME, "
                    + "    A.PATIENT_ID , "
                    + "    (SELECT AL.LOC_DESC FROM AISC_LOC AL WHERE AL.LOC_ID = B.STUDY_APPLOCID AND AL.ORG_ID = B.ORG_ID AND LOC_TYPE='A')  STUDY_APPLOCNAME , "
                    + "    (SELECT AL.LOC_DESC FROM AISC_LOC AL WHERE AL.LOC_ID = B.STUDY_WARD AND AL.ORG_ID = B.ORG_ID AND LOC_TYPE='B')  STUDY_WARDNAME , "
                    + "    B.PATIENT_OUTPATIENTID , "
                    + "    B.STUDY_BEDNO , "
                    + "    B.ROOM_ID  "
                    + " FROM AIS_PATIENTINFO   A, "
                    + "    AIS_STUDYINFO     B,  "
                    + "    AIS_STUDYREPORT   C "
                    + " WHERE A.PATIENT_GLOBALID = B.PATIENT_GLOBALID "
                    + " AND B.STUDYINFO_ID = C.STUDYINFO_ID "
                    + " AND B.STUDYINFO_ID = " + studyinfoId + " ";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                map.put("ORG_NAME", rs.getString("ORG_NAME") == null ? "" : rs.getString("ORG_NAME"));
                map.put("PATIENT_NAME", rs.getString("PATIENT_NAME") == null ? "" : rs.getString("PATIENT_NAME"));
                map.put("PATIENT_SEX", rs.getString("PATIENT_SEX") == null ? "" : rs.getString("PATIENT_SEX"));
                map.put("PATIENT_AGE", rs.getString("PATIENT_AGE") == null ? "" : rs.getString("PATIENT_AGE"));
                map.put("STUDY_ACCNUMBER", rs.getString("STUDY_ACCNUMBER") == null ? "" : rs.getString("STUDY_ACCNUMBER"));
                map.put("EQUIPMENT_ID", rs.getString("EQUIPMENT_ID") == null ? "" : rs.getString("EQUIPMENT_ID"));
                map.put("PATIENTTYPE", rs.getString("PATIENTTYPE") == null ? "" : rs.getString("PATIENTTYPE"));
                map.put("PATIENT_INPATIENTID", rs.getString("PATIENT_INPATIENTID") == null ? "" : rs.getString("PATIENT_INPATIENTID"));
                map.put("STUDYITEM_BODYINFO", rs.getString("STUDYITEM_BODYINFO") == null ? "" : rs.getString("STUDYITEM_BODYINFO"));
                map.put("STUDY_TIME", rs.getString("STUDY_TIME") == null ? "" : rs.getString("STUDY_TIME"));
                map.put("STUDY_DOCTORNAME", rs.getString("STUDY_DOCTORNAME") == null ? "" : rs.getString("STUDY_DOCTORNAME"));

                map.put("REPORT_DOCTORNAME", rs.getString("REPORT_DOCTORNAME") == null ? "" : rs.getString("REPORT_DOCTORNAME"));
                map.put("REPORT_DATETIME", rs.getString("REPORT_DATETIME") == null ? "" : rs.getString("REPORT_DATETIME"));
                map.put("REPORT_DATE", rs.getString("REPORT_DATE") == null ? "" : rs.getString("REPORT_DATE"));
                map.put("STUDY_ITEMDESC", rs.getString("STUDY_ITEMDESC") == null ? "" : rs.getString("STUDY_ITEMDESC"));
                map.put("REPORT_VERIFYDOCTORNAME", rs.getString("REPORT_VERIFYDOCTORNAME") == null ? "" : rs.getString("REPORT_VERIFYDOCTORNAME"));
                map.put("REPORT_VERIFYDATETIME", rs.getString("REPORT_VERIFYDATETIME") == null ? "" : rs.getString("REPORT_VERIFYDATETIME"));
                map.put("REPORT_FINALDOCTORNAME", rs.getString("REPORT_FINALDOCTORNAME") == null ? "" : rs.getString("REPORT_FINALDOCTORNAME"));
                map.put("REPORT_FINALDATETIME", rs.getString("REPORT_FINALDATETIME") == null ? "" : rs.getString("REPORT_FINALDATETIME"));
                map.put("PATIENT_ID", rs.getString("PATIENT_ID") == null ? "" : rs.getString("PATIENT_ID"));
                map.put("STUDY_APPLOCNAME", rs.getString("STUDY_APPLOCNAME") == null ? "" : rs.getString("STUDY_APPLOCNAME"));
                map.put("PATIENTTYPE_CODE", rs.getString("PATIENTTYPE_CODE") == null ? "" : rs.getString("PATIENTTYPE_CODE"));

                map.put("STUDY_WARDNAME", rs.getString("STUDY_WARDNAME") == null ? "" : rs.getString("STUDY_WARDNAME"));
                map.put("PATIENT_OUTPATIENTID", rs.getString("PATIENT_OUTPATIENTID") == null ? "" : rs.getString("PATIENT_OUTPATIENTID"));
                map.put("STUDY_BEDNO", rs.getString("STUDY_BEDNO") == null ? "" : rs.getString("STUDY_BEDNO"));
                map.put("ROOM_ID", rs.getString("ROOM_ID") == null ? "" : rs.getString("ROOM_ID"));
                map.put("PATIENT_IDNUMBER", rs.getString("PATIENT_IDNUMBER") == null ? "" : rs.getString("PATIENT_IDNUMBER"));

                Clob exam = rs.getClob("REPORT_EXAM");//检查所见
                Clob result = rs.getClob("REPORT_RESULT");//诊断结果
                if (exam != null) {
                    map.put("REPORT_EXAM", exam.getSubString(1, (int) exam.length()) == null ? "" : exam.getSubString(1, (int) exam.length()));
                }
                if (result != null) {
                    map.put("REPORT_RESULT", result.getSubString(1, (int) result.length()) == null ? "" : result.getSubString(1, (int) result.length()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(rs, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }

        return map;
    }
    
  //根据科室标识，查询存储服务介质
    public QryServerMediumBean[] getServerMedium(String locId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(locId)) {
            condition.append(" AND LOC_ID = " + locId);
        }
        condition.append(" AND SERVER_ENABLED = 1");  //状态有效
        condition.append(" AND MEDIUM_ENABLED = 1");  //状态有效

        condition.append(" ORDER BY MEDIUM_ID ASC");
        QryServerMediumBean[] serverMediums = QryServerMediumEngine.getBeans(condition.toString(), null);

        return serverMediums;

    }
    
    public static String getDateday(Timestamp t) {
        String str = t.toString();
        str = str.replace("-", "");
        String date = str.substring(6, 8);
        return date;
    }
    
    public AisStudyReportBean getOldReport(long studyinfoId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (studyinfoId!=0) {
            condition.append(" AND studyinfo_id = " + studyinfoId);
        }
        AisStudyReportBean[] reportBean = AisStudyReportEngine.getBeans(condition.toString(), null);

        if (reportBean!=null&&reportBean.length > 0) {
            return reportBean[0];
        } else {
            return null;
        }
    }
}
