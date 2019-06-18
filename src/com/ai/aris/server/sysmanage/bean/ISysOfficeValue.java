package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ISysOfficeValue extends DataStructInterface{

  public final static  String S_CreatePerson = "CREATE_PERSON";
  public final static  String S_State = "STATE";
  public final static  String S_OfficeCode = "OFFICE_CODE";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_OfficeName = "OFFICE_NAME";
  public final static  String S_OfficeId = "OFFICE_ID";


public String getCreatePerson();

public String getState();

public String getOfficeCode();

public Timestamp getCreateTime();

public String getOrgCode();

public String getOfficeName();

public String getOfficeId();


public  void setCreatePerson(String value);

public  void setState(String value);

public  void setOfficeCode(String value);

public  void setCreateTime(Timestamp value);

public  void setOrgCode(String value);

public  void setOfficeName(String value);

public  void setOfficeId(String value);
}
