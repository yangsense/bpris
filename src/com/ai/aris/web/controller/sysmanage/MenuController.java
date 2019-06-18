package com.ai.aris.web.controller.sysmanage;

import com.ai.aris.server.basecode.constants.SysManageConstants;
import com.ai.aris.server.webservice.bean.User;
import com.ai.common.util.Constants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.service.interfaces.IMenuManageSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jiahh on 2018/8/1.
 */
@RequestMapping("/menu")
@Controller
public class MenuController {
    private IMenuManageSV sv = (IMenuManageSV) ServiceFactory.getService(IMenuManageSV.class);


    @RequestMapping("/init")
    public String init() {
        return "sysmanage/menuManage/menuInit";
    }


    /**
     * 获取树形菜单
     */
    @RequestMapping("/queryRoleMenu.ajax")
    @ResponseBody
    public JSONArray queryRoleMenu(HttpServletRequest request) throws Exception {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        SysMenuBean[] menus = sv.queryRoleMenu(user.getOperatorCode(), true, null, false);

        JSONArray menuArrays = new JSONArray();
        if (menus != null && menus.length > 0) {
            for (SysMenuBean menu : menus) {
                JSONObject m = new JSONObject();
                m.put("id", menu.getMenuId() + "");
                m.put("pid", menu.getParentMenuId() + "");
                m.put("name", menu.getMenuName());
                m.put("menuType", menu.getMenuType() + "");

                //没有url表示父节点
                if (StringUtils.isBlank(menu.getMenuUrl()) == false &&
                        menu.getMenuType() != 3 &&
                        menu.getMenuType() != 4 &&
                        menu.getMenuType() != 5) {
                    continue;
                }
                m.put("isParent", false);
                m.put("menuUrl", menu.getMenuUrl());

                menuArrays.add(m);
            }
        }
        return menuArrays;
    }

    /**
     * 人员信息查询list
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryMenuList.ajax")
    @ResponseBody
    public JSONObject queryMenuList(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "rows", defaultValue = "10") int rows,
                                    String menuId) throws Exception {
        ResultDTO result = new ResultDTO(page, rows);
        result = sv.queryPageList(menuId, result);
        return AjaxUtil.jqGridJson(result);
    }


    /**
     * 添加菜单前查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/addMenuFolder")
    public String addMenuFolder(String parentMenuId, String menuType, Model model) throws Exception {
        SysMenuBean parentMenu = sv.querySysMenuByMenuId(parentMenuId);
        if (parentMenu == null) {
            throw new Exception("父节点已经不存在,请刷新界面后重试!");
        }

        //设置所属系统的名称，
        if (parentMenu.getMenuType() == 3) {
            //如果当前选择的是子系统，则直接显示子系统名称
            model.addAttribute("sysTypeName", parentMenu.getMenuName());
        } else {
            IMenuManageSV sv = (IMenuManageSV) ServiceFactory.getService(IMenuManageSV.class);
            SysMenuBean menuBean = sv.querySubSystemInfo(parentMenu.getSysType() + "");

            if (menuBean != null) {
                model.addAttribute("sysTypeName", menuBean.getMenuName());
            }
        }
        model.addAttribute("sysType", parentMenu.getSysType() + "");
        model.addAttribute("parentMenuName", parentMenu.getMenuName());
        model.addAttribute("parentMenuId", parentMenuId);
        model.addAttribute("menuType", menuType);

        //
        if ("3".equalsIgnoreCase(menuType)) {
            model.addAttribute("menuTypeName", "菜单目录");

        } else {
            model.addAttribute("menuTypeName", "菜单节点");
        }
        return "sysmanage/menuManage/menuAdd";
    }


    /**
     * 添加菜单操作
     *
     * @throws Exception
     */
    @RequestMapping("/add.ajax")
    @ResponseBody
    public JSONObject add(String sysTypeName, String parentMenuId, String menuName, String menuUrl, String status, String menuImage, String menuDesc, String menuType,HttpServletRequest request) throws Exception {
        String menuNameTemp = java.net.URLDecoder.decode(menuName,"UTF-8");
        String menuDescTemp = java.net.URLDecoder.decode(menuDesc,"UTF-8");
        String menuUrlTemp = java.net.URLDecoder.decode(menuUrl,"UTF-8");
        String menuImageTemp = java.net.URLDecoder.decode(menuImage,"UTF-8");
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        boolean result = true;
        String message = "添加成功!";

        try {
            sv.add(menuType, sysTypeName, parentMenuId, menuNameTemp, menuUrlTemp, status, menuImageTemp, menuDescTemp, operatorCode);
        } catch (Exception e) {
            result = false;
            message = "添加失败," + e.getMessage();
        }
        JSONObject jsonObj = new JSONObject();
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return jsonObj;
    }


    /**
     * 删除菜单
     *
     * @throws Exception
     */
    @RequestMapping("/delMenu.ajax")
    @ResponseBody
    public JSONObject delMenu(String menuIds,HttpServletRequest request) throws Exception {

        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        boolean result = true;
        String message = "删除成功!";

        try {
            sv.del(menuIds, operatorCode);
        } catch (Exception e) {
            result = false;
            message = "删除失败!";
        }
        JSONObject jsonObj = new JSONObject();
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return jsonObj;
    }


    /**
     * 修改菜单前查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateMenu.ajax")
    public String updateMenu(String menuId, Model model) throws Exception {
        SysMenuBean menuBean = sv.querySysMenuByMenuId(menuId);
        if (menuBean == null) {
            throw new Exception("该菜单已不存在!");
        }

        String parentMenuId = String.valueOf(menuBean.getParentMenuId());
        model.addAttribute("menuType", menuBean.getMenuType() + "");

        //菜单类型名称
        if (sv.MENUTYPE_MENU == menuBean.getMenuType()) {
            model.addAttribute("menuTypeName", "菜单节点");
        } else if (sv.MENUTYPE_FUNCTION == menuBean.getMenuType()) {
            model.addAttribute("menuTypeName", "功能点");
        } else if (sv.MENUTYPE_FOLDER == menuBean.getMenuType()) {
            model.addAttribute("menuTypeName", "菜单目录");
        } else if (sv.MENUTYPE_SUBSYSTEM == menuBean.getMenuType()) {
            model.addAttribute("menuTypeName", "子系统");
        } else if (sv.MENUTYPE_VISUALNODE == menuBean.getMenuType()) {
            model.addAttribute("menuTypeName", "虚拟目录");
        }
        model.addAttribute("menuName", menuBean.getMenuName());
        model.addAttribute("menuDesc", menuBean.getMenuDesc());
        model.addAttribute("parentMenuId", parentMenuId);
        model.addAttribute("menuUrl", menuBean.getMenuUrl());
        model.addAttribute("state", menuBean.getState());
        model.addAttribute("menuImage", menuBean.getMenuImage());
        model.addAttribute("menuOrder", String.valueOf(menuBean.getMenuOrder()));
        model.addAttribute("menuId", menuId);

        return "sysmanage/menuManage/menuUpdate";
    }


    /**
     * 修改菜单操作
     *
     * @throws Exception
     */
    @RequestMapping("/update.ajax")
    @ResponseBody
    public JSONObject update(String menuOrder, String menuId, String sysTypeName, String parentMenuId, String menuName, String menuUrl, String status, String menuImage, String menuDesc, String menuType,HttpServletRequest request) throws Exception {
        String menuNameTemp = java.net.URLDecoder.decode(menuName,"UTF-8");
        String menuDescTemp = java.net.URLDecoder.decode(menuDesc,"UTF-8");
        String menuUrlTemp = java.net.URLDecoder.decode(menuUrl,"UTF-8");
        String menuImageTemp = java.net.URLDecoder.decode(menuImage,"UTF-8");
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        boolean result = true;
        String message = "修改成功!";

        try {
            SysMenuBean menu = sv.querySysMenuByMenuId(menuId);
            if (menu == null) {
                throw new Exception("节点已经不存在,请刷新界面后重试!");
            }

            sv.update(menuOrder, menuId, sysTypeName, parentMenuId, menuNameTemp, menuUrlTemp, status, menuImageTemp, menuDescTemp, menuType, operatorCode);
        } catch (Exception e) {
            result = false;
            message = "修改失败," + e.getMessage();
        }
        JSONObject jsonObj = new JSONObject();
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return jsonObj;
    }


    /**
     * 添加菜单前查询
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/sortMenu.ajax")
    public String sortMenu(String parentMenuId,String menuType, Model model) throws Exception {
        model.addAttribute("parentMenuId", parentMenuId);
        model.addAttribute("menuType", menuType);
        return "sysmanage/menuManage/menuSort";
    }


    @RequestMapping("/getParentMenuList.ajax")
    @ResponseBody
    public JSONArray getParentMenuList(String menuType,String parentMenuId)throws Exception{
        String parentMenuIds = SysManageConstants.MENU_TYPE_MENU.equals(menuType)?SysManageConstants.MENU_ROOT_ID:parentMenuId;
        SysMenuBean[] beans = sv.queryOperatorMenusByParentId(null,parentMenuId,null);
        JSONArray jsonArray = new JSONArray();
        if(beans!=null){

            for (int i=0;i<beans.length;i++) {

                SysMenuBean bean = beans[i];
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("value", bean.getMenuId());
                jsonObj.put("name", bean.getMenuName());
                jsonObj.put("desc", getNotNullString(bean.getMenuUrl()));
                jsonArray.add(jsonObj);
            }
        }
        return jsonArray;
    }

    public static String getNotNullString(String s) {
        return s != null ? s.trim() : "";
    }


    @RequestMapping("/configSortMenu.ajax")
    @ResponseBody
    public JSONObject configSortMenu(String parentMenuId,String configSortJson,HttpServletRequest request) throws Exception{

        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        boolean result = true;
        String message = "操作成功!";

        try {
            sv.configSortMenu(parentMenuId,configSortJson, operatorCode);
        }catch (Exception e) {
            result = false;
            message = "操作失败!";
        }
        JSONObject jsonObj = new JSONObject();
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return jsonObj;
    }
}
