package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscTemplatecatValue extends DataStructInterface{

  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_TemplatecatDesc = "TEMPLATECAT_DESC";
  public final static  String S_TemplatecatType = "TEMPLATECAT_TYPE";
  public final static  String S_TemplatecatEnabled = "TEMPLATECAT_ENABLED";


public long getTemplatecatId();

public String getTemplatecatDesc();

public int getTemplatecatType();

public int getTemplatecatEnabled();


public  void setTemplatecatId(long value);

public  void setTemplatecatDesc(String value);

public  void setTemplatecatType(int value);

public  void setTemplatecatEnabled(int value);
}
