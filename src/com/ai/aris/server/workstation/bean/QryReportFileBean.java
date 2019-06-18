package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryReportFileBean extends DataContainer implements DataContainerInterface,IQryReportFileValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryReportFile";



  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_FileId = "FILE_ID";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_Status = "STATUS";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";
  public final static  String S_ServerName = "SERVER_NAME";
  public final static  String S_ServerIp = "SERVER_IP";
  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_FilePath = "FILE_PATH";
  public final static  String S_ReportId = "REPORT_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryReportFileBean() throws AIException{
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

  public void initFileId(long value){
     this.initProperty(S_FileId,new Long(value));
  }
  public  void setFileId(long value){
     this.set(S_FileId,new Long(value));
  }
  public  void setFileIdNull(){
     this.set(S_FileId,null);
  }

  public long getFileId(){
        return DataType.getAsLong(this.get(S_FileId));
  
  }
  public long getFileIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_FileId));
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

  public void initFilePath(String value){
     this.initProperty(S_FilePath,value);
  }
  public  void setFilePath(String value){
     this.set(S_FilePath,value);
  }
  public  void setFilePathNull(){
     this.set(S_FilePath,null);
  }

  public String getFilePath(){
       return DataType.getAsString(this.get(S_FilePath));
  
  }
  public String getFilePathInitialValue(){
        return DataType.getAsString(this.getOldObj(S_FilePath));
      }

  public void initReportId(long value){
     this.initProperty(S_ReportId,new Long(value));
  }
  public  void setReportId(long value){
     this.set(S_ReportId,new Long(value));
  }
  public  void setReportIdNull(){
     this.set(S_ReportId,null);
  }

  public long getReportId(){
        return DataType.getAsLong(this.get(S_ReportId));
  
  }
  public long getReportIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_ReportId));
      }


 
 }

