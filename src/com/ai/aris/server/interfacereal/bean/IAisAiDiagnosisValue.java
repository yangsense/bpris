package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisAiDiagnosisValue extends DataStructInterface{

  public final static  String S_PatientBirthday = "PATIENT_BIRTHDAY";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_GlobalStudyId = "GLOBAL_STUDY_ID";
  public final static  String S_ResultId = "RESULT_ID";
  public final static  String S_UploadTime = "UPLOAD_TIME";
  public final static  String S_UpdateTime = "UPDATE_TIME";
  public final static  String S_Status = "STATUS";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyDate = "STUDY_DATE";
  public final static  String S_PatientGender = "PATIENT_GENDER";
  public final static  String S_ResultMsg = "RESULT_MSG";
  public final static  String S_Message = "MESSAGE";
  public final static  String S_StudyId = "STUDY_ID";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_UploadImgNum = "UPLOAD_IMG_NUM";
  public final static  String S_StudyName = "STUDY_NAME";
  public final static  String S_Code = "CODE";


public String getPatientBirthday();

public String getPatientId();

public long getStudyType();

public long getGlobalStudyId();

public long getResultId();

public Timestamp getUploadTime();

public Timestamp getUpdateTime();

public String getStatus();

public String getOrgId();

public String getStudyDate();

public long getPatientGender();

public String getResultMsg();

public String getMessage();

public String getStudyId();

public String getPatientName();

public long getUploadImgNum();

public String getStudyName();

public String getCode();


public  void setPatientBirthday(String value);

public  void setPatientId(String value);

public  void setStudyType(long value);

public  void setGlobalStudyId(long value);

public  void setResultId(long value);

public  void setUploadTime(Timestamp value);

public  void setUpdateTime(Timestamp value);

public  void setStatus(String value);

public  void setOrgId(String value);

public  void setStudyDate(String value);

public  void setPatientGender(long value);

public  void setResultMsg(String value);

public  void setMessage(String value);

public  void setStudyId(String value);

public  void setPatientName(String value);

public  void setUploadImgNum(long value);

public  void setStudyName(String value);

public  void setCode(String value);
}
