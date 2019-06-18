package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscOrdSubCategoryValue extends DataStructInterface{

  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrdsubcategoryCode = "ORDSUBCATEGORY_CODE";
  public final static  String S_OrdsubcategoryEndesc = "ORDSUBCATEGORY_ENDESC";
  public final static  String S_OrdsubcategoryDesc = "ORDSUBCATEGORY_DESC";
  public final static  String S_OrdsubcategoryId = "ORDSUBCATEGORY_ID";
  public final static  String S_OrdsubcategoryNote = "ORDSUBCATEGORY_NOTE";


public long getOrdcategoryId();

public String getOrdsubcategoryCode();

public String getOrdsubcategoryEndesc();

public String getOrdsubcategoryDesc();

public long getOrdsubcategoryId();

public String getOrdsubcategoryNote();


public  void setOrdcategoryId(long value);

public  void setOrdsubcategoryCode(String value);

public  void setOrdsubcategoryEndesc(String value);

public  void setOrdsubcategoryDesc(String value);

public  void setOrdsubcategoryId(long value);

public  void setOrdsubcategoryNote(String value);
}
