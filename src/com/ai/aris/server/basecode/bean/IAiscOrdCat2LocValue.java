package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdCat2LocValue extends DataStructInterface{

  public final static  String S_OrdcatId = "ORDCAT_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOrdcatId();

public long getLocId();

public long getOrdcat2locId();

public String getOrgId();


public  void setOrdcatId(long value);

public  void setLocId(long value);

public  void setOrdcat2locId(long value);

public  void setOrgId(String value);
}
