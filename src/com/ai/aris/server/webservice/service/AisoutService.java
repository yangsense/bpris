package com.ai.aris.server.webservice.service;

import java.io.StringReader;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AisPixInfoBean;
import com.ai.aris.server.basecode.bean.AisServiceLogBean;
import com.ai.aris.server.webservice.service.interfaces.IAisServiceSV;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.common.util.XmlTransUtil;


public class AisoutService {
	 private static Logger logger = LoggerFactory.getLogger(AisoutService.class);
	 IAisServiceSV serviceSv = (IAisServiceSV) ServiceFactory.getService(IAisServiceSV.class);
	 IPatientRegSV patientSv = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);

	/**
	 * 上传病人的基本信息
	 * @return
	 * @throws Exception
	 */
	public String savePatientInfo(String reqXml){
		//logger.error("savePatientInfo:"+reqXml); 	
		com.ai.aris.server.webservice.bean.savePatientInfo.Response response = new com.ai.aris.server.webservice.bean.savePatientInfo.Response();
		boolean flag = false;
		boolean flag1 = false;
		String errorMsg = "";
		String serviceName = "savePatientInfo";
		long st=System.currentTimeMillis();
		Timestamp stime=new Timestamp(System.currentTimeMillis());
		String patientId ="";
		try {
			//解析上传xml
			Document doc =null;
			Element element = null;
			List nodeList=null;
			StringReader sr = new StringReader(reqXml); 
			InputSource is = new InputSource(sr); 
			SAXReader reader = new SAXReader();
			doc = reader.read(is);
	        String xPath="//jkda_data/ObjectInstance";
			List oiList=doc.selectNodes(xPath);
			int rowNums=oiList.size();
			
			for (int i = 0; i < rowNums; i++) {
				element=(Element) oiList.get(i);
				nodeList=element.selectNodes("Attribute");
				
				Map map = new HashMap();
				for(int j=0;j< nodeList.size();j++){
					element=(Element) nodeList.get(j);
					map.put(element.attributeValue("name").toLowerCase(), element.getText().trim());
					element.detach();
				}
				
				if(StringUtils.isBlank(map.get("Org_Id").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Org_Id is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Patient_HID").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Patient_HID is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Patient_Name").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Patient_Name is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Patient_Sex").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Patient_Sex is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Patient_Status").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Patient_Status is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				
				//查询主表ais_patientinfo
				AisPatientInfoBean[] patientBean = null;
				if(StringUtils.isBlank(map.get("Patient_IDNumber").toString())||!"null".equals(map.get("Patient_IDNumber"))){
					serviceSv.getAisPatientBean(map.get("Patient_IDNumber").toString());
				}
				
				//查询ais_pixinfo表中是否有记录
				AisPixInfoBean[] pixBean = serviceSv.getAisPixInfoBean(map.get("Patient_HID").toString(), map.get("Org_Id").toString());
				
				Map seqs = patientSv.getSeq(map.get("Org_Id").toString(),"-999");	
				long id = Long.parseLong(seqs.get("patientGlobalid").toString());
				String pacsPatientId  = seqs.get("patientId").toString();
				//该情况只有人为修改数据的时候才会出现
				if(pixBean!=null&&patientBean==null){
					//pixBean 更新
					
					//patient 插入
				}else if(pixBean==null&&patientBean==null){
					//patient 插入
					flag = serviceSv.insertPatientBean(id,pacsPatientId,map);
					//pixBean 插入
					flag1 =serviceSv.insertPixBean(id,map);
					patientId = pacsPatientId;
				}else if(pixBean==null&&patientBean!=null){
					//查询pacs已有病人信息
					if(patientBean!=null&&patientBean.length>0){
						for(int hh=0;hh<patientBean.length;hh++){
							//插入pixBean
							flag = serviceSv.insertPixBean(patientBean[hh].getPatientGlobalid(),map);
							//更新pacs已有病人信息
							flag = serviceSv.updatePatient(patientBean[hh].getPatientGlobalid(),map);
							if(patientBean.length>1){
								patientId += patientBean[hh].getPatientId()+"|";
							}else{
								patientId = patientBean[0].getPatientId();
							}							
						}
					}
				}else{
					//若pix和中心patient都有信息，则只更新病人信息
					if(pixBean!=null&&pixBean.length>0){
						for(int b=0;b<pixBean.length;b++){
							//更新pacs已有病人信息
							flag = serviceSv.updatePatient(pixBean[b].getPatientGlobalid(),map);
							if(patientBean.length>1){
								patientId += pixBean[b].getPatientId()+"|";
							}else{
								patientId = pixBean[0].getPatientId();
							}	
						}
					}
				}
			}
			if(flag&&flag1){
				response.setResultCode("0");
				response.setPatientid(patientId);
				response.setErrMsg("交易成功");
				return XmlTransUtil.getXmlFromBean(response);
			}else{
				response.setResultCode("1");
				response.setPatientid("");
				response.setErrMsg("交易失败");
				return XmlTransUtil.getXmlFromBean(response);
			}
		} catch (Exception e) {
			logger.error("savePatientInfo 上传病人的基本信息接口异常：",e);
			errorMsg = StringUtils.isBlank(e.getMessage())?"系统异常":e.getMessage();
			return responseFailure("-1","savePatientInfo 上传病人的基本信息接口异常");
		}finally{
			try{				
				this.addLog(stime,st,serviceName,response.getResultCode(),reqXml.substring(0,reqXml.length()>3000?3000:reqXml.length()),XmlTransUtil.getXmlFromBean(response),errorMsg);
			}catch (Exception e) {
				logger.error("savePatientInfo 上传病人的基本信息接口失败",e);
			}
		}
	}
	
	/**
	 * 上传报告内容
	 * @return
	 * @throws Exception
	 */
	public String saveReportInfo(String reqXml){
		//logger.error("savePatientInfo:"+reqXml); 	
		com.ai.aris.server.webservice.bean.savePatientInfo.Response response = new com.ai.aris.server.webservice.bean.savePatientInfo.Response();
		boolean flag = false;
		String errorMsg = "";
		String serviceName = "saveReportInfo";
		long st=System.currentTimeMillis();
		Timestamp stime=new Timestamp(System.currentTimeMillis());
		try {
			//解析上传xml
			Document doc =null;
			Element element = null;
			List nodeList=null;
			StringReader sr = new StringReader(reqXml); 
			InputSource is = new InputSource(sr); 
			SAXReader reader = new SAXReader();
			doc = reader.read(is);
	        String xPath="//jkda_data/ObjectInstance";
			List oiList=doc.selectNodes(xPath);
			int rowNums=oiList.size();
			String accessNumber = "";
			for (int i = 0; i < rowNums; i++) {
				element=(Element) oiList.get(i);
				nodeList=element.selectNodes("Attribute");
				
				Map map = new HashMap();
				for(int j=0;j< nodeList.size();j++){
					element=(Element) nodeList.get(j);
					map.put(element.attributeValue("name").toLowerCase(), element.getText().trim());
					element.detach();
				}
				
				if(StringUtils.isBlank(map.get("ORG_ID").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，ORG_ID is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Loc_code").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Loc_code is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("StudyStatus_Code").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，StudyStatus_Code is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Patient_HID").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Patient_HID is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("PatientType_Code").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，PatientType_Code is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Study_AppDateTime").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Study_AppDateTime is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				if(StringUtils.isBlank(map.get("Study_Accnumber").toString())){
					response.setResultCode("1");
					response.setErrMsg("交易失败，Study_Accnumber is null or empty");
					return XmlTransUtil.getXmlFromBean(response);
				}
				
				accessNumber = map.get("Study_Accnumber").toString();
				//插入登记表和report表
				flag = serviceSv.insertStudyInfo(map);				
			}
			if(flag){
				response.setResultCode("0");
				response.setAccessNumber(accessNumber);
				response.setErrMsg("交易成功");
				return XmlTransUtil.getXmlFromBean(response);
			}else{
				response.setResultCode("1");
				response.setAccessNumber(accessNumber);
				response.setErrMsg("交易失败");
				return XmlTransUtil.getXmlFromBean(response);
			}
		} catch (Exception e) {
			logger.error("saveReportInfo 上传报告内容接口异常：",e);
			errorMsg = StringUtils.isBlank(e.getMessage())?"系统异常":e.getMessage();
			return responseFailure("-1","saveReportInfo 上传报告内容接口异常");
		}finally{
			try{				
				this.addLog(stime,st,serviceName,response.getResultCode(),reqXml.substring(0,reqXml.length()>3000?3000:reqXml.length()),XmlTransUtil.getXmlFromBean(response),errorMsg);
			}catch (Exception e) {
				logger.error("saveReportInfo 上传报告内容接口失败",e);
			}
		}
	}
	
	
	
	
	//异常报文回执
	private static String responseFailure(String code,String errorMsg){
		Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("Reponse");
        root.addElement("ResultCode").addText(code);
        root.addElement("errMsg").addText(errorMsg);      
        logger.error(doc.asXML());
        return doc.asXML();
    }
	
	private void addLog(Timestamp  stime,long st,String serviceName,String serviceStatus,String xmlReq,String xmlRsp,String errorMsg) throws Exception{
		//1写请求日志
		AisServiceLogBean  bean=new AisServiceLogBean();
		bean.setRequestTime(stime);
		bean.setServiceName(serviceName);
		bean.setServiceRequest(xmlReq);
		bean.setServiceStatus(serviceStatus);
		bean.setServiceResponse(xmlRsp);
		bean.setErrorInfo(errorMsg);
		bean.setTotal(String.valueOf(System.currentTimeMillis()-st));
		bean.setResponseTime(new Timestamp(System.currentTimeMillis()));
		serviceSv.writeServiceLog(bean);
	}
}
