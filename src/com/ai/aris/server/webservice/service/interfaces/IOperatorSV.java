package com.ai.aris.server.webservice.service.interfaces;

import java.io.IOException;
import java.util.Map;

import com.ai.aris.server.webservice.bean.OperatorResponse;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:32
 * Email:zhangfengzhou@asiainfo.com
 */
public interface IOperatorSV {
    public OperatorResponse retrieveOperator(Map map) throws IOException;
}
