package com.ai.aris.server.webservice.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AisPixInfoBean;
import com.ai.aris.server.basecode.bean.AisPixInfoEngine;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.interfacereal.yinglian.AisFyYinglianLogBean;
import com.ai.aris.server.interfacereal.yinglian.AisFyYinglianLogEngine;
import com.ai.aris.server.interfacereal.yinglian.QryAiStudyResultBean;
import com.ai.aris.server.interfacereal.yinglian.QryAiStudyResultEngine;
import com.ai.aris.server.webservice.bean.yinglian.AiStudyUploadRequest;
import com.ai.aris.server.webservice.service.interfaces.IAiStudyOutSV;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoEngine;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;

/**
 * Created by lenovo on 2017/11/14.
 */
public class AiStudyOutSVImpl implements IAiStudyOutSV {
	IPatientRegSV patientSv = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);
	ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);
	@Override
	public String insertStudyinfo(AiStudyUploadRequest requestBodyBean)
			throws Exception {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Map seqs = patientSv.getSeq(requestBodyBean.getOrgCode(),"-999");	
		long id = Long.parseLong(seqs.get("patientGlobalid").toString());
		String pacsPatientId  = seqs.get("patientId").toString();
		AisPatientInfoBean[] patientBean = null;
		if(isNotBlank(requestBodyBean.getPatientIDNumber())){
			patientBean = getAisPatientBean(requestBodyBean.getPatientIDNumber());
		}
		//检查设备
		AiscEquipmentBean aiscEq = getEquippmetByCode(requestBodyBean.getStudyEquipment(),requestBodyBean.getOrgCode());
		//当前科室
		AiscLocBean aiscloc = getLocByCode(requestBodyBean.getLocCode(),requestBodyBean.getOrgCode());
		//申请科室
		AiscLocBean apploc = getLocByCode(requestBodyBean.getAppLoc(),requestBodyBean.getOrgCode());
		//会诊科室
		AiscLocBean consultLoc = getLocByCode(requestBodyBean.getConsultLocCode(),requestBodyBean.getConsultOrgCode());
		
		//查询ais_pixinfo表中是否有记录
		AisPixInfoBean[] pixBean = getAisPixInfoBean(requestBodyBean.getPatientID(),requestBodyBean.getOrgCode());
		
		if(pixBean!=null&&patientBean==null){
			//pixBean 更新

			//patient 插入
		}else if(pixBean==null&&patientBean==null){
			//patient 插入&pixBean 插入
			
			//插入检查信息表
			if(!getAisStudyInfo(requestBodyBean.getAccesionNumber(),requestBodyBean.getOrgCode())){
				//pix保存医院的病人ID
				AisPixInfoBean zbean = new AisPixInfoBean();
				zbean.setPatientGlobalid(id);
				zbean.setOrgId(requestBodyBean.getOrgCode());
				zbean.setPatientId(requestBodyBean.getPatientID());
				AisPixInfoEngine.save(zbean);
				//中心病人ID保存为中心规则病人ID
				AisPatientInfoBean bean = new AisPatientInfoBean();
				bean.setPatientId(pacsPatientId);
				bean.setPatientGlobalid(id);
				bean.setPatientName(requestBodyBean.getPatientName());
				bean.setPatientNamepy(requestBodyBean.getPatientPYName());
				bean.setPatientDob(Timestamp.valueOf(requestBodyBean.getPatientBirthday()+" 00:00:00"));
				bean.setPatientAge(requestBodyBean.getPatientAge());
				bean.setPatientSex(String.valueOf(requestBodyBean.getPatientGender()));
				bean.setPatientIdnumber(requestBodyBean.getPatientIDNumber());
				bean.setOrgId(requestBodyBean.getOrgCode());
				AisPatientInfoEngine.save(bean);
				
				AisStudyInfoBean studyinfoBean = new AisStudyInfoBean();
				studyinfoBean.setStudyinfoId(commonSV.getSequence("SEQSTUDYINFOID"));
				studyinfoBean.setStudyAccnumber(requestBodyBean.getAccesionNumber());
				studyinfoBean.setOrgId(requestBodyBean.getOrgCode());
				studyinfoBean.setLocId(aiscloc.getLocId());
				studyinfoBean.setPatienttypeCode(requestBodyBean.getPatientType());
				studyinfoBean.setStudyWard(requestBodyBean.getAppWard());
				studyinfoBean.setStudyBedno(requestBodyBean.getAppBedNo()); 
				studyinfoBean.setStudyAim(requestBodyBean.getStudyAim());
				studyinfoBean.setStudyClinic(requestBodyBean.getStudyClinic());
				studyinfoBean.setStudyItemdesc(requestBodyBean.getStudyDesc());
				studyinfoBean.setEquipmentId(aiscEq.getEquipmentId());
				studyinfoBean.setStudystatusCode("APPConsult");
				studyinfoBean.setPatientGlobalid(id);
				studyinfoBean.setStudyAppdoc("-1");
				studyinfoBean.setStudyApplocid(apploc.getLocId());
				studyinfoBean.setStudyConsultloc(consultLoc.getLocId());
				studyinfoBean.setStudyConsultorg(requestBodyBean.getConsultOrgCode());
				studyinfoBean.setStudyHavereport(0);
				studyinfoBean.setStudyHaveimage(1);
				studyinfoBean.setStudyType(1);
				studyinfoBean.setIsUrgent(0);
				studyinfoBean.setStudyAppdate(Timestamp.valueOf(requestBodyBean.getStudyDate()));
				Timestamp time= new Timestamp(System.currentTimeMillis());
				studyinfoBean.setStudyCreatetime(time);
				studyinfoBean.setStudyDatetime(time);
				studyinfoBean.setStudyOperationtime(time);
				AisStudyInfoEngine.save(studyinfoBean);
			}
			return pacsPatientId;
		}else if(pixBean==null&&patientBean!=null){
			//查询pacs已有病人信息
			if(patientBean!=null&&patientBean.length>0){
				//插入检查信息表
				if(!getAisStudyInfo(requestBodyBean.getAccesionNumber(),requestBodyBean.getOrgCode())){
					//插入pixBean
					AisPixInfoBean zbean = new AisPixInfoBean();
					zbean.setPatientGlobalid(patientBean[0].getPatientGlobalid());
					zbean.setOrgId(requestBodyBean.getOrgCode());
					zbean.setPatientId(requestBodyBean.getPatientID());
					AisPixInfoEngine.save(zbean);
					
					AisStudyInfoBean studyinfoBean = new AisStudyInfoBean();
					studyinfoBean.setStudyinfoId(commonSV.getSequence("SEQSTUDYINFOID"));
					studyinfoBean.setStudyAccnumber(requestBodyBean.getAccesionNumber());
					studyinfoBean.setOrgId(requestBodyBean.getOrgCode());
					studyinfoBean.setLocId(aiscloc.getLocId());
					studyinfoBean.setPatienttypeCode(requestBodyBean.getPatientType());
					studyinfoBean.setStudyWard(requestBodyBean.getAppWard());
					studyinfoBean.setStudyBedno(requestBodyBean.getAppBedNo()); 
					studyinfoBean.setStudyAim(requestBodyBean.getStudyAim());
					studyinfoBean.setStudyClinic(requestBodyBean.getStudyClinic());
					studyinfoBean.setStudyItemdesc(requestBodyBean.getStudyDesc());
					studyinfoBean.setEquipmentId(aiscEq.getEquipmentId());
					studyinfoBean.setStudystatusCode("APPConsult");
					studyinfoBean.setPatientGlobalid(patientBean[0].getPatientGlobalid());
					studyinfoBean.setStudyApplocid(apploc.getLocId());
					studyinfoBean.setStudyAppdoc("-1");
					studyinfoBean.setStudyConsultloc(consultLoc.getLocId());
					studyinfoBean.setStudyConsultorg(requestBodyBean.getConsultOrgCode());
					studyinfoBean.setStudyHavereport(0);
					studyinfoBean.setStudyHaveimage(1);
					studyinfoBean.setStudyType(1);
					studyinfoBean.setIsUrgent(0);
					studyinfoBean.setStudyAppdate(Timestamp.valueOf(requestBodyBean.getStudyDate()));
					Timestamp time= new Timestamp(System.currentTimeMillis());
					studyinfoBean.setStudyCreatetime(time);
					studyinfoBean.setStudyOperationtime(time);
					studyinfoBean.setStudyDatetime(time);
					AisStudyInfoEngine.save(studyinfoBean);
				}
				return patientBean[0].getPatientId();
			}
		}else{
			//若pix和中心patient都有信息，则只更新病人信息
//			if(pixBean!=null&&pixBean.length>0){
//				for(int hh=0;hh<pixBean.length;hh++){
//					//更新中心pacs已有病人信息
//					updatePatient(pixBean[hh].getPatientGlobalid(),model);
//				}
//			}
			//插入检查信息表
			if(!getAisStudyInfo(requestBodyBean.getAccesionNumber(),requestBodyBean.getOrgCode())){
				AisStudyInfoBean studyinfoBean = new AisStudyInfoBean();
				studyinfoBean.setStudyinfoId(commonSV.getSequence("SEQSTUDYINFOID"));
				studyinfoBean.setStudyAccnumber(requestBodyBean.getAccesionNumber());
				studyinfoBean.setOrgId(requestBodyBean.getOrgCode());
				studyinfoBean.setLocId(aiscloc.getLocId());
				studyinfoBean.setPatienttypeCode(requestBodyBean.getPatientType());
				studyinfoBean.setStudyWard(requestBodyBean.getAppWard());
				studyinfoBean.setStudyBedno(requestBodyBean.getAppBedNo()); 
				studyinfoBean.setStudyAim(requestBodyBean.getStudyAim());
				studyinfoBean.setStudyClinic(requestBodyBean.getStudyClinic());
				studyinfoBean.setStudyItemdesc(requestBodyBean.getStudyDesc());
				studyinfoBean.setEquipmentId(aiscEq.getEquipmentId());
				studyinfoBean.setStudystatusCode("APPConsult");
				studyinfoBean.setPatientGlobalid(patientBean[0].getPatientGlobalid());
				studyinfoBean.setStudyApplocid(apploc.getLocId());
				studyinfoBean.setStudyAppdoc("-1");
				studyinfoBean.setStudyConsultloc(consultLoc.getLocId());
				studyinfoBean.setStudyConsultorg(requestBodyBean.getConsultOrgCode());
				studyinfoBean.setStudyHavereport(0);
				studyinfoBean.setStudyHaveimage(1);
				studyinfoBean.setStudyType(1);
				studyinfoBean.setIsUrgent(0);
				studyinfoBean.setStudyAppdate(Timestamp.valueOf(requestBodyBean.getStudyDate()));
				Timestamp time= new Timestamp(System.currentTimeMillis());
				studyinfoBean.setStudyCreatetime(time);
				studyinfoBean.setStudyDatetime(time);
				studyinfoBean.setStudyOperationtime(time);
				AisStudyInfoEngine.save(studyinfoBean);
			}
			return patientBean[0].getPatientId();
		}
		return "";
	}
	
	public AiscLocBean getLocByCode(String code,String orgId)throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(code)) {
	    	condition.append(" AND loc_code = '" + code+"'" ); 
        }
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND org_id = '" + orgId+"'" ); 
        }
	    AiscLocBean[] aiscLoc = AiscLocEngine.getBeans(condition.toString(), null); 
	    if(aiscLoc.length > 0){
	    	return aiscLoc[0]; 
	    }else{
	    	return null;
	    }
	}
	
	public AiscEquipmentBean getEquippmetByCode(String code,String orgId)throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(code)) {
	    	condition.append(" AND equipment_code = '" + code+"'" ); 
        }
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND org_id = '" + orgId+"'" ); 
        }
	    AiscEquipmentBean[] equipment = AiscEquipmentEngine.getBeans(condition.toString(), null); 
	    if(equipment.length > 0){
	    	return equipment[0]; 
	    }else{
	    	return null;
	    }
	}
	
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
	
	public boolean getAisStudyInfo(String studyAccnumber,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyAccnumber)) {
	    	condition.append(" AND STUDY_ACCNUMBER = '" + studyAccnumber+"'" ); 
        }  
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND ORG_ID = '" + orgId+"'" ); 
        }
	    AisStudyInfoBean[] StudyInfoBean = AisStudyInfoEngine.getBeans(condition.toString(), null); 
	    if(StudyInfoBean.length > 0){
	    	return true; 
	    }else{
	    	return false;
	    }
	}
	
	
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
	
	public QryAiStudyResultBean getReportInfo(String studyAccnumber,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyAccnumber)) {
	    	condition.append(" AND study_accnumber = '" + studyAccnumber+"'" ); 
        }  
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND ORG_ID = '" + orgId+"'" ); 
        }
	    QryAiStudyResultBean[] reportBean = QryAiStudyResultEngine.getBeans(condition.toString(), null); 
	    if(reportBean!=null&&reportBean.length > 0){
	    	return reportBean[0]; 
	    }else{
	    	return null;
	    }
	}
	
	public void insertStudyinfoLog(String god_portal_request_id,String orgId,String requestBody,String responseBody,String type) throws Exception{
		AisFyYinglianLogBean bean = new AisFyYinglianLogBean();
		bean.setRequestId(god_portal_request_id);
		bean.setOrgId(orgId);
		bean.setRequestBody(requestBody);
		bean.setResponsebody(responseBody);
		bean.setInterfaceType(type);
		bean.setCreateTime(new Timestamp(System.currentTimeMillis()));
		AisFyYinglianLogEngine.save(bean);
	}
   
}
