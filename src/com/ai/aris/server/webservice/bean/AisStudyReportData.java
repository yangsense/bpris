package com.ai.aris.server.webservice.bean;

import java.sql.Timestamp;
import java.util.Date;

public class AisStudyReportData {
	private long reportId;
	private long studyinfoId;
	private Timestamp reportDatetime;
	private String reportDoctorid;
	private Timestamp reportVerifydatetime;
	private String reportVerifydoctorid;
	private Timestamp reportFinaldatetime;
	private String reportFinaldoctorid;
	private String reportRecord;
	private long reportIsvisible;
	private long reportIsprinted;
	private long reportIssent;
	private Timestamp reportPrintdatetime;
	private String reportPrintcareprovid;
	private long reportIscompleted;
	private String reportUncompletedreason;
	private String reportExammethod;
	private String reportExam;
	private String reportResult;
	private long reportIspositive;
	private long reportLock;
	private String reportRemark;
	private String reportAcrcode;
	private String reportIcd10;
	private String orgId;
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public long getReportId() {
		return reportId;
	}
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	public long getStudyinfoId() {
		return studyinfoId;
	}
	public void setStudyinfoId(long studyinfoId) {
		this.studyinfoId = studyinfoId;
	}
	public String getReportDoctorid() {
		return reportDoctorid;
	}
	public void setReportDoctorid(String reportDoctorid) {
		this.reportDoctorid = reportDoctorid;
	}
	public String getReportVerifydoctorid() {
		return reportVerifydoctorid;
	}
	public void setReportVerifydoctorid(String reportVerifydoctorid) {
		this.reportVerifydoctorid = reportVerifydoctorid;
	}
	public String getReportFinaldoctorid() {
		return reportFinaldoctorid;
	}
	public void setReportFinaldoctorid(String reportFinaldoctorid) {
		this.reportFinaldoctorid = reportFinaldoctorid;
	}
	public String getReportRecord() {
		return reportRecord;
	}
	public void setReportRecord(String reportRecord) {
		this.reportRecord = reportRecord;
	}
	public long getReportIsvisible() {
		return reportIsvisible;
	}
	public void setReportIsvisible(long reportIsvisible) {
		this.reportIsvisible = reportIsvisible;
	}
	public long getReportIsprinted() {
		return reportIsprinted;
	}
	public void setReportIsprinted(long reportIsprinted) {
		this.reportIsprinted = reportIsprinted;
	}
	public long getReportIssent() {
		return reportIssent;
	}
	public void setReportIssent(long reportIssent) {
		this.reportIssent = reportIssent;
	}
	public Timestamp getReportDatetime() {
		return reportDatetime;
	}
	public void setReportDatetime(Timestamp reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	public Timestamp getReportVerifydatetime() {
		return reportVerifydatetime;
	}
	public void setReportVerifydatetime(Timestamp reportVerifydatetime) {
		this.reportVerifydatetime = reportVerifydatetime;
	}
	public Timestamp getReportFinaldatetime() {
		return reportFinaldatetime;
	}
	public void setReportFinaldatetime(Timestamp reportFinaldatetime) {
		this.reportFinaldatetime = reportFinaldatetime;
	}
	public Timestamp getReportPrintdatetime() {
		return reportPrintdatetime;
	}
	public void setReportPrintdatetime(Timestamp reportPrintdatetime) {
		this.reportPrintdatetime = reportPrintdatetime;
	}
	public String getReportPrintcareprovid() {
		return reportPrintcareprovid;
	}
	public void setReportPrintcareprovid(String reportPrintcareprovid) {
		this.reportPrintcareprovid = reportPrintcareprovid;
	}
	public long getReportIscompleted() {
		return reportIscompleted;
	}
	public void setReportIscompleted(long reportIscompleted) {
		this.reportIscompleted = reportIscompleted;
	}
	public String getReportUncompletedreason() {
		return reportUncompletedreason;
	}
	public void setReportUncompletedreason(String reportUncompletedreason) {
		this.reportUncompletedreason = reportUncompletedreason;
	}
	public String getReportExammethod() {
		return reportExammethod;
	}
	public void setReportExammethod(String reportExammethod) {
		this.reportExammethod = reportExammethod;
	}
	public String getReportExam() {
		return reportExam;
	}
	public void setReportExam(String reportExam) {
		this.reportExam = reportExam;
	}
	public String getReportResult() {
		return reportResult;
	}
	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	public long getReportIspositive() {
		return reportIspositive;
	}
	public void setReportIspositive(long reportIspositive) {
		this.reportIspositive = reportIspositive;
	}
	public long getReportLock() {
		return reportLock;
	}
	public void setReportLock(long reportLock) {
		this.reportLock = reportLock;
	}
	public String getReportRemark() {
		return reportRemark;
	}
	public void setReportRemark(String reportRemark) {
		this.reportRemark = reportRemark;
	}
	public String getReportAcrcode() {
		return reportAcrcode;
	}
	public void setReportAcrcode(String reportAcrcode) {
		this.reportAcrcode = reportAcrcode;
	}
	public String getReportIcd10() {
		return reportIcd10;
	}
	public void setReportIcd10(String reportIcd10) {
		this.reportIcd10 = reportIcd10;
	}
	
}
