package com.ai.aris.server.basecode.model;

public class AiscHisDatasourceModel {
	private Long sourceId;
	private String orgId;
	private String orgName;
	private String sourceUrl;
	private String sourceUser;
	private String sourcePassword;
	private String status;
	private String patientTypecode;
	
	public String getPatientTypecode() {
		return patientTypecode;
	}
	public void setPatientTypecode(String patientTypecode) {
		this.patientTypecode = patientTypecode;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getSourceUser() {
		return sourceUser;
	}
	public void setSourceUser(String sourceUser) {
		this.sourceUser = sourceUser;
	}
	public String getSourcePassword() {
		return sourcePassword;
	}
	public void setSourcePassword(String sourcePassword) {
		this.sourcePassword = sourcePassword;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
