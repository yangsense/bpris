package com.ai.aris.server.interfacereal.yinglian;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisFyYinglianLogValue extends DataStructInterface{

  public final static  String S_Responsebody = "RESPONSEBODY";
  public final static  String S_RequestBody = "REQUEST_BODY";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_InterfaceType = "INTERFACE_TYPE";
  public final static  String S_RequestId = "REQUEST_ID";
  public final static  String S_OrgId = "ORG_ID";


public String getResponsebody();

public String getRequestBody();

public Timestamp getCreateTime();

public String getInterfaceType();

public String getRequestId();

public String getOrgId();


public  void setResponsebody(String value);

public  void setRequestBody(String value);

public  void setCreateTime(Timestamp value);

public  void setInterfaceType(String value);

public  void setRequestId(String value);

public  void setOrgId(String value);
}
