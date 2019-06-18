package com.ai.aris.server.webservice.service;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisBean;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisDetailBean;
import com.ai.aris.server.interfacereal.model.AisAiDiagnosisModel;
import com.ai.aris.server.webservice.service.interfaces.IAiDiagnosisSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 影像辅助诊断-平台数据查询、新增及更新接口 Created by lenovo on 2017/9/27.
 */

@Controller
@RequestMapping("/aiDiagnosisService")
public class AiDiagnosisService {
	private static Logger logger = LoggerFactory.getLogger(AisService.class);
	IAiDiagnosisSV aiDiagnosisSV = (IAiDiagnosisSV) ServiceFactory
			.getService(IAiDiagnosisSV.class);
	IDictItemSV dictItemSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	private IStudyReportSV sv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping("/queryAiTestResult")
	@ResponseBody
	public JSONObject queryAiTestResult(@RequestBody String reqData) {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		result.put("code", "0000");
		result.put("message", "queryAiTestResult->：数据查询成功");
		try {
			if (reqData != null) {
				JSONObject jsonbody = JSONObject.fromObject(reqData);
				String orgId = (String) jsonbody.get("orgId");
				String studyId = (String) jsonbody.get("studyId");
				if (StringUtils.isEmpty(orgId)) {
					result.put("code", "0002");
					result.put("message", "queryAiTestResult->：orgId 不能为空");
				} else if (StringUtils.isEmpty(studyId)) {
					result.put("code", "0003");
					result.put("message", "queryAiTestResult->：studyId 不能为空");
				} else {
					AisAiDiagnosisModel model = new AisAiDiagnosisModel();
					model.setStudyId(studyId);
					model.setOrgId(orgId);
					AisAiDiagnosisBean diagnosisBean = aiDiagnosisSV
							.queryAiDiagnosis(model);
					if (diagnosisBean != null) {
						data.put("result", diagnosisBean.getResultId());
						data.put("resultMsg", diagnosisBean.getResultMsg());
						data.put("status", diagnosisBean.getStatus());
						result.put("data", data);
					} else {
						data.put("result", "-1");
						data.put("resultMsg", "无数据");
						data.put("status", "1");
						result.put("data", data);
					}
				}

			} else {
				result.put("code", "0001");
				result.put("message", "queryAiTestResult->：查询参数不正确或为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", "9999");
			result.put("message",
					"queryAiTestResult->：操作失败，error：" + e.getMessage());
		}
		return result;
	}

	/**
	 * AI检查信息保存
	 * 
	 * @param reqData
	 * @return
	 */
	@RequestMapping("/saveAiResult")
	@ResponseBody
	public JSONObject saveAiResult(@RequestBody String reqData) {
		JSONObject result = new JSONObject();
		JSONObject data = new JSONObject();
		result.put("code", "0000");
		result.put("message", "saveAiResult->：AI检查信息保存成功");
		try {
			if (reqData != null) {
				JSONObject jsonbody = JSONObject.fromObject(reqData);
				String orgId = (String) jsonbody.get("orgId");
				String studyId = (String) jsonbody.get("studyId");
				String studyName = (String) jsonbody.get("studyName");
				String studyDate = (String) jsonbody.get("studyDate");
				String studyType = (String) jsonbody.get("studyType");
				String patientName = (String) jsonbody.get("patientName");
				String patientId = (String) jsonbody.get("patientId");
				String patientGender = (String) jsonbody.get("patientGender");
				String patientBirthday = (String) jsonbody
						.get("patientBirthday");
				String uploadTime = (String) jsonbody.get("uploadTime");
				String imgNum = (String) jsonbody.get("imgNum");
				String code = (String) jsonbody.get("code");
				String message = (String) jsonbody.get("message");

				if (StringUtils.isEmpty(orgId)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：orgId 不能为空");
				} else if (StringUtils.isEmpty(studyId)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：studyId 不能为空");
				} else if (StringUtils.isEmpty(studyName)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：studyName 不能为空");
				} else if (StringUtils.isEmpty(studyDate)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：studyDate 不能为空");
				} else if (StringUtils.isEmpty(studyType)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：studyType 不能为空");
				} else if (StringUtils.isEmpty(patientName)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：patientName 不能为空");
				} else if (StringUtils.isEmpty(patientId)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：patientId 不能为空");
				} else if (StringUtils.isEmpty(patientGender)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：patientGender 不能为空");
				} else if (StringUtils.isEmpty(patientBirthday)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：patientBirthday 不能为空");
				} else if (StringUtils.isEmpty(uploadTime)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：uploadTime 不能为空");
				} else if (StringUtils.isEmpty(imgNum)) {
					result.put("code", "0002");
					result.put("message", "saveAiResult->：imgNum 不能为空");
				} else {
					Timestamp dateTime=new Timestamp(new Date().getTime());
					AisAiDiagnosisBean diagnosisBean = new AisAiDiagnosisBean();
					diagnosisBean.setStudyId(studyId);
					diagnosisBean.setOrgId(orgId);
					diagnosisBean.setStudyDate(studyDate);
					diagnosisBean.setStudyName(URLDecoder.decode(studyName,
							"UTF-8"));
					diagnosisBean.setStudyType(Long.parseLong(studyType));
					diagnosisBean.setPatientId(patientId);
					diagnosisBean.setPatientName(URLDecoder.decode(patientName,
							"UTF-8"));
					diagnosisBean.setPatientGender(Long
							.parseLong(patientGender));
					diagnosisBean.setPatientBirthday(patientBirthday);
					diagnosisBean.setUpdateTime(dateTime);
					diagnosisBean.setUploadImgNum(Long.parseLong(imgNum));
					diagnosisBean.setCode(code);
					diagnosisBean.setMessage(message);
					diagnosisBean.setStatus("2"); // 状态: 1未上传；2：已上传；3已处理；4处理失败

					Boolean flag = aiDiagnosisSV.saveAiDiagnosis(diagnosisBean);
					if (flag == false) {
						data.put("result", "-1");
						data.put("resultMsg", "保存失败");
					}
				}

			} else {
				result.put("code", "0001");
				result.put("message", "saveAiResult->：请求参数不正确或为空");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("code", "9999");
			result.put("message", "saveAiResult->：操作失败，error：" + e.getMessage());
		}
		return result;
	}

	/**
	 * AI检查信息更新
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateAiResult")
	@ResponseBody
	public JSONObject updateAiResult(@RequestBody String request) {
		JSONObject resp = new JSONObject();
		resp.put("code", "0000");
		resp.put("message", "成功");
		try {
			JSONObject responseObj = JSONObject.fromObject(request);
			String code = (String) responseObj.get("code");
			String studyId = (String) responseObj.get("studyId");
			String status = (String) responseObj.get("status");
			String result = (String) responseObj.get("result");
			String orgId = (String) responseObj.get("orgId");
			String message = (String) responseObj.get("message");
			JSONArray aa = responseObj.getJSONArray("markList");
			AisAiDiagnosisBean diagnosisBean = new AisAiDiagnosisBean();
			diagnosisBean.setStudyId(studyId);
			diagnosisBean.setOrgId(orgId);
			diagnosisBean.setCode(code);
			diagnosisBean.setUpdateTime(new Timestamp(System
					.currentTimeMillis()));
			diagnosisBean.setResultId(Integer.parseInt(result));
			diagnosisBean.setMessage(message);
			diagnosisBean.setStatus(status);
			AisAiDiagnosisModel model = new AisAiDiagnosisModel();
			model.setStudyId(studyId);
			model.setOrgId(orgId);
			AisAiDiagnosisBean oldBean = aiDiagnosisSV.queryAiDiagnosis(model);	
			diagnosisBean.setGlobalStudyId(oldBean.getGlobalStudyId());
			String resultMsg = "处理失败";
			switch (result) {
			case "-3":
				resultMsg="连接引擎失败";
				break;
			case "-2":
				resultMsg="处理失败";
				break;
			case "-1":
				resultMsg="未处理";
				break;
			case "0":
				resultMsg="处理中";
				break;
			case "1":
				resultMsg="没有状态";
				break;
			case "2":
				resultMsg="正常";
				break;
			case "3":
				resultMsg="高风险";
				break;
			}
			diagnosisBean.setResultMsg(resultMsg);
			aiDiagnosisSV.updateAiDiagnosis(diagnosisBean);
			aiDiagnosisSV.deleteAiDiagnosisDetail(diagnosisBean.getGlobalStudyId());
			Iterator<Object> it = aa.iterator();
			while (it.hasNext()) {
				JSONObject ob = (JSONObject) it.next();
				String dcmNoList = ob.getString("dcmNoList");
				String desc = ob.getString("desc");
				String markType = ob.getString("markType");
				String no = ob.getString("no");
				String seriesNo = ob.getString("seriesNo");
				String seriesUid = ob.getString("seriesUid");
				String shape = ob.getString("shape");
				
				JSONObject shapeDesc = ob.getJSONObject("shapeDesc");
//				JSONObject center = shapeDesc.getJSONObject("center");
//				JSONArray radiuses = shapeDesc.getJSONArray("radiuses");
//				for(Object obj:radiuses){
//					   System.out.println(obj);
//				}
				AisAiDiagnosisDetailBean aisAiDiagnosisDetailBean = new AisAiDiagnosisDetailBean();
				aisAiDiagnosisDetailBean.setGlobalStudyId(String
						.valueOf(diagnosisBean.getGlobalStudyId()));
				aisAiDiagnosisDetailBean.setNo(no);
				aisAiDiagnosisDetailBean.setDcmnolist(dcmNoList);
				aisAiDiagnosisDetailBean.setDescription(desc);
				aisAiDiagnosisDetailBean.setMarktype(Long.parseLong(markType));
				aisAiDiagnosisDetailBean.setShape(Long.parseLong(shape));
				aisAiDiagnosisDetailBean.setShapedesc(shapeDesc.toString());
				aisAiDiagnosisDetailBean.setSeriesno(Long.parseLong(seriesNo));
				aisAiDiagnosisDetailBean.setSeriesuid(seriesUid);
				aiDiagnosisSV.updateAidiagnoDetail(aisAiDiagnosisDetailBean);
			}
		} catch (IOException Io) {
			logger.error("updateAiResult更新失败-JSON格式转换异常" + Io.getMessage());
			resp.put("code", "1");
			resp.put("message", "JSON格式转换失败");
		} catch (Exception e) {
			logger.error("updateAiResult更新失败");
			resp.put("code", "1");
			resp.put("message", "updateAiResult->：操作失败，error：" + e.getMessage());
		} finally {
			return resp;
		}
	}

	/**
	 * 获取字典值
	 * 
	 * @param type
	 *            1 食管癌 AI_SGA 2 眼底 AI_TW 3肠镜 4 肺结节
	 * @return
	 */
	private String getResultMsg(String type, String id) {
		DictItemModel itemModel = new DictItemModel();

		try {
			if ("1".equals(type)) {
				itemModel = dictItemSV.getDictItem("AI_SGA", id);

			} else if ("2".equals(type)) {
				itemModel = dictItemSV.getDictItem("AI_TW", id);

			}
			if (itemModel != null) {

				return itemModel.getItemName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

	private Timestamp getDate(String dateStr) {
		Date date = new Date();
		Timestamp ts = null;
		// 注意format的格式要与日期String的格式相匹配
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			date = sdf1.parse(dateStr);
			String tsStr = sdf2.format(date);
			ts = new Timestamp(System.currentTimeMillis());
			ts = Timestamp.valueOf(tsStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return ts;
	}
	
	/**
     * ai数据上传------供影像浏览器调用
     * @param reportId
     * @param request
     * @return
     */
    @RequestMapping("aiDataUpload.ajax")
    @ResponseBody
    public JSONObject aiDataUpload(String studyaccNumber,String seriesNo,String seriesUid) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
        	jsonObj = sv.sendStudyinfoUp(studyaccNumber,seriesNo,seriesUid);
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--aiDataUpload:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }
	
	@RequestMapping("/SetStudyFilmStatus.ajax")
	@ResponseBody	
	public JSONObject SetStudyFilmStatus(String AccessionNumber)
			throws Exception { 
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("code", "0");
		jsonObj.put("message", "胶片打印状态修改成功");
		try { 
			if (StringUtils.isEmpty(AccessionNumber)) {
				jsonObj.put("code", "0002");
				jsonObj.put("message", "SetStudyFilmStatus->：AccesionNumber 不能为空");
			}else{
				String accNumber[] = AccessionNumber.split(",");
				for(int i=0;i<accNumber.length;i++){
					aiDiagnosisSV.updateFilmStatus(accNumber[i]);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			jsonObj.put("code", "-1");
			jsonObj.put("message",
					"SetStudyFilmStatus->：操作失败，error：" + e.getMessage());
		}       
	    return jsonObj; 
	}
}
