package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQuerySysOperator2OrgValue extends DataStructInterface{

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_StationCode = "STATION_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrgType = "ORG_TYPE";
  public final static  String S_StationName = "STATION_NAME";
  public final static  String S_ParentOrgId = "PARENT_ORG_ID";
  public final static  String S_OrgLevel = "ORG_LEVEL";
  public final static  String S_OrgId = "ORG_ID";


public String getOperatorCode();

public String getStationCode();

public String getOrgName();

public String getOrgType();

public String getStationName();

public String getParentOrgId();

public String getOrgLevel();

public String getOrgId();


public  void setOperatorCode(String value);

public  void setStationCode(String value);

public  void setOrgName(String value);

public  void setOrgType(String value);

public  void setStationName(String value);

public  void setParentOrgId(String value);

public  void setOrgLevel(String value);

public  void setOrgId(String value);
}
