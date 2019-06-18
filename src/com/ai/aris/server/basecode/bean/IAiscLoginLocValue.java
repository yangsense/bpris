package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscLoginLocValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_LoginlocId = "LOGINLOC_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOperatorId();

public long getLoginlocId();

public long getLocId();

public String getOrgId();


public  void setOperatorId(long value);

public  void setLoginlocId(long value);

public  void setLocId(long value);

public  void setOrgId(String value);
}
