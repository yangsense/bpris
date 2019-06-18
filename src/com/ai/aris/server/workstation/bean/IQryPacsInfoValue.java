package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryPacsInfoValue extends DataStructInterface{

  public final static  String S_Modalitiesinstudy = "MODALITIESINSTUDY";
  public final static  String S_Patientid = "PATIENTID";
  public final static  String S_Receptiondate = "RECEPTIONDATE";
  public final static  String S_Patientkey = "PATIENTKEY";
  public final static  String S_Patientname = "PATIENTNAME";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";


public String getModalitiesinstudy();

public String getPatientid();

public String getReceptiondate();

public long getPatientkey();

public String getPatientname();

public String getAccessionnumber();


public  void setModalitiesinstudy(String value);

public  void setPatientid(String value);

public  void setReceptiondate(String value);

public  void setPatientkey(long value);

public  void setPatientname(String value);

public  void setAccessionnumber(String value);
}
