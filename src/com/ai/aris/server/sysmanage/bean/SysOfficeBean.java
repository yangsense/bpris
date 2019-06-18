package com.ai.aris.server.sysmanage.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.sysmanage.bean.*;

public class SysOfficeBean extends DataContainer implements DataContainerInterface,ISysOfficeValue{

  private static String  m_boName = "com.ai.aris.server.sysmanage.bean.SysOffice";



  public final static  String S_CreatePerson = "CREATE_PERSON";
  public final static  String S_State = "STATE";
  public final static  String S_OfficeCode = "OFFICE_CODE";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_OfficeName = "OFFICE_NAME";
  public final static  String S_OfficeId = "OFFICE_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SysOfficeBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCreatePerson(String value){
     this.initProperty(S_CreatePerson,value);
  }
  public  void setCreatePerson(String value){
     this.set(S_CreatePerson,value);
  }
  public  void setCreatePersonNull(){
     this.set(S_CreatePerson,null);
  }

  public String getCreatePerson(){
       return DataType.getAsString(this.get(S_CreatePerson));
  
  }
  public String getCreatePersonInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CreatePerson));
      }

  public void initState(String value){
     this.initProperty(S_State,value);
  }
  public  void setState(String value){
     this.set(S_State,value);
  }
  public  void setStateNull(){
     this.set(S_State,null);
  }

  public String getState(){
       return DataType.getAsString(this.get(S_State));
  
  }
  public String getStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_State));
      }

  public void initOfficeCode(String value){
     this.initProperty(S_OfficeCode,value);
  }
  public  void setOfficeCode(String value){
     this.set(S_OfficeCode,value);
  }
  public  void setOfficeCodeNull(){
     this.set(S_OfficeCode,null);
  }

  public String getOfficeCode(){
       return DataType.getAsString(this.get(S_OfficeCode));
  
  }
  public String getOfficeCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OfficeCode));
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

  public void initOfficeName(String value){
     this.initProperty(S_OfficeName,value);
  }
  public  void setOfficeName(String value){
     this.set(S_OfficeName,value);
  }
  public  void setOfficeNameNull(){
     this.set(S_OfficeName,null);
  }

  public String getOfficeName(){
       return DataType.getAsString(this.get(S_OfficeName));
  
  }
  public String getOfficeNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OfficeName));
      }

  public void initOfficeId(String value){
     this.initProperty(S_OfficeId,value);
  }
  public  void setOfficeId(String value){
     this.set(S_OfficeId,value);
  }
  public  void setOfficeIdNull(){
     this.set(S_OfficeId,null);
  }

  public String getOfficeId(){
       return DataType.getAsString(this.get(S_OfficeId));
  
  }
  public String getOfficeIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OfficeId));
      }


 
 }

