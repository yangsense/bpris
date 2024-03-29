/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: WebUtils.java,v 1.1 2014/04/23 14:14:33 hexg Exp $
 */
package com.ai.common.util;


import javax.servlet.http.HttpServletRequest;


/**
 * Http与Servlet工具类.
 * 
 * @author calvin,fangll
 */
public class WebUtils {
	
    public static String getBasePath(HttpServletRequest request){
        String path	= request.getContextPath();
        String basePath	= request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
        return basePath;
    }

    /**
     * weblogic集群获取客户端IP
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request){
        String ip=request.getHeader("X-Forwarded-For");
        if(ip == null || ip.length() == 0) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0) {
            ip=request.getRemoteAddr();
        }
        return ip;
    }
    

}
