package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscItemmastRealValue extends DataStructInterface{

  public final static  String S_ItemmastRealId = "ITEMMAST_REAL_ID";
  public final static  String S_OldItemmastId = "OLD_ITEMMAST_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getItemmastRealId();

public long getOldItemmastId();

public Timestamp getCreateTime();

public long getItemmastId();

public String getOrgId();


public  void setItemmastRealId(long value);

public  void setOldItemmastId(long value);

public  void setCreateTime(Timestamp value);

public  void setItemmastId(long value);

public  void setOrgId(String value);
}
