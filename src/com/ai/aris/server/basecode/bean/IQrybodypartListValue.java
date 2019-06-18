package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQrybodypartListValue extends DataStructInterface{

  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_BodypartEndesc = "BODYPART_ENDESC";
  public final static  String S_BodypartOrder = "BODYPART_ORDER";
  public final static  String S_BodypartDesc = "BODYPART_DESC";
  public final static  String S_BodypartCode = "BODYPART_CODE";
  public final static  String S_OrgId = "ORG_ID";


public String getItemmastDesc();

public String getBodypartEndesc();

public long getBodypartOrder();

public String getBodypartDesc();

public String getBodypartCode();

public String getOrgId();


public  void setItemmastDesc(String value);

public  void setBodypartEndesc(String value);

public  void setBodypartOrder(long value);

public  void setBodypartDesc(String value);

public  void setBodypartCode(String value);

public  void setOrgId(String value);
}
