package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class QryPacsInfoBean extends DataContainer implements DataContainerInterface,IQryPacsInfoValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.QryPacsInfo";



  public final static  String S_Modalitiesinstudy = "MODALITIESINSTUDY";
  public final static  String S_Patientid = "PATIENTID";
  public final static  String S_Receptiondate = "RECEPTIONDATE";
  public final static  String S_Patientkey = "PATIENTKEY";
  public final static  String S_Patientname = "PATIENTNAME";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public QryPacsInfoBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initModalitiesinstudy(String value){
     this.initProperty(S_Modalitiesinstudy,value);
  }
  public  void setModalitiesinstudy(String value){
     this.set(S_Modalitiesinstudy,value);
  }
  public  void setModalitiesinstudyNull(){
     this.set(S_Modalitiesinstudy,null);
  }

  public String getModalitiesinstudy(){
       return DataType.getAsString(this.get(S_Modalitiesinstudy));
  
  }
  public String getModalitiesinstudyInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Modalitiesinstudy));
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

  public void initReceptiondate(String value){
     this.initProperty(S_Receptiondate,value);
  }
  public  void setReceptiondate(String value){
     this.set(S_Receptiondate,value);
  }
  public  void setReceptiondateNull(){
     this.set(S_Receptiondate,null);
  }

  public String getReceptiondate(){
       return DataType.getAsString(this.get(S_Receptiondate));
  
  }
  public String getReceptiondateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Receptiondate));
      }

  public void initPatientkey(long value){
     this.initProperty(S_Patientkey,new Long(value));
  }
  public  void setPatientkey(long value){
     this.set(S_Patientkey,new Long(value));
  }
  public  void setPatientkeyNull(){
     this.set(S_Patientkey,null);
  }

  public long getPatientkey(){
        return DataType.getAsLong(this.get(S_Patientkey));
  
  }
  public long getPatientkeyInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Patientkey));
      }

  public void initPatientname(String value){
     this.initProperty(S_Patientname,value);
  }
  public  void setPatientname(String value){
     this.set(S_Patientname,value);
  }
  public  void setPatientnameNull(){
     this.set(S_Patientname,null);
  }

  public String getPatientname(){
       return DataType.getAsString(this.get(S_Patientname));
  
  }
  public String getPatientnameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Patientname));
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

