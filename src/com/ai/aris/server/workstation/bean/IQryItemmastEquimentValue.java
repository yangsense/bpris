package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryItemmastEquimentValue extends DataStructInterface{

  public final static  String S_ItemmastPrice = "ITEMMAST_PRICE";
  public final static  String S_ItemmastDesc = "ITEMMAST_DESC";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_ItemmastCode = "ITEMMAST_CODE";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getItemmastPrice();

public String getItemmastDesc();

public long getEquipmentId();

public long getOrdcategoryId();

public long getItemmastId();

public String getItemmastCode();

public long getOrdsubcategoryId();

public String getOrgId();


public  void setItemmastPrice(long value);

public  void setItemmastDesc(String value);

public  void setEquipmentId(long value);

public  void setOrdcategoryId(long value);

public  void setItemmastId(long value);

public  void setItemmastCode(String value);

public  void setOrdsubcategoryId(long value);

public  void setOrgId(String value);
}
