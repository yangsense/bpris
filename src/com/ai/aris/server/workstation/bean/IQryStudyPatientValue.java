package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyPatientValue extends DataStructInterface{

  public final static  String S_Studydate = "STUDYDATE";
  public final static  String S_Modalitiesinstudy = "MODALITIESINSTUDY";
  public final static  String S_Birthdate = "BIRTHDATE";
  public final static  String S_Institutionid = "INSTITUTIONID";
  public final static  String S_Gender = "GENDER";
  public final static  String S_Patientid = "PATIENTID";
  public final static  String S_Patientkey = "PATIENTKEY";
  public final static  String S_Patientname = "PATIENTNAME";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";


public String getStudydate();

public String getModalitiesinstudy();

public String getBirthdate();

public String getInstitutionid();

public String getGender();

public String getPatientid();

public long getPatientkey();

public String getPatientname();

public String getAccessionnumber();


public  void setStudydate(String value);

public  void setModalitiesinstudy(String value);

public  void setBirthdate(String value);

public  void setInstitutionid(String value);

public  void setGender(String value);

public  void setPatientid(String value);

public  void setPatientkey(long value);

public  void setPatientname(String value);

public  void setAccessionnumber(String value);
}
