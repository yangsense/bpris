package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscQueryOperator2CareProvValue extends DataStructInterface{

  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_CareprovTypeid = "CAREPROV_TYPEID";
  public final static  String S_CareprovCode = "CAREPROV_CODE";
  public final static  String S_User2careprovId = "USER2CAREPROV_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_CareprovId = "CAREPROV_ID";
  public final static  String S_OrgId = "ORG_ID";


public String getOperatorCode();

public long getOperatorId();

public long getCareprovTypeid();

public String getCareprovCode();

public long getUser2careprovId();

public long getLocId();

public String getCareprovName();

public long getCareprovId();

public String getOrgId();


public  void setOperatorCode(String value);

public  void setOperatorId(long value);

public  void setCareprovTypeid(long value);

public  void setCareprovCode(String value);

public  void setUser2careprovId(long value);

public  void setLocId(long value);

public  void setCareprovName(String value);

public  void setCareprovId(long value);

public  void setOrgId(String value);
}
