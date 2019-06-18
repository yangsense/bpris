package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryMedicalCountDetailValue extends DataStructInterface{

  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_Sdatetime = "SDATETIME";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_PatientAge = "PATIENT_AGE";


public Timestamp getStudyEndtime();

public String getPatientSex();

public String getCountyCode();

public Timestamp getSdatetime();

public String getOrgName();

public String getCountyDesc();

public String getReportExam();

public String getOrgId();

public String getStudyAccnumber();

public String getPatientName();

public long getStudyinfoId();

public String getReportResult();

public Timestamp getStudyStarttime();

public String getPatientAge();


public  void setStudyEndtime(Timestamp value);

public  void setPatientSex(String value);

public  void setCountyCode(String value);

public  void setSdatetime(Timestamp value);

public  void setOrgName(String value);

public  void setCountyDesc(String value);

public  void setReportExam(String value);

public  void setOrgId(String value);

public  void setStudyAccnumber(String value);

public  void setPatientName(String value);

public  void setStudyinfoId(long value);

public  void setReportResult(String value);

public  void setStudyStarttime(Timestamp value);

public  void setPatientAge(String value);
}
