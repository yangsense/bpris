package com.ai.aris.server.sysmanage.service.impl;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.constants.SysManageSqlUtil;
import com.ai.aris.server.common.util.SysManageConstants;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.server.sysmanage.model.QuerySysRoleModel;
import com.ai.aris.server.sysmanage.service.interfaces.IMenuManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;
import com.ai.aris.server.sysmanage.service.interfaces.IRoleManageSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liym5 on 2018/8/1.
 */
public class RoleManageSVImpl implements IRoleManageSV {
    private Log logger = LogFactory.getLog(RoleManageSVImpl.class);

    @Override
    public ResultDTO queryListPage(RoleManageSearchModel searchModel, ResultDTO resultDTO, String operatorCode, String orgIds, String stationCode) throws Exception {
        String createEnd = searchModel.getCreateEnd();
        String createStart = searchModel.getCreateStart();
        String roleName = searchModel.getRoleName();
        String sysType = searchModel.getSysType();
        StringBuffer condition = new StringBuffer();

        Map<String, Object> para = new HashMap<String, Object>();
        condition.append(" 1 = 1 ");
        if(StringUtils.isNotBlank(roleName)){
            condition.append(" AND ROLE_NAME LIKE :ROLE_NAME");
            para.put("ROLE_NAME", "%"+roleName+"%");
        }
        if(StringUtils.isNotBlank(createStart)){
            condition.append(" and CREATE_DATE >= to_date('"
                    + createStart
                    + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
        }
        if(StringUtils.isNotBlank(createEnd)){
            condition.append(" and CREATE_DATE <= to_date('"
                    + createEnd
                    + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }
        if(StringUtils.isNotBlank(sysType) && !"-1".equals(sysType)){
            condition.append(" AND sys_Type= :sysType");
            para.put("sysType", sysType);
        }
        condition.append(" AND ROLE_STATE="+ SysManageConstants.ROLE_STATE_USER);
        //添加工号控制
        condition.append(" and ROLE_ID in (select t.role_id from sys_operator2role t where t.operator_code = '"+operatorCode+"' ) ");
        condition.append(" ORDER BY ROLE_ID DESC");
        QuerySysRoleBean[] beans = null;
        int total = QuerySysRoleEngine.getBeansCount(condition.toString(), para);
        if(total>0){
            beans = QuerySysRoleEngine.getBeans(null, condition.toString(), para, resultDTO.getStart(), resultDTO.getEnd(), false);
        }
        resultDTO.setRows(BeanUtils.beanToList(beans,QuerySysRoleModel.class));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    @Override
    public SysRoleBean[] queryOperatorRoles(String operatorCode, String roleId, String sysType) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 and ROLE_STATE="+SysManageConstants.ROLE_STATE_USER);
        Map<String, Object> para = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(roleId)){
            condition.append(" and ROLE_ID=:ROLE_ID ");
            para.put("ROLE_ID", roleId);
        }
        if(StringUtils.isNotBlank(operatorCode)){
            condition.append(" and role_id in(select t.role_id from sys_operator2role t where t.operator_code='"+operatorCode+"') ");
        }
        condition.append(" order by role_id");
        return SysRoleEngine.getBeans(condition.toString(), para);
    }
    public SysMenuBean[] queryRoleMenu(String operatorCode, boolean isOnlyRole,String sys,boolean isFilterState) throws Exception {
        Map<String, Object> p = new HashMap<String, Object>();
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 ");
        if (isFilterState)
            condition.append(" and STATE='1' ");
        if (StringUtils.isNotBlank(operatorCode)) {
            condition.append(SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode, isOnlyRole, sys, isFilterState));
        }
        if (StringUtils.isNotBlank(sys)) {
            condition.append(" AND SYS_TYPE=" + sys);
        }
        condition.append(" order by MENU_TYPE desc, parent_menu_id,menu_order ");
        return SysMenuEngine.getBeans(condition.toString(), p);
    }
    public SysRoleBean querySysRoleBeanByRoleName(String roleName,String sysType) throws Exception {
        StringBuffer condition = new StringBuffer();
        Map<String, Object> para = new HashMap<String, Object>();
        condition.append(" 1=1");
        condition.append(" AND ROLE_NAME=:ROLE_NAME");
        //condition.append(" AND SYS_TYPE=:SYS_TYPE");
        para.put("ROLE_NAME", roleName);
        //para.put("SYS_TYPE", sysType);
        SysRoleBean[] beans = SysRoleEngine.getBeans(condition.toString(), para);

        if(beans!=null&&beans.length>0){
            return beans[0];
        }
        return null;
    }
    public void update(RoleManageSearchModel searchModel, String operatorCode) throws Exception {
        String roleId = searchModel.getRoleId();
        String role2menuList = searchModel.getRole2menuList();
        SysRoleBean[] roles = this.queryOperatorRoles(null, roleId,null);

        if(roles==null||roles.length<1){
            throw new Exception("该角色数据已经不存在");
        }
        SysRoleBean temp = this.querySysRoleBeanByRoleName(searchModel.getRoleName(),searchModel.getSysType());
        if(temp!=null&&temp.getRoleId()!=Long.valueOf(roleId)){
            throw new Exception("添加失败,角色名称为:"+searchModel.getRoleName()+"的角色已经存在!");
        }
        SysRoleBean saveBean = roles[0];
        saveBean.setRoleName(searchModel.getRoleName());
        saveBean.setRoleDes(searchModel.getRoleDes());
        saveBean.setUpdateDate(SysdateManager.getSysTimestamp());
        saveBean.setUpdateUser(operatorCode);
        SysRoleEngine.save(saveBean);
        //删除该角色对应操作员的菜单
        String delSql = "delete from sys_role2menu where menu_id in (select menu_id from sys_menu where sys_type ="+searchModel.getSysType()+""+"";
        delSql+="  intersect select b.menu_id from sys_operator2role a inner join (select temp.* from sys_role2menu temp)  b on a.role_id=b.role_id and a.operator_code='"+operatorCode+"')";
        delSql+=" and role_id = "+roleId+"";
        ServiceManager.getDataStore().execute(
                ServiceManager.getSession().getConnection(),
                delSql.toString(), null);
        if(StringUtils.isNotBlank(role2menuList)){
            String[] menuIds = role2menuList.split(",");
            List<SysRole2menuBean> saveList = new ArrayList<SysRole2menuBean>();
            for (int i = 0; i < menuIds.length; i++) {
                SysRole2menuBean role2menuBean = new SysRole2menuBean();
                role2menuBean.setRoleId(Long.valueOf(roleId));
                role2menuBean.setMenuId(Long.valueOf(menuIds[i]));
                saveList.add(role2menuBean);
            }
            if(!saveList.isEmpty()){
                SysRole2menuEngine.save(saveList.toArray(new SysRole2menuBean[0]));
            }
        }
        IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                .getService(IOperatorLogSV.class);
        logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeRoleTypeUpdate,
                "roleId=" + roleId + ",roleName="
                        + searchModel.getRoleName());
    }
    public String add(RoleManageSearchModel searchModel, String operatorCode) throws Exception {
        String roleName = searchModel.getRoleName();
        String roleDes = searchModel.getRoleDes();
        String role2menuList = searchModel.getRole2menuList();
        SysRoleBean temp = this.querySysRoleBeanByRoleName(roleName,searchModel.getSysType());
        if(temp!=null){
            throw new Exception("添加失败,角色名称为:"+roleName+"的角色已经存在!");
        }
        long roleId = SysRoleEngine.getNewId().longValue();
        SysRoleBean bean = new SysRoleBean();
        bean.setRoleId(roleId);
        bean.setOrgId(searchModel.getOrgId());
        bean.setRoleDes(roleDes);
        bean.setRoleName(roleName);
        bean.setSysType(Integer.valueOf(searchModel.getSysType()));
        bean.setCreateDate(SysdateManager.getSysTimestamp());
        bean.setCreateUser(operatorCode);
        bean.setUpdateDate(SysdateManager.getSysTimestamp());
        bean.setUpdateUser(operatorCode);
        bean.setRoleState(Integer.valueOf(SysManageConstants.ROLE_STATE_USER));
        SysRoleEngine.save(bean);
        //将新建的角色复权给当前操作员
        SysOperator2roleBean sysOperator2roleBean = new SysOperator2roleBean();
        sysOperator2roleBean.setOperatorCode(operatorCode);
        sysOperator2roleBean.setRoleId(roleId);
        SysOperator2roleEngine.save(sysOperator2roleBean);
        //记录日志
        IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                .getService(IOperatorLogSV.class);
        logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeRoleTypeAdd,
                "roleId=" + roleId + ",roleName="
                        + roleName);
//		将当前角色复权给admin，前提当前操作员不是admin
        if(SysManageConstants.SYS_ADMIN.equals(operatorCode)==false){
            SysOperator2roleBean operator2roleBean = new SysOperator2roleBean();
            operator2roleBean.setRoleId(roleId);
            operator2roleBean.setOperatorCode(SysManageConstants.SYS_ADMIN);
            SysOperator2roleEngine.save(operator2roleBean);
        }
        if(StringUtils.isNotBlank(role2menuList)){
            String[] menuIds = role2menuList.split(",");
            List<SysRole2menuBean> saveList = new ArrayList<SysRole2menuBean>();
            for (int i = 0; i < menuIds.length; i++) {
                SysRole2menuBean role2menuBean = new SysRole2menuBean();
                role2menuBean.setRoleId(roleId);
                role2menuBean.setMenuId(Long.valueOf(menuIds[i]));
                saveList.add(role2menuBean);
            }
            if(!saveList.isEmpty()){
                SysRole2menuEngine.save(saveList.toArray(new SysRole2menuBean[0]));
            }
        }
        logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeRoleTypeAdd, "roleId=" + roleId + ",roleName=" + roleName);
        return String.valueOf(roleId);
    }
    public Map delRoleManage(String  roleId, String operatorCode,String sysType,Map map){
        IMenuManageSV menuManageSV = (IMenuManageSV) ServiceFactory.getService(IMenuManageSV.class);
        try{
            if(StringUtils.isNotBlank(roleId)) {
                if (roleId.equals(SysManageConstants.SYS_MANAGER_ROLE_ID)) {
                    map.put("ERRCODE", "-1");
                    map.put("ERRINFO", "超级管理员角色不可删除");
                    return map;
                }
                if (roleId.equals(SysManageConstants.SYS_MANAGER_ROLE2_ID)) {
                    map.put("ERRCODE", "-2");
                    map.put("ERRINFO", "超级管理员角色不可删除");
                    return map;
                }
                SysMenuBean[] menus = menuManageSV.queryOperatorMenus(null, roleId, true, null, sysType);

                if (menus != null && menus.length > 1) {
                    map.put("ERRCODE", "-3");
                    map.put("ERRINFO", "只可删除无菜单权限的角色");
                    return map;
                }
                String delSql = "DELETE FROM SYS_ROLE2MENU  WHERE ROLE_ID in(" + roleId + ")";
                ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), delSql.toString(), null);
                delSql = "DELETE FROM SYS_OPERATOR2ROLE  WHERE ROLE_ID IN(" + roleId + ")";
                ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), delSql.toString(), null);
                delSql = "DELETE FROM SYS_ROLE  WHERE ROLE_ID IN(" + roleId + ")";
                ServiceManager.getDataStore().execute(ServiceManager.getSession().getConnection(), delSql.toString(), null);
                IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory.getService(IOperatorLogSV.class);
                logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeRoleTypeDel, "roleIds=" + roleId);
            }
        }catch(Exception e){
               map.put("ERRCODE", "-4");
               map.put("ERRINFO", e.getMessage());
               logger.info("RoleManageSVImpl error,errorInfp:",e);
        }
        return  map;
    }

    public SysRoleBean[] queryOperatorRoless(String operatorCode,String roleId,String sysType,String roleName)throws Exception{
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 and ROLE_STATE="+SysManageConstants.ROLE_STATE_USER);
        Map<String, Object> para = new HashMap<String, Object>();
        if(StringUtils.isNotBlank(roleId)){
            condition.append(" and ROLE_ID=:ROLE_ID ");
            para.put("ROLE_ID", roleId);
        }

        if(StringUtils.isNotBlank(roleName)){
            condition.append(" and ROLE_NAME like '%" +roleName+"%'");
        }

        if(StringUtils.isNotBlank(operatorCode)){
            condition.append(" and role_id in(select t.role_id from sys_operator2role t where t.operator_code='"+operatorCode+"') ");
        }
        condition.append(" order by role_id");

        return SysRoleEngine.getBeans(condition.toString(), para);
    }
}
