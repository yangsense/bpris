package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyInfoListPrintValue extends DataStructInterface{

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


public String getPatientSex();

public String getStudyDoctorid();

public String getOrgName();

public String getStudyConsultlocName();

public String getStudyConsultorg();

public Timestamp getStudyDatetime();

public long getPatientGlobalid();

public Timestamp getStudyStarttime();

public String getReportVerifytime();

public String getPatientDob();

public String getPatientId();

public String getBodyitem();

public String getLocDesc();

public String getStudyConsultation();

public String getCStudyDatetime();

public String getStudyAccnumber();

public Timestamp getStudyOperationtime();

public String getCStudyAppdate();

public String getCStudyStarttime();

public Timestamp getStudyAppdate();

public String getReportVerifydoctorid();

public String getStudyAppdocname();

public String getPatientInpatientid();

public long getStudyType();

public String getCStudyOperationtime();

public String getStudyAppdoc();

public long getStudyinfoId();

public long getLocId();

public String getPatientAge();

public String getStudyItemdesc();

public Timestamp getStudyEndtime();

public long getStudyApplocid();

public long getReportIsprinted();

public long getStudyConsultloc();

public Timestamp getStudyConsultstarttime();

public String getStudyDoctorname();

public String getCStudyEndtime();

public String getStudyConsultorgName();

public String getStudyHisappid();

public String getReportFinaldoctorname();

public long getIsUrgent();

public String getOrgId();

public String getReportVerifydoctorname();

public String getStudyConsultdoctorid();

public Timestamp getStudyConsultendtime();

public String getStudystatusCode();

public long getStudyHaveimage();

public String getReportDoctorname();

public String getPatientName();

public String getPatienttypeCode();

public long getStudyHavereport();

public String getStudyBedno();

public String getReportDatetime();


public  void setPatientSex(String value);

public  void setStudyDoctorid(String value);

public  void setOrgName(String value);

public  void setStudyConsultlocName(String value);

public  void setStudyConsultorg(String value);

public  void setStudyDatetime(Timestamp value);

public  void setPatientGlobalid(long value);

public  void setStudyStarttime(Timestamp value);

public  void setReportVerifytime(String value);

public  void setPatientDob(String value);

public  void setPatientId(String value);

public  void setBodyitem(String value);

public  void setLocDesc(String value);

public  void setStudyConsultation(String value);

public  void setCStudyDatetime(String value);

public  void setStudyAccnumber(String value);

public  void setStudyOperationtime(Timestamp value);

public  void setCStudyAppdate(String value);

public  void setCStudyStarttime(String value);

public  void setStudyAppdate(Timestamp value);

public  void setReportVerifydoctorid(String value);

public  void setStudyAppdocname(String value);

public  void setPatientInpatientid(String value);

public  void setStudyType(long value);

public  void setCStudyOperationtime(String value);

public  void setStudyAppdoc(String value);

public  void setStudyinfoId(long value);

public  void setLocId(long value);

public  void setPatientAge(String value);

public  void setStudyItemdesc(String value);

public  void setStudyEndtime(Timestamp value);

public  void setStudyApplocid(long value);

public  void setReportIsprinted(long value);

public  void setStudyConsultloc(long value);

public  void setStudyConsultstarttime(Timestamp value);

public  void setStudyDoctorname(String value);

public  void setCStudyEndtime(String value);

public  void setStudyConsultorgName(String value);

public  void setStudyHisappid(String value);

public  void setReportFinaldoctorname(String value);

public  void setIsUrgent(long value);

public  void setOrgId(String value);

public  void setReportVerifydoctorname(String value);

public  void setStudyConsultdoctorid(String value);

public  void setStudyConsultendtime(Timestamp value);

public  void setStudystatusCode(String value);

public  void setStudyHaveimage(long value);

public  void setReportDoctorname(String value);

public  void setPatientName(String value);

public  void setPatienttypeCode(String value);

public  void setStudyHavereport(long value);

public  void setStudyBedno(String value);

public  void setReportDatetime(String value);
}
