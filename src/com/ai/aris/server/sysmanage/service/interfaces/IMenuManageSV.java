package com.ai.aris.server.sysmanage.service.interfaces;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.common.domain.ResultDTO;

/**
 * Created by jiahh on 2018/8/1.
 */
public interface IMenuManageSV {

    public int MENUTYPE_MENU = 1;//菜单类型：菜单
    public int MENUTYPE_FUNCTION = 2;// 菜单类型：功能点（按钮权限）
    public int MENUTYPE_FOLDER = 3;//菜单类型：目录
    public int MENUTYPE_SUBSYSTEM = 4;//菜单类型：子系统
    public int MENUTYPE_VISUALNODE = 5;//菜单类型：虚拟目录
    /**
     * 获取角色所能操作的菜单
     * @param operatorCode
     * @param isOnlyRole
     * @return
     * @throws Exception
     */
    public SysMenuBean[] queryRoleMenu(String operatorCode, boolean isOnlyRole, String sys, boolean isFilterState)throws Exception;


    /*
    * 查询列表数据
    * */
    public ResultDTO queryPageList(String menuId, ResultDTO resultDTO) throws Exception;


    /*
    * 查询父节点是否存在
    * */
    public SysMenuBean querySysMenuByMenuId(String menuId)throws Exception;

    /**
     * 根据SYS_TYPE返回子系统的相关信息，
     * @param sysType
     * @return
     * @throws Exception
     */
    public SysMenuBean querySubSystemInfo(String sysType)throws Exception;


    /*
    * 新增菜单节点
    * */
    public void add(String menuType,String sysTypeName,String parentMenuId,String menuName,String menuUrl,String status,String menuImage,String menuDesc, String operatorCode)throws Exception;


    public void del(String menuIds, String operatorCode)throws Exception;
    
    public SysMenuBean[] queryAllSubSystemInfo()throws Exception;
    public SysMenuBean[] queryOperatorMenus(String operatorCode,String roleId,boolean isOnlyRole,String menuType,String sysType)throws Exception;

    public void update(String menuOrder,String menuId,String sysTypeName,String parentMenuId,String menuName,String menuUrl,String status,String menuImage,String menuDesc,String menuType, String operatorCode)throws Exception;

    public SysMenuBean[] queryOperatorMenusByParentId(String operatorCode,String parentMenuId,String sysType)throws Exception;

    public void configSortMenu(String parentMenuId,String configSortJson, String operatorCode)throws Exception;
}
