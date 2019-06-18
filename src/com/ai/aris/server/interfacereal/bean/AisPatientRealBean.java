package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AisPatientRealBean extends DataContainer implements DataContainerInterface,IAisPatientRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AisPatientReal";



  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldGlobalId = "OLD_GLOBAL_ID";
  public final static  String S_PatientRealId = "PATIENT_REAL_ID";
  public final static  String S_GlobalId = "GLOBAL_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisPatientRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initOldGlobalId(long value){
     this.initProperty(S_OldGlobalId,new Long(value));
  }
  public  void setOldGlobalId(long value){
     this.set(S_OldGlobalId,new Long(value));
  }
  public  void setOldGlobalIdNull(){
     this.set(S_OldGlobalId,null);
  }

  public long getOldGlobalId(){
        return DataType.getAsLong(this.get(S_OldGlobalId));
  
  }
  public long getOldGlobalIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldGlobalId));
      }

  public void initPatientRealId(long value){
     this.initProperty(S_PatientRealId,new Long(value));
  }
  public  void setPatientRealId(long value){
     this.set(S_PatientRealId,new Long(value));
  }
  public  void setPatientRealIdNull(){
     this.set(S_PatientRealId,null);
  }

  public long getPatientRealId(){
        return DataType.getAsLong(this.get(S_PatientRealId));
  
  }
  public long getPatientRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_PatientRealId));
      }

  public void initGlobalId(long value){
     this.initProperty(S_GlobalId,new Long(value));
  }
  public  void setGlobalId(long value){
     this.set(S_GlobalId,new Long(value));
  }
  public  void setGlobalIdNull(){
     this.set(S_GlobalId,null);
  }

  public long getGlobalId(){
        return DataType.getAsLong(this.get(S_GlobalId));
  
  }
  public long getGlobalIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_GlobalId));
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

