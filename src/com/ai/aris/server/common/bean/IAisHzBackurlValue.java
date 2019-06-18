package com.ai.aris.server.common.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisHzBackurlValue extends DataStructInterface{

  public final static  String S_BackUrl = "BACK_URL";
  public final static  String S_OrgId = "ORG_ID";


public String getBackUrl();

public String getOrgId();


public  void setBackUrl(String value);

public  void setOrgId(String value);
}
