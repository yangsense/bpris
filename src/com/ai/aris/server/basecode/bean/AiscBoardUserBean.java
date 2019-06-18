package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscBoardUserBean extends DataContainer implements DataContainerInterface,IAiscBoardUserValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscBoardUser";



  public final static  String S_UserId = "USER_ID";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_UserName = "USER_NAME";
  public final static  String S_UserCode = "USER_CODE";
  public final static  String S_AuthTimme = "AUTH_TIMME";
  public final static  String S_Status = "STATUS";
  public final static  String S_AuthLevel = "AUTH_LEVEL";
  public final static  String S_OrgId = "ORG_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscBoardUserBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initUserId(long value){
     this.initProperty(S_UserId,new Long(value));
  }
  public  void setUserId(long value){
     this.set(S_UserId,new Long(value));
  }
  public  void setUserIdNull(){
     this.set(S_UserId,null);
  }

  public long getUserId(){
        return DataType.getAsLong(this.get(S_UserId));
  
  }
  public long getUserIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_UserId));
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

  public void initUserName(String value){
     this.initProperty(S_UserName,value);
  }
  public  void setUserName(String value){
     this.set(S_UserName,value);
  }
  public  void setUserNameNull(){
     this.set(S_UserName,null);
  }

  public String getUserName(){
       return DataType.getAsString(this.get(S_UserName));
  
  }
  public String getUserNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UserName));
      }

  public void initUserCode(String value){
     this.initProperty(S_UserCode,value);
  }
  public  void setUserCode(String value){
     this.set(S_UserCode,value);
  }
  public  void setUserCodeNull(){
     this.set(S_UserCode,null);
  }

  public String getUserCode(){
       return DataType.getAsString(this.get(S_UserCode));
  
  }
  public String getUserCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UserCode));
      }

  public void initAuthTimme(Timestamp value){
     this.initProperty(S_AuthTimme,value);
  }
  public  void setAuthTimme(Timestamp value){
     this.set(S_AuthTimme,value);
  }
  public  void setAuthTimmeNull(){
     this.set(S_AuthTimme,null);
  }

  public Timestamp getAuthTimme(){
        return DataType.getAsDateTime(this.get(S_AuthTimme));
  
  }
  public Timestamp getAuthTimmeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_AuthTimme));
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

  public void initAuthLevel(String value){
     this.initProperty(S_AuthLevel,value);
  }
  public  void setAuthLevel(String value){
     this.set(S_AuthLevel,value);
  }
  public  void setAuthLevelNull(){
     this.set(S_AuthLevel,null);
  }

  public String getAuthLevel(){
       return DataType.getAsString(this.get(S_AuthLevel));
  
  }
  public String getAuthLevelInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AuthLevel));
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

