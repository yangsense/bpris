package com.ai.aris.server.cornerstone.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface ISeriesValue extends DataStructInterface{

  public final static  String S_Seriesnumber = "SERIESNUMBER";
  public final static  String S_Modality = "MODALITY";
  public final static  String S_Bodypart = "BODYPART";
  public final static  String S_Seriesuid = "SERIESUID";
  public final static  String S_Remarks = "REMARKS";
  public final static  String S_Stationname = "STATIONNAME";
  public final static  String S_Studykey = "STUDYKEY";
  public final static  String S_Serieskey = "SERIESKEY";


public String getSeriesnumber();

public String getModality();

public String getBodypart();

public String getSeriesuid();

public String getRemarks();

public String getStationname();

public long getStudykey();

public long getSerieskey();


public  void setSeriesnumber(String value);

public  void setModality(String value);

public  void setBodypart(String value);

public  void setSeriesuid(String value);

public  void setRemarks(String value);

public  void setStationname(String value);

public  void setStudykey(long value);

public  void setSerieskey(long value);
}
