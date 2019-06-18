package com.ai.aris.server.sysmanage.service.impl;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.ServiceManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.constants.SysManageConstants;
import com.ai.aris.server.basecode.constants.SysManageSqlUtil;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysMenuEngine;
import com.ai.aris.server.sysmanage.bean.SysRole2menuBean;
import com.ai.aris.server.sysmanage.bean.SysRole2menuEngine;
import com.ai.aris.server.sysmanage.model.SysMenuModel;
import com.ai.aris.server.sysmanage.service.interfaces.IMenuManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiahh on 2018/8/1.
 */
public class MenuManageSVImpl implements IMenuManageSV {

    private static HashMap<String ,SysMenuBean> SUBSYSTEM_MAP = null;

    public SysMenuBean[] queryRoleMenu(String operatorCode, boolean isOnlyRole, String sys, boolean isFilterState) throws Exception {

        Map<String, Object> p = new HashMap<String, Object>();
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 ");
        if (isFilterState)
            condition.append(" and STATE='1' ");
        if (StringUtils.isNotBlank(operatorCode)) {
            condition.append(com.ai.aris.server.common.constants.SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode, isOnlyRole, sys, isFilterState));
        }
        if (StringUtils.isNotBlank(sys)) {
            condition.append(" AND SYS_TYPE=" + sys);
        }


        condition.append(" order by MENU_TYPE desc, parent_menu_id,menu_order ");
        return SysMenuEngine.getBeans(condition.toString(), p);

    }


    @Override
    public ResultDTO queryPageList(String menuId, ResultDTO resultDTO) throws Exception {
        SysMenuBean[] beans = null;
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 ");
        if (StringUtils.isNotEmpty(menuId)) {
            condition.append(" and parent_menu_id = '" + menuId +"'");
        }
        condition.append(" order by menu_order ");
        int count = SysMenuEngine.getBeansCount(condition.toString(), null);
        if (count == 0) return null;
        beans = SysMenuEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false);
        resultDTO.setRows(BeanUtils.beanToList(beans, SysMenuModel.class));
        resultDTO.setRecords(count);
        return resultDTO;
    }

    public SysMenuBean querySysMenuByMenuId(String menuId) throws Exception {
        SysMenuBean menuBean = SysMenuEngine.getBean(Long.valueOf(menuId));
        if(menuBean==null||menuBean.isNew()){
            return null;
        }
        return menuBean;
    }


    /**
     * 根据SYS_TYPE返回子系统的相关信息，
     * @param sysType
     * @return
     * @throws Exception
     */
    public SysMenuBean querySubSystemInfo(String sysType)throws Exception{
        //如果未初始化，则先初始化
        if(SUBSYSTEM_MAP == null){
            queryAllSubSystemInfo();
        }

        return SUBSYSTEM_MAP.get(sysType);
    }
    /**
     * 返回所有子系统的相关信息
     * @return
     * @throws Exception
     */
    public SysMenuBean[] queryAllSubSystemInfo()throws Exception{
        //先尝试从缓存取数据
        if(SUBSYSTEM_MAP != null){
            return SUBSYSTEM_MAP.values().toArray(new SysMenuBean[0]);
        }

        HashMap<String ,SysMenuBean> tmpMap = new HashMap<String ,SysMenuBean>();

        //从数据库取所有子系统的数据，menutype=4表示子系统
        String condiString = "MENU_TYPE='4' AND STATE='1' ";
        SysMenuBean[] beans = SysMenuEngine.getBeans(condiString,null);

        for(SysMenuBean bean :beans){
            tmpMap.put(bean.getSysType()+"", bean);
        }

        SUBSYSTEM_MAP = tmpMap;

        return beans;
    }


    /**
     * 添加一个菜单
     */
    public void add(String menuType,String sysTypeName,String parentMenuId,String menuName,String menuUrl,String status,String menuImage,String menuDesc, String operatorCode) throws Exception {
        SysMenuBean menuBean = new SysMenuBean();
        long menuId = SysMenuEngine.getNewId().longValue();
        menuBean.setMenuDesc(menuDesc);
        menuBean.setMenuId(menuId);
        menuBean.setMenuName(menuName);
        menuBean.setCreateDate(SysdateManager.getSysTimestamp());
        menuBean.setState(status);
        menuBean.setMenuOrder(this.getMaxOrderByParentId(parentMenuId)+1);
        menuBean.setGroupId(SysManageConstants.MENU_GROUP_ID);
        menuBean.setMenuType(Integer.valueOf(menuType));
        menuBean.setParentMenuId(Long.valueOf(parentMenuId));
        menuBean.setMenuUrl(menuUrl);
        menuBean.setMenuImage(menuImage);
        menuBean.setSysType(Integer.valueOf(25));
        SysMenuEngine.save(menuBean);

        //将新增的角色复权给超级管理员角色
        String delSql = "DELETE FROM SYS_ROLE2MENU  WHERE MENU_ID="+parentMenuId+" and ROLE_ID="+SysManageConstants.SYS_MANAGER_ROLE_ID;

        ServiceManager.getDataStore().execute(
                ServiceManager.getSession().getConnection(),
                delSql.toString(), null);

        SysRole2menuBean role2menuBean = new SysRole2menuBean();
        role2menuBean.setRoleId(Long.valueOf(SysManageConstants.SYS_MANAGER_ROLE_ID));
        role2menuBean.setMenuId(menuId);

        SysRole2menuBean role2menuBeanP = new SysRole2menuBean();
        role2menuBeanP.setRoleId(Long.valueOf(SysManageConstants.SYS_MANAGER_ROLE_ID));
        role2menuBeanP.setMenuId(Long.valueOf(parentMenuId));

        SysRole2menuEngine.save(role2menuBean);
        SysRole2menuEngine.save(role2menuBeanP);


        delSql = "DELETE FROM SYS_ROLE2MENU  WHERE MENU_ID="+parentMenuId+" and ROLE_ID="+SysManageConstants.SYS_MANAGER_ROLE2_ID;

        ServiceManager.getDataStore().execute(
                ServiceManager.getSession().getConnection(),
                delSql.toString(), null);

        SysRole2menuBean role2menuBean2 = new SysRole2menuBean();
        role2menuBean2.setRoleId(Long.valueOf(SysManageConstants.SYS_MANAGER_ROLE2_ID));
        role2menuBean2.setMenuId(menuId);

        SysRole2menuBean role2menuBeanP2 = new SysRole2menuBean();
        role2menuBeanP2.setRoleId(Long.valueOf(SysManageConstants.SYS_MANAGER_ROLE2_ID));
        role2menuBeanP2.setMenuId(Long.valueOf(parentMenuId));

        SysRole2menuEngine.save(role2menuBean2);
        SysRole2menuEngine.save(role2menuBeanP2);

        IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                .getService(IOperatorLogSV.class);
        logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeMenuTypeAdd,
                "menuId=" + menuId+",menuName="+menuName);
    }


    public synchronized int getMaxOrderByParentId(String parentMenuId) throws Exception {
        String sql = "select max(t.menu_order) as menu_order FROM sys_menu t where t.PARENT_MENU_ID=:PARENT_MENU_ID";
        Map<String, Object> para = new HashMap<String, Object>();
        para.put("PARENT_MENU_ID", parentMenuId);

        SysMenuBean[] beans = SysMenuEngine.getBeansFromSql(sql, para);
        if(beans!=null&&beans.length>0){
            return (int) beans[0].getMenuOrder();
        }

        return 0;
    }


    public void del(String menuIds, String operatorCode) throws Exception {
        if(StringUtils.isNotBlank(menuIds)){

            String delSql = "DELETE FROM SYS_ROLE2MENU  WHERE MENU_ID in("+menuIds+")";

            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);
            //子节点
            delSql = "DELETE FROM SYS_ROLE2MENU  WHERE MENU_ID in(SELECT A.MENU_ID FROM SYS_MENU A WHERE A.PARENT_MENU_ID IN("+menuIds+"))";

            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);

            delSql="DELETE FROM SYS_OPERATOR2MENU  WHERE MENU_ID IN("+menuIds+")";
            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);

            delSql="DELETE FROM SYS_OPERATOR2MENU WHERE MENU_ID IN(SELECT A.MENU_ID FROM SYS_MENU A WHERE A.PARENT_MENU_ID IN("+menuIds+"))";
            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);

            delSql="DELETE FROM SYS_MENU  WHERE MENU_ID IN(SELECT MENU_ID FROM (select * from SYS_MENU temp) A WHERE A.PARENT_MENU_ID IN("+menuIds+"))";

            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);

            delSql="DELETE FROM SYS_MENU  WHERE MENU_ID IN("+menuIds+")";

            ServiceManager.getDataStore().execute(
                    ServiceManager.getSession().getConnection(),
                    delSql.toString(), null);


            IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                    .getService(IOperatorLogSV.class);
            logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeMenuTypeDel,
                    "menuIds=" + menuIds);
        }



    }
    /**
     * 根据操作员获取菜单权限菜单列表
     */
    public SysMenuBean[] queryOperatorMenus(String operatorCode,String roleId,
                                            boolean isOnlyRole,String menuType,String sysType) throws Exception {
        Map<String, Object> p = new HashMap<String, Object>();
        StringBuffer condition = new StringBuffer();
        condition.append("   STATE='1' ");
        if(StringUtils.isNotBlank(operatorCode)){
            condition.append(com.ai.aris.server.common.constants.SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode,isOnlyRole,sysType,true));
        }


        if(StringUtils.isNotBlank(roleId)){
            condition.append(" and MENU_ID IN(SELECT T.MENU_ID FROM SYS_ROLE2MENU T WHERE T.ROLE_ID="+roleId+") ");

        }
        if(StringUtils.isNotBlank(menuType)){
            condition.append(" AND (MENU_TYPE="+menuType +" or menu_type='3')");//菜单节点
        }

        if(StringUtils.isNotBlank(sysType)){
            condition.append(" and SYS_TYPE="+sysType);
        }

        condition.append(" order by parent_menu_id,menu_order ");
        return SysMenuEngine.getBeans(condition.toString(), p);
    }


    public void update(String menuOrder,String menuId,String sysTypeName,String parentMenuId,String menuName,String menuUrl,String status,String menuImage,String menuDesc,String menuType, String operatorCode) throws Exception {
        SysMenuBean menuBean = SysMenuEngine.getBean(Long.valueOf(menuId));
        if(menuBean==null||menuBean.isNew()){
            throw new Exception("该菜单已经不存在!");
        }
        menuBean.setMenuDesc(menuDesc);
        menuBean.setMenuName(menuName);
        menuBean.setMenuUrl(menuUrl);

        menuBean.setMenuType(Integer.valueOf(menuType));
        menuBean.setMenuImage(menuImage);
        menuBean.setMenuOrder(Integer.parseInt(org.apache.commons.lang.StringUtils.isNotBlank(menuOrder)?menuOrder.trim():"0"));

        //修改父节点
        boolean flag = true;
        if(SysManageConstants.MENU_STATE_USER.equals(menuBean.getState())&&SysManageConstants.MENU_STATE_UNUSER.equals(status)){  //启用改禁用
            Connection connection = null;
            try {
                connection = ServiceManager.getSession().getConnection();

                String queryRoleSql = "SELECT COUNT(1) num FROM SYS_ROLE2MENU  WHERE MENU_ID=? and ROLE_ID NOT IN (?,?)";
                PreparedStatement ps  = connection.prepareStatement(queryRoleSql);
                ps.setString(1,menuId);
                ps.setString(2,SysManageConstants.SYS_MANAGER_ROLE_ID);
                ps.setString(3,SysManageConstants.SYS_MANAGER_ROLE2_ID);
                ResultSet rs = ps.executeQuery();
                rs.next();
                int count = rs.getInt("num");
                if (count > 0) {
                    flag = false;
                } else {
                    String queryOperatorSql = "SELECT COUNT(1) num FROM SYS_OPERATOR2MENU  WHERE MENU_ID="+menuId+" and OPERATOR_CODE!='"+operatorCode+"'";
                    ps  = connection.prepareStatement(queryOperatorSql);
					/*ps.setString(1,menuId);
					ps.setString(2,operatorCode);*/
                    rs = ps.executeQuery();
                    rs.next();
                    count = rs.getInt("num");
                    if (count > 0) {
                        flag = false;
                    }
                }
            }catch (Exception e){
                throw e;
            }finally {
                connection.close();
            }
        }
        menuBean.setState(status);
        if(flag) {
            SysMenuEngine.save(menuBean);
            IOperatorLogSV logSV = (IOperatorLogSV) ServiceFactory
                    .getService(IOperatorLogSV.class);
            logSV.saveOperatorLog(operatorCode, IOperatorLogSV.optTypeMenuTypeUpdate,
                    "menuId=" + menuId + ",menuName=" + menuName);
        }
        else {
            throw new Exception("该菜单已赋权，禁用前请撤销所有授权！!");
        }
    }


    /**
     * 根据操作员、父菜单获取子菜单
     */
    public SysMenuBean[] queryOperatorMenusByParentId(String operatorCode,
                                                      String parentMenuId,String sysType) throws Exception {
        Map<String, Object> p = new HashMap<String, Object>();
        StringBuffer condition = new StringBuffer();
        condition.append(" STATE='1' ");
        if(StringUtils.isNotBlank(operatorCode)){
            condition.append(SysManageSqlUtil.appendOperatorMenuPowerSql(operatorCode,true,sysType,true));
        }


        if(StringUtils.isNotBlank(parentMenuId)){
            condition.append(" and PARENT_MENU_ID="+parentMenuId);
        }
        condition.append(" order by menu_order ");
        SysMenuBean[] sysMenuBeans = SysMenuEngine.getBeans(condition.toString(), p);
        return sysMenuBeans;
    }
    public void configSortMenu(String parentMenuId,String configSortJson,String operatorCode) throws Exception {
        if(StringUtils.isNotBlank(configSortJson)){
            SysMenuBean[] menuList = this.queryOperatorMenusByParentId(null, parentMenuId,null);
            if(menuList!=null&&menuList.length>0){
                JSONArray jsonArray = JSONArray.fromObject(configSortJson);
                SysMenuModel[] searchModels = (SysMenuModel[])JSONArray.toArray(jsonArray, SysMenuModel.class);
                for (int i = 0; i < menuList.length; i++) {
                    for (int j = 0; j < searchModels.length; j++) {
                        if(menuList[i].getMenuId()==Long.valueOf(searchModels[j].getMenuId())){
                            menuList[i].setMenuOrder(Integer.valueOf(String.valueOf(searchModels[j].getMenuOrder())));
                            SysMenuEngine.save(menuList[i]);
                            break;
                        }
                    }
                }

            }
        }


    }
}
