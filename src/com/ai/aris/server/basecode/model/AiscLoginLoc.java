package com.ai.aris.server.basecode.model;

public class AiscLoginLoc {
	private Long loginlocId;
	private Long operatorId;
	private String orgId;
	private Long locId;
	
	private String orgName;
	private String locName;
	private String careprovName;
	private String operatorCode;
	private Long careprovId;
	private String selectOption;
	
	public Long getCareprovId() {
		return careprovId;
	}
	public void setCareprovId(Long careprovId) {
		this.careprovId = careprovId;
	}
	public String getSelectOption() {
		return selectOption;
	}
	public void setSelectOption(String selectOption) {
		this.selectOption = selectOption;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getCareprovName() {
		return careprovName;
	}
	public void setCareprovName(String careprovName) {
		this.careprovName = careprovName;
	}
	public Long getLoginlocId() {
		return loginlocId;
	}
	public void setLoginlocId(Long loginlocId) {
		this.loginlocId = loginlocId;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public Long getLocId() {
		return locId;
	}
	public void setLocId(Long locId) {
		this.locId = locId;
	}
	
	
}
