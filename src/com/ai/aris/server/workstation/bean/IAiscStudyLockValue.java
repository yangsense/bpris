package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscStudyLockValue extends DataStructInterface{

  public final static  String S_StartTime = "START_TIME";
  public final static  String S_EndTime = "END_TIME";
  public final static  String S_LockStatus = "LOCK_STATUS";
  public final static  String S_LockIp = "LOCK_IP";
  public final static  String S_UserId = "USER_ID";
  public final static  String S_UserName = "USER_NAME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";


public Timestamp getStartTime();

public Timestamp getEndTime();

public String getLockStatus();

public String getLockIp();

public String getUserId();

public String getUserName();

public long getStudyinfoId();


public  void setStartTime(Timestamp value);

public  void setEndTime(Timestamp value);

public  void setLockStatus(String value);

public  void setLockIp(String value);

public  void setUserId(String value);

public  void setUserName(String value);

public  void setStudyinfoId(long value);
}
