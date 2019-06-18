package com.ai.aris.server.webservice.service;

import com.ai.common.util.PropertiesUtils;

public class BusiDataService {
	public static void main(String[] args) {
		ClientService cv = new ClientService();
		try {
          //发送数据
          //若传递对象没有orgId字段，则"ORG_ID": 标识符无效，需单独处理
		  String orgId = PropertiesUtils.getProperties("sendOrg");
		  cv.sendBusiData(orgId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
