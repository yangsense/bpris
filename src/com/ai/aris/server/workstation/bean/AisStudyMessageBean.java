package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisStudyMessageBean extends DataContainer implements DataContainerInterface,IAisStudyMessageValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisStudyMessage";



  public final static  String S_MessageDestlocid = "MESSAGE_DESTLOCID";
  public final static  String S_StudyType = "STUDY_TYPE";
  public final static  String S_MessageId = "MESSAGE_ID";
  public final static  String S_StatusCode = "STATUS_CODE";
  public final static  String S_StudyDate = "STUDY_DATE";
  public final static  String S_StudyMemo = "STUDY_MEMO";
  public final static  String S_MessageDestuserid = "MESSAGE_DESTUSERID";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyMessageBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initMessageDestlocid(long value){
     this.initProperty(S_MessageDestlocid,new Long(value));
  }
  public  void setMessageDestlocid(long value){
     this.set(S_MessageDestlocid,new Long(value));
  }
  public  void setMessageDestlocidNull(){
     this.set(S_MessageDestlocid,null);
  }

  public long getMessageDestlocid(){
        return DataType.getAsLong(this.get(S_MessageDestlocid));
  
  }
  public long getMessageDestlocidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MessageDestlocid));
      }

  public void initStudyType(String value){
     this.initProperty(S_StudyType,value);
  }
  public  void setStudyType(String value){
     this.set(S_StudyType,value);
  }
  public  void setStudyTypeNull(){
     this.set(S_StudyType,null);
  }

  public String getStudyType(){
       return DataType.getAsString(this.get(S_StudyType));
  
  }
  public String getStudyTypeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyType));
      }

  public void initMessageId(long value){
     this.initProperty(S_MessageId,new Long(value));
  }
  public  void setMessageId(long value){
     this.set(S_MessageId,new Long(value));
  }
  public  void setMessageIdNull(){
     this.set(S_MessageId,null);
  }

  public long getMessageId(){
        return DataType.getAsLong(this.get(S_MessageId));
  
  }
  public long getMessageIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_MessageId));
      }

  public void initStatusCode(String value){
     this.initProperty(S_StatusCode,value);
  }
  public  void setStatusCode(String value){
     this.set(S_StatusCode,value);
  }
  public  void setStatusCodeNull(){
     this.set(S_StatusCode,null);
  }

  public String getStatusCode(){
       return DataType.getAsString(this.get(S_StatusCode));
  
  }
  public String getStatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StatusCode));
      }

  public void initStudyDate(Timestamp value){
     this.initProperty(S_StudyDate,value);
  }
  public  void setStudyDate(Timestamp value){
     this.set(S_StudyDate,value);
  }
  public  void setStudyDateNull(){
     this.set(S_StudyDate,null);
  }

  public Timestamp getStudyDate(){
        return DataType.getAsDateTime(this.get(S_StudyDate));
  
  }
  public Timestamp getStudyDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_StudyDate));
      }

  public void initStudyMemo(String value){
     this.initProperty(S_StudyMemo,value);
  }
  public  void setStudyMemo(String value){
     this.set(S_StudyMemo,value);
  }
  public  void setStudyMemoNull(){
     this.set(S_StudyMemo,null);
  }

  public String getStudyMemo(){
       return DataType.getAsString(this.get(S_StudyMemo));
  
  }
  public String getStudyMemoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyMemo));
      }

  public void initMessageDestuserid(String value){
     this.initProperty(S_MessageDestuserid,value);
  }
  public  void setMessageDestuserid(String value){
     this.set(S_MessageDestuserid,value);
  }
  public  void setMessageDestuseridNull(){
     this.set(S_MessageDestuserid,null);
  }

  public String getMessageDestuserid(){
       return DataType.getAsString(this.get(S_MessageDestuserid));
  
  }
  public String getMessageDestuseridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_MessageDestuserid));
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

