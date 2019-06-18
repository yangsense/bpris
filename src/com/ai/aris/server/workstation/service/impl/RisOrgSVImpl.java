package com.ai.aris.server.workstation.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.common.bean.AisRightControlBean;
import com.ai.aris.server.common.bean.AisRightControlEngine;
import com.ai.aris.server.workstation.service.interfaces.RightControlConstant;
import com.ai.common.util.DESUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosBean;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosEngine;
import com.ai.aris.server.workstation.service.interfaces.IRisOrgSV;
import com.ai.common.util.ServiceUtil;
 
@Service
public class RisOrgSVImpl implements IRisOrgSV {
     
    @Override
	public QryOrgAndLocInfosBean[] getOrgs(String operatorId,String orgId) throws Exception {
    	 
		StringBuffer condition = new StringBuffer();
        Map<String, Object> param = new HashMap<String, Object>();
        condition.append(" 1=1 \n");
        
        if(isNotBlank(String.valueOf(operatorId)) && operatorId !=null){
        	condition.append(" AND OPERATOR_ID = :S_OPERATOR_ID ");
            param.put("S_OPERATOR_ID", operatorId);
        }
        if(isNotBlank(String.valueOf(orgId)) && orgId !=null){
        	condition.append(" AND ORG_ID = :S_ORG_ID ");
            param.put("S_ORG_ID", orgId);
        } 
        
        QryOrgAndLocInfosBean[] phBeans = QryOrgAndLocInfosEngine.getBeans(condition.toString(), param);          
        
        return phBeans;
       	
	}
    @Override
    public AisRightControlBean[] getBeasByOrgCode(String orgCode) throws Exception{
        StringBuffer condition = new StringBuffer(" 1=1");
        Map<String, String> map = new HashMap<String, String>();
        if(StringUtils.isNotEmpty(orgCode)){
            condition.append(" AND ORG_CODE= :ORG_CODE ");
            map.put("ORG_CODE" ,orgCode);
        }
        AisRightControlBean [] beans = AisRightControlEngine.getBeans(condition.toString(), map);
        return beans;
    }
    @Override
    public void saveAisRightControlBean(AisRightControlBean aisRightControlBean)throws Exception{
        long id =ServiceUtil.getSequence("SQE_AIS_RIGHT_CONTROL");
        aisRightControlBean.setId(String.valueOf(id));
        AisRightControlEngine.save(aisRightControlBean);
    }
    public void updateCurrentTime()throws Exception{
        Date date  = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String secretCurentTime = DESUtil.encrypt(sdf.format(date), RightControlConstant.Right_control_DesKey);
        StringBuffer sql  = new StringBuffer("UPDATE ais_right_control T SET T.CURRENT_TIME ='"+secretCurentTime+"'");
        ServiceManager.getDataStore().execute(
                ServiceManager.getSession().getConnection(),
                sql.toString(), null);
    }
}
