package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryBodyPart2ItemValue extends DataStructInterface{

  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_BodypartTypename = "BODYPART_TYPENAME";
  public final static  String S_BodypartPid = "BODYPART_PID";
  public final static  String S_BodypartType = "BODYPART_TYPE";
  public final static  String S_Bodypart2Id = "BODYPART2_ID";
  public final static  String S_BodypartPname = "BODYPART_PNAME";
  public final static  String S_BodypartDesc = "BODYPART_DESC";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";


public String getItemmastDesc();

public String getOrgName();

public long getItemmastId();

public String getBodypartTypename();

public long getBodypartPid();

public String getBodypartType();

public long getBodypart2Id();

public String getBodypartPname();

public String getBodypartDesc();

public String getBodypartCode();

public String getOrgId();


public  void setItemmastDesc(String value);

public  void setOrgName(String value);

public  void setItemmastId(long value);

public  void setBodypartTypename(String value);

public  void setBodypartPid(long value);

public  void setBodypartType(String value);

public  void setBodypart2Id(long value);

public  void setBodypartPname(String value);

public  void setBodypartDesc(String value);

public  void setBodypartCode(String value);

public  void setOrgId(String value);
}
