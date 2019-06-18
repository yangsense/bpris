package com.ai.aris.web.filter;

import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.util.UserUtil;
import com.ai.common.util.PropertiesUtils;
import org.apache.log4j.Logger;
import org.jasig.cas.client.authentication.AttributePrincipal;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2016/1/19
 * Time: 11:22
 * Email:zhangfz3@asiainfo.com
 */
public class AutoSetUserAdapterFilter implements Filter {
    Logger log = Logger.getLogger(AutoSetUserAdapterFilter.class);
    /**
     * Default constructor.
     */
    public AutoSetUserAdapterFilter() {
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }

    /**
     * 过滤逻辑：首先判断单点登录的账户是否已经存在本系统中，
     * 如果不存在使用用户查询接口查询出用户对象并设置在Session中
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        // _const_cas_assertion_是CAS中存放登录用户名的session标志
        Object object = session.getAttribute("_const_cas_assertion_");
        String appId = PropertiesUtils.getContextProperty("cas.appId");
        if (object != null) {
            AttributePrincipal principal = (AttributePrincipal)((HttpServletRequest) request).getUserPrincipal();
            Map attributes = principal.getAttributes();
            String userName = null;
            if(attributes.size()>0) {
                Object obj = attributes.get("appToUser");
                if(obj instanceof  List) {
                    List<String> appToUsers = (List<String>)obj;
                    for (String appToUser : appToUsers) {
                        if (appId.equals(appToUser.substring(0, appToUser.indexOf("=")))) {
                            userName = appToUser.substring(appToUser.indexOf("=") + 1);
                        }
                    }
                }else{
                    String appToUser = (String)obj;
                    if(appId.equals(appToUser.substring(0, appToUser.indexOf("=")))){
                        userName = appToUser.substring(appToUser.indexOf("=") + 1);
                    }
                }
            }else{
                return;
            }
            User user = UserUtil.getCurrentUser(session);

            // 第一次登录系统
            if (user == null) {
                // 保存用户信息到Session
                try {
                    UserUtil userUtil = new UserUtil();
                    userUtil.retriveUserInfo(userName,httpRequest,(HttpServletResponse)response);
                } catch (Exception e) {
                    log.error("保存用戶session信息出错",e);
                }
            }
        }
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
    }


}
