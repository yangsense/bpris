package com.ai.aris.server.webservice.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai.aris.server.webservice.bean.OrgResponse;
import com.ai.aris.server.webservice.service.AbstractRestClient;
import com.ai.aris.server.webservice.service.interfaces.IOrgByDunsSV;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:21
 * Email:zhangfengzhou@asiainfo.com
 */
@Service
public class OrgByDunsSVImpl extends AbstractRestClient<OrgResponse> implements IOrgByDunsSV {
    @Value("${orgByDuns}")
    private String url;
    @Override
    public OrgResponse retrieveOrg(Map map) throws IOException {
        return retrieveData(url,map);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
