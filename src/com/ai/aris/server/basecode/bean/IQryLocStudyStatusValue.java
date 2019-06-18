package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryLocStudyStatusValue extends DataStructInterface{

  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocStudystaId = "LOC_STUDYSTA_ID";
  public final static  String S_LocEndesc = "LOC_ENDESC";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStatus = "STUDY_STATUS";
  public final static  String S_StatusName = "STATUS_NAME";
  public final static  String S_OrgId = "ORG_ID";


public String getLocCode();

public String getOrgName();

public long getLocStudystaId();

public String getLocEndesc();

public String getLocDesc();

public long getLocId();

public String getStudyStatus();

public String getStatusName();

public String getOrgId();


public  void setLocCode(String value);

public  void setOrgName(String value);

public  void setLocStudystaId(long value);

public  void setLocEndesc(String value);

public  void setLocDesc(String value);

public  void setLocId(long value);

public  void setStudyStatus(String value);

public  void setStatusName(String value);

public  void setOrgId(String value);
}
