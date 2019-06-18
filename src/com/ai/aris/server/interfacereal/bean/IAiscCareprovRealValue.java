package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscCareprovRealValue extends DataStructInterface{

  public final static  String S_OldCareprovId = "OLD_CAREPROV_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_CareprovRealId = "CAREPROV_REAL_ID";
  public final static  String S_CareprovId = "CAREPROV_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOldCareprovId();

public Timestamp getCreateTime();

public long getCareprovRealId();

public long getCareprovId();

public String getOrgId();


public  void setOldCareprovId(long value);

public  void setCreateTime(Timestamp value);

public  void setCareprovRealId(long value);

public  void setCareprovId(long value);

public  void setOrgId(String value);
}
