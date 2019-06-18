package com.ai.common.util;

import com.ai.common.json.IgnoreFieldPropertyFilterImpl;
import com.ai.common.json.MyDateJsonValueProcessor;
import com.ai.common.json.NotIgnoreFieldPropertyFilterImpl;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {

    /**
     * 将对象所有的非空属性转换为json格式的字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        return toJson(obj,null);
    }

    /**
     * 将某一个对象中的 一部分属性 转换为json格式的字符串
     * @param obj
     * @param pros 需要转换为json的属性
     */
    public static String toJson(Object obj, String[] pros) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class,new MyDateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new MyDateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(Timestamp.class,new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Long.class,new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(String.class,new MyDateJsonValueProcessor());
		jsonConfig.registerJsonValueProcessor(Integer.class,new MyDateJsonValueProcessor());
        IgnoreFieldPropertyFilterImpl f = new IgnoreFieldPropertyFilterImpl(pros);
        jsonConfig.setJsonPropertyFilter(f);
        String jsonStr = null;
        if(obj instanceof Collection){
            jsonStr = JSONArray.fromObject(obj, jsonConfig).toString();
        }else{
            jsonStr = JSONObject.fromObject(obj, jsonConfig).toString();
        }
        return jsonStr;
    }

    /**
     * 将某一个对象中的 一部分属性 转换为json格式的字符串
     * @param obj
     * @param pros 不需要转换为json的属性
     */
    public static String toJsonNot(Object obj, String[] pros) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class,new MyDateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new MyDateJsonValueProcessor());
        jsonConfig.registerJsonValueProcessor(Timestamp.class,new MyDateJsonValueProcessor());
        NotIgnoreFieldPropertyFilterImpl f = new NotIgnoreFieldPropertyFilterImpl(pros);
        jsonConfig.setJsonPropertyFilter(f);
        String jsonStr = null;
        if(obj instanceof Collection){
            jsonStr = JSONArray.fromObject(obj, jsonConfig).toString();
        }else{
            jsonStr = JSONObject.fromObject(obj, jsonConfig).toString();
        }
        return jsonStr;
    }

    /**
     * 将json字符串转换为对象
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static Object toBean(String jsonStr,Class clazz) {
        JSONObject jsonPerson = JSONObject.fromObject(jsonStr);
        String[] dateFormats = new String[] {"yyyyMMdd"};       //处理Date类型字段
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
        Object obj = JSONObject.toBean(jsonPerson, clazz);
        return obj;
    }

    /**
     * 将json字符串转换为对象
     * @param jsonStr
     * @param clazz
     * @return
     */
	public static Object toBean(String jsonStr,Class clazz,Map m) {
        JSONObject jsonPerson = JSONObject.fromObject(jsonStr);
        String[] dateFormats = new String[] {"yyyyMMdd"};       //处理Date类型字段
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFormats));
        Object obj = JSONObject.toBean(jsonPerson, clazz, m);
        return obj;
	}
	
	public static String toJsonString(Object obj) throws Exception{
		if(obj==null)//没有值的时候直接返回null
			return null;
		Class<?> clazz=obj.getClass();
		if(clazz==String.class||clazz==Character.class||clazz==char.class)//字符或字符串的都用“”包裹返回
			return "\""+obj+"\"";
          if(clazz==int.class||clazz==long.class||clazz==float.class||clazz==double.class||
               Number.class.isAssignableFrom(clazz)	||clazz==boolean.class||clazz==Boolean.class)//数字的直接返回
			return obj.toString();
		if(clazz.isArray()||Collection.class.isAssignableFrom(clazz))//数组或集合的转换
			return arrayToJson(obj);
		if(Map.class.isAssignableFrom(clazz))//Map的转换
			return mapToJson((Map)obj);
		return objectToJson(obj);//对象的转换
	}
	//对象的转换用{}包起来，利用反射对象所有属性进行json输出
	private static String objectToJson(Object obj) throws Exception{
		  StringBuilder sb=new StringBuilder();
   		  Class<?> cls=obj.getClass();
		  BeanInfo beanInfo=Introspector.getBeanInfo(cls, Object.class);
		  PropertyDescriptor[] pds=beanInfo.getPropertyDescriptors();
		  sb.append("{");
		  for(int i=0;i<pds.length;i++){
			 PropertyDescriptor pd=pds[i];
			String key=pd.getName();
			Method method=pd.getReadMethod();
			Object value=method.invoke(obj);
			//递归调用toJsonString用于处理值可能又是对象或者map或者数组
			sb.append(key).append(":").append(toJsonString(value));
			if(i!=pds.length-1)
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}
	//数组转json用[]包起来
	private static String arrayToJson(Object obj) throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("[");
		Class<?> cls=obj.getClass();
		int len=0,i=0;
		//数组的利用Array的api取数组各元素的值，同样递归调用toJsonString以便处理又是对象或者数组或者map
		if(cls.isArray()){
			len=Array.getLength(obj);
			for(;i<len;i++){
				Object value=Array.get(obj, i);
				sb.append(toJsonString(value));
				if(i!=len-1)
					sb.append(",");
			}
		}else if(Collection.class.isAssignableFrom(cls)){
			//集合的利用Iterator的api取数组各元素的值，同样递归调用toJsonString以便处理又是对象或者数组或者map
			Collection col=(Collection)obj;
			Iterator it=col.iterator();
			len=col.size();
			for(;i<len;i++){
				Object value=it.next();
				sb.append(toJsonString(value));
				if(i!=len-1)
					sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	//map转json和对象转json类似，只是不用反射，用map的key和value生成
	private static String mapToJson(Map map) throws Exception{
		StringBuilder sb=new StringBuilder();
		sb.append("{");
		Set keys=map.keySet();
		Iterator it=keys.iterator();
		int len=keys.size();
		for(int i=0;i<len;i++){
			Object key=it.next();
			Object value=map.get(key);
			sb.append(toJsonString(key)).append(":").append(toJsonString(value));
			if(i!=len-1)
				sb.append(",");
		}
		sb.append("}");
		return sb.toString();
	}


}
