package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisStudyInfoBean extends DataContainer implements DataContainerInterface,IAisStudyInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisStudyInfo";



  public final static  String S_StudyUid = "STUDY_UID";
  public final static  String S_StudyDoctorid = "STUDY_DOCTORID";
  public final static  String S_StudyWard = "STUDY_WARD";
  public final static  String S_StudyIsbed = "STUDY_ISBED";
  public final static  String S_StudylevelId = "STUDYLEVEL_ID";
  public final static  String S_StudyEpsodeid = "STUDY_EPSODEID";
  public final static  String S_StudyConsultorg = "STUDY_CONSULTORG";
  public final static  String S_PatientWeight = "PATIENT_WEIGHT";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_AidDoctorid = "AID_DOCTORID";
  public final static  String S_PaymenttypeId = "PAYMENTTYPE_ID";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_StudyStarttime = "STUDY_STARTTIME";
  public final static  String S_PatientHeight = "PATIENT_HEIGHT";
  public final static  String S_StudyCreatetime = "STUDY_CREATETIME";
  public final static  String S_StudyFilmcount = "STUDY_FILMCOUNT";
  public final static  String S_StudyEnitemdesc = "STUDY_ENITEMDESC";
  public final static  String S_StudyConsultation = "STUDY_CONSULTATION";
  public final static  String S_StudyImagecount = "STUDY_IMAGECOUNT";
  public final static  String S_StudyAccnumber = "STUDY_ACCNUMBER";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_StudyClinic = "STUDY_CLINIC";
  public final static  String S_StudyOperationtime = "STUDY_OPERATIONTIME";
  public final static  String S_StudyCheckoutfilm = "STUDY_CHECKOUTFILM";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_HisZydjxh = "HIS_ZYDJXH";
  public final static  String S_StudyRemark = "STUDY_REMARK";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_StudyTotalfee = "STUDY_TOTALFEE";
  public final static  String S_StudyLocseqno = "STUDY_LOCSEQNO";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_StudyAppdoc = "STUDY_APPDOC";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_StudyExposurecount = "STUDY_EXPOSURECOUNT";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_StudyItemdesc = "STUDY_ITEMDESC";
  public final static  String S_StudyEndtime = "STUDY_ENDTIME";
  public final static  String S_StudyApplocid = "STUDY_APPLOCID";
  public final static  String S_StudyAim = "STUDY_AIM";
  public final static  String S_StudyConsultloc = "STUDY_CONSULTLOC";
  public final static  String S_StudyConsultstarttime = "STUDY_CONSULTSTARTTIME";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_StudyHisappid = "STUDY_HISAPPID";
  public final static  String S_IsUrgent = "IS_URGENT";
  public final static  String S_StudyFirstvisit = "STUDY_FIRSTVISIT";
  public final static  String S_StudyConsulttype = "STUDY_CONSULTTYPE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_StudyConsultdoctorid = "STUDY_CONSULTDOCTORID";
  public final static  String S_StudyConsultendtime = "STUDY_CONSULTENDTIME";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyHaveimage = "STUDY_HAVEIMAGE";
  public final static  String S_StudyId = "STUDY_ID";
  public final static  String S_StudyOperationid = "STUDY_OPERATIONID";
  public final static  String S_StudyFilmprinted = "STUDY_FILMPRINTED";
  public final static  String S_PatienttypeCode = "PATIENTTYPE_CODE";
  public final static  String S_StudyHavereport = "STUDY_HAVEREPORT";
  public final static  String S_StudyBedno = "STUDY_BEDNO";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyUid(String value){
     this.initProperty(S_StudyUid,value);
  }
  public  void setStudyUid(String value){
     this.set(S_StudyUid,value);
  }
  public  void setStudyUidNull(){
     this.set(S_StudyUid,null);
  }

  public String getStudyUid(){
       return DataType.getAsString(this.get(S_StudyUid));
  
  }
  public String getStudyUidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyUid));
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

  public void initStudyWard(String value){
     this.initProperty(S_StudyWard,value);
  }
  public  void setStudyWard(String value){
     this.set(S_StudyWard,value);
  }
  public  void setStudyWardNull(){
     this.set(S_StudyWard,null);
  }

  public String getStudyWard(){
       return DataType.getAsString(this.get(S_StudyWard));
  
  }
  public String getStudyWardInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyWard));
      }

  public void initStudyIsbed(int value){
     this.initProperty(S_StudyIsbed,new Integer(value));
  }
  public  void setStudyIsbed(int value){
     this.set(S_StudyIsbed,new Integer(value));
  }
  public  void setStudyIsbedNull(){
     this.set(S_StudyIsbed,null);
  }

  public int getStudyIsbed(){
        return DataType.getAsInt(this.get(S_StudyIsbed));
  
  }
  public int getStudyIsbedInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyIsbed));
      }

  public void initStudylevelId(int value){
     this.initProperty(S_StudylevelId,new Integer(value));
  }
  public  void setStudylevelId(int value){
     this.set(S_StudylevelId,new Integer(value));
  }
  public  void setStudylevelIdNull(){
     this.set(S_StudylevelId,null);
  }

  public int getStudylevelId(){
        return DataType.getAsInt(this.get(S_StudylevelId));
  
  }
  public int getStudylevelIdInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudylevelId));
      }

  public void initStudyEpsodeid(String value){
     this.initProperty(S_StudyEpsodeid,value);
  }
  public  void setStudyEpsodeid(String value){
     this.set(S_StudyEpsodeid,value);
  }
  public  void setStudyEpsodeidNull(){
     this.set(S_StudyEpsodeid,null);
  }

  public String getStudyEpsodeid(){
       return DataType.getAsString(this.get(S_StudyEpsodeid));
  
  }
  public String getStudyEpsodeidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyEpsodeid));
      }

  public void initStudyConsultorg(String value){
     this.initProperty(S_StudyConsultorg,value);
  }
  public  void setStudyConsultorg(String value){
     this.set(S_StudyConsultorg,value);
  }
  public  void setStudyConsultorgNull(){
     this.set(S_StudyConsultorg,null);
  }

  public String getStudyConsultorg(){
       return DataType.getAsString(this.get(S_StudyConsultorg));
  
  }
  public String getStudyConsultorgInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultorg));
      }

  public void initPatientWeight(String value){
     this.initProperty(S_PatientWeight,value);
  }
  public  void setPatientWeight(String value){
     this.set(S_PatientWeight,value);
  }
  public  void setPatientWeightNull(){
     this.set(S_PatientWeight,null);
  }

  public String getPatientWeight(){
       return DataType.getAsString(this.get(S_PatientWeight));
  
  }
  public String getPatientWeightInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientWeight));
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

  public void initPaymenttypeId(int value){
     this.initProperty(S_PaymenttypeId,new Integer(value));
  }
  public  void setPaymenttypeId(int value){
     this.set(S_PaymenttypeId,new Integer(value));
  }
  public  void setPaymenttypeIdNull(){
     this.set(S_PaymenttypeId,null);
  }

  public int getPaymenttypeId(){
        return DataType.getAsInt(this.get(S_PaymenttypeId));
  
  }
  public int getPaymenttypeIdInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_PaymenttypeId));
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

  public void initPatientHeight(String value){
     this.initProperty(S_PatientHeight,value);
  }
  public  void setPatientHeight(String value){
     this.set(S_PatientHeight,value);
  }
  public  void setPatientHeightNull(){
     this.set(S_PatientHeight,null);
  }

  public String getPatientHeight(){
       return DataType.getAsString(this.get(S_PatientHeight));
  
  }
  public String getPatientHeightInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientHeight));
      }

  public void initStudyCreatetime(Timestamp value){
     this.initProperty(S_StudyCreatetime,value);
  }
  public  void setStudyCreatetime(Timestamp value){
     this.set(S_StudyCreatetime,value);
  }
  public  void setStudyCreatetimeNull(){
     this.set(S_StudyCreatetime,null);
  }

  public Timestamp getStudyCreatetime(){
        return DataType.getAsDateTime(this.get(S_StudyCreatetime));
  
  }
  public Timestamp getStudyCreatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyCreatetime));
      }

  public void initStudyFilmcount(long value){
     this.initProperty(S_StudyFilmcount,new Long(value));
  }
  public  void setStudyFilmcount(long value){
     this.set(S_StudyFilmcount,new Long(value));
  }
  public  void setStudyFilmcountNull(){
     this.set(S_StudyFilmcount,null);
  }

  public long getStudyFilmcount(){
        return DataType.getAsLong(this.get(S_StudyFilmcount));
  
  }
  public long getStudyFilmcountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyFilmcount));
      }

  public void initStudyEnitemdesc(String value){
     this.initProperty(S_StudyEnitemdesc,value);
  }
  public  void setStudyEnitemdesc(String value){
     this.set(S_StudyEnitemdesc,value);
  }
  public  void setStudyEnitemdescNull(){
     this.set(S_StudyEnitemdesc,null);
  }

  public String getStudyEnitemdesc(){
       return DataType.getAsString(this.get(S_StudyEnitemdesc));
  
  }
  public String getStudyEnitemdescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyEnitemdesc));
      }

  public void initStudyConsultation(String value){
     this.initProperty(S_StudyConsultation,value);
  }
  public  void setStudyConsultation(String value){
     this.set(S_StudyConsultation,value);
  }
  public  void setStudyConsultationNull(){
     this.set(S_StudyConsultation,null);
  }

  public String getStudyConsultation(){
       return DataType.getAsString(this.get(S_StudyConsultation));
  
  }
  public String getStudyConsultationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultation));
      }

  public void initStudyImagecount(long value){
     this.initProperty(S_StudyImagecount,new Long(value));
  }
  public  void setStudyImagecount(long value){
     this.set(S_StudyImagecount,new Long(value));
  }
  public  void setStudyImagecountNull(){
     this.set(S_StudyImagecount,null);
  }

  public long getStudyImagecount(){
        return DataType.getAsLong(this.get(S_StudyImagecount));
  
  }
  public long getStudyImagecountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyImagecount));
      }

  public void initStudyAccnumber(String value){
     this.initProperty(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumber(String value){
     this.set(S_StudyAccnumber,value);
  }
  public  void setStudyAccnumberNull(){
     this.set(S_StudyAccnumber,null);
  }

  public String getStudyAccnumber(){
       return DataType.getAsString(this.get(S_StudyAccnumber));
  
  }
  public String getStudyAccnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAccnumber));
      }

  public void initPatientOutpatientid(String value){
     this.initProperty(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientid(String value){
     this.set(S_PatientOutpatientid,value);
  }
  public  void setPatientOutpatientidNull(){
     this.set(S_PatientOutpatientid,null);
  }

  public String getPatientOutpatientid(){
       return DataType.getAsString(this.get(S_PatientOutpatientid));
  
  }
  public String getPatientOutpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientOutpatientid));
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

  public void initStudyOperationtime(Timestamp value){
     this.initProperty(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtime(Timestamp value){
     this.set(S_StudyOperationtime,value);
  }
  public  void setStudyOperationtimeNull(){
     this.set(S_StudyOperationtime,null);
  }

  public Timestamp getStudyOperationtime(){
        return DataType.getAsDateTime(this.get(S_StudyOperationtime));
  
  }
  public Timestamp getStudyOperationtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyOperationtime));
      }

  public void initStudyCheckoutfilm(int value){
     this.initProperty(S_StudyCheckoutfilm,new Integer(value));
  }
  public  void setStudyCheckoutfilm(int value){
     this.set(S_StudyCheckoutfilm,new Integer(value));
  }
  public  void setStudyCheckoutfilmNull(){
     this.set(S_StudyCheckoutfilm,null);
  }

  public int getStudyCheckoutfilm(){
        return DataType.getAsInt(this.get(S_StudyCheckoutfilm));
  
  }
  public int getStudyCheckoutfilmInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyCheckoutfilm));
      }

  public void initStudyAppdate(Timestamp value){
     this.initProperty(S_StudyAppdate,value);
  }
  public  void setStudyAppdate(Timestamp value){
     this.set(S_StudyAppdate,value);
  }
  public  void setStudyAppdateNull(){
     this.set(S_StudyAppdate,null);
  }

  public Timestamp getStudyAppdate(){
        return DataType.getAsDateTime(this.get(S_StudyAppdate));
  
  }
  public Timestamp getStudyAppdateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyAppdate));
      }

  public void initHisZydjxh(String value){
     this.initProperty(S_HisZydjxh,value);
  }
  public  void setHisZydjxh(String value){
     this.set(S_HisZydjxh,value);
  }
  public  void setHisZydjxhNull(){
     this.set(S_HisZydjxh,null);
  }

  public String getHisZydjxh(){
       return DataType.getAsString(this.get(S_HisZydjxh));
  
  }
  public String getHisZydjxhInitialValue(){
        return DataType.getAsString(this.getOldObj(S_HisZydjxh));
      }

  public void initStudyRemark(String value){
     this.initProperty(S_StudyRemark,value);
  }
  public  void setStudyRemark(String value){
     this.set(S_StudyRemark,value);
  }
  public  void setStudyRemarkNull(){
     this.set(S_StudyRemark,null);
  }

  public String getStudyRemark(){
       return DataType.getAsString(this.get(S_StudyRemark));
  
  }
  public String getStudyRemarkInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyRemark));
      }

  public void initPatientInpatientid(String value){
     this.initProperty(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientid(String value){
     this.set(S_PatientInpatientid,value);
  }
  public  void setPatientInpatientidNull(){
     this.set(S_PatientInpatientid,null);
  }

  public String getPatientInpatientid(){
       return DataType.getAsString(this.get(S_PatientInpatientid));
  
  }
  public String getPatientInpatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientInpatientid));
      }

  public void initStudyTotalfee(float value){
     this.initProperty(S_StudyTotalfee,new Float(value));
  }
  public  void setStudyTotalfee(float value){
     this.set(S_StudyTotalfee,new Float(value));
  }
  public  void setStudyTotalfeeNull(){
     this.set(S_StudyTotalfee,null);
  }

  public float getStudyTotalfee(){
        return DataType.getAsFloat(this.get(S_StudyTotalfee));
  
  }
  public float getStudyTotalfeeInitialValue(){
        return DataType.getAsFloat(this.getOldObj(S_StudyTotalfee));
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

  public void initStudyType(int value){
     this.initProperty(S_StudyType,new Integer(value));
  }
  public  void setStudyType(int value){
     this.set(S_StudyType,new Integer(value));
  }
  public  void setStudyTypeNull(){
     this.set(S_StudyType,null);
  }

  public int getStudyType(){
        return DataType.getAsInt(this.get(S_StudyType));
  
  }
  public int getStudyTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyType));
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

  public void initStudyAppdoc(String value){
     this.initProperty(S_StudyAppdoc,value);
  }
  public  void setStudyAppdoc(String value){
     this.set(S_StudyAppdoc,value);
  }
  public  void setStudyAppdocNull(){
     this.set(S_StudyAppdoc,null);
  }

  public String getStudyAppdoc(){
       return DataType.getAsString(this.get(S_StudyAppdoc));
  
  }
  public String getStudyAppdocInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAppdoc));
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

  public void initStudyExposurecount(long value){
     this.initProperty(S_StudyExposurecount,new Long(value));
  }
  public  void setStudyExposurecount(long value){
     this.set(S_StudyExposurecount,new Long(value));
  }
  public  void setStudyExposurecountNull(){
     this.set(S_StudyExposurecount,null);
  }

  public long getStudyExposurecount(){
        return DataType.getAsLong(this.get(S_StudyExposurecount));
  
  }
  public long getStudyExposurecountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyExposurecount));
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

  public void initStudyItemdesc(String value){
     this.initProperty(S_StudyItemdesc,value);
  }
  public  void setStudyItemdesc(String value){
     this.set(S_StudyItemdesc,value);
  }
  public  void setStudyItemdescNull(){
     this.set(S_StudyItemdesc,null);
  }

  public String getStudyItemdesc(){
       return DataType.getAsString(this.get(S_StudyItemdesc));
  
  }
  public String getStudyItemdescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyItemdesc));
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

  public void initStudyApplocid(long value){
     this.initProperty(S_StudyApplocid,new Long(value));
  }
  public  void setStudyApplocid(long value){
     this.set(S_StudyApplocid,new Long(value));
  }
  public  void setStudyApplocidNull(){
     this.set(S_StudyApplocid,null);
  }

  public long getStudyApplocid(){
        return DataType.getAsLong(this.get(S_StudyApplocid));
  
  }
  public long getStudyApplocidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyApplocid));
      }

  public void initStudyAim(String value){
     this.initProperty(S_StudyAim,value);
  }
  public  void setStudyAim(String value){
     this.set(S_StudyAim,value);
  }
  public  void setStudyAimNull(){
     this.set(S_StudyAim,null);
  }

  public String getStudyAim(){
       return DataType.getAsString(this.get(S_StudyAim));
  
  }
  public String getStudyAimInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAim));
      }

  public void initStudyConsultloc(long value){
     this.initProperty(S_StudyConsultloc,new Long(value));
  }
  public  void setStudyConsultloc(long value){
     this.set(S_StudyConsultloc,new Long(value));
  }
  public  void setStudyConsultlocNull(){
     this.set(S_StudyConsultloc,null);
  }

  public long getStudyConsultloc(){
        return DataType.getAsLong(this.get(S_StudyConsultloc));
  
  }
  public long getStudyConsultlocInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyConsultloc));
      }

  public void initStudyConsultstarttime(Timestamp value){
     this.initProperty(S_StudyConsultstarttime,value);
  }
  public  void setStudyConsultstarttime(Timestamp value){
     this.set(S_StudyConsultstarttime,value);
  }
  public  void setStudyConsultstarttimeNull(){
     this.set(S_StudyConsultstarttime,null);
  }

  public Timestamp getStudyConsultstarttime(){
        return DataType.getAsDateTime(this.get(S_StudyConsultstarttime));
  
  }
  public Timestamp getStudyConsultstarttimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyConsultstarttime));
      }

  public void initRoomId(long value){
     this.initProperty(S_RoomId,new Long(value));
  }
  public  void setRoomId(long value){
     this.set(S_RoomId,new Long(value));
  }
  public  void setRoomIdNull(){
     this.set(S_RoomId,null);
  }

  public long getRoomId(){
        return DataType.getAsLong(this.get(S_RoomId));
  
  }
  public long getRoomIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoomId));
      }

  public void initStudyHisappid(String value){
     this.initProperty(S_StudyHisappid,value);
  }
  public  void setStudyHisappid(String value){
     this.set(S_StudyHisappid,value);
  }
  public  void setStudyHisappidNull(){
     this.set(S_StudyHisappid,null);
  }

  public String getStudyHisappid(){
       return DataType.getAsString(this.get(S_StudyHisappid));
  
  }
  public String getStudyHisappidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyHisappid));
      }

  public void initIsUrgent(int value){
     this.initProperty(S_IsUrgent,new Integer(value));
  }
  public  void setIsUrgent(int value){
     this.set(S_IsUrgent,new Integer(value));
  }
  public  void setIsUrgentNull(){
     this.set(S_IsUrgent,null);
  }

  public int getIsUrgent(){
        return DataType.getAsInt(this.get(S_IsUrgent));
  
  }
  public int getIsUrgentInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_IsUrgent));
      }

  public void initStudyFirstvisit(int value){
     this.initProperty(S_StudyFirstvisit,new Integer(value));
  }
  public  void setStudyFirstvisit(int value){
     this.set(S_StudyFirstvisit,new Integer(value));
  }
  public  void setStudyFirstvisitNull(){
     this.set(S_StudyFirstvisit,null);
  }

  public int getStudyFirstvisit(){
        return DataType.getAsInt(this.get(S_StudyFirstvisit));
  
  }
  public int getStudyFirstvisitInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyFirstvisit));
      }

  public void initStudyConsulttype(String value){
     this.initProperty(S_StudyConsulttype,value);
  }
  public  void setStudyConsulttype(String value){
     this.set(S_StudyConsulttype,value);
  }
  public  void setStudyConsulttypeNull(){
     this.set(S_StudyConsulttype,null);
  }

  public String getStudyConsulttype(){
       return DataType.getAsString(this.get(S_StudyConsulttype));
  
  }
  public String getStudyConsulttypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsulttype));
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

  public void initStudyConsultdoctorid(String value){
     this.initProperty(S_StudyConsultdoctorid,value);
  }
  public  void setStudyConsultdoctorid(String value){
     this.set(S_StudyConsultdoctorid,value);
  }
  public  void setStudyConsultdoctoridNull(){
     this.set(S_StudyConsultdoctorid,null);
  }

  public String getStudyConsultdoctorid(){
       return DataType.getAsString(this.get(S_StudyConsultdoctorid));
  
  }
  public String getStudyConsultdoctoridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultdoctorid));
      }

  public void initStudyConsultendtime(Timestamp value){
     this.initProperty(S_StudyConsultendtime,value);
  }
  public  void setStudyConsultendtime(Timestamp value){
     this.set(S_StudyConsultendtime,value);
  }
  public  void setStudyConsultendtimeNull(){
     this.set(S_StudyConsultendtime,null);
  }

  public Timestamp getStudyConsultendtime(){
        return DataType.getAsDateTime(this.get(S_StudyConsultendtime));
  
  }
  public Timestamp getStudyConsultendtimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyConsultendtime));
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

  public void initStudyHaveimage(int value){
     this.initProperty(S_StudyHaveimage,new Integer(value));
  }
  public  void setStudyHaveimage(int value){
     this.set(S_StudyHaveimage,new Integer(value));
  }
  public  void setStudyHaveimageNull(){
     this.set(S_StudyHaveimage,null);
  }

  public int getStudyHaveimage(){
        return DataType.getAsInt(this.get(S_StudyHaveimage));
  
  }
  public int getStudyHaveimageInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyHaveimage));
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

  public void initStudyOperationid(String value){
     this.initProperty(S_StudyOperationid,value);
  }
  public  void setStudyOperationid(String value){
     this.set(S_StudyOperationid,value);
  }
  public  void setStudyOperationidNull(){
     this.set(S_StudyOperationid,null);
  }

  public String getStudyOperationid(){
       return DataType.getAsString(this.get(S_StudyOperationid));
  
  }
  public String getStudyOperationidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyOperationid));
      }

  public void initStudyFilmprinted(int value){
     this.initProperty(S_StudyFilmprinted,new Integer(value));
  }
  public  void setStudyFilmprinted(int value){
     this.set(S_StudyFilmprinted,new Integer(value));
  }
  public  void setStudyFilmprintedNull(){
     this.set(S_StudyFilmprinted,null);
  }

  public int getStudyFilmprinted(){
        return DataType.getAsInt(this.get(S_StudyFilmprinted));
  
  }
  public int getStudyFilmprintedInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyFilmprinted));
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

  public void initStudyHavereport(int value){
     this.initProperty(S_StudyHavereport,new Integer(value));
  }
  public  void setStudyHavereport(int value){
     this.set(S_StudyHavereport,new Integer(value));
  }
  public  void setStudyHavereportNull(){
     this.set(S_StudyHavereport,null);
  }

  public int getStudyHavereport(){
        return DataType.getAsInt(this.get(S_StudyHavereport));
  
  }
  public int getStudyHavereportInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_StudyHavereport));
      }

  public void initStudyBedno(String value){
     this.initProperty(S_StudyBedno,value);
  }
  public  void setStudyBedno(String value){
     this.set(S_StudyBedno,value);
  }
  public  void setStudyBednoNull(){
     this.set(S_StudyBedno,null);
  }

  public String getStudyBedno(){
       return DataType.getAsString(this.get(S_StudyBedno));
  
  }
  public String getStudyBednoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyBedno));
      }


 
 }

