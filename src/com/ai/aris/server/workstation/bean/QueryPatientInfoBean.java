package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QueryPatientInfoBean extends DataContainer implements DataContainerInterface,IQueryPatientInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QueryPatientInfo";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_StudyLocseqno = "STUDY_LOCSEQNO";
  public final static  String S_PatientStatus = "PATIENT_STATUS";
  public final static  String S_PatientNamepy = "PATIENT_NAMEPY";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_StudylevelId = "STUDYLEVEL_ID";
  public final static  String S_PatientAddress = "PATIENT_ADDRESS";
  public final static  String S_RoomDesc = "ROOM_DESC";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_PatientRemark = "PATIENT_REMARK";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_PatientNation = "PATIENT_NATION";
  public final static  String S_StatusCode = "STATUS_CODE";
  public final static  String S_PatientDob = "PATIENT_DOB";
  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientIdnumber = "PATIENT_IDNUMBER";
  public final static  String S_PatientVocation = "PATIENT_VOCATION";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocName = "LOC_NAME";
  public final static  String S_PatientMedicareid = "PATIENT_MEDICAREID";
  public final static  String S_PatientMobile = "PATIENT_MOBILE";
  public final static  String S_PatientCardid = "PATIENT_CARDID";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_PatientStation = "PATIENT_STATION";
  public final static  String S_PatientPhone = "PATIENT_PHONE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryPatientInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initPatientSex(String value){
     this.initProperty(S_PatientSex,value);
  }
  public  void setPatientSex(String value){
     this.set(S_PatientSex,value);
  }
  public  void setPatientSexNull(){
     this.set(S_PatientSex,null);
  }

  public String getPatientSex(){
       return DataType.getAsString(this.get(S_PatientSex));
  
  }
  public String getPatientSexInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientSex));
      }

  public void initStudyDoctorid(String value){
     this.initProperty(S_StudyDoctorid,value);
  }
  public  void setStudyDoctorid(String value){
     this.set(S_StudyDoctorid,value);
  }
  public  void setStudyDoctoridNull(){
     this.set(S_StudyDoctorid,null);
  }

  public String getStudyDoctorid(){
       return DataType.getAsString(this.get(S_StudyDoctorid));
  
  }
  public String getStudyDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDoctorid));
      }

  public void initStudyLocseqno(long value){
     this.initProperty(S_StudyLocseqno,new Long(value));
  }
  public  void setStudyLocseqno(long value){
     this.set(S_StudyLocseqno,new Long(value));
  }
  public  void setStudyLocseqnoNull(){
     this.set(S_StudyLocseqno,null);
  }

  public long getStudyLocseqno(){
        return DataType.getAsLong(this.get(S_StudyLocseqno));
  
  }
  public long getStudyLocseqnoInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyLocseqno));
      }

  public void initPatientStatus(String value){
     this.initProperty(S_PatientStatus,value);
  }
  public  void setPatientStatus(String value){
     this.set(S_PatientStatus,value);
  }
  public  void setPatientStatusNull(){
     this.set(S_PatientStatus,null);
  }

  public String getPatientStatus(){
       return DataType.getAsString(this.get(S_PatientStatus));
  
  }
  public String getPatientStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientStatus));
      }

  public void initPatientNamepy(String value){
     this.initProperty(S_PatientNamepy,value);
  }
  public  void setPatientNamepy(String value){
     this.set(S_PatientNamepy,value);
  }
  public  void setPatientNamepyNull(){
     this.set(S_PatientNamepy,null);
  }

  public String getPatientNamepy(){
       return DataType.getAsString(this.get(S_PatientNamepy));
  
  }
  public String getPatientNamepyInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientNamepy));
      }

  public void initModalityId(long value){
     this.initProperty(S_ModalityId,new Long(value));
  }
  public  void setModalityId(long value){
     this.set(S_ModalityId,new Long(value));
  }
  public  void setModalityIdNull(){
     this.set(S_ModalityId,null);
  }

  public long getModalityId(){
        return DataType.getAsLong(this.get(S_ModalityId));
  
  }
  public long getModalityIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ModalityId));
      }

  public void initStudylevelId(long value){
     this.initProperty(S_StudylevelId,new Long(value));
  }
  public  void setStudylevelId(long value){
     this.set(S_StudylevelId,new Long(value));
  }
  public  void setStudylevelIdNull(){
     this.set(S_StudylevelId,null);
  }

  public long getStudylevelId(){
        return DataType.getAsLong(this.get(S_StudylevelId));
  
  }
  public long getStudylevelIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudylevelId));
      }

  public void initPatientAddress(String value){
     this.initProperty(S_PatientAddress,value);
  }
  public  void setPatientAddress(String value){
     this.set(S_PatientAddress,value);
  }
  public  void setPatientAddressNull(){
     this.set(S_PatientAddress,null);
  }

  public String getPatientAddress(){
       return DataType.getAsString(this.get(S_PatientAddress));
  
  }
  public String getPatientAddressInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAddress));
      }

  public void initRoomDesc(String value){
     this.initProperty(S_RoomDesc,value);
  }
  public  void setRoomDesc(String value){
     this.set(S_RoomDesc,value);
  }
  public  void setRoomDescNull(){
     this.set(S_RoomDesc,null);
  }

  public String getRoomDesc(){
       return DataType.getAsString(this.get(S_RoomDesc));
  
  }
  public String getRoomDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RoomDesc));
      }

  public void initEquipmentId(long value){
     this.initProperty(S_EquipmentId,new Long(value));
  }
  public  void setEquipmentId(long value){
     this.set(S_EquipmentId,new Long(value));
  }
  public  void setEquipmentIdNull(){
     this.set(S_EquipmentId,null);
  }

  public long getEquipmentId(){
        return DataType.getAsLong(this.get(S_EquipmentId));
  
  }
  public long getEquipmentIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_EquipmentId));
      }

  public void initStudyDatetime(Timestamp value){
     this.initProperty(S_StudyDatetime,value);
  }
  public  void setStudyDatetime(Timestamp value){
     this.set(S_StudyDatetime,value);
  }
  public  void setStudyDatetimeNull(){
     this.set(S_StudyDatetime,null);
  }

  public Timestamp getStudyDatetime(){
        return DataType.getAsDateTime(this.get(S_StudyDatetime));
  
  }
  public Timestamp getStudyDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyDatetime));
      }

  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
      }

  public void initAidDoctorid(String value){
     this.initProperty(S_AidDoctorid,value);
  }
  public  void setAidDoctorid(String value){
     this.set(S_AidDoctorid,value);
  }
  public  void setAidDoctoridNull(){
     this.set(S_AidDoctorid,null);
  }

  public String getAidDoctorid(){
       return DataType.getAsString(this.get(S_AidDoctorid));
  
  }
  public String getAidDoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AidDoctorid));
      }

  public void initLocId(long value){
     this.initProperty(S_LocId,new Long(value));
  }
  public  void setLocId(long value){
     this.set(S_LocId,new Long(value));
  }
  public  void setLocIdNull(){
     this.set(S_LocId,null);
  }

  public long getLocId(){
        return DataType.getAsLong(this.get(S_LocId));
  
  }
  public long getLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocId));
      }

  public void initPatientGlobalid(long value){
     this.initProperty(S_PatientGlobalid,new Long(value));
  }
  public  void setPatientGlobalid(long value){
     this.set(S_PatientGlobalid,new Long(value));
  }
  public  void setPatientGlobalidNull(){
     this.set(S_PatientGlobalid,null);
  }

  public long getPatientGlobalid(){
        return DataType.getAsLong(this.get(S_PatientGlobalid));
  
  }
  public long getPatientGlobalidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_PatientGlobalid));
      }

  public void initPatientRemark(String value){
     this.initProperty(S_PatientRemark,value);
  }
  public  void setPatientRemark(String value){
     this.set(S_PatientRemark,value);
  }
  public  void setPatientRemarkNull(){
     this.set(S_PatientRemark,null);
  }

  public String getPatientRemark(){
       return DataType.getAsString(this.get(S_PatientRemark));
  
  }
  public String getPatientRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientRemark));
      }

  public void initStudyStarttime(Timestamp value){
     this.initProperty(S_StudyStarttime,value);
  }
  public  void setStudyStarttime(Timestamp value){
     this.set(S_StudyStarttime,value);
  }
  public  void setStudyStarttimeNull(){
     this.set(S_StudyStarttime,null);
  }

  public Timestamp getStudyStarttime(){
        return DataType.getAsDateTime(this.get(S_StudyStarttime));
  
  }
  public Timestamp getStudyStarttimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyStarttime));
      }

  public void initPatientAge(String value){
     this.initProperty(S_PatientAge,value);
  }
  public  void setPatientAge(String value){
     this.set(S_PatientAge,value);
  }
  public  void setPatientAgeNull(){
     this.set(S_PatientAge,null);
  }

  public String getPatientAge(){
       return DataType.getAsString(this.get(S_PatientAge));
  
  }
  public String getPatientAgeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAge));
      }

  public void initPatientNation(String value){
     this.initProperty(S_PatientNation,value);
  }
  public  void setPatientNation(String value){
     this.set(S_PatientNation,value);
  }
  public  void setPatientNationNull(){
     this.set(S_PatientNation,null);
  }

  public String getPatientNation(){
       return DataType.getAsString(this.get(S_PatientNation));
  
  }
  public String getPatientNationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientNation));
      }

  public void initStatusCode(String value){
     this.initProperty(S_StatusCode,value);
  }
  public  void setStatusCode(String value){
     this.set(S_StatusCode,value);
  }
  public  void setStatusCodeNull(){
     this.set(S_StatusCode,null);
  }

  public String getStatusCode(){
       return DataType.getAsString(this.get(S_StatusCode));
  
  }
  public String getStatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StatusCode));
      }

  public void initPatientDob(Timestamp value){
     this.initProperty(S_PatientDob,value);
  }
  public  void setPatientDob(Timestamp value){
     this.set(S_PatientDob,value);
  }
  public  void setPatientDobNull(){
     this.set(S_PatientDob,null);
  }

  public Timestamp getPatientDob(){
        return DataType.getAsDateTime(this.get(S_PatientDob));
  
  }
  public Timestamp getPatientDobInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_PatientDob));
      }

  public void initStudyEndtime(Timestamp value){
     this.initProperty(S_StudyEndtime,value);
  }
  public  void setStudyEndtime(Timestamp value){
     this.set(S_StudyEndtime,value);
  }
  public  void setStudyEndtimeNull(){
     this.set(S_StudyEndtime,null);
  }

  public Timestamp getStudyEndtime(){
        return DataType.getAsDateTime(this.get(S_StudyEndtime));
  
  }
  public Timestamp getStudyEndtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyEndtime));
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

  public void initPatientIdnumber(String value){
     this.initProperty(S_PatientIdnumber,value);
  }
  public  void setPatientIdnumber(String value){
     this.set(S_PatientIdnumber,value);
  }
  public  void setPatientIdnumberNull(){
     this.set(S_PatientIdnumber,null);
  }

  public String getPatientIdnumber(){
       return DataType.getAsString(this.get(S_PatientIdnumber));
  
  }
  public String getPatientIdnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientIdnumber));
      }

  public void initPatientVocation(String value){
     this.initProperty(S_PatientVocation,value);
  }
  public  void setPatientVocation(String value){
     this.set(S_PatientVocation,value);
  }
  public  void setPatientVocationNull(){
     this.set(S_PatientVocation,null);
  }

  public String getPatientVocation(){
       return DataType.getAsString(this.get(S_PatientVocation));
  
  }
  public String getPatientVocationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientVocation));
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

  public void initLocName(String value){
     this.initProperty(S_LocName,value);
  }
  public  void setLocName(String value){
     this.set(S_LocName,value);
  }
  public  void setLocNameNull(){
     this.set(S_LocName,null);
  }

  public String getLocName(){
       return DataType.getAsString(this.get(S_LocName));
  
  }
  public String getLocNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocName));
      }

  public void initPatientMedicareid(String value){
     this.initProperty(S_PatientMedicareid,value);
  }
  public  void setPatientMedicareid(String value){
     this.set(S_PatientMedicareid,value);
  }
  public  void setPatientMedicareidNull(){
     this.set(S_PatientMedicareid,null);
  }

  public String getPatientMedicareid(){
       return DataType.getAsString(this.get(S_PatientMedicareid));
  
  }
  public String getPatientMedicareidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientMedicareid));
      }

  public void initPatientMobile(String value){
     this.initProperty(S_PatientMobile,value);
  }
  public  void setPatientMobile(String value){
     this.set(S_PatientMobile,value);
  }
  public  void setPatientMobileNull(){
     this.set(S_PatientMobile,null);
  }

  public String getPatientMobile(){
       return DataType.getAsString(this.get(S_PatientMobile));
  
  }
  public String getPatientMobileInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientMobile));
      }

  public void initPatientCardid(String value){
     this.initProperty(S_PatientCardid,value);
  }
  public  void setPatientCardid(String value){
     this.set(S_PatientCardid,value);
  }
  public  void setPatientCardidNull(){
     this.set(S_PatientCardid,null);
  }

  public String getPatientCardid(){
       return DataType.getAsString(this.get(S_PatientCardid));
  
  }
  public String getPatientCardidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientCardid));
      }

  public void initStudyClinic(String value){
     this.initProperty(S_StudyClinic,value);
  }
  public  void setStudyClinic(String value){
     this.set(S_StudyClinic,value);
  }
  public  void setStudyClinicNull(){
     this.set(S_StudyClinic,null);
  }

  public String getStudyClinic(){
       return DataType.getAsString(this.get(S_StudyClinic));
  
  }
  public String getStudyClinicInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyClinic));
      }

  public void initStudystatusCode(String value){
     this.initProperty(S_StudystatusCode,value);
  }
  public  void setStudystatusCode(String value){
     this.set(S_StudystatusCode,value);
  }
  public  void setStudystatusCodeNull(){
     this.set(S_StudystatusCode,null);
  }

  public String getStudystatusCode(){
       return DataType.getAsString(this.get(S_StudystatusCode));
  
  }
  public String getStudystatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudystatusCode));
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

  public void initEquipmentDesc(String value){
     this.initProperty(S_EquipmentDesc,value);
  }
  public  void setEquipmentDesc(String value){
     this.set(S_EquipmentDesc,value);
  }
  public  void setEquipmentDescNull(){
     this.set(S_EquipmentDesc,null);
  }

  public String getEquipmentDesc(){
       return DataType.getAsString(this.get(S_EquipmentDesc));
  
  }
  public String getEquipmentDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_EquipmentDesc));
      }

  public void initPatienttypeCode(String value){
     this.initProperty(S_PatienttypeCode,value);
  }
  public  void setPatienttypeCode(String value){
     this.set(S_PatienttypeCode,value);
  }
  public  void setPatienttypeCodeNull(){
     this.set(S_PatienttypeCode,null);
  }

  public String getPatienttypeCode(){
       return DataType.getAsString(this.get(S_PatienttypeCode));
  
  }
  public String getPatienttypeCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatienttypeCode));
      }

  public void initPatientStation(String value){
     this.initProperty(S_PatientStation,value);
  }
  public  void setPatientStation(String value){
     this.set(S_PatientStation,value);
  }
  public  void setPatientStationNull(){
     this.set(S_PatientStation,null);
  }

  public String getPatientStation(){
       return DataType.getAsString(this.get(S_PatientStation));
  
  }
  public String getPatientStationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientStation));
      }

  public void initPatientPhone(String value){
     this.initProperty(S_PatientPhone,value);
  }
  public  void setPatientPhone(String value){
     this.set(S_PatientPhone,value);
  }
  public  void setPatientPhoneNull(){
     this.set(S_PatientPhone,null);
  }

  public String getPatientPhone(){
       return DataType.getAsString(this.get(S_PatientPhone));
  
  }
  public String getPatientPhoneInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientPhone));
      }


 
 }

