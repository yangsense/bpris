package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryHzMultipleListValue extends DataStructInterface{

  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_Hzsqsl = "HZSQSL";
  public final static  String S_Hzsl = "HZSL";
  public final static  String S_OrgId = "ORG_ID";


public String getCountyCode();

public String getCityCode();

public String getStudyDatetime();

public String getOrgName();

public String getCountyDesc();

public long getHzsqsl();

public long getHzsl();

public String getOrgId();


public  void setCountyCode(String value);

public  void setCityCode(String value);

public  void setStudyDatetime(String value);

public  void setOrgName(String value);

public  void setCountyDesc(String value);

public  void setHzsqsl(long value);

public  void setHzsl(long value);

public  void setOrgId(String value);
}
