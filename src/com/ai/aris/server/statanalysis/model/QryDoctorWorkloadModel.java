package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;

public class QryDoctorWorkloadModel {

	private  long locId;
    private  String careprovName;
    private  float studyitemPrice;
    private  Timestamp reportDatetime;
    private  long reportId;
    private  String orgId;
    private  String startTime;
    private  String endTime;
    private  String doctorId;
    private String reportDate;
    private String reportVerifydoctorid;
    private String reportVerifydoctorname;
    private String reportDoctorname;
    private  String studystatusCode;
    private  String studystatusCodeDesc;
    private String zb;
    private String reportDoctorid;

	public String getStudystatusCodeDesc() {
		return studystatusCodeDesc;
	}

	public void setStudystatusCodeDesc(String studystatusCodeDesc) {
		this.studystatusCodeDesc = studystatusCodeDesc;
	}

	public String getReportDoctorid() {
		return reportDoctorid;
	}

	public void setReportDoctorid(String reportDoctorid) {
		this.reportDoctorid = reportDoctorid;
	}

	public String getZb() {
		return zb;
	}
	public void setZb(String zb) {
		this.zb = zb;
	}
	public String getStudystatusCode() {
		return studystatusCode;
	}
	public void setStudystatusCode(String studystatusCode) {
		this.studystatusCode = studystatusCode;
	}
	public String getReportDoctorname() {
		return reportDoctorname;
	}
	public void setReportDoctorname(String reportDoctorname) {
		this.reportDoctorname = reportDoctorname;
	}
	public long getLocId() {
		return locId;
	}
	public void setLocId(long locId) {
		this.locId = locId;
	}
	public String getCareprovName() {
		return careprovName;
	}
	public void setCareprovName(String careprovName) {
		this.careprovName = careprovName;
	}
	public float getStudyitemPrice() {
		return studyitemPrice;
	}
	public void setStudyitemPrice(float studyitemPrice) {
		this.studyitemPrice = studyitemPrice;
	}
	public Timestamp getReportDatetime() {
		return reportDatetime;
	}
	public void setReportDatetime(Timestamp reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	public long getReportId() {
		return reportId;
	}
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportVerifydoctorid() {
		return reportVerifydoctorid;
	}
	public void setReportVerifydoctorid(String reportVerifydoctorid) {
		this.reportVerifydoctorid = reportVerifydoctorid;
	}
	public String getReportVerifydoctorname() {
		return reportVerifydoctorname;
	}
	public void setReportVerifydoctorname(String reportVerifydoctorname) {
		this.reportVerifydoctorname = reportVerifydoctorname;
	}

}
