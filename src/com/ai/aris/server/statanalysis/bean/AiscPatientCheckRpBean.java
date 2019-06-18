package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class AiscPatientCheckRpBean extends DataContainer implements DataContainerInterface,IAiscPatientCheckRpValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.AiscPatientCheckRp";



  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_CheckDate = "CHECK_DATE";
  public final static  String S_RegistNo = "REGIST_NO";
  public final static  String S_CheckItem = "CHECK_ITEM";
  public final static  String S_CheckDoc = "CHECK_DOC";
  public final static  String S_ImageNum = "IMAGE_NUM";
  public final static  String S_OrgDesc = "ORG_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_ReportDoc = "REPORT_DOC";
  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_Id = "ID";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_PatientAge = "PATIENT_AGE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscPatientCheckRpBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initCheckDate(String value){
     this.initProperty(S_CheckDate,value);
  }
  public  void setCheckDate(String value){
     this.set(S_CheckDate,value);
  }
  public  void setCheckDateNull(){
     this.set(S_CheckDate,null);
  }

  public String getCheckDate(){
       return DataType.getAsString(this.get(S_CheckDate));
  
  }
  public String getCheckDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CheckDate));
      }

  public void initRegistNo(String value){
     this.initProperty(S_RegistNo,value);
  }
  public  void setRegistNo(String value){
     this.set(S_RegistNo,value);
  }
  public  void setRegistNoNull(){
     this.set(S_RegistNo,null);
  }

  public String getRegistNo(){
       return DataType.getAsString(this.get(S_RegistNo));
  
  }
  public String getRegistNoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RegistNo));
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

  public void initImageNum(long value){
     this.initProperty(S_ImageNum,new Long(value));
  }
  public  void setImageNum(long value){
     this.set(S_ImageNum,new Long(value));
  }
  public  void setImageNumNull(){
     this.set(S_ImageNum,null);
  }

  public long getImageNum(){
        return DataType.getAsLong(this.get(S_ImageNum));
  
  }
  public long getImageNumInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ImageNum));
      }

  public void initOrgDesc(String value){
     this.initProperty(S_OrgDesc,value);
  }
  public  void setOrgDesc(String value){
     this.set(S_OrgDesc,value);
  }
  public  void setOrgDescNull(){
     this.set(S_OrgDesc,null);
  }

  public String getOrgDesc(){
       return DataType.getAsString(this.get(S_OrgDesc));
  
  }
  public String getOrgDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgDesc));
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

