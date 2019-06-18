package com.ai.aris.web.controller.sysmanage;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.publicsv.interfaces.ISysManagePublicSV;
import com.ai.aris.server.sysmanage.bean.*;
import com.ai.aris.server.sysmanage.model.QuerySysOperatorModel;
import com.ai.aris.server.sysmanage.model.QuerySysStationModel;
import com.ai.aris.server.sysmanage.model.SysOperatorModel;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.IRoleManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.ISysManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.ISysOperator2OrgSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.BeanUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.ai.common.util.Constants;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sysmanage/sysoperator")
public class SysOperatorController {
    private Log logger = LogFactory.getLog(SysOperatorController.class);
	private ICommonSV commonSV = (ICommonSV)ServiceFactory.getService(ICommonSV.class);
	private ISysManageSV sysManage = (ISysManageSV) ServiceFactory.getService(ISysManageSV.class);
    private IOperatorManageSV operatorManageSV = (IOperatorManageSV) ServiceFactory.getService(IOperatorManageSV.class);
    private ISysManagePublicSV sysManagePublicSV = (ISysManagePublicSV) ServiceFactory.getService(ISysManagePublicSV.class);
    private ISysOperator2OrgSV operator2orgSV = (ISysOperator2OrgSV) ServiceFactory.getService(ISysOperator2OrgSV.class);
    private IRoleManageSV roleManageSV = (IRoleManageSV) ServiceFactory.getService(IRoleManageSV.class);
    @RequestMapping("/updateOperatorPwd")
    @ResponseBody
    public Map updateOperatorPwd(String operatorCode,String password,String newPassword) throws Exception {
    	String result = sysManage.changeMyPasswordWithCheck(operatorCode,password, newPassword);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", result);
        return map;
    }

    @RequestMapping(value = "/page/{one}.html")
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map){
        map.put("id",id);
        return "sysmanage/sysoperator/"+one;
    }

    @RequestMapping("/getOperatorList.ajax")
    @ResponseBody
    public JSONObject getOperatorList(@RequestParam(value = "page", defaultValue = "1") int page,
                                      @RequestParam(value = "rows", defaultValue = "10") int rows,
                                      QuerySysOperatorModel model, HttpServletRequest request) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        User user = (User)request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        StringBuilder sqlBuf = new StringBuilder(" 1=1 ");
        sqlBuf.append(" and operator_code != 'ADMIN'");
        sqlBuf.append(" and operator_code != '"+user.getOperatorCode()+"'");
        if (StringUtils.isNotEmpty(model.getOperatorCode())) {
            String newOperatorCode = model.getOperatorCode().trim().toUpperCase();
            sqlBuf.append(" and operator_code like '%"+newOperatorCode+"%'");
        }
        if (StringUtils.isNotEmpty(model.getOperatorName())) {
            sqlBuf.append(" and operator_name like '%"+model.getOperatorName()+"%'");
        }
        if (StringUtils.isNotEmpty(model.getOperateTimeStart())) {
            sqlBuf.append(" and create_date >= to_date('" + model.getOperateTimeStart() + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
        }
        if (StringUtils.isNotEmpty(model.getOperateTimeEnd())) {
            sqlBuf.append(" and create_date <= to_date('" + model.getOperateTimeEnd() + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
        }
        if (StringUtils.isNotEmpty(model.getRemarks())) {
            sqlBuf.append(" and REMARKS like '%" +model.getRemarks()+ "%'");
        }

        if (StringUtils.isNotEmpty(model.getOrgName())) {
            sqlBuf.append(" and org_name like '%" +model.getOrgName()+ "%'");
        }

        //权限过滤
        String stationCode = Constants.STATIONCODE_STAFF_BELONG_ORG;
        if(!Constants.OPERATOR_STAFF_ADMIN.equalsIgnoreCase(user.getOperatorCode())) {
            sqlBuf.append(" and org_id in (select org_id from sys_operator2org so where so.station_code = '" + stationCode + "'  and so.operator_code = '" + user.getOperatorCode() + "' ) ");
        }
        sqlBuf.append(" and OPERATOR_CODE in (select a.operator_code from sys_operator2org a  where a.org_id in (select distinct org_id "
                +" from sys_org a "
                +"	 start with org_id in (select t.org_id "
                +"	                         from sys_operator2org t "
                +"	                        where t.operator_code = '"+user.getOperatorCode()+"' "
                +"	                          and org_type = 0 "
                +"	                          and station_code = '"+stationCode+"') "
                +"	connect by prior org_id = parent_org_id ) )");
        result = commonSV.queryPageList(sqlBuf.toString(),sqlBuf.toString()+" order by  create_date desc ", result,null,QuerySysOperatorModel.class,QuerySysOperatorBean.class);
        return AjaxUtil.jqGridJson(result);
    }


    @RequestMapping("/getAddOperator.ajax")
    @ResponseBody
    public Map getAddOperator(String operatorCode) {
        Map map = new HashMap();
        try {
            if (StringUtils.isNotEmpty(operatorCode)) {
                map.put("sysOperatorBean", BeanUtils.beanToModel(operatorManageSV.getOperatorBeanByCode(operatorCode),SysOperatorModel.class));

                //取员工归属机构
                ISysOperator2OrgValue operatorBelongOrg = operatorManageSV.getBelongOrg(operatorCode);
                if (operatorBelongOrg != null) {
                    for (SysOrgBean orgBean : sysManagePublicSV.getAllOrgBeans()) {
                        if (operatorBelongOrg.getOrgId().equals(orgBean.getOrgId())) {
                            map.put("opBelongOrgId", orgBean.getOrgId());
                            map.put("opBelongOrgName", orgBean.getOrgName());
                        }
                    }
                }
            }
            map.put("ERRCODE",0);
        }catch (Exception e){
            map.put("ERRINFO","查询失败");
            logger.error("查询失败", e);
        }
        return map;
    }

    @RequestMapping("/deleteOperators")
    @ResponseBody
    public Map deleteOperators(@RequestParam String operatorCodes,HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        Map map = new HashMap();
        try{
            operatorManageSV.deleteOperator(user.getOperatorCode() ,operatorCodes);
            map.put("ERRCODE",0);
        }catch (Exception e){
            map.put("ERRINFO","删除失败");
            logger.error("删除失败", e);
        }
        return map;
    }
    @RequestMapping("/changeOperatorPwd")
    @ResponseBody
    public Map changeOperatorPwd(@RequestParam String operatorCode,@RequestParam String newPwd,HttpServletRequest request) throws Exception {
        Map map = new HashMap();
        User user = (User)request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        try {
            sysManage.changeUserPasswordWithoutCheck(operatorCode,newPwd,user.getOperatorCode());
            map.put("ERRCODE",0);
        } catch (Exception e) {
            map.put("ERRINFO","修改密码失败");
        }
        return map;
    }

    //岗位管理-组织机构赋权树--权限树
    @RequestMapping("/initOrgTree")
    @ResponseBody
    public  JSONArray initOrgTree(HttpServletRequest request,@RequestParam( required=false) String orgNameParam,@RequestParam( required=false) String stationCode,@RequestParam( required=false) String id) throws Exception{
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        String operatorCode = operator.getOperatorCode();
        JSONArray orgArrays = new JSONArray();
        JSONObject p = new JSONObject();

        String orgName = "";
        if(null != orgNameParam&&!"".equals(orgNameParam)  ){
            orgName = java.net.URLDecoder.decode(orgNameParam, "UTF-8");
        }

        //获取岗位代码，如果为空，则递个人隶属单位的这个特殊岗位
        if(stationCode == null || "".equals(stationCode)){
            stationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
        }
        QueryOrgTreeBean[] orgs = null;
        /*String id = "";*/
        //针对首次初始化时id=-1的处理： 顶层为-1的工号，返父节点为-1的org, 顶层非-1时，返当前org
        if("-1".equals(id)){
            ISysManagePublicSV sv = (ISysManagePublicSV)ServiceFactory.getService(ISysManagePublicSV.class);
            SysOperator2OrgBean sysOperator2org = sv.getSysOperator2org(operatorCode,stationCode);
            id = sysOperator2org.getOrgId();
            if("-1".equals(id) ){
                orgs = operator2orgSV.queryOperatorOrgs(orgName,operatorCode, id, stationCode,true);
            }else{
                orgs = operator2orgSV.queryOperatorOrgs(orgName,operatorCode, id, stationCode,false);
            }
        }else{
            orgs = operator2orgSV.queryOperatorOrgs(orgName,operatorCode, id, stationCode,true);
        }
        //操作员拥有的组织机构
        if(orgs!=null&&orgs.length>0){
            for (int i = 0; i < orgs.length; i++) {
                JSONObject m = new JSONObject();
                m.put("id",orgs[i].getOrgId());
                m.put("pid", orgs[i].getParentOrgId());
                m.put("name", orgs[i].getOrgName());
                m.put("code", orgs[i].getOrgCode());
                m.put("orgLevel", orgs[i].getOrgLevel());
                m.put("parentOrgId", orgs[i].getParentOrgId());
                if( orgs[i].getChildren()>0){
                    m.put("isParent", true);
                }else {
                    m.put("isParent", false);
                }

                if(!"".equals(orgNameParam) && null != orgNameParam){
                    m.put("isParent", false);
                }
                orgArrays.add(m);
            }
        }
        return orgArrays ;
    }
    //岗位管理-组织机构赋权树--权限树
    @RequestMapping("/initOrgTreeByCurrentOrgId")
    @ResponseBody
    public  JSONArray initOrgTreeByCurrentOrgId(HttpServletRequest request,@RequestParam( required=false) String orgNameParam,@RequestParam( required=false) String stationCode,@RequestParam( required=false) String id,@RequestParam( required=false) String currentOrgId) throws Exception{
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        String operatorCode = operator.getOperatorCode();
        JSONArray orgArrays = new JSONArray();
        JSONObject p = new JSONObject();

        String orgName = "";
        if(null != orgNameParam&&!"".equals(orgNameParam)  ){
            orgName = java.net.URLDecoder.decode(orgNameParam, "UTF-8");
        }

        //获取岗位代码，如果为空，则递个人隶属单位的这个特殊岗位
        if(stationCode == null || "".equals(stationCode)){
            stationCode = ISysManagePublicSV.STATIONCODE_STAFF_BELONG_ORG;
        }
        QueryOrgTreeBean[] orgs = null;
        /*String id = "";*/
        //针对首次初始化时id=-1的处理： 顶层为-1的工号，返父节点为-1的org, 顶层非-1时，返当前org
        if("-1".equals(id)){
            ISysManagePublicSV sv = (ISysManagePublicSV)ServiceFactory.getService(ISysManagePublicSV.class);
            SysOperator2OrgBean sysOperator2org = sv.getSysOperator2org(operatorCode,stationCode);
            id = sysOperator2org.getOrgId();
            if("-1".equals(id) ){
                orgs = operator2orgSV.queryOperatorOrgsByCurrentOrgId(orgName,operatorCode, id, stationCode,true,currentOrgId);
            }else{
                orgs = operator2orgSV.queryOperatorOrgsByCurrentOrgId(orgName,operatorCode, id, stationCode,false,currentOrgId);
            }
        }else{
            orgs = operator2orgSV.queryOperatorOrgs(orgName,operatorCode, id, stationCode,true);
        }
        //操作员拥有的组织机构
        if(orgs!=null&&orgs.length>0){
            for (int i = 0; i < orgs.length; i++) {
                JSONObject m = new JSONObject();
                m.put("id",orgs[i].getOrgId());
                m.put("pid", orgs[i].getParentOrgId());
                m.put("name", orgs[i].getOrgName());
                m.put("code", orgs[i].getOrgCode());
                m.put("orgLevel", orgs[i].getOrgLevel());
                m.put("parentOrgId", orgs[i].getParentOrgId());
                if( orgs[i].getChildren()>0){
                    m.put("isParent", true);
                }else {
                    m.put("isParent", false);
                }

                if(!"".equals(orgNameParam) && null != orgNameParam){
                    m.put("isParent", false);
                }
                orgArrays.add(m);
            }
        }
        return orgArrays ;
    }
    @RequestMapping("/getOfficeName")
    @ResponseBody
    public JSONObject getOfficeName(String orgId)  {
        JSONObject jsonRes = new JSONObject();
        jsonRes.put("ERRCODE", "0");
        try {
            List<Map> officeBeanList = operatorManageSV.initOfficeSelect(orgId);
            jsonRes.put("officeBeanList", officeBeanList);
        } catch (Exception e) {
            jsonRes.put("ERRCODE", "1");
            jsonRes.put("ERRINFO", "获取科室菜单出错");
        }
        return jsonRes;
    }
    @RequestMapping("/getOfficeInit")
    @ResponseBody
    public JSONObject getOfficeInit(String operatorCode)  {
        JSONObject jsonRes = new JSONObject();
        jsonRes.put("ERRCODE", "0");
        try {
            List<Map> officeBeanList = operatorManageSV.getOfficeInit(operatorCode);
            jsonRes.put("officeBeanList", officeBeanList);
        } catch (Exception e) {
            jsonRes.put("ERRCODE", "1");
            jsonRes.put("ERRINFO", "获取科室菜单出错");
        }
        return jsonRes;
    }
    @RequestMapping("/saveOperator")
    @ResponseBody
    public JSONObject saveOperator(@RequestBody SysOperatorModel sysOperatorModel, HttpServletRequest request) {
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        JSONObject jsonObj = new JSONObject();
        if(StringUtils.isNotBlank(sysOperatorModel.getOperatorCode()) && (sysOperatorModel.getOperatorCode().length()>=6)){
            //传新增结果到前台
            try {
                if("1".equals(sysOperatorModel.getIsUpdate())){
                    operatorManageSV.updateOperator(operator.getOperatorCode(),sysOperatorModel);
                }else {
                    operatorManageSV.saveOperator(operator.getOperatorCode(), sysOperatorModel);
                }
                jsonObj.put("result", "success");
            } catch (Exception e) {
                jsonObj.put("result", e.getMessage());
            }
        }
        else{
            jsonObj.put("result", "工号输入有误,请重新输入！工号可以由字母、数字、下划线组成，长度为6-16位！");
        }
        return jsonObj;
    }
    @RequestMapping("/getExistRoles")
    @ResponseBody
    public JSONObject getExistRoles(String operatorCode,String roleName) {
        JSONObject roleObj = new JSONObject();
        String result = "success";
        String roleIds = "";
        String sysType = Constants.ARIS_SYS_TYPE;
        try {
            SysRoleBean[] roles = roleManageSV.queryOperatorRoless(operatorCode, null,sysType,roleName);
            if(roles!=null&&roles.length>0){
                for (int i = 0; i < roles.length; i++) {
                    roleIds+=roles[i].getRoleId()+",";
                }
                if(!"".equals(roleIds)){
                    roleIds = roleIds.substring(0,roleIds.length()-1);
                }
            }
        } catch (Exception e) {
            logger.error("取菜单树异常", e);
            roleObj.put("result", e.getMessage());
            return roleObj;
        }
        roleObj.put("result", result);
        roleObj.put("existRole", roleIds);
        return roleObj;
    }
    @RequestMapping("/initRoleTrees")
    @ResponseBody
    public  JSONArray initRoleTrees(HttpServletRequest request,String roleName) throws Exception{
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        String operatorCode = operator.getOperatorCode();
        JSONArray roleArrays = new JSONArray();
        JSONObject p = new JSONObject();
        p.put("id", "-1");
        p.put("pid", "");
        p.put("name", "角色");
        p.put("isParent", true);
        roleArrays.add(p);
        SysRoleBean[] roles = roleManageSV.queryOperatorRoless(operatorCode, null,Constants.ARIS_SYS_TYPE,roleName);
        //操作员拥有的角色
        if(roles!=null&&roles.length>0){
            for (int i = 0; i < roles.length; i++) {
                JSONObject m = new JSONObject();
                m.put("id",roles[i].getRoleId());
                m.put("pid", "-1");
                String orgName = "";
                if(roles[i].getOrgId() != null && roles[i].getOrgId() != ""){
                    orgName = "("+sysManagePublicSV.getOrgNameByOrgId(roles[i].getOrgId())+")";
                }
                m.put("name", roles[i].getRoleName()+orgName);
                m.put("isParent", false);

                roleArrays.add(m);
            }
        }
       return roleArrays;
    }

    //操作员-角色权限添加
    @RequestMapping("/setRoles")
    @ResponseBody
    public JSONObject setRoles(HttpServletRequest request,String roleIds,String operatorCode) {
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        JSONObject jsonObj = new JSONObject();

        try {
            operatorManageSV.setRoles(operator.getOperatorCode(), roleIds,operatorCode);
        } catch (Exception e) {
            jsonObj.put("result", e.getMessage());
            logger.error("添加角色失败",e);
        }
        jsonObj.put("result", "success");
        return jsonObj;
    }
    @RequestMapping("/deleteOperator2org")
    @ResponseBody
    public JSONObject deleteOperator2org(HttpServletRequest request,String operatorCodes) {
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        JSONObject jsonObj = new JSONObject();

        try {
            operatorManageSV.deleteOperator2org(operator.getOperatorCode(), operatorCodes);
        } catch (Exception e) {
            jsonObj.put("result", e.getMessage());
            logger.error("删除失败",e);
            return jsonObj;
        }
        jsonObj.put("result", "success");
        return jsonObj;
    }
    @RequestMapping("/getExistOrgs")
    @ResponseBody
    public  JSONObject getExistOrgs(HttpServletRequest request,String operatorCode,String orgName,String stationCode)  {
        JSONObject jsonObj = new JSONObject();
        String orgIds = "";
        try {
            //已有组织权限回填使用
            SysOrgBean[] orgBeans = operator2orgSV.queryOrgsByStationCode2(operatorCode,stationCode/*,orgName*/);
            if(orgBeans!=null&&orgBeans.length>0){

                for (int i = 0; i < orgBeans.length; i++) {
                    orgIds+=orgBeans[i].getOrgId()+",";
                }
                orgIds = orgIds.substring(0,orgIds.length()-1);
            }else{
                jsonObj.put("orgIds", null);
            }
        } catch (Exception e) {
            jsonObj.put("result", e.getMessage());
            logger.error("查询失败",e);
            return jsonObj;
        }
        jsonObj.put("result", "success");
        jsonObj.put("orgIds", orgIds);
        return jsonObj;
    }

    //保存组织机构管理权限
    @RequestMapping("/saveOperator2Org")
    @ResponseBody
    public JSONObject saveOperator2Org(String operatorCode,String orgIds,String stationCode) {
        JSONObject jsonObj = new JSONObject();
        try {
            String tip = operatorManageSV.saveOperator2Org(operatorCode, orgIds, stationCode);
            if(tip!=null){
                jsonObj.put("result", tip);
            }else{
                jsonObj.put("result", "success");
            }
        } catch (Exception e) {
            jsonObj.put("result", e.getMessage());
            logger.error("保存失败",e);
            return jsonObj;
        }
       return jsonObj;
    }

    @RequestMapping("/getAllOrgs")
    @ResponseBody
    public  JSONObject getAllOrgs(HttpServletRequest request)  {
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        JSONObject jsonObj = new JSONObject();
        try {
            QuerySysStationBean[] stationBeans = operator2orgSV.queryOperatorStations(operator.getOperatorCode(), null,null);
            jsonObj.put("stationBeans",BeanUtils.beanToList(stationBeans,QuerySysStationModel.class));
        } catch (Exception e) {
            jsonObj.put("result", e.getMessage());
            logger.error("查询失败",e);
            return jsonObj;
        }
        jsonObj.put("result", "success");
        return jsonObj;
    }

    /**
     * 操作员机构列表
     * @return
     */
    @RequestMapping("/getStationList")
    @ResponseBody
    public JSONObject getStationList(@RequestParam(value = "page", defaultValue = "1") int page,
                                 @RequestParam(value = "rows", defaultValue = "10") int rows,
                                 String operatorCode) {
        ResultDTO result = new ResultDTO(page,rows);
        try {
            result = operator2orgSV.queryOperator2orgInfo(operatorCode, result);
        } catch (Exception e) {
            logger.error("查询操作员机构列表信息异常，异常信息：" + e.getMessage());
        }
        return AjaxUtil.jqGridJson(result);
    }
    @RequestMapping("/checkOperatorExists")
    @ResponseBody
    public JSONObject checkOperatorExists(String operatorCode) throws Exception {
        JSONObject jsonObj = new JSONObject();
        boolean flag = operatorManageSV.isExistOperator(operatorCode);
        if (flag) {
            jsonObj.put("result","工号已存在,请重输!");
        } else {
            jsonObj.put("result", "success");
        }
        return jsonObj;
    }
}
