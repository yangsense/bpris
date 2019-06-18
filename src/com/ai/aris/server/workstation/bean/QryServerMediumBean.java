package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryServerMediumBean extends DataContainer implements DataContainerInterface,IQryServerMediumValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryServerMedium";



  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_LocDesc = "LOC_DESC";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_ServerIp = "SERVER_IP";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";
  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_LocCode = "LOC_CODE";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_ServerName = "SERVER_NAME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryServerMediumBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initMediumIsfull(long value){
     this.initProperty(S_MediumIsfull,new Long(value));
  }
  public  void setMediumIsfull(long value){
     this.set(S_MediumIsfull,new Long(value));
  }
  public  void setMediumIsfullNull(){
     this.set(S_MediumIsfull,null);
  }

  public long getMediumIsfull(){
        return DataType.getAsLong(this.get(S_MediumIsfull));
  
  }
  public long getMediumIsfullInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumIsfull));
      }

  public void initMediumPath(String value){
     this.initProperty(S_MediumPath,value);
  }
  public  void setMediumPath(String value){
     this.set(S_MediumPath,value);
  }
  public  void setMediumPathNull(){
     this.set(S_MediumPath,null);
  }

  public String getMediumPath(){
       return DataType.getAsString(this.get(S_MediumPath));
  
  }
  public String getMediumPathInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MediumPath));
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

  public void initLocDesc(String value){
     this.initProperty(S_LocDesc,value);
  }
  public  void setLocDesc(String value){
     this.set(S_LocDesc,value);
  }
  public  void setLocDescNull(){
     this.set(S_LocDesc,null);
  }

  public String getLocDesc(){
       return DataType.getAsString(this.get(S_LocDesc));
  
  }
  public String getLocDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocDesc));
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

  public void initMediumIsonline(long value){
     this.initProperty(S_MediumIsonline,new Long(value));
  }
  public  void setMediumIsonline(long value){
     this.set(S_MediumIsonline,new Long(value));
  }
  public  void setMediumIsonlineNull(){
     this.set(S_MediumIsonline,null);
  }

  public long getMediumIsonline(){
        return DataType.getAsLong(this.get(S_MediumIsonline));
  
  }
  public long getMediumIsonlineInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumIsonline));
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

  public void initServerType(long value){
     this.initProperty(S_ServerType,new Long(value));
  }
  public  void setServerType(long value){
     this.set(S_ServerType,new Long(value));
  }
  public  void setServerTypeNull(){
     this.set(S_ServerType,null);
  }

  public long getServerType(){
        return DataType.getAsLong(this.get(S_ServerType));
  
  }
  public long getServerTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerType));
      }

  public void initServerEnabled(long value){
     this.initProperty(S_ServerEnabled,new Long(value));
  }
  public  void setServerEnabled(long value){
     this.set(S_ServerEnabled,new Long(value));
  }
  public  void setServerEnabledNull(){
     this.set(S_ServerEnabled,null);
  }

  public long getServerEnabled(){
        return DataType.getAsLong(this.get(S_ServerEnabled));
  
  }
  public long getServerEnabledInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ServerEnabled));
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

  public void initMediumReminesize(long value){
     this.initProperty(S_MediumReminesize,new Long(value));
  }
  public  void setMediumReminesize(long value){
     this.set(S_MediumReminesize,new Long(value));
  }
  public  void setMediumReminesizeNull(){
     this.set(S_MediumReminesize,null);
  }

  public long getMediumReminesize(){
        return DataType.getAsLong(this.get(S_MediumReminesize));
  
  }
  public long getMediumReminesizeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumReminesize));
      }

  public void initMediumType(long value){
     this.initProperty(S_MediumType,new Long(value));
  }
  public  void setMediumType(long value){
     this.set(S_MediumType,new Long(value));
  }
  public  void setMediumTypeNull(){
     this.set(S_MediumType,null);
  }

  public long getMediumType(){
        return DataType.getAsLong(this.get(S_MediumType));
  
  }
  public long getMediumTypeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumType));
      }

  public void initMediumId(long value){
     this.initProperty(S_MediumId,new Long(value));
  }
  public  void setMediumId(long value){
     this.set(S_MediumId,new Long(value));
  }
  public  void setMediumIdNull(){
     this.set(S_MediumId,null);
  }

  public long getMediumId(){
        return DataType.getAsLong(this.get(S_MediumId));
  
  }
  public long getMediumIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumId));
      }

  public void initLocCode(String value){
     this.initProperty(S_LocCode,value);
  }
  public  void setLocCode(String value){
     this.set(S_LocCode,value);
  }
  public  void setLocCodeNull(){
     this.set(S_LocCode,null);
  }

  public String getLocCode(){
       return DataType.getAsString(this.get(S_LocCode));
  
  }
  public String getLocCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_LocCode));
      }

  public void initMediumEnabled(long value){
     this.initProperty(S_MediumEnabled,new Long(value));
  }
  public  void setMediumEnabled(long value){
     this.set(S_MediumEnabled,new Long(value));
  }
  public  void setMediumEnabledNull(){
     this.set(S_MediumEnabled,null);
  }

  public long getMediumEnabled(){
        return DataType.getAsLong(this.get(S_MediumEnabled));
  
  }
  public long getMediumEnabledInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumEnabled));
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

  public void initMediumSize(long value){
     this.initProperty(S_MediumSize,new Long(value));
  }
  public  void setMediumSize(long value){
     this.set(S_MediumSize,new Long(value));
  }
  public  void setMediumSizeNull(){
     this.set(S_MediumSize,null);
  }

  public long getMediumSize(){
        return DataType.getAsLong(this.get(S_MediumSize));
  
  }
  public long getMediumSizeInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MediumSize));
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


 
 }

