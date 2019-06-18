package com.ai.aris.server.interfacereal.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.bean.*;

public class AiscRoomRealBean extends DataContainer implements DataContainerInterface,IAiscRoomRealValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.bean.AiscRoomReal";



  public final static  String S_OldRoomId = "OLD_ROOM_ID";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_RoomRealId = "ROOM_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscRoomRealBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initOldRoomId(long value){
     this.initProperty(S_OldRoomId,new Long(value));
  }
  public  void setOldRoomId(long value){
     this.set(S_OldRoomId,new Long(value));
  }
  public  void setOldRoomIdNull(){
     this.set(S_OldRoomId,null);
  }

  public long getOldRoomId(){
        return DataType.getAsLong(this.get(S_OldRoomId));
  
  }
  public long getOldRoomIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OldRoomId));
      }

  public void initRoomId(long value){
     this.initProperty(S_RoomId,new Long(value));
  }
  public  void setRoomId(long value){
     this.set(S_RoomId,new Long(value));
  }
  public  void setRoomIdNull(){
     this.set(S_RoomId,null);
  }

  public long getRoomId(){
        return DataType.getAsLong(this.get(S_RoomId));
  
  }
  public long getRoomIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoomId));
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

  public void initRoomRealId(long value){
     this.initProperty(S_RoomRealId,new Long(value));
  }
  public  void setRoomRealId(long value){
     this.set(S_RoomRealId,new Long(value));
  }
  public  void setRoomRealIdNull(){
     this.set(S_RoomRealId,null);
  }

  public long getRoomRealId(){
        return DataType.getAsLong(this.get(S_RoomRealId));
  
  }
  public long getRoomRealIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_RoomRealId));
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

