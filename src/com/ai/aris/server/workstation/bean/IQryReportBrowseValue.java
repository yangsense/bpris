package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryReportBrowseValue extends DataStructInterface{

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


public String getPatientSex();

public String getStudyRemark();

public String getStudyDoctorid();

public String getPatientInpatientid();

public String getStudyWard();

public String getOrgName();

public long getStudyType();

public String getStudyConsultorg();

public long getStudyinfoId();

public String getAidDoctorid();

public String getStudydesc();

public String getReportResult();

public long getStudyExposurecount();

public long getLocId();

public long getPatientGlobalid();

public String getPatientAge();

public long getStudyFilmcount();

public Timestamp getPatientDob();

public long getStudyConsultloc();

public String getPatientId();

public String getReportExam();

public String getPatientIdnumber();

public String getOrgId();

public String getReportVerifydoctorname();

public long getStudyHaveimage();

public String getStudyClinic();

public String getStudyAccnumber();

public String getPatientOutpatientid();

public Timestamp getReportVerifydatetime();

public String getPatientName();

public String getReportDoctorname();

public long getStudyHavereport();

public String getPatienttypeCode();

public String getReportRemark();

public String getStudyBedno();

public Timestamp getReportDatetime();

public String getHisZydjxh();


public  void setPatientSex(String value);

public  void setStudyRemark(String value);

public  void setStudyDoctorid(String value);

public  void setPatientInpatientid(String value);

public  void setStudyWard(String value);

public  void setOrgName(String value);

public  void setStudyType(long value);

public  void setStudyConsultorg(String value);

public  void setStudyinfoId(long value);

public  void setAidDoctorid(String value);

public  void setStudydesc(String value);

public  void setReportResult(String value);

public  void setStudyExposurecount(long value);

public  void setLocId(long value);

public  void setPatientGlobalid(long value);

public  void setPatientAge(String value);

public  void setStudyFilmcount(long value);

public  void setPatientDob(Timestamp value);

public  void setStudyConsultloc(long value);

public  void setPatientId(String value);

public  void setReportExam(String value);

public  void setPatientIdnumber(String value);

public  void setOrgId(String value);

public  void setReportVerifydoctorname(String value);

public  void setStudyHaveimage(long value);

public  void setStudyClinic(String value);

public  void setStudyAccnumber(String value);

public  void setPatientOutpatientid(String value);

public  void setReportVerifydatetime(Timestamp value);

public  void setPatientName(String value);

public  void setReportDoctorname(String value);

public  void setStudyHavereport(long value);

public  void setPatienttypeCode(String value);

public  void setReportRemark(String value);

public  void setStudyBedno(String value);

public  void setReportDatetime(Timestamp value);

public  void setHisZydjxh(String value);
}
