package com.ai.aris.server.common.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryOperatorAscriptionValue extends DataStructInterface{

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_OperatorPsw = "OPERATOR_PSW";
  public final static  String S_OperatorName = "OPERATOR_NAME";
  public final static  String S_OrgId = "ORG_ID";


public String getOperatorCode();

public String getOperatorPsw();

public String getOperatorName();

public String getOrgId();


public  void setOperatorCode(String value);

public  void setOperatorPsw(String value);

public  void setOperatorName(String value);

public  void setOrgId(String value);
}
