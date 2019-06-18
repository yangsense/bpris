package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryHzRecordListValue extends DataStructInterface{

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


public String getPatientSex();

public long getStudyConsultloc();

public String getOrgName();

public String getLocDesc();

public String getOperatorName();

public String getStudyConsultlocname();

public String getStudyConsultorg();

public String getOrgId();

public Timestamp getStudyDatetime();

public String getPatientName();

public long getLocId();

public String getCareprovName();

public String getStudyConsultorgname();

public String getPatientAge();

public Timestamp getReportDatetime();


public  void setPatientSex(String value);

public  void setStudyConsultloc(long value);

public  void setOrgName(String value);

public  void setLocDesc(String value);

public  void setOperatorName(String value);

public  void setStudyConsultlocname(String value);

public  void setStudyConsultorg(String value);

public  void setOrgId(String value);

public  void setStudyDatetime(Timestamp value);

public  void setPatientName(String value);

public  void setLocId(long value);

public  void setCareprovName(String value);

public  void setStudyConsultorgname(String value);

public  void setPatientAge(String value);

public  void setReportDatetime(Timestamp value);
}
