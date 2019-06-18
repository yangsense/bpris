package com.ai.aris.server.webservice.bean.realtime;

public class StudyinfoResponse {
	private String code;

	private String cenStudyinfoID;

	private String message;

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public String getCenStudyinfoID() {
		return cenStudyinfoID;
	}

	public void setCenStudyinfoID(String cenStudyinfoID) {
		this.cenStudyinfoID = cenStudyinfoID;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
