package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyinfoRealValue extends DataStructInterface{

  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_OldStudyinfoId = "OLD_STUDYINFO_ID";
  public final static  String S_StudyinfoRealId = "STUDYINFO_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getStudyinfoId();

public Timestamp getCreateTime();

public long getOldStudyinfoId();

public long getStudyinfoRealId();

public String getOrgId();


public  void setStudyinfoId(long value);

public  void setCreateTime(Timestamp value);

public  void setOldStudyinfoId(long value);

public  void setStudyinfoRealId(long value);

public  void setOrgId(String value);
}
