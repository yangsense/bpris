package com.ai.aris.server.interfacereal.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AisAiDiagnosisModel {

    private  String patientBirthday;
    private  String patientId;
    private  String studyType;
    private  long globalStudyId;
    private  String resultId;
    private  Timestamp uploadTime;
    private  Timestamp updateTime;
    private  String status;
    private  String orgId;
    private  String studyDate;
    private  String patientGender;
    private  String resultMsg;
    private  String message;
    private  String studyId;
    private  String patientName;
    private  String uploadImgNum;
    private  String studyName;
    private  String code;
    private String imgNum;

//自定义字典

    public String getImgNum() {
		return imgNum;
	}
	public void setImgNum(String imgNum) {
		this.imgNum = imgNum;
	}
	public String getPatientBirthday(){
        return patientBirthday;
    }
    public String getStudyType() {
		return studyType;
	}
	public void setStudyType(String studyType) {
		this.studyType = studyType;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getUploadImgNum() {
		return uploadImgNum;
	}
	public void setUploadImgNum(String uploadImgNum) {
		this.uploadImgNum = uploadImgNum;
	}
	public String getPatientId(){
        return patientId;
    }
    public long getGlobalStudyId(){
        return globalStudyId;
    }
    public Timestamp getUploadTime(){
        return uploadTime;
    }
    public Timestamp getUpdateTime(){
        return updateTime;
    }
    public String getStatus(){
        return status;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getStudyDate(){
        return studyDate;
    }
    public String getResultMsg(){
        return resultMsg;
    }
    public String getMessage(){
        return message;
    }
    public String getStudyId(){
        return studyId;
    }
    public String getPatientName(){
        return patientName;
    }
    public String getStudyName(){
        return studyName;
    }
    public String getCode(){
        return code;
    }

    public String getResultId() {
		return resultId;
	}
	public void setResultId(String resultId) {
		this.resultId = resultId;
	}
	public  void setPatientBirthday(String patientBirthday){
        this.patientBirthday = patientBirthday;
    }
    public  void setPatientId(String patientId){
        this.patientId = patientId;
    }
    public  void setGlobalStudyId(long globalStudyId){
        this.globalStudyId = globalStudyId;
    }
    public  void setUploadTime(Timestamp uploadTime){
        this.uploadTime = uploadTime;
    }
    public  void setUpdateTime(Timestamp updateTime){
        this.updateTime = updateTime;
    }
    public  void setStatus(String status){
        this.status = status;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setStudyDate(String studyDate){
        this.studyDate = studyDate;
    }
    public  void setResultMsg(String resultMsg){
        this.resultMsg = resultMsg;
    }
    public  void setMessage(String message){
        this.message = message;
    }
    public  void setStudyId(String studyId){
        this.studyId = studyId;
    }
    public  void setPatientName(String patientName){
        this.patientName = patientName;
    }
    public  void setStudyName(String studyName){
        this.studyName = studyName;
    }
    public  void setCode(String code){
        this.code = code;
    }

//自定义字典
}
