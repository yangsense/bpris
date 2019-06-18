package com.ai.aris.server.basecode.model;

public class AiscRule {
	private Long ruleId;
	private String orgId;
	private long locId;
	private String ruleField;
	private long ruleType;
	private String rulePrix;
	private long ruleStartindex;
	private long ruleLen;
	private String ruleTypeDesc;
	private String orgName;
	private String locDesc;
	
	
	
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getLocDesc() {
		return locDesc;
	}
	public void setLocDesc(String locDesc) {
		this.locDesc = locDesc;
	}
	public String getRuleTypeDesc() {
		return ruleTypeDesc;
	}
	public void setRuleTypeDesc(String ruleTypeDesc) {
		this.ruleTypeDesc = ruleTypeDesc;
	}
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public long getLocId() {
		return locId;
	}
	public void setLocId(long locId) {
		this.locId = locId;
	}
	public String getRuleField() {
		return ruleField;
	}
	public void setRuleField(String ruleField) {
		this.ruleField = ruleField;
	}
	public long getRuleType() {
		return ruleType;
	}
	public void setRuleType(long ruleType) {
		this.ruleType = ruleType;
	}
	public String getRulePrix() {
		return rulePrix;
	}
	public void setRulePrix(String rulePrix) {
		this.rulePrix = rulePrix;
	}
	public long getRuleStartindex() {
		return ruleStartindex;
	}
	public void setRuleStartindex(long ruleStartindex) {
		this.ruleStartindex = ruleStartindex;
	}
	public long getRuleLen() {
		return ruleLen;
	}
	public void setRuleLen(long ruleLen) {
		this.ruleLen = ruleLen;
	}
	
}
