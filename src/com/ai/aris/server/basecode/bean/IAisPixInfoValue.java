package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisPixInfoValue extends DataStructInterface{

  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_OrgId = "ORG_ID";


public String getPatientId();

public long getPatientGlobalid();

public String getOrgId();


public  void setPatientId(String value);

public  void setPatientGlobalid(long value);

public  void setOrgId(String value);
}
