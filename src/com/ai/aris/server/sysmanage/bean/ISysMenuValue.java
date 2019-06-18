package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.common.DataStructInterface;

import java.sql.Timestamp;

public interface ISysMenuValue extends DataStructInterface {

  public final static  String S_MenuName = "MENU_NAME";
  public final static  String S_MenuOrder = "MENU_ORDER";
  public final static  String S_State = "STATE";
  public final static  String S_ParentMenuId = "PARENT_MENU_ID";
  public final static  String S_GroupId = "GROUP_ID";
  public final static  String S_MenuUrl = "MENU_URL";
  public final static  String S_MenuType = "MENU_TYPE";
  public final static  String S_MenuId = "MENU_ID";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_MenuImage = "MENU_IMAGE";
  public final static  String S_SysType = "SYS_TYPE";
  public final static  String S_MenuDesc = "MENU_DESC";


public String getMenuName();

public int getMenuOrder();

public String getState();

public long getParentMenuId();

public String getGroupId();

public String getMenuUrl();

public int getMenuType();

public long getMenuId();

public Timestamp getCreateDate();

public String getMenuImage();

public int getSysType();

public String getMenuDesc();


public  void setMenuName(String value);

public  void setMenuOrder(int value);

public  void setState(String value);

public  void setParentMenuId(long value);

public  void setGroupId(String value);

public  void setMenuUrl(String value);

public  void setMenuType(int value);

public  void setMenuId(long value);

public  void setCreateDate(Timestamp value);

public  void setMenuImage(String value);

public  void setSysType(int value);

public  void setMenuDesc(String value);
}
