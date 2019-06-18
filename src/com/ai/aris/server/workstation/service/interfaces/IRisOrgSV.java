package com.ai.aris.server.workstation.service.interfaces;
 
import com.ai.aris.server.common.bean.AisRightControlBean;
import com.ai.aris.server.workstation.bean.QryOrgAndLocInfosBean;
 
public interface IRisOrgSV {   
    
    public QryOrgAndLocInfosBean[] getOrgs(String operatorId,String orgId) throws Exception;
    public AisRightControlBean[] getBeasByOrgCode(String orgCode)throws  Exception;
    public void saveAisRightControlBean(AisRightControlBean aisRightControlBean)throws Exception;
    public void updateCurrentTime()throws Exception;
}
