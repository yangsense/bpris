package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscMediumInfoValue extends DataStructInterface{

  public final static  String S_MediumType = "MEDIUM_TYPE";
  public final static  String S_MediumEnabled = "MEDIUM_ENABLED";
  public final static  String S_MediumPath = "MEDIUM_PATH";
  public final static  String S_MediumIsonline = "MEDIUM_ISONLINE";
  public final static  String S_MediumId = "MEDIUM_ID";
  public final static  String S_MediumIsfull = "MEDIUM_ISFULL";
  public final static  String S_ServerId = "SERVER_ID";
  public final static  String S_MediumSize = "MEDIUM_SIZE";
  public final static  String S_MediumName = "MEDIUM_NAME";
  public final static  String S_MediumReminesize = "MEDIUM_REMINESIZE";


public int getMediumType();

public int getMediumEnabled();

public String getMediumPath();

public int getMediumIsonline();

public long getMediumId();

public int getMediumIsfull();

public long getServerId();

public long getMediumSize();

public String getMediumName();

public long getMediumReminesize();


public  void setMediumType(int value);

public  void setMediumEnabled(int value);

public  void setMediumPath(String value);

public  void setMediumIsonline(int value);

public  void setMediumId(long value);

public  void setMediumIsfull(int value);

public  void setServerId(long value);

public  void setMediumSize(long value);

public  void setMediumName(String value);

public  void setMediumReminesize(long value);
}
