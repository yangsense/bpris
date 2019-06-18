package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscBoardroomValue extends DataStructInterface{

  public final static  String S_Maxusercount = "MAXUSERCOUNT";
  public final static  String S_Verifymode = "VERIFYMODE";
  public final static  String S_Keycode = "KEYCODE";
  public final static  String S_BoardroomName = "BOARDROOM_NAME";
  public final static  String S_BoardroomId = "BOARDROOM_ID";
  public final static  String S_Password = "PASSWORD";
  public final static  String S_Enablechairpwd = "ENABLECHAIRPWD";
  public final static  String S_Chairpassword = "CHAIRPASSWORD";


public long getMaxusercount();

public String getVerifymode();

public String getKeycode();

public String getBoardroomName();

public long getBoardroomId();

public String getPassword();

public String getEnablechairpwd();

public String getChairpassword();


public  void setMaxusercount(long value);

public  void setVerifymode(String value);

public  void setKeycode(String value);

public  void setBoardroomName(String value);

public  void setBoardroomId(long value);

public  void setPassword(String value);

public  void setEnablechairpwd(String value);

public  void setChairpassword(String value);
}
