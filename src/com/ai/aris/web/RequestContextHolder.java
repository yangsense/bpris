package com.ai.aris.web;

import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-16
 * Time: 下午2:32
 * To change this template use File | Settings | File Templates.
 */
public class RequestContextHolder {

    public static Object getAttribute(String attributeName){
        HttpServletRequest request =  ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getAttribute(attributeName);
    }

    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpSession getSession(){
        return ((ServletRequestAttributes) org.springframework.web.context.request.RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

}
