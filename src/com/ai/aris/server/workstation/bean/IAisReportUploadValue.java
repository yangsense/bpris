package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisReportUploadValue extends DataStructInterface{

  public final static  String S_Uploadtime = "UPLOADTIME";
  public final static  String S_PatientName = "PATIENT_NAME";
  public final static  String S_Id = "ID";
  public final static  String S_PatientId = "PATIENT_ID";
  public final static  String S_OperatorCode = "OPERATOR_CODE";
  public final static  String S_FileName = "FILE_NAME";
  public final static  String S_OperatorName = "OPERATOR_NAME";
  public final static  String S_ReportId = "REPORT_ID";


public Timestamp getUploadtime();

public String getPatientName();

public long getId();

public String getPatientId();

public String getOperatorCode();

public String getFileName();

public String getOperatorName();

public long getReportId();


public  void setUploadtime(Timestamp value);

public  void setPatientName(String value);

public  void setId(long value);

public  void setPatientId(String value);

public  void setOperatorCode(String value);

public  void setFileName(String value);

public  void setOperatorName(String value);

public  void setReportId(long value);
}
