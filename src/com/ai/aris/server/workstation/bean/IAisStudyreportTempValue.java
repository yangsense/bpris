package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyreportTempValue extends DataStructInterface{

  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";


public String getStudyinfoId();

public String getReportResult();

public String getReportRemark();

public String getReportExam();

public Timestamp getReportDatetime();


public  void setStudyinfoId(String value);

public  void setReportResult(String value);

public  void setReportRemark(String value);

public  void setReportExam(String value);

public  void setReportDatetime(Timestamp value);
}
