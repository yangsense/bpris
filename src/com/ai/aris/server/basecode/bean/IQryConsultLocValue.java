package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryConsultLocValue extends DataStructInterface{

  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_LocName = "LOC_NAME";
  public final static  String S_ConorgId = "CONORG_ID";
  public final static  String S_ConlocId = "CONLOC_ID";
  public final static  String S_ConorganizatId = "CONORGANIZAT_ID";


public String getOrgId();

public long getLocId();

public String getLocName();

public String getConorgId();

public String getConlocId();

public long getConorganizatId();


public  void setOrgId(String value);

public  void setLocId(long value);

public  void setLocName(String value);

public  void setConorgId(String value);

public  void setConlocId(String value);

public  void setConorganizatId(long value);
}
