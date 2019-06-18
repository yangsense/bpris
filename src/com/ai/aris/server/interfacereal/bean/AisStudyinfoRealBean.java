package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AisStudyinfoRealBean extends DataContainer implements DataContainerInterface,IAisStudyinfoRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AisStudyinfoReal";



  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldStudyinfoId = "OLD_STUDYINFO_ID";
  public final static  String S_StudyinfoRealId = "STUDYINFO_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyinfoRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
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

  public void initOldStudyinfoId(long value){
     this.initProperty(S_OldStudyinfoId,new Long(value));
  }
  public  void setOldStudyinfoId(long value){
     this.set(S_OldStudyinfoId,new Long(value));
  }
  public  void setOldStudyinfoIdNull(){
     this.set(S_OldStudyinfoId,null);
  }

  public long getOldStudyinfoId(){
        return DataType.getAsLong(this.get(S_OldStudyinfoId));
  
  }
  public long getOldStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldStudyinfoId));
      }

  public void initStudyinfoRealId(long value){
     this.initProperty(S_StudyinfoRealId,new Long(value));
  }
  public  void setStudyinfoRealId(long value){
     this.set(S_StudyinfoRealId,new Long(value));
  }
  public  void setStudyinfoRealIdNull(){
     this.set(S_StudyinfoRealId,null);
  }

  public long getStudyinfoRealId(){
        return DataType.getAsLong(this.get(S_StudyinfoRealId));
  
  }
  public long getStudyinfoRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoRealId));
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

