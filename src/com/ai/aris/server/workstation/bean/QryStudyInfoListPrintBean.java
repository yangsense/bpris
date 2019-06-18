package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryStudyInfoListPrintBean extends DataContainer implements DataContainerInterface,IQryStudyInfoListPrintValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryStudyInfoListPrint";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_StudyConsultlocName = "STUDY_CONSULTLOC_NAME";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_ReportVerifytime = "REPORT_VERIFYTIME";
  public final static  String S_PatientDob = "PATIENT_DOB";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_Bodyitem = "BODYITEM";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_StudyConsultation = "STUDY_CONSULTATION";
  public final static  String S_CStudyDatetime = "C_STUDY_DATETIME";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_CStudyAppdate = "C_STUDY_APPDATE";
  public final static  String S_CStudyStarttime = "C_STUDY_STARTTIME";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_StudyAppdocname = "STUDY_APPDOCNAME";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_CStudyOperationtime = "C_STUDY_OPERATIONTIME";
  public final static  String S_StudyAppdoc = "STUDY_APPDOC";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_StudyApplocid = "STUDY_APPLOCID";
  public final static  String S_ReportIsprinted = "REPORT_ISPRINTED";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_StudyConsultstarttime = "STUDY_CONSULTSTARTTIME";
  public final static  String S_StudyDoctorname = "STUDY_DOCTORNAME";
  public final static  String S_CStudyEndtime = "C_STUDY_ENDTIME";
  public final static  String S_StudyConsultorgName = "STUDY_CONSULTORG_NAME";
  public final static  String S_StudyHisappid = "STUDY_HISAPPID";
  public final static  String S_ReportFinaldoctorname = "REPORT_FINALDOCTORNAME";
  public final static  String S_IsUrgent = "IS_URGENT";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_StudyConsultdoctorid = "STUDY_CONSULTDOCTORID";
  public final static  String S_StudyConsultendtime = "STUDY_CONSULTENDTIME";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_StudyBedno = "STUDY_BEDNO";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryStudyInfoListPrintBean() throws AIException{
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

  public void initStudyDoctorid(String value){
     this.initProperty(S_StudyDoctorid,value);
  }
  public  void setStudyDoctorid(String value){
     this.set(S_StudyDoctorid,value);
  }
  public  void setStudyDoctoridNull(){
     this.set(S_StudyDoctorid,null);
  }

  public String getStudyDoctorid(){
       return DataType.getAsString(this.get(S_StudyDoctorid));
  
  }
  public String getStudyDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDoctorid));
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

  public void initStudyConsultlocName(String value){
     this.initProperty(S_StudyConsultlocName,value);
  }
  public  void setStudyConsultlocName(String value){
     this.set(S_StudyConsultlocName,value);
  }
  public  void setStudyConsultlocNameNull(){
     this.set(S_StudyConsultlocName,null);
  }

  public String getStudyConsultlocName(){
       return DataType.getAsString(this.get(S_StudyConsultlocName));
  
  }
  public String getStudyConsultlocNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultlocName));
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

  public void initPatientGlobalid(long value){
     this.initProperty(S_PatientGlobalid,new Long(value));
  }
  public  void setPatientGlobalid(long value){
     this.set(S_PatientGlobalid,new Long(value));
  }
  public  void setPatientGlobalidNull(){
     this.set(S_PatientGlobalid,null);
  }

  public long getPatientGlobalid(){
        return DataType.getAsLong(this.get(S_PatientGlobalid));
  
  }
  public long getPatientGlobalidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_PatientGlobalid));
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

  public void initReportVerifytime(String value){
     this.initProperty(S_ReportVerifytime,value);
  }
  public  void setReportVerifytime(String value){
     this.set(S_ReportVerifytime,value);
  }
  public  void setReportVerifytimeNull(){
     this.set(S_ReportVerifytime,null);
  }

  public String getReportVerifytime(){
       return DataType.getAsString(this.get(S_ReportVerifytime));
  
  }
  public String getReportVerifytimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifytime));
      }

  public void initPatientDob(String value){
     this.initProperty(S_PatientDob,value);
  }
  public  void setPatientDob(String value){
     this.set(S_PatientDob,value);
  }
  public  void setPatientDobNull(){
     this.set(S_PatientDob,null);
  }

  public String getPatientDob(){
       return DataType.getAsString(this.get(S_PatientDob));
  
  }
  public String getPatientDobInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientDob));
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

  public void initBodyitem(String value){
     this.initProperty(S_Bodyitem,value);
  }
  public  void setBodyitem(String value){
     this.set(S_Bodyitem,value);
  }
  public  void setBodyitemNull(){
     this.set(S_Bodyitem,null);
  }

  public String getBodyitem(){
       return DataType.getAsString(this.get(S_Bodyitem));
  
  }
  public String getBodyitemInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Bodyitem));
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

  public void initStudyConsultation(String value){
     this.initProperty(S_StudyConsultation,value);
  }
  public  void setStudyConsultation(String value){
     this.set(S_StudyConsultation,value);
  }
  public  void setStudyConsultationNull(){
     this.set(S_StudyConsultation,null);
  }

  public String getStudyConsultation(){
       return DataType.getAsString(this.get(S_StudyConsultation));
  
  }
  public String getStudyConsultationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultation));
      }

  public void initCStudyDatetime(String value){
     this.initProperty(S_CStudyDatetime,value);
  }
  public  void setCStudyDatetime(String value){
     this.set(S_CStudyDatetime,value);
  }
  public  void setCStudyDatetimeNull(){
     this.set(S_CStudyDatetime,null);
  }

  public String getCStudyDatetime(){
       return DataType.getAsString(this.get(S_CStudyDatetime));
  
  }
  public String getCStudyDatetimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyDatetime));
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

  public void initCStudyAppdate(String value){
     this.initProperty(S_CStudyAppdate,value);
  }
  public  void setCStudyAppdate(String value){
     this.set(S_CStudyAppdate,value);
  }
  public  void setCStudyAppdateNull(){
     this.set(S_CStudyAppdate,null);
  }

  public String getCStudyAppdate(){
       return DataType.getAsString(this.get(S_CStudyAppdate));
  
  }
  public String getCStudyAppdateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyAppdate));
      }

  public void initCStudyStarttime(String value){
     this.initProperty(S_CStudyStarttime,value);
  }
  public  void setCStudyStarttime(String value){
     this.set(S_CStudyStarttime,value);
  }
  public  void setCStudyStarttimeNull(){
     this.set(S_CStudyStarttime,null);
  }

  public String getCStudyStarttime(){
       return DataType.getAsString(this.get(S_CStudyStarttime));
  
  }
  public String getCStudyStarttimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyStarttime));
      }

  public void initStudyAppdate(Timestamp value){
     this.initProperty(S_StudyAppdate,value);
  }
  public  void setStudyAppdate(Timestamp value){
     this.set(S_StudyAppdate,value);
  }
  public  void setStudyAppdateNull(){
     this.set(S_StudyAppdate,null);
  }

  public Timestamp getStudyAppdate(){
        return DataType.getAsDateTime(this.get(S_StudyAppdate));
  
  }
  public Timestamp getStudyAppdateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyAppdate));
      }

  public void initReportVerifydoctorid(String value){
     this.initProperty(S_ReportVerifydoctorid,value);
  }
  public  void setReportVerifydoctorid(String value){
     this.set(S_ReportVerifydoctorid,value);
  }
  public  void setReportVerifydoctoridNull(){
     this.set(S_ReportVerifydoctorid,null);
  }

  public String getReportVerifydoctorid(){
       return DataType.getAsString(this.get(S_ReportVerifydoctorid));
  
  }
  public String getReportVerifydoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifydoctorid));
      }

  public void initStudyAppdocname(String value){
     this.initProperty(S_StudyAppdocname,value);
  }
  public  void setStudyAppdocname(String value){
     this.set(S_StudyAppdocname,value);
  }
  public  void setStudyAppdocnameNull(){
     this.set(S_StudyAppdocname,null);
  }

  public String getStudyAppdocname(){
       return DataType.getAsString(this.get(S_StudyAppdocname));
  
  }
  public String getStudyAppdocnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAppdocname));
      }

  public void initPatientInpatientid(String value){
     this.initProperty(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientid(String value){
     this.set(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientidNull(){
     this.set(S_PatientInpatientid,null);
  }

  public String getPatientInpatientid(){
       return DataType.getAsString(this.get(S_PatientInpatientid));
  
  }
  public String getPatientInpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientInpatientid));
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

  public void initCStudyOperationtime(String value){
     this.initProperty(S_CStudyOperationtime,value);
  }
  public  void setCStudyOperationtime(String value){
     this.set(S_CStudyOperationtime,value);
  }
  public  void setCStudyOperationtimeNull(){
     this.set(S_CStudyOperationtime,null);
  }

  public String getCStudyOperationtime(){
       return DataType.getAsString(this.get(S_CStudyOperationtime));
  
  }
  public String getCStudyOperationtimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyOperationtime));
      }

  public void initStudyAppdoc(String value){
     this.initProperty(S_StudyAppdoc,value);
  }
  public  void setStudyAppdoc(String value){
     this.set(S_StudyAppdoc,value);
  }
  public  void setStudyAppdocNull(){
     this.set(S_StudyAppdoc,null);
  }

  public String getStudyAppdoc(){
       return DataType.getAsString(this.get(S_StudyAppdoc));
  
  }
  public String getStudyAppdocInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAppdoc));
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

  public void initStudyApplocid(long value){
     this.initProperty(S_StudyApplocid,new Long(value));
  }
  public  void setStudyApplocid(long value){
     this.set(S_StudyApplocid,new Long(value));
  }
  public  void setStudyApplocidNull(){
     this.set(S_StudyApplocid,null);
  }

  public long getStudyApplocid(){
        return DataType.getAsLong(this.get(S_StudyApplocid));
  
  }
  public long getStudyApplocidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyApplocid));
      }

  public void initReportIsprinted(long value){
     this.initProperty(S_ReportIsprinted,new Long(value));
  }
  public  void setReportIsprinted(long value){
     this.set(S_ReportIsprinted,new Long(value));
  }
  public  void setReportIsprintedNull(){
     this.set(S_ReportIsprinted,null);
  }

  public long getReportIsprinted(){
        return DataType.getAsLong(this.get(S_ReportIsprinted));
  
  }
  public long getReportIsprintedInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIsprinted));
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

  public void initStudyConsultstarttime(Timestamp value){
     this.initProperty(S_StudyConsultstarttime,value);
  }
  public  void setStudyConsultstarttime(Timestamp value){
     this.set(S_StudyConsultstarttime,value);
  }
  public  void setStudyConsultstarttimeNull(){
     this.set(S_StudyConsultstarttime,null);
  }

  public Timestamp getStudyConsultstarttime(){
        return DataType.getAsDateTime(this.get(S_StudyConsultstarttime));
  
  }
  public Timestamp getStudyConsultstarttimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyConsultstarttime));
      }

  public void initStudyDoctorname(String value){
     this.initProperty(S_StudyDoctorname,value);
  }
  public  void setStudyDoctorname(String value){
     this.set(S_StudyDoctorname,value);
  }
  public  void setStudyDoctornameNull(){
     this.set(S_StudyDoctorname,null);
  }

  public String getStudyDoctorname(){
       return DataType.getAsString(this.get(S_StudyDoctorname));
  
  }
  public String getStudyDoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDoctorname));
      }

  public void initCStudyEndtime(String value){
     this.initProperty(S_CStudyEndtime,value);
  }
  public  void setCStudyEndtime(String value){
     this.set(S_CStudyEndtime,value);
  }
  public  void setCStudyEndtimeNull(){
     this.set(S_CStudyEndtime,null);
  }

  public String getCStudyEndtime(){
       return DataType.getAsString(this.get(S_CStudyEndtime));
  
  }
  public String getCStudyEndtimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyEndtime));
      }

  public void initStudyConsultorgName(String value){
     this.initProperty(S_StudyConsultorgName,value);
  }
  public  void setStudyConsultorgName(String value){
     this.set(S_StudyConsultorgName,value);
  }
  public  void setStudyConsultorgNameNull(){
     this.set(S_StudyConsultorgName,null);
  }

  public String getStudyConsultorgName(){
       return DataType.getAsString(this.get(S_StudyConsultorgName));
  
  }
  public String getStudyConsultorgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultorgName));
      }

  public void initStudyHisappid(String value){
     this.initProperty(S_StudyHisappid,value);
  }
  public  void setStudyHisappid(String value){
     this.set(S_StudyHisappid,value);
  }
  public  void setStudyHisappidNull(){
     this.set(S_StudyHisappid,null);
  }

  public String getStudyHisappid(){
       return DataType.getAsString(this.get(S_StudyHisappid));
  
  }
  public String getStudyHisappidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyHisappid));
      }

  public void initReportFinaldoctorname(String value){
     this.initProperty(S_ReportFinaldoctorname,value);
  }
  public  void setReportFinaldoctorname(String value){
     this.set(S_ReportFinaldoctorname,value);
  }
  public  void setReportFinaldoctornameNull(){
     this.set(S_ReportFinaldoctorname,null);
  }

  public String getReportFinaldoctorname(){
       return DataType.getAsString(this.get(S_ReportFinaldoctorname));
  
  }
  public String getReportFinaldoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportFinaldoctorname));
      }

  public void initIsUrgent(long value){
     this.initProperty(S_IsUrgent,new Long(value));
  }
  public  void setIsUrgent(long value){
     this.set(S_IsUrgent,new Long(value));
  }
  public  void setIsUrgentNull(){
     this.set(S_IsUrgent,null);
  }

  public long getIsUrgent(){
        return DataType.getAsLong(this.get(S_IsUrgent));
  
  }
  public long getIsUrgentInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_IsUrgent));
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

  public void initReportVerifydoctorname(String value){
     this.initProperty(S_ReportVerifydoctorname,value);
  }
  public  void setReportVerifydoctorname(String value){
     this.set(S_ReportVerifydoctorname,value);
  }
  public  void setReportVerifydoctornameNull(){
     this.set(S_ReportVerifydoctorname,null);
  }

  public String getReportVerifydoctorname(){
       return DataType.getAsString(this.get(S_ReportVerifydoctorname));
  
  }
  public String getReportVerifydoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportVerifydoctorname));
      }

  public void initStudyConsultdoctorid(String value){
     this.initProperty(S_StudyConsultdoctorid,value);
  }
  public  void setStudyConsultdoctorid(String value){
     this.set(S_StudyConsultdoctorid,value);
  }
  public  void setStudyConsultdoctoridNull(){
     this.set(S_StudyConsultdoctorid,null);
  }

  public String getStudyConsultdoctorid(){
       return DataType.getAsString(this.get(S_StudyConsultdoctorid));
  
  }
  public String getStudyConsultdoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultdoctorid));
      }

  public void initStudyConsultendtime(Timestamp value){
     this.initProperty(S_StudyConsultendtime,value);
  }
  public  void setStudyConsultendtime(Timestamp value){
     this.set(S_StudyConsultendtime,value);
  }
  public  void setStudyConsultendtimeNull(){
     this.set(S_StudyConsultendtime,null);
  }

  public Timestamp getStudyConsultendtime(){
        return DataType.getAsDateTime(this.get(S_StudyConsultendtime));
  
  }
  public Timestamp getStudyConsultendtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyConsultendtime));
      }

  public void initStudystatusCode(String value){
     this.initProperty(S_StudystatusCode,value);
  }
  public  void setStudystatusCode(String value){
     this.set(S_StudystatusCode,value);
  }
  public  void setStudystatusCodeNull(){
     this.set(S_StudystatusCode,null);
  }

  public String getStudystatusCode(){
       return DataType.getAsString(this.get(S_StudystatusCode));
  
  }
  public String getStudystatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudystatusCode));
      }

  public void initStudyHaveimage(long value){
     this.initProperty(S_StudyHaveimage,new Long(value));
  }
  public  void setStudyHaveimage(long value){
     this.set(S_StudyHaveimage,new Long(value));
  }
  public  void setStudyHaveimageNull(){
     this.set(S_StudyHaveimage,null);
  }

  public long getStudyHaveimage(){
        return DataType.getAsLong(this.get(S_StudyHaveimage));
  
  }
  public long getStudyHaveimageInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyHaveimage));
      }

  public void initReportDoctorname(String value){
     this.initProperty(S_ReportDoctorname,value);
  }
  public  void setReportDoctorname(String value){
     this.set(S_ReportDoctorname,value);
  }
  public  void setReportDoctornameNull(){
     this.set(S_ReportDoctorname,null);
  }

  public String getReportDoctorname(){
       return DataType.getAsString(this.get(S_ReportDoctorname));
  
  }
  public String getReportDoctornameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDoctorname));
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

  public void initPatienttypeCode(String value){
     this.initProperty(S_PatienttypeCode,value);
  }
  public  void setPatienttypeCode(String value){
     this.set(S_PatienttypeCode,value);
  }
  public  void setPatienttypeCodeNull(){
     this.set(S_PatienttypeCode,null);
  }

  public String getPatienttypeCode(){
       return DataType.getAsString(this.get(S_PatienttypeCode));
  
  }
  public String getPatienttypeCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatienttypeCode));
      }

  public void initStudyHavereport(long value){
     this.initProperty(S_StudyHavereport,new Long(value));
  }
  public  void setStudyHavereport(long value){
     this.set(S_StudyHavereport,new Long(value));
  }
  public  void setStudyHavereportNull(){
     this.set(S_StudyHavereport,null);
  }

  public long getStudyHavereport(){
        return DataType.getAsLong(this.get(S_StudyHavereport));
  
  }
  public long getStudyHavereportInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyHavereport));
      }

  public void initStudyBedno(String value){
     this.initProperty(S_StudyBedno,value);
  }
  public  void setStudyBedno(String value){
     this.set(S_StudyBedno,value);
  }
  public  void setStudyBednoNull(){
     this.set(S_StudyBedno,null);
  }

  public String getStudyBedno(){
       return DataType.getAsString(this.get(S_StudyBedno));
  
  }
  public String getStudyBednoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyBedno));
      }

  public void initReportDatetime(String value){
     this.initProperty(S_ReportDatetime,value);
  }
  public  void setReportDatetime(String value){
     this.set(S_ReportDatetime,value);
  }
  public  void setReportDatetimeNull(){
     this.set(S_ReportDatetime,null);
  }

  public String getReportDatetime(){
       return DataType.getAsString(this.get(S_ReportDatetime));
  
  }
  public String getReportDatetimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDatetime));
      }


 
 }

