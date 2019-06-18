package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscTemplatecatDirValue extends DataStructInterface{

  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirFlag = "TEMPLATEDIR_FLAG";
  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_IsDirectory = "IS_DIRECTORY";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_TemplatedirEnabled = "TEMPLATEDIR_ENABLED";
  public final static  String S_TemplatedirDesc = "TEMPLATEDIR_DESC";
  public final static  String S_OrgId = "ORG_ID";


public long getTemplatedirPdirid();

public int getTemplatedirFlag();

public long getTemplatedirOrder();

public int getIsDirectory();

public long getTemplatedirId();

public int getTemplatedirEnabled();

public String getTemplatedirDesc();

public String getOrgId();


public  void setTemplatedirPdirid(long value);

public  void setTemplatedirFlag(int value);

public  void setTemplatedirOrder(long value);

public  void setIsDirectory(int value);

public  void setTemplatedirId(long value);

public  void setTemplatedirEnabled(int value);

public  void setTemplatedirDesc(String value);

public  void setOrgId(String value);
}
