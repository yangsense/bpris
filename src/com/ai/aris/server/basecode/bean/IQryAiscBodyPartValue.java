package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryAiscBodyPartValue extends DataStructInterface{

  public final static  String S_BodypartEndesc = "BODYPART_ENDESC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LastBodypart = "LAST_BODYPART";
  public final static  String S_BodypartPid = "BODYPART_PID";
  public final static  String S_BodypartType = "BODYPART_TYPE";
  public final static  String S_BodypartOrder = "BODYPART_ORDER";
  public final static  String S_BodypartTypedesc = "BODYPART_TYPEDESC";
  public final static  String S_BodypartDesc = "BODYPART_DESC";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";


public String getBodypartEndesc();

public String getOrgName();

public String getLastBodypart();

public long getBodypartPid();

public String getBodypartType();

public long getBodypartOrder();

public String getBodypartTypedesc();

public String getBodypartDesc();

public String getBodypartCode();

public String getOrgId();


public  void setBodypartEndesc(String value);

public  void setOrgName(String value);

public  void setLastBodypart(String value);

public  void setBodypartPid(long value);

public  void setBodypartType(String value);

public  void setBodypartOrder(long value);

public  void setBodypartTypedesc(String value);

public  void setBodypartDesc(String value);

public  void setBodypartCode(String value);

public  void setOrgId(String value);
}
