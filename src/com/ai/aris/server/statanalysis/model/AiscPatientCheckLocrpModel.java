package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;

import com.ai.common.domain.DictItem;

public class AiscPatientCheckLocrpModel {

    private  long reportNum;
    private  String cityDesc;
    private  String cityCode;
    private  String locDesc;
    private  String checkDate;
    private  long collectNum;
    private  long checkedNum;
    private  long imageNum;
    private  String orgDesc;
    private  String orgId;
    private  String countyDesc;
    private  String locCode;
    private  long id;
    private  String countyCode;
    private  long registNum;

//自定义字典
    private String time_type;
    private String yearDate;
    private String yearDateOfJD;
    private String jd;
    private String dateOfDay;
    private String monthDate;
    //自定义字典
    private String startDate;
    private String endDate;
    public String getStartDate() {
        return startDate;
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

	public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public long getReportNum(){
        return reportNum;
    }
    public String getCityDesc(){
        return cityDesc;
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
    public long getCollectNum(){
        return collectNum;
    }
    public long getCheckedNum(){
        return checkedNum;
    }
    public long getImageNum(){
        return imageNum;
    }
    public String getOrgDesc(){
        return orgDesc;
    }

    public String getCountyDesc(){
        return countyDesc;
    }
    public String getLocCode(){
        return locCode;
    }
    public long getId(){
        return id;
    }
    public String getCountyCode(){
        return countyCode;
    }
    public long getRegistNum(){
        return registNum;
    }

    public  void setReportNum(long reportNum){
        this.reportNum = reportNum;
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
    public  void setCollectNum(long collectNum){
        this.collectNum = collectNum;
    }
    public  void setCheckedNum(long checkedNum){
        this.checkedNum = checkedNum;
    }
    public  void setImageNum(long imageNum){
        this.imageNum = imageNum;
    }
    public  void setOrgDesc(String orgDesc){
        this.orgDesc = orgDesc;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }
    public  void setLocCode(String locCode){
        this.locCode = locCode;
    }
    public  void setId(long id){
        this.id = id;
    }
    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setRegistNum(long registNum){
        this.registNum = registNum;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

//自定义字典
}
