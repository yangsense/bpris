package com.ai.aris.server.basecode.model;

public class AiscBodyPartItem {
	private Long bodypart2Id;
	private String bodypartCode;
	private Long itemmastId;
	private String orgId;
	private long ordcategoryId;    //医嘱大类
	private long ordsubcategoryId; //医嘱子类
	private String bodypartDesc;
	private String orgName;
	private String itemmastDesc;
	private String bodypartType;
	private long bodypartPid;
	private String bodypartTypename;
	private String bodypartPname;
	
	public String getBodypartTypename() {
		return bodypartTypename;
	}
	public void setBodypartTypename(String bodypartTypename) {
		this.bodypartTypename = bodypartTypename;
	}
	public String getBodypartPname() {
		return bodypartPname;
	}
	public void setBodypartPname(String bodypartPname) {
		this.bodypartPname = bodypartPname;
	}
	public String getBodypartType() {
		return bodypartType;
	}
	public void setBodypartType(String bodypartType) {
		this.bodypartType = bodypartType;
	}
	public long getBodypartPid() {
		return bodypartPid;
	}
	public void setBodypartPid(long bodypartPid) {
		this.bodypartPid = bodypartPid;
	}
	public String getBodypartDesc() {
		return bodypartDesc;
	}
	public void setBodypartDesc(String bodypartDesc) {
		this.bodypartDesc = bodypartDesc;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getItemmastDesc() {
		return itemmastDesc;
	}
	public void setItemmastDesc(String itemmastDesc) {
		this.itemmastDesc = itemmastDesc;
	}
	public long getOrdcategoryId() {
		return ordcategoryId;
	}
	public void setOrdcategoryId(long ordcategoryId) {
		this.ordcategoryId = ordcategoryId;
	}
	public long getOrdsubcategoryId() {
		return ordsubcategoryId;
	}
	public void setOrdsubcategoryId(long ordsubcategoryId) {
		this.ordsubcategoryId = ordsubcategoryId;
	}
	public Long getBodypart2Id() {
		return bodypart2Id;
	}
	public void setBodypart2Id(Long bodypart2Id) {
		this.bodypart2Id = bodypart2Id;
	}
	public String getBodypartCode() {
		return bodypartCode;
	}
	public void setBodypartCode(String bodypartCode) {
		this.bodypartCode = bodypartCode;
	}
	public Long getItemmastId() {
		return itemmastId;
	}
	public void setItemmastId(Long itemmastId) {
		this.itemmastId = itemmastId;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}
