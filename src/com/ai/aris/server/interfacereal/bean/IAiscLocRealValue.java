package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscLocRealValue extends DataStructInterface{

  public final static  String S_LocRealId = "LOC_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OldLocId = "OLD_LOC_ID";


public long getLocRealId();

public Timestamp getCreateTime();

public long getLocId();

public String getOrgId();

public long getOldLocId();


public  void setLocRealId(long value);

public  void setCreateTime(Timestamp value);

public  void setLocId(long value);

public  void setOrgId(String value);

public  void setOldLocId(long value);
}
