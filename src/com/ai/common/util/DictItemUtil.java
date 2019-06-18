package com.ai.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;

public class DictItemUtil {	
	 
	public static Map queryDict(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName", map);

        Map returnMap = new HashMap();
        for(DictItemBean dictItemBean:dictItemBeans){
            returnMap.put(dictItemBean.getItemNo(),dictItemBean.getItemName());
        }
        return returnMap;
    }
	
//    public static List<Map> queryDict(String dictName) throws Exception {
//        Map map = new HashMap();
//        map.put("dictName", dictName);
//        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName", map);
//        List<Map> list = new ArrayList();
//        for(DictItemBean dictItemBean:dictItemBeans){
//            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
//        }
//        return list;
//    }
	
}
