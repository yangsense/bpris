package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscBoardUserValue extends DataStructInterface{

  public final static  String S_UserId = "USER_ID";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_UserName = "USER_NAME";
  public final static  String S_UserCode = "USER_CODE";
  public final static  String S_AuthTimme = "AUTH_TIMME";
  public final static  String S_Status = "STATUS";
  public final static  String S_AuthLevel = "AUTH_LEVEL";
  public final static  String S_OrgId = "ORG_ID";


public long getUserId();

public long getRoomId();

public Timestamp getCreateTime();

public String getUserName();

public String getUserCode();

public Timestamp getAuthTimme();

public String getStatus();

public String getAuthLevel();

public String getOrgId();


public  void setUserId(long value);

public  void setRoomId(long value);

public  void setCreateTime(Timestamp value);

public  void setUserName(String value);

public  void setUserCode(String value);

public  void setAuthTimme(Timestamp value);

public  void setStatus(String value);

public  void setAuthLevel(String value);

public  void setOrgId(String value);
}
