package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryCountysValue extends DataStructInterface{

  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CountyDesc = "COUNTY_DESC";


public String getCountyCode();

public String getCityCode();

public String getCountyDesc();


public  void setCountyCode(String value);

public  void setCityCode(String value);

public  void setCountyDesc(String value);
}
