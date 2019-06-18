package com.ai.aris.server.statanalysis.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryDoctorWorkloadValue extends DataStructInterface{

  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_StudystatusCode = "STUDYSTATUS_CODE";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_ReportDoctorid = "REPORT_DOCTORID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_ReportDate = "REPORT_DATE";
  public final static  String S_Zb = "ZB";
  public final static  String S_StudyitemPrice = "STUDYITEM_PRICE";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";
  public final static  String S_ReportId = "REPORT_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getReportIspositive();

public String getStudystatusCode();

public String getReportDoctorname();

public String getReportDoctorid();

public long getLocId();

public String getReportDate();

public String getZb();

public long getStudyitemPrice();

public String getReportVerifydoctorid();

public String getReportVerifydoctorname();

public long getReportId();

public String getOrgId();


public  void setReportIspositive(long value);

public  void setStudystatusCode(String value);

public  void setReportDoctorname(String value);

public  void setReportDoctorid(String value);

public  void setLocId(long value);

public  void setReportDate(String value);

public  void setZb(String value);

public  void setStudyitemPrice(long value);

public  void setReportVerifydoctorid(String value);

public  void setReportVerifydoctorname(String value);

public  void setReportId(long value);

public  void setOrgId(String value);
}
