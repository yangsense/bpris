package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;

public class QryHzMultipleListModel {

    private  String countyCode;
    private  String cityCode;
    private  String studyDatetime;
    private  String orgName;
    private  String countyDesc;
    private  long hzsqsl;
    private  long hzsl;
    private  String orgId;
    private String checkDate;
    private String studyStarttime;
    private String studyEndtime;
    
    private String categories;
    private String startAge;
    private String endAge;

    private String patientAge;
    private String patientName;
    private String patientSex;
    private long studyinfoId;
    private String studyAccnumber;
    private String reportExam;
    private String reportResult;
    private Timestamp sdatetime;
    
    private String startDate;
    private String endDate;
    
    private  String cityDesc;
    private String time_type;
    private String yearDate;
    private String yearDateOfJD;
    private String jd;
    private String dateOfDay;
    private String monthDate;
    private  long hzCount;
    private  long hzResultcount;
    
    private  String checkDoc;
    private  String reportDoc;
    private  String checkItem;
    private  String studyConsultlocname;
    private  String careprovName;
    private  String studyConsultorgname;
    private  Timestamp reportDatetime;
    private  Timestamp studyAppdate;
    private String locDesc;
    private String locCode;
    private String excelOrgId;
    private String excelCityCode;
    private String excelCounty;
    private String excelLocCode;
//自定义字典
    private String patientId;
    
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getLocCode() {
		return locCode;
	}
	public void setLocCode(String locCode) {
		this.locCode = locCode;
	}
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
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	public String getStartDate() {
		return startDate;
	}
	public Timestamp getStudyAppdate() {
		return studyAppdate;
	}
	public void setStudyAppdate(Timestamp studyAppdate) {
		this.studyAppdate = studyAppdate;
	}
	public String getCheckItem() {
		return checkItem;
	}
	public void setCheckItem(String checkItem) {
		this.checkItem = checkItem;
	}
	public String getStudyConsultlocname() {
		return studyConsultlocname;
	}
	public void setStudyConsultlocname(String studyConsultlocname) {
		this.studyConsultlocname = studyConsultlocname;
	}
	public String getCareprovName() {
		return careprovName;
	}
	public void setCareprovName(String careprovName) {
		this.careprovName = careprovName;
	}
	public String getStudyConsultorgname() {
		return studyConsultorgname;
	}
	public void setStudyConsultorgname(String studyConsultorgname) {
		this.studyConsultorgname = studyConsultorgname;
	}
	public Timestamp getReportDatetime() {
		return reportDatetime;
	}
	public void setReportDatetime(Timestamp reportDatetime) {
		this.reportDatetime = reportDatetime;
	}
	public String getCheckDoc() {
		return checkDoc;
	}
	public void setCheckDoc(String checkDoc) {
		this.checkDoc = checkDoc;
	}
	public String getReportDoc() {
		return reportDoc;
	}
	public void setReportDoc(String reportDoc) {
		this.reportDoc = reportDoc;
	}
	public long getHzCount() {
		return hzCount;
	}
	public void setHzCount(long hzCount) {
		this.hzCount = hzCount;
	}
	public long getHzResultcount() {
		return hzResultcount;
	}
	public void setHzResultcount(long hzResultcount) {
		this.hzResultcount = hzResultcount;
	}
	public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
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
	public String getCountyCode(){
        return countyCode;
    }
	public Timestamp getSdatetime() {
		return sdatetime;
	}
	public void setSdatetime(Timestamp sdatetime) {
		this.sdatetime = sdatetime;
	}
	public String getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(String patientAge) {
		this.patientAge = patientAge;
	}
	public long getStudyinfoId() {
		return studyinfoId;
	}
	public void setStudyinfoId(long studyinfoId) {
		this.studyinfoId = studyinfoId;
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
	public String getStudyAccnumber() {
		return studyAccnumber;
	}
	public void setStudyAccnumber(String studyAccnumber) {
		this.studyAccnumber = studyAccnumber;
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
	public String getStudyStarttime() {
		return studyStarttime;
	}
	public void setStudyStarttime(String studyStarttime) {
		this.studyStarttime = studyStarttime;
	}
	public String getStudyEndtime() {
		return studyEndtime;
	}
	public void setStudyEndtime(String studyEndtime) {
		this.studyEndtime = studyEndtime;
	}
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getCityCode(){
        return cityCode;
    }
    public String getStudyDatetime(){
        return studyDatetime;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getCountyDesc(){
        return countyDesc;
    }
    public long getHzsqsl(){
        return hzsqsl;
    }
    public long getHzsl(){
        return hzsl;
    }
    public String getOrgId(){
        return orgId;
    }

    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setStudyDatetime(String studyDatetime){
        this.studyDatetime = studyDatetime;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }
    public  void setHzsqsl(long hzsqsl){
        this.hzsqsl = hzsqsl;
    }
    public  void setHzsl(long hzsl){
        this.hzsl = hzsl;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }

//自定义字典
}
