package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisModalityworklistValue extends DataStructInterface{

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


public String getPatientSex();

public Timestamp getScheduledProcedureStepDate();

public String getPatientAlerts();

public String getSpecialNeeds();

public String getPatientBirthdate();

public String getPremedication();

public String getConfidentialityConstraint();

public String getPatientWeigth();

public String getPatientSize();

public String getPatientState();

public String getStatus();

public String getPatientAddress();

public String getScheduledStationAeTitle();

public String getScheduledProcedureStepId();

public String getScheduledProceduredescription();

public String getScheduledPerformingPhysical();

public String getPatientHistory();

public String getRequestingService();

public String getRequestedProcedureLocation();

public String getAllergies();

public String getRequestedProcedureId();

public String getPatientTransportArrangem();

public String getAdmissionId();

public String getInstitutionName();

public String getPatientAge();

public String getScheduledProcedureLocation();

public String getRequestingPhysician();

public String getRequestedContrastAgent();

public String getCurrentPatientLocation();

public String getPatientId();

public String getScheduledStationName();

public int getPregnancyStatus();

public String getReferringPhysicianName();

public String getConfidentialityCode();

public String getOrgId();

public long getWmlid();

public String getModality();

public String getRequestedProcedurePriori();

public String getAccessionNumber();

public String getStudyInstanceUid();

public String getRequestedProceduredescription();

public String getPatientName();


public  void setPatientSex(String value);

public  void setScheduledProcedureStepDate(Timestamp value);

public  void setPatientAlerts(String value);

public  void setSpecialNeeds(String value);

public  void setPatientBirthdate(String value);

public  void setPremedication(String value);

public  void setConfidentialityConstraint(String value);

public  void setPatientWeigth(String value);

public  void setPatientSize(String value);

public  void setPatientState(String value);

public  void setStatus(String value);

public  void setPatientAddress(String value);

public  void setScheduledStationAeTitle(String value);

public  void setScheduledProcedureStepId(String value);

public  void setScheduledProceduredescription(String value);

public  void setScheduledPerformingPhysical(String value);

public  void setPatientHistory(String value);

public  void setRequestingService(String value);

public  void setRequestedProcedureLocation(String value);

public  void setAllergies(String value);

public  void setRequestedProcedureId(String value);

public  void setPatientTransportArrangem(String value);

public  void setAdmissionId(String value);

public  void setInstitutionName(String value);

public  void setPatientAge(String value);

public  void setScheduledProcedureLocation(String value);

public  void setRequestingPhysician(String value);

public  void setRequestedContrastAgent(String value);

public  void setCurrentPatientLocation(String value);

public  void setPatientId(String value);

public  void setScheduledStationName(String value);

public  void setPregnancyStatus(int value);

public  void setReferringPhysicianName(String value);

public  void setConfidentialityCode(String value);

public  void setOrgId(String value);

public  void setWmlid(long value);

public  void setModality(String value);

public  void setRequestedProcedurePriori(String value);

public  void setAccessionNumber(String value);

public  void setStudyInstanceUid(String value);

public  void setRequestedProceduredescription(String value);

public  void setPatientName(String value);
}
