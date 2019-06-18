package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AisAiDiagnosisBean extends DataContainer implements DataContainerInterface,IAisAiDiagnosisValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AisAiDiagnosis";



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

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisAiDiagnosisBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initPatientBirthday(String value){
     this.initProperty(S_PatientBirthday,value);
  }
  public  void setPatientBirthday(String value){
     this.set(S_PatientBirthday,value);
  }
  public  void setPatientBirthdayNull(){
     this.set(S_PatientBirthday,null);
  }

  public String getPatientBirthday(){
       return DataType.getAsString(this.get(S_PatientBirthday));
  
  }
  public String getPatientBirthdayInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientBirthday));
      }

  public void initPatientId(String value){
     this.initProperty(S_PatientId,value);
  }
  public  void setPatientId(String value){
     this.set(S_PatientId,value);
  }
  public  void setPatientIdNull(){
     this.set(S_PatientId,null);
  }

  public String getPatientId(){
       return DataType.getAsString(this.get(S_PatientId));
  
  }
  public String getPatientIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientId));
      }

  public void initStudyType(long value){
     this.initProperty(S_StudyType,new Long(value));
  }
  public  void setStudyType(long value){
     this.set(S_StudyType,new Long(value));
  }
  public  void setStudyTypeNull(){
     this.set(S_StudyType,null);
  }

  public long getStudyType(){
        return DataType.getAsLong(this.get(S_StudyType));
  
  }
  public long getStudyTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyType));
      }

  public void initGlobalStudyId(long value){
     this.initProperty(S_GlobalStudyId,new Long(value));
  }
  public  void setGlobalStudyId(long value){
     this.set(S_GlobalStudyId,new Long(value));
  }
  public  void setGlobalStudyIdNull(){
     this.set(S_GlobalStudyId,null);
  }

  public long getGlobalStudyId(){
        return DataType.getAsLong(this.get(S_GlobalStudyId));
  
  }
  public long getGlobalStudyIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_GlobalStudyId));
      }

  public void initResultId(long value){
     this.initProperty(S_ResultId,new Long(value));
  }
  public  void setResultId(long value){
     this.set(S_ResultId,new Long(value));
  }
  public  void setResultIdNull(){
     this.set(S_ResultId,null);
  }

  public long getResultId(){
        return DataType.getAsLong(this.get(S_ResultId));
  
  }
  public long getResultIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ResultId));
      }

  public void initUploadTime(Timestamp value){
     this.initProperty(S_UploadTime,value);
  }
  public  void setUploadTime(Timestamp value){
     this.set(S_UploadTime,value);
  }
  public  void setUploadTimeNull(){
     this.set(S_UploadTime,null);
  }

  public Timestamp getUploadTime(){
        return DataType.getAsDateTime(this.get(S_UploadTime));
  
  }
  public Timestamp getUploadTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_UploadTime));
      }

  public void initUpdateTime(Timestamp value){
     this.initProperty(S_UpdateTime,value);
  }
  public  void setUpdateTime(Timestamp value){
     this.set(S_UpdateTime,value);
  }
  public  void setUpdateTimeNull(){
     this.set(S_UpdateTime,null);
  }

  public Timestamp getUpdateTime(){
        return DataType.getAsDateTime(this.get(S_UpdateTime));
  
  }
  public Timestamp getUpdateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_UpdateTime));
      }

  public void initStatus(String value){
     this.initProperty(S_Status,value);
  }
  public  void setStatus(String value){
     this.set(S_Status,value);
  }
  public  void setStatusNull(){
     this.set(S_Status,null);
  }

  public String getStatus(){
       return DataType.getAsString(this.get(S_Status));
  
  }
  public String getStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Status));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }

  public void initStudyDate(String value){
     this.initProperty(S_StudyDate,value);
  }
  public  void setStudyDate(String value){
     this.set(S_StudyDate,value);
  }
  public  void setStudyDateNull(){
     this.set(S_StudyDate,null);
  }

  public String getStudyDate(){
       return DataType.getAsString(this.get(S_StudyDate));
  
  }
  public String getStudyDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDate));
      }

  public void initPatientGender(long value){
     this.initProperty(S_PatientGender,new Long(value));
  }
  public  void setPatientGender(long value){
     this.set(S_PatientGender,new Long(value));
  }
  public  void setPatientGenderNull(){
     this.set(S_PatientGender,null);
  }

  public long getPatientGender(){
        return DataType.getAsLong(this.get(S_PatientGender));
  
  }
  public long getPatientGenderInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_PatientGender));
      }

  public void initResultMsg(String value){
     this.initProperty(S_ResultMsg,value);
  }
  public  void setResultMsg(String value){
     this.set(S_ResultMsg,value);
  }
  public  void setResultMsgNull(){
     this.set(S_ResultMsg,null);
  }

  public String getResultMsg(){
       return DataType.getAsString(this.get(S_ResultMsg));
  
  }
  public String getResultMsgInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ResultMsg));
      }

  public void initMessage(String value){
     this.initProperty(S_Message,value);
  }
  public  void setMessage(String value){
     this.set(S_Message,value);
  }
  public  void setMessageNull(){
     this.set(S_Message,null);
  }

  public String getMessage(){
       return DataType.getAsString(this.get(S_Message));
  
  }
  public String getMessageInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Message));
      }

  public void initStudyId(String value){
     this.initProperty(S_StudyId,value);
  }
  public  void setStudyId(String value){
     this.set(S_StudyId,value);
  }
  public  void setStudyIdNull(){
     this.set(S_StudyId,null);
  }

  public String getStudyId(){
       return DataType.getAsString(this.get(S_StudyId));
  
  }
  public String getStudyIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyId));
      }

  public void initPatientName(String value){
     this.initProperty(S_PatientName,value);
  }
  public  void setPatientName(String value){
     this.set(S_PatientName,value);
  }
  public  void setPatientNameNull(){
     this.set(S_PatientName,null);
  }

  public String getPatientName(){
       return DataType.getAsString(this.get(S_PatientName));
  
  }
  public String getPatientNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientName));
      }

  public void initUploadImgNum(long value){
     this.initProperty(S_UploadImgNum,new Long(value));
  }
  public  void setUploadImgNum(long value){
     this.set(S_UploadImgNum,new Long(value));
  }
  public  void setUploadImgNumNull(){
     this.set(S_UploadImgNum,null);
  }

  public long getUploadImgNum(){
        return DataType.getAsLong(this.get(S_UploadImgNum));
  
  }
  public long getUploadImgNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_UploadImgNum));
      }

  public void initStudyName(String value){
     this.initProperty(S_StudyName,value);
  }
  public  void setStudyName(String value){
     this.set(S_StudyName,value);
  }
  public  void setStudyNameNull(){
     this.set(S_StudyName,null);
  }

  public String getStudyName(){
       return DataType.getAsString(this.get(S_StudyName));
  
  }
  public String getStudyNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyName));
      }

  public void initCode(String value){
     this.initProperty(S_Code,value);
  }
  public  void setCode(String value){
     this.set(S_Code,value);
  }
  public  void setCodeNull(){
     this.set(S_Code,null);
  }

  public String getCode(){
       return DataType.getAsString(this.get(S_Code));
  
  }
  public String getCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Code));
      }


 
 }

