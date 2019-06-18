package com.ai.aris.server.sysmanage.service.interfaces;

import com.ai.aris.server.sysmanage.bean.ISysOperator2OrgValue;
import com.ai.aris.server.sysmanage.bean.ISysOperatorValue;
import com.ai.aris.server.sysmanage.model.SysOperatorModel;

import java.util.List;
import java.util.Map;

public interface IOperatorManageSV {
    void deleteOperator(String operatorCode,String operatorCodes) throws Exception;
    ISysOperatorValue getOperatorBeanByCode(String operatorCode) throws Exception;
    ISysOperator2OrgValue getBelongOrg(String operatorCode);
    List<Map> initOfficeSelect(String orgId) throws Exception;
    List<Map> getOfficeInit(String operatorCode) throws Exception;

    boolean isExistOperator(String operatorCode) throws Exception;
    void updateOperator(String operatorCode, SysOperatorModel searchModel) throws Exception;
    void saveOperator(String operatorCode,SysOperatorModel searchModel) throws Exception;
    void setRoles(String operatorCode, String roleIds,String operatorCodeParam)throws Exception;

    void deleteOperator2org(String operatorCode, String operatorCode2)throws Exception ;
    String saveOperator2Org(String operatorCode, String orgIDS,String stationCode)
            throws Exception ;
}
