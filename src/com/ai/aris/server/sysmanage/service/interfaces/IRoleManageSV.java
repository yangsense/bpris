package com.ai.aris.server.sysmanage.service.interfaces;

import com.ai.aris.server.sysmanage.bean.RoleManageSearchModel;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysRoleBean;
import com.ai.common.domain.ResultDTO;

import java.util.Map;

/**
 * Created by liym5 on 2018/8/1.
 */
public interface IRoleManageSV {
    public ResultDTO queryListPage(RoleManageSearchModel searchModel, ResultDTO resultDTO, String operatorCode, String orgIds, String stationCode) throws Exception;
    public SysRoleBean[] queryOperatorRoles(String operatorCode, String roleId, String sysType)throws Exception;
    public SysMenuBean[] queryRoleMenu(String operatorCode, boolean isOnlyRole, String sys, boolean isFilterState)throws Exception;
    public SysRoleBean querySysRoleBeanByRoleName(String roleName,String sysOrg)throws Exception;
    public void update(RoleManageSearchModel searchModel, String operatorCode)throws Exception;
    public String add(RoleManageSearchModel searchModel, String operatorCode)throws Exception;
    public Map delRoleManage(String  roleId, String operatorCode, String sysType,Map map);
    SysRoleBean[] queryOperatorRoless(String operatorCode,String roleId,String sysType,String roleName)throws Exception;
}
