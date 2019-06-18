package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQueryPatientInfoHZValue extends DataStructInterface{

  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_RoomDesc = "ROOM_DESC";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_PatientNation = "PATIENT_NATION";
  public final static  String S_PatientDob = "PATIENT_DOB";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_PatientAddress = "PATIENT_ADDRESS";
  public final static  String S_StudyLocseqno = "STUDY_LOCSEQNO";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_LocName = "LOC_NAME";
  public final static  String S_OrgIds = "ORG_IDS";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_PatientMobile = "PATIENT_MOBILE";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_PatientCardid = "PATIENT_CARDID";
  public final static  String S_PatientStatus = "PATIENT_STATUS";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_StudyConsultendtime = "STUDY_CONSULTENDTIME";
  public final static  String S_StudylevelId = "STUDYLEVEL_ID";
  public final static  String S_StatusCode = "STATUS_CODE";
  public final static  String S_StudyConsultstarttime = "STUDY_CONSULTSTARTTIME";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_PatientNamepy = "PATIENT_NAMEPY";
  public final static  String S_PatientRemark = "PATIENT_REMARK";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatientMedicareid = "PATIENT_MEDICAREID";
  public final static  String S_PatientPhone = "PATIENT_PHONE";
  public final static  String S_PatientIdnumber = "PATIENT_IDNUMBER";
  public final static  String S_PatientVocation = "PATIENT_VOCATION";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_PatientStation = "PATIENT_STATION";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";


public long getPatientGlobalid();

public String getRoomDesc();

public String getEquipmentDesc();

public String getOrgId();

public String getPatientNation();

public Timestamp getPatientDob();

public Timestamp getStudyAppdate();

public String getPatientAddress();

public long getStudyLocseqno();

public String getStudyAccnumber();

public Timestamp getStudyDatetime();

public String getPatienttypeCode();

public String getAidDoctorid();

public String getLocName();

public String getOrgIds();

public String getStudyDoctorid();

public String getPatientMobile();

public String getStudyItemdesc();

public String getPatientSex();

public String getStudyConsultorg();

public String getPatientName();

public String getPatientCardid();

public String getPatientStatus();

public long getStudyType();

public String getOrgName();

public Timestamp getStudyConsultendtime();

public long getStudylevelId();

public String getStatusCode();

public Timestamp getStudyConsultstarttime();

public String getStudyClinic();

public String getPatientNamepy();

public String getPatientRemark();

public long getLocId();

public String getPatientMedicareid();

public String getPatientPhone();

public String getPatientIdnumber();

public String getPatientVocation();

public String getPatientId();

public long getStudyConsultloc();

public String getPatientStation();

public String getPatientAge();

public long getEquipmentId();

public long getStudyinfoId();

public String getStudystatusCode();


public  void setPatientGlobalid(long value);

public  void setRoomDesc(String value);

public  void setEquipmentDesc(String value);

public  void setOrgId(String value);

public  void setPatientNation(String value);

public  void setPatientDob(Timestamp value);

public  void setStudyAppdate(Timestamp value);

public  void setPatientAddress(String value);

public  void setStudyLocseqno(long value);

public  void setStudyAccnumber(String value);

public  void setStudyDatetime(Timestamp value);

public  void setPatienttypeCode(String value);

public  void setAidDoctorid(String value);

public  void setLocName(String value);

public  void setOrgIds(String value);

public  void setStudyDoctorid(String value);

public  void setPatientMobile(String value);

public  void setStudyItemdesc(String value);

public  void setPatientSex(String value);

public  void setStudyConsultorg(String value);

public  void setPatientName(String value);

public  void setPatientCardid(String value);

public  void setPatientStatus(String value);

public  void setStudyType(long value);

public  void setOrgName(String value);

public  void setStudyConsultendtime(Timestamp value);

public  void setStudylevelId(long value);

public  void setStatusCode(String value);

public  void setStudyConsultstarttime(Timestamp value);

public  void setStudyClinic(String value);

public  void setPatientNamepy(String value);

public  void setPatientRemark(String value);

public  void setLocId(long value);

public  void setPatientMedicareid(String value);

public  void setPatientPhone(String value);

public  void setPatientIdnumber(String value);

public  void setPatientVocation(String value);

public  void setPatientId(String value);

public  void setStudyConsultloc(long value);

public  void setPatientStation(String value);

public  void setPatientAge(String value);

public  void setEquipmentId(long value);

public  void setStudyinfoId(long value);

public  void setStudystatusCode(String value);
}
