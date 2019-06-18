package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryReportBrowseBean extends DataContainer implements DataContainerInterface,IQryReportBrowseValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryReportBrowse";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyRemark = "STUDY_REMARK";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_StudyWard = "STUDY_WARD";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_Studydesc = "STUDYDESC";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_StudyExposurecount = "STUDY_EXPOSURECOUNT";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_StudyFilmcount = "STUDY_FILMCOUNT";
  public final static  String S_PatientDob = "PATIENT_DOB";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_PatientIdnumber = "PATIENT_IDNUMBER";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_ReportVerifydatetime = "REPORT_VERIFYDATETIME";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_StudyBedno = "STUDY_BEDNO";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_HisZydjxh = "HIS_ZYDJXH";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryReportBrowseBean() throws AIException{
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

  public void initStudyRemark(String value){
     this.initProperty(S_StudyRemark,value);
  }
  public  void setStudyRemark(String value){
     this.set(S_StudyRemark,value);
  }
  public  void setStudyRemarkNull(){
     this.set(S_StudyRemark,null);
  }

  public String getStudyRemark(){
       return DataType.getAsString(this.get(S_StudyRemark));
  
  }
  public String getStudyRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyRemark));
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

  public void initStudyWard(String value){
     this.initProperty(S_StudyWard,value);
  }
  public  void setStudyWard(String value){
     this.set(S_StudyWard,value);
  }
  public  void setStudyWardNull(){
     this.set(S_StudyWard,null);
  }

  public String getStudyWard(){
       return DataType.getAsString(this.get(S_StudyWard));
  
  }
  public String getStudyWardInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyWard));
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

  public void initAidDoctorid(String value){
     this.initProperty(S_AidDoctorid,value);
  }
  public  void setAidDoctorid(String value){
     this.set(S_AidDoctorid,value);
  }
  public  void setAidDoctoridNull(){
     this.set(S_AidDoctorid,null);
  }

  public String getAidDoctorid(){
       return DataType.getAsString(this.get(S_AidDoctorid));
  
  }
  public String getAidDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AidDoctorid));
      }

  public void initStudydesc(String value){
     this.initProperty(S_Studydesc,value);
  }
  public  void setStudydesc(String value){
     this.set(S_Studydesc,value);
  }
  public  void setStudydescNull(){
     this.set(S_Studydesc,null);
  }

  public String getStudydesc(){
       return DataType.getAsString(this.get(S_Studydesc));
  
  }
  public String getStudydescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studydesc));
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

  public void initStudyExposurecount(long value){
     this.initProperty(S_StudyExposurecount,new Long(value));
  }
  public  void setStudyExposurecount(long value){
     this.set(S_StudyExposurecount,new Long(value));
  }
  public  void setStudyExposurecountNull(){
     this.set(S_StudyExposurecount,null);
  }

  public long getStudyExposurecount(){
        return DataType.getAsLong(this.get(S_StudyExposurecount));
  
  }
  public long getStudyExposurecountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyExposurecount));
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

  public void initStudyFilmcount(long value){
     this.initProperty(S_StudyFilmcount,new Long(value));
  }
  public  void setStudyFilmcount(long value){
     this.set(S_StudyFilmcount,new Long(value));
  }
  public  void setStudyFilmcountNull(){
     this.set(S_StudyFilmcount,null);
  }

  public long getStudyFilmcount(){
        return DataType.getAsLong(this.get(S_StudyFilmcount));
  
  }
  public long getStudyFilmcountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyFilmcount));
      }

  public void initPatientDob(Timestamp value){
     this.initProperty(S_PatientDob,value);
  }
  public  void setPatientDob(Timestamp value){
     this.set(S_PatientDob,value);
  }
  public  void setPatientDobNull(){
     this.set(S_PatientDob,null);
  }

  public Timestamp getPatientDob(){
        return DataType.getAsDateTime(this.get(S_PatientDob));
  
  }
  public Timestamp getPatientDobInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_PatientDob));
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

  public void initPatientIdnumber(String value){
     this.initProperty(S_PatientIdnumber,value);
  }
  public  void setPatientIdnumber(String value){
     this.set(S_PatientIdnumber,value);
  }
  public  void setPatientIdnumberNull(){
     this.set(S_PatientIdnumber,null);
  }

  public String getPatientIdnumber(){
       return DataType.getAsString(this.get(S_PatientIdnumber));
  
  }
  public String getPatientIdnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientIdnumber));
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

  public void initStudyClinic(String value){
     this.initProperty(S_StudyClinic,value);
  }
  public  void setStudyClinic(String value){
     this.set(S_StudyClinic,value);
  }
  public  void setStudyClinicNull(){
     this.set(S_StudyClinic,null);
  }

  public String getStudyClinic(){
       return DataType.getAsString(this.get(S_StudyClinic));
  
  }
  public String getStudyClinicInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyClinic));
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

  public void initPatientOutpatientid(String value){
     this.initProperty(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientid(String value){
     this.set(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientidNull(){
     this.set(S_PatientOutpatientid,null);
  }

  public String getPatientOutpatientid(){
       return DataType.getAsString(this.get(S_PatientOutpatientid));
  
  }
  public String getPatientOutpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientOutpatientid));
      }

  public void initReportVerifydatetime(Timestamp value){
     this.initProperty(S_ReportVerifydatetime,value);
  }
  public  void setReportVerifydatetime(Timestamp value){
     this.set(S_ReportVerifydatetime,value);
  }
  public  void setReportVerifydatetimeNull(){
     this.set(S_ReportVerifydatetime,null);
  }

  public Timestamp getReportVerifydatetime(){
        return DataType.getAsDateTime(this.get(S_ReportVerifydatetime));
  
  }
  public Timestamp getReportVerifydatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportVerifydatetime));
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

  public void initReportRemark(String value){
     this.initProperty(S_ReportRemark,value);
  }
  public  void setReportRemark(String value){
     this.set(S_ReportRemark,value);
  }
  public  void setReportRemarkNull(){
     this.set(S_ReportRemark,null);
  }

  public String getReportRemark(){
       return DataType.getAsString(this.get(S_ReportRemark));
  
  }
  public String getReportRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportRemark));
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

  public void initHisZydjxh(String value){
     this.initProperty(S_HisZydjxh,value);
  }
  public  void setHisZydjxh(String value){
     this.set(S_HisZydjxh,value);
  }
  public  void setHisZydjxhNull(){
     this.set(S_HisZydjxh,null);
  }

  public String getHisZydjxh(){
       return DataType.getAsString(this.get(S_HisZydjxh));
  
  }
  public String getHisZydjxhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_HisZydjxh));
      }


 
 }

