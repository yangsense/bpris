package com.ai.aris.util;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.sysmanage.bean.ISysOrgValue;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.service.interfaces.ISysManageSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.service.impl.AuthorSVImpl;
import com.ai.aris.server.webservice.service.interfaces.IAuthorSV;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.util.*;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2016/1/19
 * Time: 11:34
 * Email:zhangfz3@asiainfo.com
 */
public class UserUtil {
    private static ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
    private ISysManageSV sysManageSV = (ISysManageSV) ServiceFactory.getService(ISysManageSV.class);
    /**
     *      用户的Session标志
     */
    public static String USER = Constants.SESSION_USER_OBJ;

    /**
     *      已登录的用户
     */
    public static Map<String, User> loginUsers = new HashMap<String, User>();

    /**
     * 保存用户信息到Session
     * @param user
     */
    public static void saveUserToSession(HttpSession session, User user) {
        session.setAttribute(USER, user);
        loginUsers.put(user.getOperatorCode(), user);
    }

    /**
     * 获取当前登录的用户
     * @return
     */
    public static User getCurrentUser(HttpSession session) {
        Object sessionUser = session.getAttribute(USER);
        if (sessionUser == null) {
            return null;
        }
        User User = (User) sessionUser;
        return User;
    }

    /**
     * 从session中移除用户
     */
    public static void removeUserFromSession(HttpSession session) {
        session.removeAttribute(USER);
    }

    public void retriveUserInfo(String loginName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //操作员默认组织机构获取处理
        //从cookie中
        String c_currentOrgId = "";
        Cookie[] cookies = request.getCookies();

        for(Cookie c :cookies ){
            if("c_currentOrgId".equals(c.getName())){
                c_currentOrgId = c.getValue();
                break;
            }
        }
        //当前请求中orgId不为空时写入cookie，为空时从cookie中取
        String currentOrgId =  request.getParameter("orgId") ;
        if(!"".equals(currentOrgId) && currentOrgId != null){
            //放cookie
            Cookie c2 = new Cookie("c_currentOrgId",currentOrgId);
            response.addCookie(c2);

            request.setAttribute("currentOrgId",currentOrgId);
        }else{
            request.setAttribute("currentOrgId",c_currentOrgId);
        }
        //将当前组织机构放入session中
        request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID,currentOrgId==null?c_currentOrgId:currentOrgId);

            /*Map map = new HashMap();
            map.put("operatorCode",loginName);
            map.put("appType","airis");
            map.put("secureDesc","123456");
            IAuthorSV authorSV = ApplicationUtil.getBean(AuthorSVImpl.class);
            AuthorDataResponse authorDataResponse;
            authorDataResponse = authorSV.retrieveData(map);
            User user = authorDataResponse.getUser();
            List<Menu> menus = authorDataResponse.getMenuBeans();
            List<Menu> buttons = authorDataResponse.getButtonBeans();*/

            String operatorCode = loginName.toUpperCase();
            String ip = WebUtils.getRemoteAddr(request);
            SysOperatorBean userBean = null;
            userBean = (SysOperatorBean) sysManageSV.login(operatorCode, ip);

            User user  = new User();
            user.setOperatorId(String.valueOf(userBean.getOperatorId()));
            user.setOperatorPsw(userBean.getOperatorPsw());
            user.setOperatorCode(userBean.getOperatorCode());
            user.setOperatorName(userBean.getOperatorName());

            SysMenuBean[] menus = sysManageSV.queryMenuList(loginName.toUpperCase(),"airis");
            SysMenuBean[] buttons = sysManageSV.queryButtonList(loginName.toUpperCase(),"airis");

            AiscUser2CareProvBean user2careProv = commonDataSv.getCareProvByOperatorId(user.getOperatorId());
            if(user2careProv != null){
                user.setCareProvId(String.valueOf(user2careProv.getCareprovId()));
            }else{
                user.setCareProvId(user.getOperatorId());
            }

            //SysOrg org = authorDataResponse.getSysOrgBean();
            ISysOrgValue[] orgList =  sysManageSV.getBelongOrgByOprCode(loginName.toUpperCase());
            SysOrgBean org = null;
            if(orgList.length>0){
                org = (SysOrgBean)orgList[0];
            }

            request.getSession().setAttribute(Constants.SESSION_USER, JsonUtil.toJson(user));
            request.getSession().setAttribute(Constants.SESSION_USER_OBJ, user);
            request.getSession().setAttribute(Constants.SESSION_MENU, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(menus == null ? new SysMenuBean[0] : menus));
            request.getSession().setAttribute(Constants.SESSION_BUTTON, buttons);
            request.getSession().setAttribute(Constants.SESSION_ORG, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(org == null ? new SysOrgBean() : org));
            request.getSession().setAttribute(Constants.SESSION_ORG_OBJ, org);
    }

    public static Map<String,List<String>> getStationBtnRes(SysMenuBean[] buttons){
        List<String> pageResource;
        Map<String,List<String>> stationResources = new HashMap<String, List<String>>();
        if(buttons==null||buttons.length==0){
            return new HashMap<>();
        }else{
            String page;
            String resource;
            String[]tempStr;
            for(int i=0; i < buttons.length;i++){

                if(buttons[i].getMenuUrl()!=null&&buttons[i].getMenuUrl().indexOf('@')!=-1){ //含有@
                    tempStr = buttons[i].getMenuUrl().split("@");
                    page = tempStr[0];
                    resource = tempStr[1];
                }else{
                    continue;
                }
                if(stationResources.containsKey(page)){
                    pageResource = stationResources.get(page);
                    pageResource.add(resource);
                    stationResources.put(page,pageResource);
                }else{
                    pageResource = new ArrayList<>();
                    pageResource.add(resource);
                    stationResources.put(page,pageResource);
                }
            }
        }
        return stationResources;
    }
}
