package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQrySQLocValue extends DataStructInterface{

  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocName = "LOC_NAME";


public long getLocId();

public String getOrgId();

public String getLocName();


public  void setLocId(long value);

public  void setOrgId(String value);

public  void setLocName(String value);
}
