package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QueryStudyItemBean extends DataContainer implements DataContainerInterface,IQueryStudyItemValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QueryStudyItem";



  public final static  String S_StudyitemDesc = "STUDYITEM_DESC";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_StudyitemBodyinfo = "STUDYITEM_BODYINFO";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QueryStudyItemBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initStudyitemDesc(String value){
     this.initProperty(S_StudyitemDesc,value);
  }
  public  void setStudyitemDesc(String value){
     this.set(S_StudyitemDesc,value);
  }
  public  void setStudyitemDescNull(){
     this.set(S_StudyitemDesc,null);
  }

  public String getStudyitemDesc(){
       return DataType.getAsString(this.get(S_StudyitemDesc));
  
  }
  public String getStudyitemDescInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemDesc));
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

  public void initStudyitemBodyinfo(String value){
     this.initProperty(S_StudyitemBodyinfo,value);
  }
  public  void setStudyitemBodyinfo(String value){
     this.set(S_StudyitemBodyinfo,value);
  }
  public  void setStudyitemBodyinfoNull(){
     this.set(S_StudyitemBodyinfo,null);
  }

  public String getStudyitemBodyinfo(){
       return DataType.getAsString(this.get(S_StudyitemBodyinfo));
  
  }
  public String getStudyitemBodyinfoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyitemBodyinfo));
      }


 
 }

