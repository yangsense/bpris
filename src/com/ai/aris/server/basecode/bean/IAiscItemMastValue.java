package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscItemMastValue extends DataStructInterface{

  public final static  String S_ItemmastPrice = "ITEMMAST_PRICE";
  public final static  String S_ItemmastEndesc = "ITEMMAST_ENDESC";
  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_PyInitial = "PY_INITIAL";
  public final static  String S_ItemmastIsenhanced = "ITEMMAST_ISENHANCED";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_ItemmastExposurecount = "ITEMMAST_EXPOSURECOUNT";
  public final static  String S_ItemmastSevice = "ITEMMAST_SEVICE";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_ItemmastCode = "ITEMMAST_CODE";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ItemmastWeight = "ITEMMAST_WEIGHT";


public float getItemmastPrice();

public String getItemmastEndesc();

public String getItemmastDesc();

public String getPyInitial();

public int getItemmastIsenhanced();

public long getOrdcategoryId();

public int getItemmastExposurecount();

public int getItemmastSevice();

public long getItemmastId();

public String getItemmastCode();

public long getOrdsubcategoryId();

public String getOrgId();

public float getItemmastWeight();


public  void setItemmastPrice(float value);

public  void setItemmastEndesc(String value);

public  void setItemmastDesc(String value);

public  void setPyInitial(String value);

public  void setItemmastIsenhanced(int value);

public  void setOrdcategoryId(long value);

public  void setItemmastExposurecount(int value);

public  void setItemmastSevice(int value);

public  void setItemmastId(long value);

public  void setItemmastCode(String value);

public  void setOrdsubcategoryId(long value);

public  void setOrgId(String value);

public  void setItemmastWeight(float value);
}
