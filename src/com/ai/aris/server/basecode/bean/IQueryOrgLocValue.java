package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryOrgLocValue extends DataStructInterface{

  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgId = "ORG_ID";


public String getOrgName();

public String getLocDesc();

public long getLocId();

public String getOrgId();


public  void setOrgName(String value);

public  void setLocDesc(String value);

public  void setLocId(long value);

public  void setOrgId(String value);
}
