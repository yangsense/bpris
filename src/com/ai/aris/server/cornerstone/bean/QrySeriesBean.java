package com.ai.aris.server.cornerstone.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.cornerstone.bean.*;

public class QrySeriesBean extends DataContainer implements DataContainerInterface,IQrySeriesValue{

  private static String  m_boName = "com.ai.aris.server.cornerstone.bean.QrySeries";



  public final static  String S_SeriesNumber = "SERIES_NUMBER";
  public final static  String S_SeriesDescription = "SERIES_DESCRIPTION";
  public final static  String S_StudyKey = "STUDY_KEY";
  public final static  String S_SeriesUid = "SERIES_UID";
  public final static  String S_SeriesKey = "SERIES_KEY";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QrySeriesBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initSeriesNumber(String value){
     this.initProperty(S_SeriesNumber,value);
  }
  public  void setSeriesNumber(String value){
     this.set(S_SeriesNumber,value);
  }
  public  void setSeriesNumberNull(){
     this.set(S_SeriesNumber,null);
  }

  public String getSeriesNumber(){
       return DataType.getAsString(this.get(S_SeriesNumber));
  
  }
  public String getSeriesNumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SeriesNumber));
      }

  public void initSeriesDescription(String value){
     this.initProperty(S_SeriesDescription,value);
  }
  public  void setSeriesDescription(String value){
     this.set(S_SeriesDescription,value);
  }
  public  void setSeriesDescriptionNull(){
     this.set(S_SeriesDescription,null);
  }

  public String getSeriesDescription(){
       return DataType.getAsString(this.get(S_SeriesDescription));
  
  }
  public String getSeriesDescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SeriesDescription));
      }

  public void initStudyKey(long value){
     this.initProperty(S_StudyKey,new Long(value));
  }
  public  void setStudyKey(long value){
     this.set(S_StudyKey,new Long(value));
  }
  public  void setStudyKeyNull(){
     this.set(S_StudyKey,null);
  }

  public long getStudyKey(){
        return DataType.getAsLong(this.get(S_StudyKey));
  
  }
  public long getStudyKeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_StudyKey));
      }

  public void initSeriesUid(String value){
     this.initProperty(S_SeriesUid,value);
  }
  public  void setSeriesUid(String value){
     this.set(S_SeriesUid,value);
  }
  public  void setSeriesUidNull(){
     this.set(S_SeriesUid,null);
  }

  public String getSeriesUid(){
       return DataType.getAsString(this.get(S_SeriesUid));
  
  }
  public String getSeriesUidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SeriesUid));
      }

  public void initSeriesKey(long value){
     this.initProperty(S_SeriesKey,new Long(value));
  }
  public  void setSeriesKey(long value){
     this.set(S_SeriesKey,new Long(value));
  }
  public  void setSeriesKeyNull(){
     this.set(S_SeriesKey,null);
  }

  public long getSeriesKey(){
        return DataType.getAsLong(this.get(S_SeriesKey));
  
  }
  public long getSeriesKeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_SeriesKey));
      }


 
 }

