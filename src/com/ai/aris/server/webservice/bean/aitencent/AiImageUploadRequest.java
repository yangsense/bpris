package com.ai.aris.server.webservice.bean.aitencent;

public class AiImageUploadRequest {
	private String patientID;
	private String globalpatientID;
	private String accesionNumber;
	private String studyInstanceUid;
	private String index;
	private String zipContent;
	public String getPatientID() {
		return patientID;
	}
	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}
	public String getGlobalpatientID() {
		return globalpatientID;
	}
	public void setGlobalpatientID(String globalpatientID) {
		this.globalpatientID = globalpatientID;
	}
	public String getAccesionNumber() {
		return accesionNumber;
	}
	public void setAccesionNumber(String accesionNumber) {
		this.accesionNumber = accesionNumber;
	}
	public String getStudyInstanceUid() {
		return studyInstanceUid;
	}
	public void setStudyInstanceUid(String studyInstanceUid) {
		this.studyInstanceUid = studyInstanceUid;
	}
	public String getZipContent() {
		return zipContent;
	}
	public void setZipContent(String zipContent) {
		this.zipContent = zipContent;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
}
