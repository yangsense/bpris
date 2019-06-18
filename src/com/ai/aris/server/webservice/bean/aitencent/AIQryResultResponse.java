package com.ai.aris.server.webservice.bean.aitencent;

/**
 * Created by Walter on 2017/9/27.
 */
public class AIQryResultResponse {

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


   
}
