package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;

import com.ai.common.domain.DictItem;

public class AiscPatientCheckRpModel {

    private  String cityDesc;
    private  String cityCode;
    private  String locDesc;
    private  String checkDate;
    private  String registNo;
    private  String checkItem;
    private  String checkDoc;
    private  long imageNum;
    private  String orgDesc;
    private  String orgId;
    private  String reportDoc;
    private  String patientSex;
    private  String patientId;
    private  String countyDesc;
    private  String locCode;
    private  String patientName;
    private  long id;
    private  String countyCode;
    private  String patientAge;
    private String time_type;
    private String yearDate;
    private String yearDateOfJD;
    private String jd;
    private String dateOfDay;
    private String monthDate;
    //自定义字典
    private String startDate;
    private String endDate;
//自定义字典

    public String getCityDesc(){
        return cityDesc;
    }
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getTime_type() {
		return time_type;
	}
	public void setTime_type(String time_type) {
		this.time_type = time_type;
	}
	public String getYearDate() {
		return yearDate;
	}
	public void setYearDate(String yearDate) {
		this.yearDate = yearDate;
	}
	public String getYearDateOfJD() {
		return yearDateOfJD;
	}
	public void setYearDateOfJD(String yearDateOfJD) {
		this.yearDateOfJD = yearDateOfJD;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getDateOfDay() {
		return dateOfDay;
	}
	public void setDateOfDay(String dateOfDay) {
		this.dateOfDay = dateOfDay;
	}
	public String getMonthDate() {
		return monthDate;
	}
	public void setMonthDate(String monthDate) {
		this.monthDate = monthDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCityCode(){
        return cityCode;
    }
    public String getLocDesc(){
        return locDesc;
    }
    public String getCheckDate(){
        return checkDate;
    }
    public String getRegistNo(){
        return registNo;
    }
    public String getCheckItem(){
        return checkItem;
    }
    public String getCheckDoc(){
        return checkDoc;
    }
    public long getImageNum(){
        return imageNum;
    }
    public String getOrgDesc(){
        return orgDesc;
    }
    public String getReportDoc(){
        return reportDoc;
    }
    public String getPatientSex(){
        return patientSex;
    }
    public String getCountyDesc(){
        return countyDesc;
    }
    public String getLocCode(){
        return locCode;
    }
    public String getPatientName(){
        return patientName;
    }
    public long getId(){
        return id;
    }
    public String getCountyCode(){
        return countyCode;
    }
    public String getPatientAge(){
        return patientAge;
    }

    public  void setCityDesc(String cityDesc){
        this.cityDesc = cityDesc;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setLocDesc(String locDesc){
        this.locDesc = locDesc;
    }
    public  void setCheckDate(String checkDate){
        this.checkDate = checkDate;
    }
    public  void setRegistNo(String registNo){
        this.registNo = registNo;
    }
    public  void setCheckItem(String checkItem){
        this.checkItem = checkItem;
    }
    public  void setCheckDoc(String checkDoc){
        this.checkDoc = checkDoc;
    }
    public  void setImageNum(long imageNum){
        this.imageNum = imageNum;
    }
    public  void setOrgDesc(String orgDesc){
        this.orgDesc = orgDesc;
    }
    public  void setReportDoc(String reportDoc){
        this.reportDoc = reportDoc;
    }
    public  void setPatientSex(String patientSex){
        this.patientSex = patientSex;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }
    public  void setLocCode(String locCode){
        this.locCode = locCode;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setId(long id){
        this.id = id;
    }
    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setPatientAge(String patientAge){
        this.patientAge = patientAge;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

//自定义字典
}
