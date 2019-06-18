package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class QueryBigSortBean extends DataContainer implements DataContainerInterface,IQueryBigSortValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.QueryBigSort";



  public final static  String S_CountyCode = "COUNTY_CODE";
  public final static  String S_CityCode = "CITY_CODE";
  public final static  String S_OrdcategoryId = "ORDCATEGORY_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OrdcategoryDesc = "ORDCATEGORY_DESC";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryBigSortBean() throws AIException{
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

  public void initOrdcategoryId(long value){
     this.initProperty(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryId(long value){
     this.set(S_OrdcategoryId,new Long(value));
  }
  public  void setOrdcategoryIdNull(){
     this.set(S_OrdcategoryId,null);
  }

  public long getOrdcategoryId(){
        return DataType.getAsLong(this.get(S_OrdcategoryId));
  
  }
  public long getOrdcategoryIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrdcategoryId));
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

  public void initOrdcategoryDesc(String value){
     this.initProperty(S_OrdcategoryDesc,value);
  }
  public  void setOrdcategoryDesc(String value){
     this.set(S_OrdcategoryDesc,value);
  }
  public  void setOrdcategoryDescNull(){
     this.set(S_OrdcategoryDesc,null);
  }

  public String getOrdcategoryDesc(){
       return DataType.getAsString(this.get(S_OrdcategoryDesc));
  
  }
  public String getOrdcategoryDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrdcategoryDesc));
      }


 
 }

