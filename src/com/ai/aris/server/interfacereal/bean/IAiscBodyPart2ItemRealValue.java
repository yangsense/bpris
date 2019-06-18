package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscBodyPart2ItemRealValue extends DataStructInterface{

  public final static  String S_ItemRealId = "ITEM_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ItemId = "ITEM_ID";
  public final static  String S_OldItemId = "OLD_ITEM_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getItemRealId();

public Timestamp getCreateTime();

public long getItemId();

public long getOldItemId();

public String getOrgId();


public  void setItemRealId(long value);

public  void setCreateTime(Timestamp value);

public  void setItemId(long value);

public  void setOldItemId(long value);

public  void setOrgId(String value);
}
