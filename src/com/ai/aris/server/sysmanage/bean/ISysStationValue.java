package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ISysStationValue extends DataStructInterface{

  public final static  String S_StationCode = "STATION_CODE";
  public final static  String S_StationName = "STATION_NAME";
  public final static  String S_Remark = "REMARK";
  public final static  String S_SysType = "SYS_TYPE";


public String getStationCode();

public String getStationName();

public String getRemark();

public long getSysType();


public  void setStationCode(String value);

public  void setStationName(String value);

public  void setRemark(String value);

public  void setSysType(long value);
}
