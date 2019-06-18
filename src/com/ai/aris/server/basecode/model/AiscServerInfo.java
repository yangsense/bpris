package com.ai.aris.server.basecode.model;

public class AiscServerInfo {
	private Long serverId;
	private String serverName;
	private String serverIp;
	private long serverPort;
	private long serverType;
	private String serverUser;
	private String serverPassword;
	private long serverEnabled;
	
	private String serverTypeDesc;

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public long getServerPort() {
		return serverPort;
	}

	public void setServerPort(long serverPort) {
		this.serverPort = serverPort;
	}

	public long getServerType() {
		return serverType;
	}

	public void setServerType(long serverType) {
		this.serverType = serverType;
	}

	public String getServerUser() {
		return serverUser;
	}

	public void setServerUser(String serverUser) {
		this.serverUser = serverUser;
	}

	public String getServerPassword() {
		return serverPassword;
	}

	public void setServerPassword(String serverPassword) {
		this.serverPassword = serverPassword;
	}

	public long getServerEnabled() {
		return serverEnabled;
	}

	public void setServerEnabled(long serverEnabled) {
		this.serverEnabled = serverEnabled;
	}

	public String getServerTypeDesc() {
		return serverTypeDesc;
	}

	public void setServerTypeDesc(String serverTypeDesc) {
		this.serverTypeDesc = serverTypeDesc;
	}
	
}
