package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryOrgAndLocInfosValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_OrgName = "ORG_NAME";


public long getOperatorId();

public String getOrgId();

public long getLocId();

public String getLocDesc();

public String getOrgName();


public  void setOperatorId(long value);

public  void setOrgId(String value);

public  void setLocId(long value);

public  void setLocDesc(String value);

public  void setOrgName(String value);
}
