package com.ai.aris.server.sysmanage.model;

import com.ai.aris.server.sysmanage.bean.DictBean;

/**
 * 
 * @author hejm
 */
public class DictManageSearchModel extends BaseSearchModel {

	private static final long serialVersionUID = 1L;

	private String dictName;
	
	private String itemName;
	
	private String itemNo;
	
	private String itemState;
	
	private DictBean dictBean;
	
	private String queryType;
	 
	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemState() {
		return itemState;
	}

	public void setItemState(String itemState) {
		this.itemState = itemState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public DictBean getDictBean() {
		return dictBean;
	}

	public void setDictBean(DictBean dictBean) {
		this.dictBean = dictBean;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	 
	 
 
	
	
	
}
