package com.ai.aris.server.common.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisRightControlValue extends DataStructInterface{

  public final static  String S_InstallTime = "INSTALL_TIME";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_ExpirationTime = "EXPIRATION_TIME";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_Id = "ID";
  public final static  String S_ServerMac = "SERVER_MAC";
  public final static  String S_UpdateTime = "UPDATE_TIME";
  public final static  String S_CurrentTime = "CURRENT_TIME";


public String getInstallTime();

public String getOrgName();

public String getOrgCode();

public String getExpirationTime();

public String getCreateTime();

public String getId();

public String getServerMac();

public String getUpdateTime();

public String getCurrentTime();


public  void setInstallTime(String value);

public  void setOrgName(String value);

public  void setOrgCode(String value);

public  void setExpirationTime(String value);

public  void setCreateTime(String value);

public  void setId(String value);

public  void setServerMac(String value);

public  void setUpdateTime(String value);

public  void setCurrentTime(String value);
}
