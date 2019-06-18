package com.ai.aris.server.webservice.bean.aitencent;

/**
 * Created by Walter on 2017/9/27.
 */
public class QryResultRequest {

    /**
     * studyId : 052389001911
     */

    private String studyId;
    private String orgId;

    public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
    public String getStudyId() {
        return studyId;
    }

    public void setStudyId(String studyId) {
        this.studyId = studyId;
    }
}
