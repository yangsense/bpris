package com.ai.aris.server.workstation.model;

public class TemplateContent {
	private Long templatecontentId;
	private Long templatedirId;
	private String templateName;
	private String templateMethod;
	private String templateExam;
	private String templateResult;
	private Long templateFq;
	private Long templatedirPdirid;
	
	private Long modalityID;
	private Long locId;
	private Long operatorId;
	private Long orgId;
	private Long isDirectory;
	private long ispositive;
	private String modalityId;
	
	private String templatecatDesc;
	private String templatedirDesc;
	
	private Long knowledgebaseid;
	private String casegroupdesc;
	
	public Long getKnowledgebaseid() {
		return knowledgebaseid;
	}
	public void setKnowledgebaseid(Long knowledgebaseid) {
		this.knowledgebaseid = knowledgebaseid;
	}
	public String getCasegroupdesc() {
		return casegroupdesc;
	}
	public void setCasegroupdesc(String casegroupdesc) {
		this.casegroupdesc = casegroupdesc;
	}
	public String getModalityId() {
		return modalityId;
	}
	public void setModalityId(String modalityId) {
		this.modalityId = modalityId;
	}
	public long getIspositive() {
		return ispositive;
	}
	public void setIspositive(long ispositive) {
		this.ispositive = ispositive;
	}
	public Long getIsDirectory() {
		return isDirectory;
	}
	public void setIsDirectory(Long isDirectory) {
		this.isDirectory = isDirectory;
	}
	public Long getModalityID() {
		return modalityID;
	}
	public void setModalityID(Long modalityID) {
		this.modalityID = modalityID;
	}
	public Long getLocId() {
		return locId;
	}
	public void setLocId(Long locId) {
		this.locId = locId;
	}
	public Long getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getTemplatedirPdirid() {
		return templatedirPdirid;
	}
	public void setTemplatedirPdirid(Long templatedirPdirid) {
		this.templatedirPdirid = templatedirPdirid;
	}
	public String getTemplatecatDesc() {
		return templatecatDesc;
	}
	public void setTemplatecatDesc(String templatecatDesc) {
		this.templatecatDesc = templatecatDesc;
	}
	public String getTemplatedirDesc() {
		return templatedirDesc;
	}
	public void setTemplatedirDesc(String templatedirDesc) {
		this.templatedirDesc = templatedirDesc;
	}
	public Long getTemplatecontentId() {
		return templatecontentId;
	}
	public void setTemplatecontentId(Long templatecontentId) {
		this.templatecontentId = templatecontentId;
	}
	public Long getTemplatedirId() {
		return templatedirId;
	}
	public void setTemplatedirId(Long templatedirId) {
		this.templatedirId = templatedirId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateMethod() {
		return templateMethod;
	}
	public void setTemplateMethod(String templateMethod) {
		this.templateMethod = templateMethod;
	}
	public String getTemplateExam() {
		return templateExam;
	}
	public void setTemplateExam(String templateExam) {
		this.templateExam = templateExam;
	}
	public String getTemplateResult() {
		return templateResult;
	}
	public void setTemplateResult(String templateResult) {
		this.templateResult = templateResult;
	}
	public Long getTemplateFq() {
		return templateFq;
	}
	public void setTemplateFq(Long templateFq) {
		this.templateFq = templateFq;
	}
	
}
