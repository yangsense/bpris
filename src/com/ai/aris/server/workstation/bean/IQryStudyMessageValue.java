package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyMessageValue extends DataStructInterface{

  public final static  String S_PaymenttypeId = "PAYMENTTYPE_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_StudyConsulttype = "STUDY_CONSULTTYPE";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_StudyFilmcount = "STUDY_FILMCOUNT";
  public final static  String S_StudyConsultdoctorid = "STUDY_CONSULTDOCTORID";
  public final static  String S_StudyLocseqno = "STUDY_LOCSEQNO";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_MessageDestlocid = "MESSAGE_DESTLOCID";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_StudyConsultation = "STUDY_CONSULTATION";
  public final static  String S_StudyExposurecount = "STUDY_EXPOSURECOUNT";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_StudyTotalfee = "STUDY_TOTALFEE";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_StudyImagecount = "STUDY_IMAGECOUNT";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_PatientHeight = "PATIENT_HEIGHT";
  public final static  String S_MessageDestuserid = "MESSAGE_DESTUSERID";
  public final static  String S_StudyFirstvisit = "STUDY_FIRSTVISIT";
  public final static  String S_StudyRemark = "STUDY_REMARK";
  public final static  String S_StudyWard = "STUDY_WARD";
  public final static  String S_StudyAppdoc = "STUDY_APPDOC";
  public final static  String S_StudyAim = "STUDY_AIM";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyApplocid = "STUDY_APPLOCID";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyEpsodeid = "STUDY_EPSODEID";
  public final static  String S_StudyOperationid = "STUDY_OPERATIONID";
  public final static  String S_StudyUid = "STUDY_UID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_PatientWeight = "PATIENT_WEIGHT";
  public final static  String S_StudyConsultendtime = "STUDY_CONSULTENDTIME";
  public final static  String S_StudylevelId = "STUDYLEVEL_ID";
  public final static  String S_StudyHisappid = "STUDY_HISAPPID";
  public final static  String S_StatusCode = "STATUS_CODE";
  public final static  String S_StudyConsultstarttime = "STUDY_CONSULTSTARTTIME";
  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_StudyDate = "STUDY_DATE";
  public final static  String S_StudyFilmprinted = "STUDY_FILMPRINTED";
  public final static  String S_StudyCheckoutfilm = "STUDY_CHECKOUTFILM";
  public final static  String S_StudyId = "STUDY_ID";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_StudyIsbed = "STUDY_ISBED";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_StudyBedno = "STUDY_BEDNO";
  public final static  String S_StudyEnitemdesc = "STUDY_ENITEMDESC";
  public final static  String S_StudyMemo = "STUDY_MEMO";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";


public long getPaymenttypeId();

public String getOrgId();

public long getStudyHavereport();

public String getStudyConsulttype();

public Timestamp getStudyAppdate();

public long getStudyFilmcount();

public String getStudyConsultdoctorid();

public long getStudyLocseqno();

public String getStudyAccnumber();

public long getMessageDestlocid();

public String getAidDoctorid();

public String getStudyConsultation();

public long getStudyExposurecount();

public String getPatientInpatientid();

public long getStudyTotalfee();

public String getStudyItemdesc();

public long getStudyImagecount();

public String getStudyConsultorg();

public String getPatientHeight();

public String getMessageDestuserid();

public long getStudyFirstvisit();

public String getStudyRemark();

public String getStudyWard();

public String getStudyAppdoc();

public String getStudyAim();

public String getStudyClinic();

public long getLocId();

public long getStudyApplocid();

public long getStudyHaveimage();

public String getStudyEpsodeid();

public String getStudyOperationid();

public String getStudyUid();

public long getStudyinfoId();

public String getStudystatusCode();

public String getPatientOutpatientid();

public long getPatientGlobalid();

public Timestamp getStudyDatetime();

public String getPatienttypeCode();

public String getStudyDoctorid();

public Timestamp getStudyStarttime();

public Timestamp getStudyOperationtime();

public long getStudyType();

public String getPatientWeight();

public Timestamp getStudyConsultendtime();

public long getStudylevelId();

public String getStudyHisappid();

public String getStatusCode();

public Timestamp getStudyConsultstarttime();

public Timestamp getStudyEndtime();

public Timestamp getStudyDate();

public long getStudyFilmprinted();

public long getStudyCheckoutfilm();

public String getStudyId();

public long getRoomId();

public long getStudyIsbed();

public long getStudyConsultloc();

public String getStudyBedno();

public String getStudyEnitemdesc();

public String getStudyMemo();

public long getEquipmentId();


public  void setPaymenttypeId(long value);

public  void setOrgId(String value);

public  void setStudyHavereport(long value);

public  void setStudyConsulttype(String value);

public  void setStudyAppdate(Timestamp value);

public  void setStudyFilmcount(long value);

public  void setStudyConsultdoctorid(String value);

public  void setStudyLocseqno(long value);

public  void setStudyAccnumber(String value);

public  void setMessageDestlocid(long value);

public  void setAidDoctorid(String value);

public  void setStudyConsultation(String value);

public  void setStudyExposurecount(long value);

public  void setPatientInpatientid(String value);

public  void setStudyTotalfee(long value);

public  void setStudyItemdesc(String value);

public  void setStudyImagecount(long value);

public  void setStudyConsultorg(String value);

public  void setPatientHeight(String value);

public  void setMessageDestuserid(String value);

public  void setStudyFirstvisit(long value);

public  void setStudyRemark(String value);

public  void setStudyWard(String value);

public  void setStudyAppdoc(String value);

public  void setStudyAim(String value);

public  void setStudyClinic(String value);

public  void setLocId(long value);

public  void setStudyApplocid(long value);

public  void setStudyHaveimage(long value);

public  void setStudyEpsodeid(String value);

public  void setStudyOperationid(String value);

public  void setStudyUid(String value);

public  void setStudyinfoId(long value);

public  void setStudystatusCode(String value);

public  void setPatientOutpatientid(String value);

public  void setPatientGlobalid(long value);

public  void setStudyDatetime(Timestamp value);

public  void setPatienttypeCode(String value);

public  void setStudyDoctorid(String value);

public  void setStudyStarttime(Timestamp value);

public  void setStudyOperationtime(Timestamp value);

public  void setStudyType(long value);

public  void setPatientWeight(String value);

public  void setStudyConsultendtime(Timestamp value);

public  void setStudylevelId(long value);

public  void setStudyHisappid(String value);

public  void setStatusCode(String value);

public  void setStudyConsultstarttime(Timestamp value);

public  void setStudyEndtime(Timestamp value);

public  void setStudyDate(Timestamp value);

public  void setStudyFilmprinted(long value);

public  void setStudyCheckoutfilm(long value);

public  void setStudyId(String value);

public  void setRoomId(long value);

public  void setStudyIsbed(long value);

public  void setStudyConsultloc(long value);

public  void setStudyBedno(String value);

public  void setStudyEnitemdesc(String value);

public  void setStudyMemo(String value);

public  void setEquipmentId(long value);
}
