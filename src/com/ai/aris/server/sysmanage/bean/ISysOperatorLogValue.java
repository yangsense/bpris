package com.ai.aris.server.sysmanage.bean;

import com.ai.appframe2.common.DataStructInterface;

import java.sql.Timestamp;

public interface ISysOperatorLogValue extends DataStructInterface {

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_LogId = "LOG_ID";
  public final static  String S_OperatorType = "OPERATOR_TYPE";
  public final static  String S_OperatorDesc = "OPERATOR_DESC";
  public final static  String S_OperatorDate = "OPERATOR_DATE";


public String getOperatorCode();

public long getLogId();

public String getOperatorType();

public String getOperatorDesc();

public Timestamp getOperatorDate();


public  void setOperatorCode(String value);

public  void setLogId(long value);

public  void setOperatorType(String value);

public  void setOperatorDesc(String value);

public  void setOperatorDate(Timestamp value);
}
