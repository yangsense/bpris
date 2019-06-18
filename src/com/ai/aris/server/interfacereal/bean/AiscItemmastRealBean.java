package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscItemmastRealBean extends DataContainer implements DataContainerInterface,IAiscItemmastRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscItemmastReal";



  public final static  String S_ItemmastRealId = "ITEMMAST_REAL_ID";
  public final static  String S_OldItemmastId = "OLD_ITEMMAST_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_ItemmastId = "ITEMMAST_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscItemmastRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initItemmastRealId(long value){
     this.initProperty(S_ItemmastRealId,new Long(value));
  }
  public  void setItemmastRealId(long value){
     this.set(S_ItemmastRealId,new Long(value));
  }
  public  void setItemmastRealIdNull(){
     this.set(S_ItemmastRealId,null);
  }

  public long getItemmastRealId(){
        return DataType.getAsLong(this.get(S_ItemmastRealId));
  
  }
  public long getItemmastRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastRealId));
      }

  public void initOldItemmastId(long value){
     this.initProperty(S_OldItemmastId,new Long(value));
  }
  public  void setOldItemmastId(long value){
     this.set(S_OldItemmastId,new Long(value));
  }
  public  void setOldItemmastIdNull(){
     this.set(S_OldItemmastId,null);
  }

  public long getOldItemmastId(){
        return DataType.getAsLong(this.get(S_OldItemmastId));
  
  }
  public long getOldItemmastIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldItemmastId));
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

  public void initItemmastId(long value){
     this.initProperty(S_ItemmastId,new Long(value));
  }
  public  void setItemmastId(long value){
     this.set(S_ItemmastId,new Long(value));
  }
  public  void setItemmastIdNull(){
     this.set(S_ItemmastId,null);
  }

  public long getItemmastId(){
        return DataType.getAsLong(this.get(S_ItemmastId));
  
  }
  public long getItemmastIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ItemmastId));
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

