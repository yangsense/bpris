package com.ai.aris.server.cornerstone.bean;

import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.*;

public class QryStudyBean extends DataContainer implements DataContainerInterface,IQryStudyValue{

  private static String  m_boName = "com.ai.aris.server.cornerstone.bean.QryStudy";



  public final static  String S_Modality = "MODALITY";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyId = "STUDY_ID";
  public final static  String S_StudyDescription = "STUDY_DESCRIPTION";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_NumImages = "NUM_IMAGES";
  public final static  String S_StudyKey = "STUDY_KEY";
  public final static  String S_StudyDate = "STUDY_DATE";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryStudyBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
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

  public void initPatientId(String value){
     this.initProperty(S_PatientId,value);
  }
  public  void setPatientId(String value){
     this.set(S_PatientId,value);
  }
  public  void setPatientIdNull(){
     this.set(S_PatientId,null);
  }

  public String getPatientId(){
       return DataType.getAsString(this.get(S_PatientId));
  
  }
  public String getPatientIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientId));
      }

  public void initStudyId(String value){
     this.initProperty(S_StudyId,value);
  }
  public  void setStudyId(String value){
     this.set(S_StudyId,value);
  }
  public  void setStudyIdNull(){
     this.set(S_StudyId,null);
  }

  public String getStudyId(){
       return DataType.getAsString(this.get(S_StudyId));
  
  }
  public String getStudyIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyId));
      }

  public void initStudyDescription(String value){
     this.initProperty(S_StudyDescription,value);
  }
  public  void setStudyDescription(String value){
     this.set(S_StudyDescription,value);
  }
  public  void setStudyDescriptionNull(){
     this.set(S_StudyDescription,null);
  }

  public String getStudyDescription(){
       return DataType.getAsString(this.get(S_StudyDescription));
  
  }
  public String getStudyDescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDescription));
      }

  public void initPatientName(String value){
     this.initProperty(S_PatientName,value);
  }
  public  void setPatientName(String value){
     this.set(S_PatientName,value);
  }
  public  void setPatientNameNull(){
     this.set(S_PatientName,null);
  }

  public String getPatientName(){
       return DataType.getAsString(this.get(S_PatientName));
  
  }
  public String getPatientNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientName));
      }

  public void initNumImages(long value){
     this.initProperty(S_NumImages,new Long(value));
  }
  public  void setNumImages(long value){
     this.set(S_NumImages,new Long(value));
  }
  public  void setNumImagesNull(){
     this.set(S_NumImages,null);
  }

  public long getNumImages(){
        return DataType.getAsLong(this.get(S_NumImages));
  
  }
  public long getNumImagesInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_NumImages));
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

  public void initStudyDate(String value){
     this.initProperty(S_StudyDate,value);
  }
  public  void setStudyDate(String value){
     this.set(S_StudyDate,value);
  }
  public  void setStudyDateNull(){
     this.set(S_StudyDate,null);
  }

  public String getStudyDate(){
       return DataType.getAsString(this.get(S_StudyDate));
  
  }
  public String getStudyDateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyDate));
      }


 
 }

