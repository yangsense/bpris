package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscServerInfoValue extends DataStructInterface{

  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_ServerName = "SERVER_NAME";
  public final static  String S_ServerIp = "SERVER_IP";


public String getServerPassword();

public long getServerPort();

public String getServerUser();

public long getServerId();

public int getServerEnabled();

public int getServerType();

public String getServerName();

public String getServerIp();


public  void setServerPassword(String value);

public  void setServerPort(long value);

public  void setServerUser(String value);

public  void setServerId(long value);

public  void setServerEnabled(int value);

public  void setServerType(int value);

public  void setServerName(String value);

public  void setServerIp(String value);
}
