package com.ai.aris.server.sysmanage.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class SysOrgModel {

    private  String webUrl;
    private  String state;
    private  String setupTime;
    private  String remarks;
    private  String orgName;
    private  Timestamp createTime;
    private  String contactName;
    private  String villageCode;
    private  String orgType;
    private  String lawPersonTel;
    private  String branchMark;
    private  String ybMark;
    private  String postcode;
    private  String economicTypeCode;
    private  String orgClassLevel;
    private  long orgLevel;
    private  String contactTel;
    private  String xnhMark;
    private  String orgAddr;
    private  String orgTypeFirst;
    private  String lawPersonName;
    private  String orgHead;
    private  String managementCode;
    private  String orgIntroduct;
    private  String orgTypeThird;
    private  String email;
    private  String districtCode;
    private  String districtDesc;
    private  String createOrgCode;
    private  String provinceCode;
    private  String orgHeadTelephone;
    private  String cityCode;
    private  String cityDesc;
    private  String orgClassGrade;
    private  String streetCode;
    private  String xnhDuns;
    private  String createUserId;
    private  Timestamp updateTime;
    private  String orgId;
    private  String orgTypeSecond;
    private  String subordinateDivisionCode;
    private  String subordinateRelationshipCode;
    private  String duns;
    private  String updateUserId;
    private  String faxNo;
    private  String organizerTypeCode;
    private  String parentOrgId;
    private  int haspacs;
    private  String orgCode;
    private  String orgShort;

//自定义字典

    public String getWebUrl(){
        return webUrl;
    }
    public String getDistrictDesc() {
		return districtDesc;
	}
	public void setDistrictDesc(String districtDesc) {
		this.districtDesc = districtDesc;
	}
	public String getCityDesc() {
		return cityDesc;
	}
	public void setCityDesc(String cityDesc) {
		this.cityDesc = cityDesc;
	}
	public String getState(){
        return state;
    }
    public String getSetupTime(){
        return setupTime;
    }
    public String getRemarks(){
        return remarks;
    }
    public String getOrgName(){
        return orgName;
    }
    public Timestamp getCreateTime(){
        return createTime;
    }
    public String getContactName(){
        return contactName;
    }
    public String getVillageCode(){
        return villageCode;
    }
    public String getOrgType(){
        return orgType;
    }
    public String getLawPersonTel(){
        return lawPersonTel;
    }
    public String getBranchMark(){
        return branchMark;
    }
    public String getYbMark(){
        return ybMark;
    }
    public String getPostcode(){
        return postcode;
    }
    public String getEconomicTypeCode(){
        return economicTypeCode;
    }
    public String getOrgClassLevel(){
        return orgClassLevel;
    }
    public long getOrgLevel(){
        return orgLevel;
    }
    public String getContactTel(){
        return contactTel;
    }
    public String getXnhMark(){
        return xnhMark;
    }
    public String getOrgAddr(){
        return orgAddr;
    }
    public String getOrgTypeFirst(){
        return orgTypeFirst;
    }
    public String getLawPersonName(){
        return lawPersonName;
    }
    public String getOrgHead(){
        return orgHead;
    }
    public String getManagementCode(){
        return managementCode;
    }
    public String getOrgIntroduct(){
        return orgIntroduct;
    }
    public String getOrgTypeThird(){
        return orgTypeThird;
    }
    public String getEmail(){
        return email;
    }
    public String getDistrictCode(){
        return districtCode;
    }
    public String getCreateOrgCode(){
        return createOrgCode;
    }
    public String getProvinceCode(){
        return provinceCode;
    }
    public String getOrgHeadTelephone(){
        return orgHeadTelephone;
    }
    public String getCityCode(){
        return cityCode;
    }
    public String getOrgClassGrade(){
        return orgClassGrade;
    }
    public String getStreetCode(){
        return streetCode;
    }
    public String getXnhDuns(){
        return xnhDuns;
    }
    public String getCreateUserId(){
        return createUserId;
    }
    public Timestamp getUpdateTime(){
        return updateTime;
    }
    public String getOrgId(){
        return orgId;
    }
    public String getOrgTypeSecond(){
        return orgTypeSecond;
    }
    public String getSubordinateDivisionCode(){
        return subordinateDivisionCode;
    }
    public String getSubordinateRelationshipCode(){
        return subordinateRelationshipCode;
    }
    public String getDuns(){
        return duns;
    }
    public String getUpdateUserId(){
        return updateUserId;
    }
    public String getFaxNo(){
        return faxNo;
    }
    public String getOrganizerTypeCode(){
        return organizerTypeCode;
    }
    public String getParentOrgId(){
        return parentOrgId;
    }
    public int getHaspacs(){
        return haspacs;
    }
    public String getOrgCode(){
        return orgCode;
    }
    public String getOrgShort(){
        return orgShort;
    }

    public  void setWebUrl(String webUrl){
        this.webUrl = webUrl;
    }
    public  void setState(String state){
        this.state = state;
    }
    public  void setSetupTime(String setupTime){
        this.setupTime = setupTime;
    }
    public  void setRemarks(String remarks){
        this.remarks = remarks;
    }
    public  void setOrgName(String orgName){
        this.orgName = orgName;
    }
    public  void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }
    public  void setContactName(String contactName){
        this.contactName = contactName;
    }
    public  void setVillageCode(String villageCode){
        this.villageCode = villageCode;
    }
    public  void setOrgType(String orgType){
        this.orgType = orgType;
    }
    public  void setLawPersonTel(String lawPersonTel){
        this.lawPersonTel = lawPersonTel;
    }
    public  void setBranchMark(String branchMark){
        this.branchMark = branchMark;
    }
    public  void setYbMark(String ybMark){
        this.ybMark = ybMark;
    }
    public  void setPostcode(String postcode){
        this.postcode = postcode;
    }
    public  void setEconomicTypeCode(String economicTypeCode){
        this.economicTypeCode = economicTypeCode;
    }
    public  void setOrgClassLevel(String orgClassLevel){
        this.orgClassLevel = orgClassLevel;
    }
    public  void setOrgLevel(long orgLevel){
        this.orgLevel = orgLevel;
    }
    public  void setContactTel(String contactTel){
        this.contactTel = contactTel;
    }
    public  void setXnhMark(String xnhMark){
        this.xnhMark = xnhMark;
    }
    public  void setOrgAddr(String orgAddr){
        this.orgAddr = orgAddr;
    }
    public  void setOrgTypeFirst(String orgTypeFirst){
        this.orgTypeFirst = orgTypeFirst;
    }
    public  void setLawPersonName(String lawPersonName){
        this.lawPersonName = lawPersonName;
    }
    public  void setOrgHead(String orgHead){
        this.orgHead = orgHead;
    }
    public  void setManagementCode(String managementCode){
        this.managementCode = managementCode;
    }
    public  void setOrgIntroduct(String orgIntroduct){
        this.orgIntroduct = orgIntroduct;
    }
    public  void setOrgTypeThird(String orgTypeThird){
        this.orgTypeThird = orgTypeThird;
    }
    public  void setEmail(String email){
        this.email = email;
    }
    public  void setDistrictCode(String districtCode){
        this.districtCode = districtCode;
    }
    public  void setCreateOrgCode(String createOrgCode){
        this.createOrgCode = createOrgCode;
    }
    public  void setProvinceCode(String provinceCode){
        this.provinceCode = provinceCode;
    }
    public  void setOrgHeadTelephone(String orgHeadTelephone){
        this.orgHeadTelephone = orgHeadTelephone;
    }
    public  void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    public  void setOrgClassGrade(String orgClassGrade){
        this.orgClassGrade = orgClassGrade;
    }
    public  void setStreetCode(String streetCode){
        this.streetCode = streetCode;
    }
    public  void setXnhDuns(String xnhDuns){
        this.xnhDuns = xnhDuns;
    }
    public  void setCreateUserId(String createUserId){
        this.createUserId = createUserId;
    }
    public  void setUpdateTime(Timestamp updateTime){
        this.updateTime = updateTime;
    }
    public  void setOrgId(String orgId){
        this.orgId = orgId;
    }
    public  void setOrgTypeSecond(String orgTypeSecond){
        this.orgTypeSecond = orgTypeSecond;
    }
    public  void setSubordinateDivisionCode(String subordinateDivisionCode){
        this.subordinateDivisionCode = subordinateDivisionCode;
    }
    public  void setSubordinateRelationshipCode(String subordinateRelationshipCode){
        this.subordinateRelationshipCode = subordinateRelationshipCode;
    }
    public  void setDuns(String duns){
        this.duns = duns;
    }
    public  void setUpdateUserId(String updateUserId){
        this.updateUserId = updateUserId;
    }
    public  void setFaxNo(String faxNo){
        this.faxNo = faxNo;
    }
    public  void setOrganizerTypeCode(String organizerTypeCode){
        this.organizerTypeCode = organizerTypeCode;
    }
    public  void setParentOrgId(String parentOrgId){
        this.parentOrgId = parentOrgId;
    }
    public  void setHaspacs(int haspacs){
        this.haspacs = haspacs;
    }
    public  void setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
    public  void setOrgShort(String orgShort){
        this.orgShort = orgShort;
    }

//自定义字典
}
