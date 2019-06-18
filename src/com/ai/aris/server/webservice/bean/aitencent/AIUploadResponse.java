package com.ai.aris.server.webservice.bean.aitencent;

/**
 * Created by Walter on 2017/9/27.
 */
public class AIUploadResponse {

    /**
     * code : 0
     * message : 错误描述
     * data : {"studyId":"0","result":"诊断信息id","resultMsg":"诊断信息"}
     */

    private String code;
    private String message;
    /**
     * studyId : 0
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
        private String studyId;
        private String result;
        private String resultMsg;

        public String getStudyId() {
            return studyId;
        }

        public void setStudyId(String studyId) {
            this.studyId = studyId;
        }

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getResultMsg() {
            return resultMsg;
        }

        public void setResultMsg(String resultMsg) {
            this.resultMsg = resultMsg;
        }
    }
}
