package com.ai.common.util;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;

public class XmlTransUtil {
	/**
	 * 将bean对象转换成xml
	 * 
	 * @param bean 需要转换成XML的JAVA BEAN对象
	 * @return xml字符串
	 * @throws Exception
	 */
	public  static String getXmlFromBean(Object xmlBean)
			throws Exception {
		if( xmlBean == null){
			return "";
		}
		Method method =   xmlBean.getClass().getMethod("marshal", new Class[]{Writer.class});
		StringWriter stringWriter = new StringWriter();
		method.invoke(xmlBean, new Object[]{stringWriter});
	
		return stringWriter.toString();
	}
	
	
	/**
	 * 将xml字符串转换成bean对象
	 * 
	 * @param xml 需要转换成JAVA BEAN对象的XML字符串
	 * @param resultBeanClass 待转换的XML BEAN对象类的class
	 * @return JAVA BEAN对象
	 * @throws Exception
	 */
	public  static <T> T getBeanFromXml(String xml ,Class<T> resultBeanClass)
			throws Exception {
			Method  method = resultBeanClass.getMethod("unmarshal", new Class[]{Reader.class});		
			StringReader stringReader = new StringReader(xml);
			
			return (T) method.invoke(null, new Object[]{stringReader});
	}
	
}
