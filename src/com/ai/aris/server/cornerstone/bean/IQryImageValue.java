package com.ai.aris.server.cornerstone.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IQryImageValue extends DataStructInterface{

  public final static  String S_ImageId = "IMAGE_ID";
  public final static  String S_ImageUid = "IMAGE_UID";
  public final static  String S_ImageKey = "IMAGE_KEY";
  public final static  String S_SeriesKey = "SERIES_KEY";


public String getImageId();

public String getImageUid();

public long getImageKey();

public long getSeriesKey();


public  void setImageId(String value);

public  void setImageUid(String value);

public  void setImageKey(long value);

public  void setSeriesKey(long value);
}
