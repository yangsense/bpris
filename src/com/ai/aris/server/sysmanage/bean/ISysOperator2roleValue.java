package com.ai.aris.server.sysmanage.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ISysOperator2roleValue extends DataStructInterface{

  public final static  String S_RoleId = "ROLE_ID";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_OperatorCode = "OPERATOR_CODE";


public long getRoleId();

public String getRemarks();

public String getOperatorCode();


public  void setRoleId(long value);

public  void setRemarks(String value);

public  void setOperatorCode(String value);
}
