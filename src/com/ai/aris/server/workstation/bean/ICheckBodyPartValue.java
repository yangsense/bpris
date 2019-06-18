package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ICheckBodyPartValue extends DataStructInterface{

  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_Bodypart2Id = "BODYPART2_ID";
  public final static  String S_BodypartDesc = "BODYPART_DESC";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";


public long getItemmastId();

public long getBodypart2Id();

public String getBodypartDesc();

public String getBodypartCode();

public String getOrgId();


public  void setItemmastId(long value);

public  void setBodypart2Id(long value);

public  void setBodypartDesc(String value);

public  void setBodypartCode(String value);

public  void setOrgId(String value);
}
