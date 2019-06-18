package com.ai.aris.server.webservice.service.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai.aris.server.webservice.bean.OperatorResponse;
import com.ai.aris.server.webservice.service.AbstractRestClient;
import com.ai.aris.server.webservice.service.interfaces.IOperatorSV;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:33
 * Email:zhangfengzhou@asiainfo.com
 */
@Service
public class OperatorSVImpl extends AbstractRestClient<OperatorResponse> implements IOperatorSV{
    @Value("${operatorPath}")
    private String url;
    @Override
    public OperatorResponse retrieveOperator(Map map) throws IOException {
        return retrieveData(url,map);
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
