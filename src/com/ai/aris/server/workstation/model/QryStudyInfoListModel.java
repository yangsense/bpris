package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;

public class QryStudyInfoListModel {

    private  long patientGlobalid;
    private  String orgId;
    private  String locDesc;
    private  String reportDatetime;
    private  String reportVerifytime;
    private  long studyHavereport;
    private  String patientDob;
    private  Timestamp studyAppdate;
    private  String cStudyStarttime;
    private  String studyAccnumber;
    private  Timestamp studyDatetime;
    private  String studyConsultation;
    private  String studyItemdesc;
    private  String patientSex;
    private  String reportVerifydoctorname;
    private  String studyConsultorgName;
    private  String patientName;
    private  Timestamp studyStarttime;
    private  String studyConsultlocName;
    private  String orgName;
    private  Timestamp studyConsultendtime;
    private  Timestamp studyConsultstarttime;
    private  Timestamp studyEndtime;
    private  String studyAppdoc;
    private  String cStudyEndtime;
    private  long locId;
    private  String cStudyDatetime;
    private  String cStudyOperationtime;
    private  long studyApplocid;
    private  long studyHaveimage;
    private  String patientId;
    private  String reportDoctorname;
    private  String patientAge;
    private  String reportFinaldoctorname;
    private  long studyinfoId;
    private  String studystatusCode;
    private  long studyType;
    private  String studyConsultorg;
    private  String studyConsultloc;
    private  String studyDoctorid;
    private  String studyDoctorname;
    private  long studyFilmprinted;
    
    private String startTime;
    private String endTime; 
    private String qType;
    private String qValue;
    private String consultFlag;
    private String isDate;
    private String diagnoseFlag; 
    private String patienttypeCode;
    private String cStudyAppdate;
    private String studyAppdocname;
    private String messageFlag;
    private String doctorId;
    private String allStudyType;
    private  long isUrgent;
    private String reportVerifydoctorid;
    private String studyBedno;
    private String bodyitem;
    private String patientInpatientid;
    private String patientOutpatientid;
    private long reportIsprinted;
    
    private String reportExam;
    private String reportResult;
    private String reportRemark;
    
    private long modalityId;
    private String modalityType;
    private String equipmentId;
    private String equipmentDesc;
    private  String oldPatientId;
	
	public String getOldPatientId() {
		return oldPatientId;
	}
	public void setOldPatientId(String oldPatientId) {
		this.oldPatientId = oldPatientId;
	}
	public long getStudyFilmprinted() {
		return studyFilmprinted;
	}
	public void setStudyFilmprinted(long studyFilmprinted) {
		this.studyFilmprinted = studyFilmprinted;
	}
	public String getIsDate() {
		return isDate;
	}
	public void setIsDate(String isDate) {
		this.isDate = isDate;
	}
	public String getEquipmentDesc() {
		return equipmentDesc;
	}
	public void setEquipmentDesc(String equipmentDesc) {
		this.equipmentDesc = equipmentDesc;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getModalityType() {
		return modalityType;
	}
	public void setModalityType(String modalityType) {
		this.modalityType = modalityType;
	}
	public String getPatientOutpatientid() {
		return patientOutpatientid;
	}
	public void setPatientOutpatientid(String patientOutpatientid) {
		this.patientOutpatientid = patientOutpatientid;
	}
	public long getModalityId() {
		return modalityId;
	}
	public void setModalityId(long modalityId) {
		this.modalityId = modalityId;
	}
	public String getReportRemark() {
		return reportRemark;
	}
	public void setReportRemark(String reportRemark) {
		this.reportRemark = reportRemark;
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
	public long getReportIsprinted() {
		return reportIsprinted;
	}
	public void setReportIsprinted(long reportIsprinted) {
		this.reportIsprinted = reportIsprinted;
	}
	public String getReportVerifytime() {
		return reportVerifytime;
	}
	public void setReportVerifytime(String reportVerifytime) {
		this.reportVerifytime = reportVerifytime;
	}
	public String getPatientInpatientid() {
		return patientInpatientid;
	}
	public void setPatientInpatientid(String patientInpatientid) {
		this.patientInpatientid = patientInpatientid;
	}
	public String getBodyitem() {
		return bodyitem;
	}
	public void setBodyitem(String bodyitem) {
		this.bodyitem = bodyitem;
	}
	public String getStudyBedno() {
		return studyBedno;
	}
	public void setStudyBedno(String studyBedno) {
		this.studyBedno = studyBedno;
	}
	public String getReportVerifydoctorid() {
		return reportVerifydoctorid;
	}
	public void setReportVerifydoctorid(String reportVerifydoctorid) {
		this.reportVerifydoctorid = reportVerifydoctorid;
	}
	public long getIsUrgent() {
		return isUrgent;
	}
	public void setIsUrgent(long isUrgent) {
		this.isUrgent = isUrgent;
	}
	public String getcStudyAppdate() {
		return cStudyAppdate;
	}
	public void setcStudyAppdate(String cStudyAppdate) {
		this.cStudyAppdate = cStudyAppdate;
	}
	public String getPatienttypeCode() {
		return patienttypeCode;
	}
	public void setPatienttypeCode(String patienttypeCode) {
		this.patienttypeCode = patienttypeCode;
	}
	public String getcStudyOperationtime() {
		return cStudyOperationtime;
	}
	public void setcStudyOperationtime(String cStudyOperationtime) {
		this.cStudyOperationtime = cStudyOperationtime;
	}
	public long getPatientGlobalid(){
        return patientGlobalid;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getLocDesc(){
        return locDesc;
    }
    public String getReportDatetime(){
        return reportDatetime;
    }
    public long getStudyHavereport(){
        return studyHavereport;
    }
    public String getPatientDob(){
        return patientDob;
    }
    public Timestamp getStudyAppdate(){
        return studyAppdate;
    }
    public String getCStudyStarttime(){
        return cStudyStarttime;
    }
    public String getStudyAccnumber(){
        return studyAccnumber;
    }
    public Timestamp getStudyDatetime(){
        return studyDatetime;
    }
    public String getStudyConsultation(){
        return studyConsultation;
    }
    public String getStudyItemdesc(){
        return studyItemdesc;
    }
    public String getPatientSex(){
        return patientSex;
    }
    public String getReportVerifydoctorname(){
        return reportVerifydoctorname;
    }
    public String getStudyConsultorgName(){
        return studyConsultorgName;
    }
    public String getPatientName(){
        return patientName;
    }
    public Timestamp getStudyStarttime(){
        return studyStarttime;
    }
    public String getStudyConsultlocName(){
        return studyConsultlocName;
    }
    public String getOrgName(){
        return orgName;
    }
    public Timestamp getStudyConsultendtime(){
        return studyConsultendtime;
    }
    public Timestamp getStudyConsultstarttime(){
        return studyConsultstarttime;
    }
    public Timestamp getStudyEndtime(){
        return studyEndtime;
    }
    public String getStudyAppdoc(){
        return studyAppdoc;
    }
    public String getCStudyEndtime(){
        return cStudyEndtime;
    }
    public long getLocId(){
        return locId;
    }
    public String getCStudyDatetime(){
        return cStudyDatetime;
    }
    public long getStudyApplocid(){
        return studyApplocid;
    }
    public long getStudyHaveimage(){
        return studyHaveimage;
    }
    public String getPatientId(){
        return patientId;
    }
    public String getReportDoctorname(){
        return reportDoctorname;
    }
    public String getPatientAge(){
        return patientAge;
    }
    public String getReportFinaldoctorname(){
        return reportFinaldoctorname;
    }
    public long getStudyinfoId(){
        return studyinfoId;
    }
    public String getStudystatusCode(){
        return studystatusCode;
    }

    public  void setPatientGlobalid(long patientGlobalid){
        this.patientGlobalid = patientGlobalid;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setLocDesc(String locDesc){
        this.locDesc = locDesc;
    }
    public  void setReportDatetime(String reportDatetime){
        this.reportDatetime = reportDatetime;
    }
    public  void setStudyHavereport(long studyHavereport){
        this.studyHavereport = studyHavereport;
    }
    public  void setPatientDob(String patientDob){
        this.patientDob = patientDob;
    }
    public  void setStudyAppdate(Timestamp studyAppdate){
        this.studyAppdate = studyAppdate;
    }
    public  void setCStudyStarttime(String cStudyStarttime){
        this.cStudyStarttime = cStudyStarttime;
    }
    public  void setStudyAccnumber(String studyAccnumber){
        this.studyAccnumber = studyAccnumber;
    }
    public  void setStudyDatetime(Timestamp studyDatetime){
        this.studyDatetime = studyDatetime;
    }
    public  void setStudyConsultation(String studyConsultation){
        this.studyConsultation = studyConsultation;
    }
    public  void setStudyItemdesc(String studyItemdesc){
        this.studyItemdesc = studyItemdesc;
    }
    public  void setPatientSex(String patientSex){
        this.patientSex = patientSex;
    }
    public  void setReportVerifydoctorname(String reportVerifydoctorname){
        this.reportVerifydoctorname = reportVerifydoctorname;
    }
    public  void setStudyConsultorgName(String studyConsultorgName){
        this.studyConsultorgName = studyConsultorgName;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setStudyStarttime(Timestamp studyStarttime){
        this.studyStarttime = studyStarttime;
    }
    public  void setStudyConsultlocName(String studyConsultlocName){
        this.studyConsultlocName = studyConsultlocName;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setStudyConsultendtime(Timestamp studyConsultendtime){
        this.studyConsultendtime = studyConsultendtime;
    }
    public  void setStudyConsultstarttime(Timestamp studyConsultstarttime){
        this.studyConsultstarttime = studyConsultstarttime;
    }
    public  void setStudyEndtime(Timestamp studyEndtime){
        this.studyEndtime = studyEndtime;
    }
    public  void setStudyAppdoc(String studyAppdoc){
        this.studyAppdoc = studyAppdoc;
    }
    public  void setCStudyEndtime(String cStudyEndtime){
        this.cStudyEndtime = cStudyEndtime;
    }
    public  void setLocId(long locId){
        this.locId = locId;
    }
    public  void setCStudyDatetime(String cStudyDatetime){
        this.cStudyDatetime = cStudyDatetime;
    }
    public  void setStudyApplocid(long studyApplocid){
        this.studyApplocid = studyApplocid;
    }
    public  void setStudyHaveimage(long studyHaveimage){
        this.studyHaveimage = studyHaveimage;
    }
    public  void setPatientId(String patientId){
        this.patientId = patientId;
    }
    public  void setReportDoctorname(String reportDoctorname){
        this.reportDoctorname = reportDoctorname;
    }
    public  void setPatientAge(String patientAge){
        this.patientAge = patientAge;
    }
    public  void setReportFinaldoctorname(String reportFinaldoctorname){
        this.reportFinaldoctorname = reportFinaldoctorname;
    }
    public  void setStudyinfoId(long studyinfoId){
        this.studyinfoId = studyinfoId;
    }
    public  void setStudystatusCode(String studystatusCode){
        this.studystatusCode = studystatusCode;
    }
	public String getcStudyStarttime() {
		return cStudyStarttime;
	}
	public void setcStudyStarttime(String cStudyStarttime) {
		this.cStudyStarttime = cStudyStarttime;
	}
	public String getcStudyEndtime() {
		return cStudyEndtime;
	}
	public void setcStudyEndtime(String cStudyEndtime) {
		this.cStudyEndtime = cStudyEndtime;
	}
	public String getcStudyDatetime() {
		return cStudyDatetime;
	}
	public void setcStudyDatetime(String cStudyDatetime) {
		this.cStudyDatetime = cStudyDatetime;
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
	public String getqType() {
		return qType;
	}
	public void setqType(String qType) {
		this.qType = qType;
	}
	public String getqValue() {
		return qValue;
	}
	public void setqValue(String qValue) {
		this.qValue = qValue;
	}
	public String getConsultFlag() {
		return consultFlag;
	}
	public void setConsultFlag(String consultFlag) {
		this.consultFlag = consultFlag;
	}
	public long getStudyType() {
		return studyType;
	}
	public void setStudyType(long studyType) {
		this.studyType = studyType;
	}
	public String getStudyConsultorg() {
		return studyConsultorg;
	}
	public void setStudyConsultorg(String studyConsultorg) {
		this.studyConsultorg = studyConsultorg;
	}
	public String getStudyConsultloc() {
		return studyConsultloc;
	}
	public void setStudyConsultloc(String studyConsultloc) {
		this.studyConsultloc = studyConsultloc;
	}
	public String getStudyDoctorid() {
		return studyDoctorid;
	}
	public void setStudyDoctorid(String studyDoctorid) {
		this.studyDoctorid = studyDoctorid;
	}
	public String getStudyDoctorname() {
		return studyDoctorname;
	}
	public void setStudyDoctorname(String studyDoctorname) {
		this.studyDoctorname = studyDoctorname;
	}
	public String getDiagnoseFlag() {
		return diagnoseFlag;
	}
	public void setDiagnoseFlag(String diagnoseFlag) {
		this.diagnoseFlag = diagnoseFlag;
	}
	public String getStudyAppdocname() {
		return studyAppdocname;
	}
	public void setStudyAppdocname(String studyAppdocname) {
		this.studyAppdocname = studyAppdocname;
	}
	public String getMessageFlag() {
		return messageFlag;
	}
	public void setMessageFlag(String messageFlag) {
		this.messageFlag = messageFlag;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getAllStudyType() {
		return allStudyType;
	}
	public void setAllStudyType(String allStudyType) {
		this.allStudyType = allStudyType;
	}
    
	
}
