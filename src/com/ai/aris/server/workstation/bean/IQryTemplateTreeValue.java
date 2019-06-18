package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryTemplateTreeValue extends DataStructInterface{

  public final static  String S_TemplatedirPdirid = "TEMPLATEDIR_PDIRID";
  public final static  String S_TemplatedirFlag = "TEMPLATEDIR_FLAG";
  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_TemplatedirOrder = "TEMPLATEDIR_ORDER";
  public final static  String S_Children = "CHILDREN";
  public final static  String S_IsDirectory = "IS_DIRECTORY";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_TemplatedirEnabled = "TEMPLATEDIR_ENABLED";
  public final static  String S_TemplatedirDesc = "TEMPLATEDIR_DESC";
  public final static  String S_OrgId = "ORG_ID";


public long getTemplatedirPdirid();

public long getTemplatedirFlag();

public long getOperatorId();

public long getTemplatedirOrder();

public long getChildren();

public long getIsDirectory();

public String getModalityId();

public long getLocId();

public long getTemplatedirId();

public long getTemplatedirEnabled();

public String getTemplatedirDesc();

public String getOrgId();


public  void setTemplatedirPdirid(long value);

public  void setTemplatedirFlag(long value);

public  void setOperatorId(long value);

public  void setTemplatedirOrder(long value);

public  void setChildren(long value);

public  void setIsDirectory(long value);

public  void setModalityId(String value);

public  void setLocId(long value);

public  void setTemplatedirId(long value);

public  void setTemplatedirEnabled(long value);

public  void setTemplatedirDesc(String value);

public  void setOrgId(String value);
}
