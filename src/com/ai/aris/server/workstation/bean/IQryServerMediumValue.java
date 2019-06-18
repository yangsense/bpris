package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryServerMediumValue extends DataStructInterface{

  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_ServerIp = "SERVER_IP";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";
  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_ServerName = "SERVER_NAME";


public long getMediumIsfull();

public String getMediumPath();

public long getServerPort();

public String getLocDesc();

public long getServerId();

public long getMediumIsonline();

public String getServerUser();

public long getServerType();

public long getServerEnabled();

public String getServerIp();

public long getLocId();

public long getMediumReminesize();

public long getMediumType();

public long getMediumId();

public String getLocCode();

public long getMediumEnabled();

public String getServerPassword();

public long getMediumSize();

public String getServerName();


public  void setMediumIsfull(long value);

public  void setMediumPath(String value);

public  void setServerPort(long value);

public  void setLocDesc(String value);

public  void setServerId(long value);

public  void setMediumIsonline(long value);

public  void setServerUser(String value);

public  void setServerType(long value);

public  void setServerEnabled(long value);

public  void setServerIp(String value);

public  void setLocId(long value);

public  void setMediumReminesize(long value);

public  void setMediumType(long value);

public  void setMediumId(long value);

public  void setLocCode(String value);

public  void setMediumEnabled(long value);

public  void setServerPassword(String value);

public  void setMediumSize(long value);

public  void setServerName(String value);
}
