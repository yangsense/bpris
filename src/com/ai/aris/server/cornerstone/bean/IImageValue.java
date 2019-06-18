package com.ai.aris.server.cornerstone.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IImageValue extends DataStructInterface{

  public final static  String S_Columns = "COLUMNS";
  public final static  String S_Graphattr = "GRAPHATTR";
  public final static  String S_Sopclass = "SOPCLASS";
  public final static  String S_Contenttime = "CONTENTTIME";
  public final static  String S_Transforms = "TRANSFORMS";
  public final static  String S_Archived = "ARCHIVED";
  public final static  String S_Bitsallocated = "BITSALLOCATED";
  public final static  String S_Filename = "FILENAME";
  public final static  String S_Imagenumber = "IMAGENUMBER";
  public final static  String S_Bitsstored = "BITSSTORED";
  public final static  String S_Contentdate = "CONTENTDATE";
  public final static  String S_Highbit = "HIGHBIT";
  public final static  String S_Rows = "ROWS";
  public final static  String S_Pixelrepresentation = "PIXELREPRESENTATION";
  public final static  String S_Numberofframes = "NUMBEROFFRAMES";
  public final static  String S_Photometricinterpretation = "PHOTOMETRICINTERPRETATION";
  public final static  String S_Disknumber = "DISKNUMBER";
  public final static  String S_Serieskey = "SERIESKEY";
  public final static  String S_Pixpm = "PIXPM";
  public final static  String S_Samplesperpixel = "SAMPLESPERPIXEL";
  public final static  String S_Imageuid = "IMAGEUID";
  public final static  String S_Imagekey = "IMAGEKEY";
  public final static  String S_Presence = "PRESENCE";


public long getColumns();

public String getGraphattr();

public String getSopclass();

public String getContenttime();

public String getTransforms();

public String getArchived();

public long getBitsallocated();

public String getFilename();

public long getImagenumber();

public long getBitsstored();

public String getContentdate();

public long getHighbit();

public long getRows();

public long getPixelrepresentation();

public long getNumberofframes();

public String getPhotometricinterpretation();

public long getDisknumber();

public long getSerieskey();

public String getPixpm();

public long getSamplesperpixel();

public String getImageuid();

public long getImagekey();

public String getPresence();


public  void setColumns(long value);

public  void setGraphattr(String value);

public  void setSopclass(String value);

public  void setContenttime(String value);

public  void setTransforms(String value);

public  void setArchived(String value);

public  void setBitsallocated(long value);

public  void setFilename(String value);

public  void setImagenumber(long value);

public  void setBitsstored(long value);

public  void setContentdate(String value);

public  void setHighbit(long value);

public  void setRows(long value);

public  void setPixelrepresentation(long value);

public  void setNumberofframes(long value);

public  void setPhotometricinterpretation(String value);

public  void setDisknumber(long value);

public  void setSerieskey(long value);

public  void setPixpm(String value);

public  void setSamplesperpixel(long value);

public  void setImageuid(String value);

public  void setImagekey(long value);

public  void setPresence(String value);
}
