package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.common.DataStructInterface;

import java.sql.Timestamp;

public interface IDictValue extends DataStructInterface {

  public final static  String S_ItemDesc = "ITEM_DESC";
  public final static  String S_ItemState = "ITEM_STATE";
  public final static  String S_CreateUser = "CREATE_USER";
  public final static  String S_ParentItemNo = "PARENT_ITEM_NO";
  public final static  String S_ItemName = "ITEM_NAME";
  public final static  String S_DictName = "DICT_NAME";
  public final static  String S_ItemOrder = "ITEM_ORDER";
  public final static  String S_ItemNamePycap = "ITEM_NAME_PYCAP";
  public final static  String S_ItemNamePy = "ITEM_NAME_PY";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_ItemNo = "ITEM_NO";
  public final static  String S_ItemLevel = "ITEM_LEVEL";


public String getItemDesc();

public String getItemState();

public String getCreateUser();

public String getParentItemNo();

public String getItemName();

public String getDictName();

public long getItemOrder();

public String getItemNamePycap();

public String getItemNamePy();

public Timestamp getCreateDate();

public String getItemNo();

public long getItemLevel();


public  void setItemDesc(String value);

public  void setItemState(String value);

public  void setCreateUser(String value);

public  void setParentItemNo(String value);

public  void setItemName(String value);

public  void setDictName(String value);

public  void setItemOrder(long value);

public  void setItemNamePycap(String value);

public  void setItemNamePy(String value);

public  void setCreateDate(Timestamp value);

public  void setItemNo(String value);

public  void setItemLevel(long value);
}
