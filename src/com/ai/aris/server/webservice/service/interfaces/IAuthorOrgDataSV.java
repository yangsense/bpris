package com.ai.aris.server.webservice.service.interfaces;

import com.ai.aris.server.webservice.bean.AuthorDataResponse;
import com.ai.aris.server.webservice.bean.AuthorOrgDataResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:18
 * Email:zhangfengzhou@asiainfo.com
 */
public interface IAuthorOrgDataSV {
    public AuthorOrgDataResponse retrieveOrgData(Map map) throws IOException;
}
