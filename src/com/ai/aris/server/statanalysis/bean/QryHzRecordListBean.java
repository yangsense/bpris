package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryHzRecordListBean extends DataContainer implements DataContainerInterface,IQryHzRecordListValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryHzRecordList";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_OperatorName = "OPERATOR_NAME";
  public final static  String S_StudyConsultlocname = "STUDY_CONSULTLOCNAME";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_StudyConsultorgname = "STUDY_CONSULTORGNAME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryHzRecordListBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initPatientSex(String value){
     this.initProperty(S_PatientSex,value);
  }
  public  void setPatientSex(String value){
     this.set(S_PatientSex,value);
  }
  public  void setPatientSexNull(){
     this.set(S_PatientSex,null);
  }

  public String getPatientSex(){
       return DataType.getAsString(this.get(S_PatientSex));
  
  }
  public String getPatientSexInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientSex));
      }

  public void initStudyConsultloc(long value){
     this.initProperty(S_StudyConsultloc,new Long(value));
  }
  public  void setStudyConsultloc(long value){
     this.set(S_StudyConsultloc,new Long(value));
  }
  public  void setStudyConsultlocNull(){
     this.set(S_StudyConsultloc,null);
  }

  public long getStudyConsultloc(){
        return DataType.getAsLong(this.get(S_StudyConsultloc));
  
  }
  public long getStudyConsultlocInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyConsultloc));
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

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
      }

  public void initOperatorName(String value){
     this.initProperty(S_OperatorName,value);
  }
  public  void setOperatorName(String value){
     this.set(S_OperatorName,value);
  }
  public  void setOperatorNameNull(){
     this.set(S_OperatorName,null);
  }

  public String getOperatorName(){
       return DataType.getAsString(this.get(S_OperatorName));
  
  }
  public String getOperatorNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OperatorName));
      }

  public void initStudyConsultlocname(String value){
     this.initProperty(S_StudyConsultlocname,value);
  }
  public  void setStudyConsultlocname(String value){
     this.set(S_StudyConsultlocname,value);
  }
  public  void setStudyConsultlocnameNull(){
     this.set(S_StudyConsultlocname,null);
  }

  public String getStudyConsultlocname(){
       return DataType.getAsString(this.get(S_StudyConsultlocname));
  
  }
  public String getStudyConsultlocnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultlocname));
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

  public void initCareprovName(String value){
     this.initProperty(S_CareprovName,value);
  }
  public  void setCareprovName(String value){
     this.set(S_CareprovName,value);
  }
  public  void setCareprovNameNull(){
     this.set(S_CareprovName,null);
  }

  public String getCareprovName(){
       return DataType.getAsString(this.get(S_CareprovName));
  
  }
  public String getCareprovNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CareprovName));
      }

  public void initStudyConsultorgname(String value){
     this.initProperty(S_StudyConsultorgname,value);
  }
  public  void setStudyConsultorgname(String value){
     this.set(S_StudyConsultorgname,value);
  }
  public  void setStudyConsultorgnameNull(){
     this.set(S_StudyConsultorgname,null);
  }

  public String getStudyConsultorgname(){
       return DataType.getAsString(this.get(S_StudyConsultorgname));
  
  }
  public String getStudyConsultorgnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultorgname));
      }

  public void initPatientAge(String value){
     this.initProperty(S_PatientAge,value);
  }
  public  void setPatientAge(String value){
     this.set(S_PatientAge,value);
  }
  public  void setPatientAgeNull(){
     this.set(S_PatientAge,null);
  }

  public String getPatientAge(){
       return DataType.getAsString(this.get(S_PatientAge));
  
  }
  public String getPatientAgeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAge));
      }

  public void initReportDatetime(Timestamp value){
     this.initProperty(S_ReportDatetime,value);
  }
  public  void setReportDatetime(Timestamp value){
     this.set(S_ReportDatetime,value);
  }
  public  void setReportDatetimeNull(){
     this.set(S_ReportDatetime,null);
  }

  public Timestamp getReportDatetime(){
        return DataType.getAsDateTime(this.get(S_ReportDatetime));
  
  }
  public Timestamp getReportDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportDatetime));
      }


 
 }

