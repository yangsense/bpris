package com.ai.aris.util.cache;

/**
 * 通用线程变量
 * 如需要用到线程变量,可在BaseBean 加属性
 * @author fangll
 */
public class ThreadCommon {
	private static ThreadLocal<BaseBean> logThreadLocal = new ThreadLocal<BaseBean>();
	
	private static ThreadLocal<String> basePathThreadLocal = new ThreadLocal<String>();

	// 取值
	public static BaseBean getBaseBean() {
		BaseBean bean = logThreadLocal.get();
		if(bean==null){
			bean = new BaseBean();
		}
		return bean;
	}

	// 设置值
	public static void setBaseBean(BaseBean bean) {
		logThreadLocal.set(bean);
	}

	//设置远程IP
	public static void setRemoteIp(String remoteIp) {
		BaseBean bean = logThreadLocal.get();
		if (bean == null) {
			bean = new BaseBean();
		}
		bean.setRemoteIp(remoteIp);
		setBaseBean(bean);
	}


	//
	public static String getRemoteIp() {
		return getBaseBean().getRemoteIp();
	}
	
	//设置当前服务器访问路径
	public static void setWebBasePath(String remoteIp) {
		basePathThreadLocal.set(remoteIp);
	}


	//
	public static String getWebBasePath() {
		return basePathThreadLocal.get();
	}
	
}
