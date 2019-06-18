package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscHisDatasourceValue extends DataStructInterface{

  public final static  String S_SourceUrl = "SOURCE_URL";
  public final static  String S_SourceId = "SOURCE_ID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_PatientTypecode = "PATIENT_TYPECODE";
  public final static  String S_SourceUser = "SOURCE_USER";
  public final static  String S_Status = "STATUS";
  public final static  String S_SourcePassword = "SOURCE_PASSWORD";
  public final static  String S_OrgId = "ORG_ID";


public String getSourceUrl();

public long getSourceId();

public String getOrgName();

public String getPatientTypecode();

public String getSourceUser();

public String getStatus();

public String getSourcePassword();

public String getOrgId();


public  void setSourceUrl(String value);

public  void setSourceId(long value);

public  void setOrgName(String value);

public  void setPatientTypecode(String value);

public  void setSourceUser(String value);

public  void setStatus(String value);

public  void setSourcePassword(String value);

public  void setOrgId(String value);
}
