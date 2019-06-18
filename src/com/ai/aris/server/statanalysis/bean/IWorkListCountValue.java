package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IWorkListCountValue extends DataStructInterface{

  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyApplocid = "STUDY_APPLOCID";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_Bodyitem = "BODYITEM";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CStudyOperationtime = "C_STUDY_OPERATIONTIME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_StudyAppdoc = "STUDY_APPDOC";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_StudyBedno = "STUDY_BEDNO";
  public final static  String S_StudyAppdocname = "STUDY_APPDOCNAME";
  public final static  String S_PatientAge = "PATIENT_AGE";


public String getStudyItemdesc();

public String getPatientSex();

public long getStudyApplocid();

public String getPatientInpatientid();

public String getBodyitem();

public String getLocDesc();

public String getCStudyOperationtime();

public String getOrgId();

public long getEquipmentId();

public String getStudyAppdoc();

public String getStudyAccnumber();

public long getStudyinfoId();

public String getPatientName();

public String getEquipmentDesc();

public Timestamp getStudyAppdate();

public long getLocId();

public String getPatienttypeCode();

public String getStudyBedno();

public String getStudyAppdocname();

public String getPatientAge();


public  void setStudyItemdesc(String value);

public  void setPatientSex(String value);

public  void setStudyApplocid(long value);

public  void setPatientInpatientid(String value);

public  void setBodyitem(String value);

public  void setLocDesc(String value);

public  void setCStudyOperationtime(String value);

public  void setOrgId(String value);

public  void setEquipmentId(long value);

public  void setStudyAppdoc(String value);

public  void setStudyAccnumber(String value);

public  void setStudyinfoId(long value);

public  void setPatientName(String value);

public  void setEquipmentDesc(String value);

public  void setStudyAppdate(Timestamp value);

public  void setLocId(long value);

public  void setPatienttypeCode(String value);

public  void setStudyBedno(String value);

public  void setStudyAppdocname(String value);

public  void setPatientAge(String value);
}
