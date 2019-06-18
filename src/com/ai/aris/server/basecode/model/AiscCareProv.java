package com.ai.aris.server.basecode.model;

public class AiscCareProv {
	private Long careprovId;
    private Long locId;
    private Long careprovTypeid;
    private String orgId;
    private String careprovCode;
    private String careprovName;
    
    private String orgName;
    private String locName;
    
    private String careprovTypeDesc;

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

	public Long getCareprovId() {
		return careprovId;
	}

	public void setCareprovId(Long careprovId) {
		this.careprovId = careprovId;
	}

	public Long getLocId() {
		return locId;
	}

	public void setLocId(Long locId) {
		this.locId = locId;
	}

	public Long getCareprovTypeid() {
		return careprovTypeid;
	}

	public void setCareprovTypeid(Long careprovTypeid) {
		this.careprovTypeid = careprovTypeid;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCareprovCode() {
		return careprovCode;
	}

	public void setCareprovCode(String careprovCode) {
		this.careprovCode = careprovCode;
	}

	public String getCareprovName() {
		return careprovName;
	}

	public void setCareprovName(String careprovName) {
		this.careprovName = careprovName;
	}

	public String getCareprovTypeDesc() {
		return careprovTypeDesc;
	}

	public void setCareprovTypeDesc(String careprovTypeDesc) {
		this.careprovTypeDesc = careprovTypeDesc;
	}

}
