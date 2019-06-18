package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyItemInterfaceValue extends DataStructInterface{

  public final static  String S_StudyitemStatus = "STUDYITEM_STATUS";
  public final static  String S_StudyitemDesc = "STUDYITEM_DESC";
  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_StudyitemHisid = "STUDYITEM_HISID";
  public final static  String S_StudyitemId = "STUDYITEM_ID";
  public final static  String S_StudyitemCode = "STUDYITEM_CODE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyitemItemno = "STUDYITEM_ITEMNO";
  public final static  String S_StudyitemBodypart = "STUDYITEM_BODYPART";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_StudyitemBodyinfo = "STUDYITEM_BODYINFO";
  public final static  String S_StudyitemEndesc = "STUDYITEM_ENDESC";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";


public String getStudyitemStatus();

public String getStudyitemDesc();

public long getStudyitemNumber();

public String getStudyitemHisid();

public long getStudyitemId();

public String getStudyitemCode();

public String getOrgId();

public String getStudyitemItemno();

public String getStudyitemBodypart();

public String getStudystatusCode();

public long getStudyinfoId();

public Timestamp getStudyOperationtime();

public String getStudyitemBodyinfo();

public String getStudyitemEndesc();

public long getStudyitemPrice();


public  void setStudyitemStatus(String value);

public  void setStudyitemDesc(String value);

public  void setStudyitemNumber(long value);

public  void setStudyitemHisid(String value);

public  void setStudyitemId(long value);

public  void setStudyitemCode(String value);

public  void setOrgId(String value);

public  void setStudyitemItemno(String value);

public  void setStudyitemBodypart(String value);

public  void setStudystatusCode(String value);

public  void setStudyinfoId(long value);

public  void setStudyOperationtime(Timestamp value);

public  void setStudyitemBodyinfo(String value);

public  void setStudyitemEndesc(String value);

public  void setStudyitemPrice(long value);
}
