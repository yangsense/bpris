package com.ai.aris.server.webservice.bean.realtime;

public class PatientResponse {
	private String code;

	private String cenGlobalID;

	private String message;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public void setCenGlobalID(String cenGlobalID) {
		this.cenGlobalID = cenGlobalID;
	}

	public String getCenGlobalID() {
		return this.cenGlobalID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
