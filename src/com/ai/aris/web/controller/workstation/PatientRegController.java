package com.ai.aris.web.controller.workstation;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.QryBodyPartBean;
import com.ai.aris.server.basecode.bean.QryConsultLocBean;
import com.ai.aris.server.basecode.bean.QryConsultOrgBean;
import com.ai.aris.server.basecode.bean.QryItemMastBean;
import com.ai.aris.server.basecode.service.interfaces.IAiscLocSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.common.util.SpellHelper;
import com.ai.aris.server.jchis.model.PacsHzjbxxViewModel;
import com.ai.aris.server.jchis.service.interfaces.IPacsHzjbxxViewSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.imageservice.FileServiceStub;
import com.ai.aris.server.webservice.imageservice.FileServiceStub.UpFileResult;
import com.ai.aris.server.workstation.bean.AisFilesInfoBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.aris.server.workstation.bean.QryBigBodypartBean;
import com.ai.aris.server.workstation.bean.QryItemmastEquimentBean;
import com.ai.aris.server.workstation.bean.QryRegInfoBean;
import com.ai.aris.server.workstation.model.QryBigBodypartModel;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.Constants;
import com.ai.common.util.DateUtils;
import com.ai.common.util.JsonUtil;

@Controller
@RequestMapping("/patientReg")
public class PatientRegController {
	
	private Log logger = LogFactory.getLog(PatientRegController.class);
    private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    private IPatientRegSV sv = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);
    private IPacsHzjbxxViewSV hisSV = (IPacsHzjbxxViewSV) ServiceFactory.getService(IPacsHzjbxxViewSV.class);
    private IAiscLocSV aiscLocsv = (IAiscLocSV) ServiceFactory.getService(IAiscLocSV.class);
    private IStudyReportSV studysv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
  
    //病人登记首面初始化
	@RequestMapping("patientRegPageinit.html")
	public String patientRegInit(String studyinfoId,String patientGlobalid,String orgId,String locId,String diagnoseFlag,String again,Model model,HttpServletRequest request){
		String isNew = "y";		
		try {
			//有检查号直接调数据库，查回登记信息展现 否则直接展现页面
			if(!"".equals(studyinfoId) && !"undefined".equals(studyinfoId)&&"n".equals(again)){
				//需要存储部位编码--增加部位编码字段
				//1.取病人基本信息
				AisPatientInfoBean patientInfo = sv.getPatientInfo(patientGlobalid);
				//2.取检查信息
				AisStudyInfoBean studyInfoBean = sv.getStudyInfoBean(studyinfoId);	
				model.addAttribute("isUrgent", studyInfoBean.getIsUrgent());
			    //3.取检查项目
				AisStudyItemInfoBean[] studyItemInfoBeans = sv.getStudyItemInfoBean(studyinfoId);
				
				model.addAttribute("patientInfo", patientInfo);
				model.addAttribute("studyInfoBean", studyInfoBean);
				model.addAttribute("studyItemInfoBeans", studyItemInfoBeans);
				//特殊处理几个回显值
				model.addAttribute("patientGlobalid", patientInfo.getPatientGlobalid());
				model.addAttribute("patientId", patientInfo.getPatientId());
				model.addAttribute("studyinfoId", studyinfoId);
				model.addAttribute("studyAccnumber", studyInfoBean.getStudyAccnumber());
				model.addAttribute("studyConsultorg", studyInfoBean.getStudyConsultorg());
				model.addAttribute("studyAppdoc", studyInfoBean.getStudyAppdoc());
				model.addAttribute("studyConsultloc", studyInfoBean.getStudyConsultloc());
				model.addAttribute("periodDate",studyInfoBean.getStudyDatetime().toString().substring(0,11));
				//获取old预约时间时间段
				AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
				//判断当前时间
				String periodStartTime = ""; 
				for(int i=0;i<periods.length;i++){
					if(DateUtils.isInDate(studyInfoBean.getStudyDatetime(),periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
						periodStartTime = periods[i].getPeriodStarttime();
					}
				}
				model.addAttribute("periods", periods);
				model.addAttribute("periodStartTime",periodStartTime);
				isNew = "n";
			}else if(!"".equals(studyinfoId) && !"undefined".equals(studyinfoId)&&"y".equals(again)){
				//1.取病人基本信息
				AisPatientInfoBean patientInfo = sv.getPatientInfo(patientGlobalid);
				model.addAttribute("patientInfo", patientInfo);
				model.addAttribute("patientGlobalid", patientInfo.getPatientGlobalid());
				model.addAttribute("patientId", patientInfo.getPatientId());
				//2.取检查信息
				AisStudyInfoBean studyInfoBean = sv.getStudyInfoBean(studyinfoId);	
				model.addAttribute("studyInfoBean", studyInfoBean);
				AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
				//判断当前时间
				Date date = new Date();
				String periodStartTime = ""; 
				for(int i=0;i<periods.length;i++){
					if(DateUtils.isInDate(date,periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
						periodStartTime = periods[i].getPeriodStarttime();
					}
				}
				model.addAttribute("periods", periods);
				model.addAttribute("periodStartTime",periodStartTime);
			}else{
				//序列初始化
//				Map seqs = sv.getSeq(orgId,"");
//				AisPatientInfoBean patientInfo = new AisPatientInfoBean();
//				patientInfo.setPatientId(String.valueOf(seqs.get("studyAccnumber")));
//				patientInfo.setPatientGlobalid(Long.parseLong(String.valueOf(seqs.get("patientGlobalid"))));
//				AisStudyInfoBean studyInfoBean = new AisStudyInfoBean();
//				studyInfoBean.setStudyAccnumber(String.valueOf(seqs.get("studyAccnumber")));
//				
//				model.addAttribute("patientInfo", patientInfo);
//				model.addAttribute("studyInfoBean", studyInfoBean);
				
//				model.addAttribute("studyAccnumber", seqs.get("studyAccnumber"));
//				model.addAttribute("patientId", seqs.get("patientId"));
//				model.addAttribute("patientGlobalid", seqs.get("patientGlobalid"));
				
				//获取预约时间时间段
				AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
				//判断当前时间
				Date date = new Date();
				String periodStartTime = ""; 
				for(int i=0;i<periods.length;i++){
					if(DateUtils.isInDate(date,periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
						periodStartTime = periods[i].getPeriodStarttime();
					}
				}
				model.addAttribute("periods", periods);
				model.addAttribute("periodStartTime",periodStartTime);
			}			
			//病人类型
			DictItemBean[] patientTypes = dictSV.queryDictItem("PATIENT_TYPE");
			//申请科室
			AiscLocBean[] locBeans = commonDataSv.getLocInfo(orgId,"A");//Loc_Type=A 科室
			//病区
			AiscLocBean[] studyWards = commonDataSv.getLocInfo(orgId,"B");//Loc_Type=B 病区
			//检查设备
			AiscEquipmentBean[] equipmentBeans = commonDataSv.getEquipment(orgId,locId);
			//检查等级
			DictItemBean[] studyLevels = dictSV.queryDictItem("STUDY_LEVEL");
			//收费类型
			DictItemBean[] paymentTypes = dictSV.queryDictItem("PAYMENT_TYPE");
			//基层HIS查询条件 
			DictItemBean[] jcHisQryType = dictSV.queryDictItem("JC_HIS_QRY_TYPE");
			//4.取申请医生
//			AiscCareProvBean[] careProvBeans = commonDataSv.getCareProv(orgId, String.valueOf(locId),"");				
			
//			model.addAttribute("careProvBeans", careProvBeans);		
			model.addAttribute("paymentTypes", paymentTypes);
			model.addAttribute("studyLevels", studyLevels);
			model.addAttribute("equipmentBeans", equipmentBeans);
			model.addAttribute("studyWards", studyWards);
			model.addAttribute("studyApplocs", locBeans);
			model.addAttribute("patientTypes", patientTypes); 
			model.addAttribute("isNew", isNew);
			model.addAttribute("locId", locId); 
			model.addAttribute("queryKey", jcHisQryType); 
			 
			//按钮权限控制
//	        List<Menu> buttons = (List<Menu>)request.getSession().getAttribute(Constants.SESSION_BUTTON);
//	        for(Menu m : buttons){ 
//            	if("DIAGNOSE_ROLE".equals(m.getMenuUrl())){
//            		model.addAttribute("DIAGNOSE_ROLE", 'y');
//            	}
//            }
			model.addAttribute("again", again);
			model.addAttribute("DIAGNOSE_ROLE", diagnoseFlag);
			
		} catch (Exception e) {
			logger.error("PatientRegController--patientRegInit:", e); 
		} 
		
		return "workstation/patientReg";
	} 
	
	//远程会诊登记首面初始化
	@RequestMapping("consultPatientRegInit.html")
	public String consultPatientRegInit(String studyinfoId,String patientGlobalid,String orgId,String locId,String diagnoseFlag,String hzsqFlag,Model model,HttpServletRequest request){
		String isNew = "y";		
		try {
			//有检查号直接调数据库，查回登记信息展现 否则直接展现页面
			if(!"".equals(studyinfoId) && !"undefined".equals(studyinfoId)){
				//需要存储部位编码--增加部位编码字段
				//1.取病人基本信息
				AisPatientInfoBean patientInfo = sv.getPatientInfo(patientGlobalid);
				//2.取检查信息
				AisStudyInfoBean studyInfoBean = sv.getStudyInfoBean(studyinfoId);				
			    			
				model.addAttribute("patientInfo", patientInfo);
				model.addAttribute("studyInfoBean", studyInfoBean);
				//特殊处理几个回显值
				model.addAttribute("patientGlobalid", patientInfo.getPatientGlobalid());
				model.addAttribute("patientId", patientInfo.getPatientId());
				model.addAttribute("studyinfoId", studyinfoId);
				model.addAttribute("studyAccnumber", studyInfoBean.getStudyAccnumber());
				model.addAttribute("studyConsultorg", studyInfoBean.getStudyConsultorg());
				model.addAttribute("studyConsultloc", studyInfoBean.getStudyConsultloc());
				model.addAttribute("studystutascode", studyInfoBean.getStudystatusCode());
				model.addAttribute("studyhaveimage", studyInfoBean.getStudyHaveimage());
				model.addAttribute("periodDate",studyInfoBean.getStudyDatetime().toString().substring(0,11));
				//获取old预约时间时间段
				AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
				//判断当前时间
				String periodStartTime = ""; 
				for(int i=0;i<periods.length;i++){
					if(DateUtils.isInDate(studyInfoBean.getStudyDatetime(),periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
						periodStartTime = periods[i].getPeriodStarttime();
					}
				}
				model.addAttribute("periods", periods);
				model.addAttribute("periodStartTime",periodStartTime);
				isNew = "n";
				AisFilesInfoBean[] fileBean = studysv.getFileInfo(studyinfoId);
				String prePath = com.ai.common.util.WebUtils.getBasePath(request);
			    prePath = prePath.replace("Aris", "FileServer/aris/downloadPDF/");
		    	model.addAttribute("fileBean", fileBean);
		    	model.addAttribute("prePath", prePath);
			}else{ 
				//获取预约时间时间段
				AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
				//判断当前时间
				Date date = new Date();
				String periodStartTime = ""; 
				for(int i=0;i<periods.length;i++){
					if(DateUtils.isInDate(date,periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
						periodStartTime = periods[i].getPeriodStarttime();
					}
				}
				model.addAttribute("periods", periods);
				model.addAttribute("periodStartTime",periodStartTime);
			}	
			
			//病人类型
			DictItemBean[] patientTypes = dictSV.queryDictItem("PATIENT_TYPE");
			//申请科室
			AiscLocBean[] locBeans = commonDataSv.getLocInfo(orgId,"A");//Loc_Type=A 科室
			//病区
			AiscLocBean[] studyWards = commonDataSv.getLocInfo(orgId,"B");//Loc_Type=B 病区
			  
			//基层HIS查询条件 
			DictItemBean[] jcHisQryType = dictSV.queryDictItem("JC_HIS_QRY_TYPE");
			//4.取申请医生
//			AiscCareProvBean[] careProvBeans = commonDataSv.getCareProv(orgId, String.valueOf(locId),"");				
			
//			model.addAttribute("careProvBeans", careProvBeans);		 
			model.addAttribute("studyWards", studyWards);
			model.addAttribute("studyApplocs", locBeans);
			model.addAttribute("patientTypes", patientTypes); 
			model.addAttribute("isNew", isNew);
			model.addAttribute("locId", locId); 
			model.addAttribute("queryKey", jcHisQryType); 
			 
			model.addAttribute("DIAGNOSE_ROLE", diagnoseFlag);
			model.addAttribute("HZSQ_ROLE", hzsqFlag);
		} catch (Exception e) {
			logger.error("PatientRegController--consultPatientReg:", e); 
		} 
		
		return "workstation/consultPatientReg";
	} 
	//电子病历上传
	@RequestMapping("/saveUploadPdf.ajax")
	@ResponseBody
	public JSONObject saveUploadPdf(String studyInfoId,HttpServletRequest request)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("ERRORCODE", "0");
		List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file1");
		String pdfUpPath = AIConfigManager.getConfigItem("PdfUpPath");
		for (int i =0; i< files.size(); i++) {
            MultipartFile file = (MultipartFile)files.get(i);
            String name = file.getOriginalFilename();
            if (!file.isEmpty()) {
                String result = sv.uploadPdf(file,pdfUpPath,name,studyInfoId);
                json.put("RESULT", result);
            } else {
            	json.put("ERRORCODE", "-1");
            }
        }
		return json;
	}
	//电子病历pdf文件删除
	@RequestMapping("/deleteFile.ajax")
	@ResponseBody
	public JSONObject deleteFile(long fileId)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("ERRORCODE", "0");
		sv.deleteFile(fileId);
		return json;
	}
	
	@RequestMapping("updateStudinfoHavingimg.ajax")
	@ResponseBody
	public JSONObject updateStudinfoHavingimg(String studyinfoId)throws Exception {
		JSONObject json = new JSONObject();
		json.put("ERRORCODE", "0");
		sv.updateStudinfoHavingimg(studyinfoId);
		return json;
	}
	
	//序列初始化
    @RequestMapping("getSeq.ajax")
    @ResponseBody
    public JSONObject getSeq(String orgId,String locId) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			Map bodyPartBeans = sv.getSeq(orgId,locId);			
			jsonObj.put("patientGlobalid", bodyPartBeans.get("patientGlobalid"));
			jsonObj.put("patientId", bodyPartBeans.get("patientId"));
			jsonObj.put("studyAccnumber", bodyPartBeans.get("studyAccnumber")); 
		} catch (Exception e) {
			logger.error("PatientRegController--getStudyItem:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }	
	//病人类型
	@SuppressWarnings("unchecked")
	@RequestMapping("/getPatientType.ajax")
    @ResponseBody
    public Map getPatientType()  {
		Map map = new HashMap();
		try {
			List<Map> status = dictSV.queryDict("PATIENT_TYPE");	       
	        map.put("PATIENT_TYPE", JsonUtil.toJson(status));
		} catch (Exception e) {
			logger.error("PatientRegController--getPatientType:", e);
			e.printStackTrace();
		}
		
        return map;
    }
	//支付类型
	@SuppressWarnings("unchecked")
	@RequestMapping("/getPaymentType.ajax")
    @ResponseBody
    public Map getPaymentType() throws Exception {
		Map map = new HashMap();
		try {
			List<Map> status = dictSV.queryDict("PAYMENT_TYPE");        
	        map.put("PAYMENT_TYPE", JsonUtil.toJson(status));
		} catch (Exception e) {
			logger.error("PatientRegController--getPaymentType:", e);
			e.printStackTrace(); 
		}		
        return map;
    }
	//检查等级
	@SuppressWarnings("unchecked")
	@RequestMapping("/getStudyLevel.ajax")
    @ResponseBody
    public Map getStudyLevel() throws Exception{
		Map map = new HashMap();
		try {
			List<Map> status = dictSV.queryDict("STUDY_LEVEL");       
	        map.put("STUDY_LEVEL", JsonUtil.toJson(status));
		} catch (Exception e) {
			logger.error("PatientRegController--getStudyLevel:", e);
			e.printStackTrace(); 
		}		
        return map;
    }	
	
	//查询his病人是否在aris中存在
    @RequestMapping("validatePatient.ajax")
    @ResponseBody
    public JSONObject validatePatient(AisPatientInfoBean aisPatientInfo) {
    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success",false);
		try {
			AisPatientInfoBean[] aisPatientInfos = sv.validatePatient(aisPatientInfo);	
			if(aisPatientInfos!=null&&aisPatientInfos.length>0){
				jsonObj.put("patientGlobalid",aisPatientInfos[0].getPatientGlobalid());
				jsonObj.put("patientId",aisPatientInfos[0].getPatientId());
				jsonObj.put("success",true);
			}
		} catch (Exception e) {
			logger.error("PatientRegController--getLocInfo:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
    }
	
	//科室列表查询
    @RequestMapping("getLocInfo.ajax")
    @ResponseBody
    public JSONObject getLocInfo(String orgId,String locType) {
    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscLocBean[] locBeans = commonDataSv.getLocInfo(orgId,locType);			
			jsonObj.put("locBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new AiscLocBean[0] : locBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getLocInfo:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
    }
    //申请医生列表查询
    @RequestMapping("getCareProv.ajax")
    @ResponseBody
    public JSONObject getCareProv(String orgId,String locId,String doctype) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscCareProvBean[] careProvBeans = commonDataSv.getCareProv(orgId,locId,doctype);			
			jsonObj.put("careProvBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(careProvBeans == null ? new AiscCareProvBean[0] : careProvBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getCareProv:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
    //检查项目
    @RequestMapping("getCareProvItem.ajax")
    @ResponseBody
    public JSONObject getCareProvItem(String keyword) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscCareProvBean[] careProvBeans = commonDataSv.getCareProv(null,null,null,keyword);			
			jsonObj.put("dataList", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(careProvBeans == null ? new AiscCareProvBean[0] : careProvBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getCareProvItem:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
    
    //检查设备查询
    @RequestMapping("getEquipment.ajax")
    @ResponseBody
    public JSONObject getEquipment(String orgId,String locId) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscEquipmentBean[] equipmentBeans = commonDataSv.getEquipment(orgId,locId);			
			jsonObj.put("equipmentBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(equipmentBeans == null ? new AiscEquipmentBean[0] : equipmentBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getEquipment:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    //检查项目
    @RequestMapping("getStudyItem.ajax")
    @ResponseBody
    public JSONObject getStudyItem(String orgId,String locId,String keyword,String exists,String equipmentId) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			QryItemMastBean[] itemMastBeans = commonDataSv.getStudyItem(orgId,locId,keyword,exists,equipmentId);			
			jsonObj.put("dataList", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(itemMastBeans == null ? new QryItemMastBean[0] : itemMastBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getStudyItem:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
    //姓名转拼音
    @RequestMapping("getPatientPy.ajax")
    @ResponseBody
    public JSONObject getPatientPy(String patientName) { 
    	JSONObject jsonObj = new JSONObject();
		try {
			SpellHelper py = new SpellHelper();
			patientName = URLDecoder.decode(patientName,"UTF-8"); 
			String patientEname = py.getUpEname(patientName);
			jsonObj.put("patientEname", patientEname);
		} catch (Exception e) {
			logger.error("PatientRegController--getPatientPy:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
    //检查部位
    @RequestMapping("getBodyPart.ajax")
    @ResponseBody
    public JSONObject getBodyPart(String orgId,String itemmastId,String partId) { 
    	JSONObject jsonObj = new JSONObject();
		try {
			QryBodyPartBean[] bodyPartBeans = commonDataSv.getBodyPart(orgId,itemmastId,partId);
			jsonObj.put("bodyPartBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(bodyPartBeans == null ? new QryBodyPartBean[0] : bodyPartBeans));
		} catch (Exception e) {
			logger.error("PatientRegController--getBodyPart:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    //保存
    @RequestMapping("savePatientReg.ajax")
    @ResponseBody
    public Map savePatientReg(AisPatientInfoBean patientBean,AisStudyInfoBean studyBean,
    		                  String studyitemDescClone,String studyitemBodyinfoClone,
    		                  String studyitemNumber,String studyitemPrice,String isNew,String yuyueTime,String isHis,String oldStudyinfoId,String isConsult,String arrayBodystrs,String patientkey,
    		                  HttpServletRequest request) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try {
			User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ)); 			
			Map map = sv.savePatientReg(user,patientBean,studyBean,studyitemDescClone,studyitemBodyinfoClone,studyitemNumber,studyitemPrice,isNew,yuyueTime,isHis,oldStudyinfoId,isConsult,arrayBodystrs,patientkey);			
			jsonObj.put("studyInfoId", map.get("studyInfoId"));	
			jsonObj.put("patientGlobalid", map.get("patientGlobalid"));	
			jsonObj.put("patientId", map.get("patientId"));	
			jsonObj.put("studyAccnumber", map.get("studyAccnumber"));	
			jsonObj.put("flag", map.get("flag"));			
		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("PatientRegController--savePatientReg.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
    //打印
	@RequestMapping("/regPrint.html")
    public String regPrint(@RequestParam String studyinfoId,Model model) throws Exception {
		QryRegInfoBean regInfoBean = sv.getRegInfo(studyinfoId); 
      	model.addAttribute("regInfoBean",regInfoBean); 
        return "print/regPrint";
    }
	
	//检查项查询 
	@RequestMapping("getStydyItemInfo.ajax")
    @ResponseBody
    public JSONObject getStydyItemInfo(String studyinfoId) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try {
			AisStudyItemInfoBean[] studyItemInfoBeans = sv.getStudyItemInfoBean(studyinfoId);
			jsonObj.put("studyItemInfoBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(studyItemInfoBeans == null ? new AisStudyItemInfoBean[0] : studyItemInfoBeans));

		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("PatientRegController--getStydyItemInfo.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
	
	//根据病人信息查，统计检查记录
	@RequestMapping("getPatients.ajax")
    @ResponseBody
    public JSONObject countPatient(AisPatientInfoBean patientInfoBean) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try {
			AisPatientInfoBean[] beans = sv.countPatient(patientInfoBean);
			//jsonObj.put("patientInfoBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(beans == null ? new AisPatientInfoBean[0] : beans));
			if(beans != null){
				jsonObj.put("total",  beans.length);
			}else{
				jsonObj.put("total",  0);
			}
			
		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("PatientRegController--countPatient.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
	//根据病人信息查查病人list
	@RequestMapping("patientRegList.html") 
    public String patientRegList(AisPatientInfoBean patientInfoBean,Model model) {  
		try {
    	   model.addAttribute("patientName", java.net.URLDecoder.decode(patientInfoBean.getPatientName(), "UTF-8"))  ;    
		} catch (Exception e) { 
			logger.error("PatientRegController--countPatient.ajax:", e);
			e.printStackTrace();
		} 
    	return "workstation/patientlist"; 
    }
	
	//查询病人列表
	@RequestMapping("/getPatientList.ajax")
	@ResponseBody	
	public JSONObject getPatientList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			AisPatientInfoBean patientInfoBean, HttpServletRequest request)
			throws Exception { 
		 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.getPatientList(patientInfoBean, result);
		return AjaxUtil.jqGridJson(result);
	}
	
	//统计HIS侧病人检查信息
	@RequestMapping("getHisStudyInfoCount.ajax")
    @ResponseBody
    public JSONObject getHisStudyInfoCount(PacsHzjbxxViewModel searchModel) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try {
			//根据机构id查询duns(机构编码)
			String duns = sv.getOrgDuns(searchModel.getOrgId());
			searchModel.setOrgCode(duns);
			//根据机构id和locid查询loc实体信息
			AiscLocBean locBean = aiscLocsv.getAiscLocById(searchModel.getOrgId(),searchModel.getLocId());
			searchModel.setLocCode(locBean.getLocCode());
			//获取his视图数量
			int total = hisSV.getHisStudyInfoCount(searchModel);			 
			jsonObj.put("total", total);
		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("PatientRegController--countPatient.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
	
	//会诊申请取消/提交修改状态------------调用实时上传影像接口
    @RequestMapping("updateRecordStatus.ajax")
    @ResponseBody
    public JSONObject updateRecordStatus(String studyinfoId,String patientGlobalid,String type) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
    	try {	
    		String status = "";
    		if("AppSave".equals(type)){
    			status = "APPConsult";
    		}else{
    			status = "AppCancel";
    		}
    		String result = sv.sendRealTimeData(studyinfoId,patientGlobalid);
    		if("0".equals(result)){
    			sv.updateRecordStatus(studyinfoId,status);
    		}
    		jsonObj.put("result", result);
    	}catch (Exception e) {
			jsonObj.put("success", false);
			jsonObj.put("result", "-99");
			logger.error("PatientRegController--cancelHzrecord.ajax:", e);
			e.printStackTrace();
		}   
	    return jsonObj; 
    }
    
	//查询his病人检查项目对应设备
	@RequestMapping("getItemmastEquiment.ajax")
    @ResponseBody
    public JSONObject getItemmastEquiment(PacsHzjbxxViewModel searchModel) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try {	
			QryItemmastEquimentBean itemmastEquiment = sv.getItemmastEquiment(searchModel);
			if(itemmastEquiment!=null){
				jsonObj.put("EquipmentId", itemmastEquiment.getEquipmentId());
				jsonObj.put("itemmastId", itemmastEquiment.getItemmastId());
				jsonObj.put("itemmastCode", itemmastEquiment.getItemmastCode());
				jsonObj.put("itemPrice", itemmastEquiment.getItemmastPrice());
				jsonObj.put("itemmastDesc", itemmastEquiment.getItemmastDesc());
			}else{
				jsonObj.put("EquipmentId", "-1");
			}
		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("PatientRegController--countPatient.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
	
	//展现HIS侧病人检查信息
	@RequestMapping("hisStudyInfo.html") 
    public String HisStudyInfo(PacsHzjbxxViewModel searchModel,Model model){ 
		
		try {
			String duns = sv.getOrgDuns(searchModel.getOrgId());
			AiscLocBean locBean = aiscLocsv.getAiscLocById(searchModel.getOrgId(),searchModel.getLocId());
			
			model.addAttribute("orgCode", duns);	
			model.addAttribute("orgId", searchModel.getOrgId());
			model.addAttribute("locCode", locBean==null?"":locBean.getLocCode());	
			model.addAttribute("locId", searchModel.getLocId());	
		  	model.addAttribute("queryKey", searchModel.getQueryKey());	
		  	model.addAttribute("queryValue", searchModel.getQueryValue());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return "workstation/hisPatientlist"; 
    }
	//查询病人列表
	@RequestMapping("/getHisStudyInfoList.ajax")
	@ResponseBody	
	public JSONObject getHisStudyInfoList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			PacsHzjbxxViewModel searchModel, HttpServletRequest request)
			throws Exception { 
		 
		ResultDTO result = new ResultDTO(page, rows);
		try {
			result = hisSV.getHisStudyInfo(searchModel, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return AjaxUtil.jqGridJson(result);
	}
	
	
	//病人取消登记页面
	@RequestMapping("patientRegCancel.html")
	public String patientRegCancel(String studyinfoId,String patientGlobalid,String orgId,String locId,String consultFlag,Model model,HttpServletRequest request){
		try {
			if(!"".equals(studyinfoId) && !"undefined".equals(studyinfoId)){
				AisPatientInfoBean patientInfo = sv.getPatientInfo(patientGlobalid);
				model.addAttribute("patientInfo", patientInfo);
				model.addAttribute("patientGlobalid", patientInfo.getPatientGlobalid());
				model.addAttribute("patientId", patientInfo.getPatientId());
				model.addAttribute("studyinfoId", studyinfoId);
			}
		} catch (Exception e) {
			logger.error("病人取消登记页面patientRegCancel:", e); 
		} 
		return "workstation/patientRegCancel";
	} 
	//取消登记
	@RequestMapping("regCancel.ajax")
	@ResponseBody
	public JSONObject regCancel(String studyinfoId,HttpServletRequest request) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("ERRCODE", "0");
		try {
			User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
			sv.regCancel(Long.parseLong(studyinfoId),user.getOperatorId());
		} catch (Exception e) {
			logger.error("取消登记regCancel：", e);
			jsonRes.put("ERRCODE", "1");
			jsonRes.put("ERRINFO", e.getMessage());
		}
		return jsonRes;
	}
	
	//查询组织机构
	@RequestMapping("getConsultOrg.ajax")
	@ResponseBody
	public JSONObject getConsultOrg(String orgId,String locId, HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			QryConsultOrgBean[] locBeans = sv.getConsultOrg(orgId,locId);			
			jsonObj.put("conorganization", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QryConsultOrgBean[0] : locBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getConsultOrg:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
	}
	
	//查询组织机构
	@RequestMapping("getConsultLoc.ajax")
	@ResponseBody
	public JSONObject getConsultLoc(String orgId,String locId,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			QryConsultLocBean[] locBeans = sv.getConsultLoc(orgId,locId);			
			jsonObj.put("locBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QryConsultOrgBean[0] : locBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getConsultLoc:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
	}
	
	@RequestMapping("getBodypartTree.ajax")
	@ResponseBody
	public String getBodypartTree(String itemmastId,String orgId,String bodypartDesc) {
		String json = "";
		try {
			QryBigBodypartBean[] bigBodypartBeans = sv.getBodypartTree(orgId,itemmastId,java.net.URLDecoder.decode(bodypartDesc, "UTF-8"));
			List<QryBigBodypartModel> list = BeanUtils.beanToList(bigBodypartBeans, QryBigBodypartModel.class);
//			ObjectMapper objectMapper = new ObjectMapper();
//			objectMapper.writeValueAsString(bigBodypartBeans);
//			List<QryBigBodypartModel> list =JsonUtil.
			json= JsonUtil.toJson(list);
		} catch (Exception e) {
			logger.error("PatientRegController--getBodypartTree:", e);
			e.printStackTrace();
		}       
	     return json; 
	}
	
	
	//查询读卡返回信息
	@RequestMapping("getReadCardInfo.ajax")
	@ResponseBody
	public JSONObject getReadCardInfo(String cardNo,String chipSerialNo) {
		JSONObject jsonObj = new JSONObject();
		try {
		    String studyInfoXml = sv.getReadCardInfo(cardNo,chipSerialNo);	
		    if(!"".equals(studyInfoXml)){
		    	Document document = DocumentHelper.parseText(studyInfoXml);
				Element root=document.getRootElement();
				String result = root.element("ResultCode").getText();
				if("0".equals(result)){
					jsonObj.put("result",result);
					String Name = root.element("Name").getText();
					String CertIdNo = root.element("CertIdNo").getText();
					String Sex = root.element("Sex").getText();
					String Age = root.element("Age").getText();
					String PhoneNumber = root.element("PhoneNumber").getText();
					String Address = root.element("Address").getText();
					jsonObj.put("result",result);
					jsonObj.put("Name",Name);
					jsonObj.put("CertIdNo",CertIdNo);
					jsonObj.put("Sex",Sex);
					jsonObj.put("Age",Age);
					jsonObj.put("PhoneNumber",PhoneNumber);
					jsonObj.put("Address",Address);
				}
				else{
					jsonObj.put("result",result);
				}
		    }
		} catch (Exception e) {
			logger.error("PatientRegController--getConsultLoc:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
	}
	
	@RequestMapping("/medicalRecordUpload.html")
    public String medicalRecordUpload(@RequestParam String locId, String orgId,String studyInfoId,Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("locId", locId);
        model.addAttribute("orgId", orgId);
        model.addAttribute("studyInfoId", studyInfoId);
        String forward = "";
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        long operatorId = Long.parseLong(user.getOperatorId());
        model.addAttribute("operatorId", operatorId);
        forward = "workstation/medicalRecordUpload";
        return forward;
    }
	
	public static void main(String[] args){
		try {
		String wsUrl = "http://10.63.82.38:8732/AIPACS/AisStorageSCU/FileService/?wsdl";
//        String method = "UpLoadPatientImage"; 
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
//        Client client =  dcf.createClient(new URL(wsUrl));  
//        Object[] object ={"2018061118","304980","111111","6042","00066373"}; 
//        Object[] res;
//			res = client.invoke(method, object);
//			System.out.println(res[0].toString() + "");
		
		FileServiceStub cilent = new FileServiceStub();
		cilent._getServiceClient().getOptions().setProperty(org.apache.axis2.transport.http.HTTPConstants.CHUNKED, Boolean.FALSE);
		FileServiceStub.GPatientID gid = new FileServiceStub.GPatientID();
		gid.setGPatientID("304980");
		FileServiceStub.AccessionNumber acc = new FileServiceStub.AccessionNumber();
		acc.setAccessionNumber("2018061118");
		FileServiceStub.OrgCode orgcode = new FileServiceStub.OrgCode();
		orgcode.setOrgCode("111111");
		FileServiceStub.OrgId orgId = new FileServiceStub.OrgId();
		orgId.setOrgId("1378");
		FileServiceStub.PatientID patientid = new FileServiceStub.PatientID();
		patientid.setPatientID("00066962");
		cilent._getServiceClient().getOptions().setTimeOutInMilliSeconds(5*60*1000);
		UpFileResult result = cilent.upLoadPatientImage(new FileServiceStub.UpFileInfo(), acc, gid, orgcode, orgId, patientid);
		System.out.println(result.toString());
//		result.getOMElement("retCode", factory);
//		result.getOMElement("retMsg", factory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        
	}
}
