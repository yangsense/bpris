package com.ai.aris.server.workstation.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QryStudyPatientModel {

    private  String studydate;
    private  String modalitiesinstudy;
    private  String birthdate;
    private  String institutionid;
    private  String gender;
    private  String patientid;
    private  long patientkey;
    private  String patientname;
    private  String accessionnumber;
    
    private String orgId;
    private String startDate;
    private String endDate;
    private String qType;
    private String qValue;
//自定义字典

    public String getStudydate(){
        return studydate;
    }
    public String getqValue() {
		return qValue;
	}
	public void setqValue(String qValue) {
		this.qValue = qValue;
	}
	public String getqType() {
		return qType;
	}
	public void setqType(String qType) {
		this.qType = qType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
	public String getModalitiesinstudy(){
        return modalitiesinstudy;
    }
    public String getBirthdate(){
        return birthdate;
    }
    public String getInstitutionid(){
        return institutionid;
    }
    public String getGender(){
        return gender;
    }
    public String getPatientid(){
        return patientid;
    }
    public long getPatientkey(){
        return patientkey;
    }
    public String getPatientname(){
        return patientname;
    }
    public String getAccessionnumber(){
        return accessionnumber;
    }

    public  void setStudydate(String studydate){
        this.studydate = studydate;
    }
    public  void setModalitiesinstudy(String modalitiesinstudy){
        this.modalitiesinstudy = modalitiesinstudy;
    }
    public  void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }
    public  void setInstitutionid(String institutionid){
        this.institutionid = institutionid;
    }
    public  void setGender(String gender){
        this.gender = gender;
    }
    public  void setPatientid(String patientid){
        this.patientid = patientid;
    }
    public  void setPatientkey(long patientkey){
        this.patientkey = patientkey;
    }
    public  void setPatientname(String patientname){
        this.patientname = patientname;
    }
    public  void setAccessionnumber(String accessionnumber){
        this.accessionnumber = accessionnumber;
    }

//自定义字典
}
