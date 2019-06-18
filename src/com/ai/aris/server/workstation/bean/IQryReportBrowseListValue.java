package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryReportBrowseListValue extends DataStructInterface{

  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_ReportFinaldoctorname = "REPORT_FINALDOCTORNAME";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_CStudyStarttime = "C_STUDY_STARTTIME";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportVerifytime = "REPORT_VERIFYTIME";


public String getStudyItemdesc();

public String getPatientId();

public String getPatientInpatientid();

public String getOrgName();

public String getReportFinaldoctorname();

public String getReportExam();

public String getReportVerifydoctorname();

public String getOrgId();

public long getStudyHaveimage();

public String getStudyAccnumber();

public String getPatientOutpatientid();

public long getStudyinfoId();

public String getReportDoctorname();

public String getCStudyStarttime();

public String getReportResult();

public long getStudyHavereport();

public String getReportRemark();

public String getReportDatetime();

public String getReportVerifytime();


public  void setStudyItemdesc(String value);

public  void setPatientId(String value);

public  void setPatientInpatientid(String value);

public  void setOrgName(String value);

public  void setReportFinaldoctorname(String value);

public  void setReportExam(String value);

public  void setReportVerifydoctorname(String value);

public  void setOrgId(String value);

public  void setStudyHaveimage(long value);

public  void setStudyAccnumber(String value);

public  void setPatientOutpatientid(String value);

public  void setStudyinfoId(long value);

public  void setReportDoctorname(String value);

public  void setCStudyStarttime(String value);

public  void setReportResult(String value);

public  void setStudyHavereport(long value);

public  void setReportRemark(String value);

public  void setReportDatetime(String value);

public  void setReportVerifytime(String value);
}
