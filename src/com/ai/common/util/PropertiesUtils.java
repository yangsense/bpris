package com.ai.common.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/12/17
 * Time: 17:45
 * Email:zhangfz3@asiainfo.com
 */
public class PropertiesUtils extends
        PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap;

    @Override
    protected void processProperties(
            ConfigurableListableBeanFactory beanFactoryToProcess,
            Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        ctxPropertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public static String getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

    public static String getProperties(String propertieName,String... props){
        String param = null;
        String properties  = "/webservice.properties";
        if(props!=null&&props.length>0){
            properties = props[0];
        }
        Properties prop =  new  Properties();
        InputStream in = Object. class .getResourceAsStream( properties);
        try  {
            prop.load(in);
            param = prop.getProperty(propertieName ).trim();
        }  catch  (IOException e) {
            e.printStackTrace();
        }
        return  param;
    }
}
