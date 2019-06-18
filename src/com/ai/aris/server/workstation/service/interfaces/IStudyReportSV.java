package com.ai.aris.server.workstation.service.interfaces;
 
 
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.ai.aris.server.basecode.bean.AiscUserPhraseBean;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.workstation.bean.*;
import com.ai.aris.server.workstation.model.AisKnowledgebaseModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.TemplateContent;
import com.ai.common.domain.ResultDTO;

import org.springframework.web.multipart.MultipartFile;

public interface IStudyReportSV {

    //模板记录初始化 
	public QryTemplateTreeBean[] getTemplateTree(String id,String orgId,String locId,String operatorId,String modalityId) throws Exception;
	
 	//根据科室类型取科室信息
	public Map getSeq(String orgId, String locId) throws Exception;
	
	//保存病人登记记录
	public long savePatientReg(User user,AisPatientInfoBean patientBean,AisStudyInfoBean studyBean,String studyitemDesc,String studyitemBodyinfo,String studyitemNumber,String studyitemPrice) throws Exception;
	
	//登记信息查询
	public QryRegInfoBean getRegInfo(String studyinfoId) throws Exception;
	
	//模板详情查询
	public Map getTempInfo(String templatedirId) throws Exception;

    //报告详情查询
    public Map getKnowLedgeReportInfo(long id) throws Exception;
	
	//保存报告
	public String saveReport(AisStudyReportBean reportBean,User user,String studyConsultorg,String studyConsultloc,String reportId,HttpServletRequest request,String imgArr,String orgId) throws Exception;
	
	//审核报告
	public String checkReport(AisStudyReportBean reportBean,HttpServletRequest request,String locId,String studyConsultorg,String studyConsultloc,String imgAddrArray,String orgId,String consultRole) throws Exception;
	
	//根据检查ID查报告信息
	public AisStudyReportBean getReport(String studyinfoId) throws Exception;
	
	//用户短语查询
	public AiscUserPhraseBean[] getPhrase(String operatorId) throws Exception;
	
	//添加短语
	public void addPhrase(String operatorId,String pInfo) throws Exception;
	
	//删除短语
	public void delPhrase(String pId) throws Exception;
	
	//修改短语
	public void updatePhrase(String pId,String pInfo) throws Exception;
	
	//相关检查
	public QryRegInfoBean[] getRelCheck(String patientGlobalid, String studyinfoId,String patientInpatientId,String patientIdnumber) throws Exception;
	
	//获取报告存在文件地址
	public QryReportFileBean getReportPath(String reportId,String status) throws Exception;
	
	//根据报告ID取AIS_StudyInfo信息
	public AisStudyInfoBean getAisStudyInfoBean(String reportId);
	//保存信息
	public boolean saveAisStudyMessage(AisStudyInfoBean aisStudyInfoBean,String messageDestuerId,String sourceType,String statusCode);
	
	//删除树菜单
	public boolean deleteNodes(long id) throws Exception;

	/**
	 * 文件上传
	 * @param file
	 * @param filePath
	 * @param pdfName
	 * @param studyInfoId
	 * @return
	 * @throws Exception
	 */
	public String saveReportFileUpload(MultipartFile file, String filePath, String fileName, String reportId,User user,String patientId,String patientName) throws Exception;
	/**
	 * 根据reportId,patientName查询
	 */
	public AisReportUploadBean [] queryFileUpload(String reportId,String patientId,String patientName) throws Exception;

	//修改树菜单
	public boolean editNodes(long id,String name) throws Exception;
	
	//模板查询list
	public ResultDTO queryPageList(TemplateContent model, ResultDTO resultDTO) throws Exception;
	
	//
	public boolean getIshaveNode(long id,long pId) throws Exception;
	
	public long getNodeIndex() throws Exception;
	
	//保存节点
	public boolean saveNode(long id,long pId,String name,long locId,long orgId,long isDirectory,long operatorId,long templatedir_flag,String modalityId) throws Exception;
	
	//获取模板详情
	public TemplateContent getAiscTemplateDetail(long dirid) throws Exception;
	//保存或修改模板详情
	public void saveTemplateDetail(TemplateContent bean) throws Exception;
	//删除模板详情
	public boolean deleteTemplate(long id) throws Exception;
	
	public long getNodeId(String dirName,long templatedir_flag,String orgId) throws Exception;
	
	public int isHaveDir(String dirName,long templatedir_flag,String orgId,long id) throws Exception;
	
	//更新打印状态
	public void setReportPrintInfo(String reportId,User user) throws Exception; 
	
	public void consultStart(String studyinfoId,String getOperatorId,String  reportId) throws Exception; 
	
	public String getImgPrintTemp(String imgArr,long studyinfoId) throws Exception; 
	
	public String uploadImg(String locId,HttpServletRequest request,long newRreportId,AisStudyReportBean reportBean,String imgArr,String studyinfoId) throws Exception;
	
	public QryReportFileBean[] getReportImgupload(String studyinfoId) throws Exception;
	
	public String uploadReportTemp(AisStudyReportBean reportBean,long reportId,HttpServletRequest request,String imgArr,String orgId) throws Exception;
	
	public AisReportFilesBean[]  getfileBeans(long reportId,String status)throws Exception;
	
	public String checkFinalReport(AisStudyReportBean reportBean,HttpServletRequest request,String locId,
            String studyConsultorg,String studyConsultloc,String imgAddrArray,String orgId) throws Exception;
	public QryReportBrowseBean getReportByAccnumber(String studyAccnumber,String patientId) throws Exception;
	public ResultDTO queryReportList(QryStudyInfoListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public QryReportBrowseBean getReportByOther(String name,String idNo,String birthDate) throws Exception;
	
	public QryReportBrowseBean getReportByInoutPatinetId(String orgId,String patientType,String outInPatientId) throws Exception;
	
	public String saveUpReport(AisStudyReportBean reportBean,User user,String studyConsultorg,String studyConsultloc,String reportId,HttpServletRequest request,String imgArr,String orgId) throws Exception;
	
	public QryReportBrowseBean getReportContrast(String studyinfoId) throws Exception;
	
	public QryReportHBean getReportH(String studyinfoId) throws Exception;
	
	public List<Map> getReportSelect(String studyinfoId) throws Exception;

	public List<AisKnowledgebaseModel> getAisKnowledgebase(String operatorId) throws Exception ;

	public List<AisKnowledgebaseModel> getAisKnowledgebaseKeyDesc(String dataSore) throws Exception ;

	public void saveAisKnowledgebase(String reportId, String operatorId,String keydesc) throws Exception ;

	public AisKnowledgebaseBean[] getKnowledgeTree(String id,String operatorId) throws Exception;
	
	public JSONObject aiDataResult(String studyinfoId,String orgId) throws Exception;

	public int checkReportIsRepeat(String reportId) throws Exception;
	
	public int checkKeydescIsRepeat(String keydesc,String casegroupdescTemp) throws Exception;
	
	public JSONObject sendStudyinfoUp(String studyaccNumber,String seriesNo,String seriesUid) throws Exception;
	
	public void updateStudyInfo(String statusCode,long studyInfoId) throws Exception;
	
	public AisFilesInfoBean[]  getFileInfo(String studyinfoId) throws Exception;
	
	public boolean deletebingliNodes(long id) throws Exception;
	
	public boolean editbingliNodes(long id, String name) throws Exception;
	
	public void updateKnowledgebase(TemplateContent bean) throws Exception;

	public void fileDelete(String fileName,String patientName,String patientId,String reportId)throws Exception;
	
	public String retrivePatientImage(String patientId,String studyAccnumber,String orgId);
}
