package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryPatientIsHisBean extends DataContainer implements DataContainerInterface,IQryPatientIsHisValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryPatientIsHis";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_PatientInpatientid = "PATIENT_INPATIENTID";
  public final static  String S_PatientStatus = "PATIENT_STATUS";
  public final static  String S_PatientNamepy = "PATIENT_NAMEPY";
  public final static  String S_PatientAddress = "PATIENT_ADDRESS";
  public final static  String S_PatientIdnumber = "PATIENT_IDNUMBER";
  public final static  String S_PatientVocation = "PATIENT_VOCATION";
  public final static  String S_PatientMedicareid = "PATIENT_MEDICAREID";
  public final static  String S_PatientMobile = "PATIENT_MOBILE";
  public final static  String S_PatientCardid = "PATIENT_CARDID";
  public final static  String S_PatientOutpatientid = "PATIENT_OUTPATIENTID";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_PatientStation = "PATIENT_STATION";
  public final static  String S_PatientRemark = "PATIENT_REMARK";
  public final static  String S_PatientGlobalid = "PATIENT_GLOBALID";
  public final static  String S_PatientPhone = "PATIENT_PHONE";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_PatientNation = "PATIENT_NATION";
  public final static  String S_PatientDob = "PATIENT_DOB";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryPatientIsHisBean() throws AIException{
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


 
 }

