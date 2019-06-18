package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscEquipmentRealBean extends DataContainer implements DataContainerInterface,IAiscEquipmentRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscEquipmentReal";



  public final static  String S_EquipmentId = "EQUIPMENT_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_EquipmentRealId = "EQUIPMENT_REAL_ID";
  public final static  String S_OldEquipmentId = "OLD_EQUIPMENT_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscEquipmentRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initEquipmentRealId(long value){
     this.initProperty(S_EquipmentRealId,new Long(value));
  }
  public  void setEquipmentRealId(long value){
     this.set(S_EquipmentRealId,new Long(value));
  }
  public  void setEquipmentRealIdNull(){
     this.set(S_EquipmentRealId,null);
  }

  public long getEquipmentRealId(){
        return DataType.getAsLong(this.get(S_EquipmentRealId));
  
  }
  public long getEquipmentRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_EquipmentRealId));
      }

  public void initOldEquipmentId(long value){
     this.initProperty(S_OldEquipmentId,new Long(value));
  }
  public  void setOldEquipmentId(long value){
     this.set(S_OldEquipmentId,new Long(value));
  }
  public  void setOldEquipmentIdNull(){
     this.set(S_OldEquipmentId,null);
  }

  public long getOldEquipmentId(){
        return DataType.getAsLong(this.get(S_OldEquipmentId));
  
  }
  public long getOldEquipmentIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldEquipmentId));
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

