package com.ai.aris.server.basecode.bean;
import com.ai.appframe2.common.DataStructInterface;
import java.sql.Timestamp;
public interface IAiscRoomValue extends DataStructInterface{

  public final static  String S_RoomDesc = "ROOM_DESC";
  public final static  String S_RoomId = "ROOM_ID";
  public final static  String S_LocId = "LOC_ID";
  public final static  String S_RoomEnabled = "ROOM_ENABLED";
  public final static  String S_RoomEndesc = "ROOM_ENDESC";
  public final static  String S_OrgId = "ORG_ID";


public String getRoomDesc();

public long getRoomId();

public long getLocId();

public int getRoomEnabled();

public String getRoomEndesc();

public long getOrgId();


public  void setRoomDesc(String value);

public  void setRoomId(long value);

public  void setLocId(long value);

public  void setRoomEnabled(int value);

public  void setRoomEndesc(String value);

public  void setOrgId(long value);
}
