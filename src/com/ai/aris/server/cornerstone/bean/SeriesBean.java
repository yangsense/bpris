package com.ai.aris.server.cornerstone.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

public class SeriesBean extends DataContainer implements DataContainerInterface,ISeriesValue{

  private static String  m_boName = "com.ai.aris.server.cornerstone.bean.Series";



  public final static  String S_Seriesnumber = "SERIESNUMBER";
  public final static  String S_Modality = "MODALITY";
  public final static  String S_Bodypart = "BODYPART";
  public final static  String S_Seriesuid = "SERIESUID";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_Stationname = "STATIONNAME";
  public final static  String S_Studykey = "STUDYKEY";
  public final static  String S_Serieskey = "SERIESKEY";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public SeriesBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initSeriesnumber(String value){
     this.initProperty(S_Seriesnumber,value);
  }
  public  void setSeriesnumber(String value){
     this.set(S_Seriesnumber,value);
  }
  public  void setSeriesnumberNull(){
     this.set(S_Seriesnumber,null);
  }

  public String getSeriesnumber(){
       return DataType.getAsString(this.get(S_Seriesnumber));
  
  }
  public String getSeriesnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Seriesnumber));
      }

  public void initModality(String value){
     this.initProperty(S_Modality,value);
  }
  public  void setModality(String value){
     this.set(S_Modality,value);
  }
  public  void setModalityNull(){
     this.set(S_Modality,null);
  }

  public String getModality(){
       return DataType.getAsString(this.get(S_Modality));
  
  }
  public String getModalityInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Modality));
      }

  public void initBodypart(String value){
     this.initProperty(S_Bodypart,value);
  }
  public  void setBodypart(String value){
     this.set(S_Bodypart,value);
  }
  public  void setBodypartNull(){
     this.set(S_Bodypart,null);
  }

  public String getBodypart(){
       return DataType.getAsString(this.get(S_Bodypart));
  
  }
  public String getBodypartInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Bodypart));
      }

  public void initSeriesuid(String value){
     this.initProperty(S_Seriesuid,value);
  }
  public  void setSeriesuid(String value){
     this.set(S_Seriesuid,value);
  }
  public  void setSeriesuidNull(){
     this.set(S_Seriesuid,null);
  }

  public String getSeriesuid(){
       return DataType.getAsString(this.get(S_Seriesuid));
  
  }
  public String getSeriesuidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Seriesuid));
      }

  public void initRemarks(String value){
     this.initProperty(S_Remarks,value);
  }
  public  void setRemarks(String value){
     this.set(S_Remarks,value);
  }
  public  void setRemarksNull(){
     this.set(S_Remarks,null);
  }

  public String getRemarks(){
       return DataType.getAsString(this.get(S_Remarks));
  
  }
  public String getRemarksInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Remarks));
      }

  public void initStationname(String value){
     this.initProperty(S_Stationname,value);
  }
  public  void setStationname(String value){
     this.set(S_Stationname,value);
  }
  public  void setStationnameNull(){
     this.set(S_Stationname,null);
  }

  public String getStationname(){
       return DataType.getAsString(this.get(S_Stationname));
  
  }
  public String getStationnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Stationname));
      }

  public void initStudykey(long value){
     this.initProperty(S_Studykey,new Long(value));
  }
  public  void setStudykey(long value){
     this.set(S_Studykey,new Long(value));
  }
  public  void setStudykeyNull(){
     this.set(S_Studykey,null);
  }

  public long getStudykey(){
        return DataType.getAsLong(this.get(S_Studykey));
  
  }
  public long getStudykeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Studykey));
      }

  public void initSerieskey(long value){
     this.initProperty(S_Serieskey,new Long(value));
  }
  public  void setSerieskey(long value){
     this.set(S_Serieskey,new Long(value));
  }
  public  void setSerieskeyNull(){
     this.set(S_Serieskey,null);
  }

  public long getSerieskey(){
        return DataType.getAsLong(this.get(S_Serieskey));
  
  }
  public long getSerieskeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Serieskey));
      }


 
 }

