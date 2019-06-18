package com.ai.aris.server.workstation.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscRuleBean;
import com.ai.aris.server.basecode.bean.AiscRuleEngine;
import com.ai.aris.server.basecode.bean.QryConsultLocBean;
import com.ai.aris.server.basecode.bean.QryConsultLocEngine;
import com.ai.aris.server.basecode.bean.QryConsultOrgBean;
import com.ai.aris.server.basecode.bean.QryConsultOrgEngine;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.jchis.model.PacsHzjbxxViewModel;
import com.ai.aris.server.webservice.bean.AisPatientInfoData;
import com.ai.aris.server.webservice.bean.AisStudyItemInfoData;
import com.ai.aris.server.webservice.bean.AiscLocData;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.bean.realtime.PatientResponse;
import com.ai.aris.server.webservice.bean.realtime.StudyinfoItemResponse;
import com.ai.aris.server.webservice.bean.realtime.StudyinfoResponse;
import com.ai.aris.server.webservice.imageservice.FileServiceStub;
import com.ai.aris.server.webservice.imageservice.FileServiceStub.UpFileResult;
import com.ai.aris.server.workstation.bean.AisFilesInfoBean;
import com.ai.aris.server.workstation.bean.AisFilesInfoEngine;
import com.ai.aris.server.workstation.bean.AisModalityworklistBean;
import com.ai.aris.server.workstation.bean.AisModalityworklistEngine;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyHistoryInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyHistoryInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyMessageBean;
import com.ai.aris.server.workstation.bean.AisStudyMessageEngine;
import com.ai.aris.server.workstation.bean.CheckBodyPartBean;
import com.ai.aris.server.workstation.bean.CheckBodyPartEngine;
import com.ai.aris.server.workstation.bean.QryBigBodypartBean;
import com.ai.aris.server.workstation.bean.QryBigBodypartEngine;
import com.ai.aris.server.workstation.bean.QryItemmastEquimentBean;
import com.ai.aris.server.workstation.bean.QryItemmastEquimentEngine;
import com.ai.aris.server.workstation.bean.QryPatientIsHisBean;
import com.ai.aris.server.workstation.bean.QryPatientIsHisEngine;
import com.ai.aris.server.workstation.bean.QryRegInfoBean;
import com.ai.aris.server.workstation.bean.QryRegInfoEngine;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListEngine;
import com.ai.aris.server.workstation.bean.StudyBean;
import com.ai.aris.server.workstation.bean.StudyEngine;
import com.ai.aris.server.workstation.model.AisFilesInfoModel;
import com.ai.aris.server.workstation.model.AisPatientInfoModel;
import com.ai.aris.server.workstation.model.AisStudyInfoModel;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.json.CustomObjectMapper;
import com.ai.common.util.ApplicationUtil;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.FTPSUtil;
import com.ai.common.util.PropertiesUtils;
import com.ai.common.util.ServiceUtil;
 

public class PatientRegSVImpl implements IPatientRegSV{  
	private Log logger = LogFactory.getLog(PatientRegSVImpl.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	private static IStudyReportSV studysv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
	@Override
	public void updatePatient(AisPatientInfoBean bean) throws Exception{
		AisPatientInfoBean oldBean = AisPatientInfoEngine.getBean(bean.getPatientGlobalid());
        DataContainerFactory.copyNoClearData(bean, oldBean);
        AisPatientInfoEngine.save(oldBean);
	}
	
	public Map getSeq(String orgId, String locId) throws Exception {
		Map map = new HashMap();
		
        //1. 查病人全局序列
		long patientGlobalid = ServiceUtil.getSequence("SEQGOBALPATIENTID");		
		String patientId = getIdByRule("PATIENT_ID",orgId,locId) ;
		String studyAccnumber = getIdByRule("STUDY_ACCNUMBER",orgId,locId) ;	
		String studyLocseqno = getIdByRule("STUDY_LOCSEQNO",orgId,locId) ;	
		map.put("patientGlobalid", patientGlobalid);
		map.put("patientId", patientId);
		map.put("studyAccnumber", studyAccnumber);
		map.put("studyLocseqno", studyLocseqno);
		
		return map;
	}
	
	public String getIdByRule(String fieldName,String orgId,String locId) throws Exception{
		Date date = new Date();       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        
		String ids = "";
		AiscRuleBean pRuleBean = getRule(orgId,locId,fieldName);
		if(pRuleBean != null){
			//1.前缀
        	String prix = pRuleBean.getRulePrix()==null?"":pRuleBean.getRulePrix(); 
        	//2.长度
        	long len = pRuleBean.getRuleLen();
        	//3.开始序号
        	long startIndex = pRuleBean.getRuleStartindex();
			if(pRuleBean.getRuleType() == 1){
	        	//按日期增量规则生成
	        	String eLen = prix+String.valueOf(startIndex);
	        	long cLen = len - eLen.length();
	        	String blen = "";
	        	for(int i=0;i<cLen;i++){
	        		blen+="0";
	            }
	        	ids = prix+String.valueOf(dateFormat.format(date))+blen+startIndex;         	          	
	        }else if(pRuleBean.getRuleType() == 2){
	        	//按增量规则生成	  
	        	if(null == String.valueOf(len) || len == 0 ){
	        		ids = String.valueOf(startIndex+1);
	        	}else{
	        		String eLen = prix+String.valueOf(startIndex);
		        	long cLen = len - eLen.length();
		        	String blen = "";
		        	for(int i=0;i<cLen;i++){
		        		blen+="0";
		            }
		        	ids = prix+blen+startIndex;   	
	        	}	        	
	        }
			//规则递增更新
			startIndex +=1; 
			if(pRuleBean != null){
				pRuleBean.setRuleStartindex(startIndex);  			 
				AiscRuleEngine.save(pRuleBean);
			}			
		}
	    
	    //如果没有配置，则取序列
	    if("".equals(ids)){
	    	if("PATIENT_ID".equals(fieldName)){
	    		ids = String.valueOf(ServiceUtil.getSequence("SEQGOBALPATIENTID"));
	    	}else if("STUDY_ACCNUMBER".equals(fieldName)){
	    		ids = String.valueOf(ServiceUtil.getSequence("SEQSTUDYACCNUMBER"));
	    	}
	    }	    
	   return ids;
	}
		
	//取登记号
	public String getPatientId(String dateStr,AiscRuleBean pRuleBean) throws Exception{		        
		String sql = " SELECT MAX(PATIENT_ID) FROM AIS_PATIENTINFO WHERE SUBSTR(PATIENT_ID, 0, 8) = "+dateStr+" "; 
        AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeansFromSql(sql, null);
         
        if(beans[0].getPatientId() != null && !"".equals(beans[0].getPatientId())){
        	return String.valueOf(Integer.valueOf(beans[0].getPatientId())+1) ;
        }else{
        	return dateStr + 001;
        }  
	}
	//取检查号
	public String getStudyAccnumber(String dateStr) throws Exception{		        
		String sql = " SELECT MAX(STUDY_ACCNUMBER) FROM AIS_STUDYINFO WHERE SUBSTR(STUDY_ACCNUMBER, 0, 8) = "+dateStr+" "; 
        AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeansFromSql(sql, null);
         
        if(beans[0].getPatientId() != null && !"".equals(beans[0].getPatientId())){
        	return String.valueOf(Integer.valueOf(beans[0].getPatientId())+1) ;
        }else{
        	return dateStr + "001";
        } 
	} 
	

	/*  查询序列生成规则 
	 *  先按机构+科室+字段检索，检索不到再按机构+字段检索，检查不到就直接按字段检索	 
     */	
	public AiscRuleBean getRule(String orgId, String locId ,String ruleField) throws Exception {
		StringBuffer condition = new StringBuffer();
		StringBuffer condition1 = new StringBuffer();
		StringBuffer condition2 = new StringBuffer();
		StringBuffer condition3 = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"-1".equals(orgId) ) {
	    	condition1.append(" AND ORG_ID = '" + orgId +"'"); 
        } 
	    if (isNotBlank(locId) && !"-1".equals(locId)) { 
	    	condition2.append(" AND LOC_ID = '" + locId +"'");
        }
	    if (isNotBlank(ruleField) && !"-1".equals(ruleField)) {
	    	condition3.append(" AND RULE_FIELD = '" + ruleField +"'"); 
        }else{
        	return null;
        } 	      
	    AiscRuleBean[] ruleBean = AiscRuleEngine.getBeans(String.valueOf(condition)+String.valueOf(condition1)+String.valueOf(condition2)+String.valueOf(condition3), null); 
		
	    if(ruleBean.length > 0){
	    	return ruleBean[0]; 
	    }else{
	    	ruleBean = AiscRuleEngine.getBeans(String.valueOf(condition)+String.valueOf(condition1)+String.valueOf(condition3), null);	    	
	    	if(ruleBean.length > 0){	    		
	    		return ruleBean[0]; 
		    }else{
		    	ruleBean = AiscRuleEngine.getBeans(String.valueOf(condition)+String.valueOf(condition3), null);
		    	if(ruleBean.length > 0){	    		
		    		return ruleBean[0]; 
			    }else{
			    	return new AiscRuleBean(); 
			    }	    	
		    }	    	
	    } 
	}
	
	private CheckBodyPartBean checkBodyPart(String bodypart,String itemmastId,String orgId) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");
		if(isNotBlank(orgId)){
			condition.append(" and org_id ='"+orgId+"'");
		}
		if(isNotBlank(bodypart)) {
			condition.append(" and bodypart_desc ='"+bodypart.trim()+"'");
		}
		if(isNotBlank(itemmastId)) {
			condition.append(" and itemmast_id = '"+itemmastId+"'");
		}
		CheckBodyPartBean[] beans = CheckBodyPartEngine.getBeans(condition.toString(), null);
		if (beans!=null&&beans.length>0){
			return  beans[0] ;
		}else return  null ;
	}
	
	public QryPatientIsHisBean getPatient(AisStudyInfoBean studyBean,AisPatientInfoBean aisPatient) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");
		if(isNotBlank(studyBean.getPatienttypeCode())){
			if("INP".equals(studyBean.getPatienttypeCode())||"HP".equals(studyBean.getPatienttypeCode())){
				condition.append(" and PATIENT_INPATIENTID ='"+studyBean.getPatientInpatientid()+"'");
			}else if("OP".equals(studyBean.getPatienttypeCode())){
				condition.append(" and PATIENT_OUTPATIENTID ='"+studyBean.getPatientOutpatientid()+"'");
			}
		}
		if(isNotBlank(aisPatient.getPatientIdnumber())) {
			condition.append(" and patient_idnumber ='"+aisPatient.getPatientIdnumber()+"'");
		}
		if(isNotBlank(aisPatient.getPatientName())) {
			condition.append(" and patient_name ='"+aisPatient.getPatientName()+"'");
		}
		if(isNotBlank(aisPatient.getPatientSex())) {
			condition.append(" and patient_sex ='"+aisPatient.getPatientSex()+"'");
		}
		
		QryPatientIsHisBean[] beans = QryPatientIsHisEngine.getBeans(condition.toString(), null);
		if (beans!=null&&beans.length>0){
			return  beans[0] ;
		}else return  null ;
	}
	
	public boolean getStudyByAccnumber(String accesstionNumber) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");
		if(isNotBlank(accesstionNumber)){
			condition.append(" and ACCESSIONNUMBER ='"+accesstionNumber+"'");
		}
		StudyBean[] beans = StudyEngine.getBeans(condition.toString(), null);
		if (beans!=null&&beans.length>0){
			return  true;
		}else return  false ;
	}
	
	public void updateStudyMark(String accesstionNumber,String status) throws Exception{
		Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sqlUp = "update study set hasrisinfo='"+status+"' where ACCESSIONNUMBER='"+ accesstionNumber+"'";
            stmt.executeUpdate(sqlUp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
	}
	
	public void updateAccnumber(String patientKey,String accesstionNumber,String status) throws Exception{
		Statement stmt = null;
        try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sqlUp = "update study set hasrisinfo='"+status+"',ACCESSIONNUMBER='"+ accesstionNumber+"' where patientkey="+patientKey;
            stmt.executeUpdate(sqlUp);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
	}
	
	//保存病人登记记录
	public Map savePatientReg(User user,AisPatientInfoBean patientBean,
			AisStudyInfoBean studyBean, String studyitemDesc,
			String studyitemBodyinfo, String studyitemNumber,
			String studyitemPrice,String isNew,String yuyueTime,String isHis,
			String oldStudyinfoId,String isConsult,String arrayBodystrs,String patientkey) throws Exception {
		
		//处理掉下拉框取值为-1的数据
//		if(studyBean.getStudyApplocid() == -1){
//			studyBean.getStudyApplocid() = 0l;
//		}
	    Map map = new HashMap();
	    map.put("flag","false");
		Timestamp dateTime=new Timestamp(new Date().getTime());
		long studyInfoId = 0l;
		long patientGlobalid = 0l;
		String patientId = "";
		String studyAccnumber = "";
		String studyLocseqno = "";
		String studyItemDescS = "";//检查项拼接
		String studyItemDescEnS = "";
		String bodypartModality = "";
		if("y".equals(isNew) || "".equals(isNew)){
			//若病人為新增則走y
			//取规则生成的流水号
			Map seqs = getSeq(studyBean.getOrgId(),String.valueOf(studyBean.getLocId()));
			if("y".equals(isHis)){
				//从登记表中获取存在病人
				QryPatientIsHisBean patient = getPatient(studyBean,patientBean);
				if(patient!=null){
					patientId = patient.getPatientId();
					patientGlobalid= patient.getPatientGlobalid();
					AisPatientInfoBean oldPatient = AisPatientInfoEngine.getBean(patientGlobalid);
					patientBean.setPatientId(patientId);
					patientBean.setPatientGlobalid(patientGlobalid);
					patientBean.setOrgId(studyBean.getOrgId());
					DataContainerFactory.copyNoClearData(patientBean, oldPatient);			 
					AisPatientInfoEngine.save(oldPatient);
				}else{
					patientId = String.valueOf(seqs.get("patientId"));
					patientGlobalid = Long.parseLong(String.valueOf(seqs.get("patientGlobalid")));
					//病人信息
					patientBean.setOrgId(studyBean.getOrgId());
					patientBean.setPatientId(patientId);
					patientBean.setPatientGlobalid(patientGlobalid);
					AisPatientInfoEngine.save(patientBean);
				}
			}else{
				patientId = String.valueOf(seqs.get("patientId"));
				patientGlobalid = Long.parseLong(String.valueOf(seqs.get("patientGlobalid")));
				//病人信息
				patientBean.setPatientId(patientId);
				patientBean.setOrgId(studyBean.getOrgId());
				patientBean.setPatientGlobalid(patientGlobalid);
				AisPatientInfoEngine.save(patientBean);
			}
			studyAccnumber = String.valueOf(seqs.get("studyAccnumber"));
			studyLocseqno = String.valueOf(seqs.get("studyLocseqno"));
			//检查信息
			studyInfoId = ServiceUtil.getSequence("SEQSTUDYINFOID");
			studyBean.setPatientGlobalid(patientGlobalid);
			studyBean.setStudyinfoId(studyInfoId);
			studyBean.setStudyAccnumber(studyAccnumber);
			//新增时，两个时间都写
			studyBean.setStudyAppdate(dateTime);//申请时间
			
			//his申请单
//			if("y".equals(isHis)){
//				studyBean.setStudyHisappid("-99");
//			}
			
//			if(studyBean.getStudyWard()==null&&studyBean.getStudyWard().equals("-1")){
//				studyBean.setStudyWard("");
//			}
			
			//yuyueTime
			studyBean.setStudyDatetime(Timestamp.valueOf(yuyueTime));//登记时间
			
			studyBean.setStudystatusCode("ARRIVE");
			studyBean.setStudyHaveimage(0);//无图片
			studyBean.setStudyHavereport(0);//无报告
			studyBean.setStudyOperationid(user.getOperatorId());//操作员
			studyBean.setStudyCreatetime(dateTime);
			//检查类型	
			if(isConsult.equals("y")){
				studyBean.setStudyType(1);//远程会诊医嘱
				studyBean.setIsUrgent(0);
				studyBean.setStudyConsultstarttime(dateTime);//会诊时间
				//操作记录
//				studyBean.setStudyItemdesc("远程会诊");
//				studyItemDescS = "远程会诊";
				if("AppSave".equals(studyBean.getStudystatusCode())||!"".equals(studyBean.getStudystatusCode())){
					studyBean.setStudystatusCode("AppSave");								
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
				}else{
//					studyBean.setStudystatusCode("APPConsult");								
//					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
//					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",1);
				}
			}else{
				if(null != studyBean.getStudyConsultorg() && !"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg()) && !"undefined".equals(studyBean.getStudyConsultorg()) ){
					studyBean.setStudyType(1);//远程会诊医嘱
					studyBean.setStudystatusCode("APPConsult");						
					studyBean.setStudyConsultstarttime(dateTime);//诊断时间	
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",1);
				}else{
					studyBean.setStudyType(0);//检查医嘱
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APP");
				}				
			}			
			//检查项目   new方式
			if(arrayBodystrs!=null&&!"".equals(arrayBodystrs)){
				JSONArray json = JSONArray.fromObject(arrayBodystrs);
				if(json.size()>0){
					for(int i=0;i<json.size();i++){
					    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					    System.out.println() ;  // 得到 每个对象中的属性值
					    //检查登记部位ais_studyitem_info
					    AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
						bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
						bean.setStudyinfoId(studyBean.getStudyinfoId());
						String itemdesc = URLDecoder.decode(job.getString("itemdesc"),"UTF-8");
						String[] itemArray = itemdesc.split("_");
						bean.setStudyitemCode(itemArray[0]);
						bean.setStudyitemDesc(itemArray[1]);
						if(itemArray.length>2){
							bean.setStudyitemEndesc(itemArray[2]);
						}
						String bodyinfo = job.getString("itempart");
						String bodypart = URLDecoder.decode(job.getString("itempartname"),"UTF-8");
						bean.setStudyitemBodyinfo("".equals(bodypart)?"":bodypart.replace(",","、"));
						bean.setStudyitemBodypart("".equals(bodyinfo)?"":bodyinfo);
						bean.setStudyitemNumber(Integer.valueOf(job.getString("itemnum")));
						bean.setStudyitemPrice(Integer.valueOf(job.getString("itemprice")));
						bean.setStudyitemStatus("N"); //医嘱项目状态:N: 正常 , S: 停止					
						AisStudyItemInfoEngine.save(bean);		
						bodypartModality+=bodypartModality==""?bodypart:","+bodypart;
						studyItemDescS+=studyItemDescS==""?bean.getStudyitemDesc():","+bean.getStudyitemDesc();
						studyItemDescEnS+=studyItemDescEnS==""?bean.getStudyitemEndesc():","+bean.getStudyitemEndesc();
					}
					
				}
			}
			//检查序列号
			studyBean.setStudyLocseqno(Long.parseLong(studyLocseqno));
			//保存
			studyBean.setStudyEnitemdesc(studyItemDescEnS);
			studyBean.setStudyItemdesc(studyItemDescS);
			AisStudyInfoEngine.save(studyBean);
			
			DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC",studyBean.getOrgId());
			if(dictBean!=null){
				String info = studyBean.getStudyHisappid()+"^"+patientId+"^"+studyBean.getPatientInpatientid()+"^"+studyBean.getPatientOutpatientid()+"^"
						+studyBean.getPatienttypeCode()+"^"+patientBean.getPatientName()+"^"+studyAccnumber+"^"+studyItemDescS+"^"+studyBean.getRoomId()+"^"+studyBean.getStudyLocseqno()+"^";
				sysHisProc("I",info,studyBean.getOrgId());
			}
		}else if("yn".equals(isNew) ){
			//新增---若病人已存在則走yn
			Map seqs = getSeq(studyBean.getOrgId(),String.valueOf(studyBean.getLocId()));	 
			studyAccnumber = String.valueOf(seqs.get("studyAccnumber"));		
			//判断study表中是否存在记录，如果存在，更新标识为Y
			boolean isstudy = getStudyByAccnumber(studyAccnumber);
			if(isstudy){
				updateStudyMark(studyAccnumber,"N");
			}
			
			//更新病人信息，新增检查信息
			//更新病人基本信息
			patientGlobalid = patientBean.getPatientGlobalid();
			patientId = patientBean.getPatientId();
			studyLocseqno = String.valueOf(seqs.get("studyLocseqno"));
			AisPatientInfoBean oldPatientInfoBean = new AisPatientInfoBean();
			oldPatientInfoBean.setPatientGlobalid(patientBean.getPatientGlobalid());
			oldPatientInfoBean.setOrgId(studyBean.getOrgId());
			oldPatientInfoBean.setStsToOld();
			DataContainerFactory.copyNoClearData(patientBean, oldPatientInfoBean);			 
			AisPatientInfoEngine.save(oldPatientInfoBean);
			//检查信息
			studyInfoId = ServiceUtil.getSequence("SEQSTUDYINFOID");
			studyBean.setPatientGlobalid(patientBean.getPatientGlobalid());
			studyBean.setStudyinfoId(studyInfoId);
			studyBean.setStudyAccnumber(studyAccnumber);
			
			//判断有没有申请时间 
			studyBean.setStudyAppdate(dateTime);//申请时间
			studyBean.setStudyDatetime(Timestamp.valueOf(yuyueTime));//登记时间
			studyBean.setStudystatusCode("ARRIVE");
			studyBean.setStudyHaveimage(0);//无图片
			studyBean.setStudyHavereport(0);//无报告
			studyBean.setStudyOperationid(user.getOperatorId());//操作员		
			studyBean.setStudyCreatetime(dateTime);
			//检查类型	
			if(isConsult.equals("y")){
				studyBean.setStudyType(1);//远程会诊医嘱
				studyBean.setIsUrgent(0);
				studyBean.setStudyConsultstarttime(dateTime);//会诊时间
				//操作记录
//				studyBean.setStudyItemdesc("远程会诊");
//				studyItemDescS = "远程会诊";
				if("AppSave".equals(studyBean.getStudystatusCode())||!"".equals(studyBean.getStudystatusCode())){
					studyBean.setStudystatusCode("AppSave");								
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
				}else{
//					studyBean.setStudystatusCode("APPConsult");								
//					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
//					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",1);
				}
			}else{
				if(null != studyBean.getStudyConsultorg() && !"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg()) && !"undefined".equals(studyBean.getStudyConsultorg()) && !"null".equals(studyBean.getStudyConsultorg())){
					studyBean.setStudyType(1);//远程诊断医嘱
					studyBean.setIsUrgent(0);
					studyBean.setStudyConsultstarttime(dateTime);//诊断时间	
					studyBean.setStudystatusCode("AppSave");
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
				}else{
					studyBean.setStudyType(0);//检查医嘱
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
				}
			}		
			//检查项目   new方式
			if(arrayBodystrs!=null&&!"".equals(arrayBodystrs)){
				JSONArray json = JSONArray.fromObject(arrayBodystrs);
				if(json.size()>0){
					for(int i=0;i<json.size();i++){
					    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					    System.out.println() ;  // 得到 每个对象中的属性值
					    //检查登记部位ais_studyitem_info
					    AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
						bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
						bean.setStudyinfoId(studyBean.getStudyinfoId());
						String itemdesc = URLDecoder.decode(job.getString("itemdesc"),"UTF-8");
						String[] itemArray = itemdesc.split("_");
						bean.setStudyitemCode(itemArray[0]);
						bean.setStudyitemDesc(itemArray[1]);
						if(itemArray.length>2){
							bean.setStudyitemEndesc(itemArray[2]);
						}
						String bodyinfo = job.getString("itempart");
						String bodypart = URLDecoder.decode(job.getString("itempartname"),"UTF-8");
						bean.setStudyitemBodyinfo("".equals(bodypart)?"":bodypart.replace(",","、"));
						bean.setStudyitemBodypart("".equals(bodyinfo)?"":bodyinfo);
						bean.setStudyitemNumber(Integer.valueOf(job.getString("itemnum")));
						bean.setStudyitemPrice(Integer.valueOf(job.getString("itemprice")));
						bean.setStudyitemStatus("N"); //医嘱项目状态:N: 正常 , S: 停止					
						AisStudyItemInfoEngine.save(bean);		
						bodypartModality+=bodypartModality==""?bodypart:","+bodypart;
						studyItemDescS+=studyItemDescS==""?bean.getStudyitemDesc():","+bean.getStudyitemDesc();
						studyItemDescEnS+=studyItemDescEnS==""?bean.getStudyitemEndesc():","+bean.getStudyitemEndesc();
					}
					
				}
			}
			//检查序列号
			studyBean.setStudyLocseqno(Long.parseLong(studyLocseqno));
			studyBean.setStudyItemdesc(studyItemDescS);
			studyBean.setStudyEnitemdesc(studyItemDescEnS);
			AisStudyInfoEngine.save(studyBean);
			
			DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC",studyBean.getOrgId());
			if(dictBean!=null){
				String info = studyBean.getStudyHisappid()+"^"+patientId+"^"+studyBean.getPatientInpatientid()+"^"+studyBean.getPatientOutpatientid()+"^"
						+studyBean.getPatienttypeCode()+"^"+patientBean.getPatientName()+"^"+studyAccnumber+"^"+studyItemDescS+"^"+studyBean.getRoomId()+"^"+studyBean.getStudyLocseqno()+"^";
				sysHisProc("I",info,studyBean.getOrgId());
			}
		}else if("work2".equals(isNew)){
			Map seqs = getSeq(studyBean.getOrgId(),String.valueOf(studyBean.getLocId()));
			System.out.print("打印详细信息" + seqs);
			if("".equals(studyBean.getStudyAccnumber())){
				//从seq中获取
				studyAccnumber = String.valueOf(seqs.get("studyAccnumber"));
				studyBean.setStudyAccnumber(studyAccnumber);
				AisPatientInfoBean pabean = getPatientBywork2(patientBean);
				if(pabean!=null){
					AisPatientInfoBean oldPatientInfoBean = new AisPatientInfoBean();
					oldPatientInfoBean.setPatientGlobalid(pabean.getPatientGlobalid());
					oldPatientInfoBean.setOrgId(pabean.getOrgId());
					oldPatientInfoBean.setStsToOld();
					DataContainerFactory.copyNoClearData(pabean, oldPatientInfoBean);			 
					AisPatientInfoEngine.save(oldPatientInfoBean);
					patientGlobalid = pabean.getPatientGlobalid();
				}else{
					//新增
					patientGlobalid = Long.parseLong(String.valueOf(seqs.get("patientGlobalid")));
					//病人信息
					patientId = String.valueOf(seqs.get("patientId"));
					patientBean.setPatientId(patientId);
					patientBean.setOrgId(patientBean.getOrgId());
					patientBean.setPatientGlobalid(patientGlobalid);
					AisPatientInfoEngine.save(patientBean);
				}
				
				//检查信息
				studyInfoId = ServiceUtil.getSequence("SEQSTUDYINFOID");
				studyLocseqno = String.valueOf(seqs.get("studyLocseqno"));
				studyBean.setPatientGlobalid(patientGlobalid);
				studyBean.setStudyinfoId(studyInfoId);
				
				//判断有没有申请时间 
				studyBean.setStudyAppdate(dateTime);//申请时间
				studyBean.setStudyDatetime(Timestamp.valueOf(yuyueTime));//登记时间
				studyBean.setStudystatusCode("ARRIVE");
				studyBean.setStudyHaveimage(1);//无图片
				studyBean.setStudyHavereport(0);//无报告
				studyBean.setStudyOperationid(user.getOperatorId());//操作员		
				studyBean.setStudyCreatetime(dateTime);
				//检查类型	
				if(isConsult.equals("y")){
					studyBean.setStudyType(1);//远程会诊医嘱
					studyBean.setIsUrgent(0);
					studyBean.setStudyConsultstarttime(dateTime);//会诊时间
					//操作记录
//					studyBean.setStudyItemdesc("远程会诊");
//					studyItemDescS = "远程会诊";
					if("AppSave".equals(studyBean.getStudystatusCode())||!"".equals(studyBean.getStudystatusCode())){
						studyBean.setStudystatusCode("AppSave");								
						saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
						saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
					}else{
//						studyBean.setStudystatusCode("APPConsult");								
//						saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
//						saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",1);
					}
				}else{
					if(null != studyBean.getStudyConsultorg() && !"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg()) && !"undefined".equals(studyBean.getStudyConsultorg()) && !"null".equals(studyBean.getStudyConsultorg())){
						studyBean.setStudyType(1);//远程诊断医嘱
						studyBean.setIsUrgent(0);
						studyBean.setStudyConsultstarttime(dateTime);//诊断时间	
						studyBean.setStudystatusCode("AppSave");
						saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
						saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
					}else{
						studyBean.setStudyType(0);//检查医嘱
						saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"ARRIVE");
					}
				}
				//检查项目   new方式
				if(arrayBodystrs!=null&&!"".equals(arrayBodystrs)){
					JSONArray json = JSONArray.fromObject(arrayBodystrs);
					if(json.size()>0){
						for(int i=0;i<json.size();i++){
						    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
						    System.out.println() ;  // 得到 每个对象中的属性值
						    //检查登记部位ais_studyitem_info
						    AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
							bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
							bean.setStudyinfoId(studyBean.getStudyinfoId());
							String itemdesc = URLDecoder.decode(job.getString("itemdesc"),"UTF-8");
							String[] itemArray = itemdesc.split("_");
							bean.setStudyitemCode(itemArray[0]);
							bean.setStudyitemDesc(itemArray[1]);
							if(itemArray.length>2){
								bean.setStudyitemEndesc(itemArray[2]);
							}
							String bodyinfo = job.getString("itempart");
							String bodypart = URLDecoder.decode(job.getString("itempartname"),"UTF-8");
							bean.setStudyitemBodyinfo("".equals(bodypart)?"":bodypart.replace(",","、"));
							bean.setStudyitemBodypart("".equals(bodyinfo)?"":bodyinfo);
							bean.setStudyitemNumber(Integer.valueOf(job.getString("itemnum")));
							bean.setStudyitemPrice(Integer.valueOf(job.getString("itemprice")));
							bean.setStudyitemStatus("N"); //医嘱项目状态:N: 正常 , S: 停止					
							AisStudyItemInfoEngine.save(bean);		
							bodypartModality+=bodypartModality==""?bodypart:","+bodypart;
							studyItemDescS+=studyItemDescS==""?bean.getStudyitemDesc():","+bean.getStudyitemDesc();
							studyItemDescEnS+=studyItemDescEnS==""?bean.getStudyitemEndesc():","+bean.getStudyitemEndesc();
						}
						
					}
				}
				//检查序列号
				studyBean.setStudyLocseqno(Long.parseLong(studyLocseqno));
				//保存
				studyBean.setStudyEnitemdesc(studyItemDescEnS);
				studyBean.setStudyItemdesc(studyItemDescS);
				AisStudyInfoEngine.save(studyBean);
				//回写study检查号
				if(isNotBlank(patientkey)){
					updateAccnumber(patientkey,studyAccnumber,"Y");
				}
			}else{
				//根据检查号判断是否存在检查记录
				if(getStudyRecord(studyBean.getStudyAccnumber())){
					//检查病人是否存在，不存在，则新增；存在，更新
					studyAccnumber = studyBean.getStudyAccnumber();
					AisPatientInfoBean pabean = getPatientBywork2(patientBean);
					if(pabean!=null){
						AisPatientInfoBean oldPatientInfoBean = new AisPatientInfoBean();
						oldPatientInfoBean.setPatientGlobalid(pabean.getPatientGlobalid());
						oldPatientInfoBean.setOrgId(pabean.getOrgId());
						oldPatientInfoBean.setStsToOld();
						DataContainerFactory.copyNoClearData(pabean, oldPatientInfoBean);			 
						AisPatientInfoEngine.save(oldPatientInfoBean);
						patientGlobalid = pabean.getPatientGlobalid();
					}else{
						//新增
						patientGlobalid = Long.parseLong(String.valueOf(seqs.get("patientGlobalid")));
						//病人信息
						patientBean.setPatientId(patientBean.getPatientId());
						patientBean.setOrgId(patientBean.getOrgId());
						patientBean.setPatientGlobalid(patientGlobalid);
						AisPatientInfoEngine.save(patientBean);
					}
					
					//检查信息
					studyInfoId = ServiceUtil.getSequence("SEQSTUDYINFOID");
					studyLocseqno = String.valueOf(seqs.get("studyLocseqno"));
					studyBean.setPatientGlobalid(patientGlobalid);
					studyBean.setStudyinfoId(studyInfoId);
					
					//判断有没有申请时间 
					studyBean.setStudyAppdate(dateTime);//申请时间
					studyBean.setStudyDatetime(Timestamp.valueOf(yuyueTime));//登记时间
					studyBean.setStudystatusCode("ARRIVE");
					studyBean.setStudyHaveimage(1);//无图片
					studyBean.setStudyHavereport(0);//无报告
					studyBean.setStudyOperationid(user.getOperatorId());//操作员		
					studyBean.setStudyCreatetime(dateTime);
					//检查类型	
					if(isConsult.equals("y")){
						studyBean.setStudyType(1);//远程会诊医嘱
						studyBean.setIsUrgent(0);
						studyBean.setStudyConsultstarttime(dateTime);//会诊时间
						//操作记录
//						studyBean.setStudyItemdesc("远程会诊");
//						studyItemDescS = "远程会诊";
						if("AppSave".equals(studyBean.getStudystatusCode())||!"".equals(studyBean.getStudystatusCode())){
							studyBean.setStudystatusCode("AppSave");								
							saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
							saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
						}else{
//							studyBean.setStudystatusCode("APPConsult");								
//							saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
//							saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",1);
						}
					}else{
						if(null != studyBean.getStudyConsultorg() && !"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg()) && !"undefined".equals(studyBean.getStudyConsultorg()) && !"null".equals(studyBean.getStudyConsultorg())){
							studyBean.setStudyType(1);//远程诊断医嘱
							studyBean.setIsUrgent(0);
							studyBean.setStudyConsultstarttime(dateTime);//诊断时间	
							studyBean.setStudystatusCode("AppSave");
							saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
							saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",1);
						}else{
							studyBean.setStudyType(0);//检查医嘱
							saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"ARRIVE");
						}
					}
					//检查项目   new方式
					if(arrayBodystrs!=null&&!"".equals(arrayBodystrs)){
						JSONArray json = JSONArray.fromObject(arrayBodystrs);
						if(json.size()>0){
							for(int i=0;i<json.size();i++){
							    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
							    System.out.println() ;  // 得到 每个对象中的属性值
							    //检查登记部位ais_studyitem_info
							    AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
								bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
								bean.setStudyinfoId(studyBean.getStudyinfoId());
								String itemdesc = URLDecoder.decode(job.getString("itemdesc"),"UTF-8");
								String[] itemArray = itemdesc.split("_");
								bean.setStudyitemCode(itemArray[0]);
								bean.setStudyitemDesc(itemArray[1]);
								if(itemArray.length>2){
									bean.setStudyitemEndesc(itemArray[2]);
								}
								String bodyinfo = job.getString("itempart");
								String bodypart = URLDecoder.decode(job.getString("itempartname"),"UTF-8");
								bean.setStudyitemBodyinfo("".equals(bodypart)?"":bodypart.replace(",","、"));
								bean.setStudyitemBodypart("".equals(bodyinfo)?"":bodyinfo);
								bean.setStudyitemNumber(Integer.valueOf(job.getString("itemnum")));
								bean.setStudyitemPrice(Integer.valueOf(job.getString("itemprice")));
								bean.setStudyitemStatus("N"); //医嘱项目状态:N: 正常 , S: 停止					
								AisStudyItemInfoEngine.save(bean);		
								bodypartModality+=bodypartModality==""?bodypart:","+bodypart;
								studyItemDescS+=studyItemDescS==""?bean.getStudyitemDesc():","+bean.getStudyitemDesc();
								studyItemDescEnS+=studyItemDescEnS==""?bean.getStudyitemEndesc():","+bean.getStudyitemEndesc();
							}
							
						}
					}
					//检查序列号
					studyBean.setStudyLocseqno(Long.parseLong(studyLocseqno));
					//保存
					studyBean.setStudyEnitemdesc(studyItemDescEnS);
					studyBean.setStudyItemdesc(studyItemDescS);
					AisStudyInfoEngine.save(studyBean);
					//有检查记录状态更新为N
					updateStudyMark(studyAccnumber,"Y");
				}else{
					map.put("flag","ture");
				}
			}
		}else{
			//更新病人基本信息
			patientGlobalid = patientBean.getPatientGlobalid();
			patientId = patientBean.getPatientId();
			AisPatientInfoBean oldPatientInfoBean = new AisPatientInfoBean();
			oldPatientInfoBean.setPatientGlobalid(patientBean.getPatientGlobalid());
			oldPatientInfoBean.setOrgId(studyBean.getOrgId());
			oldPatientInfoBean.setStsToOld();
			DataContainerFactory.copyNoClearData(patientBean, oldPatientInfoBean);			 
			AisPatientInfoEngine.save(oldPatientInfoBean);
			
			//检查类型	
			if(isConsult.equals("y")){
				studyBean.setStudyType(1);//远程会诊医嘱
				studyBean.setStudyConsultstarttime(dateTime);//会诊时间
				//操作记录
//				studyBean.setStudyItemdesc("远程会诊");
//				studyItemDescS = "远程会诊";
				if("AppSave".equals(studyBean.getStudystatusCode())){
					studyBean.setStudystatusCode("AppSave");								
					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave");
					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"AppSave",2);
				}else{
//					studyBean.setStudystatusCode("APPConsult");								
//					saveStudyhistoryinfo(String.valueOf(studyInfoId),user.getOperatorId(),"APPConsult");
//					saveStudyMessage(String.valueOf(studyInfoId),user.getOperatorId(),"APP",2);
				}
				
			}else{
				if(null != studyBean.getStudyConsultorg() && !"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg()) && !"undefined".equals(studyBean.getStudyConsultorg()) && !"null".equals(studyBean.getStudyConsultorg())){
					studyBean.setStudyType(1);//远程诊断医嘱
					studyBean.setStudystatusCode("AppSave");						
					studyBean.setStudyConsultstarttime(dateTime);//诊断时间
					saveStudyhistoryinfo(String.valueOf(oldStudyinfoId),user.getOperatorId(),"AppSave");
					saveStudyMessage(String.valueOf(oldStudyinfoId),user.getOperatorId(),"APP",1);
				}else{
					studyBean.setStudyType(0);//检查医嘱
					saveStudyhistoryinfo(String.valueOf(oldStudyinfoId),user.getOperatorId(),"APP");
				}
			}		
			//更新检查信息
			studyAccnumber = studyBean.getStudyAccnumber();
			AisStudyInfoBean oldStudyInfoBean = new AisStudyInfoBean();
			oldStudyInfoBean.setStudyinfoId(Long.parseLong(oldStudyinfoId));
			oldStudyInfoBean.setStsToOld();
			//oldStudyInfoBean.setStudyAppdate(dateTime);//申请时间
			

			if(yuyueTime!=null && !(yuyueTime.equals(""))){	
		        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		        java.util.Date date = format1.parse(yuyueTime); 
		        
		        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		        String sdate=format2.format(date);		
		        Timestamp timestamp=Timestamp.valueOf(sdate);
		        oldStudyInfoBean.setStudyDatetime(timestamp);//登记时间
			}
			
			
			DataContainerFactory.copyNoClearData(studyBean, oldStudyInfoBean);
			oldStudyInfoBean.setStudyCreatetime(dateTime);
			studyInfoId = Long.parseLong(oldStudyinfoId);
						
			//更新检查项目信息 删除所有，重新添加
			String sql = "SELECT * FROM AIS_STUDYITEMINFO WHERE STUDYINFO_ID = " + oldStudyinfoId;
			AisStudyItemInfoBean[] studyItemInfoBeans = AisStudyItemInfoEngine.getBeansFromSql(sql, null); 
			for(AisStudyItemInfoBean bean : studyItemInfoBeans){
				bean.setStsToOld();
				bean.delete();	 
			}
			AisStudyItemInfoEngine.save(studyItemInfoBeans);
			//检查项目   new方式
			if(arrayBodystrs!=null&&!"".equals(arrayBodystrs)){
				JSONArray json = JSONArray.fromObject(arrayBodystrs);
				if(json.size()>0){
					for(int i=0;i<json.size();i++){
					    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					    System.out.println() ;  // 得到 每个对象中的属性值
					    //检查登记部位ais_studyitem_info
					    AisStudyItemInfoBean bean = new AisStudyItemInfoBean();
						bean.setStudyitemId(ServiceUtil.getSequence("SEQSTUDYITEMID"));
						bean.setStudyinfoId(oldStudyInfoBean.getStudyinfoId());
						String itemdesc = URLDecoder.decode(job.getString("itemdesc"),"UTF-8");
						String[] itemArray = itemdesc.split("_");
						bean.setStudyitemCode(itemArray[0]);
						bean.setStudyitemDesc(itemArray[1]);
						if(itemArray.length>2){
							bean.setStudyitemEndesc(itemArray[2]);
						}
						String bodyinfo = job.getString("itempart");
						String bodypart = URLDecoder.decode(job.getString("itempartname"),"UTF-8");
						bean.setStudyitemBodyinfo("".equals(bodypart)?"":bodypart.replace(",","、"));
						bean.setStudyitemBodypart("".equals(bodyinfo)?"":bodyinfo);
						bean.setStudyitemNumber(Integer.valueOf(job.getString("itemnum")));
						String itemprice = "".equals(job.getString("itemprice"))?"0":job.getString("itemprice");
						bean.setStudyitemPrice(Integer.valueOf(itemprice));
						bean.setStudyitemStatus("N"); //医嘱项目状态:N: 正常 , S: 停止					
						AisStudyItemInfoEngine.save(bean);		
						bodypartModality+=bodypartModality==""?bodypart:","+bodypart;
						studyItemDescS+=studyItemDescS==""?bean.getStudyitemDesc():","+bean.getStudyitemDesc();
						studyItemDescEnS+=studyItemDescEnS==""?bean.getStudyitemEndesc():","+bean.getStudyitemEndesc();
					}
					
				}
			}
			oldStudyInfoBean.setStudyOperationid(user.getOperatorId());//操作员
			oldStudyInfoBean.setStudyItemdesc(studyItemDescS);
			oldStudyInfoBean.setStudyEnitemdesc(studyItemDescEnS);
			AisStudyInfoEngine.save(oldStudyInfoBean);
		
			DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC",studyBean.getOrgId());
			if(dictBean!=null){
				String info = studyBean.getStudyHisappid()+"^"+patientId+"^"+studyBean.getPatientInpatientid()+"^"+studyBean.getPatientOutpatientid()+"^"
						+studyBean.getPatienttypeCode()+"^"+patientBean.getPatientName()+"^"+studyAccnumber+"^"+studyItemDescS+"^"+studyBean.getRoomId()+"^"+studyBean.getStudyLocseqno()+"^";
				sysHisProc("U",info,studyBean.getOrgId());
			}
		}	 
		
		//会诊申请不写AisModalityworklist表
		if(!isConsult.equals("y") ){
			AiscEquipmentBean eqiBean = AiscEquipmentEngine.getBean(studyBean.getEquipmentId());
			if(eqiBean.getModalityWorklist()==1){
				if(("y".equals(isNew) || "yn".equals(isNew))){
					//设备工作列表
					AisModalityworklistBean amwork = new AisModalityworklistBean();
					amwork.setWmlid(ServiceUtil.getSequence("SEQWMLID"));
					amwork.setAccessionNumber(studyAccnumber);
					amwork.setOrgId(studyBean.getOrgId());
					amwork.setInstitutionName(studyBean.getOrgId());
					//amwork.setPatientName(patientBean.getPatientNamepy());
					amwork.setPatientId(patientBean.getPatientId());			     
			        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			        if(null != patientBean.getPatientDob() &&!"".equals(patientBean.getPatientDob())){
			        	amwork.setPatientBirthdate(String.valueOf(dateFormat.format(patientBean.getPatientDob())));
			        }			
					amwork.setPatientSex(patientBean.getPatientSex());
					
	//				amwork.setStudyInstanceUid(String.valueOf(ServiceUtil.getSequence("SEQWMLUID")));
					amwork.setStudyInstanceUid(getStudyInstanceUid(studyBean.getOrgId())); //STUYUID生成
					
					amwork.setRequestedProceduredescription(studyItemDescS);//检查描述			
					//检查类型			
					if(!"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg())){
						//amwork.setModality("1");//检查类型			
	//					studyBean.setStudyConsultstarttime(dateTime);//会诊时间	
					}
					if(studyBean.getEquipmentId()!=-1){
						try {
							AiscEquipmentBean ebean = AiscEquipmentEngine.getBean(studyBean.getEquipmentId());
							amwork.setScheduledStationName(ebean.getEquipmentEndesc()==null?"":ebean.getEquipmentEndesc());
							DictItemModel modality = dictSV.getDictItem("AISC_MODALITY",String.valueOf(ebean.getModalityId()));
							if(modality != null){
								amwork.setModality(modality.getItemName());  
								
							}
						} catch (Exception e) {
							logger.debug("检查终端编码转换失败！");
						}
					}else{
						amwork.setModality("");  
					}
					//根据检查设备查Dicom AE
					if(!"-1".equals(studyBean.getEquipmentId()) && !"".equals(studyBean.getStudyConsultorg())){
						 AiscEquipmentBean ebean = AiscEquipmentEngine.getBean(studyBean.getEquipmentId());
						 if(ebean != null){
							 amwork.setScheduledStationAeTitle(ebean.getEquipmentAe());  //预约计算机AE TITLE
							 
							 String sex = patientBean.getPatientSex().equals("1")?"M":"F";
							 String age =  "";
							 if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("岁")){
								 String ageName = "Y";
								 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
							 }else if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("月")){
								 String ageName = "M";
								 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
							 }else if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("天")){
								 String ageName = "D";
								 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
							 }else{
								 String ageName = "H";
								 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-2)+ageName;
							 }
							 amwork.setPatientAge(age);
							//首先支持中文 
							 if(ebean.getModalitySupportchinese()==1){
								 //支持体检--支持年龄性别
								 if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==1){
									 String number = "";
									 if(studyBean.getPatienttypeCode().equals("OP")){
										 if(isNotBlank(studyBean.getPatientOutpatientid())){
											 number = "^"+studyBean.getPatientOutpatientid();
										 }
									 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
										 if(isNotBlank(studyBean.getPatientInpatientid())){
											 number = "^"+studyBean.getPatientInpatientid();
										 }
									 }
									 amwork.setPatientName(patientBean.getPatientName()+" "+age+" "+sex+number); 
								 }else if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==0){
									 String number = "";
									 if(studyBean.getPatienttypeCode().equals("OP")){
										 if(isNotBlank(studyBean.getPatientOutpatientid())){
											 number = "^"+studyBean.getPatientOutpatientid();
										 }
									 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
										 if(isNotBlank(studyBean.getPatientInpatientid())){
											 number = "^"+studyBean.getPatientInpatientid();
										 }
									 }
									 amwork.setPatientName(patientBean.getPatientName()+number); 
								 }else if(ebean.getModalitySupportid()==0&&ebean.getModalitySupportsexage()==1){
									 amwork.setPatientName(patientBean.getPatientName()+" "+age+" "+sex); 
								 }else{
									 amwork.setPatientName(patientBean.getPatientName());
								 }
								 amwork.setScheduledProceduredescription(studyItemDescS);
							 }else{
								 String number = "";
								 if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==1){
									 if(studyBean.getPatienttypeCode().equals("OP")){
										 if(isNotBlank(studyBean.getPatientOutpatientid())){
											 number = "^"+studyBean.getPatientOutpatientid();
										 }
									 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
										 if(isNotBlank(studyBean.getPatientInpatientid())){
											 number = "^"+studyBean.getPatientInpatientid();
										 }
									 }
									 amwork.setPatientName(patientBean.getPatientNamepy()+" "+age+" "+sex+number); 
								 }else if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==0){
									 if(studyBean.getPatienttypeCode().equals("OP")){
										 if(isNotBlank(studyBean.getPatientOutpatientid())){
											 number = "^"+studyBean.getPatientOutpatientid();
										 }
									 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
										 if(isNotBlank(studyBean.getPatientInpatientid())){
											 number = "^"+studyBean.getPatientInpatientid();
										 }
									 }
									 amwork.setPatientName(patientBean.getPatientNamepy()+number); 
								 }else if(ebean.getModalitySupportid()==0&&ebean.getModalitySupportsexage()==1){
									 amwork.setPatientName(patientBean.getPatientNamepy()+" "+age+" "+sex); 
								 }else{
									 amwork.setPatientName(patientBean.getPatientNamepy());
								 }
								 amwork.setScheduledProceduredescription(studyItemDescEnS);
							 }
						 }
					} 	
					amwork.setScheduledProcedureStepDate(studyBean.getStudyDatetime());//预约开始执行的日期
					amwork.setStatus("0");
					AisModalityworklistEngine.save(amwork);
				}else{
					StringBuffer condition = new StringBuffer();
				    condition.append(" 1=1");
				    if (isNotBlank(studyAccnumber)  ) {
				    	condition.append(" AND Accession_Number = '" + studyAccnumber + "'" ); 
			        } 	    
				    AisModalityworklistBean[] mwlBeans = AisModalityworklistEngine.getBeans(condition.toString(), null);    
					if(mwlBeans.length>0){
						AisModalityworklistBean oldBean = mwlBeans[0];
						//设备工作列表
						AisModalityworklistBean amwork = new AisModalityworklistBean();
						amwork.setWmlid(oldBean.getWmlid());
						amwork.setStsToOld(); 
						amwork.setAccessionNumber(studyAccnumber);
						amwork.setOrgId(studyBean.getOrgId());
						amwork.setInstitutionName(studyBean.getOrgId());
						//oldBean.setPatientName(patientBean.getPatientNamepy());
						amwork.setPatientId(patientBean.getPatientId());
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	        
						amwork.setPatientBirthdate(String.valueOf(dateFormat.format(patientBean.getPatientDob())));
						amwork.setPatientSex(patientBean.getPatientSex());
						//amwork.setStudyInstanceUid(String.valueOf(ServiceUtil.getSequence("SEQWMLUID")));
						amwork.setRequestedProceduredescription(studyItemDescS);//检查描述		
						//检查类型			
						if(!"-1".equals(studyBean.getStudyConsultorg()) && !"".equals(studyBean.getStudyConsultorg())){
							//oldBean.setModality("1");//检查类型
	//						studyBean.setStudyConsultstarttime(dateTime);//会诊时间	
						} 
						if(studyBean.getEquipmentId()!=-1){
							try {
								AiscEquipmentBean ebean = AiscEquipmentEngine.getBean(studyBean.getEquipmentId());
								amwork.setScheduledStationName(ebean.getEquipmentEndesc()==null?"":ebean.getEquipmentEndesc());
								DictItemModel modality = dictSV.getDictItem("AISC_MODALITY",String.valueOf(ebean.getModalityId()));
								if(modality != null){
									amwork.setModality(modality.getItemName());  
								}
							} catch (Exception e) {
								logger.debug("检查终端编码转换失败！");
							}
						}else{
							amwork.setModality("");
						}
						  
						//根据检查设备查Dicom AE
						if(!"-1".equals(studyBean.getEquipmentId()) && !"".equals(studyBean.getStudyConsultorg())){
							 AiscEquipmentBean ebean = AiscEquipmentEngine.getBean(studyBean.getEquipmentId());
							 if(ebean != null){
								 amwork.setScheduledStationAeTitle(ebean.getEquipmentAe());  //预约计算机AE TITLE
								 
								 String sex = patientBean.getPatientSex().equals("1")?"M":"F";
								 String age =  "";
								 if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("岁")){
									 String ageName = "Y";
									 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
								 }else if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("月")){
									 String ageName = "M";
									 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
								 }else if(patientBean.getPatientAge().substring(patientBean.getPatientAge().length()-1).equals("天")){
									 String ageName = "D";
									 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-1)+ageName;
								 }else{
									 String ageName = "H";
									 age = patientBean.getPatientAge().substring(0,patientBean.getPatientAge().length()-2)+ageName;
								 }
								 amwork.setPatientAge(age);
								 if(ebean.getModalitySupportchinese()==1){
									 if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==1){
										 String number = "";
										 if(studyBean.getPatienttypeCode().equals("OP")){
											 if(isNotBlank(studyBean.getPatientOutpatientid())){
												 number = "^"+studyBean.getPatientOutpatientid();
											 }
										 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
											 if(isNotBlank(studyBean.getPatientInpatientid())){
												 number = "^"+studyBean.getPatientInpatientid();
											 }
										 }
										 amwork.setPatientName(patientBean.getPatientName()+" "+age+" "+sex+number); 
									 }else if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==0){
										 String number = "";
										 if(studyBean.getPatienttypeCode().equals("OP")){
											 if(isNotBlank(studyBean.getPatientOutpatientid())){
												 number = "^"+studyBean.getPatientOutpatientid();
											 }
										 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
											 if(isNotBlank(studyBean.getPatientInpatientid())){
												 number = "^"+studyBean.getPatientInpatientid();
											 }
										 }
										 amwork.setPatientName(patientBean.getPatientName()+number); 
									 }else if(ebean.getModalitySupportid()==0&&ebean.getModalitySupportsexage()==1){
										 amwork.setPatientName(patientBean.getPatientName()+" "+age+" "+sex); 
									 }else{
										 amwork.setPatientName(patientBean.getPatientName());
									 }
									 amwork.setScheduledProceduredescription(studyItemDescS);
								 }else{
									 String number = "";
									 if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==1){
										 if(studyBean.getPatienttypeCode().equals("OP")){
											 if(isNotBlank(studyBean.getPatientOutpatientid())){
												 number = "^"+studyBean.getPatientOutpatientid();
											 }
										 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
											 if(isNotBlank(studyBean.getPatientInpatientid())){
												 number = "^"+studyBean.getPatientInpatientid();
											 }
										 }
										 amwork.setPatientName(patientBean.getPatientNamepy()+" "+age+" "+sex+number); 
									 }else if(ebean.getModalitySupportid()==1&&ebean.getModalitySupportsexage()==0){
										 if(studyBean.getPatienttypeCode().equals("OP")){
											 if(isNotBlank(studyBean.getPatientOutpatientid())){
												 number = "^"+studyBean.getPatientOutpatientid();
											 }
										 }else if(studyBean.getPatienttypeCode().equals("HP")||studyBean.getPatienttypeCode().equals("INP")){
											 if(isNotBlank(studyBean.getPatientInpatientid())){
												 number = "^"+studyBean.getPatientInpatientid();
											 }
										 }
										 amwork.setPatientName(patientBean.getPatientNamepy()+number); 
									 }else if(ebean.getModalitySupportid()==0&&ebean.getModalitySupportsexage()==1){
										 amwork.setPatientName(patientBean.getPatientNamepy()+" "+age+" "+sex); 
									 }else{
										 amwork.setPatientName(patientBean.getPatientNamepy());
									 }
									 amwork.setScheduledProceduredescription(studyItemDescEnS);
								 }
							 }
							 
						} 	
						amwork.setStatus("0");
						amwork.setScheduledProcedureStepDate(Timestamp.valueOf(yuyueTime));//预约开始执行的日期
						AisModalityworklistEngine.save(amwork);
					}			
				} 
			}		
		}
		map.put("studyInfoId", studyInfoId);
		map.put("patientGlobalid", patientGlobalid);
		map.put("patientId", patientId);
		map.put("studyAccnumber", studyAccnumber);
		return map; 
	}
	
	private void sysHisProc(String status,String info,String orgId){
		Connection conn = null;
		try {			 
		     conn = com.ai.aris.util.DAOUtils.getDBConnection(orgId,"");
		     CallableStatement c=conn.prepareCall("{call p_setRegInfo_t(?,?)}");
		     c.setString(1,status);
		     c.setString(2,info);
	         c.execute();
	         
		} catch (SQLException se) {
			logger.info("同步his proc error:"
							+ se.toString());
			throw new com.ai.aris.util.DAOException(se.getMessage(), se);
		} finally {
			com.ai.aris.util.DAOUtils.closeConnection(conn);
		} 
	}
		
	//操作日志记录
	public void saveStudyhistoryinfo(String studyInfoId,String getOperatorId,String status) {
		Timestamp dateTime=new Timestamp(new Date().getTime());
		try { 
            	AisStudyHistoryInfoBean studyHisBean = new AisStudyHistoryInfoBean();
            	studyHisBean.setStudyhistoryinfoId(ServiceUtil.getSequence("SEQ_AIS_STUDYHISTORYINFO"));
            	studyHisBean.setStudyDate(dateTime);
            	studyHisBean.setStudyinfoId(Long.parseLong(studyInfoId));
            	studyHisBean.setStudyUserid(getOperatorId);
            	studyHisBean.setStudystatusCode(status);
            	AisStudyHistoryInfoEngine.save(studyHisBean); 
			
		} catch (Exception e) {
			logger.error("PatientRegSVImpl--saveStudyhistoryinfo:", e); 
		}	
	}
	
	//消息队列记录
	public void saveStudyMessage(String studyInfoId,String getOperatorId,String status,long studyType) {
		Timestamp dateTime=new Timestamp(new Date().getTime());
		try { 
			AisStudyMessageBean[] studyMessage = new AisStudyMessageEngine().getBeans(" STUDYINFO_ID = "+studyInfoId, null);
            if(studyMessage != null && studyMessage.length>0){
            	//已审核状态才能变更为 已读状态
            	if(status.equals("READ")){
            		if(studyMessage[0].getStatusCode().equals("VERIFY")){
            			studyMessage[0].setStatusCode(status);
                    	studyMessage[0].setStudyType(String.valueOf(studyType));
                    	studyMessage[0].setMessageDestuserid(getOperatorId);
                    	studyMessage[0].setStudyDate(dateTime);  		  
          			    AisStudyMessageEngine.save(studyMessage[0]); 
            		}
            	}else{
            		studyMessage[0].setStatusCode(status);
                	studyMessage[0].setStudyType(String.valueOf(studyType));
                	studyMessage[0].setMessageDestuserid(getOperatorId);
                	studyMessage[0].setStudyDate(dateTime);  		  
      			    AisStudyMessageEngine.save(studyMessage[0]);           	
            	}
            }else{
            	AisStudyMessageBean studyMsg = new AisStudyMessageBean();
            	studyMsg.setMessageId(ServiceUtil.getSequence("SEQ_AIS_STUDYMESSAGE"));
            	studyMsg.setStudyinfoId(Long.parseLong(studyInfoId));
            	studyMsg.setStatusCode(status);
            	studyMsg.setStudyType(String.valueOf(studyType));
            	studyMsg.setMessageDestuserid(getOperatorId);
            	studyMsg.setStudyDate(dateTime);
            	AisStudyMessageEngine.save(studyMsg);
            }
			
		} catch (Exception e) {
			logger.error("PatientRegSVImpl--saveStudyhistoryinfo:", e); 
		}	
	}
		
	//登记信息查询
	public QryRegInfoBean getRegInfo(String studyinfoId) throws Exception {

		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyinfoId)  ) {
	    	condition.append(" AND STUDYINFO_ID = " + studyinfoId ); 
        } 	    
	    QryRegInfoBean[] regInfoBeans = QryRegInfoEngine.getBeans(condition.toString(), null);    
	    if(regInfoBeans.length > 0){
	    	for(QryRegInfoBean regInfoBean : regInfoBeans){
	    		regInfoBean.setStudyDoctorid(getCareProvInfo(regInfoBean.getStudyDoctorid()));
	    		regInfoBean.setAidDoctorid(getCareProvInfo(regInfoBean.getAidDoctorid()));
	    	}
	    	return regInfoBeans[0]; 
	    }else{
	    	return new QryRegInfoBean();
	    } 
	}
	
	public boolean getStudyRecord(String studyAccnumber) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyAccnumber)  ) {
	    	condition.append(" AND study_accnumber = '" + studyAccnumber+"'" ); 
        } 
	    AisStudyInfoBean[] beans = AisStudyInfoEngine.getBeans(condition.toString(), null);  
	    if(beans.length > 0){
	    	return false;
	    }
	    return true;
	}
	
	public AisPatientInfoBean getPatientBywork2(AisPatientInfoBean patient) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");
	    if (isNotBlank(patient.getPatientId())){
	    	condition.append(" AND patient_id = '" + patient.getPatientId()+"'" ); 
        }
	    if (isNotBlank(patient.getOrgId())){
	    	condition.append(" AND org_id = '" + patient.getOrgId()+"'" ); 
        }
	    if(isNotBlank(patient.getPatientName())){
	    	condition.append(" AND patient_name = '" + patient.getPatientName()+"'" ); 
	    }
	    AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(), null);  
	    if(beans.length > 0){
	    	return beans[0];
	    }
	    return null;
	}
	
	//修改会诊报告状态
	public boolean updateAisStudyMessage(String patientGlobalid,String orgId) throws Exception{
		boolean isSuccess = true;
		try{
			Map map = new HashMap();
			StringBuffer condition = new StringBuffer(" 1=1");
			if(StringUtils.isNotBlank(patientGlobalid)){
				condition.append(" AND PATIENT_GLOBALID='" +patientGlobalid +"'");
			}
			AisStudyInfoBean [] aisStudyInfoBean = AisStudyInfoEngine.getBeans(condition.toString(), null);
			StringBuffer conditionBuffer2 = new StringBuffer(" 1=1");
			if(aisStudyInfoBean != null && aisStudyInfoBean.length>0 ){
				if(StringUtils.isNotBlank(String.valueOf(aisStudyInfoBean[0].getStudyinfoId()))){
					conditionBuffer2.append(" AND STUDYINFO_ID='"+aisStudyInfoBean[0].getStudyinfoId()+"'");
					QryStudyInfoListBean []qryStudyInfoListBeans = QryStudyInfoListEngine.getBeans(conditionBuffer2.toString(), null);
					AisStudyMessageBean [] aisStudyMessageBeans = AisStudyMessageEngine.getBeans(conditionBuffer2.toString(), null);
					if(qryStudyInfoListBeans != null && qryStudyInfoListBeans.length>0  && aisStudyMessageBeans!=null && aisStudyMessageBeans.length>0){
						/*if(!qryStudyInfoListBeans[0].getOrgId().equals(orgId)){
							StringBuffer conditionBuffer = new StringBuffer(" 1=1");
				    		if(StringUtils.isNotBlank(String.valueOf(aisStudyInfoBean[0].getStudyinfoId()))){
				    			conditionBuffer.append(" AND STUDYINFO_ID='"+aisStudyInfoBean[0].getStudyinfoId()+"'");
				    			AisStudyMessageBean [] aisStudyMessageBeans2 =  AisStudyMessageEngine.getBeans(conditionBuffer.toString(), null);
				    			if(aisStudyMessageBeans2 != null && aisStudyMessageBeans2.length>0){
				    				AisStudyMessageBean aisStudyMessageBean = new AisStudyMessageBean();
				    				aisStudyMessageBean = aisStudyMessageBeans2[0];
				    				aisStudyMessageBean.setStatusCode("READ");
				    				AisStudyMessageEngine.save(aisStudyMessageBean);
				    			}
				    		}
						}*/
						if(qryStudyInfoListBeans[0].getOrgId().equals(orgId) && aisStudyMessageBeans[0].getStatusCode().equals("VERIFY")){
							StringBuffer conditionBuffer = new StringBuffer(" 1=1");
				    		if(StringUtils.isNotBlank(String.valueOf(aisStudyInfoBean[0].getStudyinfoId()))){
				    			conditionBuffer.append(" AND STUDYINFO_ID='"+aisStudyInfoBean[0].getStudyinfoId()+"'");
				    			AisStudyMessageBean [] aisStudyMessageBeans2 =  AisStudyMessageEngine.getBeans(conditionBuffer.toString(), null);
				    			if(aisStudyMessageBeans2 != null && aisStudyMessageBeans2.length>0){
				    				AisStudyMessageBean aisStudyMessageBean = new AisStudyMessageBean();
				    				aisStudyMessageBean = aisStudyMessageBeans2[0];
				    				aisStudyMessageBean.setStatusCode("READ");
				    				AisStudyMessageEngine.save(aisStudyMessageBean);
				    			}
				    		}
						}
					}
				}
			}
		}catch(Exception e){
			isSuccess = false;
		}
		return isSuccess;
	}
	
	//根据医生编码查医姓名
	public String getCareProvInfo(String careProvCode) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(careProvCode)) {
            condition.append(" and careprov_id = '" + careProvCode + "'");
        }else{
        	return "";
        }	    
	    AiscCareProvBean[] beans = AiscCareProvEngine.getBeans(condition.toString(),null) ;
	    if(beans.length>0){
	    	 return beans[0].getCareprovName();
	    }else{
	    	return "";
	    }	   
	}
	
	//病人基本信息查询
	public AisPatientInfoBean getPatientInfo(String patientGlobalid) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(patientGlobalid)) {
            condition.append(" and PATIENT_GLOBALID = '" + patientGlobalid + "'");
        }else{
        	return null;
        }	    
	    AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(),null) ;
	    if(beans.length>0){
	    	 return beans[0];
	    }else{
	    	return null;
	    }	   
	}
	//检查信息查询
	public AisStudyInfoBean getStudyInfoBean(String studyinfoId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyinfoId)) {
            condition.append(" and STUDYINFO_ID = '" + studyinfoId + "'");
        }else{
        	return null;
        }	    
	    AisStudyInfoBean[] beans = AisStudyInfoEngine.getBeans(condition.toString(),null) ;
	    if(beans.length>0){
	    	 return beans[0];
	    }else{
	    	return null;
	    }	   
	}
	//检查项目查询
	public AisStudyItemInfoBean[] getStudyItemInfoBean(String studyinfoId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyinfoId)) {
            condition.append(" and STUDYINFO_ID = '" + studyinfoId + "'");
        }else{
        	return null;
        }	    
	    AisStudyItemInfoBean[] beans = AisStudyItemInfoEngine.getBeans(condition.toString(),null) ;
	    return beans;	   
	}
	//根据病人相关信息统计病人检查记录数据
	public AisPatientInfoBean[] countPatient(AisPatientInfoBean patientInfoBean)
			throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(patientInfoBean.getPatientName())) {
            condition.append(" and PATIENT_NAME like '%" + java.net.URLDecoder.decode(patientInfoBean.getPatientName(), "UTF-8") + "%'");
        }else{
        	return null;
        }	    
	    AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}
	//查病人记录列表
	public ResultDTO getPatientList(AisPatientInfoBean patientInfoBean,
			ResultDTO resultDTO) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
         
        if (isNotBlank(patientInfoBean.getPatientName()) ) {
            condition.append(" and patient_name like '%" + java.net.URLDecoder.decode(patientInfoBean.getPatientName(), "UTF-8") +"%'");
        }           

        int total = AisPatientInfoEngine.getBeansCount(condition.toString(), null);
        AisPatientInfoBean[] beans = null;

        if (total > 0) { 
            beans = AisPatientInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
             
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>(); 
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex"));
        resultDTO.setRows(BeanUtils.beanToList(beans,AisPatientInfoModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;	 
	} 
	
	public String getOrgName(String orgId) throws Exception {

		Statement stmt = null;
		ResultSet rs = null;
		String orgName = "";
		try {
			stmt = ServiceManager.getSession().getConnection()
					.createStatement();
			rs = stmt.executeQuery("select * from sys_org where org_id = "
					+ orgId + "");
			if (rs.next()) {
				orgName = rs.getString("ORG_NAME");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return orgName;
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
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
		return duns;
	}
	 
	
	//取消登记
	public void regCancel(long studyinfoId,String studyUserID) throws Exception{
		AisStudyHistoryInfoBean bean = new AisStudyHistoryInfoBean();
		bean.setStudyinfoId(studyinfoId);
		bean.setStudyUserid(studyUserID);
		bean.setStudystatusCode("Cancel");
		long id = ServiceUtil.getSequence("SEQ_AIS_STUDYHISTORYINFO");
		bean.setStudyhistoryinfoId(id);
		AisStudyHistoryInfoEngine.save(bean);
		StringBuffer sql = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		sql.append(" update ais_studyinfo set STUDYSTATUS_CODE='Cancel' where STUDYINFO_ID = :STUDYINFO_ID");
		param.put("STUDYINFO_ID", bean.getStudyinfoId());
		ServiceManager.getDataStore().execute(
				ServiceManager.getSession().getConnection(), sql.toString(),
				param);
		//删除Ais_Modalityworklist中记录
		StringBuffer sql2 = new StringBuffer();
		sql2.append(" delete from Ais_Modalityworklist where accession_number = (select study_accnumber from ais_studyinfo where studyinfo_id="+studyinfoId+" )");
		ServiceManager.getDataStore().execute(
				ServiceManager.getSession().getConnection(), sql2.toString(),
				param);
		
		AisStudyInfoBean studyBean = AisStudyInfoEngine.getBean(studyinfoId);
		AisPatientInfoBean patientBean = AisPatientInfoEngine.getBean(studyBean.getPatientGlobalid());
		DictItemModel dictBean = dictSV.getDictItem("IS_USEPROC",studyBean.getOrgId());
		if(dictBean!=null){
			String info = studyBean.getStudyHisappid()+"^"+patientBean.getPatientId()+"^"+studyBean.getPatientInpatientid()+"^"+studyBean.getPatientOutpatientid()+"^"
					+studyBean.getPatienttypeCode()+"^"+patientBean.getPatientName()+"^"+studyBean.getStudyAccnumber()+"^"+studyBean.getStudyItemdesc()+"^"+studyBean.getRoomId()+"^"+studyBean.getStudyLocseqno()+"^";
			sysHisProc("D",info,studyBean.getOrgId());
		}
	}
	
	public AisPatientInfoBean[] validatePatient(AisPatientInfoBean aisPatientInfo) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(aisPatientInfo.getPatientMobile())) {
            condition.append(" and patient_mobile='"+aisPatientInfo.getPatientMobile()+"'");
            AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(),null) ;
    	    return beans; 
        }
	    if (isNotBlank(aisPatientInfo.getPatientPhone())) {
            condition.append(" and patient_phone='"+aisPatientInfo.getPatientPhone()+"'");
            AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(),null) ;
    	    return beans; 
        }
	    if (isNotBlank(aisPatientInfo.getPatientIdnumber())) {
            condition.append(" and patient_idnumber='"+aisPatientInfo.getPatientIdnumber()+"'");
            AisPatientInfoBean[] beans = AisPatientInfoEngine.getBeans(condition.toString(),null) ;
    	    return beans; 
        }
	    return null;
	}
	//STUYUID生成
	public String getStudyInstanceUid(String orgId){
		
		String stuUid = "1.2.840.201306";
		Date date = new Date();       
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		String stuDate = dateFormat.format(date);
		String stuRanDom = getRandom(2);
        
		return stuUid + stuDate + orgId + stuRanDom;
	}
	
	public String getRandom(int len){
	   Random random = new Random();
	   String result="";
	    for(int i=0;i<len;i++){
              result+=random.nextInt(10);
        }
	    return result;
	}

	@Override
	public QryConsultOrgBean[] getConsultOrg(String orgId, String locId)
			throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and ORG_ID = "+orgId+" ");
        } 	
	    if (isNotBlank(locId)) {
            condition.append(" and LOC_ID = "+locId+" ");
        } 
	    QryConsultOrgBean[] beans = QryConsultOrgEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}	
	
	
	@Override
	public QryConsultLocBean[] getConsultLoc(String orgId,String locId)throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and CONORG_ID = "+orgId+" ");
        } 	
	    if (isNotBlank(locId)) {
            condition.append(" and LOC_ID = "+locId+" ");
        } 
	    QryConsultLocBean[] beans = QryConsultLocEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}	
	
	@Override
	public QryBigBodypartBean[] getBodypartTree(String orgId,String itemmastId,String bodypartDesc)throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and org_id = "+orgId+" ");
        } 	
	    if (isNotBlank(itemmastId)) {
            condition.append(" and itemmast_id = "+itemmastId+" ");
        } 
	    if (isNotBlank(bodypartDesc)&&!"搜索".equals(bodypartDesc)) {
	    	if(bodypartDesc.indexOf("，")!=-1){
	    		bodypartDesc = bodypartDesc.replace("，",",");
	    	}
	    	String arr[] = bodypartDesc.split(",");
	    	String sql = "";
	    	if(arr!=null&&arr.length>0){
		    	for(int i=0;i<arr.length;i++){
		    		sql+= " or bodypart_desc like '%"+arr[i]+"%' " ;
		    	}
	    	}
	    	sql = sql.substring(sql.indexOf("or")+2,sql.length()-1);
	    	condition.append(" and ("+sql+")");
        } 
	    condition.append(" order by bodypart_type asc,bodypart_order asc ");
	    QryBigBodypartBean[] beans = QryBigBodypartEngine.getBeans(condition.toString(),null) ;
	    return beans; 
	}
	
	@Override
	public String getReadCardInfo(String cardno,String chipSerialNo){
		try {
            StringBuilder request = new StringBuilder("");
            request = new StringBuilder();
            request.append("<Request>");
            request.append("<CardNo>"+cardno+"</CardNo>");
            request.append("<XpNo>"+chipSerialNo+"</XpNo>");
            request.append("<CardSeq></CardSeq>");
            request.append("</Request>");
            
            Service service = new Service();
            Call call = (Call) service.createCall();
            System.out.println(PropertiesUtils.getContextProperty("readCardUrl"));
            call.setTargetEndpointAddress(PropertiesUtils.getContextProperty("readCardUrl"));
            call.setOperationName(new QName("","queryCardBaseInfo"));
            Object[] param = {request.toString()};
            String ret = (String)call.invoke(param);
            System.out.println("收到信息...\n" + ret);
            return ret;
		} catch (Exception ex){
            ex.printStackTrace();
        }
		return "";
	}
	
	@Override
	public AisStudyItemInfoBean[] getItemInfo(String studyinfoId) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(studyinfoId)) {
            condition.append(" and studyinfo_id = "+studyinfoId);
        } 	
	    AisStudyItemInfoBean[] beans = AisStudyItemInfoEngine.getBeans(condition.toString(),null) ;
	    return beans;
	}
	
	@Override
	public void updateRecordStatus(String studyinfoId,String status) throws Exception{
		AisStudyInfoBean bean = AisStudyInfoEngine.getBean(Long.parseLong(studyinfoId));
		bean.setStsToOld();
	    bean.setStudystatusCode(status);
	    AisStudyInfoEngine.save(bean);
	}
	
	public String sendRealTimeData(String studyinfoId,String patientGlobalid) throws Exception{
		DictItemModel dictBean = dictSV.getDictItem("REAL_TIME_DATA","OPEN");
		if(dictBean!=null){
			//上传病人信息
			String sendPatientUrl = PropertiesUtils.getContextProperty("sendPatientUrl");
			String ip = PropertiesUtils.getContextProperty("sendData.host");
			String port = PropertiesUtils.getContextProperty("sendData.port");
			String orgId = PropertiesUtils.getContextProperty("sendOrg");
			String sendStudyinfoUrl = PropertiesUtils.getContextProperty("sendStudyinfoUrl");
			String sendStudyItemUrl = PropertiesUtils.getContextProperty("sendStudyItemUrl");
			String sendHzLocUrl = PropertiesUtils.getContextProperty("sendHzLocUrl");
			String saveFilePDFUrl = PropertiesUtils.getContextProperty("saveFilePDFUrl");
			String patientUrl = "http://"+ip+":"+port+sendPatientUrl;
			String studyUrl = "http://"+ip+":"+port+sendStudyinfoUrl;
			String studyitemUrl = "http://"+ip+":"+port+sendStudyItemUrl;
			String hzLocUrl = "http://"+ip+":"+port+sendHzLocUrl;
			String saveCenPdfUrl = "http://"+ip+":"+port+saveFilePDFUrl;
	
			RestTemplate restTemplate = ApplicationUtil.getBean("restTemplate",RestTemplate.class);
			HttpHeaders headers = new HttpHeaders();
			MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
			headers.setContentType(type);
			headers.add("Accept", MediaType.APPLICATION_JSON.toString());
			headers.add("orgId",orgId);
			StringBuffer condition = new StringBuffer();
			condition.append(" 1=1 and patient_globalid="+patientGlobalid);
			AisPatientInfoBean[] patient = AisPatientInfoEngine.getBeans(condition.toString(), null);
			AisPatientInfoData model = BeanUtils.beanToModel(patient[0],AisPatientInfoData.class);
			CustomObjectMapper objectMapper = new CustomObjectMapper();
			String json = objectMapper.writeValueAsString(model);
			HttpEntity<String> entity = new HttpEntity<String>(json, headers);
			ResponseEntity<String> responseEntity = restTemplate.postForEntity(patientUrl, entity, String.class);
			String responseBody = responseEntity.getBody();
			JSONObject jsonobject = JSONObject.fromObject(responseBody);
			PatientResponse response = (PatientResponse) JSONObject.toBean(jsonobject,PatientResponse.class);
			if("0".equals(response.getCode())){
				//上传检查记录信息
				StringBuffer condition1 = new StringBuffer();
				condition1.append(" 1=1 and studyinfo_id="+studyinfoId);
				AisStudyInfoBean[] studyinfo = AisStudyInfoEngine.getBeans(condition1.toString(), null);
				AisStudyInfoModel studymodel = BeanUtils.beanToModel(studyinfo[0],AisStudyInfoModel.class);
				String json1 = objectMapper.writeValueAsString(studymodel);
				HttpEntity<String> entityItem = new HttpEntity<String>(json1, headers);
				HttpEntity<String> entityStu = new HttpEntity<String>(json1, headers);
				ResponseEntity<String> responseEntityStu = restTemplate.postForEntity(studyUrl, entityStu, String.class);
				String responseBodyStu = responseEntityStu.getBody();
				JSONObject jsonobjectStu = JSONObject.fromObject(responseBodyStu);
				StudyinfoResponse responseStu = (StudyinfoResponse) JSONObject.toBean(jsonobjectStu,StudyinfoResponse.class);
				if("0".equals(responseStu.getCode())){
					//上传检查项目信息
					StringBuffer condition2 = new StringBuffer();
					condition2.append(" 1=1 and studyinfo_id="+studyinfoId);
					AisStudyItemInfoBean[] iteminfo = AisStudyItemInfoEngine.getBeans(condition2.toString(), null);
					List list = BeanUtils.beanToList(iteminfo,AisStudyItemInfoData.class);
					String json2 = objectMapper.writeValueAsString(list);
					entityItem = new HttpEntity<String>(json2, headers);
					ResponseEntity<String> responseEntityItem = restTemplate.postForEntity(studyitemUrl, entityItem, String.class);
					String responseBodyItem = responseEntityItem.getBody();
					JSONObject jsonobjectItem = JSONObject.fromObject(responseBodyItem);
					StudyinfoItemResponse responseItem = (StudyinfoItemResponse) JSONObject.toBean(jsonobjectItem,StudyinfoItemResponse.class);
					if("0".equals(responseItem.getCode())){
						//上传会诊科室
						HttpHeaders headers1 = new HttpHeaders();
						MediaType type1 = MediaType.parseMediaType("application/json; charset=UTF-8");
						headers1.setContentType(type1);
						headers1.add("Accept", MediaType.APPLICATION_JSON.toString());
						headers1.add("studyinfoId",String.valueOf(responseStu.getCenStudyinfoID()));
						headers1.add("orgId",orgId);
						headers1.add("conlocId",String.valueOf(studyinfo[0].getStudyConsultloc()));
						headers1.add("conorgId",studyinfo[0].getStudyConsultorg());
						headers1.add("locId",String.valueOf(studyinfo[0].getLocId()));
						StringBuffer condition3 = new StringBuffer();
						condition3.append(" 1=1 and loc_id="+studyinfo[0].getStudyConsultloc());
						AiscLocBean[] loc = AiscLocEngine.getBeans(condition3.toString(), null);
						AiscLocData locmodel = BeanUtils.beanToModel(loc[0],AiscLocData.class);
						String json3 = objectMapper.writeValueAsString(locmodel);
						HttpEntity<String> entityloc = new HttpEntity<String>(json3, headers1);
						ResponseEntity<String> responseEntityloc = restTemplate.postForEntity(hzLocUrl, entityloc, String.class);
						String responseBodyloc = responseEntityloc.getBody();
						JSONObject jsonobjectloc = JSONObject.fromObject(responseBodyloc);
						StudyinfoResponse responseloc = (StudyinfoResponse) JSONObject.toBean(jsonobjectloc,StudyinfoResponse.class);
						if("0".equals(responseloc.getCode())){
							//电子病例实时上传
							AisFilesInfoBean[] fileBeans = studysv.getFileInfo(studyinfoId);
							String path = AIConfigManager.getConfigItem("PdfUpPath");
						    if(fileBeans!=null&&fileBeans.length>0){
						    	for(AisFilesInfoBean file:fileBeans){
						            FTPSUtil ftpUtil = new FTPSUtil();
						            try {
						            	try {
						    				ftpUtil.connect("10.63.89.238", 21, "ftp123", "Ftp_123*456AB");
						    	            ftpUtil.upload("/medicalRecordPdf/",path+ file.getFilePath());
						    	            ftpUtil.closeChannel();
						    	            //插入中心端电子病例记录表
						    	            HttpHeaders headers2 = new HttpHeaders();
											MediaType type2 = MediaType.parseMediaType("application/json; charset=UTF-8");
											headers2.setContentType(type2);
											headers2.add("Accept", MediaType.APPLICATION_JSON.toString());
											AisFilesInfoModel filemodel = new AisFilesInfoModel();
											filemodel.setFilePath(file.getFilePath());
											filemodel.setFileType("PDF");
											filemodel.setStudyinfoId(Long.parseLong(responseStu.getCenStudyinfoID()));
											String json4 = objectMapper.writeValueAsString(filemodel);
											HttpEntity<String> entitypdf = new HttpEntity<String>(json4, headers2);
											ResponseEntity<String> responsepdf = restTemplate.postForEntity(saveCenPdfUrl, entitypdf, String.class);
											String responseBodypdf = responsepdf.getBody();
											JSONObject jsonobjectpdf = JSONObject.fromObject(responseBodypdf);
											StudyinfoResponse rsp = (StudyinfoResponse) JSONObject.toBean(jsonobjectpdf,StudyinfoResponse.class);
											if("0".equals(rsp.getCode())){
												return "0";
											}else{
												return "-5";
											}
						            	 } catch (SocketException e) {
						                     e.printStackTrace();
						                 } catch (IOException e) {
						                     e.printStackTrace();
						                 }
						    			} catch (Exception e) {
						    				e.printStackTrace();
						    			}
						        }
							}
							//实时影像上传接口
							try{ 
								FileServiceStub cilent = new FileServiceStub();
								cilent._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
								FileServiceStub.GPatientID gid = new FileServiceStub.GPatientID();
								gid.setGPatientID(response.getCenGlobalID());
								FileServiceStub.AccessionNumber acc = new FileServiceStub.AccessionNumber();
								acc.setAccessionNumber(studyinfo[0].getStudyAccnumber());
								FileServiceStub.OrgCode orgcode = new FileServiceStub.OrgCode();
								orgcode.setOrgCode("");
								FileServiceStub.OrgId orgIds = new FileServiceStub.OrgId();
								orgIds.setOrgId(orgId);
								FileServiceStub.PatientID patientid = new FileServiceStub.PatientID();
								patientid.setPatientID(patient[0].getPatientId());
								cilent._getServiceClient().getOptions().setTimeOutInMilliSeconds(5*60*1000);
								logger.debug("中心端病人ID主键："+response.getCenGlobalID()+"检查号："+studyinfo[0].getStudyAccnumber()+"机构ID："+orgId+"医院侧病人ID："+patient[0].getPatientId());
								UpFileResult result = cilent.upLoadPatientImage(new FileServiceStub.UpFileInfo(), acc, gid, orgcode, orgIds, patientid);
							}catch(Exception e){
								
							}
							return "0";
						}else{
							return "-4";
						}
					}else{
						return "-3";
					}
				}else{
					return "-2";
				}
			}else{
				return "-1";
			}
		}else{
			return "0";
		}	
		
	}
	
	public QryItemmastEquimentBean getItemmastEquiment(PacsHzjbxxViewModel searchModel)	throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(searchModel.getYzdm()) && !"undefined".equals(searchModel.getYzdm())) {
	    	condition.append(" AND itemmast_code = '" + searchModel.getYzdm()+"'" );
        }
	    if (isNotBlank(searchModel.getOrgId()) && !"undefined".equals(searchModel.getOrgId())) {
	    	condition.append(" AND org_id = '" + searchModel.getOrgId()+"'" );
        }
//	    if (isNotBlank(searchModel.getYzmc()) && !"undefined".equals(searchModel.getYzmc())) {
//	    	String yzmcName = URLDecoder.decode(searchModel.getYzmc(), "utf-8");
//            condition.append(" AND itemmast_desc = '" + yzmcName +"'");
//        }
	    QryItemmastEquimentBean[] itemEqBeans = QryItemmastEquimentEngine.getBeans(condition.toString(), null); 
	    if(itemEqBeans!=null&&itemEqBeans.length>0){
	    	return itemEqBeans[0];
	    }
        return null;
	}
	
	public void updateStudinfoHavingimg(String studyInfoId)throws Exception{
		AisStudyInfoBean studyinfo = AisStudyInfoEngine.getBean(Long.parseLong(studyInfoId));
		if(studyinfo.getStudyHaveimage()==0){
			studyinfo.setStudyHaveimage(2);
		}else if(studyinfo.getStudyHaveimage()==1){
			studyinfo.setStudyHaveimage(3);
		}
		AisStudyInfoEngine.save(studyinfo);
	}
	
	public String uploadPdf(MultipartFile file,String filePath,String pdfName,String studyInfoId) throws Exception{
		//判断已上传的图片不再上传
		AisFilesInfoBean oldBean = getAisFilesInfo(studyInfoId, pdfName,-1);
		if (oldBean == null) {
			//将本地文件上传至服务器
			File targetFile = new File(filePath,pdfName);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			try {
	            //记录文件地址到库表中
	            AisFilesInfoBean fileBean = new AisFilesInfoBean();
	            long fileId = ServiceUtil.getSequence("SEQFILEID");
	            fileBean.setFileId(fileId);
	            fileBean.setStudyinfoId(Long.parseLong(studyInfoId));
	            fileBean.setFileType("PDF");
	            fileBean.setFilePath(pdfName);
	            fileBean.setMiId(-1);
	            AisFilesInfoEngine.save(fileBean);
	            return "0";
	
	        } catch (Exception e) {
	            logger.error("上传电子病历文件异常,请稍后再试：" + e.getMessage());
	        }
		}else{
			return "1";
		}
		return "0";
	}
	
	public void deleteFile(long fileId) throws Exception{
		AisFilesInfoBean fileBean = AisFilesInfoEngine.getBean(fileId);
		fileBean.delete();
		AisFilesInfoEngine.save(fileBean);
	}
	
//	private boolean ftpPdf(InputStream input,String pdfName,String basePath){
//		boolean result = false; 
//		logger.info("上传文件开始...");
////        FTPClient ftp = new FTPClient(); 
//        FTPSUtil ftp = new FTPSUtil();
//        try {
//			ftp.connect("10.63.82.38",21,"pacs","123456");
//			ftp.uploadPdfFile(basePath,pdfName,input);
//			ftp.closeChannel();
//			result = true;
//		} catch (SocketException e) {
//			// TODO Auto-generated catch block
//			result = false;
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			result = false;
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			result = false;
//			e.printStackTrace();
//		}
//        logger.info("上传电子病历文件结束...");
//        return result;  
//	}
	
	private AisFilesInfoBean getAisFilesInfo(String studyinfoId, String pathName, long miId) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(studyinfoId)) {
            condition.append(" AND STUDYINFO_ID = " + studyinfoId);
        }
        if (isNotBlank(pathName)) {
            condition.append(" AND FILE_PATH = '" + pathName + "'");
        }
        if (isNotBlank(pathName)) {
            condition.append(" AND MI_ID = " + miId);
        }
        condition.append(" and FILE_TYPE='PDF' ");
        AisFilesInfoBean[] bean = AisFilesInfoEngine.getBeans(condition.toString(), null);

        if (bean.length > 0) {
            return bean[0];
        }
        return null;
    }
}
