package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
public interface ISysRole2menuValue extends DataStructInterface{

  public final static  String S_MenuId = "MENU_ID";
  public final static  String S_RoleId = "ROLE_ID";
  public final static  String S_SysType = "SYS_TYPE";


public long getMenuId();

public long getRoleId();

public long getSysType();


public  void setMenuId(long value);

public  void setRoleId(long value);

public  void setSysType(long value);
}
