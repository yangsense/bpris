package com.ai.aris.server.cornerstone.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQrySeriesValue extends DataStructInterface{

  public final static  String S_SeriesNumber = "SERIES_NUMBER";
  public final static  String S_SeriesDescription = "SERIES_DESCRIPTION";
  public final static  String S_StudyKey = "STUDY_KEY";
  public final static  String S_SeriesUid = "SERIES_UID";
  public final static  String S_SeriesKey = "SERIES_KEY";


public String getSeriesNumber();

public String getSeriesDescription();

public long getStudyKey();

public String getSeriesUid();

public long getSeriesKey();


public  void setSeriesNumber(String value);

public  void setSeriesDescription(String value);

public  void setStudyKey(long value);

public  void setSeriesUid(String value);

public  void setSeriesKey(long value);
}
