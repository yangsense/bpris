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
public class AuthorDataResponse {
    private String code;
    private String message;
    private List<Menu> menuBeans;
    private List<Menu> buttonBeans;
    private User user;
    private  SysOrg sysOrgBean;
    private List<SysOperator2Org> sysOperator2OrgBeans;
    private List<SysOperator2Org> sysOperator2orgManageBeans;
    private List<SysOrg> sysOrgManageBeans;
    private List<SysOperator> SysOperatorBeans;
    
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

    public List<Menu> getMenuBeans() {
        return menuBeans;
    }

    public void setMenuBeans(List<Menu> menuBeans) {
        this.menuBeans = menuBeans;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SysOrg getSysOrgBean() {
        return sysOrgBean;
    }

    public void setSysOrgBean(SysOrg sysOrgBean) {
        this.sysOrgBean = sysOrgBean;
    }

    public List<SysOperator2Org> getSysOperator2OrgBeans() {
        return sysOperator2OrgBeans;
    }

    public void setSysOperator2OrgBeans(List<SysOperator2Org> sysOperator2OrgBeans) {
        this.sysOperator2OrgBeans = sysOperator2OrgBeans;
    }

	public List<Menu> getButtonBeans() {
		return buttonBeans;
	}

	public void setButtonBeans(List<Menu> buttonBeans) {
		this.buttonBeans = buttonBeans;
	}

	public List<SysOperator2Org> getSysOperator2orgManageBeans() {
		return sysOperator2orgManageBeans;
	}

	public void setSysOperator2orgManageBeans(
			List<SysOperator2Org> sysOperator2orgManageBeans) {
		this.sysOperator2orgManageBeans = sysOperator2orgManageBeans;
	}

	public List<SysOperator> getSysOperatorBeans() {
		return SysOperatorBeans;
	}

	public void setSysOperatorBeans(List<SysOperator> sysOperatorBeans) {
		SysOperatorBeans = sysOperatorBeans;
	}

	public List<SysOrg> getSysOrgManageBeans() {
		return sysOrgManageBeans;
	}

	public void setSysOrgManageBeans(List<SysOrg> sysOrgManageBeans) {
		this.sysOrgManageBeans = sysOrgManageBeans;
	}
    
}
