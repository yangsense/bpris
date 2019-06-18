package com.ai.aris.server.webservice.bean;

/**
 * 房间信息同步
 * Created by lenovo on 2016/12/19.
 */
public class AiscRoomData {
    private String roomDesc ;
    private long roomId ;
    private long locId ;
    private long roomEnabled;
    private String roomEndesc;
    private long orgId ;

    public long getLocId() {
        return locId;
    }

    public void setLocId(long locId) {
        this.locId = locId;
    }

    public long getOrgId() {
        return orgId;
    }

    public void setOrgId(long orgId) {
        this.orgId = orgId;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc;
    }

    public long getRoomEnabled() {
        return roomEnabled;
    }

    public void setRoomEnabled(long roomEnabled) {
        this.roomEnabled = roomEnabled;
    }

    public String getRoomEndesc() {
        return roomEndesc;
    }

    public void setRoomEndesc(String roomEndesc) {
        this.roomEndesc = roomEndesc;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }
}
