package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;


public class QueryCheckMasterModel {
	
    private long patientGlobalid;
    private String patientId;
    private String patientName;
    private String patientNamepy;
    private Timestamp patientDob;
    private String patientAge;
    private String patientSex;
    private String patientIdnumber;
    private String patientCardid;
    private String patientMedicareid;
    private String patientMobile;
    private String patientPhone;
    private String patientVocation;
    private String patientNation;
    private String patientStation;
    private String patientStatus;
    private String patientRemark;
    private String patientAddress;
    private String patienttypeCode;
    private String studyClinic;
    private long studyLocseqno;
   
    private long studyinfoId;
	private String studystatusCode;
    private String equipmentDesc;
    private String roomDesc;
    private String studyItemdesc;
    private String studyitemBodyinfo;
    private long studylevelId ;
    private String studylevelIdDesc;
    private long  studyFilmcount;
    private long studyExposurecount;
    private String studyDoctorid;
    private String aidDoctorid;
    private long locId;
    private String locName;
	private Timestamp studyStarttime;
	private String  statusCode;
	
	private Timestamp studyEndtime;
    
    private String actionType;
    
    private long equipmentId;
    private String startTime;
    private String endTime;
    
    private String orgName;
    
    private Timestamp studyAppdate;
    private Timestamp studyDatetime;
    private Timestamp studyConsultstarttime;
    private Timestamp studyConsultendtime;
    private String studyAccnumber;
    private long studyConsultloc;
    private String modalityId;
    
	public String getModalityId() {
		return modalityId;
	}
	public void setModalityId(String modalityId) {
		this.modalityId = modalityId;
	}
	public long getStudyConsultloc() {
		return studyConsultloc;
	}
	public void setStudyConsultloc(long studyConsultloc) {
		this.studyConsultloc = studyConsultloc;
	}
	public String getStudyAccnumber() {
		return studyAccnumber;
	}
	public void setStudyAccnumber(String studyAccnumber) {
		this.studyAccnumber = studyAccnumber;
	}
	public Timestamp getStudyAppdate() {
		return studyAppdate;
	}
	public void setStudyAppdate(Timestamp studyAppdate) {
		this.studyAppdate = studyAppdate;
	}
	public Timestamp getStudyDatetime() {
		return studyDatetime;
	}
	public void setStudyDatetime(Timestamp studyDatetime) {
		this.studyDatetime = studyDatetime;
	}
	public Timestamp getStudyConsultstarttime() {
		return studyConsultstarttime;
	}
	public void setStudyConsultstarttime(Timestamp studyConsultstarttime) {
		this.studyConsultstarttime = studyConsultstarttime;
	}
	public Timestamp getStudyConsultendtime() {
		return studyConsultendtime;
	}
	public void setStudyConsultendtime(Timestamp studyConsultendtime) {
		this.studyConsultendtime = studyConsultendtime;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public long getStudyFilmcount() {
		return studyFilmcount;
	}
	public void setStudyFilmcount(long studyFilmcount) {
		this.studyFilmcount = studyFilmcount;
	}
	public long getStudyExposurecount() {
		return studyExposurecount;
	}
	public void setStudyExposurecount(long studyExposurecount) {
		this.studyExposurecount = studyExposurecount;
	}
	
    
   
	public long getStudyLocseqno() {
		return studyLocseqno;
	}
	public void setStudyLocseqno(long studyLocseqno) {
		this.studyLocseqno = studyLocseqno;
	}
    public long getPatientGlobalid() {
		return patientGlobalid;
	}
	public void setPatientGlobalid(long patientGlobalid) {
		this.patientGlobalid = patientGlobalid;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getPatientNamepy() {
		return patientNamepy;
	}
	public void setPatientNamepy(String patientNamepy) {
		this.patientNamepy = patientNamepy;
	}
	public Timestamp getPatientDob() {
		return patientDob;
	}
	public void setPatientDob(Timestamp patientDob) {
		this.patientDob = patientDob;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientSex() {
		return patientSex;
	}
	public void setPatientSex(String patientSex) {
		this.patientSex = patientSex;
	}
	public String getPatientIdnumber() {
		return patientIdnumber;
	}
	public void setPatientIdnumber(String patientIdnumber) {
		this.patientIdnumber = patientIdnumber;
	}
	public String getPatientCardid() {
		return patientCardid;
	}
	public void setPatientCardid(String patientCardid) {
		this.patientCardid = patientCardid;
	}
	public String getPatientMedicareid() {
		return patientMedicareid;
	}
	public void setPatientMedicareid(String patientMedicareid) {
		this.patientMedicareid = patientMedicareid;
	}
	public String getPatientMobile() {
		return patientMobile;
	}
	public void setPatientMobile(String patientMobile) {
		this.patientMobile = patientMobile;
	}
	public String getPatientPhone() {
		return patientPhone;
	}
	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}
	public String getPatientVocation() {
		return patientVocation;
	}
	public void setPatientVocation(String patientVocation) {
		this.patientVocation = patientVocation;
	}
	public String getPatientNation() {
		return patientNation;
	}
	public void setPatientNation(String patientNation) {
		this.patientNation = patientNation;
	}
	public String getPatientStation() {
		return patientStation;
	}
	public void setPatientStation(String patientStation) {
		this.patientStation = patientStation;
	}
	public String getPatientStatus() {
		return patientStatus;
	}
	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}
	public String getPatientRemark() {
		return patientRemark;
	}
	public void setPatientRemark(String patientRemark) {
		this.patientRemark = patientRemark;
	}
	public String getPatientAddress() {
		return patientAddress;
	}
	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	public String getPatienttypeCode() {
		return patienttypeCode;
	}
	public void setPatienttypeCode(String patienttypeCode) {
		this.patienttypeCode = patienttypeCode;
	}
	public String getStudyClinic() {
		return studyClinic;
	}
	public void setStudyClinic(String studyClinic) {
		this.studyClinic = studyClinic;
	}
	
	public String getStudystatusCode() {
		return studystatusCode;
	}
	public void setStudystatusCode(String studystatusCode) {
		this.studystatusCode = studystatusCode;
	}
	public String getEquipmentDesc() {
		return equipmentDesc;
	}
	public void setEquipmentDesc(String equipmentDesc) {
		this.equipmentDesc = equipmentDesc;
	}
	public String getRoomDesc() {
		return roomDesc;
	}
	public void setRoomDesc(String roomDesc) {
		this.roomDesc = roomDesc;
	}
	public long getStudyinfoId() {
		return studyinfoId;
	}
	public void setStudyinfoId(long studyinfoId) {
		this.studyinfoId = studyinfoId;
	}
	public String getStudyItemdesc() {
		return studyItemdesc;
	}
	public void setStudyItemdesc(String studyItemdesc) {
		this.studyItemdesc = studyItemdesc;
	}
	public String getStudyitemBodyinfo() {
		return studyitemBodyinfo;
	}
	public void setStudyitemBodyinfo(String studyitemBodyinfo) {
		this.studyitemBodyinfo = studyitemBodyinfo;
	}
	public String getStudyDoctorid() {
		return studyDoctorid;
	}
	public void setStudyDoctorid(String studyDoctorid) {
		this.studyDoctorid = studyDoctorid;
	}
	public String getAidDoctorid() {
		return aidDoctorid;
	}
	public void setAidDoctorid(String aidDoctorid) {
		this.aidDoctorid = aidDoctorid;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	
	public long getLocId() {
		return locId;
	}
	public void setLocId(long locId) {
		this.locId = locId;
	}
	public Timestamp getStudyStarttime() {
		return studyStarttime;
	}
	public void setStudyStarttime(Timestamp studyStarttime) {
		this.studyStarttime = studyStarttime;
	}
	public Timestamp getStudyEndtime() {
		return studyEndtime;
	}
	public void setStudyEndtime(Timestamp studyEndtime) {
		this.studyEndtime = studyEndtime;
	}
	public long getStudylevelId() {
		return studylevelId;
	}
	public void setStudylevelId(long studylevelId) {
		this.studylevelId = studylevelId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStudylevelIdDesc() {
		return studylevelIdDesc;
	}
	public void setStudylevelIdDesc(String studylevelIdDesc) {
		this.studylevelIdDesc = studylevelIdDesc;
	}
	public long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(long equipmentId) {
		this.equipmentId = equipmentId;
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
	
}
