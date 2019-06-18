package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisStudyReportValue extends DataStructInterface{

  public final static  String S_ReportIspositive = "REPORT_ISPOSITIVE";
  public final static  String S_ReportExammethod = "REPORT_EXAMMETHOD";
  public final static  String S_ReportDoctorid = "REPORT_DOCTORID";
  public final static  String S_ReportRecord = "REPORT_RECORD";
  public final static  String S_ReportIssent = "REPORT_ISSENT";
  public final static  String S_ReportFinaldatetime = "REPORT_FINALDATETIME";
  public final static  String S_ReportAcrcode = "REPORT_ACRCODE";
  public final static  String S_ReportLock = "REPORT_LOCK";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_ReportResult = "REPORT_RESULT";
  public final static  String S_ReportUncompletedreason = "REPORT_UNCOMPLETEDREASON";
  public final static  String S_ReportIscompleted = "REPORT_ISCOMPLETED";
  public final static  String S_ReportPrintdatetime = "REPORT_PRINTDATETIME";
  public final static  String S_ReportIsprinted = "REPORT_ISPRINTED";
  public final static  String S_ReportFinaldoctorid = "REPORT_FINALDOCTORID";
  public final static  String S_ReportIsvisible = "REPORT_ISVISIBLE";
  public final static  String S_ReportExam = "REPORT_EXAM";
  public final static  String S_ReportIcd10 = "REPORT_ICD10";
  public final static  String S_ReportVerifydatetime = "REPORT_VERIFYDATETIME";
  public final static  String S_ReportPrintcareprovid = "REPORT_PRINTCAREPROVID";
  public final static  String S_ReportRemark = "REPORT_REMARK";
  public final static  String S_ReportVerifydoctorid = "REPORT_VERIFYDOCTORID";
  public final static  String S_ReportDatetime = "REPORT_DATETIME";
  public final static  String S_ReportId = "REPORT_ID";
  public final static  String S_ReportToarchive = "REPORT_TOARCHIVE";


public int getReportIspositive();

public String getReportExammethod();

public String getReportDoctorid();

public String getReportRecord();

public int getReportIssent();

public Timestamp getReportFinaldatetime();

public String getReportAcrcode();

public int getReportLock();

public long getStudyinfoId();

public String getReportResult();

public String getReportUncompletedreason();

public int getReportIscompleted();

public Timestamp getReportPrintdatetime();

public int getReportIsprinted();

public String getReportFinaldoctorid();

public int getReportIsvisible();

public String getReportExam();

public String getReportIcd10();

public Timestamp getReportVerifydatetime();

public String getReportPrintcareprovid();

public String getReportRemark();

public String getReportVerifydoctorid();

public Timestamp getReportDatetime();

public long getReportId();

public int getReportToarchive();


public  void setReportIspositive(int value);

public  void setReportExammethod(String value);

public  void setReportDoctorid(String value);

public  void setReportRecord(String value);

public  void setReportIssent(int value);

public  void setReportFinaldatetime(Timestamp value);

public  void setReportAcrcode(String value);

public  void setReportLock(int value);

public  void setStudyinfoId(long value);

public  void setReportResult(String value);

public  void setReportUncompletedreason(String value);

public  void setReportIscompleted(int value);

public  void setReportPrintdatetime(Timestamp value);

public  void setReportIsprinted(int value);

public  void setReportFinaldoctorid(String value);

public  void setReportIsvisible(int value);

public  void setReportExam(String value);

public  void setReportIcd10(String value);

public  void setReportVerifydatetime(Timestamp value);

public  void setReportPrintcareprovid(String value);

public  void setReportRemark(String value);

public  void setReportVerifydoctorid(String value);

public  void setReportDatetime(Timestamp value);

public  void setReportId(long value);

public  void setReportToarchive(int value);
}
