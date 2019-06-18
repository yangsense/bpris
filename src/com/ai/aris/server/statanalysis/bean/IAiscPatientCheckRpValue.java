package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscPatientCheckRpValue extends DataStructInterface{

  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_RegistNo = "REGIST_NO";
  public final static  String S_CheckItem = "CHECK_ITEM";
  public final static  String S_CheckDoc = "CHECK_DOC";
  public final static  String S_ImageNum = "IMAGE_NUM";
  public final static  String S_OrgDesc = "ORG_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ReportDoc = "REPORT_DOC";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_Id = "ID";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_PatientAge = "PATIENT_AGE";


public String getCityDesc();

public String getCityCode();

public String getLocDesc();

public String getCheckDate();

public String getRegistNo();

public String getCheckItem();

public String getCheckDoc();

public long getImageNum();

public String getOrgDesc();

public String getOrgId();

public String getReportDoc();

public String getPatientSex();

public String getCountyDesc();

public String getLocCode();

public String getPatientName();

public long getId();

public String getCountyCode();

public String getPatientAge();


public  void setCityDesc(String value);

public  void setCityCode(String value);

public  void setLocDesc(String value);

public  void setCheckDate(String value);

public  void setRegistNo(String value);

public  void setCheckItem(String value);

public  void setCheckDoc(String value);

public  void setImageNum(long value);

public  void setOrgDesc(String value);

public  void setOrgId(String value);

public  void setReportDoc(String value);

public  void setPatientSex(String value);

public  void setCountyDesc(String value);

public  void setLocCode(String value);

public  void setPatientName(String value);

public  void setId(long value);

public  void setCountyCode(String value);

public  void setPatientAge(String value);
}
