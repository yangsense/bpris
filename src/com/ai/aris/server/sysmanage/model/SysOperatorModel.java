package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOperatorModel {

    private  Timestamp birthday;
    private  long operatorId;
    private  String remarks;
    private  String telNo;
    private  String certNo;
    private  String positionCode;
    private  Timestamp changePswDate;
    private  String empPhoto;
    private  String addressVillageCode;
    private  String operatorName;
    private  String positionLevelCode;
    private  String addressTownCode;
    private  String orgId;
    private  String operatorCode;
    private  String addressDetail;
    private  String sexCode;
    private  String addressAreaCode;
    private  String operatorPsw;
    private  String addressProvCode;
    private  String addressCityCode;
    private  String email;
    private  Timestamp createDate;
    private  long operatorState;

    private String officeId;
    private String isUpdate;
//自定义字典

    public Timestamp getBirthday(){
        return birthday;
    }
    public long getOperatorId(){
        return operatorId;
    }
    public String getRemarks(){
        return remarks;
    }
    public String getTelNo(){
        return telNo;
    }
    public String getCertNo(){
        return certNo;
    }
    public String getPositionCode(){
        return positionCode;
    }
    public Timestamp getChangePswDate(){
        return changePswDate;
    }
    public String getEmpPhoto(){
        return empPhoto;
    }
    public String getAddressVillageCode(){
        return addressVillageCode;
    }
    public String getOperatorName(){
        return operatorName;
    }
    public String getPositionLevelCode(){
        return positionLevelCode;
    }
    public String getAddressTownCode(){
        return addressTownCode;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getOperatorCode(){
        return operatorCode;
    }
    public String getAddressDetail(){
        return addressDetail;
    }
    public String getSexCode(){
        return sexCode;
    }
    public String getAddressAreaCode(){
        return addressAreaCode;
    }
    public String getOperatorPsw(){
        return operatorPsw;
    }
    public String getAddressProvCode(){
        return addressProvCode;
    }
    public String getAddressCityCode(){
        return addressCityCode;
    }
    public String getEmail(){
        return email;
    }
    public Timestamp getCreateDate(){
        return createDate;
    }
    public long getOperatorState(){
        return operatorState;
    }

    public  void setBirthday(Timestamp birthday){
        this.birthday = birthday;
    }
    public  void setOperatorId(long operatorId){
        this.operatorId = operatorId;
    }
    public  void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public  void setTelNo(String telNo){
        this.telNo = telNo;
    }
    public  void setCertNo(String certNo){
        this.certNo = certNo;
    }
    public  void setPositionCode(String positionCode){
        this.positionCode = positionCode;
    }
    public  void setChangePswDate(Timestamp changePswDate){
        this.changePswDate = changePswDate;
    }
    public  void setEmpPhoto(String empPhoto){
        this.empPhoto = empPhoto;
    }
    public  void setAddressVillageCode(String addressVillageCode){
        this.addressVillageCode = addressVillageCode;
    }
    public  void setOperatorName(String operatorName){
        this.operatorName = operatorName;
    }
    public  void setPositionLevelCode(String positionLevelCode){
        this.positionLevelCode = positionLevelCode;
    }
    public  void setAddressTownCode(String addressTownCode){
        this.addressTownCode = addressTownCode;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setOperatorCode(String operatorCode){
        this.operatorCode = operatorCode;
    }
    public  void setAddressDetail(String addressDetail){
        this.addressDetail = addressDetail;
    }
    public  void setSexCode(String sexCode){
        this.sexCode = sexCode;
    }
    public  void setAddressAreaCode(String addressAreaCode){
        this.addressAreaCode = addressAreaCode;
    }
    public  void setOperatorPsw(String operatorPsw){
        this.operatorPsw = operatorPsw;
    }
    public  void setAddressProvCode(String addressProvCode){
        this.addressProvCode = addressProvCode;
    }
    public  void setAddressCityCode(String addressCityCode){
        this.addressCityCode = addressCityCode;
    }
    public  void setEmail(String email){
        this.email = email;
    }
    public  void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }
    public  void setOperatorState(long operatorState){
        this.operatorState = operatorState;
    }

//自定义字典


    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }


}
