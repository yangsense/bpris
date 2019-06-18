package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryMedicalCaseWorkloadBean extends DataContainer implements DataContainerInterface,IQryMedicalCaseWorkloadValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryMedicalCaseWorkload";



  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_ReportFinaldoctorid = "REPORT_FINALDOCTORID";
  public final static  String S_ReportDoctorid = "REPORT_DOCTORID";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_VerifydoctorName = "VERIFYDOCTOR_NAME";
  public final static  String S_ReportId = "REPORT_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryMedicalCaseWorkloadBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initReportIspositive(long value){
     this.initProperty(S_ReportIspositive,new Long(value));
  }
  public  void setReportIspositive(long value){
     this.set(S_ReportIspositive,new Long(value));
  }
  public  void setReportIspositiveNull(){
     this.set(S_ReportIspositive,null);
  }

  public long getReportIspositive(){
        return DataType.getAsLong(this.get(S_ReportIspositive));
  
  }
  public long getReportIspositiveInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportIspositive));
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

  public void initReportFinaldoctorid(String value){
     this.initProperty(S_ReportFinaldoctorid,value);
  }
  public  void setReportFinaldoctorid(String value){
     this.set(S_ReportFinaldoctorid,value);
  }
  public  void setReportFinaldoctoridNull(){
     this.set(S_ReportFinaldoctorid,null);
  }

  public String getReportFinaldoctorid(){
       return DataType.getAsString(this.get(S_ReportFinaldoctorid));
  
  }
  public String getReportFinaldoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportFinaldoctorid));
      }

  public void initReportDoctorid(String value){
     this.initProperty(S_ReportDoctorid,value);
  }
  public  void setReportDoctorid(String value){
     this.set(S_ReportDoctorid,value);
  }
  public  void setReportDoctoridNull(){
     this.set(S_ReportDoctorid,null);
  }

  public String getReportDoctorid(){
       return DataType.getAsString(this.get(S_ReportDoctorid));
  
  }
  public String getReportDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDoctorid));
      }

  public void initReportExam(String value){
     this.initProperty(S_ReportExam,value);
  }
  public  void setReportExam(String value){
     this.set(S_ReportExam,value);
  }
  public  void setReportExamNull(){
     this.set(S_ReportExam,null);
  }

  public String getReportExam(){
       return DataType.getAsString(this.get(S_ReportExam));
  
  }
  public String getReportExamInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportExam));
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

  public void initReportResult(String value){
     this.initProperty(S_ReportResult,value);
  }
  public  void setReportResult(String value){
     this.set(S_ReportResult,value);
  }
  public  void setReportResultNull(){
     this.set(S_ReportResult,null);
  }

  public String getReportResult(){
       return DataType.getAsString(this.get(S_ReportResult));
  
  }
  public String getReportResultInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportResult));
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

  public void initVerifydoctorName(String value){
     this.initProperty(S_VerifydoctorName,value);
  }
  public  void setVerifydoctorName(String value){
     this.set(S_VerifydoctorName,value);
  }
  public  void setVerifydoctorNameNull(){
     this.set(S_VerifydoctorName,null);
  }

  public String getVerifydoctorName(){
       return DataType.getAsString(this.get(S_VerifydoctorName));
  
  }
  public String getVerifydoctorNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_VerifydoctorName));
      }

  public void initReportId(long value){
     this.initProperty(S_ReportId,new Long(value));
  }
  public  void setReportId(long value){
     this.set(S_ReportId,new Long(value));
  }
  public  void setReportIdNull(){
     this.set(S_ReportId,null);
  }

  public long getReportId(){
        return DataType.getAsLong(this.get(S_ReportId));
  
  }
  public long getReportIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportId));
      }


 
 }

