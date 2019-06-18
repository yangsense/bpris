package com.ai.aris.server.webservice.service.impl;

import com.ai.aris.server.webservice.bean.AuthorDataResponse;
import com.ai.aris.server.webservice.bean.AuthorOrgDataResponse;
import com.ai.aris.server.webservice.service.AbstractRestClient;
import com.ai.aris.server.webservice.service.interfaces.IAuthorOrgDataSV;
import com.ai.aris.server.webservice.service.interfaces.IAuthorSV;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 14:22
 * Email:zhangfengzhou@asiainfo.com
 */
@Service
public class AuthorOrgDataSVImpl extends AbstractRestClient<AuthorOrgDataResponse> implements IAuthorOrgDataSV {

    @Value("${sysOrgPath}")
    private String orgPathUrl;

    @Override
    public AuthorOrgDataResponse retrieveOrgData(Map map) throws IOException {
        return retrieveData(orgPathUrl,map);
    }

    public void setOrgPathUrl(String orgPathUrl) {
        this.orgPathUrl = orgPathUrl;
    }
}
