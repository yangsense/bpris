package com.ai.aris.server.basecode.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.basecode.bean.*;

public class AiscServerInfoBean extends DataContainer implements DataContainerInterface,IAiscServerInfoValue{

  private static String  m_boName = "com.ai.aris.server.basecode.bean.AiscServerInfo";



  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_ServerName = "SERVER_NAME";
  public final static  String S_ServerIp = "SERVER_IP";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AiscServerInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initServerPassword(String value){
     this.initProperty(S_ServerPassword,value);
  }
  public  void setServerPassword(String value){
     this.set(S_ServerPassword,value);
  }
  public  void setServerPasswordNull(){
     this.set(S_ServerPassword,null);
  }

  public String getServerPassword(){
       return DataType.getAsString(this.get(S_ServerPassword));
  
  }
  public String getServerPasswordInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServerPassword));
      }

  public void initServerPort(long value){
     this.initProperty(S_ServerPort,new Long(value));
  }
  public  void setServerPort(long value){
     this.set(S_ServerPort,new Long(value));
  }
  public  void setServerPortNull(){
     this.set(S_ServerPort,null);
  }

  public long getServerPort(){
        return DataType.getAsLong(this.get(S_ServerPort));
  
  }
  public long getServerPortInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerPort));
      }

  public void initServerUser(String value){
     this.initProperty(S_ServerUser,value);
  }
  public  void setServerUser(String value){
     this.set(S_ServerUser,value);
  }
  public  void setServerUserNull(){
     this.set(S_ServerUser,null);
  }

  public String getServerUser(){
       return DataType.getAsString(this.get(S_ServerUser));
  
  }
  public String getServerUserInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServerUser));
      }

  public void initServerId(long value){
     this.initProperty(S_ServerId,new Long(value));
  }
  public  void setServerId(long value){
     this.set(S_ServerId,new Long(value));
  }
  public  void setServerIdNull(){
     this.set(S_ServerId,null);
  }

  public long getServerId(){
        return DataType.getAsLong(this.get(S_ServerId));
  
  }
  public long getServerIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerId));
      }

  public void initServerEnabled(int value){
     this.initProperty(S_ServerEnabled,new Integer(value));
  }
  public  void setServerEnabled(int value){
     this.set(S_ServerEnabled,new Integer(value));
  }
  public  void setServerEnabledNull(){
     this.set(S_ServerEnabled,null);
  }

  public int getServerEnabled(){
        return DataType.getAsInt(this.get(S_ServerEnabled));
  
  }
  public int getServerEnabledInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ServerEnabled));
      }

  public void initServerType(int value){
     this.initProperty(S_ServerType,new Integer(value));
  }
  public  void setServerType(int value){
     this.set(S_ServerType,new Integer(value));
  }
  public  void setServerTypeNull(){
     this.set(S_ServerType,null);
  }

  public int getServerType(){
        return DataType.getAsInt(this.get(S_ServerType));
  
  }
  public int getServerTypeInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_ServerType));
      }

  public void initServerName(String value){
     this.initProperty(S_ServerName,value);
  }
  public  void setServerName(String value){
     this.set(S_ServerName,value);
  }
  public  void setServerNameNull(){
     this.set(S_ServerName,null);
  }

  public String getServerName(){
       return DataType.getAsString(this.get(S_ServerName));
  
  }
  public String getServerNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServerName));
      }

  public void initServerIp(String value){
     this.initProperty(S_ServerIp,value);
  }
  public  void setServerIp(String value){
     this.set(S_ServerIp,value);
  }
  public  void setServerIpNull(){
     this.set(S_ServerIp,null);
  }

  public String getServerIp(){
       return DataType.getAsString(this.get(S_ServerIp));
  
  }
  public String getServerIpInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ServerIp));
      }


 
 }

