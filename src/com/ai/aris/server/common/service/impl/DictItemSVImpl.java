package com.ai.aris.server.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.bo.SysdateManager;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.QueryCountysBean;
import com.ai.aris.server.basecode.bean.QueryCountysEngine;
import com.ai.aris.server.basecode.bean.QueryOrgSelectBean;
import com.ai.aris.server.basecode.bean.QueryOrgSelectEngine;
import com.ai.aris.server.common.bean.AisVersionRecordBean;
import com.ai.aris.server.common.bean.AisVersionRecordEngine;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.DictBean;
import com.ai.aris.util.cache.DictCache;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
import com.borland.enterprise.util.StrUtil;

public class DictItemSVImpl implements IDictItemSV{
	
	@Override
    public List<Map> queryDict(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName ORDER BY ITEM_ORDER ASC", map);
        List<Map> list = new ArrayList();
        for(DictItemBean dictItemBean:dictItemBeans){
            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
        }
        return list;
    }
//	 public List<Map> queryCitys() throws Exception{
//		StringBuffer condition = new StringBuffer(" 1=1");
//        ArisAreaInstitutionBean[] areaInstitutionBeans = ArisAreaInstitutionEngine.getBeans(condition.toString(), null);
//        List<Map> list = new ArrayList();
//        for(ArisAreaInstitutionBean areaInstitutionBean:areaInstitutionBeans){
//            list.add(ServiceUtil.transerBeanToMap(areaInstitutionBean));
//        }
//        return list;
//	 }
	 public List<Map> queryCountys(String cityCode) throws Exception{
		StringBuffer condition = new StringBuffer(" 1=1");
		if(cityCode != null &&  !"".equals(cityCode)){
			condition.append(" AND CITY_CODE='"+cityCode+"'"); 
		}
		QueryCountysBean[] queryCountysBeans = QueryCountysEngine.getBeans(condition.toString(), null);
		List<Map> list = new ArrayList();
		for(QueryCountysBean queryCountysBean:queryCountysBeans){
		    list.add(ServiceUtil.transerBeanToMap(queryCountysBean));
		}
		return list;
	 }
	 public List<Map> queryOrg(String cityCode,String countyCode) throws Exception{
			StringBuffer condition = new StringBuffer(" 1=1");
			if(cityCode != null &&  !"-1".equals(cityCode)){
				condition.append(" AND CITY_CODE='"+cityCode+"'"); 
			}
			if(countyCode != null &&  !"-1".equals(countyCode)){
				condition.append(" AND COUNTY_CODE='"+countyCode+"'"); 
			}
			QueryOrgSelectBean[] queryOrgSelectBeans = QueryOrgSelectEngine.getBeans(condition.toString(), null);
			List<Map> list = new ArrayList();
			for(QueryOrgSelectBean queryOrgSelectBean:queryOrgSelectBeans){
			    list.add(ServiceUtil.transerBeanToMap(queryOrgSelectBean));
			}
			return list;
		 }
	 public List<Map> queryAreaById(String dictName,String level) throws Exception{
		StringBuffer condition = new StringBuffer(" 1=1");
		if(dictName != null &&  !"".equals(dictName)){
			condition.append(" AND item_level='"+level+"'"); 
		}
		condition.append(" AND item_state=1"); 
		DictItemBean[] areaBeans = DictItemEngine.getBeans(condition.toString(), null);
		List<Map> list = new ArrayList();
        for(DictItemBean dictItemBean:areaBeans){
            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
        }
		return list;
	 }
    public DictItemBean[] queryDictItem(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName ORDER BY ITEM_ORDER ASC", map);
        return dictItemBeans;        
    }
    public String  queryQueryVersionNo() throws Exception{
        AisVersionRecordBean[] aisVersionRecordBeans = AisVersionRecordEngine.getBeans(" ORDER BY UPDATE_DATE DESC", null);
        return aisVersionRecordBeans[0].getVersionNo();
    }

    @Override
    public ResultDTO queryListPage(DictItemModel dictItemModel,ResultDTO resultDTO) throws Exception {
        // TODO Auto-generated method stub
        StringBuffer condition = new StringBuffer(" 1=1");
        Map<String, String> params = new HashMap<String, String>();
        if (dictItemModel != null) {
            if (!StrUtil.isBlank(dictItemModel.getItemName())) {
                condition.append(" AND item_name like '%"+dictItemModel.getItemName()+"%' ");

            }
            if (!StrUtil.isBlank(dictItemModel.getItemDesc())) {
                condition.append(" AND item_desc like '%"+dictItemModel.getItemDesc()+"%' ");
            }
            if (!StrUtil.isBlank(dictItemModel.getItemState())) {
                condition.append(" AND item_state = :item_state");
                params.put("item_state", dictItemModel.getItemState());
            }
        }
        int total = DictItemEngine.getBeansCount(condition.toString(),
                params);
        DictItemBean[] beans = null;
        if (total > 0) {
            condition.append(" order by DICT_NAME,ITEM_ORDER");
            beans = DictItemEngine.getBeans(null, condition.toString(), params, resultDTO.getStart(), resultDTO.getEnd(), false);
        }

        resultDTO.setRows(BeanUtils.beanToList(beans, DictItemModel.class));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    /**
     * 新增字典项
     */
    public void addDictItem(DictItemBean dictItemBean, String operatorCode)
            throws Exception {
        dictItemBean.setCreateDate(SysdateManager.getSysTimestamp());
        dictItemBean.setCreateUser(operatorCode);
        DictItemEngine.save(dictItemBean);

    }

    @Override
    public void updateDictItem(DictItemBean dictItemBean, String operatorCode) throws Exception {
        //生成修改bean，并设置主键
    	DictItemBean updateBean = new DictItemBean();
        updateBean.setDictName(dictItemBean.getDictName());
        updateBean.setItemNo(dictItemBean.getItemNo());
        updateBean.setStsToOld();

        DataContainerFactory.copyNoClearData(dictItemBean,updateBean);

        DictItemEngine.save(updateBean);
    }

    @Override
    public boolean deleteDictItem(String dictName, String item_no) throws Exception {
        DictItemBean bean = new DictItemBean();
        bean.setDictName(dictName);
        bean.setItemNo(item_no);
        bean.setStsToOld();
        bean.delete();
        DictItemEngine.save(bean);
        return true;
    }

    @Override
    public DictItemModel getDictItem(String dictName, String itemNo) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        map.put("itemNo", itemNo);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName AND ITEM_NO=:itemNo", map);
        List<DictItemModel> dictItemModels = BeanUtils.beanToList(dictItemBeans,DictItemModel.class);
        if(null != dictItemModels && dictItemModels.size()>0 ){
        	return dictItemModels.get(0);
        }else{
        	return null;
        }
        
    }
    
    public List<Map> queryDictListMap(String dictName) throws Exception {
        DictBean[] dictItemBeans = DictCache.getDictItemsByDictName(dictName);
        List<Map> list = new ArrayList();
        if(dictItemBeans!=null)
            for(int i=0;i<dictItemBeans.length;i++){
                list.add(ServiceUtil.transerBeanToMap(dictItemBeans[i]));
            }
        return list;
    }
}
