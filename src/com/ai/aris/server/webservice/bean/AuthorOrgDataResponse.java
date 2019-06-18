package com.ai.aris.server.webservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:13
 * Email:zhangfengzhou@asiainfo.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorOrgDataResponse {
    private String code;
    private String message;
    private SysOrgBean sysOrgBean;

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


    public SysOrgBean getSysOrgBean() {
        return sysOrgBean;
    }

    public void setSysOrgBean(SysOrgBean sysOrgBean) {
        this.sysOrgBean = sysOrgBean;
    }
}
