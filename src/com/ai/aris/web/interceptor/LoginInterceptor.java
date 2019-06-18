package com.ai.aris.web.interceptor;

import com.ai.aris.server.webservice.bean.User;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.PropertiesUtils;
import com.ai.common.util.WebUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {

	 private static final Log logger = LogFactory.getLog(LoginInterceptor.class);

	    private String domain;

	    private String appCode;
		@Resource(name = "objectMapper")
		private ObjectMapper objectMapper;

	    @Override
	    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	        logger.debug("--------- 进入登录拦截器 LoginInterceptor ---------");
	        String url = request.getRequestURI().toString();

	        if(url.indexOf("/bpris/login.html") > -1){
	             return true;
	        }
	        if (url.indexOf(".ajax") == -1 && url.indexOf(".html") == -1) {//对于非.ajax,.html资源免认证
	            return true;
	        }

	        String fullUrl = request.getRequestURL().toString();
	        try {
	            return execute(fullUrl,url, request, response, handler);//执行操作
	        } catch (Exception e) {
	            logger.error("执行登录拦截器失败LoginInterceptor：", e);
	            logger.debug("--------- 退出登录拦截器 LoginInterceptor ---------");
	            return false;
	        }
	    }

	    /**
	     * @param url      拦截URL
	     * @param request  request
	     * @param response response
	     * @param handler  handler
	     * @return boolean
	     */
	    private boolean execute(String fullUrl,String url, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

	        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);//获取用户登录信息；

	        if (handler instanceof HandlerMethod) {

	            if (user != null) {
	                //用户已登录
	                request.setAttribute(Constants.SESSION_USER_OBJ,user);

//	                TODO 单设备登录
//					String userHost = request.getRemoteHost();
//					String sessionId = request.getSession().getId();
//					UserSessionAdd usa = new UserSessionAdd();
//					usa.setUsername(user.getOperatorName());
//					usa.setSessid(sessionId);
//					usa.setAdd(userHost);
//					request.getSession().setAttribute("usa",usa);

	                return true;
	            } else {
	                //进行用户登录认证
	                return goNeedLogin(request, response, url,fullUrl);
	            }

	        }
	        return true;
	    }

	    /**
	     * 登录认证方法
	     *
	     * @param request  request
	     * @param response response
	     * @param url      拦截URl
	     * @return boolean
	     */
	    private boolean goNeedLogin(HttpServletRequest request, HttpServletResponse response, String url,String fullUrl) {
	        logger.debug(url + "--------- 进入用户登录认证 goNeedLogin ---------");
	        try {
	            if (url.indexOf(".ajax") > -1) {
	                //所有的ajax请求资源必须经过权限授权认证
	                logger.debug(url + "--------- 您需要登录系统才能使用该功能！ ---------");
	                response.setCharacterEncoding("UTF-8");
	                response.setContentType("application/json;charset=utf-8");
	                PrintWriter printWriter = response.getWriter();
	                printWriter.print(AjaxUtil.failure("您需要登录系统才能使用该功能！"));
	                printWriter.flush();
	                printWriter.close();
	            } else {
	                //跳转到用户登录页面；
	                //String authUrl = "http://" + domain  + "/Aris/aris/login.html";
	                
	            	String authUrl = WebUtils.getBasePath(request)+"/bpris/login.html";
	                response.sendRedirect(authUrl);
	            }
	        } catch (Exception e) {
	            logger.error("进入用户登录认证失败goNeedLogin", e);
	        }
	        logger.debug("--------- goNeedLogin 退出登录拦截器 LoginInterceptor ---------");
	        return false;
	    }


	    @Override
	    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
			String appId = PropertiesUtils.getContextProperty("cas.appId");
			String url = request.getRequestURI().substring(request.getRequestURI().indexOf("/"+appId+"/")+("/"+appId+"/").length());
			Map<String,List<String>> stationResources = (Map<String,List<String>>)request.getSession().getAttribute(Constants.SESSION_STATION_BUTTON_RESOURCES);
			if(modelAndView!=null&&modelAndView.getModelMap()!=null)
				request.setAttribute("resources",objectMapper.writeValueAsString(new ArrayList<>()));

			if(stationResources.containsKey(url)) {
				request.setAttribute("resources",objectMapper.writeValueAsString(stationResources.get(url)));
			}
	    }

	    @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	    }

	    public String getDomain() {
	        return domain;
	    }

	    public void setDomain(String domain) {
	        this.domain = domain;
	    }

	    public String getAppCode() {
	        return appCode;
	    }

	    public void setAppCode(String appCode) {
	        this.appCode = appCode;
	    }

}
