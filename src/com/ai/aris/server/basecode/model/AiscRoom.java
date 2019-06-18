package com.ai.aris.server.basecode.model;

public class AiscRoom {
	private Long roomId;
	private Long locId;
	private String roomDesc;
	private String roomEndesc;
	private long roomEnabled;
	private long orgId;
	
	private String orgName;
	private String locName;
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public Long getRoomId() {
		return roomId;
	}
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}
	public Long getLocId() {
		return locId;
	}
	public void setLocId(Long locId) {
		this.locId = locId;
	}
	public String getRoomDesc() {
		return roomDesc;
	}
	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}
	public String getRoomEndesc() {
		return roomEndesc;
	}
	public void setRoomEndesc(String roomEndesc) {
		this.roomEndesc = roomEndesc;
	}
	public long getRoomEnabled() {
		return roomEnabled;
	}
	public void setRoomEnabled(long roomEnabled) {
		this.roomEnabled = roomEnabled;
	}
	public long getOrgId() {
		return orgId;
	}
	public void setOrgId(long orgId) {
		this.orgId = orgId;
	}

}
