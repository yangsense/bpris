package com.ai.aris.server.workstation.service.interfaces;
 
 
import java.io.InputStream;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.ai.aris.server.basecode.bean.QryConsultLocBean;
import com.ai.aris.server.basecode.bean.QryConsultOrgBean;
import com.ai.aris.server.jchis.model.PacsHzjbxxViewModel;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.QryBigBodypartBean;
import com.ai.aris.server.workstation.bean.QryItemmastEquimentBean;
import com.ai.aris.server.workstation.bean.QryRegInfoBean;
import com.ai.common.domain.ResultDTO;

public interface IPatientRegSV {
	 
 	//根据科室类型取科室信息
	public Map getSeq(String orgId, String locId) throws Exception;
	
	//保存病人登记记录
	public Map savePatientReg(User user,AisPatientInfoBean patientBean,AisStudyInfoBean studyBean,String studyitemDesc,String studyitemBodyinfo,String studyitemNumber,String studyitemPrice,String isNew,String yuyueTime,String isHis,String oldStudyinfoId,String isConsult,String arrayBodystrs,String patientkey) throws Exception;
	
	//登记信息查询
	public QryRegInfoBean getRegInfo(String studyinfoId) throws Exception;
	
	//根据病人全球唯一码，修改ais_studymessage表报告单状态
	public boolean updateAisStudyMessage(String patientGlobalid,String orgId) throws Exception;
	
	//病人基本信息查询
	public AisPatientInfoBean getPatientInfo(String patientGlobalid) throws Exception;
	
	//检查信息查询
	public AisStudyInfoBean getStudyInfoBean(String studyinfoId) throws Exception;
	
	//检查项目查询
	public AisStudyItemInfoBean[] getStudyItemInfoBean(String studyinfoId) throws Exception;
	
	//根据病人相关信息统计病人检查记录数据
	public AisPatientInfoBean[] countPatient(AisPatientInfoBean patientInfoBean) throws Exception;
	
	//查病人记录列表
	public ResultDTO getPatientList(AisPatientInfoBean patientInfoBean, ResultDTO resultDTO) throws Exception;
	
	//取消登记
	public void regCancel(long studyinfoId,String studyUserID) throws Exception;
	
	public String getOrgDuns(String orgId) throws Exception;
	
	public AisPatientInfoBean[] validatePatient(AisPatientInfoBean aisPatientInfo) throws Exception;	
	
	public void saveStudyhistoryinfo(String studyInfoId,String getOperatorId,String status);
	
	//消息队列记录
	public void saveStudyMessage(String studyInfoId,String getOperatorId,String status,long studyType);
	
	public QryConsultOrgBean[] getConsultOrg(String orgId,String locId) throws Exception;
	public QryConsultLocBean[] getConsultLoc(String orgId,String locId) throws Exception;	
	 
	public AisStudyItemInfoBean[] getItemInfo(String studyinfoId) throws Exception;	
	
	public void updatePatient(AisPatientInfoBean bean) throws Exception;
	
	public QryItemmastEquimentBean getItemmastEquiment(PacsHzjbxxViewModel searchModel)	throws Exception;
	
	public String getReadCardInfo(String cardno,String chipSerialNo);
	
	public QryBigBodypartBean[] getBodypartTree(String orgId,String itemmastId,String bodypartDesc)throws Exception;
	
	public void updateRecordStatus(String studyinfoId,String status) throws Exception;
	
	public String uploadPdf(MultipartFile file,String filePath,String pdfName,String studyInfoId) throws Exception;
	
	public void updateStudinfoHavingimg(String studyInfoId)throws Exception;
	
	public void deleteFile(long fileId) throws Exception;
	
	public String sendRealTimeData(String studyinfoId,String patientGlobalid) throws Exception;
}
