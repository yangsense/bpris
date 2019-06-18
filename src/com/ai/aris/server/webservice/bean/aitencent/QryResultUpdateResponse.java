package com.ai.aris.server.webservice.bean.aitencent;

/**
 * Created by Walter on 2017/9/27.
 */
public class QryResultUpdateResponse {

    /**
     * code : 0
     * message : 错误描述
     * data : {"result":"诊断信息id","resultMsg":"诊断信息"}
     */

    private String code;
    private String message;
    /**
     * result : 诊断信息id
     * resultMsg : 诊断信息
     */

    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String result;
        private String orgId;
        private String studyId;
        private String updateTime;
        private String status;
        
		public String getResult() {
			return result;
		}
		public void setResult(String result) {
			this.result = result;
		}
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
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
        
    }
}
