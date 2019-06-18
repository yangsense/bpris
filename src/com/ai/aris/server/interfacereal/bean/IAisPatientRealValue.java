package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisPatientRealValue extends DataStructInterface{

  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldGlobalId = "OLD_GLOBAL_ID";
  public final static  String S_PatientRealId = "PATIENT_REAL_ID";
  public final static  String S_GlobalId = "GLOBAL_ID";
  public final static  String S_OrgId = "ORG_ID";


public Timestamp getCreateTime();

public long getOldGlobalId();

public long getPatientRealId();

public long getGlobalId();

public String getOrgId();


public  void setCreateTime(Timestamp value);

public  void setOldGlobalId(long value);

public  void setPatientRealId(long value);

public  void setGlobalId(long value);

public  void setOrgId(String value);
}
