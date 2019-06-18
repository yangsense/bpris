package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class WorkListCountBean extends DataContainer implements DataContainerInterface,IWorkListCountValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.WorkListCount";



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

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public WorkListCountBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initBodyitem(String value){
     this.initProperty(S_Bodyitem,value);
  }
  public  void setBodyitem(String value){
     this.set(S_Bodyitem,value);
  }
  public  void setBodyitemNull(){
     this.set(S_Bodyitem,null);
  }

  public String getBodyitem(){
       return DataType.getAsString(this.get(S_Bodyitem));
  
  }
  public String getBodyitemInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Bodyitem));
      }

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
      }

  public void initCStudyOperationtime(String value){
     this.initProperty(S_CStudyOperationtime,value);
  }
  public  void setCStudyOperationtime(String value){
     this.set(S_CStudyOperationtime,value);
  }
  public  void setCStudyOperationtimeNull(){
     this.set(S_CStudyOperationtime,null);
  }

  public String getCStudyOperationtime(){
       return DataType.getAsString(this.get(S_CStudyOperationtime));
  
  }
  public String getCStudyOperationtimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CStudyOperationtime));
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

  public void initStudyAppdocname(String value){
     this.initProperty(S_StudyAppdocname,value);
  }
  public  void setStudyAppdocname(String value){
     this.set(S_StudyAppdocname,value);
  }
  public  void setStudyAppdocnameNull(){
     this.set(S_StudyAppdocname,null);
  }

  public String getStudyAppdocname(){
       return DataType.getAsString(this.get(S_StudyAppdocname));
  
  }
  public String getStudyAppdocnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyAppdocname));
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


 
 }

