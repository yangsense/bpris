package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdsubcategoryRealValue extends DataStructInterface{

  public final static  String S_SubcateRealId = "SUBCATE_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_SubcateId = "SUBCATE_ID";
  public final static  String S_OldSubcateId = "OLD_SUBCATE_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getSubcateRealId();

public Timestamp getCreateTime();

public long getSubcateId();

public long getOldSubcateId();

public String getOrgId();


public  void setSubcateRealId(long value);

public  void setCreateTime(Timestamp value);

public  void setSubcateId(long value);

public  void setOldSubcateId(long value);

public  void setOrgId(String value);
}
