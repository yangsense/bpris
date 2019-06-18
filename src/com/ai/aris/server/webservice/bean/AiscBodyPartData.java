package com.ai.aris.server.webservice.bean;

/**
 * 检查部位n
 * Created by lenovo on 2016/12/19.
 */
public class AiscBodyPartData {
    private String bodypartCode ;
    private String bodypartDesc  ;
    private String bodypartEndesc  ;
    private String orgId;
    
    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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
