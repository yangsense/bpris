package com.ai.aris.server.basecode.model;

public class AiscBodyPart {
	private String bodypartCode;
	private String bodypartDesc;
	private String bodypartEndesc;
	private long bodypartOrder;
	private String itemmastDesc;
	private String orgId;
	private String orgName;
	private String lastBodypart;
	private String bodypartType;
	private String bodypartTypedesc;
	private long bodypartPid;
	
	public long getBodypartPid() {
		return bodypartPid;
	}
	public void setBodypartPid(long bodypartPid) {
		this.bodypartPid = bodypartPid;
	}
	public String getBodypartTypedesc() {
		return bodypartTypedesc;
	}
	public void setBodypartTypedesc(String bodypartTypedesc) {
		this.bodypartTypedesc = bodypartTypedesc;
	}
	public String getLastBodypart() {
		return lastBodypart;
	}
	public void setLastBodypart(String lastBodypart) {
		this.lastBodypart = lastBodypart;
	}
	public String getBodypartType() {
		return bodypartType;
	}
	public void setBodypartType(String bodypartType) {
		this.bodypartType = bodypartType;
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
	public String getItemmastDesc() {
		return itemmastDesc;
	}
	public void setItemmastDesc(String itemmastDesc) {
		this.itemmastDesc = itemmastDesc;
	}
	public long getBodypartOrder() {
		return bodypartOrder;
	}
	public void setBodypartOrder(long bodypartOrder) {
		this.bodypartOrder = bodypartOrder;
	}
	public String getBodypartCode() {
		return bodypartCode;
	}
	public void setBodypartCode(String bodypartCode) {
		this.bodypartCode = bodypartCode;
	}
	public String getBodypartDesc() {
		return bodypartDesc;
	}
	public void setBodypartDesc(String bodypartDesc) {
		this.bodypartDesc = bodypartDesc;
	}
	public String getBodypartEndesc() {
		return bodypartEndesc;
	}
	public void setBodypartEndesc(String bodypartEndesc) {
		this.bodypartEndesc = bodypartEndesc;
	}
	
}
