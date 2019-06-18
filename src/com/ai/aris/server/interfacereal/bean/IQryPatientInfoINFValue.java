package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryPatientInfoINFValue extends DataStructInterface{

  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientStatus = "PATIENT_STATUS";
  public final static  String S_PatientNamepy = "PATIENT_NAMEPY";
  public final static  String S_PatientAddress = "PATIENT_ADDRESS";
  public final static  String S_PatientIdnumber = "PATIENT_IDNUMBER";
  public final static  String S_PatientVocation = "PATIENT_VOCATION";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_PatientMedicareid = "PATIENT_MEDICAREID";
  public final static  String S_PatientMobile = "PATIENT_MOBILE";
  public final static  String S_PatientCardid = "PATIENT_CARDID";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_PatientStation = "PATIENT_STATION";
  public final static  String S_PatientRemark = "PATIENT_REMARK";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_PatientPhone = "PATIENT_PHONE";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_PatientNation = "PATIENT_NATION";
  public final static  String S_PatientDob = "PATIENT_DOB";


public String getPatientSex();

public String getPatientId();

public String getPatientStatus();

public String getPatientNamepy();

public String getPatientAddress();

public String getPatientIdnumber();

public String getPatientVocation();

public String getOrgId();

public String getPatientMedicareid();

public String getPatientMobile();

public String getPatientCardid();

public String getStudystatusCode();

public String getPatientName();

public Timestamp getStudyOperationtime();

public String getPatientStation();

public String getPatientRemark();

public long getPatientGlobalid();

public String getPatientPhone();

public String getPatientAge();

public String getPatientNation();

public Timestamp getPatientDob();


public  void setPatientSex(String value);

public  void setPatientId(String value);

public  void setPatientStatus(String value);

public  void setPatientNamepy(String value);

public  void setPatientAddress(String value);

public  void setPatientIdnumber(String value);

public  void setPatientVocation(String value);

public  void setOrgId(String value);

public  void setPatientMedicareid(String value);

public  void setPatientMobile(String value);

public  void setPatientCardid(String value);

public  void setStudystatusCode(String value);

public  void setPatientName(String value);

public  void setStudyOperationtime(Timestamp value);

public  void setPatientStation(String value);

public  void setPatientRemark(String value);

public  void setPatientGlobalid(long value);

public  void setPatientPhone(String value);

public  void setPatientAge(String value);

public  void setPatientNation(String value);

public  void setPatientDob(Timestamp value);
}
