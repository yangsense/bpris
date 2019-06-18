package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
public interface IAiscTemplatedirValue extends DataStructInterface{

  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_TemplatedirEnabled = "TEMPLATEDIR_ENABLED";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirDesc = "TEMPLATEDIR_DESC";


public int getTemplatedirOrder();

public int getTemplatedirEnabled();

public long getTemplatedirId();

public long getTemplatedirPdirid();

public String getTemplatedirDesc();


public  void setTemplatedirOrder(int value);

public  void setTemplatedirEnabled(int value);

public  void setTemplatedirId(long value);

public  void setTemplatedirPdirid(long value);

public  void setTemplatedirDesc(String value);
}
