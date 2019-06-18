package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscCareProvValue extends DataStructInterface{

  public final static  String S_CareprovTypeid = "CAREPROV_TYPEID";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_CareprovCode = "CAREPROV_CODE";
  public final static  String S_CareprovId = "CAREPROV_ID";


public long getCareprovTypeid();

public String getCareprovName();

public String getOrgId();

public long getLocId();

public String getCareprovCode();

public long getCareprovId();


public  void setCareprovTypeid(long value);

public  void setCareprovName(String value);

public  void setOrgId(String value);

public  void setLocId(long value);

public  void setCareprovCode(String value);

public  void setCareprovId(long value);
}
