package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscTpldir2locValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_TemplatedirId = "TEMPLATEDIR_ID";
  public final static  String S_Tplcat2locId = "TPLCAT2LOC_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOperatorId();

public long getModalityId();

public long getLocId();

public long getTemplatedirId();

public long getTplcat2locId();

public long getOrgId();


public  void setOperatorId(long value);

public  void setModalityId(long value);

public  void setLocId(long value);

public  void setTemplatedirId(long value);

public  void setTplcat2locId(long value);

public  void setOrgId(long value);
}
