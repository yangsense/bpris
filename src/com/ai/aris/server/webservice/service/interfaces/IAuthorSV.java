package com.ai.aris.server.webservice.service.interfaces;

import java.io.IOException;
import java.util.Map;

import com.ai.aris.server.webservice.bean.AuthorDataResponse;
import com.ai.aris.server.webservice.bean.AuthorOrgDataResponse;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:18
 * Email:zhangfengzhou@asiainfo.com
 */
public interface IAuthorSV {
    public AuthorDataResponse retrieveData(Map map) throws IOException;
}
