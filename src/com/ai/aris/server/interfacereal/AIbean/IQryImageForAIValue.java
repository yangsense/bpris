package com.ai.aris.server.interfacereal.AIbean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryImageForAIValue extends DataStructInterface{

  public final static  String S_Eventseconds = "EVENTSECONDS";
  public final static  String S_Sopclass = "SOPCLASS";
  public final static  String S_Seriesuid = "SERIESUID";
  public final static  String S_Studyuid = "STUDYUID";
  public final static  String S_Sopinstanceuid = "SOPINSTANCEUID";
  public final static  String S_Eventtime = "EVENTTIME";
  public final static  String S_Dcmrogserieid = "DCMROGSERIEID";
  public final static  String S_Completed0 = "COMPLETED_0";
  public final static  String S_Completed = "COMPLETED";
  public final static  String S_Completed1 = "COMPLETED_1";
  public final static  String S_Completed2 = "COMPLETED_2";
  public final static  String S_Completed3 = "COMPLETED_3";
  public final static  String S_Completed7 = "COMPLETED_7";
  public final static  String S_Completed6 = "COMPLETED_6";
  public final static  String S_Completed5 = "COMPLETED_5";
  public final static  String S_Completed4 = "COMPLETED_4";
  public final static  String S_Completed9 = "COMPLETED_9";
  public final static  String S_Completed8 = "COMPLETED_8";
  public final static  String S_Calledae = "CALLEDAE";
  public final static  String S_Modality = "MODALITY";
  public final static  String S_Eventdate = "EVENTDATE";
  public final static  String S_Dcmrogfilename = "DCMROGFILENAME";
  public final static  String S_Patientid = "PATIENTID";
  public final static  String S_Callingae = "CALLINGAE";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";


public String getEventseconds();

public String getSopclass();

public String getSeriesuid();

public String getStudyuid();

public String getSopinstanceuid();

public String getEventtime();

public long getDcmrogserieid();

public String getCompleted0();

public String getCompleted();

public String getCompleted1();

public String getCompleted2();

public String getCompleted3();

public String getCompleted7();

public String getCompleted6();

public String getCompleted5();

public String getCompleted4();

public String getCompleted9();

public String getCompleted8();

public String getCalledae();

public String getModality();

public String getEventdate();

public String getDcmrogfilename();

public String getPatientid();

public String getCallingae();

public String getAccessionnumber();


public  void setEventseconds(String value);

public  void setSopclass(String value);

public  void setSeriesuid(String value);

public  void setStudyuid(String value);

public  void setSopinstanceuid(String value);

public  void setEventtime(String value);

public  void setDcmrogserieid(long value);

public  void setCompleted0(String value);

public  void setCompleted(String value);

public  void setCompleted1(String value);

public  void setCompleted2(String value);

public  void setCompleted3(String value);

public  void setCompleted7(String value);

public  void setCompleted6(String value);

public  void setCompleted5(String value);

public  void setCompleted4(String value);

public  void setCompleted9(String value);

public  void setCompleted8(String value);

public  void setCalledae(String value);

public  void setModality(String value);

public  void setEventdate(String value);

public  void setDcmrogfilename(String value);

public  void setPatientid(String value);

public  void setCallingae(String value);

public  void setAccessionnumber(String value);
}
