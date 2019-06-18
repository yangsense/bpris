package com.ai.aris.server.webservice.service.interfaces;

import java.io.IOException;
import java.util.Map;

import com.ai.aris.server.webservice.bean.OrgsResponse;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosBean;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:17
 * Email:zhangfengzhou@asiainfo.com
 */
public interface IOrgSV {
    public OrgsResponse retrieveOrg(Map map) throws IOException;
    
    public QryOrgAndLocInfosBean[] getOrgs(String operatorId,String orgId) throws Exception;
}
