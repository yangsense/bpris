package com.ai.aris.server.interfacereal.yinglian;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.yinglian.*;

public class AisFyYinglianLogBean extends DataContainer implements DataContainerInterface,IAisFyYinglianLogValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.yinglian.AisFyYinglianLog";



  public final static  String S_Responsebody = "RESPONSEBODY";
  public final static  String S_RequestBody = "REQUEST_BODY";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_InterfaceType = "INTERFACE_TYPE";
  public final static  String S_RequestId = "REQUEST_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisFyYinglianLogBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initResponsebody(String value){
     this.initProperty(S_Responsebody,value);
  }
  public  void setResponsebody(String value){
     this.set(S_Responsebody,value);
  }
  public  void setResponsebodyNull(){
     this.set(S_Responsebody,null);
  }

  public String getResponsebody(){
       return DataType.getAsString(this.get(S_Responsebody));
  
  }
  public String getResponsebodyInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Responsebody));
      }

  public void initRequestBody(String value){
     this.initProperty(S_RequestBody,value);
  }
  public  void setRequestBody(String value){
     this.set(S_RequestBody,value);
  }
  public  void setRequestBodyNull(){
     this.set(S_RequestBody,null);
  }

  public String getRequestBody(){
       return DataType.getAsString(this.get(S_RequestBody));
  
  }
  public String getRequestBodyInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestBody));
      }

  public void initCreateTime(Timestamp value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(Timestamp value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public Timestamp getCreateTime(){
        return DataType.getAsDateTime(this.get(S_CreateTime));
  
  }
  public Timestamp getCreateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTime));
      }

  public void initInterfaceType(String value){
     this.initProperty(S_InterfaceType,value);
  }
  public  void setInterfaceType(String value){
     this.set(S_InterfaceType,value);
  }
  public  void setInterfaceTypeNull(){
     this.set(S_InterfaceType,null);
  }

  public String getInterfaceType(){
       return DataType.getAsString(this.get(S_InterfaceType));
  
  }
  public String getInterfaceTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_InterfaceType));
      }

  public void initRequestId(String value){
     this.initProperty(S_RequestId,value);
  }
  public  void setRequestId(String value){
     this.set(S_RequestId,value);
  }
  public  void setRequestIdNull(){
     this.set(S_RequestId,null);
  }

  public String getRequestId(){
       return DataType.getAsString(this.get(S_RequestId));
  
  }
  public String getRequestIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestId));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }


 
 }

