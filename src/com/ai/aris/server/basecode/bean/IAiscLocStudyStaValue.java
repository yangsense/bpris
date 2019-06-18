package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscLocStudyStaValue extends DataStructInterface{

  public final static  String S_LocStudystaId = "LOC_STUDYSTA_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStatus = "STUDY_STATUS";
  public final static  String S_StatusName = "STATUS_NAME";
  public final static  String S_OrgId = "ORG_ID";


public long getLocStudystaId();

public long getLocId();

public String getStudyStatus();

public String getStatusName();

public String getOrgId();


public  void setLocStudystaId(long value);

public  void setLocId(long value);

public  void setStudyStatus(String value);

public  void setStatusName(String value);

public  void setOrgId(String value);
}
