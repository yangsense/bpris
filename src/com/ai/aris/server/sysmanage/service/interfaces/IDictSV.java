package com.ai.aris.server.sysmanage.service.interfaces;


import com.ai.aris.server.sysmanage.bean.DictBean;
import com.ai.aris.server.sysmanage.bean.IDictValue;
import com.ai.aris.server.sysmanage.model.DictManageSearchModel;

import java.util.List;
import java.util.Map;


/**
 * 字典接口
 * 
 * @author wuyz 2012-3-15
 *
 */
public interface IDictSV {
	/**
	 * 
	 * 功能描述：获取字典数据。
	 *
	 * @author wuyz Mar 15, 2012
	 * 
	 * @param condition
	 * @param map
	 * @return
	 */
	public IDictValue[] queryDicts(String condition, Map map) throws Exception;

	public List<Map> queryDict(String dictName) throws Exception;
	/**
	 * 根据DICT_NAME查询
	 * .
	 * @author: caijx 
	 * @createdate:2014-6-20
	 */
	public DictBean[] getDictItemsByDictName(String dictName) throws Exception;
	

    /**
     * 根据dictName+itemNo ,查找字典
     * @param searchModel
     * @return
     */
	public DictBean queryDictByNameAndNo(DictManageSearchModel searchModel)throws Exception;




}
