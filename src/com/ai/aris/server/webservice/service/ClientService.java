package com.ai.aris.server.webservice.service;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AisServiceLogBean;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.basecode.bean.QryAiscOrdsubcateBean;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.interfacereal.bean.QryPatientInfoINFBean;
import com.ai.aris.server.interfacereal.bean.QryStudyItemInterfaceBean;
import com.ai.aris.server.interfacereal.bean.QryStudyReportInterfaceBean;
import com.ai.aris.server.webservice.BusiDataEnum;
import com.ai.aris.server.webservice.DataEnum;
import com.ai.aris.server.webservice.bean.AisStudyReportData;
import com.ai.aris.server.webservice.model.SendDataResponse;
import com.ai.aris.server.webservice.service.interfaces.IAisServiceSV;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.PropertiesUtils;

public class ClientService {
    private static Logger logger = LoggerFactory.getLogger(ClientService.class);
    ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);
    IAisServiceSV serviceSv = (IAisServiceSV) ServiceFactory.getService(IAisServiceSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    
    //发送数据--------系统管理
    public SendDataResponse sendAllData(String orgId)  {
        SendDataResponse dataResponse = null ;
        String serviceName = "sendAllData";
        long st=System.currentTimeMillis();
        Timestamp stime=new Timestamp(System.currentTimeMillis());
        try {
        	Map map = getInterfaceData(orgId);
        	map.put("interfaceType", "SYSTEM");
        	dataResponse = sendAiscAllData(map);
        }catch (Exception e){
            e.printStackTrace();
            dataResponse.setCode("1111");
            dataResponse.setMessage("sendAllData-> 系统异常！");
        }finally{
            try{
                this.addLog(stime,st,serviceName,dataResponse.getCode(),dataResponse.getMessage(),"0");
            }catch (Exception e) {
                logger.error("sendAllData-> 系统管理数据接口失败",e);
            }
        }
        return dataResponse;
    }
    
    public Map getInterfaceData(String orgId){
    	Map map = new HashMap();
    	try {
	    	//13、服务器
	        List list1 = sendAiscDataMap(orgId,AiscServerInfoBean.class,DataEnum.SEVERINFO,false);
	        //3、房间信息
	        List list2 = sendAiscDataMap(orgId,AiscRoomBean.class,DataEnum.ROOM,true);
	        //1、科室信息
	        List list3 =  sendAiscDataMap(orgId, AiscLocBean.class ,DataEnum.LOC,true);
	        //2、设备信息
	        List list4 =  sendAiscDataMap(orgId,AiscEquipmentBean.class,DataEnum.EQUIPMENT,true);
	        //4、检查大类----无机构，只执行一次
	        List list5 =  sendAiscDataMap(orgId,AiscOrdCategoryBean.class,DataEnum.ORDCATEGORY,true);
	        //5、检查子类----无机构，只执行一次
	        List list6 =  sendAiscDataMap(orgId,QryAiscOrdsubcateBean.class,DataEnum.ORDSUBCATEGORY,true);
	        //6、检查项目----无机构，只执行一次
	        List list7 =  sendAiscDataMap(orgId,AiscItemMastBean.class,DataEnum.ITEMMAST,true);
	        //7、检查部位----无机构，只执行一次
	        List list8 =  sendAiscDataMap(orgId,AiscBodyPartBean.class,DataEnum.BODYPART,true);
	        //8、检查项目部位对应
	        List list9 =  sendAiscDataMap(orgId,AiscBodyPart2ItemBean.class,DataEnum.BODYPART2ITEM,true);
	        //9、检查大类与科室关联
	        List list10 =  sendAiscDataMap(orgId,AiscOrdCat2LocBean.class,DataEnum.ORDCAT2LOC,true);
	        //10、医护人员维护
	        List list11 =  sendAiscDataMap(orgId,AiscCareProvBean.class,DataEnum.CAREPROV,true);
	        
	        map.put(DataEnum.SEVERINFO, list1);
	        map.put(DataEnum.ROOM, list2);
	        map.put(DataEnum.LOC, list3);
	        map.put(DataEnum.EQUIPMENT, list4);
	        map.put(DataEnum.ORDCATEGORY, list5);
	        map.put(DataEnum.ORDSUBCATEGORY, list6);
	        map.put(DataEnum.ITEMMAST, list7);
	        map.put(DataEnum.BODYPART, list8);
	        map.put(DataEnum.BODYPART2ITEM, list9);
	        map.put(DataEnum.ORDCAT2LOC, list10);
	        map.put(DataEnum.CAREPROV, list11);
	        
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    	return map;
    }
    
    
    //发送数据--------业务数据
    public SendDataResponse sendBusiData(String orgId) throws Exception  {
        SendDataResponse dataResponse = null ;
        String serviceName = "sendBusiData";
        long st=System.currentTimeMillis();
        Timestamp stime=new Timestamp(System.currentTimeMillis());
        DictItemBean item = dictSV.queryDictItem("SYN_INTERFACE")[0];
    	String mark = item.getItemNo();
        try {
        	Map map = getInterfaceBusiData(orgId,mark);
        	map.put("interfaceType", "BUSI");
        	dataResponse = sendAiscAllData(map);
        	if("0000".equals(dataResponse.getCode())){
        		List<AisStudyReportData> list = (List) map.get(BusiDataEnum.STUDYREPORT);
        		serviceSv.updateSynMark(list);
        	}
        }catch (Exception e){
            e.printStackTrace();
            dataResponse.setCode("1111");
            dataResponse.setMessage("sendBusiData-> 系统异常！");
        }finally{
            try{
                this.addLog(stime,st,serviceName,dataResponse.getCode(),dataResponse.getMessage(),mark);
            }catch (Exception e) {
                logger.error("sendAllData-> 业务数据接口失败",e);
            }
        }
        return dataResponse;
    }
    
    public Map getInterfaceBusiData(String orgId,String mark){
    	Map map = new HashMap();
    	try {
	    	//病人信息
	        List list1 = sendAiscBusiDataMap(mark,orgId, QryPatientInfoINFBean.class ,BusiDataEnum.PATIENTINFO,true);
	        //工作列表信息
	        List list2 = sendAiscBusiDataMap(mark,orgId, AisStudyInfoBean.class ,BusiDataEnum.STUDYINFO,true);
	     	//检查项目信息
	        List list3 = sendAiscBusiDataMap(mark,orgId, QryStudyItemInterfaceBean.class ,BusiDataEnum.STUDYITEMINFO,true);
	        //报告信息
	        List list4 = sendAiscBusiDataMap(mark,orgId, QryStudyReportInterfaceBean.class ,BusiDataEnum.STUDYREPORT,true);
	        
	        map.put(BusiDataEnum.PATIENTINFO, list1);
	        map.put(BusiDataEnum.STUDYINFO, list2);
	        map.put(BusiDataEnum.STUDYITEMINFO, list3);
	        map.put(BusiDataEnum.STUDYREPORT, list4);
	        
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    	return map;
    }
    
    public <K extends DataContainerInterface> List sendAiscDataMap(String orgId,Class<K> beanClz ,DataEnum dataEnum,Boolean... qryOrg)  {
        List dataList = new ArrayList();
        Boolean flag = false ;
        if (qryOrg!=null&&qryOrg.length>0){
            flag=qryOrg[0];
        }
        String sql = " 1=1 ";
        if(flag&&isNotBlank(orgId)){
        	sql+= " and org_Id = '"+orgId+"'"; 
        }
        try {
            K[] beans = commonSV.getBeans(sql, null, beanClz);
            dataList = BeanUtils.beanToList(beans, dataEnum.getDataClz());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return dataList;
    }
    
    public <K extends DataContainerInterface> List sendAiscBusiDataMap(String mark,String orgId,Class<K> beanClz ,BusiDataEnum dataEnum,Boolean... qryOrg) throws Exception  {
        List dataList = new ArrayList();
        Boolean flag = false ;
        if (qryOrg!=null&&qryOrg.length>0){
            flag=qryOrg[0];
        }
        String sql = " 1=1 ";
        if(flag&&isNotBlank(orgId)){
        	sql+= " and (org_Id = '"+orgId+"' or org_id is null) "; 
        	sql+= " and studystatus_code ='VERIFY' "; 
        }
        //增量时判断时间
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = df.format(date);
        if("STUDYREPORT".equals(dataEnum.toString())){
        	//sql+= " or REPORT_TOARCHIVE=2 ";
        }
        if(!"1".equals(mark)){
        	sql+= " and trunc(study_operationtime) = trunc(sysdate-1)"; 
        }else{
	        DictItemBean item = dictSV.queryDictItem("SYN_INTERFACE")[0];
	        sql+= " and trunc(study_operationtime)>=to_date('"+item.getMappingItemNo()+"','yyyy/mm/dd') and trunc(study_operationtime)<=to_date('"+item.getMappingDictName()+"','yyyy/mm/dd') ";
        }
        try {
            K[] beans = commonSV.getBeans(sql, null, beanClz);
            dataList = BeanUtils.beanToList(beans, dataEnum.getDataClz());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return dataList;
    }
    
    public static void main(String[] args) {
		ClientService cv = new ClientService();
		try {
            //发送数据
            //若传递对象没有orgId字段，则"ORG_ID": 标识符无效，需单独处理
			String orgId = PropertiesUtils.getProperties("sendOrg");
			cv.sendAllData(orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

    private SendDataResponse sendAiscAllData(Map map) {
    	SendDataResponse dataResponse = new SendDataResponse();
        if (map.size()<1){
            dataResponse.setCode("0002");
            dataResponse.setMessage("sendAiscAllData->不存在接口同步记录");
           logger.debug("sendAiscAllData-> 接口同步记录不存在！");
       }else {
           String sendDataPath = PropertiesUtils.getProperties("sendLocDataPath");
           String host = PropertiesUtils.getProperties("sendData.host");
           int port = Integer.valueOf(PropertiesUtils.getProperties("sendData.port"));
           String orgId = PropertiesUtils.getProperties("sendOrg");
           HttpHost httpHost = new HttpHost(host, port);
           HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
           httpRequestFactory.setConnectTimeout(60000*30);
           httpRequestFactory.setReadTimeout(60000*30);
           RestTemplate restTemplate = new RestTemplate();
           restTemplate.setRequestFactory(httpRequestFactory);
           HttpHeaders httpHeaders = new HttpHeaders();
           httpHeaders.add("orgId",orgId);
           httpHeaders.add("interfaceType",map.get("interfaceType").toString());
           HttpEntity<Map> entity = new HttpEntity(map,httpHeaders); //请求体、请求头
           ResponseEntity<SendDataResponse> responseEntitye = restTemplate.postForEntity(httpHost.toURI() + sendDataPath, entity, SendDataResponse.class);
           dataResponse = responseEntitye.getBody();
       }
       return dataResponse ;
   }
    
    private void addLog(Timestamp stime, long st, String serviceName, String serviceStatus, String errorMsg,String mark) throws Exception{
        //1写请求日志
        AisServiceLogBean  bean=new AisServiceLogBean();
        bean.setRequestTime(stime);
        bean.setServiceName(serviceName);
        bean.setServiceStatus(serviceStatus);
        bean.setErrorInfo(errorMsg);
        bean.setServiceBak(mark);
        bean.setTotal(String.valueOf(System.currentTimeMillis()-st));
        bean.setResponseTime(new Timestamp(System.currentTimeMillis()));
        serviceSv.writeServiceLog(bean);
    }

   

}
