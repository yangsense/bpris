package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QueryDataBaseBean extends DataContainer implements DataContainerInterface,IQueryDataBaseValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QueryDataBase";



  public final static  String S_SourceUrl = "SOURCE_URL";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_SourceId = "SOURCE_ID";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_Status = "STATUS";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_CityDesc = "CITY_DESC";
  public final static  String S_PatientTypecode = "PATIENT_TYPECODE";
  public final static  String S_Haspacs = "HASPACS";
  public final static  String S_SourceUser = "SOURCE_USER";
  public final static  String S_SourcePassword = "SOURCE_PASSWORD";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryDataBaseBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initSourceUrl(String value){
     this.initProperty(S_SourceUrl,value);
  }
  public  void setSourceUrl(String value){
     this.set(S_SourceUrl,value);
  }
  public  void setSourceUrlNull(){
     this.set(S_SourceUrl,null);
  }

  public String getSourceUrl(){
       return DataType.getAsString(this.get(S_SourceUrl));
  
  }
  public String getSourceUrlInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SourceUrl));
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

  public void initSourceId(long value){
     this.initProperty(S_SourceId,new Long(value));
  }
  public  void setSourceId(long value){
     this.set(S_SourceId,new Long(value));
  }
  public  void setSourceIdNull(){
     this.set(S_SourceId,null);
  }

  public long getSourceId(){
        return DataType.getAsLong(this.get(S_SourceId));
  
  }
  public long getSourceIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SourceId));
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

  public void initPatientTypecode(String value){
     this.initProperty(S_PatientTypecode,value);
  }
  public  void setPatientTypecode(String value){
     this.set(S_PatientTypecode,value);
  }
  public  void setPatientTypecodeNull(){
     this.set(S_PatientTypecode,null);
  }

  public String getPatientTypecode(){
       return DataType.getAsString(this.get(S_PatientTypecode));
  
  }
  public String getPatientTypecodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientTypecode));
      }

  public void initHaspacs(long value){
     this.initProperty(S_Haspacs,new Long(value));
  }
  public  void setHaspacs(long value){
     this.set(S_Haspacs,new Long(value));
  }
  public  void setHaspacsNull(){
     this.set(S_Haspacs,null);
  }

  public long getHaspacs(){
        return DataType.getAsLong(this.get(S_Haspacs));
  
  }
  public long getHaspacsInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Haspacs));
      }

  public void initSourceUser(String value){
     this.initProperty(S_SourceUser,value);
  }
  public  void setSourceUser(String value){
     this.set(S_SourceUser,value);
  }
  public  void setSourceUserNull(){
     this.set(S_SourceUser,null);
  }

  public String getSourceUser(){
       return DataType.getAsString(this.get(S_SourceUser));
  
  }
  public String getSourceUserInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SourceUser));
      }

  public void initSourcePassword(String value){
     this.initProperty(S_SourcePassword,value);
  }
  public  void setSourcePassword(String value){
     this.set(S_SourcePassword,value);
  }
  public  void setSourcePasswordNull(){
     this.set(S_SourcePassword,null);
  }

  public String getSourcePassword(){
       return DataType.getAsString(this.get(S_SourcePassword));
  
  }
  public String getSourcePasswordInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SourcePassword));
      }


 
 }

