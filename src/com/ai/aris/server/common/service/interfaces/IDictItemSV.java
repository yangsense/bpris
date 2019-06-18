package com.ai.aris.server.common.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.common.domain.ResultDTO;

public interface IDictItemSV {
	public List<Map> queryDict(String dictName) throws Exception;
	/**
	 * 查询城市下拉，取表AISC_AreaInstitution
	 */
//	 public List<Map> queryCitys() throws Exception;
//	 /**
//	  *查询区县下拉 
//	  */
	 public List<Map> queryCountys(String cityCode) throws Exception;
	 /*
	  *机构下拉 
	  **/
	 public List<Map> queryOrg(String cityCode,String countyCode) throws Exception;
	 
	 public List<Map> queryAreaById(String dictName,String level) throws Exception;

	/**
	 * ��ѯ�ֵ��б�
	 * @param map
	 * @param operatorCode
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryListPage(DictItemModel map,ResultDTO resultDTO)throws Exception;
	/**
	 * ���������ֵ�
	 * @param dictItemBean
	 * @param operatorCode
	 * @throws Exception
	 */
	public void addDictItem(DictItemBean dictItemBean, String operatorCode)throws Exception;

	/**
	 * �޸��ֵ���Ϣ
	 * @param dictItemBean
	 * @param operatorCode
	 */
	public void updateDictItem(DictItemBean dictItemBean, String operatorCode)throws Exception;
	/**
	 * ɾ���ֵ���
	 * @param operatorCode
	 * @param dictName
	 * @param item_no
	 * @throws Exception
	 */
	public boolean deleteDictItem(String dictName ,String item_no ) throws Exception;

	public DictItemModel getDictItem(String dictName,String itemNo) throws Exception;
	
	public DictItemBean[] queryDictItem(String dictName) throws Exception;

	public String queryQueryVersionNo() throws Exception;
	
	List<Map> queryDictListMap(String dictName) throws Exception;
}
