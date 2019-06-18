package com.ai.aris.server.webservice.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai.aris.server.webservice.bean.OrgsResponse;
import com.ai.aris.server.webservice.service.AbstractRestClient;
import com.ai.aris.server.webservice.service.interfaces.IOrgSV;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosBean;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosEngine;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/9/6
 * Time: 17:21
 * Email:zhangfengzhou@asiainfo.com
 */
@Service
public class OrgSVImpl extends AbstractRestClient<OrgsResponse> implements IOrgSV {
    @Value("${orgPath}")
    private String url;
    @Override
    public OrgsResponse retrieveOrg(Map map) throws IOException {
        return retrieveData(url,map);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
	public QryOrgAndLocInfosBean[] getOrgs(String operatorId,String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        condition.append(" 1=1 \n");
        
        if(isNotBlank(String.valueOf(operatorId))){
        	condition.append(" AND OPERATOR_ID = :S_OPERATOR_ID ");
            param.put("S_OPERATOR_ID", operatorId);
        }
        if(isNotBlank(String.valueOf(orgId))){
        	condition.append(" AND ORG_ID = :S_ORG_ID ");
            param.put("S_ORG_ID", orgId);
        } 
        
        QryOrgAndLocInfosBean[] phBeans = QryOrgAndLocInfosEngine.getBeans(condition.toString(), param);          
        
        return phBeans;
       	
	}
}
