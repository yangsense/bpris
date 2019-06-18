package com.ai.aris.web.controller.workstation;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.webservice.bean.OrgResponse;
import com.ai.aris.server.webservice.service.interfaces.IOrgByDunsSV;
import com.ai.aris.server.workstation.bean.QryReportBrowseBean;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;

@Controller
@RequestMapping("/reportBrowse")
public class ReportBrowseController {
	private Log logger = LogFactory.getLog(ReportBrowseController.class);
	private IStudyReportSV sv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
	@Autowired
    private IOrgByDunsSV orgByDunsSV;
	
	// 报告浏览器初始化
	@RequestMapping("reportBrowse.html")
	public String reportBrowse(HttpServletRequest request,Model model,String AccessionNumber,String PatientID,String name,String idNo,String birthDate,String duns,String patientType,String outInPatientId){
		try {
			if(isNotBlank(AccessionNumber)&&isNotBlank(PatientID)){
				QryReportBrowseBean reportBean = sv.getReportByAccnumber(AccessionNumber,PatientID);
				model.addAttribute("reportBean",reportBean); 
			}
			//健康档案浏览器调阅
			if(isNotBlank(name)||isNotBlank(idNo)||isNotBlank(birthDate)){
				String pacsName = URLDecoder.decode(name, "utf-8");
				QryReportBrowseBean reportBean = sv.getReportByOther(pacsName,idNo,birthDate);
				model.addAttribute("reportBean",reportBean); 
			}
			//his侧调用，通过orgId+住院号/门诊号+病人类型
			if(isNotBlank(duns)&&isNotBlank(patientType)&&isNotBlank(outInPatientId)){
				Map map = new HashMap();
	            map.put("duns",duns);
	            String orgId = "";
	            try{
	            OrgResponse org  = orgByDunsSV.retrieveOrg(map);
	            orgId = org.getSysOrgBean().getOrgId();
	            }catch(Exception e){
	            	e.printStackTrace();
	            } 
				QryReportBrowseBean reportBean = sv.getReportByInoutPatinetId(orgId,patientType,outInPatientId);
				model.addAttribute("reportBean",reportBean); 
			}
		} catch (Exception e) {
			logger.error("ReportBrowseController->reportBrowse error!" + e.getMessage());			 
		} 		
		
		return "reportBrowse/reportBrowse";
	}
	
	//影像浏览器获取影像------------调用实时上传影像RetrivePatientImage接口
    @RequestMapping("retrivePatientImage.ajax")
    @ResponseBody
    public JSONObject retrivePatientImage(String patientId,String studyAccnumber,String orgId) {    	   
    	JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
    	try {	
    		String result = sv.retrivePatientImage(patientId,studyAccnumber,orgId);
    		jsonObj.put("result", result);
    	}catch (Exception e) {
			jsonObj.put("success", false);
			jsonObj.put("result", "-99");
			logger.error("ReportBrowseController--retrivePatientImage.ajax:", e);
			e.printStackTrace();
		}   
	    return jsonObj; 
    }

	// 查询报告列表记录
	@RequestMapping("/queryReportList.ajax")
	@ResponseBody	
	public JSONObject queryReportList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String sidx,String sord,
			QryStudyInfoListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		// 取员工组织机构
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryReportList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
}
