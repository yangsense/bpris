package com.ai.aris.server.webservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 12:40
 * Email:zhangfengzhou@asiainfo.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOrgBean implements Serializable{
    private  String webUrl;
    private  String state;
    private  String setupTime;

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getLawPersonTel() {
        return lawPersonTel;
    }

    public void setLawPersonTel(String lawPersonTel) {
        this.lawPersonTel = lawPersonTel;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
    }

    public String getBranchMark() {
        return branchMark;
    }

    public void setBranchMark(String branchMark) {
        this.branchMark = branchMark;
    }

    public String getYbMark() {
        return ybMark;
    }

    public void setYbMark(String ybMark) {
        this.ybMark = ybMark;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEconomicTypeCode() {
        return economicTypeCode;
    }

    public void setEconomicTypeCode(String economicTypeCode) {
        this.economicTypeCode = economicTypeCode;
    }

    public String getOrgClassLevel() {
        return orgClassLevel;
    }

    public void setOrgClassLevel(String orgClassLevel) {
        this.orgClassLevel = orgClassLevel;
    }

    public String getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(String orgLevel) {
        this.orgLevel = orgLevel;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getXnhMark() {
        return xnhMark;
    }

    public void setXnhMark(String xnhMark) {
        this.xnhMark = xnhMark;
    }

    public String getOrgAddr() {
        return orgAddr;
    }

    public void setOrgAddr(String orgAddr) {
        this.orgAddr = orgAddr;
    }

    public String getOrgTypeFirst() {
        return orgTypeFirst;
    }

    public void setOrgTypeFirst(String orgTypeFirst) {
        this.orgTypeFirst = orgTypeFirst;
    }

    public String getLawPersonName() {
        return lawPersonName;
    }

    public void setLawPersonName(String lawPersonName) {
        this.lawPersonName = lawPersonName;
    }

    public String getOrgHead() {
        return orgHead;
    }

    public void setOrgHead(String orgHead) {
        this.orgHead = orgHead;
    }

    public String getManagementCode() {
        return managementCode;
    }

    public void setManagementCode(String managementCode) {
        this.managementCode = managementCode;
    }

    public String getOrgIntroduct() {
        return orgIntroduct;
    }

    public void setOrgIntroduct(String orgIntroduct) {
        this.orgIntroduct = orgIntroduct;
    }

    public String getOrgTypeThird() {
        return orgTypeThird;
    }

    public void setOrgTypeThird(String orgTypeThird) {
        this.orgTypeThird = orgTypeThird;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCreateOrgCode() {
        return createOrgCode;
    }

    public void setCreateOrgCode(String createOrgCode) {
        this.createOrgCode = createOrgCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getOrgHeadTelephone() {
        return orgHeadTelephone;
    }

    public void setOrgHeadTelephone(String orgHeadTelephone) {
        this.orgHeadTelephone = orgHeadTelephone;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getOrgClassGrade() {
        return orgClassGrade;
    }

    public void setOrgClassGrade(String orgClassGrade) {
        this.orgClassGrade = orgClassGrade;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgTypeSecond() {
        return orgTypeSecond;
    }

    public void setOrgTypeSecond(String orgTypeSecond) {
        this.orgTypeSecond = orgTypeSecond;
    }

    public String getSubordinateDivisionCode() {
        return subordinateDivisionCode;
    }

    public void setSubordinateDivisionCode(String subordinateDivisionCode) {
        this.subordinateDivisionCode = subordinateDivisionCode;
    }

    public String getSubordinateRelationshipCode() {
        return subordinateRelationshipCode;
    }

    public void setSubordinateRelationshipCode(String subordinateRelationshipCode) {
        this.subordinateRelationshipCode = subordinateRelationshipCode;
    }

    public String getDuns() {
        return duns;
    }

    public void setDuns(String duns) {
        this.duns = duns;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getOrganizerTypeCode() {
        return organizerTypeCode;
    }

    public void setOrganizerTypeCode(String organizerTypeCode) {
        this.organizerTypeCode = organizerTypeCode;
    }

    public String getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(String parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgShort() {
        return orgShort;
    }

    public void setOrgShort(String orgShort) {
        this.orgShort = orgShort;
    }

    private  String remarks;
    private  String orgName;
    private  String createTime;
    private  String contactName;
    private  String villageCode;
    private  String orgType;
    private  String lawPersonTel;
    private  String children;
    private  String branchMark;
    private  String ybMark;
    private  String postcode;
    private  String economicTypeCode;
    private  String orgClassLevel;
    private  String orgLevel;
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
    private  String createOrgCode;
    private  String provinceCode;
    private  String orgHeadTelephone;
    private  String cityCode;
    private  String orgClassGrade;
    private  String streetCode;
    private  String createUserId;
    private  String updateTime;
    private  String orgId;
    private  String orgTypeSecond;
    private  String subordinateDivisionCode;
    private  String subordinateRelationshipCode;
    private  String duns;
    private  String updateUserId;
    private  String faxNo;
    private  String organizerTypeCode;
    private  String parentOrgId;
    private  String orgCode;
    private  String orgShort;
}
