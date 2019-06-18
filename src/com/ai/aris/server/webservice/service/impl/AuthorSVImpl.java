package com.ai.aris.server.webservice.service.impl;

import java.io.IOException;
import java.util.Map;

import com.ai.aris.server.webservice.bean.AuthorOrgDataResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai.aris.server.webservice.bean.AuthorDataResponse;
import com.ai.aris.server.webservice.service.AbstractRestClient;
import com.ai.aris.server.webservice.service.interfaces.IAuthorSV;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:22
 * Email:zhangfengzhou@asiainfo.com
 */
@Service
public class AuthorSVImpl extends AbstractRestClient<AuthorDataResponse> implements IAuthorSV {
    @Value("${authorPath}")
    private String url;
    @Override
    public AuthorDataResponse retrieveData(Map map) throws IOException {
        return retrieveData(url,map);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
