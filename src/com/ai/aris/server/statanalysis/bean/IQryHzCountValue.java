package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryHzCountValue extends DataStructInterface{

  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_CheckDoc = "CHECK_DOC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_StudyConsultlocname = "STUDY_CONSULTLOCNAME";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_Id = "ID";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_HzResultcount = "HZ_RESULTCOUNT";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_StudyConsultorgname = "STUDY_CONSULTORGNAME";
  public final static  String S_CheckItem = "CHECK_ITEM";
  public final static  String S_ReportDoc = "REPORT_DOC";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_HzCount = "HZ_COUNT";


public String getPatientSex();

public String getLocCode();

public String getCityCode();

public String getCountyCode();

public String getPatientId();

public String getCheckDoc();

public String getOrgName();

public String getLocDesc();

public Timestamp getCheckDate();

public String getStudyConsultlocname();

public String getCountyDesc();

public String getOrgId();

public String getCityDesc();

public String getPatientName();

public long getId();

public Timestamp getStudyAppdate();

public long getHzResultcount();

public String getCareprovName();

public String getStudyConsultorgname();

public String getCheckItem();

public String getReportDoc();

public Timestamp getReportDatetime();

public String getPatientAge();

public long getHzCount();


public  void setPatientSex(String value);

public  void setLocCode(String value);

public  void setCityCode(String value);

public  void setCountyCode(String value);

public  void setPatientId(String value);

public  void setCheckDoc(String value);

public  void setOrgName(String value);

public  void setLocDesc(String value);

public  void setCheckDate(Timestamp value);

public  void setStudyConsultlocname(String value);

public  void setCountyDesc(String value);

public  void setOrgId(String value);

public  void setCityDesc(String value);

public  void setPatientName(String value);

public  void setId(long value);

public  void setStudyAppdate(Timestamp value);

public  void setHzResultcount(long value);

public  void setCareprovName(String value);

public  void setStudyConsultorgname(String value);

public  void setCheckItem(String value);

public  void setReportDoc(String value);

public  void setReportDatetime(Timestamp value);

public  void setPatientAge(String value);

public  void setHzCount(long value);
}
