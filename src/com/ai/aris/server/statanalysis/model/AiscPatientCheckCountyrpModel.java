package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;

import com.ai.common.domain.DictItem;

public class AiscPatientCheckCountyrpModel {

    private  long reportNum;
    private  long checkedNum;
    private  String cityDesc;
    private  long imageNum;
    private  String cityCode;
    private  String checkDate;
    private  String countyDesc;
    private  long id;
    private  long collectNum;
    private  String countyCode;
    private  long registNum;
    private  String orgId;
    //自定义字典
    private String startDate;
    private String endDate;
    private  String orgDesc;
    private  String locDesc;
    private String time_type;
    private String yearDate;
    private String yearDateOfJD;
    private String jd;
    private String dateOfDay;
    private String monthDate;
    private String locCode;
    private String excelOrgId;
    private String excelCityCode;
    private String excelCounty;
    private String excelLocCode;
    
    
	public String getExcelOrgId() {
		return excelOrgId;
	}

	public void setExcelOrgId(String excelOrgId) {
		this.excelOrgId = excelOrgId;
	}

	public String getExcelCityCode() {
		return excelCityCode;
	}

	public void setExcelCityCode(String excelCityCode) {
		this.excelCityCode = excelCityCode;
	}

	public String getExcelCounty() {
		return excelCounty;
	}

	public void setExcelCounty(String excelCounty) {
		this.excelCounty = excelCounty;
	}

	public String getExcelLocCode() {
		return excelLocCode;
	}

	public void setExcelLocCode(String excelLocCode) {
		this.excelLocCode = excelLocCode;
	}

	public String getLocCode() {
		return locCode;
	}

	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}

	public String getLocDesc() {
		return locDesc;
	}

	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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

	public String getTime_type() {
		return time_type;
	}

	public void setTime_type(String time_type) {
		this.time_type = time_type;
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
    public long getReportNum(){
        return reportNum;
    }
    public long getCheckedNum(){
        return checkedNum;
    }
    public String getCityDesc(){
        return cityDesc;
    }
    public long getImageNum(){
        return imageNum;
    }
    public String getCityCode(){
        return cityCode;
    }
    public String getCheckDate(){
        return checkDate;
    }
    public String getCountyDesc(){
        return countyDesc;
    }
    public long getId(){
        return id;
    }
    public long getCollectNum(){
        return collectNum;
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
    public  void setCheckedNum(long checkedNum){
        this.checkedNum = checkedNum;
    }
    public  void setCityDesc(String cityDesc){
        this.cityDesc = cityDesc;
    }
    public  void setImageNum(long imageNum){
        this.imageNum = imageNum;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setCheckDate(String checkDate){
        this.checkDate = checkDate;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }
    public  void setId(long id){
        this.id = id;
    }
    public  void setCollectNum(long collectNum){
        this.collectNum = collectNum;
    }
    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setRegistNum(long registNum){
        this.registNum = registNum;
    }

//自定义字典
}
