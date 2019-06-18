package com.ai.aris.web.controller.sysmanage;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.model.AiscCareProv;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.RoleManageSearchModel;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysRoleBean;
import com.ai.aris.server.sysmanage.service.interfaces.IMenuManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.IRoleManageSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("roleManageController")
public class RoleManageController {
    private Log logger = LogFactory.getLog(RoleManageController.class);
    private IRoleManageSV roleManageSV = (IRoleManageSV) ServiceFactory.getService(IRoleManageSV.class);
    private IMenuManageSV sv = (IMenuManageSV) ServiceFactory.getService(IMenuManageSV.class);
    @RequestMapping("index.html")
    public String index(HttpServletRequest request, Model model) throws Exception {
        SysMenuBean[] sysMenuBeans = sv.queryAllSubSystemInfo();//menu_type=4,子系统
        model.addAttribute("sysMenuBeans",sysMenuBeans);
        return "sysmanage/rolManage/role-manage-init";
    }

    @RequestMapping("/queryList.ajax")
    @ResponseBody
    public JSONObject queryList(@RequestParam(value = "page", defaultValue = "1") int page,
                                            @RequestParam(value = "rows", defaultValue = "10") int rows,
                                RoleManageSearchModel model,RoleManageSearchModel searchModel,HttpServletRequest request) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        //取用户操作编码
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        //获取当前用户所属机构以及所属机构的下级机构id的集合
        String orgIds = "";
        String stationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
        try {
            result =  roleManageSV.queryListPage(searchModel,result,user.getOperatorCode(),orgIds,stationCode);
        } catch (Exception e) {
            logger.error("查询角色列表异常，异常信息："+e.getMessage());
        }
        return AjaxUtil.jqGridJson(result);
    }
    @RequestMapping("/roleManageDetail.html")
    public String roleManageDetail(HttpServletRequest request,@RequestParam String id,@RequestParam String type,Model model) throws Exception {
        //查询所有系统菜单
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));

        String operatorCode = user.getOperatorCode();
        SysMenuBean[] sysMenuBeans = sv.queryAllSubSystemInfo();
        model.addAttribute("sysMenuBeans",sysMenuBeans);
        RoleManageSearchModel searchModel = new RoleManageSearchModel();
        if(!"add".equals(type)){
            String roleId = id;
            SysRoleBean[] beans = roleManageSV.queryOperatorRoles(null, roleId,null);
            searchModel.setRoleId(roleId);
            searchModel.setRoleDes(beans[0].getRoleDes());
            searchModel.setRoleName(beans[0].getRoleName());
            searchModel.setSysType(String.valueOf(beans[0].getSysType()));
            //角色对应的菜单
            SysMenuBean[] menus = sv.queryOperatorMenus(operatorCode,roleId,true,null,searchModel.getSysType());
            if(menus!=null&&menus.length>0){
                String menuIds = "";
                for (int i = 0; i < menus.length; i++) {
                    menuIds+=menus[i].getMenuId()+"_";
                }
                searchModel.setRole2menuList(menuIds.substring(0, menuIds.length()-1));
            }
        }
        model.addAttribute("sysMenuBeans",sysMenuBeans);
        model.addAttribute("searchModel", searchModel);
        model.addAttribute("dataType",type);
        return "sysmanage/rolManage/role-manage-detail";
    }
    /**
     * 当前操作员【角色】所拥有的菜单
     *
     */
    @RequestMapping("/queryRoleMenu.ajax")
    @ResponseBody
    public JSONObject queryRoleMenu(HttpServletRequest request,RoleManageSearchModel searchModel) {
        JSONObject jsonObj  = new JSONObject();
        try {
            //取角菜对应的菜单及工号对应的菜单
            User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
            String operatorCode = user.getOperatorCode();
            SysMenuBean[] menus = roleManageSV.queryRoleMenu(operatorCode,true,searchModel.getSysType(),true);
            JSONArray menuArrays = new JSONArray();
            if(menus!=null&&menus.length>0){
                for (SysMenuBean menu : menus) {
                    JSONObject m = new JSONObject();
                    m.put("id", menu.getMenuId());
                    m.put("pid", menu.getParentMenuId());
                    m.put("name", menu.getMenuType() == 2 ? "(功能点)"+menu.getMenuName() : menu.getMenuName());
                    m.put("menuUrl", menu.getMenuUrl());
                    menuArrays.add(m);
                }
            }
            //角色对应的菜单
            SysMenuBean[] menus1 = sv.queryOperatorMenus(operatorCode,searchModel.getRoleId(),true,null,
                    searchModel.getSysType());
            String menuIds = "";
            if(menus1!=null&&menus1.length>0){

                for (int i = 0; i < menus1.length; i++) {
                    menuIds+=menus1[i].getMenuId()+"_";
                }
                searchModel.setRole2menuList(menuIds.substring(0, menuIds.length()-1));
            }
            jsonObj.put("errorCode", "0");
            jsonObj.put("menuIds", menuIds);
            jsonObj.put("menuTree", menuArrays.toString());
        } catch (Exception e) {
            jsonObj.put("errorCode", "-1");
            logger.error("取菜单树异常", e);
        }
        return  jsonObj;
    }
    @RequestMapping("/checkRoleName.ajax")
    @ResponseBody
    public JSONObject checkRoleName(HttpServletRequest request,RoleManageSearchModel searchModel,Model model) {
        boolean result = true;
        String message = "该角色名称可用!";
        String roleId = searchModel.getRoleId();
        try {
            String roleName = "";
            if(StringUtils.isNotBlank(roleId)){
                SysRoleBean[] roles = roleManageSV.queryOperatorRoles(null, roleId,null);
                if(roles!=null&&roles.length>0){
                    roleName = roles[0].getRoleName();
                }
            }
            if(!roleName.equals(searchModel.getRoleName())){
                SysRoleBean role = roleManageSV.querySysRoleBeanByRoleName(searchModel.getRoleName(),searchModel.getSysType());
                if(role!=null){
                    result = false;
                    message = "该角色名称已存在,请修改角色名称!";
                }
            }
        }catch (Exception e) {
            result = false;
            message = e.getMessage();
        }
        JSONObject jsonObj = new JSONObject();
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
    @RequestMapping("/saveRoleManage.ajax")
    @ResponseBody
    public JSONObject saveRoleManage(RoleManageSearchModel searchModel,HttpServletRequest request) {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String operatorCode = user.getOperatorCode();
        String result = "0";
        String message = "修改成功!";
        JSONObject jsonObj = new JSONObject();
        try {
            if("update".equals(searchModel.getDataType())){
                roleManageSV.update(searchModel, operatorCode);
            }else {
                roleManageSV.add(searchModel, operatorCode);
            }
        }catch (Exception e) {
            result = "-1";
            message = e.getMessage();
        }
        //传新增结果到前台
        jsonObj.put("result", result);
        jsonObj.put("message", message);
        return  jsonObj;
    }
    /**
     * 删除
     */
    @RequestMapping("/deleteRoleManage.ajax")
    @ResponseBody
    public Map deleteRoleManage(@RequestParam String  id,HttpServletRequest request) throws Exception {
        User user = (User)(request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        String sysType = (String) request.getSession().getAttribute(Constants.SESSION_LOGIN_SYS);
        String operatorCode = user.getOperatorCode();
        Map map = new HashMap();
        map.put("ERRCODE",0);
        map.put("ERRINFO","删除成功");
        try{
            map = roleManageSV.delRoleManage(id,operatorCode,sysType,map);
        }catch (Exception e){
            map.put("ERRCODE",-6);
            map.put("ERRINFO",e.getMessage());
            logger.info("deleteRoleManage.ajax error,errorInfo:",e);
        }
        return map;
    }
}
