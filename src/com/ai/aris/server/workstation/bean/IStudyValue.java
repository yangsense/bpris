package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IStudyValue extends DataStructInterface{

  public final static  String S_Modalitiesinstudy = "MODALITIESINSTUDY";
  public final static  String S_Receptiontime = "RECEPTIONTIME";
  public final static  String S_Verifiedstatus = "VERIFIEDSTATUS";
  public final static  String S_Studykey = "STUDYKEY";
  public final static  String S_Studyuid = "STUDYUID";
  public final static  String S_Studyid = "STUDYID";
  public final static  String S_Hasrisinfo = "HASRISINFO";
  public final static  String S_Referringhospital = "REFERRINGHOSPITAL";
  public final static  String S_Diagnosedstatus = "DIAGNOSEDSTATUS";
  public final static  String S_Studydate = "STUDYDATE";
  public final static  String S_Radiologist = "RADIOLOGIST";
  public final static  String S_Studypriority = "STUDYPRIORITY";
  public final static  String S_Institutionid = "INSTITUTIONID";
  public final static  String S_Procedureid = "PROCEDUREID";
  public final static  String S_Studydescription = "STUDYDESCRIPTION";
  public final static  String S_Receptiondate = "RECEPTIONDATE";
  public final static  String S_Patientkey = "PATIENTKEY";
  public final static  String S_Protocol = "PROTOCOL";
  public final static  String S_Referringdoctor = "REFERRINGDOCTOR";
  public final static  String S_Verifiederror = "VERIFIEDERROR";
  public final static  String S_Studytime = "STUDYTIME";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";
  public final static  String S_Referringdepartment = "REFERRINGDEPARTMENT";


public String getModalitiesinstudy();

public String getReceptiontime();

public String getVerifiedstatus();

public long getStudykey();

public String getStudyuid();

public String getStudyid();

public String getHasrisinfo();

public String getReferringhospital();

public String getDiagnosedstatus();

public String getStudydate();

public String getRadiologist();

public String getStudypriority();

public String getInstitutionid();

public String getProcedureid();

public String getStudydescription();

public String getReceptiondate();

public long getPatientkey();

public String getProtocol();

public String getReferringdoctor();

public String getVerifiederror();

public String getStudytime();

public String getAccessionnumber();

public String getReferringdepartment();


public  void setModalitiesinstudy(String value);

public  void setReceptiontime(String value);

public  void setVerifiedstatus(String value);

public  void setStudykey(long value);

public  void setStudyuid(String value);

public  void setStudyid(String value);

public  void setHasrisinfo(String value);

public  void setReferringhospital(String value);

public  void setDiagnosedstatus(String value);

public  void setStudydate(String value);

public  void setRadiologist(String value);

public  void setStudypriority(String value);

public  void setInstitutionid(String value);

public  void setProcedureid(String value);

public  void setStudydescription(String value);

public  void setReceptiondate(String value);

public  void setPatientkey(long value);

public  void setProtocol(String value);

public  void setReferringdoctor(String value);

public  void setVerifiederror(String value);

public  void setStudytime(String value);

public  void setAccessionnumber(String value);

public  void setReferringdepartment(String value);
}
