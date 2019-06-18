package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryBigSortValue extends DataStructInterface{

  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrdcategoryDesc = "ORDCATEGORY_DESC";


public String getCountyCode();

public String getCityCode();

public long getOrdcategoryId();

public String getOrgId();

public String getOrdcategoryDesc();


public  void setCountyCode(String value);

public  void setCityCode(String value);

public  void setOrdcategoryId(long value);

public  void setOrgId(String value);

public  void setOrdcategoryDesc(String value);
}
