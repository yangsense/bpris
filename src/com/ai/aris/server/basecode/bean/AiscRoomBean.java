package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscRoomBean extends DataContainer implements DataContainerInterface,IAiscRoomValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscRoom";



  public final static  String S_RoomDesc = "ROOM_DESC";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_RoomEnabled = "ROOM_ENABLED";
  public final static  String S_RoomEndesc = "ROOM_ENDESC";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscRoomBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initRoomDesc(String value){
     this.initProperty(S_RoomDesc,value);
  }
  public  void setRoomDesc(String value){
     this.set(S_RoomDesc,value);
  }
  public  void setRoomDescNull(){
     this.set(S_RoomDesc,null);
  }

  public String getRoomDesc(){
       return DataType.getAsString(this.get(S_RoomDesc));
  
  }
  public String getRoomDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RoomDesc));
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

  public void initRoomEnabled(int value){
     this.initProperty(S_RoomEnabled,new Integer(value));
  }
  public  void setRoomEnabled(int value){
     this.set(S_RoomEnabled,new Integer(value));
  }
  public  void setRoomEnabledNull(){
     this.set(S_RoomEnabled,null);
  }

  public int getRoomEnabled(){
        return DataType.getAsInt(this.get(S_RoomEnabled));
  
  }
  public int getRoomEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_RoomEnabled));
      }

  public void initRoomEndesc(String value){
     this.initProperty(S_RoomEndesc,value);
  }
  public  void setRoomEndesc(String value){
     this.set(S_RoomEndesc,value);
  }
  public  void setRoomEndescNull(){
     this.set(S_RoomEndesc,null);
  }

  public String getRoomEndesc(){
       return DataType.getAsString(this.get(S_RoomEndesc));
  
  }
  public String getRoomEndescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RoomEndesc));
      }

  public void initOrgId(long value){
     this.initProperty(S_OrgId,new Long(value));
  }
  public  void setOrgId(long value){
     this.set(S_OrgId,new Long(value));
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public long getOrgId(){
        return DataType.getAsLong(this.get(S_OrgId));
  
  }
  public long getOrgIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_OrgId));
      }


 
 }

