package com.ai.aris.server.statanalysis.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryHzRecordListModel {

    private  String patientSex;
    private  long studyConsultloc;
    private  String orgName;
    private  String locDesc;
    private  String operatorName;
    private  String studyConsultlocname;
    private  String studyConsultorg;
    private  String orgId;
    private  Timestamp studyDatetime;
    private  String patientName;
    private  long locId;
    private  String careprovName;
    private  String studyConsultorgname;
    private  String patientAge;
    private  Timestamp reportDatetime;
	private String studyStarttime;
	private String studyEndtime;
//自定义字典

    public String getPatientSex(){
        return patientSex;
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
	public long getStudyConsultloc(){
        return studyConsultloc;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getLocDesc(){
        return locDesc;
    }
    public String getOperatorName(){
        return operatorName;
    }
    public String getStudyConsultlocname(){
        return studyConsultlocname;
    }
    public String getStudyConsultorg(){
        return studyConsultorg;
    }
    public String getOrgId(){
        return orgId;
    }
    public Timestamp getStudyDatetime(){
        return studyDatetime;
    }
    public String getPatientName(){
        return patientName;
    }
    public long getLocId(){
        return locId;
    }
    public String getCareprovName(){
        return careprovName;
    }
    public String getStudyConsultorgname(){
        return studyConsultorgname;
    }
    public String getPatientAge(){
        return patientAge;
    }
    public Timestamp getReportDatetime(){
        return reportDatetime;
    }

    public  void setPatientSex(String patientSex){
        this.patientSex = patientSex;
    }
    public  void setStudyConsultloc(long studyConsultloc){
        this.studyConsultloc = studyConsultloc;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setLocDesc(String locDesc){
        this.locDesc = locDesc;
    }
    public  void setOperatorName(String operatorName){
        this.operatorName = operatorName;
    }
    public  void setStudyConsultlocname(String studyConsultlocname){
        this.studyConsultlocname = studyConsultlocname;
    }
    public  void setStudyConsultorg(String studyConsultorg){
        this.studyConsultorg = studyConsultorg;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setStudyDatetime(Timestamp studyDatetime){
        this.studyDatetime = studyDatetime;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setLocId(long locId){
        this.locId = locId;
    }
    public  void setCareprovName(String careprovName){
        this.careprovName = careprovName;
    }
    public  void setStudyConsultorgname(String studyConsultorgname){
        this.studyConsultorgname = studyConsultorgname;
    }
    public  void setPatientAge(String patientAge){
        this.patientAge = patientAge;
    }
    public  void setReportDatetime(Timestamp reportDatetime){
        this.reportDatetime = reportDatetime;
    }

//自定义字典
}
