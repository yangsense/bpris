package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscLocStudyStaBean extends DataContainer implements DataContainerInterface,IAiscLocStudyStaValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscLocStudySta";



  public final static  String S_LocStudystaId = "LOC_STUDYSTA_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStatus = "STUDY_STATUS";
  public final static  String S_StatusName = "STATUS_NAME";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscLocStudyStaBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initLocStudystaId(long value){
     this.initProperty(S_LocStudystaId,new Long(value));
  }
  public  void setLocStudystaId(long value){
     this.set(S_LocStudystaId,new Long(value));
  }
  public  void setLocStudystaIdNull(){
     this.set(S_LocStudystaId,null);
  }

  public long getLocStudystaId(){
        return DataType.getAsLong(this.get(S_LocStudystaId));
  
  }
  public long getLocStudystaIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocStudystaId));
      }

  public void initLocId(long value){
     this.initProperty(S_LocId,new Long(value));
  }
  public  void setLocId(long value){
     this.set(S_LocId,new Long(value));
  }
  public  void setLocIdNull(){
     this.set(S_LocId,null);
  }

  public long getLocId(){
        return DataType.getAsLong(this.get(S_LocId));
  
  }
  public long getLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocId));
      }

  public void initStudyStatus(String value){
     this.initProperty(S_StudyStatus,value);
  }
  public  void setStudyStatus(String value){
     this.set(S_StudyStatus,value);
  }
  public  void setStudyStatusNull(){
     this.set(S_StudyStatus,null);
  }

  public String getStudyStatus(){
       return DataType.getAsString(this.get(S_StudyStatus));
  
  }
  public String getStudyStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyStatus));
      }

  public void initStatusName(String value){
     this.initProperty(S_StatusName,value);
  }
  public  void setStatusName(String value){
     this.set(S_StatusName,value);
  }
  public  void setStatusNameNull(){
     this.set(S_StatusName,null);
  }

  public String getStatusName(){
       return DataType.getAsString(this.get(S_StatusName));
  
  }
  public String getStatusNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StatusName));
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

