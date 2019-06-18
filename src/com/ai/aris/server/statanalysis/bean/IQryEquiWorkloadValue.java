package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryEquiWorkloadValue extends DataStructInterface{

  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_CStudyDatetime = "C_STUDY_DATETIME";
  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_ModalityId = "MODALITY_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_Zb = "ZB";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_OrgId = "ORG_ID";


public long getReportIspositive();

public String getCStudyDatetime();

public long getStudyitemNumber();

public String getEquipmentDesc();

public long getModalityId();

public long getLocId();

public String getZb();

public long getStudyitemPrice();

public String getOrgId();


public  void setReportIspositive(long value);

public  void setCStudyDatetime(String value);

public  void setStudyitemNumber(long value);

public  void setEquipmentDesc(String value);

public  void setModalityId(long value);

public  void setLocId(long value);

public  void setZb(String value);

public  void setStudyitemPrice(long value);

public  void setOrgId(String value);
}
