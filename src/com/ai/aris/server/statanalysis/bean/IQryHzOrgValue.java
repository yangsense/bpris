package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryHzOrgValue extends DataStructInterface{

  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ConlocId = "CONLOC_ID";
  public final static  String S_ConorganizatId = "CONORGANIZAT_ID";
  public final static  String S_ConorgId = "CONORG_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocName = "LOC_NAME";


public String getOrgName();

public long getLocId();

public String getConlocId();

public long getConorganizatId();

public String getConorgId();

public String getOrgId();

public String getLocName();


public  void setOrgName(String value);

public  void setLocId(long value);

public  void setConlocId(String value);

public  void setConorganizatId(long value);

public  void setConorgId(String value);

public  void setOrgId(String value);

public  void setLocName(String value);
}
