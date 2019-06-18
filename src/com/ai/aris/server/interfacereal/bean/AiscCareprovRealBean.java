package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscCareprovRealBean extends DataContainer implements DataContainerInterface,IAiscCareprovRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscCareprovReal";



  public final static  String S_OldCareprovId = "OLD_CAREPROV_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_CareprovRealId = "CAREPROV_REAL_ID";
  public final static  String S_CareprovId = "CAREPROV_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscCareprovRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOldCareprovId(long value){
     this.initProperty(S_OldCareprovId,new Long(value));
  }
  public  void setOldCareprovId(long value){
     this.set(S_OldCareprovId,new Long(value));
  }
  public  void setOldCareprovIdNull(){
     this.set(S_OldCareprovId,null);
  }

  public long getOldCareprovId(){
        return DataType.getAsLong(this.get(S_OldCareprovId));
  
  }
  public long getOldCareprovIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldCareprovId));
      }

  public void initCreateTime(Timestamp value){
     this.initProperty(S_CreateTime,value);
  }
  public  void setCreateTime(Timestamp value){
     this.set(S_CreateTime,value);
  }
  public  void setCreateTimeNull(){
     this.set(S_CreateTime,null);
  }

  public Timestamp getCreateTime(){
        return DataType.getAsDateTime(this.get(S_CreateTime));
  
  }
  public Timestamp getCreateTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_CreateTime));
      }

  public void initCareprovRealId(long value){
     this.initProperty(S_CareprovRealId,new Long(value));
  }
  public  void setCareprovRealId(long value){
     this.set(S_CareprovRealId,new Long(value));
  }
  public  void setCareprovRealIdNull(){
     this.set(S_CareprovRealId,null);
  }

  public long getCareprovRealId(){
        return DataType.getAsLong(this.get(S_CareprovRealId));
  
  }
  public long getCareprovRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_CareprovRealId));
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

