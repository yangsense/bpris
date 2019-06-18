package com.ai.aris.server.common.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.common.bean.*;

public class AisHzBackurlBean extends DataContainer implements DataContainerInterface,IAisHzBackurlValue{

  private static String  m_boName = "com.ai.aris.server.common.bean.AisHzBackurl";



  public final static  String S_BackUrl = "BACK_URL";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisHzBackurlBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initBackUrl(String value){
     this.initProperty(S_BackUrl,value);
  }
  public  void setBackUrl(String value){
     this.set(S_BackUrl,value);
  }
  public  void setBackUrlNull(){
     this.set(S_BackUrl,null);
  }

  public String getBackUrl(){
       return DataType.getAsString(this.get(S_BackUrl));
  
  }
  public String getBackUrlInitialValue(){
        return DataType.getAsString(this.getOldObj(S_BackUrl));
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

