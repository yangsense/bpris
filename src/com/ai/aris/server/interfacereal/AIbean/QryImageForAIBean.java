package com.ai.aris.server.interfacereal.AIbean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.interfacereal.AIbean.*;

public class QryImageForAIBean extends DataContainer implements DataContainerInterface,IQryImageForAIValue{

  private static String  m_boName = "com.ai.aris.server.interfacereal.AIbean.QryImageForAI";



  public final static  String S_Eventseconds = "EVENTSECONDS";
  public final static  String S_Sopclass = "SOPCLASS";
  public final static  String S_Seriesuid = "SERIESUID";
  public final static  String S_Studyuid = "STUDYUID";
  public final static  String S_Sopinstanceuid = "SOPINSTANCEUID";
  public final static  String S_Eventtime = "EVENTTIME";
  public final static  String S_Dcmrogserieid = "DCMROGSERIEID";
  public final static  String S_Completed0 = "COMPLETED_0";
  public final static  String S_Completed = "COMPLETED";
  public final static  String S_Completed1 = "COMPLETED_1";
  public final static  String S_Completed2 = "COMPLETED_2";
  public final static  String S_Completed3 = "COMPLETED_3";
  public final static  String S_Completed7 = "COMPLETED_7";
  public final static  String S_Completed6 = "COMPLETED_6";
  public final static  String S_Completed5 = "COMPLETED_5";
  public final static  String S_Completed4 = "COMPLETED_4";
  public final static  String S_Completed9 = "COMPLETED_9";
  public final static  String S_Completed8 = "COMPLETED_8";
  public final static  String S_Calledae = "CALLEDAE";
  public final static  String S_Modality = "MODALITY";
  public final static  String S_Eventdate = "EVENTDATE";
  public final static  String S_Dcmrogfilename = "DCMROGFILENAME";
  public final static  String S_Patientid = "PATIENTID";
  public final static  String S_Callingae = "CALLINGAE";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryImageForAIBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initEventseconds(String value){
     this.initProperty(S_Eventseconds,value);
  }
  public  void setEventseconds(String value){
     this.set(S_Eventseconds,value);
  }
  public  void setEventsecondsNull(){
     this.set(S_Eventseconds,null);
  }

  public String getEventseconds(){
       return DataType.getAsString(this.get(S_Eventseconds));
  
  }
  public String getEventsecondsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Eventseconds));
      }

  public void initSopclass(String value){
     this.initProperty(S_Sopclass,value);
  }
  public  void setSopclass(String value){
     this.set(S_Sopclass,value);
  }
  public  void setSopclassNull(){
     this.set(S_Sopclass,null);
  }

  public String getSopclass(){
       return DataType.getAsString(this.get(S_Sopclass));
  
  }
  public String getSopclassInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sopclass));
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

  public void initStudyuid(String value){
     this.initProperty(S_Studyuid,value);
  }
  public  void setStudyuid(String value){
     this.set(S_Studyuid,value);
  }
  public  void setStudyuidNull(){
     this.set(S_Studyuid,null);
  }

  public String getStudyuid(){
       return DataType.getAsString(this.get(S_Studyuid));
  
  }
  public String getStudyuidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studyuid));
      }

  public void initSopinstanceuid(String value){
     this.initProperty(S_Sopinstanceuid,value);
  }
  public  void setSopinstanceuid(String value){
     this.set(S_Sopinstanceuid,value);
  }
  public  void setSopinstanceuidNull(){
     this.set(S_Sopinstanceuid,null);
  }

  public String getSopinstanceuid(){
       return DataType.getAsString(this.get(S_Sopinstanceuid));
  
  }
  public String getSopinstanceuidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Sopinstanceuid));
      }

  public void initEventtime(String value){
     this.initProperty(S_Eventtime,value);
  }
  public  void setEventtime(String value){
     this.set(S_Eventtime,value);
  }
  public  void setEventtimeNull(){
     this.set(S_Eventtime,null);
  }

  public String getEventtime(){
       return DataType.getAsString(this.get(S_Eventtime));
  
  }
  public String getEventtimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Eventtime));
      }

  public void initDcmrogserieid(long value){
     this.initProperty(S_Dcmrogserieid,new Long(value));
  }
  public  void setDcmrogserieid(long value){
     this.set(S_Dcmrogserieid,new Long(value));
  }
  public  void setDcmrogserieidNull(){
     this.set(S_Dcmrogserieid,null);
  }

  public long getDcmrogserieid(){
        return DataType.getAsLong(this.get(S_Dcmrogserieid));
  
  }
  public long getDcmrogserieidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Dcmrogserieid));
      }

  public void initCompleted0(String value){
     this.initProperty(S_Completed0,value);
  }
  public  void setCompleted0(String value){
     this.set(S_Completed0,value);
  }
  public  void setCompleted0Null(){
     this.set(S_Completed0,null);
  }

  public String getCompleted0(){
       return DataType.getAsString(this.get(S_Completed0));
  
  }
  public String getCompleted0InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed0));
      }

  public void initCompleted(String value){
     this.initProperty(S_Completed,value);
  }
  public  void setCompleted(String value){
     this.set(S_Completed,value);
  }
  public  void setCompletedNull(){
     this.set(S_Completed,null);
  }

  public String getCompleted(){
       return DataType.getAsString(this.get(S_Completed));
  
  }
  public String getCompletedInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed));
      }

  public void initCompleted1(String value){
     this.initProperty(S_Completed1,value);
  }
  public  void setCompleted1(String value){
     this.set(S_Completed1,value);
  }
  public  void setCompleted1Null(){
     this.set(S_Completed1,null);
  }

  public String getCompleted1(){
       return DataType.getAsString(this.get(S_Completed1));
  
  }
  public String getCompleted1InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed1));
      }

  public void initCompleted2(String value){
     this.initProperty(S_Completed2,value);
  }
  public  void setCompleted2(String value){
     this.set(S_Completed2,value);
  }
  public  void setCompleted2Null(){
     this.set(S_Completed2,null);
  }

  public String getCompleted2(){
       return DataType.getAsString(this.get(S_Completed2));
  
  }
  public String getCompleted2InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed2));
      }

  public void initCompleted3(String value){
     this.initProperty(S_Completed3,value);
  }
  public  void setCompleted3(String value){
     this.set(S_Completed3,value);
  }
  public  void setCompleted3Null(){
     this.set(S_Completed3,null);
  }

  public String getCompleted3(){
       return DataType.getAsString(this.get(S_Completed3));
  
  }
  public String getCompleted3InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed3));
      }

  public void initCompleted7(String value){
     this.initProperty(S_Completed7,value);
  }
  public  void setCompleted7(String value){
     this.set(S_Completed7,value);
  }
  public  void setCompleted7Null(){
     this.set(S_Completed7,null);
  }

  public String getCompleted7(){
       return DataType.getAsString(this.get(S_Completed7));
  
  }
  public String getCompleted7InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed7));
      }

  public void initCompleted6(String value){
     this.initProperty(S_Completed6,value);
  }
  public  void setCompleted6(String value){
     this.set(S_Completed6,value);
  }
  public  void setCompleted6Null(){
     this.set(S_Completed6,null);
  }

  public String getCompleted6(){
       return DataType.getAsString(this.get(S_Completed6));
  
  }
  public String getCompleted6InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed6));
      }

  public void initCompleted5(String value){
     this.initProperty(S_Completed5,value);
  }
  public  void setCompleted5(String value){
     this.set(S_Completed5,value);
  }
  public  void setCompleted5Null(){
     this.set(S_Completed5,null);
  }

  public String getCompleted5(){
       return DataType.getAsString(this.get(S_Completed5));
  
  }
  public String getCompleted5InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed5));
      }

  public void initCompleted4(String value){
     this.initProperty(S_Completed4,value);
  }
  public  void setCompleted4(String value){
     this.set(S_Completed4,value);
  }
  public  void setCompleted4Null(){
     this.set(S_Completed4,null);
  }

  public String getCompleted4(){
       return DataType.getAsString(this.get(S_Completed4));
  
  }
  public String getCompleted4InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed4));
      }

  public void initCompleted9(String value){
     this.initProperty(S_Completed9,value);
  }
  public  void setCompleted9(String value){
     this.set(S_Completed9,value);
  }
  public  void setCompleted9Null(){
     this.set(S_Completed9,null);
  }

  public String getCompleted9(){
       return DataType.getAsString(this.get(S_Completed9));
  
  }
  public String getCompleted9InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed9));
      }

  public void initCompleted8(String value){
     this.initProperty(S_Completed8,value);
  }
  public  void setCompleted8(String value){
     this.set(S_Completed8,value);
  }
  public  void setCompleted8Null(){
     this.set(S_Completed8,null);
  }

  public String getCompleted8(){
       return DataType.getAsString(this.get(S_Completed8));
  
  }
  public String getCompleted8InitialValue(){
        return DataType.getAsString(this.getOldObj(S_Completed8));
      }

  public void initCalledae(String value){
     this.initProperty(S_Calledae,value);
  }
  public  void setCalledae(String value){
     this.set(S_Calledae,value);
  }
  public  void setCalledaeNull(){
     this.set(S_Calledae,null);
  }

  public String getCalledae(){
       return DataType.getAsString(this.get(S_Calledae));
  
  }
  public String getCalledaeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Calledae));
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

  public void initEventdate(String value){
     this.initProperty(S_Eventdate,value);
  }
  public  void setEventdate(String value){
     this.set(S_Eventdate,value);
  }
  public  void setEventdateNull(){
     this.set(S_Eventdate,null);
  }

  public String getEventdate(){
       return DataType.getAsString(this.get(S_Eventdate));
  
  }
  public String getEventdateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Eventdate));
      }

  public void initDcmrogfilename(String value){
     this.initProperty(S_Dcmrogfilename,value);
  }
  public  void setDcmrogfilename(String value){
     this.set(S_Dcmrogfilename,value);
  }
  public  void setDcmrogfilenameNull(){
     this.set(S_Dcmrogfilename,null);
  }

  public String getDcmrogfilename(){
       return DataType.getAsString(this.get(S_Dcmrogfilename));
  
  }
  public String getDcmrogfilenameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Dcmrogfilename));
      }

  public void initPatientid(String value){
     this.initProperty(S_Patientid,value);
  }
  public  void setPatientid(String value){
     this.set(S_Patientid,value);
  }
  public  void setPatientidNull(){
     this.set(S_Patientid,null);
  }

  public String getPatientid(){
       return DataType.getAsString(this.get(S_Patientid));
  
  }
  public String getPatientidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Patientid));
      }

  public void initCallingae(String value){
     this.initProperty(S_Callingae,value);
  }
  public  void setCallingae(String value){
     this.set(S_Callingae,value);
  }
  public  void setCallingaeNull(){
     this.set(S_Callingae,null);
  }

  public String getCallingae(){
       return DataType.getAsString(this.get(S_Callingae));
  
  }
  public String getCallingaeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Callingae));
      }

  public void initAccessionnumber(String value){
     this.initProperty(S_Accessionnumber,value);
  }
  public  void setAccessionnumber(String value){
     this.set(S_Accessionnumber,value);
  }
  public  void setAccessionnumberNull(){
     this.set(S_Accessionnumber,null);
  }

  public String getAccessionnumber(){
       return DataType.getAsString(this.get(S_Accessionnumber));
  
  }
  public String getAccessionnumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Accessionnumber));
      }


 
 }

