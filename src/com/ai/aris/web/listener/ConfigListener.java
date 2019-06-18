package com.ai.aris.web.listener;

import com.ai.aris.util.cache.DictCache;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 监听
 * @author fangll
 *
 */
public class ConfigListener implements ServletContextListener {
	private transient static Log logger = LogFactory.getLog(ConfigListener.class);
	
	
	
	public void contextDestroyed(ServletContextEvent sce) {
	}

	public void contextInitialized(ServletContextEvent sce) {
	    try {
	    	//是否加载数据字典
	    	DictCache.loadDataFromFile();
		} catch (Throwable ex) {
			logger.error("监听loadDataFromFile",ex);
		}
	}
}
