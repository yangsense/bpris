package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryReportFileValue extends DataStructInterface{

  public final static  String S_ServerPassword = "SERVER_PASSWORD";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_ServerPort = "SERVER_PORT";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_FileId = "FILE_ID";
  public final static  String S_ServerUser = "SERVER_USER";
  public final static  String S_Status = "STATUS";
  public final static  String S_ServerType = "SERVER_TYPE";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";
  public final static  String S_ServerName = "SERVER_NAME";
  public final static  String S_ServerIp = "SERVER_IP";
  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_ServerEnabled = "SERVER_ENABLED";
  public final static  String S_FilePath = "FILE_PATH";
  public final static  String S_ReportId = "REPORT_ID";


public String getServerPassword();

public long getMediumEnabled();

public long getServerPort();

public String getMediumPath();

public long getFileId();

public String getServerUser();

public String getStatus();

public long getServerType();

public long getMediumReminesize();

public String getServerName();

public String getServerIp();

public long getMediumType();

public long getMediumIsonline();

public long getStudyinfoId();

public long getMediumId();

public long getMediumIsfull();

public long getServerId();

public long getMediumSize();

public long getServerEnabled();

public String getFilePath();

public long getReportId();


public  void setServerPassword(String value);

public  void setMediumEnabled(long value);

public  void setServerPort(long value);

public  void setMediumPath(String value);

public  void setFileId(long value);

public  void setServerUser(String value);

public  void setStatus(String value);

public  void setServerType(long value);

public  void setMediumReminesize(long value);

public  void setServerName(String value);

public  void setServerIp(String value);

public  void setMediumType(long value);

public  void setMediumIsonline(long value);

public  void setStudyinfoId(long value);

public  void setMediumId(long value);

public  void setMediumIsfull(long value);

public  void setServerId(long value);

public  void setMediumSize(long value);

public  void setServerEnabled(long value);

public  void setFilePath(String value);

public  void setReportId(long value);
}
