package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryMedicalCaseWorkloadValue extends DataStructInterface{

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


public String getStudyItemdesc();

public long getReportIspositive();

public String getPatientSex();

public String getPatientId();

public String getReportFinaldoctorid();

public String getReportDoctorid();

public String getReportExam();

public String getOrgId();

public long getStudyHaveimage();

public String getStudyAccnumber();

public String getStudystatusCode();

public long getStudyinfoId();

public String getPatientName();

public String getReportDoctorname();

public String getReportResult();

public long getStudyHavereport();

public long getLocId();

public long getPatientGlobalid();

public String getReportVerifydoctorid();

public String getPatientAge();

public Timestamp getReportDatetime();

public String getVerifydoctorName();

public long getReportId();


public  void setStudyItemdesc(String value);

public  void setReportIspositive(long value);

public  void setPatientSex(String value);

public  void setPatientId(String value);

public  void setReportFinaldoctorid(String value);

public  void setReportDoctorid(String value);

public  void setReportExam(String value);

public  void setOrgId(String value);

public  void setStudyHaveimage(long value);

public  void setStudyAccnumber(String value);

public  void setStudystatusCode(String value);

public  void setStudyinfoId(long value);

public  void setPatientName(String value);

public  void setReportDoctorname(String value);

public  void setReportResult(String value);

public  void setStudyHavereport(long value);

public  void setLocId(long value);

public  void setPatientGlobalid(long value);

public  void setReportVerifydoctorid(String value);

public  void setPatientAge(String value);

public  void setReportDatetime(Timestamp value);

public  void setVerifydoctorName(String value);

public  void setReportId(long value);
}
