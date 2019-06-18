package com.ai.aris.server.webservice.bean;

import java.sql.Timestamp;
import java.util.Date;

public class AisStudyInfoData {
	private long studyinfoId;
	private String orgId;
	private long locId;
	private  String patientOutpatientid;
	private  long patientGlobalid;
	private  String patientInpatientid;
	private  String studyEpsodeid;
	private  String patienttypeCode;
	private  String studyHisappid;
	private  long studyApplocid;
	private  String studyAppdoc;
	private  String studyWard;
	private  String studyBedno;
	private  String studyAim;
	private  long studyIsbed;
	private  String studyClinic;
	private  long studyFirstvisit;
	private  String studyItemdesc;
	private  String studyEnitemdesc;
	private Timestamp studyStarttime;
	private Timestamp studyEndtime;
	private Timestamp studyDatetime;
	private Timestamp studyAppdate; 
	private String studyAccnumber;
	private  Date studyConsultendtime;
    private  Date studyConsultstarttime;
    private  Date studyOperationtime;
	
	private String studyUid;
	private String studyId;
	private String studystatusCode;
	private long studyOperationid;
	private  long equipmentId;
	private  long paymenttypeId;
	private  long roomId;
	private  long studyLocseqno;
	private  long studyDoctorid;
	private  float studyTotalfee;
	
	private  long studyFilmprinted;
	private  long studyFilmcount;
	private  long studyExposurecount;
	private  long studyImagecount;
	private  long studyCheckoutfilm;
	private String studyRemark;
	private  String studyConsultation;
	private  long studyConsultloc;
	private  String studyConsultorg;
	private  long studyHavereport;
	private  long studyHaveimage;
	private  String patientWeight;
	private  String patientHeight;
	private  long studyType;
	private  long studylevelId;
	private  String aidDoctorid;
	private String studyConsultdoctorid;
	private String studyConsulttype;
	private  long isUrgent;
	
	public long getStudyinfoId() {
		return studyinfoId;
	}
	public void setStudyinfoId(long studyinfoId) {
		this.studyinfoId = studyinfoId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public long getLocId() {
		return locId;
	}
	public void setLocId(long locId) {
		this.locId = locId;
	}
	public String getPatientOutpatientid() {
		return patientOutpatientid;
	}
	public void setPatientOutpatientid(String patientOutpatientid) {
		this.patientOutpatientid = patientOutpatientid;
	}
	public long getPatientGlobalid() {
		return patientGlobalid;
	}
	public void setPatientGlobalid(long patientGlobalid) {
		this.patientGlobalid = patientGlobalid;
	}
	public String getPatientInpatientid() {
		return patientInpatientid;
	}
	public void setPatientInpatientid(String patientInpatientid) {
		this.patientInpatientid = patientInpatientid;
	}
	public String getStudyEpsodeid() {
		return studyEpsodeid;
	}
	public void setStudyEpsodeid(String studyEpsodeid) {
		this.studyEpsodeid = studyEpsodeid;
	}
	public String getPatienttypeCode() {
		return patienttypeCode;
	}
	public void setPatienttypeCode(String patienttypeCode) {
		this.patienttypeCode = patienttypeCode;
	}
	public String getStudyHisappid() {
		return studyHisappid;
	}
	public void setStudyHisappid(String studyHisappid) {
		this.studyHisappid = studyHisappid;
	}
	public long getStudyApplocid() {
		return studyApplocid;
	}
	public void setStudyApplocid(long studyApplocid) {
		this.studyApplocid = studyApplocid;
	}
	public String getStudyAppdoc() {
		return studyAppdoc;
	}
	public void setStudyAppdoc(String studyAppdoc) {
		this.studyAppdoc = studyAppdoc;
	}
	public String getStudyWard() {
		return studyWard;
	}
	public void setStudyWard(String studyWard) {
		this.studyWard = studyWard;
	}
	public String getStudyBedno() {
		return studyBedno;
	}
	public void setStudyBedno(String studyBedno) {
		this.studyBedno = studyBedno;
	}
	public String getStudyAim() {
		return studyAim;
	}
	public void setStudyAim(String studyAim) {
		this.studyAim = studyAim;
	}
	public long getStudyIsbed() {
		return studyIsbed;
	}
	public void setStudyIsbed(long studyIsbed) {
		this.studyIsbed = studyIsbed;
	}
	public String getStudyClinic() {
		return studyClinic;
	}
	public void setStudyClinic(String studyClinic) {
		this.studyClinic = studyClinic;
	}
	public long getStudyFirstvisit() {
		return studyFirstvisit;
	}
	public void setStudyFirstvisit(long studyFirstvisit) {
		this.studyFirstvisit = studyFirstvisit;
	}
	public String getStudyEnitemdesc() {
		return studyEnitemdesc;
	}
	public String getStudyItemdesc() {
		return studyItemdesc;
	}
	public void setStudyItemdesc(String studyItemdesc) {
		this.studyItemdesc = studyItemdesc;
	}
	public void setStudyEnitemdesc(String studyEnitemdesc) {
		this.studyEnitemdesc = studyEnitemdesc;
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
	public Timestamp getStudyDatetime() {
		return studyDatetime;
	}
	public void setStudyDatetime(Timestamp studyDatetime) {
		this.studyDatetime = studyDatetime;
	}
	public Timestamp getStudyAppdate() {
		return studyAppdate;
	}
	public void setStudyAppdate(Timestamp studyAppdate) {
		this.studyAppdate = studyAppdate;
	}
	public String getStudyAccnumber() {
		return studyAccnumber;
	}
	public void setStudyAccnumber(String studyAccnumber) {
		this.studyAccnumber = studyAccnumber;
	}
	public Date getStudyConsultendtime() {
		return studyConsultendtime;
	}
	public void setStudyConsultendtime(Date studyConsultendtime) {
		this.studyConsultendtime = studyConsultendtime;
	}
	public Date getStudyConsultstarttime() {
		return studyConsultstarttime;
	}
	public void setStudyConsultstarttime(Date studyConsultstarttime) {
		this.studyConsultstarttime = studyConsultstarttime;
	}
	public Date getStudyOperationtime() {
		return studyOperationtime;
	}
	public void setStudyOperationtime(Date studyOperationtime) {
		this.studyOperationtime = studyOperationtime;
	}
	public String getStudyUid() {
		return studyUid;
	}
	public void setStudyUid(String studyUid) {
		this.studyUid = studyUid;
	}
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	public String getStudystatusCode() {
		return studystatusCode;
	}
	public void setStudystatusCode(String studystatusCode) {
		this.studystatusCode = studystatusCode;
	}
	public long getStudyOperationid() {
		return studyOperationid;
	}
	public void setStudyOperationid(long studyOperationid) {
		this.studyOperationid = studyOperationid;
	}
	public long getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(long equipmentId) {
		this.equipmentId = equipmentId;
	}
	public long getPaymenttypeId() {
		return paymenttypeId;
	}
	public void setPaymenttypeId(long paymenttypeId) {
		this.paymenttypeId = paymenttypeId;
	}
	public long getRoomId() {
		return roomId;
	}
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	public long getStudyLocseqno() {
		return studyLocseqno;
	}
	public void setStudyLocseqno(long studyLocseqno) {
		this.studyLocseqno = studyLocseqno;
	}
	public long getStudyDoctorid() {
		return studyDoctorid;
	}
	public void setStudyDoctorid(long studyDoctorid) {
		this.studyDoctorid = studyDoctorid;
	}
	public float getStudyTotalfee() {
		return studyTotalfee;
	}
	public void setStudyTotalfee(float studyTotalfee) {
		this.studyTotalfee = studyTotalfee;
	}
	public long getStudyFilmprinted() {
		return studyFilmprinted;
	}
	public void setStudyFilmprinted(long studyFilmprinted) {
		this.studyFilmprinted = studyFilmprinted;
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
	public long getStudyImagecount() {
		return studyImagecount;
	}
	public void setStudyImagecount(long studyImagecount) {
		this.studyImagecount = studyImagecount;
	}
	public long getStudyCheckoutfilm() {
		return studyCheckoutfilm;
	}
	public void setStudyCheckoutfilm(long studyCheckoutfilm) {
		this.studyCheckoutfilm = studyCheckoutfilm;
	}
	public String getStudyRemark() {
		return studyRemark;
	}
	public void setStudyRemark(String studyRemark) {
		this.studyRemark = studyRemark;
	}
	public String getStudyConsultation() {
		return studyConsultation;
	}
	public void setStudyConsultation(String studyConsultation) {
		this.studyConsultation = studyConsultation;
	}
	public long getStudyConsultloc() {
		return studyConsultloc;
	}
	public void setStudyConsultloc(long studyConsultloc) {
		this.studyConsultloc = studyConsultloc;
	}
	public String getStudyConsultorg() {
		return studyConsultorg;
	}
	public void setStudyConsultorg(String studyConsultorg) {
		this.studyConsultorg = studyConsultorg;
	}
	public long getStudyHavereport() {
		return studyHavereport;
	}
	public void setStudyHavereport(long studyHavereport) {
		this.studyHavereport = studyHavereport;
	}
	public long getStudyHaveimage() {
		return studyHaveimage;
	}
	public void setStudyHaveimage(long studyHaveimage) {
		this.studyHaveimage = studyHaveimage;
	}
	public String getPatientWeight() {
		return patientWeight;
	}
	public void setPatientWeight(String patientWeight) {
		this.patientWeight = patientWeight;
	}
	public String getPatientHeight() {
		return patientHeight;
	}
	public void setPatientHeight(String patientHeight) {
		this.patientHeight = patientHeight;
	}
	public long getStudyType() {
		return studyType;
	}
	public void setStudyType(long studyType) {
		this.studyType = studyType;
	}
	public long getStudylevelId() {
		return studylevelId;
	}
	public void setStudylevelId(long studylevelId) {
		this.studylevelId = studylevelId;
	}
	public String getAidDoctorid() {
		return aidDoctorid;
	}
	public void setAidDoctorid(String aidDoctorid) {
		this.aidDoctorid = aidDoctorid;
	}
	public String getStudyConsultdoctorid() {
		return studyConsultdoctorid;
	}
	public void setStudyConsultdoctorid(String studyConsultdoctorid) {
		this.studyConsultdoctorid = studyConsultdoctorid;
	}
	public String getStudyConsulttype() {
		return studyConsulttype;
	}
	public void setStudyConsulttype(String studyConsulttype) {
		this.studyConsulttype = studyConsulttype;
	}
	public long getIsUrgent() {
		return isUrgent;
	}
	public void setIsUrgent(long isUrgent) {
		this.isUrgent = isUrgent;
	}
}
