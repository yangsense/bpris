package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryOrgSelectValue extends DataStructInterface{

  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrgId = "ORG_ID";


public String getCountyCode();

public String getCityCode();

public String getOrgName();

public String getOrgId();


public  void setCountyCode(String value);

public  void setCityCode(String value);

public  void setOrgName(String value);

public  void setOrgId(String value);
}
