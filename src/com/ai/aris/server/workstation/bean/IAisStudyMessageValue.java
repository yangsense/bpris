package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyMessageValue extends DataStructInterface{

  public final static  String S_MessageDestlocid = "MESSAGE_DESTLOCID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_MessageId = "MESSAGE_ID";
  public final static  String S_StatusCode = "STATUS_CODE";
  public final static  String S_StudyDate = "STUDY_DATE";
  public final static  String S_StudyMemo = "STUDY_MEMO";
  public final static  String S_MessageDestuserid = "MESSAGE_DESTUSERID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";


public long getMessageDestlocid();

public String getStudyType();

public long getMessageId();

public String getStatusCode();

public Timestamp getStudyDate();

public String getStudyMemo();

public String getMessageDestuserid();

public long getStudyinfoId();


public  void setMessageDestlocid(long value);

public  void setStudyType(String value);

public  void setMessageId(long value);

public  void setStatusCode(String value);

public  void setStudyDate(Timestamp value);

public  void setStudyMemo(String value);

public  void setMessageDestuserid(String value);

public  void setStudyinfoId(long value);
}
