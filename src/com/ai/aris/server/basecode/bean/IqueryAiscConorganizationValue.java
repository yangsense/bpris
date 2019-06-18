package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IqueryAiscConorganizationValue extends DataStructInterface{

  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ConlocId = "CONLOC_ID";
  public final static  String S_ConorganizatId = "CONORGANIZAT_ID";
  public final static  String S_Hzks = "HZKS";
  public final static  String S_ConorgId = "CONORG_ID";
  public final static  String S_Hzjgmc = "HZJGMC";
  public final static  String S_OrgId = "ORG_ID";


public long getLocId();

public String getConlocId();

public long getConorganizatId();

public String getHzks();

public String getConorgId();

public String getHzjgmc();

public String getOrgId();


public  void setLocId(long value);

public  void setConlocId(String value);

public  void setConorganizatId(long value);

public  void setHzks(String value);

public  void setConorgId(String value);

public  void setHzjgmc(String value);

public  void setOrgId(String value);
}
