package com.ai.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.*;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class IpUtil {
    private static final Log logger = LogFactory.getLog(IpUtil.class);
    private static InetAddress LocalIP = null;

    //取得本机IP地址
    public static InetAddress getLocalIp() {
        try {
            LocalIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("取IP错误！",e);
        }
        return LocalIP;
    }


    /**
     * 获取本地IP地址
     *
     * @return
     */
    public static String getIp() {
        try {
            LocalIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("取IP错误！",e);
        }
        return LocalIP.toString();
    }

    public static String getFirstNonLoopBackAddress(boolean preferIpv4, boolean preferIPv6) {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface i = (NetworkInterface) en.nextElement();
                for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements(); ) {
                    InetAddress addr = (InetAddress) en2.nextElement();
                    if (!addr.isLoopbackAddress()) {
                        if (addr instanceof Inet4Address) {
                            if (preferIPv6) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                        if (addr instanceof Inet6Address) {
                            if (preferIpv4) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
