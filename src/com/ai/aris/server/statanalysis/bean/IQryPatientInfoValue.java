package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryPatientInfoValue extends DataStructInterface{

  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyLocseqno = "STUDY_LOCSEQNO";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_StudylevelId = "STUDYLEVEL_ID";
  public final static  String S_LocName = "LOC_NAME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_RoomDesc = "ROOM_DESC";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_StatusCode = "STATUS_CODE";


public Timestamp getStudyEndtime();

public String getPatientSex();

public String getStudyDoctorid();

public String getPatientId();

public long getStudyLocseqno();

public long getModalityId();

public long getStudylevelId();

public String getLocName();

public String getOrgId();

public String getRoomDesc();

public long getEquipmentId();

public Timestamp getStudyDatetime();

public String getStudyClinic();

public String getStudystatusCode();

public long getStudyinfoId();

public String getPatientName();

public String getAidDoctorid();

public String getEquipmentDesc();

public long getLocId();

public String getPatienttypeCode();

public Timestamp getStudyStarttime();

public String getPatientAge();

public String getStatusCode();


public  void setStudyEndtime(Timestamp value);

public  void setPatientSex(String value);

public  void setStudyDoctorid(String value);

public  void setPatientId(String value);

public  void setStudyLocseqno(long value);

public  void setModalityId(long value);

public  void setStudylevelId(long value);

public  void setLocName(String value);

public  void setOrgId(String value);

public  void setRoomDesc(String value);

public  void setEquipmentId(long value);

public  void setStudyDatetime(Timestamp value);

public  void setStudyClinic(String value);

public  void setStudystatusCode(String value);

public  void setStudyinfoId(long value);

public  void setPatientName(String value);

public  void setAidDoctorid(String value);

public  void setEquipmentDesc(String value);

public  void setLocId(long value);

public  void setPatienttypeCode(String value);

public  void setStudyStarttime(Timestamp value);

public  void setPatientAge(String value);

public  void setStatusCode(String value);
}
