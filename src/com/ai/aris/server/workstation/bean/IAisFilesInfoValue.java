package com.ai.aris.server.workstation.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAisFilesInfoValue extends DataStructInterface{

  public final static  String S_FilePrinted = "FILE_PRINTED";
  public final static  String S_FileDesc = "FILE_DESC";
  public final static  String S_FileType = "FILE_TYPE";
  public final static  String S_StudyinfoId = "STUDYINFO_ID";
  public final static  String S_MiId = "MI_ID";
  public final static  String S_FileId = "FILE_ID";
  public final static  String S_FileArchive = "FILE_ARCHIVE";
  public final static  String S_FileSize = "FILE_SIZE";
  public final static  String S_FilePath = "FILE_PATH";


public int getFilePrinted();

public String getFileDesc();

public String getFileType();

public long getStudyinfoId();

public long getMiId();

public long getFileId();

public int getFileArchive();

public float getFileSize();

public String getFilePath();


public  void setFilePrinted(int value);

public  void setFileDesc(String value);

public  void setFileType(String value);

public  void setStudyinfoId(long value);

public  void setMiId(long value);

public  void setFileId(long value);

public  void setFileArchive(int value);

public  void setFileSize(float value);

public  void setFilePath(String value);
}
