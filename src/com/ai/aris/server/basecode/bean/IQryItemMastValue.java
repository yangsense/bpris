package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryItemMastValue extends DataStructInterface{

  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_ItemmastPrice = "ITEMMAST_PRICE";
  public final static  String S_PyInitial = "PY_INITIAL";
  public final static  String S_ItemmastIsenhanced = "ITEMMAST_ISENHANCED";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_ItemmastExposurecount = "ITEMMAST_EXPOSURECOUNT";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_ItemmastSevice = "ITEMMAST_SEVICE";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ItemmastWeight = "ITEMMAST_WEIGHT";
  public final static  String S_ItemmastEndesc = "ITEMMAST_ENDESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ItemmastCode = "ITEMMAST_CODE";


public String getItemmastDesc();

public long getItemmastPrice();

public String getPyInitial();

public long getItemmastIsenhanced();

public long getOrdcategoryId();

public long getItemmastExposurecount();

public long getItemmastId();

public long getItemmastSevice();

public long getOrdsubcategoryId();

public String getOrgId();

public long getItemmastWeight();

public String getItemmastEndesc();

public long getLocId();

public String getItemmastCode();


public  void setItemmastDesc(String value);

public  void setItemmastPrice(long value);

public  void setPyInitial(String value);

public  void setItemmastIsenhanced(long value);

public  void setOrdcategoryId(long value);

public  void setItemmastExposurecount(long value);

public  void setItemmastId(long value);

public  void setItemmastSevice(long value);

public  void setOrdsubcategoryId(long value);

public  void setOrgId(String value);

public  void setItemmastWeight(long value);

public  void setItemmastEndesc(String value);

public  void setLocId(long value);

public  void setItemmastCode(String value);
}
