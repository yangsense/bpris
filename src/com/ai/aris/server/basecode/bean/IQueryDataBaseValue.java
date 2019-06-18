package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryDataBaseValue extends DataStructInterface{

  public final static  String S_SourceUrl = "SOURCE_URL";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_SourceId = "SOURCE_ID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_Status = "STATUS";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_PatientTypecode = "PATIENT_TYPECODE";
  public final static  String S_Haspacs = "HASPACS";
  public final static  String S_SourceUser = "SOURCE_USER";
  public final static  String S_SourcePassword = "SOURCE_PASSWORD";


public String getSourceUrl();

public String getCityCode();

public String getCountyCode();

public long getSourceId();

public String getOrgName();

public String getStatus();

public String getCountyDesc();

public String getOrgId();

public String getCityDesc();

public String getPatientTypecode();

public long getHaspacs();

public String getSourceUser();

public String getSourcePassword();


public  void setSourceUrl(String value);

public  void setCityCode(String value);

public  void setCountyCode(String value);

public  void setSourceId(long value);

public  void setOrgName(String value);

public  void setStatus(String value);

public  void setCountyDesc(String value);

public  void setOrgId(String value);

public  void setCityDesc(String value);

public  void setPatientTypecode(String value);

public  void setHaspacs(long value);

public  void setSourceUser(String value);

public  void setSourcePassword(String value);
}
