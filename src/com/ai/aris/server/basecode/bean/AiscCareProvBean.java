package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscCareProvBean extends DataContainer implements DataContainerInterface,IAiscCareProvValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscCareProv";



  public final static  String S_CareprovTypeid = "CAREPROV_TYPEID";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_CareprovCode = "CAREPROV_CODE";
  public final static  String S_CareprovId = "CAREPROV_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscCareProvBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initCareprovTypeid(long value){
     this.initProperty(S_CareprovTypeid,new Long(value));
  }
  public  void setCareprovTypeid(long value){
     this.set(S_CareprovTypeid,new Long(value));
  }
  public  void setCareprovTypeidNull(){
     this.set(S_CareprovTypeid,null);
  }

  public long getCareprovTypeid(){
        return DataType.getAsLong(this.get(S_CareprovTypeid));
  
  }
  public long getCareprovTypeidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CareprovTypeid));
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

  public void initCareprovCode(String value){
     this.initProperty(S_CareprovCode,value);
  }
  public  void setCareprovCode(String value){
     this.set(S_CareprovCode,value);
  }
  public  void setCareprovCodeNull(){
     this.set(S_CareprovCode,null);
  }

  public String getCareprovCode(){
       return DataType.getAsString(this.get(S_CareprovCode));
  
  }
  public String getCareprovCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CareprovCode));
      }

  public void initCareprovId(long value){
     this.initProperty(S_CareprovId,new Long(value));
  }
  public  void setCareprovId(long value){
     this.set(S_CareprovId,new Long(value));
  }
  public  void setCareprovIdNull(){
     this.set(S_CareprovId,null);
  }

  public long getCareprovId(){
        return DataType.getAsLong(this.get(S_CareprovId));
  
  }
  public long getCareprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CareprovId));
      }


 
 }

