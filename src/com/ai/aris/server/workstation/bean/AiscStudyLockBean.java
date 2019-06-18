package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AiscStudyLockBean extends DataContainer implements DataContainerInterface,IAiscStudyLockValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AiscStudyLock";



  public final static  String S_StartTime = "START_TIME";
  public final static  String S_EndTime = "END_TIME";
  public final static  String S_LockStatus = "LOCK_STATUS";
  public final static  String S_LockIp = "LOCK_IP";
  public final static  String S_UserId = "USER_ID";
  public final static  String S_UserName = "USER_NAME";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscStudyLockBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStartTime(Timestamp value){
     this.initProperty(S_StartTime,value);
  }
  public  void setStartTime(Timestamp value){
     this.set(S_StartTime,value);
  }
  public  void setStartTimeNull(){
     this.set(S_StartTime,null);
  }

  public Timestamp getStartTime(){
        return DataType.getAsDateTime(this.get(S_StartTime));
  
  }
  public Timestamp getStartTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StartTime));
      }

  public void initEndTime(Timestamp value){
     this.initProperty(S_EndTime,value);
  }
  public  void setEndTime(Timestamp value){
     this.set(S_EndTime,value);
  }
  public  void setEndTimeNull(){
     this.set(S_EndTime,null);
  }

  public Timestamp getEndTime(){
        return DataType.getAsDateTime(this.get(S_EndTime));
  
  }
  public Timestamp getEndTimeInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_EndTime));
      }

  public void initLockStatus(String value){
     this.initProperty(S_LockStatus,value);
  }
  public  void setLockStatus(String value){
     this.set(S_LockStatus,value);
  }
  public  void setLockStatusNull(){
     this.set(S_LockStatus,null);
  }

  public String getLockStatus(){
       return DataType.getAsString(this.get(S_LockStatus));
  
  }
  public String getLockStatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LockStatus));
      }

  public void initLockIp(String value){
     this.initProperty(S_LockIp,value);
  }
  public  void setLockIp(String value){
     this.set(S_LockIp,value);
  }
  public  void setLockIpNull(){
     this.set(S_LockIp,null);
  }

  public String getLockIp(){
       return DataType.getAsString(this.get(S_LockIp));
  
  }
  public String getLockIpInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LockIp));
      }

  public void initUserId(String value){
     this.initProperty(S_UserId,value);
  }
  public  void setUserId(String value){
     this.set(S_UserId,value);
  }
  public  void setUserIdNull(){
     this.set(S_UserId,null);
  }

  public String getUserId(){
       return DataType.getAsString(this.get(S_UserId));
  
  }
  public String getUserIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_UserId));
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

  public void initStudyinfoId(long value){
     this.initProperty(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoId(long value){
     this.set(S_StudyinfoId,new Long(value));
  }
  public  void setStudyinfoIdNull(){
     this.set(S_StudyinfoId,null);
  }

  public long getStudyinfoId(){
        return DataType.getAsLong(this.get(S_StudyinfoId));
  
  }
  public long getStudyinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyinfoId));
      }


 
 }

