package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisReportFilesValue extends DataStructInterface{

  public final static  String S_FileId = "FILE_ID";
  public final static  String S_Status = "STATUS";
  public final static  String S_ReportfileId = "REPORTFILE_ID";
  public final static  String S_ReportId = "REPORT_ID";


public long getFileId();

public String getStatus();

public long getReportfileId();

public long getReportId();


public  void setFileId(long value);

public  void setStatus(String value);

public  void setReportfileId(long value);

public  void setReportId(long value);
}
