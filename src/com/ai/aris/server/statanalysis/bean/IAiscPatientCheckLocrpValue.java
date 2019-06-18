package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscPatientCheckLocrpValue extends DataStructInterface{

  public final static  String S_ReportNum = "REPORT_NUM";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_CollectNum = "COLLECT_NUM";
  public final static  String S_CheckedNum = "CHECKED_NUM";
  public final static  String S_ImageNum = "IMAGE_NUM";
  public final static  String S_OrgDesc = "ORG_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_Id = "ID";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_RegistNum = "REGIST_NUM";


public long getReportNum();

public String getCityDesc();

public String getCityCode();

public String getLocDesc();

public String getCheckDate();

public long getCollectNum();

public long getCheckedNum();

public long getImageNum();

public String getOrgDesc();

public String getOrgId();

public String getCountyDesc();

public String getLocCode();

public long getId();

public String getCountyCode();

public long getRegistNum();


public  void setReportNum(long value);

public  void setCityDesc(String value);

public  void setCityCode(String value);

public  void setLocDesc(String value);

public  void setCheckDate(String value);

public  void setCollectNum(long value);

public  void setCheckedNum(long value);

public  void setImageNum(long value);

public  void setOrgDesc(String value);

public  void setOrgId(String value);

public  void setCountyDesc(String value);

public  void setLocCode(String value);

public  void setId(long value);

public  void setCountyCode(String value);

public  void setRegistNum(long value);
}
