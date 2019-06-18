package com.ai.aris.server.common.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.common.bean.*;

public class AisRightControlBean extends DataContainer implements DataContainerInterface,IAisRightControlValue{

  private static String  m_boName = "com.ai.aris.server.common.bean.AisRightControl";



  public final static  String S_InstallTime = "INSTALL_TIME";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_ExpirationTime = "EXPIRATION_TIME";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_Id = "ID";
  public final static  String S_ServerMac = "SERVER_MAC";
  public final static  String S_UpdateTime = "UPDATE_TIME";
  public final static  String S_CurrentTime = "CURRENT_TIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisRightControlBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initInstallTime(String value){
     this.initProperty(S_InstallTime,value);
  }
  public  void setInstallTime(String value){
     this.set(S_InstallTime,value);
  }
  public  void setInstallTimeNull(){
     this.set(S_InstallTime,null);
  }

  public String getInstallTime(){
       return DataType.getAsString(this.get(S_InstallTime));
  
  }
  public String getInstallTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_InstallTime));
      }

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
      }

  public void initOrgCode(String value){
     this.initProperty(S_OrgCode,value);
  }
  public  void setOrgCode(String value){
     this.set(S_OrgCode,value);
  }
  public  void setOrgCodeNull(){
     this.set(S_OrgCode,null);
  }

  public String getOrgCode(){
       return DataType.getAsString(this.get(S_OrgCode));
  
  }
  public String getOrgCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgCode));
      }

  public void initExpirationTime(String value){
     this.initProperty(S_ExpirationTime,value);
  }
  public  void setExpirationTime(String value){
     this.set(S_ExpirationTime,value);
  }
  public  void setExpirationTimeNull(){
     this.set(S_ExpirationTime,null);
  }

  public String getExpirationTime(){
       return DataType.getAsString(this.get(S_ExpirationTime));
  
  }
  public String getExpirationTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ExpirationTime));
      }

  public void initCreateTime(String value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(String value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public String getCreateTime(){
       return DataType.getAsString(this.get(S_CreateTime));
  
  }
  public String getCreateTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreateTime));
      }

  public void initId(String value){
     this.initProperty(S_Id,value);
  }
  public  void setId(String value){
     this.set(S_Id,value);
  }
  public  void setIdNull(){
     this.set(S_Id,null);
  }

  public String getId(){
       return DataType.getAsString(this.get(S_Id));
  
  }
  public String getIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Id));
      }

  public void initServerMac(String value){
     this.initProperty(S_ServerMac,value);
  }
  public  void setServerMac(String value){
     this.set(S_ServerMac,value);
  }
  public  void setServerMacNull(){
     this.set(S_ServerMac,null);
  }

  public String getServerMac(){
       return DataType.getAsString(this.get(S_ServerMac));
  
  }
  public String getServerMacInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServerMac));
      }

  public void initUpdateTime(String value){
     this.initProperty(S_UpdateTime,value);
  }
  public  void setUpdateTime(String value){
     this.set(S_UpdateTime,value);
  }
  public  void setUpdateTimeNull(){
     this.set(S_UpdateTime,null);
  }

  public String getUpdateTime(){
       return DataType.getAsString(this.get(S_UpdateTime));
  
  }
  public String getUpdateTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UpdateTime));
      }

  public void initCurrentTime(String value){
     this.initProperty(S_CurrentTime,value);
  }
  public  void setCurrentTime(String value){
     this.set(S_CurrentTime,value);
  }
  public  void setCurrentTimeNull(){
     this.set(S_CurrentTime,null);
  }

  public String getCurrentTime(){
       return DataType.getAsString(this.get(S_CurrentTime));
  
  }
  public String getCurrentTimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CurrentTime));
      }


 
 }

