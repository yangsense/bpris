package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscBodypartRealValue extends DataStructInterface{

  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldBodypartCode = "OLD_BODYPART_CODE";
  public final static  String S_BodypartRealId = "BODYPART_REAL_ID";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";


public Timestamp getCreateTime();

public long getOldBodypartCode();

public long getBodypartRealId();

public long getBodypartCode();

public String getOrgId();


public  void setCreateTime(Timestamp value);

public  void setOldBodypartCode(long value);

public  void setBodypartRealId(long value);

public  void setBodypartCode(long value);

public  void setOrgId(String value);
}
