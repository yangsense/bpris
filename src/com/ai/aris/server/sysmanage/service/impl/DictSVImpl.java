package com.ai.aris.server.sysmanage.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.aris.server.sysmanage.bean.DictBean;
import com.ai.aris.server.sysmanage.bean.DictEngine;
import com.ai.aris.server.sysmanage.bean.IDictValue;
import com.ai.aris.server.sysmanage.model.DictManageSearchModel;
import com.ai.aris.server.sysmanage.service.interfaces.IDictSV;
import com.ai.common.util.ServiceUtil;

import org.apache.commons.lang.StringUtils;


import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.service.ServiceFactory;
import com.borland.enterprise.util.StrUtil;

/**
 * @author wuyz 2012-3-15
 */
public class DictSVImpl implements IDictSV {


	/**
	 * 根据条件获取字典项目
	 */
	public IDictValue[] queryDicts(String condition, Map map) throws Exception {
		return DictEngine.getBeans(condition, map);
	}

	public List<Map> queryDict(String dictName) throws Exception {
		Map map = new HashMap();
		map.put("dictName", dictName);
		DictBean[] dictItemBeans = DictEngine.getBeans("DICT_NAME=:dictName ORDER BY ITEM_ORDER ASC", map);
		List<Map> list = new ArrayList();
		for(DictBean dictItemBean:dictItemBeans){
			list.add(ServiceUtil.transerBeanToMap(dictItemBean));
		}
		return list;
	}

	/**
	 * 根据DICT_NAME查询
	 * .
	 * @author: caijx
	 * @createdate:2014-6-20
	 */
	public DictBean[] getDictItemsByDictName(String dictName) throws Exception {
		if (StringUtils.isEmpty(dictName)) {
			return new DictBean[0];
		}

		StringBuffer cond = new StringBuffer(" ITEM_STATE = '1' ");
		Map<String, String> params = new HashMap<String, String>();
		cond.append(" AND DICT_NAME = :DICT_NAME ");
		params.put("DICT_NAME", dictName);
		cond.append(" ORDER BY ITEM_ORDER ");

		return DictEngine.getBeans(cond.toString(), params);
	}

	/**
	 * 根据DICT_NAME + ITEM_NO查询
	 * .
	 * @author: caijx
	 * @createdate:2014-6-20
	 */
	public DictBean getDictItemByDictNameAndItemNo(String dictName, String itemNo) throws Exception {
		if (StringUtils.isEmpty(dictName) || StringUtils.isEmpty(itemNo)) {
			return null;
		}

		StringBuffer cond = new StringBuffer(" item_state = '1' ");
		Map<String, String> params = new HashMap<String, String>();

		cond.append(" and dict_name = :arg0");
		params.put("arg0", dictName);

		cond.append(" and item_no = :arg1");
		params.put("arg1", itemNo);

		DictBean[] beans = DictEngine.getBeans(cond.toString(), params);
		if (beans.length > 0) {
			return beans[0];
		}

		return null;
	}


	public DictBean[] getDictItemByParentItemNo(String dictName,
												String parentItemNo) throws Exception {
		// TODO Auto-generated method stub
		if (StringUtils.isEmpty(dictName) || StringUtils.isEmpty(parentItemNo)) {
			return null;
		}

		StringBuffer cond = new StringBuffer(" item_state = '1' ");
		Map<String, String> params = new HashMap<String, String>();

		cond.append(" and dict_name = :arg0");
		params.put("arg0", dictName);

		cond.append(" and parent_item_no = :arg1");
		params.put("arg1", parentItemNo);

		DictBean[] beans = DictEngine.getBeans(cond.toString(), params);
		if (beans.length > 0) {
			return beans;
		}

		return null;
	}





	public DictBean queryDictByNameAndNo(DictManageSearchModel searchModel) throws Exception {
		// TODO Auto-generated method stub
		StringBuffer condition = new StringBuffer(" 1=1");
		Map<String, String> params = new HashMap<String, String>();
		if (searchModel != null) {
			if (!StrUtil.isBlank(searchModel.getDictName())) {
				condition.append(" AND dict_name = :dict_name ");
				params.put("dict_name", searchModel.getDictName());
			}
			if (!StrUtil.isBlank(searchModel.getItemNo())) {
				condition.append(" AND item_no = :item_no ");
				params.put("item_no", searchModel.getItemNo());
			}

		}

		DictBean[] beans = null;
		beans = DictEngine.getBeans(condition.toString(), params);

		if(beans.length>=1){
			return beans[0];
		}
		return null;

	}

}
