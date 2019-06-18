package com.ai.aris.server.webservice.bean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:31
 * Email:zhangfengzhou@asiainfo.com
 */
public class OperatorResponse {
    private String code;
    private String message;
    private List<User> sysOperatorBeans;

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

    public List<User> getSysOperatorBeans() {
        return sysOperatorBeans;
    }

    public void setSysOperatorBeans(List<User> sysOperatorBeans) {
        this.sysOperatorBeans = sysOperatorBeans;
    }
}
