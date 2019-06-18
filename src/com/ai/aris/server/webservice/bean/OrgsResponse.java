package com.ai.aris.server.webservice.bean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:19
 * Email:zhangfengzhou@asiainfo.com
 */
public class OrgsResponse extends BaseResponse{
    private List<SysOrg> sysOrgManageBeans;

	public List<SysOrg> getSysOrgManageBeans() {
		return sysOrgManageBeans;
	}

	public void setSysOrgManageBeans(List<SysOrg> sysOrgManageBeans) {
		this.sysOrgManageBeans = sysOrgManageBeans;
	}

    
}
