package com.ai.aris.server.workstation.bean;

import java.sql.*;
import com.ai.appframe2.bo.DataContainer;
import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.AIException;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.common.ObjectType;
import com.ai.appframe2.common.DataType;

import com.ai.aris.server.workstation.bean.*;

public class AisModalityworklistBean extends DataContainer implements DataContainerInterface,IAisModalityworklistValue{

  private static String  m_boName = "com.ai.aris.server.workstation.bean.AisModalityworklist";



  public final static  String S_PatientSex = "PATIENT_SEX";
  public final static  String S_ScheduledProcedureStepDate = "SCHEDULED_PROCEDURE_STEP_DATE";
  public final static  String S_PatientAlerts = "PATIENT_ALERTS";
  public final static  String S_SpecialNeeds = "SPECIAL_NEEDS";
  public final static  String S_PatientBirthdate = "PATIENT_BIRTHDATE";
  public final static  String S_Premedication = "PREMEDICATION";
  public final static  String S_ConfidentialityConstraint = "CONFIDENTIALITY_CONSTRAINT";
  public final static  String S_PatientWeigth = "PATIENT_WEIGTH";
  public final static  String S_PatientSize = "PATIENT_SIZE";
  public final static  String S_PatientState = "PATIENT_STATE";
  public final static  String S_Status = "STATUS";
  public final static  String S_PatientAddress = "PATIENT_ADDRESS";
  public final static  String S_ScheduledStationAeTitle = "SCHEDULED_STATION_AE_TITLE";
  public final static  String S_ScheduledProcedureStepId = "SCHEDULED_PROCEDURE_STEP_ID";
  public final static  String S_ScheduledProceduredescription = "SCHEDULED_PROCEDUREDESCRIPTION";
  public final static  String S_ScheduledPerformingPhysical = "SCHEDULED_PERFORMING_PHYSICAL";
  public final static  String S_PatientHistory = "PATIENT_HISTORY";
  public final static  String S_RequestingService = "REQUESTING_SERVICE";
  public final static  String S_RequestedProcedureLocation = "REQUESTED_PROCEDURE_LOCATION";
  public final static  String S_Allergies = "ALLERGIES";
  public final static  String S_RequestedProcedureId = "REQUESTED_PROCEDURE_ID";
  public final static  String S_PatientTransportArrangem = "PATIENT_TRANSPORT_ARRANGEM";
  public final static  String S_AdmissionId = "ADMISSION_ID";
  public final static  String S_InstitutionName = "INSTITUTION_NAME";
  public final static  String S_PatientAge = "PATIENT_AGE";
  public final static  String S_ScheduledProcedureLocation = "SCHEDULED_PROCEDURE_LOCATION";
  public final static  String S_RequestingPhysician = "REQUESTING_PHYSICIAN";
  public final static  String S_RequestedContrastAgent = "REQUESTED_CONTRAST_AGENT";
  public final static  String S_CurrentPatientLocation = "CURRENT_PATIENT_LOCATION";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_ScheduledStationName = "SCHEDULED_STATION_NAME";
  public final static  String S_PregnancyStatus = "PREGNANCY_STATUS";
  public final static  String S_ReferringPhysicianName = "REFERRING_PHYSICIAN_NAME";
  public final static  String S_ConfidentialityCode = "CONFIDENTIALITY_CODE";
  public final static  String S_OrgId = "ORG_ID";
  public final static  String S_Wmlid = "WMLID";
  public final static  String S_Modality = "MODALITY";
  public final static  String S_RequestedProcedurePriori = "REQUESTED_PROCEDURE_PRIORI";
  public final static  String S_AccessionNumber = "ACCESSION_NUMBER";
  public final static  String S_StudyInstanceUid = "STUDY_INSTANCE_UID";
  public final static  String S_RequestedProceduredescription = "REQUESTED_PROCEDUREDESCRIPTION";
  public final static  String S_PatientName = "PATIENT_NAME";

  public static ObjectType S_TYPE = null;
  static{
    try {
      S_TYPE = ServiceManager.getObjectTypeFactory().getInstance(m_boName);
    }catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  public AisModalityworklistBean() throws AIException{
      super(S_TYPE);
  }

 public static ObjectType getObjectTypeStatic() throws AIException{
   return S_TYPE;
 }

 public void setObjectType(ObjectType  value) throws AIException{
   throw new AIException("此种数据容器不能重置业务对象类型");
 }


  public void initPatientSex(String value){
     this.initProperty(S_PatientSex,value);
  }
  public  void setPatientSex(String value){
     this.set(S_PatientSex,value);
  }
  public  void setPatientSexNull(){
     this.set(S_PatientSex,null);
  }

  public String getPatientSex(){
       return DataType.getAsString(this.get(S_PatientSex));
  
  }
  public String getPatientSexInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientSex));
      }

  public void initScheduledProcedureStepDate(Timestamp value){
     this.initProperty(S_ScheduledProcedureStepDate,value);
  }
  public  void setScheduledProcedureStepDate(Timestamp value){
     this.set(S_ScheduledProcedureStepDate,value);
  }
  public  void setScheduledProcedureStepDateNull(){
     this.set(S_ScheduledProcedureStepDate,null);
  }

  public Timestamp getScheduledProcedureStepDate(){
        return DataType.getAsDateTime(this.get(S_ScheduledProcedureStepDate));
  
  }
  public Timestamp getScheduledProcedureStepDateInitialValue(){
        return DataType.getAsDateTime(this.getOldObj(S_ScheduledProcedureStepDate));
      }

  public void initPatientAlerts(String value){
     this.initProperty(S_PatientAlerts,value);
  }
  public  void setPatientAlerts(String value){
     this.set(S_PatientAlerts,value);
  }
  public  void setPatientAlertsNull(){
     this.set(S_PatientAlerts,null);
  }

  public String getPatientAlerts(){
       return DataType.getAsString(this.get(S_PatientAlerts));
  
  }
  public String getPatientAlertsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAlerts));
      }

  public void initSpecialNeeds(String value){
     this.initProperty(S_SpecialNeeds,value);
  }
  public  void setSpecialNeeds(String value){
     this.set(S_SpecialNeeds,value);
  }
  public  void setSpecialNeedsNull(){
     this.set(S_SpecialNeeds,null);
  }

  public String getSpecialNeeds(){
       return DataType.getAsString(this.get(S_SpecialNeeds));
  
  }
  public String getSpecialNeedsInitialValue(){
        return DataType.getAsString(this.getOldObj(S_SpecialNeeds));
      }

  public void initPatientBirthdate(String value){
     this.initProperty(S_PatientBirthdate,value);
  }
  public  void setPatientBirthdate(String value){
     this.set(S_PatientBirthdate,value);
  }
  public  void setPatientBirthdateNull(){
     this.set(S_PatientBirthdate,null);
  }

  public String getPatientBirthdate(){
       return DataType.getAsString(this.get(S_PatientBirthdate));
  
  }
  public String getPatientBirthdateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientBirthdate));
      }

  public void initPremedication(String value){
     this.initProperty(S_Premedication,value);
  }
  public  void setPremedication(String value){
     this.set(S_Premedication,value);
  }
  public  void setPremedicationNull(){
     this.set(S_Premedication,null);
  }

  public String getPremedication(){
       return DataType.getAsString(this.get(S_Premedication));
  
  }
  public String getPremedicationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Premedication));
      }

  public void initConfidentialityConstraint(String value){
     this.initProperty(S_ConfidentialityConstraint,value);
  }
  public  void setConfidentialityConstraint(String value){
     this.set(S_ConfidentialityConstraint,value);
  }
  public  void setConfidentialityConstraintNull(){
     this.set(S_ConfidentialityConstraint,null);
  }

  public String getConfidentialityConstraint(){
       return DataType.getAsString(this.get(S_ConfidentialityConstraint));
  
  }
  public String getConfidentialityConstraintInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ConfidentialityConstraint));
      }

  public void initPatientWeigth(String value){
     this.initProperty(S_PatientWeigth,value);
  }
  public  void setPatientWeigth(String value){
     this.set(S_PatientWeigth,value);
  }
  public  void setPatientWeigthNull(){
     this.set(S_PatientWeigth,null);
  }

  public String getPatientWeigth(){
       return DataType.getAsString(this.get(S_PatientWeigth));
  
  }
  public String getPatientWeigthInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientWeigth));
      }

  public void initPatientSize(String value){
     this.initProperty(S_PatientSize,value);
  }
  public  void setPatientSize(String value){
     this.set(S_PatientSize,value);
  }
  public  void setPatientSizeNull(){
     this.set(S_PatientSize,null);
  }

  public String getPatientSize(){
       return DataType.getAsString(this.get(S_PatientSize));
  
  }
  public String getPatientSizeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientSize));
      }

  public void initPatientState(String value){
     this.initProperty(S_PatientState,value);
  }
  public  void setPatientState(String value){
     this.set(S_PatientState,value);
  }
  public  void setPatientStateNull(){
     this.set(S_PatientState,null);
  }

  public String getPatientState(){
       return DataType.getAsString(this.get(S_PatientState));
  
  }
  public String getPatientStateInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientState));
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

  public void initPatientAddress(String value){
     this.initProperty(S_PatientAddress,value);
  }
  public  void setPatientAddress(String value){
     this.set(S_PatientAddress,value);
  }
  public  void setPatientAddressNull(){
     this.set(S_PatientAddress,null);
  }

  public String getPatientAddress(){
       return DataType.getAsString(this.get(S_PatientAddress));
  
  }
  public String getPatientAddressInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAddress));
      }

  public void initScheduledStationAeTitle(String value){
     this.initProperty(S_ScheduledStationAeTitle,value);
  }
  public  void setScheduledStationAeTitle(String value){
     this.set(S_ScheduledStationAeTitle,value);
  }
  public  void setScheduledStationAeTitleNull(){
     this.set(S_ScheduledStationAeTitle,null);
  }

  public String getScheduledStationAeTitle(){
       return DataType.getAsString(this.get(S_ScheduledStationAeTitle));
  
  }
  public String getScheduledStationAeTitleInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledStationAeTitle));
      }

  public void initScheduledProcedureStepId(String value){
     this.initProperty(S_ScheduledProcedureStepId,value);
  }
  public  void setScheduledProcedureStepId(String value){
     this.set(S_ScheduledProcedureStepId,value);
  }
  public  void setScheduledProcedureStepIdNull(){
     this.set(S_ScheduledProcedureStepId,null);
  }

  public String getScheduledProcedureStepId(){
       return DataType.getAsString(this.get(S_ScheduledProcedureStepId));
  
  }
  public String getScheduledProcedureStepIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledProcedureStepId));
      }

  public void initScheduledProceduredescription(String value){
     this.initProperty(S_ScheduledProceduredescription,value);
  }
  public  void setScheduledProceduredescription(String value){
     this.set(S_ScheduledProceduredescription,value);
  }
  public  void setScheduledProceduredescriptionNull(){
     this.set(S_ScheduledProceduredescription,null);
  }

  public String getScheduledProceduredescription(){
       return DataType.getAsString(this.get(S_ScheduledProceduredescription));
  
  }
  public String getScheduledProceduredescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledProceduredescription));
      }

  public void initScheduledPerformingPhysical(String value){
     this.initProperty(S_ScheduledPerformingPhysical,value);
  }
  public  void setScheduledPerformingPhysical(String value){
     this.set(S_ScheduledPerformingPhysical,value);
  }
  public  void setScheduledPerformingPhysicalNull(){
     this.set(S_ScheduledPerformingPhysical,null);
  }

  public String getScheduledPerformingPhysical(){
       return DataType.getAsString(this.get(S_ScheduledPerformingPhysical));
  
  }
  public String getScheduledPerformingPhysicalInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledPerformingPhysical));
      }

  public void initPatientHistory(String value){
     this.initProperty(S_PatientHistory,value);
  }
  public  void setPatientHistory(String value){
     this.set(S_PatientHistory,value);
  }
  public  void setPatientHistoryNull(){
     this.set(S_PatientHistory,null);
  }

  public String getPatientHistory(){
       return DataType.getAsString(this.get(S_PatientHistory));
  
  }
  public String getPatientHistoryInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientHistory));
      }

  public void initRequestingService(String value){
     this.initProperty(S_RequestingService,value);
  }
  public  void setRequestingService(String value){
     this.set(S_RequestingService,value);
  }
  public  void setRequestingServiceNull(){
     this.set(S_RequestingService,null);
  }

  public String getRequestingService(){
       return DataType.getAsString(this.get(S_RequestingService));
  
  }
  public String getRequestingServiceInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestingService));
      }

  public void initRequestedProcedureLocation(String value){
     this.initProperty(S_RequestedProcedureLocation,value);
  }
  public  void setRequestedProcedureLocation(String value){
     this.set(S_RequestedProcedureLocation,value);
  }
  public  void setRequestedProcedureLocationNull(){
     this.set(S_RequestedProcedureLocation,null);
  }

  public String getRequestedProcedureLocation(){
       return DataType.getAsString(this.get(S_RequestedProcedureLocation));
  
  }
  public String getRequestedProcedureLocationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestedProcedureLocation));
      }

  public void initAllergies(String value){
     this.initProperty(S_Allergies,value);
  }
  public  void setAllergies(String value){
     this.set(S_Allergies,value);
  }
  public  void setAllergiesNull(){
     this.set(S_Allergies,null);
  }

  public String getAllergies(){
       return DataType.getAsString(this.get(S_Allergies));
  
  }
  public String getAllergiesInitialValue(){
        return DataType.getAsString(this.getOldObj(S_Allergies));
      }

  public void initRequestedProcedureId(String value){
     this.initProperty(S_RequestedProcedureId,value);
  }
  public  void setRequestedProcedureId(String value){
     this.set(S_RequestedProcedureId,value);
  }
  public  void setRequestedProcedureIdNull(){
     this.set(S_RequestedProcedureId,null);
  }

  public String getRequestedProcedureId(){
       return DataType.getAsString(this.get(S_RequestedProcedureId));
  
  }
  public String getRequestedProcedureIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestedProcedureId));
      }

  public void initPatientTransportArrangem(String value){
     this.initProperty(S_PatientTransportArrangem,value);
  }
  public  void setPatientTransportArrangem(String value){
     this.set(S_PatientTransportArrangem,value);
  }
  public  void setPatientTransportArrangemNull(){
     this.set(S_PatientTransportArrangem,null);
  }

  public String getPatientTransportArrangem(){
       return DataType.getAsString(this.get(S_PatientTransportArrangem));
  
  }
  public String getPatientTransportArrangemInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientTransportArrangem));
      }

  public void initAdmissionId(String value){
     this.initProperty(S_AdmissionId,value);
  }
  public  void setAdmissionId(String value){
     this.set(S_AdmissionId,value);
  }
  public  void setAdmissionIdNull(){
     this.set(S_AdmissionId,null);
  }

  public String getAdmissionId(){
       return DataType.getAsString(this.get(S_AdmissionId));
  
  }
  public String getAdmissionIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AdmissionId));
      }

  public void initInstitutionName(String value){
     this.initProperty(S_InstitutionName,value);
  }
  public  void setInstitutionName(String value){
     this.set(S_InstitutionName,value);
  }
  public  void setInstitutionNameNull(){
     this.set(S_InstitutionName,null);
  }

  public String getInstitutionName(){
       return DataType.getAsString(this.get(S_InstitutionName));
  
  }
  public String getInstitutionNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_InstitutionName));
      }

  public void initPatientAge(String value){
     this.initProperty(S_PatientAge,value);
  }
  public  void setPatientAge(String value){
     this.set(S_PatientAge,value);
  }
  public  void setPatientAgeNull(){
     this.set(S_PatientAge,null);
  }

  public String getPatientAge(){
       return DataType.getAsString(this.get(S_PatientAge));
  
  }
  public String getPatientAgeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_PatientAge));
      }

  public void initScheduledProcedureLocation(String value){
     this.initProperty(S_ScheduledProcedureLocation,value);
  }
  public  void setScheduledProcedureLocation(String value){
     this.set(S_ScheduledProcedureLocation,value);
  }
  public  void setScheduledProcedureLocationNull(){
     this.set(S_ScheduledProcedureLocation,null);
  }

  public String getScheduledProcedureLocation(){
       return DataType.getAsString(this.get(S_ScheduledProcedureLocation));
  
  }
  public String getScheduledProcedureLocationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledProcedureLocation));
      }

  public void initRequestingPhysician(String value){
     this.initProperty(S_RequestingPhysician,value);
  }
  public  void setRequestingPhysician(String value){
     this.set(S_RequestingPhysician,value);
  }
  public  void setRequestingPhysicianNull(){
     this.set(S_RequestingPhysician,null);
  }

  public String getRequestingPhysician(){
       return DataType.getAsString(this.get(S_RequestingPhysician));
  
  }
  public String getRequestingPhysicianInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestingPhysician));
      }

  public void initRequestedContrastAgent(String value){
     this.initProperty(S_RequestedContrastAgent,value);
  }
  public  void setRequestedContrastAgent(String value){
     this.set(S_RequestedContrastAgent,value);
  }
  public  void setRequestedContrastAgentNull(){
     this.set(S_RequestedContrastAgent,null);
  }

  public String getRequestedContrastAgent(){
       return DataType.getAsString(this.get(S_RequestedContrastAgent));
  
  }
  public String getRequestedContrastAgentInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestedContrastAgent));
      }

  public void initCurrentPatientLocation(String value){
     this.initProperty(S_CurrentPatientLocation,value);
  }
  public  void setCurrentPatientLocation(String value){
     this.set(S_CurrentPatientLocation,value);
  }
  public  void setCurrentPatientLocationNull(){
     this.set(S_CurrentPatientLocation,null);
  }

  public String getCurrentPatientLocation(){
       return DataType.getAsString(this.get(S_CurrentPatientLocation));
  
  }
  public String getCurrentPatientLocationInitialValue(){
        return DataType.getAsString(this.getOldObj(S_CurrentPatientLocation));
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

  public void initScheduledStationName(String value){
     this.initProperty(S_ScheduledStationName,value);
  }
  public  void setScheduledStationName(String value){
     this.set(S_ScheduledStationName,value);
  }
  public  void setScheduledStationNameNull(){
     this.set(S_ScheduledStationName,null);
  }

  public String getScheduledStationName(){
       return DataType.getAsString(this.get(S_ScheduledStationName));
  
  }
  public String getScheduledStationNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ScheduledStationName));
      }

  public void initPregnancyStatus(int value){
     this.initProperty(S_PregnancyStatus,new Integer(value));
  }
  public  void setPregnancyStatus(int value){
     this.set(S_PregnancyStatus,new Integer(value));
  }
  public  void setPregnancyStatusNull(){
     this.set(S_PregnancyStatus,null);
  }

  public int getPregnancyStatus(){
        return DataType.getAsInt(this.get(S_PregnancyStatus));
  
  }
  public int getPregnancyStatusInitialValue(){
        return DataType.getAsInt(this.getOldObj(S_PregnancyStatus));
      }

  public void initReferringPhysicianName(String value){
     this.initProperty(S_ReferringPhysicianName,value);
  }
  public  void setReferringPhysicianName(String value){
     this.set(S_ReferringPhysicianName,value);
  }
  public  void setReferringPhysicianNameNull(){
     this.set(S_ReferringPhysicianName,null);
  }

  public String getReferringPhysicianName(){
       return DataType.getAsString(this.get(S_ReferringPhysicianName));
  
  }
  public String getReferringPhysicianNameInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ReferringPhysicianName));
      }

  public void initConfidentialityCode(String value){
     this.initProperty(S_ConfidentialityCode,value);
  }
  public  void setConfidentialityCode(String value){
     this.set(S_ConfidentialityCode,value);
  }
  public  void setConfidentialityCodeNull(){
     this.set(S_ConfidentialityCode,null);
  }

  public String getConfidentialityCode(){
       return DataType.getAsString(this.get(S_ConfidentialityCode));
  
  }
  public String getConfidentialityCodeInitialValue(){
        return DataType.getAsString(this.getOldObj(S_ConfidentialityCode));
      }

  public void initOrgId(String value){
     this.initProperty(S_OrgId,value);
  }
  public  void setOrgId(String value){
     this.set(S_OrgId,value);
  }
  public  void setOrgIdNull(){
     this.set(S_OrgId,null);
  }

  public String getOrgId(){
       return DataType.getAsString(this.get(S_OrgId));
  
  }
  public String getOrgIdInitialValue(){
        return DataType.getAsString(this.getOldObj(S_OrgId));
      }

  public void initWmlid(long value){
     this.initProperty(S_Wmlid,new Long(value));
  }
  public  void setWmlid(long value){
     this.set(S_Wmlid,new Long(value));
  }
  public  void setWmlidNull(){
     this.set(S_Wmlid,null);
  }

  public long getWmlid(){
        return DataType.getAsLong(this.get(S_Wmlid));
  
  }
  public long getWmlidInitialValue(){
        return DataType.getAsLong(this.getOldObj(S_Wmlid));
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

  public void initRequestedProcedurePriori(String value){
     this.initProperty(S_RequestedProcedurePriori,value);
  }
  public  void setRequestedProcedurePriori(String value){
     this.set(S_RequestedProcedurePriori,value);
  }
  public  void setRequestedProcedurePrioriNull(){
     this.set(S_RequestedProcedurePriori,null);
  }

  public String getRequestedProcedurePriori(){
       return DataType.getAsString(this.get(S_RequestedProcedurePriori));
  
  }
  public String getRequestedProcedurePrioriInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestedProcedurePriori));
      }

  public void initAccessionNumber(String value){
     this.initProperty(S_AccessionNumber,value);
  }
  public  void setAccessionNumber(String value){
     this.set(S_AccessionNumber,value);
  }
  public  void setAccessionNumberNull(){
     this.set(S_AccessionNumber,null);
  }

  public String getAccessionNumber(){
       return DataType.getAsString(this.get(S_AccessionNumber));
  
  }
  public String getAccessionNumberInitialValue(){
        return DataType.getAsString(this.getOldObj(S_AccessionNumber));
      }

  public void initStudyInstanceUid(String value){
     this.initProperty(S_StudyInstanceUid,value);
  }
  public  void setStudyInstanceUid(String value){
     this.set(S_StudyInstanceUid,value);
  }
  public  void setStudyInstanceUidNull(){
     this.set(S_StudyInstanceUid,null);
  }

  public String getStudyInstanceUid(){
       return DataType.getAsString(this.get(S_StudyInstanceUid));
  
  }
  public String getStudyInstanceUidInitialValue(){
        return DataType.getAsString(this.getOldObj(S_StudyInstanceUid));
      }

  public void initRequestedProceduredescription(String value){
     this.initProperty(S_RequestedProceduredescription,value);
  }
  public  void setRequestedProceduredescription(String value){
     this.set(S_RequestedProceduredescription,value);
  }
  public  void setRequestedProceduredescriptionNull(){
     this.set(S_RequestedProceduredescription,null);
  }

  public String getRequestedProceduredescription(){
       return DataType.getAsString(this.get(S_RequestedProceduredescription));
  
  }
  public String getRequestedProceduredescriptionInitialValue(){
        return DataType.getAsString(this.getOldObj(S_RequestedProceduredescription));
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


 
 }

