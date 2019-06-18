package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class QuerySysOperatorModel {

    private  long operatorId;
    private  String orgName;
    private  String remarks;
    private  String certNo;
    private  String empPhoto;
    private  String addressVillageCode;
    private  String addressDetail;
    private  String addressAreaCode;
    private  String sexCode;
    private  String operatorPsw;
    private  String email;
    private  long operatorState;
    private  Timestamp birthday;
    private  String showChangePswDate;
    private  String telNo;
    private  Timestamp changePswDate;
    private  String positionCode;
    private  String operatorName;
    private  String positionLevelCode;
    private  String addressTownCode;
    private  String orgId;
    private  String operatorCode;
    private  String addressProvCode;
    private  String addressCityCode;
    private  Timestamp createDate;
    private  String showCreateDate;

//自定义字典
    @DictItem(valueFiled = "operatorState", dictType = "OPERATOR_STATE")
    private String operatorStateDesc;

    /**
     *自定义属性
     */
    //操作开始时间
    private String operateTimeStart;

    //操作结束时间
    private String operateTimeEnd;

    public long getOperatorId(){
        return operatorId;
    }
    public String getOrgName(){
        return orgName;
    }
    public String getRemarks(){
        return remarks;
    }
    public String getCertNo(){
        return certNo;
    }
    public String getEmpPhoto(){
        return empPhoto;
    }
    public String getAddressVillageCode(){
        return addressVillageCode;
    }
    public String getAddressDetail(){
        return addressDetail;
    }
    public String getAddressAreaCode(){
        return addressAreaCode;
    }
    public String getSexCode(){
        return sexCode;
    }
    public String getOperatorPsw(){
        return operatorPsw;
    }
    public String getEmail(){
        return email;
    }
    public long getOperatorState(){
        return operatorState;
    }
    public Timestamp getBirthday(){
        return birthday;
    }
    public String getShowChangePswDate(){
        return showChangePswDate;
    }
    public String getTelNo(){
        return telNo;
    }
    public Timestamp getChangePswDate(){
        return changePswDate;
    }
    public String getPositionCode(){
        return positionCode;
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
    public String getAddressProvCode(){
        return addressProvCode;
    }
    public String getAddressCityCode(){
        return addressCityCode;
    }
    public Timestamp getCreateDate(){
        return createDate;
    }
    public String getShowCreateDate(){
        return showCreateDate;
    }

    public  void setOperatorId(long operatorId){
        this.operatorId = operatorId;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public  void setCertNo(String certNo){
        this.certNo = certNo;
    }
    public  void setEmpPhoto(String empPhoto){
        this.empPhoto = empPhoto;
    }
    public  void setAddressVillageCode(String addressVillageCode){
        this.addressVillageCode = addressVillageCode;
    }
    public  void setAddressDetail(String addressDetail){
        this.addressDetail = addressDetail;
    }
    public  void setAddressAreaCode(String addressAreaCode){
        this.addressAreaCode = addressAreaCode;
    }
    public  void setSexCode(String sexCode){
        this.sexCode = sexCode;
    }
    public  void setOperatorPsw(String operatorPsw){
        this.operatorPsw = operatorPsw;
    }
    public  void setEmail(String email){
        this.email = email;
    }
    public  void setOperatorState(long operatorState){
        this.operatorState = operatorState;
    }
    public  void setBirthday(Timestamp birthday){
        this.birthday = birthday;
    }
    public  void setShowChangePswDate(String showChangePswDate){
        this.showChangePswDate = showChangePswDate;
    }
    public  void setTelNo(String telNo){
        this.telNo = telNo;
    }
    public  void setChangePswDate(Timestamp changePswDate){
        this.changePswDate = changePswDate;
    }
    public  void setPositionCode(String positionCode){
        this.positionCode = positionCode;
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
    public  void setAddressProvCode(String addressProvCode){
        this.addressProvCode = addressProvCode;
    }
    public  void setAddressCityCode(String addressCityCode){
        this.addressCityCode = addressCityCode;
    }
    public  void setCreateDate(Timestamp createDate){
        this.createDate = createDate;
    }
    public  void setShowCreateDate(String showCreateDate){
        this.showCreateDate = showCreateDate;
    }

//自定义字典
    public String getOperatorStateDesc(){
           return this.operatorStateDesc;
    }

    public void setOperatorStateDesc(String operatorStateDesc){
           this.operatorStateDesc = operatorStateDesc;
    }

    public String getOperateTimeStart() {
        return operateTimeStart;
    }

    public void setOperateTimeStart(String operateTimeStart) {
        this.operateTimeStart = operateTimeStart;
    }

    public String getOperateTimeEnd() {
        return operateTimeEnd;
    }

    public void setOperateTimeEnd(String operateTimeEnd) {
        this.operateTimeEnd = operateTimeEnd;
    }


}
