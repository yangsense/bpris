package com.ai.aris.server.basecode.model;

public class AiscReportFormat2Loc {
	private Long format2locId;
	private String orgId;
	private Long locId;
	private Long modalityId;
	private Long modelType;
	private String modelName;
	private Long formatId;
	private String reportFormat;
	private String formatName;
	
	private String orgName;
	private String locName;
	private String modalityType;
	
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Long getModelType() {
		return modelType;
	}
	public void setModelType(Long modelType) {
		this.modelType = modelType;
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
	public String getModalityType() {
		return modalityType;
	}
	public void setModalityType(String modalityType) {
		this.modalityType = modalityType;
	}
	public Long getFormat2locId() {
		return format2locId;
	}
	public void setFormat2locId(Long format2locId) {
		this.format2locId = format2locId;
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
	public Long getModalityId() {
		return modalityId;
	}
	public void setModalityId(Long modalityId) {
		this.modalityId = modalityId;
	}
	public Long getFormatId() {
		return formatId;
	}
	public void setFormatId(Long formatId) {
		this.formatId = formatId;
	}
	public String getReportFormat() {
		return reportFormat;
	}
	public void setReportFormat(String reportFormat) {
		this.reportFormat = reportFormat;
	}
	public String getFormatName() {
		return formatName;
	}
	public void setFormatName(String formatName) {
		this.formatName = formatName;
	}
	
}
