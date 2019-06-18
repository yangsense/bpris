package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class StudyBean extends DataContainer implements DataContainerInterface,IStudyValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.Study";



  public final static  String S_Modalitiesinstudy = "MODALITIESINSTUDY";
  public final static  String S_Receptiontime = "RECEPTIONTIME";
  public final static  String S_Verifiedstatus = "VERIFIEDSTATUS";
  public final static  String S_Studykey = "STUDYKEY";
  public final static  String S_Studyuid = "STUDYUID";
  public final static  String S_Studyid = "STUDYID";
  public final static  String S_Hasrisinfo = "HASRISINFO";
  public final static  String S_Referringhospital = "REFERRINGHOSPITAL";
  public final static  String S_Diagnosedstatus = "DIAGNOSEDSTATUS";
  public final static  String S_Studydate = "STUDYDATE";
  public final static  String S_Radiologist = "RADIOLOGIST";
  public final static  String S_Studypriority = "STUDYPRIORITY";
  public final static  String S_Institutionid = "INSTITUTIONID";
  public final static  String S_Procedureid = "PROCEDUREID";
  public final static  String S_Studydescription = "STUDYDESCRIPTION";
  public final static  String S_Receptiondate = "RECEPTIONDATE";
  public final static  String S_Patientkey = "PATIENTKEY";
  public final static  String S_Protocol = "PROTOCOL";
  public final static  String S_Referringdoctor = "REFERRINGDOCTOR";
  public final static  String S_Verifiederror = "VERIFIEDERROR";
  public final static  String S_Studytime = "STUDYTIME";
  public final static  String S_Accessionnumber = "ACCESSIONNUMBER";
  public final static  String S_Referringdepartment = "REFERRINGDEPARTMENT";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public StudyBean() throws AIException{
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

  public void initReceptiontime(String value){
     this.initProperty(S_Receptiontime,value);
  }
  public  void setReceptiontime(String value){
     this.set(S_Receptiontime,value);
  }
  public  void setReceptiontimeNull(){
     this.set(S_Receptiontime,null);
  }

  public String getReceptiontime(){
       return DataType.getAsString(this.get(S_Receptiontime));
  
  }
  public String getReceptiontimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Receptiontime));
      }

  public void initVerifiedstatus(String value){
     this.initProperty(S_Verifiedstatus,value);
  }
  public  void setVerifiedstatus(String value){
     this.set(S_Verifiedstatus,value);
  }
  public  void setVerifiedstatusNull(){
     this.set(S_Verifiedstatus,null);
  }

  public String getVerifiedstatus(){
       return DataType.getAsString(this.get(S_Verifiedstatus));
  
  }
  public String getVerifiedstatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Verifiedstatus));
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

  public void initStudyid(String value){
     this.initProperty(S_Studyid,value);
  }
  public  void setStudyid(String value){
     this.set(S_Studyid,value);
  }
  public  void setStudyidNull(){
     this.set(S_Studyid,null);
  }

  public String getStudyid(){
       return DataType.getAsString(this.get(S_Studyid));
  
  }
  public String getStudyidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studyid));
      }

  public void initHasrisinfo(String value){
     this.initProperty(S_Hasrisinfo,value);
  }
  public  void setHasrisinfo(String value){
     this.set(S_Hasrisinfo,value);
  }
  public  void setHasrisinfoNull(){
     this.set(S_Hasrisinfo,null);
  }

  public String getHasrisinfo(){
       return DataType.getAsString(this.get(S_Hasrisinfo));
  
  }
  public String getHasrisinfoInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Hasrisinfo));
      }

  public void initReferringhospital(String value){
     this.initProperty(S_Referringhospital,value);
  }
  public  void setReferringhospital(String value){
     this.set(S_Referringhospital,value);
  }
  public  void setReferringhospitalNull(){
     this.set(S_Referringhospital,null);
  }

  public String getReferringhospital(){
       return DataType.getAsString(this.get(S_Referringhospital));
  
  }
  public String getReferringhospitalInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Referringhospital));
      }

  public void initDiagnosedstatus(String value){
     this.initProperty(S_Diagnosedstatus,value);
  }
  public  void setDiagnosedstatus(String value){
     this.set(S_Diagnosedstatus,value);
  }
  public  void setDiagnosedstatusNull(){
     this.set(S_Diagnosedstatus,null);
  }

  public String getDiagnosedstatus(){
       return DataType.getAsString(this.get(S_Diagnosedstatus));
  
  }
  public String getDiagnosedstatusInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Diagnosedstatus));
      }

  public void initStudydate(String value){
     this.initProperty(S_Studydate,value);
  }
  public  void setStudydate(String value){
     this.set(S_Studydate,value);
  }
  public  void setStudydateNull(){
     this.set(S_Studydate,null);
  }

  public String getStudydate(){
       return DataType.getAsString(this.get(S_Studydate));
  
  }
  public String getStudydateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studydate));
      }

  public void initRadiologist(String value){
     this.initProperty(S_Radiologist,value);
  }
  public  void setRadiologist(String value){
     this.set(S_Radiologist,value);
  }
  public  void setRadiologistNull(){
     this.set(S_Radiologist,null);
  }

  public String getRadiologist(){
       return DataType.getAsString(this.get(S_Radiologist));
  
  }
  public String getRadiologistInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Radiologist));
      }

  public void initStudypriority(String value){
     this.initProperty(S_Studypriority,value);
  }
  public  void setStudypriority(String value){
     this.set(S_Studypriority,value);
  }
  public  void setStudypriorityNull(){
     this.set(S_Studypriority,null);
  }

  public String getStudypriority(){
       return DataType.getAsString(this.get(S_Studypriority));
  
  }
  public String getStudypriorityInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studypriority));
      }

  public void initInstitutionid(String value){
     this.initProperty(S_Institutionid,value);
  }
  public  void setInstitutionid(String value){
     this.set(S_Institutionid,value);
  }
  public  void setInstitutionidNull(){
     this.set(S_Institutionid,null);
  }

  public String getInstitutionid(){
       return DataType.getAsString(this.get(S_Institutionid));
  
  }
  public String getInstitutionidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Institutionid));
      }

  public void initProcedureid(String value){
     this.initProperty(S_Procedureid,value);
  }
  public  void setProcedureid(String value){
     this.set(S_Procedureid,value);
  }
  public  void setProcedureidNull(){
     this.set(S_Procedureid,null);
  }

  public String getProcedureid(){
       return DataType.getAsString(this.get(S_Procedureid));
  
  }
  public String getProcedureidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Procedureid));
      }

  public void initStudydescription(String value){
     this.initProperty(S_Studydescription,value);
  }
  public  void setStudydescription(String value){
     this.set(S_Studydescription,value);
  }
  public  void setStudydescriptionNull(){
     this.set(S_Studydescription,null);
  }

  public String getStudydescription(){
       return DataType.getAsString(this.get(S_Studydescription));
  
  }
  public String getStudydescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studydescription));
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

  public void initProtocol(String value){
     this.initProperty(S_Protocol,value);
  }
  public  void setProtocol(String value){
     this.set(S_Protocol,value);
  }
  public  void setProtocolNull(){
     this.set(S_Protocol,null);
  }

  public String getProtocol(){
       return DataType.getAsString(this.get(S_Protocol));
  
  }
  public String getProtocolInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Protocol));
      }

  public void initReferringdoctor(String value){
     this.initProperty(S_Referringdoctor,value);
  }
  public  void setReferringdoctor(String value){
     this.set(S_Referringdoctor,value);
  }
  public  void setReferringdoctorNull(){
     this.set(S_Referringdoctor,null);
  }

  public String getReferringdoctor(){
       return DataType.getAsString(this.get(S_Referringdoctor));
  
  }
  public String getReferringdoctorInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Referringdoctor));
      }

  public void initVerifiederror(String value){
     this.initProperty(S_Verifiederror,value);
  }
  public  void setVerifiederror(String value){
     this.set(S_Verifiederror,value);
  }
  public  void setVerifiederrorNull(){
     this.set(S_Verifiederror,null);
  }

  public String getVerifiederror(){
       return DataType.getAsString(this.get(S_Verifiederror));
  
  }
  public String getVerifiederrorInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Verifiederror));
      }

  public void initStudytime(String value){
     this.initProperty(S_Studytime,value);
  }
  public  void setStudytime(String value){
     this.set(S_Studytime,value);
  }
  public  void setStudytimeNull(){
     this.set(S_Studytime,null);
  }

  public String getStudytime(){
       return DataType.getAsString(this.get(S_Studytime));
  
  }
  public String getStudytimeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Studytime));
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

  public void initReferringdepartment(String value){
     this.initProperty(S_Referringdepartment,value);
  }
  public  void setReferringdepartment(String value){
     this.set(S_Referringdepartment,value);
  }
  public  void setReferringdepartmentNull(){
     this.set(S_Referringdepartment,null);
  }

  public String getReferringdepartment(){
       return DataType.getAsString(this.get(S_Referringdepartment));
  
  }
  public String getReferringdepartmentInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Referringdepartment));
      }


 
 }

