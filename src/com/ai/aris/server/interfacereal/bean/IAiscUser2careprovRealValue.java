package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscUser2careprovRealValue extends DataStructInterface{

  public final static  String S_OldUser2careprovId = "OLD_USER2CAREPROV_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_User2careprovId = "USER2CAREPROV_ID";
  public final static  String S_User2careprovRealId = "USER2CAREPROV_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOldUser2careprovId();

public Timestamp getCreateTime();

public long getUser2careprovId();

public long getUser2careprovRealId();

public String getOrgId();


public  void setOldUser2careprovId(long value);

public  void setCreateTime(Timestamp value);

public  void setUser2careprovId(long value);

public  void setUser2careprovRealId(long value);

public  void setOrgId(String value);
}
