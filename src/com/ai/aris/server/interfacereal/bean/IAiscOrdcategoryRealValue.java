package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdcategoryRealValue extends DataStructInterface{

  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrdcategoryRealId = "ORDCATEGORY_REAL_ID";
  public final static  String S_OldOrdcategoryId = "OLD_ORDCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";


public Timestamp getCreateTime();

public long getOrdcategoryId();

public long getOrdcategoryRealId();

public long getOldOrdcategoryId();

public String getOrgId();


public  void setCreateTime(Timestamp value);

public  void setOrdcategoryId(long value);

public  void setOrdcategoryRealId(long value);

public  void setOldOrdcategoryId(long value);

public  void setOrgId(String value);
}
