package com.ai.aris.server.webservice.bean;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:19
 * Email:zhangfengzhou@asiainfo.com
 */
public class SysOperatorsResponse extends BaseResponse{
    private List<SysOperator> sysOperatorBeans;

	public List<SysOperator> getSysOperatorBeans() {
		return sysOperatorBeans;
	}

	public void setSysOperatorBeans(List<SysOperator> sysOperatorBeans) {
		this.sysOperatorBeans = sysOperatorBeans;
	}

   
}
