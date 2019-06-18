package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscTplcat2locValue extends DataStructInterface{

  public final static  String S_OperatorId = "OPERATOR_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_TemplatecatId = "TEMPLATECAT_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Tplcat2locId = "TPLCAT2LOC_ID";
  public final static  String S_ModalityId = "MODALITY_ID";


public long getOperatorId();

public long getOrgId();

public long getTemplatecatId();

public long getLocId();

public long getTplcat2locId();

public long getModalityId();


public  void setOperatorId(long value);

public  void setOrgId(long value);

public  void setTemplatecatId(long value);

public  void setLocId(long value);

public  void setTplcat2locId(long value);

public  void setModalityId(long value);
}
