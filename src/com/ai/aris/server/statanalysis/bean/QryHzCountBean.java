package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryHzCountBean extends DataContainer implements DataContainerInterface,IQryHzCountValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryHzCount";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_CheckDoc = "CHECK_DOC";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_StudyConsultlocname = "STUDY_CONSULTLOCNAME";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_Id = "ID";
  public final static  String S_StudyAppdate = "STUDY_APPDATE";
  public final static  String S_HzResultcount = "HZ_RESULTCOUNT";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_StudyConsultorgname = "STUDY_CONSULTORGNAME";
  public final static  String S_CheckItem = "CHECK_ITEM";
  public final static  String S_ReportDoc = "REPORT_DOC";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_HzCount = "HZ_COUNT";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryHzCountBean() throws AIException{
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

  public void initLocCode(String value){
     this.initProperty(S_LocCode,value);
  }
  public  void setLocCode(String value){
     this.set(S_LocCode,value);
  }
  public  void setLocCodeNull(){
     this.set(S_LocCode,null);
  }

  public String getLocCode(){
       return DataType.getAsString(this.get(S_LocCode));
  
  }
  public String getLocCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocCode));
      }

  public void initCityCode(String value){
     this.initProperty(S_CityCode,value);
  }
  public  void setCityCode(String value){
     this.set(S_CityCode,value);
  }
  public  void setCityCodeNull(){
     this.set(S_CityCode,null);
  }

  public String getCityCode(){
       return DataType.getAsString(this.get(S_CityCode));
  
  }
  public String getCityCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CityCode));
      }

  public void initCountyCode(String value){
     this.initProperty(S_CountyCode,value);
  }
  public  void setCountyCode(String value){
     this.set(S_CountyCode,value);
  }
  public  void setCountyCodeNull(){
     this.set(S_CountyCode,null);
  }

  public String getCountyCode(){
       return DataType.getAsString(this.get(S_CountyCode));
  
  }
  public String getCountyCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CountyCode));
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

  public void initCheckDoc(String value){
     this.initProperty(S_CheckDoc,value);
  }
  public  void setCheckDoc(String value){
     this.set(S_CheckDoc,value);
  }
  public  void setCheckDocNull(){
     this.set(S_CheckDoc,null);
  }

  public String getCheckDoc(){
       return DataType.getAsString(this.get(S_CheckDoc));
  
  }
  public String getCheckDocInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CheckDoc));
      }

  public void initOrgName(String value){
     this.initProperty(S_OrgName,value);
  }
  public  void setOrgName(String value){
     this.set(S_OrgName,value);
  }
  public  void setOrgNameNull(){
     this.set(S_OrgName,null);
  }

  public String getOrgName(){
       return DataType.getAsString(this.get(S_OrgName));
  
  }
  public String getOrgNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgName));
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

  public void initCheckDate(Timestamp value){
     this.initProperty(S_CheckDate,value);
  }
  public  void setCheckDate(Timestamp value){
     this.set(S_CheckDate,value);
  }
  public  void setCheckDateNull(){
     this.set(S_CheckDate,null);
  }

  public Timestamp getCheckDate(){
        return DataType.getAsDateTime(this.get(S_CheckDate));
  
  }
  public Timestamp getCheckDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CheckDate));
      }

  public void initStudyConsultlocname(String value){
     this.initProperty(S_StudyConsultlocname,value);
  }
  public  void setStudyConsultlocname(String value){
     this.set(S_StudyConsultlocname,value);
  }
  public  void setStudyConsultlocnameNull(){
     this.set(S_StudyConsultlocname,null);
  }

  public String getStudyConsultlocname(){
       return DataType.getAsString(this.get(S_StudyConsultlocname));
  
  }
  public String getStudyConsultlocnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultlocname));
      }

  public void initCountyDesc(String value){
     this.initProperty(S_CountyDesc,value);
  }
  public  void setCountyDesc(String value){
     this.set(S_CountyDesc,value);
  }
  public  void setCountyDescNull(){
     this.set(S_CountyDesc,null);
  }

  public String getCountyDesc(){
       return DataType.getAsString(this.get(S_CountyDesc));
  
  }
  public String getCountyDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CountyDesc));
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

  public void initCityDesc(String value){
     this.initProperty(S_CityDesc,value);
  }
  public  void setCityDesc(String value){
     this.set(S_CityDesc,value);
  }
  public  void setCityDescNull(){
     this.set(S_CityDesc,null);
  }

  public String getCityDesc(){
       return DataType.getAsString(this.get(S_CityDesc));
  
  }
  public String getCityDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CityDesc));
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

  public void initId(long value){
     this.initProperty(S_Id,new Long(value));
  }
  public  void setId(long value){
     this.set(S_Id,new Long(value));
  }
  public  void setIdNull(){
     this.set(S_Id,null);
  }

  public long getId(){
        return DataType.getAsLong(this.get(S_Id));
  
  }
  public long getIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Id));
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

  public void initHzResultcount(long value){
     this.initProperty(S_HzResultcount,new Long(value));
  }
  public  void setHzResultcount(long value){
     this.set(S_HzResultcount,new Long(value));
  }
  public  void setHzResultcountNull(){
     this.set(S_HzResultcount,null);
  }

  public long getHzResultcount(){
        return DataType.getAsLong(this.get(S_HzResultcount));
  
  }
  public long getHzResultcountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_HzResultcount));
      }

  public void initCareprovName(String value){
     this.initProperty(S_CareprovName,value);
  }
  public  void setCareprovName(String value){
     this.set(S_CareprovName,value);
  }
  public  void setCareprovNameNull(){
     this.set(S_CareprovName,null);
  }

  public String getCareprovName(){
       return DataType.getAsString(this.get(S_CareprovName));
  
  }
  public String getCareprovNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CareprovName));
      }

  public void initStudyConsultorgname(String value){
     this.initProperty(S_StudyConsultorgname,value);
  }
  public  void setStudyConsultorgname(String value){
     this.set(S_StudyConsultorgname,value);
  }
  public  void setStudyConsultorgnameNull(){
     this.set(S_StudyConsultorgname,null);
  }

  public String getStudyConsultorgname(){
       return DataType.getAsString(this.get(S_StudyConsultorgname));
  
  }
  public String getStudyConsultorgnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyConsultorgname));
      }

  public void initCheckItem(String value){
     this.initProperty(S_CheckItem,value);
  }
  public  void setCheckItem(String value){
     this.set(S_CheckItem,value);
  }
  public  void setCheckItemNull(){
     this.set(S_CheckItem,null);
  }

  public String getCheckItem(){
       return DataType.getAsString(this.get(S_CheckItem));
  
  }
  public String getCheckItemInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CheckItem));
      }

  public void initReportDoc(String value){
     this.initProperty(S_ReportDoc,value);
  }
  public  void setReportDoc(String value){
     this.set(S_ReportDoc,value);
  }
  public  void setReportDocNull(){
     this.set(S_ReportDoc,null);
  }

  public String getReportDoc(){
       return DataType.getAsString(this.get(S_ReportDoc));
  
  }
  public String getReportDocInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReportDoc));
      }

  public void initReportDatetime(Timestamp value){
     this.initProperty(S_ReportDatetime,value);
  }
  public  void setReportDatetime(Timestamp value){
     this.set(S_ReportDatetime,value);
  }
  public  void setReportDatetimeNull(){
     this.set(S_ReportDatetime,null);
  }

  public Timestamp getReportDatetime(){
        return DataType.getAsDateTime(this.get(S_ReportDatetime));
  
  }
  public Timestamp getReportDatetimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ReportDatetime));
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

  public void initHzCount(long value){
     this.initProperty(S_HzCount,new Long(value));
  }
  public  void setHzCount(long value){
     this.set(S_HzCount,new Long(value));
  }
  public  void setHzCountNull(){
     this.set(S_HzCount,null);
  }

  public long getHzCount(){
        return DataType.getAsLong(this.get(S_HzCount));
  
  }
  public long getHzCountInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_HzCount));
      }


 
 }

