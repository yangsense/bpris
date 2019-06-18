package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryOfficeNameValue extends DataStructInterface{

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_CreateTimes = "CREATE_TIMES";
  public final static  String S_State = "STATE";
  public final static  String S_Id = "ID";
  public final static  String S_OfficeName = "OFFICE_NAME";
  public final static  String S_RelationId = "RELATION_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OfficeId = "OFFICE_ID";


public String getOperatorCode();

public Timestamp getCreateTimes();

public String getState();

public String getId();

public String getOfficeName();

public String getRelationId();

public String getOrgId();

public String getOfficeId();


public  void setOperatorCode(String value);

public  void setCreateTimes(Timestamp value);

public  void setState(String value);

public  void setId(String value);

public  void setOfficeName(String value);

public  void setRelationId(String value);

public  void setOrgId(String value);

public  void setOfficeId(String value);
}
