package com.ai.aris.server.statanalysis.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.statanalysis.bean.*;

public class QryHzMultipleListBean extends DataContainer implements DataContainerInterface,IQryHzMultipleListValue{

  private static String  m_boName = "com.ai.aris.server.statanalysis.bean.QryHzMultipleList";



  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_StudyDatetime = "STUDY_DATETIME";
  public final static  String S_OrgName = "ORG_NAME";
  public final static  String S_CountyDesc = "COUNTY_DESC";
  public final static  String S_Hzsqsl = "HZSQSL";
  public final static  String S_Hzsl = "HZSL";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryHzMultipleListBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initStudyDatetime(String value){
     this.initProperty(S_StudyDatetime,value);
  }
  public  void setStudyDatetime(String value){
     this.set(S_StudyDatetime,value);
  }
  public  void setStudyDatetimeNull(){
     this.set(S_StudyDatetime,null);
  }

  public String getStudyDatetime(){
       return DataType.getAsString(this.get(S_StudyDatetime));
  
  }
  public String getStudyDatetimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDatetime));
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

  public void initHzsqsl(long value){
     this.initProperty(S_Hzsqsl,new Long(value));
  }
  public  void setHzsqsl(long value){
     this.set(S_Hzsqsl,new Long(value));
  }
  public  void setHzsqslNull(){
     this.set(S_Hzsqsl,null);
  }

  public long getHzsqsl(){
        return DataType.getAsLong(this.get(S_Hzsqsl));
  
  }
  public long getHzsqslInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Hzsqsl));
      }

  public void initHzsl(long value){
     this.initProperty(S_Hzsl,new Long(value));
  }
  public  void setHzsl(long value){
     this.set(S_Hzsl,new Long(value));
  }
  public  void setHzslNull(){
     this.set(S_Hzsl,null);
  }

  public long getHzsl(){
        return DataType.getAsLong(this.get(S_Hzsl));
  
  }
  public long getHzslInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Hzsl));
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


 
 }

