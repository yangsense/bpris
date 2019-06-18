package com.ai.aris.server.webservice.service.interfaces;

import com.ai.aris.server.interfacereal.yinglian.QryAiStudyResultBean;
import com.ai.aris.server.webservice.bean.yinglian.AiStudyUploadRequest;

public interface IAiStudyOutSV {
    public String insertStudyinfo(AiStudyUploadRequest requestBodyBean) throws Exception;
    
    public void insertStudyinfoLog(String god_portal_request_id,String orgId,String requestBody,String responseBody,String type) throws Exception;
    
    public QryAiStudyResultBean getReportInfo(String studyAccnumber,String orgId) throws Exception;
}
