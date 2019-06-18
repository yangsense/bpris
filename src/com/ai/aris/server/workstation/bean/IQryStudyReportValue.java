package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryStudyReportValue extends DataStructInterface{

  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportDoctorname = "REPORT_DOCTORNAME";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportVerifydoctorname = "REPORT_VERIFYDOCTORNAME";


public long getStudyinfoId();

public String getReportDoctorname();

public String getReportResult();

public String getReportRemark();

public String getReportExam();

public Timestamp getReportDatetime();

public String getReportVerifydoctorname();


public  void setStudyinfoId(long value);

public  void setReportDoctorname(String value);

public  void setReportResult(String value);

public  void setReportRemark(String value);

public  void setReportExam(String value);

public  void setReportDatetime(Timestamp value);

public  void setReportVerifydoctorname(String value);
}
