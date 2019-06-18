package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscLocRealBean extends DataContainer implements DataContainerInterface,IAiscLocRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscLocReal";



  public final static  String S_LocRealId = "LOC_REAL_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_OldLocId = "OLD_LOC_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscLocRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initLocRealId(long value){
     this.initProperty(S_LocRealId,new Long(value));
  }
  public  void setLocRealId(long value){
     this.set(S_LocRealId,new Long(value));
  }
  public  void setLocRealIdNull(){
     this.set(S_LocRealId,null);
  }

  public long getLocRealId(){
        return DataType.getAsLong(this.get(S_LocRealId));
  
  }
  public long getLocRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_LocRealId));
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

  public void initOldLocId(long value){
     this.initProperty(S_OldLocId,new Long(value));
  }
  public  void setOldLocId(long value){
     this.set(S_OldLocId,new Long(value));
  }
  public  void setOldLocIdNull(){
     this.set(S_OldLocId,null);
  }

  public long getOldLocId(){
        return DataType.getAsLong(this.get(S_OldLocId));
  
  }
  public long getOldLocIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldLocId));
      }


 
 }

