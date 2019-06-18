package com.ai.aris.server.webservice.model;

import com.ai.aris.server.webservice.bean.BaseResponse;

/**
 * Created by lenovo on 2016/12/19.
 */
public class SendDataResponse extends BaseResponse{
    private String sendDataPath ;

    public String getSendDataPath() {
        return sendDataPath;
    }

    public void setSendDataPath(String sendDataPath) {
        this.sendDataPath = sendDataPath;
    }
}
