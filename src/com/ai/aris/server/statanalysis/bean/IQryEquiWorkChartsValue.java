package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryEquiWorkChartsValue extends DataStructInterface{

  public final static  String S_StudyitemNumber = "STUDYITEM_NUMBER";
  public final static  String S_EquipmentDesc = "EQUIPMENT_DESC";
  public final static  String S_CareprovName = "CAREPROV_NAME";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_ReportId = "REPORT_ID";


public long getStudyitemNumber();

public String getEquipmentDesc();

public String getCareprovName();

public long getStudyitemPrice();

public long getReportId();


public  void setStudyitemNumber(long value);

public  void setEquipmentDesc(String value);

public  void setCareprovName(String value);

public  void setStudyitemPrice(long value);

public  void setReportId(long value);
}
