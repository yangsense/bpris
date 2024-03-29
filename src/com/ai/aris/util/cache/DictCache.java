package com.ai.aris.util.cache;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.sysmanage.bean.DictBean;
import com.ai.aris.server.sysmanage.bean.IDictValue;
import com.ai.aris.server.sysmanage.service.interfaces.IDictSV;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 字典缓存类。
 * 
 * @author wuyz 2012-3-20
 *
 */
public class DictCache extends TimerTask {
	private static Logger logger = LoggerFactory.getLogger(DictCache.class);
	private static Map<String, Map<String,IDictValue>> dictMap = new HashMap<String, Map<String, IDictValue>>();
	 	
	static{
		try {
			Timer timer = new Timer(true);
			
			//每隔60分钟运行1次
			//启动定时任务，从现在起过60分钟执行一次，以后每天执行一次
			timer.schedule(new DictCache(),	60*60*1000L, 24*60*60*1000L);
		} catch (Throwable e) {
			logger.error("" ,e);
		}
	}
	
	
	public static DictBean[] getDictItemsByDictName(String dictName){
		List<IDictValue> list =  getDictItemListsByDictName( dictName);
		if(list == null){
			return new DictBean[0];
		}
		
		
		return list.toArray(new DictBean[0]);
	}
	/**
	 * 
	 * 功能说明：根据根节点取出树
	 * TODO 
	 * @param dictName
	 * @author hejm
	 * @return
	 */
	public static List<IDictValue> getDictTreeByRootItemNo(String dictName,String itemNo){
		List<IDictValue> list =  getDictItemListsByDictName( dictName);
		if(list == null){
		
			return null;
		}
		
		List<IDictValue> newList = new ArrayList<IDictValue>();
		for (IDictValue value : list) {
			if("".equals(itemNo)){
				if(value.getParentItemNo()==null){
					newList.add(value);
				}
			}else {
				if(value.getParentItemNo()!=null){
					if(value.getParentItemNo().equals(itemNo) ){
						newList.add(value);
				    }
				}
			}
			
		}
		
		return newList;
	}
	/**
	 * 
	 * 功能描述：根据字典名称获取列表。
	 *
	 * 
	 * @param dictName 字典名称
	 * @return
	 */
	public static List<IDictValue> getDictItemListsByDictName(String dictName) {
		List<IDictValue> items = new ArrayList<IDictValue>();
		if (StringUtils.isBlank(dictName)) {
			return items;
		}
		
		Map<String, IDictValue> map = dictMap.get(dictName);
		if (map == null || map.size() == 0) {
			return items;
		}
		
		items.addAll(map.values());
		
		//根据item_no排序
		Collections.sort(items, new Comparator<IDictValue>() {
			public int compare(IDictValue o1, IDictValue o2) {
				long tmp=o1.getItemOrder() - o2.getItemOrder();
				
				if(tmp==0){
					return 0;					
				}
				else if(tmp>0){
					return 1;
				}else{
					return -1;
				}
			}
		});
		
		//根据item_state来过滤字典
		for(int i=0;i<items.size();i++){
			IDictValue item = items.get(i);
			if("0".equals(item.getItemState())){
				items.remove(item);
			}
		}

		return items;
	}
	
	
	/**
	 * 
	 * 功能描述：根据字典名称和字典项名称，获取字典对象
	 *
	 * @author wuyz Mar 20, 2012
	 * 
	 * @param dictName
	 * @param itemCode
	 * @return
	 */
	public static DictBean getDictItemByDictNameAndItemNo(String dictName, String itemCode) {
		if (StringUtils.isBlank(dictName) || StringUtils.isBlank(itemCode)) {
			return null;
		}
		
		Map<String, IDictValue> map = dictMap.get(dictName);
		if (map == null || map.size() == 0) {
			return null;
		}
		return (DictBean)map.get(itemCode);
	}
	
	/**
	 * 
	 * 功能描述：根据字典名称和父级编码和级别，获取字典对象
	 *
	 * @author wuyz Mar 20, 2012
	 * 
	 * @param dictName
	 * @param
	 * @return
	 */
	public static List<IDictValue> getDictItemByDictNameAndParentItemNoAndLevel(String dictName, String parentItemNo, String level) {
		List<IDictValue> listItems = new ArrayList<IDictValue>();
		if (StringUtils.isBlank(dictName) || StringUtils.isBlank(parentItemNo) || StringUtils.isBlank(level)) {
			return listItems;
		}
		
		//先获取该字典项下的所有项目
		listItems = getDictItemListsByDictName(dictName) ;
/*		List<IDictValue> resultList = new ArrayList<IDictValue>();
		for(IDictValue value :listItems){
			if(parentItemNo.equals(value.getParentItemNo()) && level.equals(String.valueOf(value.getItemLevel()))){
				resultList.add(value);
			}
			//if(parentItemNo.equals(value.getParentItemNo()) == false
			//		|| level.equals(value.getItemLevel()) == false){				 
            //listItems.remove(value);
			//delList.add(value);
			//}
		} */
		
		for(int i=listItems.size()-1;i>=0;i--){
			IDictValue value = listItems.get(i);
			if(parentItemNo.equals(value.getParentItemNo()) == false ||
					level.equals(String.valueOf(value.getItemLevel())) == false){
				listItems.remove(i);
			}
		}
		return listItems;
	}
	
	/**
	 * 
	 * 功能描述：根据字典名称和级别，获取字典对象
	 *
	 * @author wuyz Mar 20, 2012
	 * 
	 * @param dictName
	 * @param
	 * @return
	 */
	public static List<IDictValue> getDictItemByDictNameAndAndLevel(String dictName, String level) {
		List<IDictValue> listItems = new ArrayList<IDictValue>();
		if (StringUtils.isBlank(dictName) || StringUtils.isBlank(level)) {
			return listItems;
		}
		
		//先获取该字典项下的所有项目
		listItems = getDictItemListsByDictName(dictName) ;
 
		
		for(int i=listItems.size()-1;i>=0;i--){
			IDictValue value = listItems.get(i);
			if(level.equals(String.valueOf(value.getItemLevel())) == false){
				listItems.remove(i);
			}
		}
		return listItems;
	}
	
	/**
	 * 
	 * 功能描述：根据字典名称和字典值来获取字典值描述
	 *
	 * @author wuyz 2012-3-20
	 * 
	 * @param dictName 字典名称
	 * @param itemCode 字典编码
	 * @return
	 */
	public static String getDictItemValue(String dictName, String itemCode) {
		IDictValue value = getDictItemByDictNameAndItemNo(dictName, itemCode);
		return value == null ? "" : value.getItemName();
	}
	/**
	 * 
	 * 功能描述：根据字典名称和字典值来获取字典值描述，获取字典表中ItemDesc对应值
	 *
	 * @author wuyz 2014-3-20
	 * 
	 * @param dictName 字典名称
	 * @param itemCode 字典编码
	 * @return
	 */
	public static String getDictItemValueGetItemDesc(String dictName, String itemCode) {
		IDictValue value = getDictItemByDictNameAndItemNo(dictName, itemCode);
		return value == null ? "" : value.getItemDesc();
	}

	
	
	@Override
	public void run(){
		try {
			reloadData();
		} catch (Exception e) {
			logger.error("" ,e);
		}
	}
	

	
	/**
	 * 
	 * 功能描述：根据字典名称获取字典项数据
	 * 
	 * @param dictName 字典项名称，如果该值为空，则表示获取所有
	 * @return
	 */
	private static Map<String, Map<String,IDictValue>> loadDictData(String dictName) {
		Map<String, Map<String,IDictValue>> map = new HashMap<String, Map<String,IDictValue>>();
		try {
			IDictSV dictSv = (IDictSV) ServiceFactory.getService(IDictSV.class);
			
			//根据字典项获取数据
			Map<String, Object> condMap = new HashMap<String, Object>();
			String condition="";
			if (StringUtils.isNotBlank(dictName)) {
				condition = "dict_name = :dictName ";
				condMap.put("dictName", dictName);
			}
			
			IDictValue[] dictValues = dictSv.queryDicts(condition, condMap);
			
			//将返回的数据放入map
			for (int i=0;i<dictValues.length;i++) {
				IDictValue value  = dictValues[i];
				String name = value.getDictName();
				Map<String, IDictValue> tmpMap = map.get(name);
				if (tmpMap == null) {
					tmpMap = new HashMap<String, IDictValue>();
					map.put(name, tmpMap);
				}
				tmpMap.put(value.getItemNo(), value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return map;
	}
	

	/**
	 * 
	 * 功能描述：重新加载所有字典缓存数据
	 *
	 * @author wuyz Mar 20, 2012
	 *
	 */
	private static void reloadData() {
		logger.info("缓存字典参数信息重新加载 --- 开始");
		
		Map newMap  = loadDictData("");
		Map tmp = dictMap;
		synchronized (dictMap) {
			dictMap = newMap;
		}
		
		tmp = null;
		//将文件内容写入文件
		ObjectCacheWithFile.saveObjectToFile(CACHE_FILE_NAME, CACHE_FILE_NAME, dictMap);
		logger.info("缓存字典参数信息重新加载 --- 结束");

	}
	
	/**
	 * 启动时从文件加载 
	 */
	public static void loadDataFromFile(){
		Map newMap  = (Map) ObjectCacheWithFile.getObjectFromFile(CACHE_FILE_NAME, CACHE_FILE_NAME);
		if(newMap != null){
			dictMap = newMap;
		}else{
			reloadData();
		}
	}
	
	/**
	 * jsp调用刷新字典
	 * @param 
	 * @return void
	 * @throws
	 */
	public static void refreshDictData(){
		reloadData();
	}
	
	private static final String CACHE_FILE_NAME = "ARIS_DICT_ITEM";//存放在硬盘上的缓存文件
	
	
	
}
