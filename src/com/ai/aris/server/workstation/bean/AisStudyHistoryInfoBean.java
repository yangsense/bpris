package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisStudyHistoryInfoBean extends DataContainer implements DataContainerInterface,IAisStudyHistoryInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisStudyHistoryInfo";



  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyhistoryinfoId = "STUDYHISTORYINFO_ID";
  public final static  String S_StudyMemo = "STUDY_MEMO";
  public final static  String S_StudyUserid = "STUDY_USERID";
  public final static  String S_StudyDate = "STUDY_DATE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisStudyHistoryInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudystatusCode(String value){
     this.initProperty(S_StudystatusCode,value);
  }
  public  void setStudystatusCode(String value){
     this.set(S_StudystatusCode,value);
  }
  public  void setStudystatusCodeNull(){
     this.set(S_StudystatusCode,null);
  }

  public String getStudystatusCode(){
       return DataType.getAsString(this.get(S_StudystatusCode));
  
  }
  public String getStudystatusCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudystatusCode));
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

  public void initStudyhistoryinfoId(long value){
     this.initProperty(S_StudyhistoryinfoId,new Long(value));
  }
  public  void setStudyhistoryinfoId(long value){
     this.set(S_StudyhistoryinfoId,new Long(value));
  }
  public  void setStudyhistoryinfoIdNull(){
     this.set(S_StudyhistoryinfoId,null);
  }

  public long getStudyhistoryinfoId(){
        return DataType.getAsLong(this.get(S_StudyhistoryinfoId));
  
  }
  public long getStudyhistoryinfoIdInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyhistoryinfoId));
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

  public void initStudyUserid(String value){
     this.initProperty(S_StudyUserid,value);
  }
  public  void setStudyUserid(String value){
     this.set(S_StudyUserid,value);
  }
  public  void setStudyUseridNull(){
     this.set(S_StudyUserid,null);
  }

  public String getStudyUserid(){
       return DataType.getAsString(this.get(S_StudyUserid));
  
  }
  public String getStudyUseridInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyUserid));
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


 
 }

