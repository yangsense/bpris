package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AisServiceLogBean extends DataContainer implements DataContainerInterface,IAisServiceLogValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AisServiceLog";



  public final static  String S_ResponseTime = "RESPONSE_TIME";
  public final static  String S_ServiceResponse = "SERVICE_RESPONSE";
  public final static  String S_ServiceBak = "SERVICE_BAK";
  public final static  String S_Total = "TOTAL";
  public final static  String S_ServiceRequest = "SERVICE_REQUEST";
  public final static  String S_RequestTime = "REQUEST_TIME";
  public final static  String S_ServiceStatus = "SERVICE_STATUS";
  public final static  String S_ServiceName = "SERVICE_NAME";
  public final static  String S_ServiceId = "SERVICE_ID";
  public final static  String S_ErrorInfo = "ERROR_INFO";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisServiceLogBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initResponseTime(Timestamp value){
     this.initProperty(S_ResponseTime,value);
  }
  public  void setResponseTime(Timestamp value){
     this.set(S_ResponseTime,value);
  }
  public  void setResponseTimeNull(){
     this.set(S_ResponseTime,null);
  }

  public Timestamp getResponseTime(){
        return DataType.getAsDateTime(this.get(S_ResponseTime));
  
  }
  public Timestamp getResponseTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ResponseTime));
      }

  public void initServiceResponse(String value){
     this.initProperty(S_ServiceResponse,value);
  }
  public  void setServiceResponse(String value){
     this.set(S_ServiceResponse,value);
  }
  public  void setServiceResponseNull(){
     this.set(S_ServiceResponse,null);
  }

  public String getServiceResponse(){
       return DataType.getAsString(this.get(S_ServiceResponse));
  
  }
  public String getServiceResponseInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServiceResponse));
      }

  public void initServiceBak(String value){
     this.initProperty(S_ServiceBak,value);
  }
  public  void setServiceBak(String value){
     this.set(S_ServiceBak,value);
  }
  public  void setServiceBakNull(){
     this.set(S_ServiceBak,null);
  }

  public String getServiceBak(){
       return DataType.getAsString(this.get(S_ServiceBak));
  
  }
  public String getServiceBakInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServiceBak));
      }

  public void initTotal(String value){
     this.initProperty(S_Total,value);
  }
  public  void setTotal(String value){
     this.set(S_Total,value);
  }
  public  void setTotalNull(){
     this.set(S_Total,null);
  }

  public String getTotal(){
       return DataType.getAsString(this.get(S_Total));
  
  }
  public String getTotalInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Total));
      }

  public void initServiceRequest(String value){
     this.initProperty(S_ServiceRequest,value);
  }
  public  void setServiceRequest(String value){
     this.set(S_ServiceRequest,value);
  }
  public  void setServiceRequestNull(){
     this.set(S_ServiceRequest,null);
  }

  public String getServiceRequest(){
       return DataType.getAsString(this.get(S_ServiceRequest));
  
  }
  public String getServiceRequestInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServiceRequest));
      }

  public void initRequestTime(Timestamp value){
     this.initProperty(S_RequestTime,value);
  }
  public  void setRequestTime(Timestamp value){
     this.set(S_RequestTime,value);
  }
  public  void setRequestTimeNull(){
     this.set(S_RequestTime,null);
  }

  public Timestamp getRequestTime(){
        return DataType.getAsDateTime(this.get(S_RequestTime));
  
  }
  public Timestamp getRequestTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_RequestTime));
      }

  public void initServiceStatus(String value){
     this.initProperty(S_ServiceStatus,value);
  }
  public  void setServiceStatus(String value){
     this.set(S_ServiceStatus,value);
  }
  public  void setServiceStatusNull(){
     this.set(S_ServiceStatus,null);
  }

  public String getServiceStatus(){
       return DataType.getAsString(this.get(S_ServiceStatus));
  
  }
  public String getServiceStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServiceStatus));
      }

  public void initServiceName(String value){
     this.initProperty(S_ServiceName,value);
  }
  public  void setServiceName(String value){
     this.set(S_ServiceName,value);
  }
  public  void setServiceNameNull(){
     this.set(S_ServiceName,null);
  }

  public String getServiceName(){
       return DataType.getAsString(this.get(S_ServiceName));
  
  }
  public String getServiceNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServiceName));
      }

  public void initServiceId(long value){
     this.initProperty(S_ServiceId,new Long(value));
  }
  public  void setServiceId(long value){
     this.set(S_ServiceId,new Long(value));
  }
  public  void setServiceIdNull(){
     this.set(S_ServiceId,null);
  }

  public long getServiceId(){
        return DataType.getAsLong(this.get(S_ServiceId));
  
  }
  public long getServiceIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServiceId));
      }

  public void initErrorInfo(String value){
     this.initProperty(S_ErrorInfo,value);
  }
  public  void setErrorInfo(String value){
     this.set(S_ErrorInfo,value);
  }
  public  void setErrorInfoNull(){
     this.set(S_ErrorInfo,null);
  }

  public String getErrorInfo(){
       return DataType.getAsString(this.get(S_ErrorInfo));
  
  }
  public String getErrorInfoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ErrorInfo));
      }


 
 }

