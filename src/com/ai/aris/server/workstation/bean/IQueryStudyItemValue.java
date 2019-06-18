package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryStudyItemValue extends DataStructInterface{

  public final static  String S_StudyitemDesc = "STUDYITEM_DESC";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyitemBodyinfo = "STUDYITEM_BODYINFO";


public String getStudyitemDesc();

public long getStudyinfoId();

public String getStudyitemBodyinfo();


public  void setStudyitemDesc(String value);

public  void setStudyinfoId(long value);

public  void setStudyitemBodyinfo(String value);
}
