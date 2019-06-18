package com.ai.aris.server.statanalysis.model;

public class QryStudyInfoModel {
	private Long studyinfoId;
	private String patienttypeCode;
	private String patientInpatientid;
	private String studyAppdoc;
	private String studyBedno;
	private String studyAccnumber;
	private String studyItemdesc;
	private String studyDatetimeDesc;
	private String patientName;
	private String patientSex;
	private String patientAge;
	private String bodyitem;
	private String locDesc;
	private long reportIspositive;

	public long getReportIspositive() {
		return reportIspositive;
	}

	public void setReportIspositive(long reportIspositive) {
		this.reportIspositive = reportIspositive;
	}
	public Long getStudyinfoId() {
		return studyinfoId;
	}
	public void setStudyinfoId(Long studyinfoId) {
		this.studyinfoId = studyinfoId;
	}
	public String getPatienttypeCode() {
		return patienttypeCode;
	}
	public void setPatienttypeCode(String patienttypeCode) {
		this.patienttypeCode = patienttypeCode;
	}
	public String getPatientInpatientid() {
		return patientInpatientid;
	}
	public void setPatientInpatientid(String patientInpatientid) {
		this.patientInpatientid = patientInpatientid;
	}
	public String getStudyAppdoc() {
		return studyAppdoc;
	}
	public void setStudyAppdoc(String studyAppdoc) {
		this.studyAppdoc = studyAppdoc;
	}
	public String getStudyBedno() {
		return studyBedno;
	}
	public void setStudyBedno(String studyBedno) {
		this.studyBedno = studyBedno;
	}
	public String getStudyAccnumber() {
		return studyAccnumber;
	}
	public void setStudyAccnumber(String studyAccnumber) {
		this.studyAccnumber = studyAccnumber;
	}
	public String getStudyItemdesc() {
		return studyItemdesc;
	}
	public void setStudyItemdesc(String studyItemdesc) {
		this.studyItemdesc = studyItemdesc;
	}
	public String getStudyDatetimeDesc() {
		return studyDatetimeDesc;
	}
	public void setStudyDatetimeDesc(String studyDatetimeDesc) {
		this.studyDatetimeDesc = studyDatetimeDesc;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getBodyitem() {
		return bodyitem;
	}
	public void setBodyitem(String bodyitem) {
		this.bodyitem = bodyitem;
	}
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	
}
