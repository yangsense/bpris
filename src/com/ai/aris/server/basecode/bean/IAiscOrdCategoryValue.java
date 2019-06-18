package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdCategoryValue extends DataStructInterface{

  public final static  String S_OrdcategoryCode = "ORDCATEGORY_CODE";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrdcategoryEndesc = "ORDCATEGORY_ENDESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrdcategoryDesc = "ORDCATEGORY_DESC";


public String getOrdcategoryCode();

public long getOrdcategoryId();

public String getOrdcategoryEndesc();

public String getOrgId();

public String getOrdcategoryDesc();


public  void setOrdcategoryCode(String value);

public  void setOrdcategoryId(long value);

public  void setOrdcategoryEndesc(String value);

public  void setOrgId(String value);

public  void setOrdcategoryDesc(String value);
}
