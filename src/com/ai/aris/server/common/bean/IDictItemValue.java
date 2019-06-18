package com.ai.aris.server.common.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IDictItemValue extends DataStructInterface{

  public final static  String S_ItemNamePy = "ITEM_NAME_PY";
  public final static  String S_ItemNo = "ITEM_NO";
  public final static  String S_CreateUser = "CREATE_USER";
  public final static  String S_ItemDesc = "ITEM_DESC";
  public final static  String S_MappingItemNo = "MAPPING_ITEM_NO";
  public final static  String S_ItemNamePycap = "ITEM_NAME_PYCAP";
  public final static  String S_ParentItemNo = "PARENT_ITEM_NO";
  public final static  String S_MappingDictName = "MAPPING_DICT_NAME";
  public final static  String S_ItemState = "ITEM_STATE";
  public final static  String S_DictName = "DICT_NAME";
  public final static  String S_ItemName = "ITEM_NAME";
  public final static  String S_ItemOrder = "ITEM_ORDER";
  public final static  String S_CreateDate = "CREATE_DATE";
  public final static  String S_ItemLevel = "ITEM_LEVEL";


public String getItemNamePy();

public String getItemNo();

public String getCreateUser();

public String getItemDesc();

public String getMappingItemNo();

public String getItemNamePycap();

public String getParentItemNo();

public String getMappingDictName();

public String getItemState();

public String getDictName();

public String getItemName();

public long getItemOrder();

public Timestamp getCreateDate();

public long getItemLevel();


public  void setItemNamePy(String value);

public  void setItemNo(String value);

public  void setCreateUser(String value);

public  void setItemDesc(String value);

public  void setMappingItemNo(String value);

public  void setItemNamePycap(String value);

public  void setParentItemNo(String value);

public  void setMappingDictName(String value);

public  void setItemState(String value);

public  void setDictName(String value);

public  void setItemName(String value);

public  void setItemOrder(long value);

public  void setCreateDate(Timestamp value);

public  void setItemLevel(long value);
}
