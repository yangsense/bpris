package com.ai.aris.server.interfacereal.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscRoomRealValue extends DataStructInterface{

  public final static  String S_OldRoomId = "OLD_ROOM_ID";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_CreateTime = "CREATE_TIME";
  public final static  String S_RoomRealId = "ROOM_REAL_ID";
  public final static  String S_OrgId = "ORG_ID";


public long getOldRoomId();

public long getRoomId();

public Timestamp getCreateTime();

public long getRoomRealId();

public String getOrgId();


public  void setOldRoomId(long value);

public  void setRoomId(long value);

public  void setCreateTime(Timestamp value);

public  void setRoomRealId(long value);

public  void setOrgId(String value);
}
