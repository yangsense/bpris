package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscLocTestValue extends DataStructInterface{

  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_LocPhone = "LOC_PHONE";
  public final static  String S_LocType = "LOC_TYPE";
  public final static  String S_LocAddress = "LOC_ADDRESS";
  public final static  String S_LocEndesc = "LOC_ENDESC";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_LocIndex = "LOC_INDEX";
  public final static  String S_OrgId = "ORG_ID";


public String getLocCode();

public String getLocPhone();

public String getLocType();

public String getLocAddress();

public String getLocEndesc();

public String getLocDesc();

public long getServerId();

public long getLocId();

public String getLocIndex();

public String getOrgId();


public  void setLocCode(String value);

public  void setLocPhone(String value);

public  void setLocType(String value);

public  void setLocAddress(String value);

public  void setLocEndesc(String value);

public  void setLocDesc(String value);

public  void setServerId(long value);

public  void setLocId(long value);

public  void setLocIndex(String value);

public  void setOrgId(String value);
}
