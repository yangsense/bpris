package com.ai.aris.server.webservice.bean;


/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:19
 * Email:zhangfengzhou@asiainfo.com
 */
public class OrgResponse extends BaseResponse{
    private SysOrg sysOrgBean;

    public SysOrg getSysOrgBean() {
        return sysOrgBean;
    }

    public void setSysOrgBean(SysOrg sysOrgBean) {
        this.sysOrgBean = sysOrgBean;
    }
}

