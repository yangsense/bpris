package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscSeverinfoRealValue extends DataStructInterface{

  public final static  String S_ServerRealId = "SERVER_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_OldServerId = "OLD_SERVER_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getServerRealId();

public Timestamp getCreateTime();

public long getServerId();

public long getOldServerId();

public String getOrgId();


public  void setServerRealId(long value);

public  void setCreateTime(Timestamp value);

public  void setServerId(long value);

public  void setOldServerId(long value);

public  void setOrgId(String value);
}
