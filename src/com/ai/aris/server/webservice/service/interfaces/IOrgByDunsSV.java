package com.ai.aris.server.webservice.service.interfaces;

import java.io.IOException;
import java.util.Map;

import com.ai.aris.server.webservice.bean.OrgResponse;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:17
 * Email:zhangfengzhou@asiainfo.com
 */
public interface IOrgByDunsSV {
    public OrgResponse retrieveOrg(Map map) throws IOException;
}
