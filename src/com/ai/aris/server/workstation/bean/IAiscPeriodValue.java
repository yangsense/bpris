package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscPeriodValue extends DataStructInterface{

  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_PeriodStarttime = "PERIOD_STARTTIME";
  public final static  String S_PeriodDesc = "PERIOD_DESC";
  public final static  String S_Id = "ID";
  public final static  String S_PeriodId = "PERIOD_ID";
  public final static  String S_PeriodEndtime = "PERIOD_ENDTIME";


public Timestamp getCreateTime();

public String getPeriodStarttime();

public String getPeriodDesc();

public long getId();

public String getPeriodId();

public String getPeriodEndtime();


public  void setCreateTime(Timestamp value);

public  void setPeriodStarttime(String value);

public  void setPeriodDesc(String value);

public  void setId(long value);

public  void setPeriodId(String value);

public  void setPeriodEndtime(String value);
}
