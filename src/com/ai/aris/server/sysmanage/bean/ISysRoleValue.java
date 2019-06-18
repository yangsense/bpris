package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ISysRoleValue extends DataStructInterface{

  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CreateUser = "CREATE_USER";
  public final static  String S_UpdateDate = "UPDATE_DATE";
  public final static  String S_RoleState = "ROLE_STATE";
  public final static  String S_UpdateUser = "UPDATE_USER";
  public final static  String S_RoleId = "ROLE_ID";
  public final static  String S_RoleName = "ROLE_NAME";
  public final static  String S_RoleDes = "ROLE_DES";
  public final static  String S_SysType = "SYS_TYPE";


public Timestamp getCreateDate();

public String getOrgId();

public String getCreateUser();

public Timestamp getUpdateDate();

public long getRoleState();

public String getUpdateUser();

public long getRoleId();

public String getRoleName();

public String getRoleDes();

public long getSysType();


public  void setCreateDate(Timestamp value);

public  void setOrgId(String value);

public  void setCreateUser(String value);

public  void setUpdateDate(Timestamp value);

public  void setRoleState(long value);

public  void setUpdateUser(String value);

public  void setRoleId(long value);

public  void setRoleName(String value);

public  void setRoleDes(String value);

public  void setSysType(long value);
}
