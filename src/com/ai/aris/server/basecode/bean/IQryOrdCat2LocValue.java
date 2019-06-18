package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryOrdCat2LocValue extends DataStructInterface{

  public final static  String S_OrdcategoryCode = "ORDCATEGORY_CODE";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_OrdcatId = "ORDCAT_ID";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgCode = "ORG_CODE";
  public final static  String S_Ordcat2locId = "ORDCAT2LOC_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrdcategoryDesc = "ORDCATEGORY_DESC";


public String getOrdcategoryCode();

public String getLocCode();

public String getOrgName();

public long getOrdcatId();

public String getLocDesc();

public long getLocId();

public String getOrgCode();

public long getOrdcat2locId();

public String getOrgId();

public String getOrdcategoryDesc();


public  void setOrdcategoryCode(String value);

public  void setLocCode(String value);

public  void setOrgName(String value);

public  void setOrdcatId(long value);

public  void setLocDesc(String value);

public  void setLocId(long value);

public  void setOrgCode(String value);

public  void setOrdcat2locId(long value);

public  void setOrgId(String value);

public  void setOrdcategoryDesc(String value);
}
