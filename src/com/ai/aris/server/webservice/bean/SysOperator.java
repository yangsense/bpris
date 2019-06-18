package com.ai.aris.server.webservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:07
 * Email:zhangfengzhou@asiainfo.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SysOperator {
    private String operatorId;
    private String operatorCode;
    private String operatorName;
	public String getOperatorId() {
		return operatorId;
	}
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}
	public String getOperatorCode() {
		return operatorCode;
	}
	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

    
}
