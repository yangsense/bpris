package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyHistoryInfoValue extends DataStructInterface{

  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyhistoryinfoId = "STUDYHISTORYINFO_ID";
  public final static  String S_StudyMemo = "STUDY_MEMO";
  public final static  String S_StudyUserid = "STUDY_USERID";
  public final static  String S_StudyDate = "STUDY_DATE";


public String getStudystatusCode();

public long getStudyinfoId();

public long getStudyhistoryinfoId();

public String getStudyMemo();

public String getStudyUserid();

public Timestamp getStudyDate();


public  void setStudystatusCode(String value);

public  void setStudyinfoId(long value);

public  void setStudyhistoryinfoId(long value);

public  void setStudyMemo(String value);

public  void setStudyUserid(String value);

public  void setStudyDate(Timestamp value);
}
