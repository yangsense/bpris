package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryMedicalCaseWorkloadModel {

    private  String studyItemdesc;
    private  String patientSex;
    private  long reportIspositive;
    private  String patientId;
    private  String reportDoctorid;
    private  long studyHaveimage;
    private  String studyAccnumber;
    private  String studystatusCode;
    private  String patientName;
    private  String reportDoctorname;
    private  long studyinfoId;
    private  long studyHavereport;
    private  long locId;
    private  long patientGlobalid;
    private  String reportVerifydoctorid;
    private  String patientAge;
    private  Timestamp reportDatetime;
    private  String verifydoctorName;
    
    private String startTime;
    private String endTime;
    private String categories;
    private String startAge;
    private String endAge;
    private String orgId;
    private long reportId;
    private String reportFinaldoctorid;
    private String reportResult;

//自定义字典

    public String getStudyItemdesc(){
        return studyItemdesc;
    }
	public String getReportResult() {
		return reportResult;
	}
	public void setReportResult(String reportResult) {
		this.reportResult = reportResult;
	}
	public long getReportId() {
		return reportId;
	}
	public void setReportId(long reportId) {
		this.reportId = reportId;
	}
	public String getReportFinaldoctorid() {
		return reportFinaldoctorid;
	}
	public void setReportFinaldoctorid(String reportFinaldoctorid) {
		this.reportFinaldoctorid = reportFinaldoctorid;
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
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getStartAge() {
		return startAge;
	}
	public void setStartAge(String startAge) {
		this.startAge = startAge;
	}
	public String getEndAge() {
		return endAge;
	}
	public void setEndAge(String endAge) {
		this.endAge = endAge;
	}
	public String getPatientSex(){
        return patientSex;
    }
    public long getReportIspositive(){
        return reportIspositive;
    }
    public String getPatientId(){
        return patientId;
    }
    public String getReportDoctorid(){
        return reportDoctorid;
    }
    public long getStudyHaveimage(){
        return studyHaveimage;
    }
    public String getStudyAccnumber(){
        return studyAccnumber;
    }
    public String getStudystatusCode(){
        return studystatusCode;
    }
    public String getPatientName(){
        return patientName;
    }
    public String getReportDoctorname(){
        return reportDoctorname;
    }
    public long getStudyinfoId(){
        return studyinfoId;
    }
    public long getStudyHavereport(){
        return studyHavereport;
    }
    public long getLocId(){
        return locId;
    }
    public long getPatientGlobalid(){
        return patientGlobalid;
    }
    public String getReportVerifydoctorid(){
        return reportVerifydoctorid;
    }
    public String getPatientAge(){
        return patientAge;
    }
    public Timestamp getReportDatetime(){
        return reportDatetime;
    }
    public String getVerifydoctorName(){
        return verifydoctorName;
    }

    public  void setStudyItemdesc(String studyItemdesc){
        this.studyItemdesc = studyItemdesc;
    }
    public  void setPatientSex(String patientSex){
        this.patientSex = patientSex;
    }
    public  void setReportIspositive(long reportIspositive){
        this.reportIspositive = reportIspositive;
    }
    public  void setPatientId(String patientId){
        this.patientId = patientId;
    }
    public  void setReportDoctorid(String reportDoctorid){
        this.reportDoctorid = reportDoctorid;
    }
    public  void setStudyHaveimage(long studyHaveimage){
        this.studyHaveimage = studyHaveimage;
    }
    public  void setStudyAccnumber(String studyAccnumber){
        this.studyAccnumber = studyAccnumber;
    }
    public  void setStudystatusCode(String studystatusCode){
        this.studystatusCode = studystatusCode;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setReportDoctorname(String reportDoctorname){
        this.reportDoctorname = reportDoctorname;
    }
    public  void setStudyinfoId(long studyinfoId){
        this.studyinfoId = studyinfoId;
    }
    public  void setStudyHavereport(long studyHavereport){
        this.studyHavereport = studyHavereport;
    }
    public  void setLocId(long locId){
        this.locId = locId;
    }
    public  void setPatientGlobalid(long patientGlobalid){
        this.patientGlobalid = patientGlobalid;
    }
    public  void setReportVerifydoctorid(String reportVerifydoctorid){
        this.reportVerifydoctorid = reportVerifydoctorid;
    }
    public  void setPatientAge(String patientAge){
        this.patientAge = patientAge;
    }
    public  void setReportDatetime(Timestamp reportDatetime){
        this.reportDatetime = reportDatetime;
    }
    public  void setVerifydoctorName(String verifydoctorName){
        this.verifydoctorName = verifydoctorName;
    }

//自定义字典
}
