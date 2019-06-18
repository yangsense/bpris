package com.ai.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.common.DataContainerInterface;
import com.ai.appframe2.common.DataType;

public class AppframeBeanToJsonUtil {
	//缓存每个类对应的属性名称和方法
	private static HashMap BEAN_PROP_METHOD = new HashMap();



	public static String getJsonStringFromBean(List list) throws Exception{
		DataContainerInterface dcs[] =(DataContainerInterface[]) list.toArray(new DataContainerInterface[0]);
		return getJsonStringFromBean(dcs);
	}

	public static String getJsonStringFromBean(DataContainerInterface[] beans) throws Exception{
		StringBuffer sb = new StringBuffer("[");
		for(int i=0;i<beans.length;i++){
			if(i>0){
				sb.append(",");
			}
			sb.append(getJsonStringFromBean(beans[i]));
		}
		sb.append("]");
		return sb.toString();
	}

	public static String getJsonStringFromBean(DataContainerInterface bean) throws Exception{
		HashMap map = getBeanMethods(bean.getClass());
		Iterator it = map.keySet().iterator();

		StringBuffer sb = new StringBuffer("{");
		while(it.hasNext()){
			String propName = (String)it.next();
			Method method = (Method)map.get(propName);
			Object value = method.invoke(bean, null);
			if(value == null){value=" ";}
			if(sb.length()==1){
				sb.append("\"").append(propName).append("\":");
				sb.append(getStringFromObj(value));
			}else{
				sb.append(",\"").append(propName).append("\":");
				sb.append(getStringFromObj(value));
			}
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 剔除null值,用空字符串 add by hanlq
	 * @param
	 * @return String
	 * @throws
	 */
	public static String getJsonStringFromBeanWithOutNull(DataContainerInterface bean) throws Exception{
		HashMap map = getBeanMethods(bean.getClass());
		Iterator it = map.keySet().iterator();

		StringBuffer sb = new StringBuffer("{");
		while(it.hasNext()){
			String propName = (String)it.next();
			Method method = (Method)map.get(propName);
			Object value = method.invoke(bean, null);
			if(sb.length()==1){
				sb.append("\"").append(propName).append("\":");
				sb.append(getStringFromObjWithOutNull(value));
			}else{
				sb.append(",\"").append(propName).append("\":");
				sb.append(getStringFromObjWithOutNull(value));
			}
		}
		sb.append("}");
		return sb.toString();
	}

	public static String getJsonStringFromBeanWithOutNull(DataContainerInterface[] beans) throws Exception{
		StringBuffer sb = new StringBuffer("[");
		for(int i=0;i<beans.length;i++){
			if(i>0){
				sb.append(",");
			}
			sb.append(getJsonStringFromBeanWithOutNull(beans[i]));
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * 剔除null值,用空字符串 add by hanlq   用于app端返回json
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static String getStringFromObjWithOutNull(Object value) throws Exception{
		if(value ==null){
			return "\"\"";
		}
		//数字
		else if(value instanceof Integer ||
				value instanceof Long ||
				value instanceof Double ||
				value instanceof Float){
			return value+"";
		}
		//日期
		else if(value instanceof java.sql.Timestamp){
			value = DataType.transferToString(value, DataType.DATATYPE_DATETIME);
			return "\"" + value + "\"";
		}

		//其它
		return "\"" + value + "\"";
	}

	/**
	 * 根据class，获取所有的的属性名称，以及获取对应值的方法
	 * @param c
	 * @return
	 * @throws Exception
	 */
	private static HashMap getBeanMethods(Class c) throws Exception{
		HashMap map = (HashMap)BEAN_PROP_METHOD.get(c.getName());
		if(map !=null){
			return map;
		}

		map = new HashMap();
		BEAN_PROP_METHOD.put(c.getName(), map);

		//获取所有的属性，及对应的get方法
		String[] propNames =getBeanProperties(c);
		for(int i=0;i<propNames.length;i++){
			String name = propNames[i].substring(0, 1).toLowerCase() + propNames[i].substring(1);
			Method method = c.getMethod("get" + propNames[i], null);
			map.put(name, method);
		}
		return map;
	}

	/**
	 * 根据bean对象获取属性名称
	 * @param
	 * @return
	 * @throws Exception
	 */
	private static String[] getBeanProperties(Class c) throws Exception {
		Field field[] = c.getDeclaredFields();

		ArrayList list = new ArrayList();
		for (int i = 0; i < field.length; i++) {
			String fieldName = field[i].getName();
			if("m_boName".equals(fieldName) || "S_TYPE".equals(fieldName)
					|| fieldName.startsWith("S_") == false){
				continue;
			}

			list.add(fieldName.substring(2));

		}

		return (String[])list.toArray(new String[0]);
	}

	/**
	 * 将对象转换成字符串
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private static String getStringFromObj(Object value) throws Exception{
		if(value ==null){
			return "null";
		}
		//数字
		else if(value instanceof Integer ||
				value instanceof Long ||
				value instanceof Double ||
				value instanceof Float){
			return value+"";
		}
		//日期
		else if(value instanceof java.sql.Timestamp){
			value = DataType.transferToString(value, DataType.DATATYPE_DATETIME);
			return "\"" + value + "\"";
		}

		//其它
		return "\"" + value + "\"";
	}

	/**
     * 获取Bean的 属性名以及对应的名称
     * @param bean 需要转换的DataContainerInterface对象；
     * @param beanName bean的变量名称
     * @return
     * @throws Exception
     */
    public static Map getMapFromBean(DataContainerInterface bean,String beanName) throws Exception{
        Map resultMap = new HashMap();
        resultMap.clear();
        if(bean == null){
            return resultMap;
        }

        ///获取方法
        HashMap map = getBeanMethods(bean.getClass());
        Iterator it = map.keySet().iterator();
        while(it.hasNext()){
            String propName = (String)it.next();
            Method method = (Method)map.get(propName);
            Object value = method.invoke(bean, null);
            //获取value , 通过appframe2获取到的数据，如果是数字类型
              // 为空，那么返回 0D, 0, 0L, 0F 的数据，所以要取消这些信息；
            if(value instanceof Integer ||
                value instanceof Long ||
                value instanceof Double ||
                value instanceof Float){
                if((value+"").equalsIgnoreCase("0")){
                    value = null;
                }
            }
            String propValue = getStringFromObj(value);
            /*if("\"\"".equalsIgnoreCase(propValue)){
                propValue = "";
            }*/
            if(propValue.startsWith("\"")){
                propValue = propValue.substring(1);
            }
            if(propValue.endsWith("\"")){
                propValue = propValue.substring(0,propValue.length()-1);
            }
            if(beanName==null || "".equals(beanName)){
                //如果输入的beanName为空，不需要修改属性名称
            } else {
                propName = beanName+"."+propName;
            }
            resultMap.put(propName, propValue);
        }
        return resultMap;
    }
}
