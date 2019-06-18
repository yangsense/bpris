package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QueryDataBaseModel {

    private  String sourceUrl;
    private  String cityCode;
    private  String countyCode;
    private  long sourceId;
    private  String orgName;
    private  String status;
    private  String countyDesc;
    private  String orgId;
    private  String cityDesc;
    private  String patientTypecode;
    private  long haspacs;
    private  String sourceUser;
    private  String sourcePassword;

//自定义字典
    private String cityCode2;
    public String getSelectType() {
		return selectType;
	}
	public void setSelectType(String selectType) {
		this.selectType = selectType;
	}
	private String selectType;
    public String getCityCode2() {
		return cityCode2;
	}
	public void setCityCode2(String cityCode2) {
		this.cityCode2 = cityCode2;
	}
	public String getCountyCode2() {
		return countyCode2;
	}
	public void setCountyCode2(String countyCode2) {
		this.countyCode2 = countyCode2;
	}
	public String getOrgId2() {
		return orgId2;
	}
	public void setOrgId2(String orgId2) {
		this.orgId2 = orgId2;
	}
	private String countyCode2;
    private String orgId2;

    public String getSourceUrl(){
        return sourceUrl;
    }
    public String getCityCode(){
        return cityCode;
    }
    public String getCountyCode(){
        return countyCode;
    }
    public long getSourceId(){
        return sourceId;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getStatus(){
        return status;
    }
    public String getCountyDesc(){
        return countyDesc;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getCityDesc(){
        return cityDesc;
    }
    public String getPatientTypecode(){
        return patientTypecode;
    }
    public long getHaspacs(){
        return haspacs;
    }
    public String getSourceUser(){
        return sourceUser;
    }
    public String getSourcePassword(){
        return sourcePassword;
    }

    public  void setSourceUrl(String sourceUrl){
        this.sourceUrl = sourceUrl;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    public  void setSourceId(long sourceId){
        this.sourceId = sourceId;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setStatus(String status){
        this.status = status;
    }
    public  void setCountyDesc(String countyDesc){
        this.countyDesc = countyDesc;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setCityDesc(String cityDesc){
        this.cityDesc = cityDesc;
    }
    public  void setPatientTypecode(String patientTypecode){
        this.patientTypecode = patientTypecode;
    }
    public  void setHaspacs(long haspacs){
        this.haspacs = haspacs;
    }
    public  void setSourceUser(String sourceUser){
        this.sourceUser = sourceUser;
    }
    public  void setSourcePassword(String sourcePassword){
        this.sourcePassword = sourcePassword;
    }

//自定义字典
}
