package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyItemInfoValue extends DataStructInterface{

  public final static  String S_StudyitemStatus = "STUDYITEM_STATUS";
  public final static  String S_StudyitemBodypart = "STUDYITEM_BODYPART";
  public final static  String S_StudyitemDesc = "STUDYITEM_DESC";
  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyitemHisid = "STUDYITEM_HISID";
  public final static  String S_StudyitemId = "STUDYITEM_ID";
  public final static  String S_StudyitemBodyinfo = "STUDYITEM_BODYINFO";
  public final static  String S_StudyitemEndesc = "STUDYITEM_ENDESC";
  public final static  String S_StudyitemCode = "STUDYITEM_CODE";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_StudyitemItemno = "STUDYITEM_ITEMNO";


public String getStudyitemStatus();

public String getStudyitemBodypart();

public String getStudyitemDesc();

public int getStudyitemNumber();

public long getStudyinfoId();

public String getStudyitemHisid();

public long getStudyitemId();

public String getStudyitemBodyinfo();

public String getStudyitemEndesc();

public String getStudyitemCode();

public float getStudyitemPrice();

public String getStudyitemItemno();


public  void setStudyitemStatus(String value);

public  void setStudyitemBodypart(String value);

public  void setStudyitemDesc(String value);

public  void setStudyitemNumber(int value);

public  void setStudyinfoId(long value);

public  void setStudyitemHisid(String value);

public  void setStudyitemId(long value);

public  void setStudyitemBodyinfo(String value);

public  void setStudyitemEndesc(String value);

public  void setStudyitemCode(String value);

public  void setStudyitemPrice(float value);

public  void setStudyitemItemno(String value);
}
