package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryRisInfoValue extends DataStructInterface{

  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";


public Timestamp getStudyEndtime();

public String getStudyItemdesc();

public String getPatientId();

public long getStudyType();

public String getStudyConsultorg();

public String getOrgId();

public Timestamp getStudyDatetime();

public String getStudyAccnumber();

public String getPatientName();

public long getStudyinfoId();

public Timestamp getStudyOperationtime();

public long getLocId();

public Timestamp getStudyStarttime();


public  void setStudyEndtime(Timestamp value);

public  void setStudyItemdesc(String value);

public  void setPatientId(String value);

public  void setStudyType(long value);

public  void setStudyConsultorg(String value);

public  void setOrgId(String value);

public  void setStudyDatetime(Timestamp value);

public  void setStudyAccnumber(String value);

public  void setPatientName(String value);

public  void setStudyinfoId(long value);

public  void setStudyOperationtime(Timestamp value);

public  void setLocId(long value);

public  void setStudyStarttime(Timestamp value);
}
