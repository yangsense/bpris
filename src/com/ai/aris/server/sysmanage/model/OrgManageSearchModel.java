package com.ai.aris.server.sysmanage.model;




import com.ai.aris.server.sysmanage.bean.DictBean;
import com.ai.aris.server.sysmanage.bean.IDictValue;

import java.util.List;

/**
 * @author Mysh
 * @since 2014/3/28 16:25
 */
public class OrgManageSearchModel extends BaseSearchModel {

	private static final long serialVersionUID = -7031556891095540338L;

	private String parentOrgId;
	private String orgName;
	private String orgId;
	private int flag;
	
	
    
    //组织机构    
	public List<IDictValue> provinceCodeBean;
    public List<IDictValue> cityCodeBean ;
    public List<IDictValue> districtCodeBean ; 
    public List<IDictValue> streetCodeBean ;
    public List<IDictValue> villageCodeBean  ;    
    public DictBean[] managementCodeBean  ;
    public DictBean[] economicTypeCodeBean  ;
    public DictBean[] orgClassLevelBean  ;
    public DictBean[] orgClassGradeBean  ;
    public DictBean[] organizerTypeCodeBean  ;
    public DictBean[] subordinateRelationshipCodeBean  ;
    public DictBean[] subordinateDivisionCodeBean  ;
    public List<IDictValue> orgTypeFirstList;
    public List<IDictValue> orgTypeSecondList;
    public List<IDictValue> orgTypeThirdList;
    
    //政府办机构隶属行政区划代码特殊处理
    public String provinceCode;
    public String cityCode;
    public String districtCode;
    public String streetCode;
    public String villageCode;
    public String duns;
   
    private String parentItemNo;
    private String itemLevel;
    
	public final String getDuns() {
		return duns;
	}

	public final void setDuns(String duns) {
		this.duns = duns;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getStreetCode() {
		return streetCode;
	}

	public void setStreetCode(String streetCode) {
		this.streetCode = streetCode;
	}

	public String getVillageCode() {
		return villageCode;
	}

	public void setVillageCode(String villageCode) {
		this.villageCode = villageCode;
	}

	public DictBean[] getManagementCodeBean() {
		return managementCodeBean;
	}

	public void setManagementCodeBean(DictBean[] managementCodeBean) {
		this.managementCodeBean = managementCodeBean;
	}

	public DictBean[] getEconomicTypeCodeBean() {
		return economicTypeCodeBean;
	}

	public void setEconomicTypeCodeBean(DictBean[] economicTypeCodeBean) {
		this.economicTypeCodeBean = economicTypeCodeBean;
	}

	public DictBean[] getOrgClassLevelBean() {
		return orgClassLevelBean;
	}

	public void setOrgClassLevelBean(DictBean[] orgClassLevelBean) {
		this.orgClassLevelBean = orgClassLevelBean;
	}

	public DictBean[] getOrgClassGradeBean() {
		return orgClassGradeBean;
	}

	public void setOrgClassGradeBean(DictBean[] orgClassGradeBean) {
		this.orgClassGradeBean = orgClassGradeBean;
	}

	public DictBean[] getOrganizerTypeCodeBean() {
		return organizerTypeCodeBean;
	}

	public void setOrganizerTypeCodeBean(DictBean[] organizerTypeCodeBean) {
		this.organizerTypeCodeBean = organizerTypeCodeBean;
	}

	public DictBean[] getSubordinateRelationshipCodeBean() {
		return subordinateRelationshipCodeBean;
	}

	public void setSubordinateRelationshipCodeBean(
			DictBean[] subordinateRelationshipCodeBean) {
		this.subordinateRelationshipCodeBean = subordinateRelationshipCodeBean;
	}

	public DictBean[] getSubordinateDivisionCodeBean() {
		return subordinateDivisionCodeBean;
	}

	public void setSubordinateDivisionCodeBean(
			DictBean[] subordinateDivisionCodeBean) {
		this.subordinateDivisionCodeBean = subordinateDivisionCodeBean;
	}

	public List<IDictValue> getOrgTypeFirstList() {
		return orgTypeFirstList;
	}

	public void setOrgTypeFirstList(List<IDictValue> orgTypeFirstList) {
		this.orgTypeFirstList = orgTypeFirstList;
	}

	public List<IDictValue> getOrgTypeSecondList() {
		return orgTypeSecondList;
	}

	public void setOrgTypeSecondList(List<IDictValue> orgTypeSecondList) {
		this.orgTypeSecondList = orgTypeSecondList;
	}

	public List<IDictValue> getOrgTypeThirdList() {
		return orgTypeThirdList;
	}

	public void setOrgTypeThirdList(List<IDictValue> orgTypeThirdList) {
		this.orgTypeThirdList = orgTypeThirdList;
	}

	 

	public List<IDictValue> getProvinceCodeBean() {
		return provinceCodeBean;
	}

	public void setProvinceCodeBean(List<IDictValue> provinceCodeBean) {
		this.provinceCodeBean = provinceCodeBean;
	}

	public List<IDictValue> getCityCodeBean() {
		return cityCodeBean;
	}

	public void setCityCodeBean(List<IDictValue> cityCodeBean) {
		this.cityCodeBean = cityCodeBean;
	}

	public List<IDictValue> getDistrictCodeBean() {
		return districtCodeBean;
	}

	public void setDistrictCodeBean(List<IDictValue> districtCodeBean) {
		this.districtCodeBean = districtCodeBean;
	}

	public List<IDictValue> getStreetCodeBean() {
		return streetCodeBean;
	}

	public void setStreetCodeBean(List<IDictValue> streetCodeBean) {
		this.streetCodeBean = streetCodeBean;
	}

	public List<IDictValue> getVillageCodeBean() {
		return villageCodeBean;
	}

	public void setVillageCodeBean(List<IDictValue> villageCodeBean) {
		this.villageCodeBean = villageCodeBean;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getParentItemNo() {
		return parentItemNo;
	}

	public void setParentItemNo(String parentItemNo) {
		this.parentItemNo = parentItemNo;
	}

	public String getItemLevel() {
		return itemLevel;
	}

	public void setItemLevel(String itemLevel) {
		this.itemLevel = itemLevel;
	}

	 
	
}
