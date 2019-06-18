package com.ai.common.util;

import com.ai.common.domain.DictItem;
import com.ai.common.domain.DictTranslator;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-9-17
 * Time: 上午10:06
 * To change this template use File | Settings | File Templates.
 */
public class BeanUtils {

    public static <T> T copyBean(Object source, T dest, Map<String, DictTranslator> dictTranslators, Map<String, Map> dictItems) throws Exception {
         /* 1.源对象与目标对象都不能为空 */
//        if (source == null || dest == null) {
//            throw new Exception("拷贝BEAN属性值出错:源对象为空或目标对象为空或指定字段为空");
//        }
        /* 2.获取目标对象的所有get方法 */
        Class destClazz = dest.getClass();
        Method[] methods = destClazz.getDeclaredMethods();

        String methodName;
        String fieldName;
        Object value;
        String textField;
        Method setDestMethod;
        for (Method method : methods) {
            methodName = method.getName();
            if (methodName.startsWith("set")) {
                fieldName = ServiceUtil.getFieldNameFromSetMethod(method);
                /* 3.根据目标对象的方法名获取属性名，调用源目标的对应get方法获取属性值*/
                try {
                    Method getMethod = source.getClass().getMethod(ServiceUtil.getGetMethodNameFromFiledName(fieldName));
                    if (getMethod != null) {
                        value = getMethod.invoke(source, null);
                        method.invoke(dest, new Object[]{value});

                        if (dictItems.containsKey(fieldName)&&value!=null) {
                        	if("studylevelId".equals(fieldName)){
                        		System.out.println();
                        	}
                            textField = dictTranslators.get(fieldName).getTextField();
                            setDestMethod = dest.getClass().getMethod(ServiceUtil.getSetMethodNameFromFiledName(textField), String.class);
                            setDestMethod.invoke(dest, dictItems.get(fieldName).get(value.toString()));
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return dest;
    }

    public static <T> List<T> beanToList(Object[] objects, Class<T> destClazz,Map<String,DictTranslator>...dictTranslatorAttr ) throws Exception {
        List<T> list = new ArrayList<T>();
        if (objects == null) {
            return list;
        }

        Map<String, Map> dictItems = new HashMap<String, Map>();

        Field[]fields = destClazz.getDeclaredFields();
        Map<String, DictTranslator> dictTranslators;
        if(dictTranslatorAttr ==null||dictTranslatorAttr.length==0) {
            dictTranslators = new HashMap<String, DictTranslator>();
            DictTranslator dictTranslator;
            Annotation a;
            for(Field field:fields) {
                a = field.getAnnotation(DictItem.class);
                if (a != null) {
                    //get value
                    String valueFiled = AnnotationUtils.getValue(a, "valueFiled").toString();
                    String dictType = AnnotationUtils.getValue(a, "dictType").toString();
                    dictTranslator = new DictTranslator(valueFiled, dictType, field.getName());
                    dictItems.put(valueFiled, ServiceUtil.queryDict(dictType));
                    dictTranslators.put(valueFiled, dictTranslator);
                }
            }
        }else{
            dictTranslators = dictTranslatorAttr[0];
            Set<String> keys = dictTranslators.keySet();
            Iterator<String> keyItx = keys.iterator();
            String key;
            DictTranslator dictTranslator;
            while (keyItx.hasNext()) {
                key = keyItx.next();
                dictTranslator = dictTranslators.get(key);
                dictItems.put(key, ServiceUtil.queryDict(dictTranslator.getDictType()));
            }
        }

        for (Object obj : objects) {
            T destObj = destClazz.newInstance();
            destObj = copyBean(obj, destObj, dictTranslators, dictItems);
            list.add(destObj);
        }
        return list;
    }

    public static <T> T beanToModel(Object object, Class<T> destClazz,Map<String,DictTranslator>...dictTranslatorAttr ) throws Exception {
        T obj = destClazz.newInstance();
        if (object == null) {
            return obj;
        }

        Map<String, Map> dictItems = new HashMap<String, Map>();

        Field[]fields = destClazz.getDeclaredFields();
        Map<String, DictTranslator> dictTranslators;
        if(dictTranslatorAttr ==null||dictTranslatorAttr.length==0) {
            dictTranslators = new HashMap<String, DictTranslator>();
            DictTranslator dictTranslator;
            Annotation a;
            for(Field field:fields) {
                a = field.getAnnotation(DictItem.class);
                if (a != null) {
                    //get value
                    String valueFiled = AnnotationUtils.getValue(a, "valueFiled").toString();
                    String dictType = AnnotationUtils.getValue(a, "dictType").toString();
                    dictTranslator = new DictTranslator(valueFiled, dictType, field.getName());
                    dictItems.put(valueFiled, ServiceUtil.queryDict(dictType));
                    dictTranslators.put(valueFiled, dictTranslator);
                }
            }
        }else{
            dictTranslators = dictTranslatorAttr[0];
            Set<String> keys = dictTranslators.keySet();
            Iterator<String> keyItx = keys.iterator();
            String key;
            DictTranslator dictTranslator;
            while (keyItx.hasNext()) {
                key = keyItx.next();
                dictTranslator = dictTranslators.get(key);
                dictItems.put(key, ServiceUtil.queryDict(dictTranslator.getDictType()));
            }
        }
        obj = copyBean(object, obj, dictTranslators, dictItems);

        return obj;
    }

    //将会作废
    private static Map transerBeanToMap(Object o) throws InvocationTargetException, IllegalAccessException {
        Map map = new HashMap();
        Method[] methods = o.getClass().getDeclaredMethods();
        Set<String> set = getValueInterfaceNames(o);
        for (int i = 0; i < methods.length; i++) {
            if (Modifier.isPublic(methods[i].getModifiers()) && set.contains(methods[i].getName())) {
                String name = methods[i].getName().substring(3).toLowerCase();
                Object value = methods[i].invoke(o);
                map.put(name, value);
            }
        }
        return map;
    }

    private static Set<String> getValueInterfaceNames(Object o) {
        Set<String> set = new HashSet();
        Class<?>[] types = o.getClass().getInterfaces();
        for (int i = 0; i < types.length; i++) {
            String interfaceName = types[i].getName();
            if (interfaceName.endsWith("Value")) {
                Method[] methods = types[i].getDeclaredMethods();
                for (int j = 0; j < methods.length; j++) {
                    if (Modifier.isPublic(methods[j].getModifiers()) &&
                            methods[j].getName().startsWith("get")) {
                        set.add(methods[j].getName());
                    }
                }
                break;
            }
        }
        return set;
    }
}
