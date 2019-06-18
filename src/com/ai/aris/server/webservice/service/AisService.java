package com.ai.aris.server.webservice.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.webservice.bean.AisPatientInfoData;
import com.ai.aris.server.webservice.bean.AisStudyReportData;
import com.ai.aris.server.webservice.service.interfaces.IAisServiceSV;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.model.AisFilesInfoModel;

/**
 *
 *
 * 医院pacs数据上传中心http接口
 * 
 * @author wuzheng
 *
 */
@Controller
@RequestMapping("/aisService")
public class AisService {
	private static Logger logger = LoggerFactory.getLogger(AisService.class);
	IAisServiceSV serviceSv = (IAisServiceSV) ServiceFactory
			.getService(IAisServiceSV.class);

	@RequestMapping("/sendAiscData")
	@ResponseBody
	public JSONObject sendAiscData(
			@RequestHeader("interfaceType") String interfaceType,
			@RequestHeader("orgId") String orgId, @RequestBody String body)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0000");
		json.put("message", "sendAllData->：数据更新成功");
		if (body == null || "".equals(body)) {
			json.put("code", "0003");
			json.put("message", "sendAiscData-> body：接口请求体为空");
			return json;
		}
		if ("SYSTEM".equals(interfaceType)) {
			String result = serviceSv.sendData(body, orgId);
			if (!"success".equals(result)) {
				json.put("code", "0001");
				json.put("message", "sendAiscData->：系统数据接口更新失败");
			}
		}
		if ("BUSI".equals(interfaceType)) {
			String result = serviceSv.sendBusiData(body, orgId);
			if (!"success".equals(result)) {
				json.put("code", "0001");
				json.put("message", "sendAiscData->：业务数据接口更新失败");
			}
		}
		return json;
	}

	private String getPreDate(int days) {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = new Date();
		Calendar date = Calendar.getInstance();
		date.setTime(beginDate);
		date.set(Calendar.DATE, date.get(Calendar.DATE) - days);
		String dateStr = dft.format(date.getTime());
		return dateStr;
	}

	@RequestMapping("/upPatientInfo")
	@ResponseBody
	public JSONObject upPatientInfo(@RequestHeader("orgId") String orgId,@RequestBody AisPatientInfoData patient)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "upPatientInfo->：单条病人信息数据更新成功");
		if (patient.getPatientGlobalid()==0) {
			json.put("code", "-3");
			json.put("cenPatientID", "");
			json.put("message", "upPatientInfo-> body：单条病人信息接口请求体为空");
			return json;
		}
		String result = serviceSv.upPatientInfo(patient,orgId);
		if ("".equals(result)) {
			json.put("code", "-1");
			json.put("cenPatientID", "");
			json.put("message", "upPatientInfo->：单条病人信息数据接口更新失败");
		}else{
			json.put("cenGlobalID", result);
		}
		return json;
	}

	@RequestMapping("/upStudyInfo")
	@ResponseBody
	public JSONObject upStudyInfo(@RequestHeader("orgId") String orgId,@RequestBody AisStudyInfoBean studyinfo)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "upStudyInfo->：单条检查信息数据更新成功");
		if (studyinfo.getStudyinfoId() == 0) {
			json.put("code", "-3");
			json.put("cenStudyinfoID", "");
			json.put("message", "upStudyInfo-> body：单条检查信息接口请求体为空");
			return json;
		}
		String result = serviceSv.upStudyInfo(studyinfo,orgId);
		if ("".equals(result)) {
			json.put("code", "-1");
			json.put("cenStudyinfoID", "");
			json.put("message", "upStudyInfo->：单条检查信息数据接口更新失败");
		}else{
			json.put("cenStudyinfoID", result);
		}
		return json;
	}
	
	@RequestMapping("/upStudyItemInfo")
	@ResponseBody
	public JSONObject upStudyItemInfo(@RequestHeader("orgId") String orgId,@RequestBody AisStudyItemInfoBean[] studyitemInfo)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "upStudyItemInfo->：单条检查项目信息数据更新成功");
		try {
			if(studyitemInfo.length>0){
				for(AisStudyItemInfoBean bean:studyitemInfo){
					serviceSv.upStudyItemInfo(bean,orgId);
				}
			}
		} catch (Exception e) {
			json.put("code", "-1");
			json.put("message", "upStudyItemInfo-> body：单条检查项目信息数据更新失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/upHzloc")
	@ResponseBody
	public JSONObject upHzloc(@RequestHeader("studyinfoId") String studyinfoId,@RequestHeader("orgId") String orgId,@RequestHeader("conlocId") String conlocId,@RequestHeader("conorgId") String conorgId,
			@RequestHeader("locId") String locId,@RequestBody AiscLocBean aiscloc)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "upHzloc->：会诊科室信息更新成功");
		try {
			if (aiscloc.getLocId() == 0) {
				json.put("code", "-3");
				json.put("message", "upHzloc-> body：会诊科室信息数据请求体为空");
				return json;
			}
			String result = serviceSv.upHzloc(studyinfoId,orgId,conlocId,conorgId,locId,aiscloc);
			if ("".equals(result)) {
				json.put("code", "-1");
				json.put("message", "upHzloc->：单条检查信息数据接口更新失败");
			}
		} catch (Exception e) {
			json.put("code", "-1");
			json.put("message", "upHzloc-> body：会诊科室信息数据更新失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/saveFilePDFUrl")
	@ResponseBody
	public JSONObject saveFilePDFUrl(@RequestBody AisFilesInfoModel filemodel)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "saveFilePDFUrl->：电子病例中心库插入成功");
		try {
			if (filemodel.getStudyinfoId() == 0) {
				json.put("code", "-3");
				json.put("message", "saveFilePDFUrl-> body:检查记录id为空");
				return json;
			}
			String result = serviceSv.saveFilePDFUrl(filemodel);
			if ("".equals(result)) {
				json.put("code", "-1");
				json.put("message", "saveFilePDFUrl->：电子病例中心库插入失败");
			}
		} catch (Exception e) {
			json.put("code", "-1");
			json.put("message", "saveFilePDFUrl-> body：电子病例中心库插入失败");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/reportBack")
	@ResponseBody
	public JSONObject reportBack(@RequestHeader("hzOrgId") String hzOrgId,@RequestHeader("studyAccnumber") String studyAccnumber,@RequestHeader("ipport") String ipport,
			@RequestHeader("reportDoc") String reportDoc,@RequestHeader("verifyDoc") String verifyDoc,@RequestHeader("studyTime") String studyTime,@RequestBody AisStudyReportData report)
			throws Exception {
		JSONObject json = new JSONObject();
		json.put("code", "0");
		json.put("message", "reportBack->：会诊记录报告回传成功");
		try {
			Map map = serviceSv.reportBack(hzOrgId,studyAccnumber,report.getReportExam(),report.getReportResult(),ipport,reportDoc,verifyDoc,studyTime);
			if ("-1".equals(map.get("code"))) {
				json.put("code", "-1");
				json.put("message", map.get("message").toString());
			}
			if ("-2".equals(map.get("code"))) {
				json.put("code", "-2");
				json.put("message", map.get("message").toString());
			}
		} catch (Exception e) {
			json.put("code", "-3");
			json.put("message", "reportBack-> body：会诊记录报告回传失败");
			e.printStackTrace();
		}
		return json;
	}
}
