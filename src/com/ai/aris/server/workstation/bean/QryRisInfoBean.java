package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryRisInfoBean extends DataContainer implements DataContainerInterface,IQryRisInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryRisInfo";



  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryRisInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyEndtime(Timestamp value){
     this.initProperty(S_StudyEndtime,value);
  }
  public  void setStudyEndtime(Timestamp value){
     this.set(S_StudyEndtime,value);
  }
  public  void setStudyEndtimeNull(){
     this.set(S_StudyEndtime,null);
  }

  public Timestamp getStudyEndtime(){
        return DataType.getAsDateTime(this.get(S_StudyEndtime));
  
  }
  public Timestamp getStudyEndtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyEndtime));
      }

  public void initStudyItemdesc(String value){
     this.initProperty(S_StudyItemdesc,value);
  }
  public  void setStudyItemdesc(String value){
     this.set(S_StudyItemdesc,value);
  }
  public  void setStudyItemdescNull(){
     this.set(S_StudyItemdesc,null);
  }

  public String getStudyItemdesc(){
       return DataType.getAsString(this.get(S_StudyItemdesc));
  
  }
  public String getStudyItemdescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyItemdesc));
      }

  public void initPatientId(String value){
     this.initProperty(S_PatientId,value);
  }
  public  void setPatientId(String value){
     this.set(S_PatientId,value);
  }
  public  void setPatientIdNull(){
     this.set(S_PatientId,null);
  }

  public String getPatientId(){
       return DataType.getAsString(this.get(S_PatientId));
  
  }
  public String getPatientIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientId));
      }

  public void initStudyType(long value){
     this.initProperty(S_StudyType,new Long(value));
  }
  public  void setStudyType(long value){
     this.set(S_StudyType,new Long(value));
  }
  public  void setStudyTypeNull(){
     this.set(S_StudyType,null);
  }

  public long getStudyType(){
        return DataType.getAsLong(this.get(S_StudyType));
  
  }
  public long getStudyTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyType));
      }

  public void initStudyConsultorg(String value){
     this.initProperty(S_StudyConsultorg,value);
  }
  public  void setStudyConsultorg(String value){
     this.set(S_StudyConsultorg,value);
  }
  public  void setStudyConsultorgNull(){
     this.set(S_StudyConsultorg,null);
  }

  public String getStudyConsultorg(){
       return DataType.getAsString(this.get(S_StudyConsultorg));
  
  }
  public String getStudyConsultorgInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultorg));
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

  public void initStudyDatetime(Timestamp value){
     this.initProperty(S_StudyDatetime,value);
  }
  public  void setStudyDatetime(Timestamp value){
     this.set(S_StudyDatetime,value);
  }
  public  void setStudyDatetimeNull(){
     this.set(S_StudyDatetime,null);
  }

  public Timestamp getStudyDatetime(){
        return DataType.getAsDateTime(this.get(S_StudyDatetime));
  
  }
  public Timestamp getStudyDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyDatetime));
      }

  public void initStudyAccnumber(String value){
     this.initProperty(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumber(String value){
     this.set(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumberNull(){
     this.set(S_StudyAccnumber,null);
  }

  public String getStudyAccnumber(){
       return DataType.getAsString(this.get(S_StudyAccnumber));
  
  }
  public String getStudyAccnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAccnumber));
      }

  public void initPatientName(String value){
     this.initProperty(S_PatientName,value);
  }
  public  void setPatientName(String value){
     this.set(S_PatientName,value);
  }
  public  void setPatientNameNull(){
     this.set(S_PatientName,null);
  }

  public String getPatientName(){
       return DataType.getAsString(this.get(S_PatientName));
  
  }
  public String getPatientNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientName));
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

  public void initStudyOperationtime(Timestamp value){
     this.initProperty(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtime(Timestamp value){
     this.set(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtimeNull(){
     this.set(S_StudyOperationtime,null);
  }

  public Timestamp getStudyOperationtime(){
        return DataType.getAsDateTime(this.get(S_StudyOperationtime));
  
  }
  public Timestamp getStudyOperationtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyOperationtime));
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

  public void initStudyStarttime(Timestamp value){
     this.initProperty(S_StudyStarttime,value);
  }
  public  void setStudyStarttime(Timestamp value){
     this.set(S_StudyStarttime,value);
  }
  public  void setStudyStarttimeNull(){
     this.set(S_StudyStarttime,null);
  }

  public Timestamp getStudyStarttime(){
        return DataType.getAsDateTime(this.get(S_StudyStarttime));
  
  }
  public Timestamp getStudyStarttimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyStarttime));
      }


 
 }

