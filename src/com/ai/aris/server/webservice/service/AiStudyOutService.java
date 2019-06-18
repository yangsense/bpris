package com.ai.aris.server.webservice.service;

import java.io.ByteArrayInputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sun.misc.BASE64Decoder;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.interfacereal.yinglian.QryAiStudyResultBean;
import com.ai.aris.server.webservice.bean.aitencent.AIStudyOutResponse;
import com.ai.aris.server.webservice.bean.aitencent.AIStudyResultRequest;
import com.ai.aris.server.webservice.bean.aitencent.AiImageUploadRequest;
import com.ai.aris.server.webservice.bean.aitencent.AiImageUploadResponse;
import com.ai.aris.server.webservice.bean.aitencent.YLStudyResultResponse;
import com.ai.aris.server.webservice.bean.aitencent.YLStudyResultResponse.Data;
import com.ai.aris.server.webservice.bean.yinglian.AiStudyUploadRequest;
import com.ai.aris.server.webservice.service.interfaces.IAiStudyOutSV;
import com.ai.common.util.FTPUtil;
import com.ai.common.util.JsonUtil;
import com.ai.common.util.PropertiesUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * 阜阳影联接口
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/aiStudyOutService")
public class AiStudyOutService {
	private static Logger logger = LoggerFactory.getLogger(AisService.class);
	IAiStudyOutSV aiStudyOutSV = (IAiStudyOutSV) ServiceFactory
			.getService(IAiStudyOutSV.class);
	IDictItemSV dictItemSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	@Autowired
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/studyupload",method = RequestMethod.POST)
	public void studyupload(@RequestHeader("partnerId") String partnerId,
            @RequestHeader("god-portal-signature") String god_portal_signature,
            @RequestHeader("god-portal-timestamp") String god_portal_timestamp,
            @RequestHeader("god-portal-request-id") String god_portal_request_id,
            @RequestBody String request,HttpServletResponse result)throws Exception{
		result.setCharacterEncoding("UTF-8");      //解决中文乱码问题
        PrintWriter out = result.getWriter();
		AIStudyOutResponse response = new AIStudyOutResponse();
		logger.debug("-----------------uuid--------start----------------:"+god_portal_request_id);
		if(!StringUtils.isNotBlank(partnerId)){
			response.setCode("-1");
			response.setPatientId("");
			response.setMessage("参数有误,partnerId参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_signature)){
			response.setCode("-1");
			response.setPatientId("");
			response.setMessage("参数有误,god-portal-signature参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_timestamp)){
			response.setCode("-1");
			response.setPatientId("");
			response.setMessage("参数有误,god-portal-timestamp参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_request_id)){
			response.setCode("-1");
			response.setPatientId("");
			response.setMessage("参数有误,god-portal-request_id参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		DictItemModel dictItem = dictItemSV.getDictItem("FY_YINGLIAN","FY_YINGLIAN");
		if(dictItem!=null){
			String parId = dictItem.getMappingItemNo();
			String token = dictItem.getMappingDictName();
			String sign = sha256(token,partnerId+god_portal_timestamp);
			if(!parId.equals(partnerId)){
				response.setCode("-2");
				response.setPatientId("");
				response.setMessage("partnerId参数有误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!sign.equals(god_portal_signature)){
				response.setCode("-2");
				response.setPatientId("");
				response.setMessage("签名错误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			JSONObject requestBody = JSONObject.fromObject(request);
			AiStudyUploadRequest requestBodyBean = (AiStudyUploadRequest) JSONObject.toBean(requestBody,AiStudyUploadRequest.class);
			if(!StringUtils.isNotBlank(requestBodyBean.getOrgCode())){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数OrgCode不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(requestBodyBean.getPatientID())){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数patientID不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(requestBodyBean.getPatientName())){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数patientName不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(requestBodyBean.getPatientBirthday())){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数patientBirthday不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getPatientAge()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数PatientAge不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getPatientGender()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数patientGender不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getPatientType()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数patientType不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getConsultOrgCode()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数ConsultOrgCode不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getLocCode()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数LocCode不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getAccesionNumber()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数AccesionNumber不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getAppDoc()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数AppDoc不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getStudyDate()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数studyDate不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getStudyEquipment()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数studyEquipment不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(String.valueOf(requestBodyBean.getStudyDesc()))){
				response.setCode("-3");
				response.setPatientId("");
				response.setMessage("body体请求参数StudyDesc不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			
			//插入病人信息表&检查信息表
			String patientId = aiStudyOutSV.insertStudyinfo(requestBodyBean);
			if(!"".equals(patientId)){
				response.setCode("0");
				response.setPatientId(patientId);
				response.setMessage("上传成功");
			}else{
				response.setCode("-4");
				response.setPatientId(patientId);
				response.setMessage("上传异常失败");
			}
			//插入接口日志表
			aiStudyOutSV.insertStudyinfoLog(god_portal_request_id,requestBodyBean.getOrgCode(),JsonUtil.toJson(requestBody),JSONObject.fromObject(response).toString(),"1");
			logger.debug("-----------------uuid--------end----------------:"+god_portal_request_id);
			
		}else{
			response.setCode("-2");
			response.setPatientId("");
			response.setMessage("服务端接口影联密钥参数未配置");
			throw new Exception("影联密钥参数未配置");
		}
		out.write(JSONObject.fromObject(response).toString());
        out.flush();
        out.close();
	}
	
	@RequestMapping(value = "/qryStudyResult",method = RequestMethod.POST)
	public void qryStudyResult(@RequestHeader("partnerId") String partnerId,
            @RequestHeader("god-portal-signature") String god_portal_signature,
            @RequestHeader("god-portal-timestamp") String god_portal_timestamp,
            @RequestHeader("god-portal-request-id") String god_portal_request_id,
            @RequestBody String request,HttpServletResponse result)throws Exception{
		result.setCharacterEncoding("UTF-8");      //解决中文乱码问题
        PrintWriter out = result.getWriter();
		YLStudyResultResponse response = new YLStudyResultResponse();
		logger.debug("-----------------uuid--------start----------------:"+god_portal_request_id);
		if(!StringUtils.isNotBlank(partnerId)){
			response.setCode("-1");
			response.setMessage("参数有误,partnerId参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_signature)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_signature参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_timestamp)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_timestamp参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_request_id)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_request_id参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		DictItemModel dictItem = dictItemSV.getDictItem("FY_YINGLIAN","FY_YINGLIAN");
		if(dictItem!=null){
			String parId = dictItem.getMappingItemNo();
			String token = dictItem.getMappingDictName();
			String sign = sha256(token,partnerId+god_portal_timestamp);
			if(!parId.equals(partnerId)){
				response.setCode("-2");
				response.setMessage("partnerId参数有误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!sign.equals(god_portal_signature)){
				response.setCode("-2");
				response.setMessage("签名错误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			JSONObject requestBody = JSONObject.fromObject(request);
			AIStudyResultRequest requestBodyBean = (AIStudyResultRequest) JSONObject.toBean(requestBody,AIStudyResultRequest.class);
			if(!StringUtils.isNotBlank(requestBodyBean.getAccesionNumber())){
				response.setCode("-3");
				response.setMessage("body体请求参数accesionNumber不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!StringUtils.isNotBlank(requestBodyBean.getOrgCode())){
				response.setCode("-3");
				response.setMessage("body体请求参数orgCode不能为空");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
			Data data = new Data();
			QryAiStudyResultBean report = aiStudyOutSV.getReportInfo(requestBodyBean.getAccesionNumber(),requestBodyBean.getOrgCode());
			if(report!=null&&"VERIFY".equals(report.getStudystatusCode())){
				data.setAccesionNumber(requestBodyBean.getAccesionNumber());
				data.setExamDesc(report.getReportExam());
				data.setResult(report.getReportResult());
				data.setReportDate(sdf.format(report.getReportDatetime()));
				data.setReportDoc(report.getReportDoc());
				data.setReportTime(sdf.format(report.getReportVerifydatetime()));
				response.setData(data);
				response.setCode("0");
				response.setMessage("会诊查询结果成功");
			}else if(report!=null&&!"VERIFY".equals(report.getStudystatusCode())){
				response.setData(data);
				response.setCode("0");
				response.setMessage("查询成功，但报告未审核，待返回结果");
			}else{
				response.setData(data);
				response.setCode("-4");
				response.setMessage("会诊查询结果异常失败");
			}
			//插入接口日志表
			aiStudyOutSV.insertStudyinfoLog(god_portal_request_id,requestBodyBean.getOrgCode(),JsonUtil.toJson(requestBody),JSONObject.fromObject(response).toString(),"2");
		}
		out.write(JSONObject.fromObject(response).toString());
        out.flush();
        out.close();
	}
	
	@RequestMapping(value = "/Imageupload",method = RequestMethod.POST)
	public void Imageupload(@RequestHeader("partnerId") String partnerId,
            @RequestHeader("god-portal-signature") String god_portal_signature,
            @RequestHeader("god-portal-timestamp") String god_portal_timestamp,
            @RequestHeader("god-portal-request-id") String god_portal_request_id,
            @RequestBody String request,HttpServletResponse result)throws Exception{
		result.setCharacterEncoding("UTF-8");      //解决中文乱码问题
        PrintWriter out = result.getWriter();
		AiImageUploadResponse response = new AiImageUploadResponse();
		logger.debug("-----------------uuid--------start----------------:"+god_portal_request_id);
		if(!StringUtils.isNotBlank(partnerId)){
			response.setCode("-1");
			response.setMessage("参数有误,partnerId参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_signature)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_signature参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_timestamp)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_timestamp参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		if(!StringUtils.isNotBlank(god_portal_request_id)){
			response.setCode("-1");
			response.setMessage("参数有误,god_portal_request_id参数为空");
			out.write(JSONObject.fromObject(response).toString());
	        out.flush();
	        out.close();
		}
		DictItemModel dictItem = dictItemSV.getDictItem("FY_YINGLIAN","FY_YINGLIAN");
		if(dictItem!=null){
			String parId = dictItem.getMappingItemNo();
			String token = dictItem.getMappingDictName();
			String sign = sha256(token,partnerId+god_portal_timestamp);
			if(!parId.equals(partnerId)){
				response.setCode("-2");
				response.setMessage("partnerId参数有误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			if(!sign.equals(god_portal_signature)){
				response.setCode("-2");
				response.setMessage("签名错误");
				out.write(JSONObject.fromObject(response).toString());
		        out.flush();
		        out.close();
			}
			JSONObject requestBody = JSONObject.fromObject(request);
			AiImageUploadRequest requestBodyBean = (AiImageUploadRequest) JSONObject.toBean(requestBody,AiImageUploadRequest.class);
			FTPUtil ftpUtil = new FTPUtil();
			String ip = PropertiesUtils.getContextProperty("yinglian_ftp_ip");
			String userName = PropertiesUtils.getContextProperty("yinglian_ftp_user");
			String pwd = PropertiesUtils.getContextProperty("yinglian_ftp_pwd");
	    	ftpUtil.connect(ip,21,userName,pwd);
//	    	String compString = ZipUtil.encodeBase64File("C:\\Users\\Administrator\\Desktop\\1.zip");
	    	String compString = requestBodyBean.getZipContent();
	    	byte[] buffer = new BASE64Decoder().decodeBuffer(compString);
	    	ByteArrayInputStream io = new ByteArrayInputStream(buffer);
	    	ftpUtil.uploadInputStream(requestBodyBean.getStudyInstanceUid()+"_"+requestBodyBean.getIndex()+".zip", io);
	        ftpUtil.closeChannel();
	        response.setCode("0");
			response.setMessage("success");
			//插入接口日志表
			aiStudyOutSV.insertStudyinfoLog(god_portal_request_id,"",JsonUtil.toJson(requestBody),JSONObject.fromObject(response).toString(),"3");
		}
		out.write(JSONObject.fromObject(response).toString());
        out.flush();
        out.close();
	}
	
	private final static char[] hexArray = "0123456789abcdef".toCharArray();
	
	public static String sha256(String token, String data) throws Exception {
		Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secret_key = new SecretKeySpec(token.getBytes("UTF-8"),
				"HmacSHA256");
		sha256_HMAC.init(secret_key);

		return bytesToHex(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
	}
	
	private static String bytesToHex(byte[] bytes) {
			char[] hexChars = new char[bytes.length * 2];
			for (int j = 0; j < bytes.length; j++) {
				int v = bytes[j] & 0xFF;
				hexChars[j * 2] = hexArray[v >>> 4];
				hexChars[j * 2 + 1] = hexArray[v & 0x0F];
			}
			return new String(hexChars);
	}
}
