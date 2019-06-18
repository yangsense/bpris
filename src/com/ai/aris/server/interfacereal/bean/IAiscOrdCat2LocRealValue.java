package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdCat2LocRealValue extends DataStructInterface{

  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldOrdcat2locId = "OLD_ORDCAT2LOC_ID";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_Ordcat2locRealId = "ORDCAT2LOC_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";


public Timestamp getCreateTime();

public long getOldOrdcat2locId();

public long getOrdcat2locId();

public long getOrdcat2locRealId();

public String getOrgId();


public  void setCreateTime(Timestamp value);

public  void setOldOrdcat2locId(long value);

public  void setOrdcat2locId(long value);

public  void setOrdcat2locRealId(long value);

public  void setOrgId(String value);
}
