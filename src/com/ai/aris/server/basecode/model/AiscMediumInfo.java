package com.ai.aris.server.basecode.model;

public class AiscMediumInfo {
	private Long mediumId;
	private String mediumName;
	private String mediumPath;
	private Long serverId;
	private long mediumIsfull;
	private long mediumIsonline;
	private long mediumSize;
	private long mediumReminesize;
	private long mediumType;
	private long mediumEnabled;
	private String mediumTypeDesc;
	private String serverName;

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public Long getMediumId() {
		return mediumId;
	}

	public void setMediumId(Long mediumId) {
		this.mediumId = mediumId;
	}

	public String getMediumName() {
		return mediumName;
	}

	public void setMediumName(String mediumName) {
		this.mediumName = mediumName;
	}

	public String getMediumPath() {
		return mediumPath;
	}

	public void setMediumPath(String mediumPath) {
		this.mediumPath = mediumPath;
	}

	public Long getServerId() {
		return serverId;
	}

	public void setServerId(Long serverId) {
		this.serverId = serverId;
	}

	public long getMediumIsfull() {
		return mediumIsfull;
	}

	public void setMediumIsfull(long mediumIsfull) {
		this.mediumIsfull = mediumIsfull;
	}

	public long getMediumIsonline() {
		return mediumIsonline;
	}

	public void setMediumIsonline(long mediumIsonline) {
		this.mediumIsonline = mediumIsonline;
	}

	public long getMediumSize() {
		return mediumSize;
	}

	public void setMediumSize(long mediumSize) {
		this.mediumSize = mediumSize;
	}

	public long getMediumReminesize() {
		return mediumReminesize;
	}

	public void setMediumReminesize(long mediumReminesize) {
		this.mediumReminesize = mediumReminesize;
	}

	public long getMediumType() {
		return mediumType;
	}

	public void setMediumType(long mediumType) {
		this.mediumType = mediumType;
	}

	public long getMediumEnabled() {
		return mediumEnabled;
	}

	public void setMediumEnabled(long mediumEnabled) {
		this.mediumEnabled = mediumEnabled;
	}

	public String getMediumTypeDesc() {
		return mediumTypeDesc;
	}

	public void setMediumTypeDesc(String mediumTypeDesc) {
		this.mediumTypeDesc = mediumTypeDesc;
	}
	
}
