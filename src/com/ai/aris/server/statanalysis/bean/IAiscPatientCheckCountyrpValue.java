package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscPatientCheckCountyrpValue extends DataStructInterface{

  public final static  String S_ReportNum = "REPORT_NUM";
  public final static  String S_CheckedNum = "CHECKED_NUM";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_ImageNum = "IMAGE_NUM";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_Id = "ID";
  public final static  String S_CollectNum = "COLLECT_NUM";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_RegistNum = "REGIST_NUM";


public long getReportNum();

public long getCheckedNum();

public String getCityDesc();

public long getImageNum();

public String getCityCode();

public String getCheckDate();

public String getCountyDesc();

public long getId();

public long getCollectNum();

public String getCountyCode();

public long getRegistNum();


public  void setReportNum(long value);

public  void setCheckedNum(long value);

public  void setCityDesc(String value);

public  void setImageNum(long value);

public  void setCityCode(String value);

public  void setCheckDate(String value);

public  void setCountyDesc(String value);

public  void setId(long value);

public  void setCollectNum(long value);

public  void setCountyCode(String value);

public  void setRegistNum(long value);
}
