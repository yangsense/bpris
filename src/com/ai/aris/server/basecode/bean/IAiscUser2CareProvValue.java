package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscUser2CareProvValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_User2careprovId = "USER2CAREPROV_ID";
  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_CareprovId = "CAREPROV_ID";


public long getOperatorId();

public long getUser2careprovId();

public String getOperatorCode();

public long getCareprovId();


public  void setOperatorId(long value);

public  void setUser2careprovId(long value);

public  void setOperatorCode(String value);

public  void setCareprovId(long value);
}
