package com.ai.aris.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext a;

    public static ApplicationContext getApplicationContext() {
        return a;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        a = applicationContext;
    }


}
