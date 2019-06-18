package com.ai.aris.server.cornerstone.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyValue extends DataStructInterface{

  public final static  String S_Modality = "MODALITY";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_StudyId = "STUDY_ID";
  public final static  String S_StudyDescription = "STUDY_DESCRIPTION";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_NumImages = "NUM_IMAGES";
  public final static  String S_StudyKey = "STUDY_KEY";
  public final static  String S_StudyDate = "STUDY_DATE";


public String getModality();

public String getPatientId();

public String getStudyId();

public String getStudyDescription();

public String getPatientName();

public long getNumImages();

public long getStudyKey();

public String getStudyDate();


public  void setModality(String value);

public  void setPatientId(String value);

public  void setStudyId(String value);

public  void setStudyDescription(String value);

public  void setPatientName(String value);

public  void setNumImages(long value);

public  void setStudyKey(long value);

public  void setStudyDate(String value);
}
