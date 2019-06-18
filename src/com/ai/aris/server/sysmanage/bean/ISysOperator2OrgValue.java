package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;

public interface ISysOperator2OrgValue extends DataStructInterface {

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_StationCode = "STATION_CODE";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_Includesuborg = "INCLUDESUBORG";
  public final static  String S_OrgType = "ORG_TYPE";
  public final static  String S_ParentOrgId = "PARENT_ORG_ID";
  public final static  String S_OrgLevel = "ORG_LEVEL";
  public final static  String S_OrgId = "ORG_ID";


public String getOperatorCode();

public String getStationCode();

public String getRemarks();

public String getIncludesuborg();

public String getOrgType();

public String getParentOrgId();

public String getOrgLevel();

public String getOrgId();


public  void setOperatorCode(String value);

public  void setStationCode(String value);

public  void setRemarks(String value);

public  void setIncludesuborg(String value);

public  void setOrgType(String value);

public  void setParentOrgId(String value);

public  void setOrgLevel(String value);

public  void setOrgId(String value);
}
