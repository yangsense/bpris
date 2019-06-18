package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryEngine;
import com.ai.aris.server.basecode.bean.QueryAiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.QueryAiscOrdSubCategoryEngine;
import com.ai.aris.server.basecode.bean.QueryAiscOrdcategoryBean;
import com.ai.aris.server.basecode.bean.QueryAiscOrdcategoryEngine;
import com.ai.aris.server.basecode.bean.QueryBigSortBean;
import com.ai.aris.server.basecode.bean.QueryBigSortEngine;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdCateSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscOrdCateSVImpl implements IAiscOrdCateSV {

    @Override
    public ResultDTO queryPageList(AiscOrdCategoryModel model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getOrdcategoryDesc())) {
            condition.append(" and OrdCategory_Desc like '%"+model.getOrdcategoryDesc()+"%' ");
        }
        if (isNotBlank(model.getOrdcategoryEndesc())) {
            condition.append(" and OrdCategory_EnDesc like '%"+model.getOrdcategoryEndesc()+"%' ");
        }
        if(isNotBlank(model.getOrdcategoryCode())){
            condition.append(" and OrdCategory_Code like '%"+model.getOrdcategoryCode()+"%' ");
        }
    	if (!"-1".equals(model.getCityCode())&& StringUtils.isNotBlank(model.getCityCode())) {
			condition.append(" and city_code = '"+model.getCityCode()+"'");
		}
		if (!"-1".equals(model.getCountyCode())&& StringUtils.isNotBlank(model.getCountyCode())) {
			condition.append(" and county_code ='"+model.getCountyCode()+"'");
		}
		if (!"-1".equals(model.getOrgId())&& StringUtils.isNotBlank(model.getOrgId())) {
			condition.append(" and org_id ='"+model.getOrgId()+"'");
		}
        int total = QueryAiscOrdcategoryEngine.getBeansCount(condition.toString(), null);
        QueryAiscOrdcategoryBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY OrdCategory_ID desc");
            beans = QueryAiscOrdcategoryEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscOrdCategoryModel.class));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    @Override
    public AiscOrdCategoryModel getAiscOrdCategory(long id) throws Exception {
        return BeanUtils.beanToModel(AiscOrdCategoryEngine.getBean(id), AiscOrdCategoryModel.class);
    }
    @Override
    public boolean deleteAiscOrdCategory(long id) throws Exception{
        AiscOrdCategoryBean bean = AiscOrdCategoryEngine.getBean(id);
        bean.setStsToOld();
    	 bean.delete();
    	 try {
             AiscOrdCategoryEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscOrdCategory(AiscOrdCategoryBean bean) throws Exception{
    	if (bean.getOrdcategoryId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQ_OrdCategory");
	    	bean.setOrdcategoryId(id);
            AiscOrdCategoryEngine.save(bean);
    	}else {
            AiscOrdCategoryBean oldBean = AiscOrdCategoryEngine.getBean(bean.getOrdcategoryId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscOrdCategoryEngine.save(oldBean);
        }
    }
    @Override
	public List<Map> getAiscOrdCategorySelect() throws Exception {
		Map map = new HashMap();
		AiscOrdCategoryBean[] beans = AiscOrdCategoryEngine.getBeans(" ORDER BY ORDCATEGORY_DESC ASC", map);
        List<Map> list = new ArrayList();
        for(AiscOrdCategoryBean ordCategoryBean:beans){
            list.add(ServiceUtil.transerBeanToMap(ordCategoryBean));
        }
        return list;
	}
    public List<Map> getAiscOrdCategorySelect2(String orgId) throws Exception {
    	Map map = new HashMap();
    	StringBuffer condition = new StringBuffer("1=1");
    	if(StringUtils.isNotBlank(orgId)){
    		condition.append(" AND ORG_ID='"+orgId+"'");
    	}
    	condition.append(" ORDER BY ORDCATEGORY_DESC ASC");
		AiscOrdCategoryBean[] beans = AiscOrdCategoryEngine.getBeans(condition.toString(), null);
        List<Map> list = new ArrayList();
        for(AiscOrdCategoryBean ordCategoryBean:beans){
            list.add(ServiceUtil.transerBeanToMap(ordCategoryBean));
        }
        return list;
    }
    public List<Map> getAiscOrdCategorySelect2(String cityCode,String countyCode,String orgId) throws Exception {
    	Map map = new HashMap();
    	StringBuffer condition = new StringBuffer(" 1=1");
    	if(cityCode != null &&  !"".equals(cityCode) && !"-1".equals(cityCode)){
    		condition.append(" AND CITY_CODE='"+cityCode+"'");
    	}
    	if(countyCode != null &&  !"".equals(countyCode)  && !"-1".equals(countyCode)){
    		condition.append(" AND COUNTY_CODE='"+countyCode+"'");
    	}
    	if(orgId != null &&  !"".equals(orgId)  && !"-1".equals(orgId)){
    		condition.append(" AND ORG_ID='"+orgId+"'");
    	}
    	QueryBigSortBean[] beans = QueryBigSortEngine.getBeans(condition.toString(), map);
        List<Map> list = new ArrayList();
        for(QueryBigSortBean ordCategoryBean:beans){
            list.add(ServiceUtil.transerBeanToMap(ordCategoryBean));
        }
        return list;
    }
    public List<Map> getAiscOrdCategorySelect3(String cityCode,String countyCode,String orgId ,String ordcategoryId) throws Exception {
    	Map map = new HashMap();
    	StringBuffer condition = new StringBuffer(" 1=1");
    	if(cityCode != null &&  !"".equals(cityCode)){
    		condition.append(" AND CITY_CODE='"+cityCode+"'");
    	}
    	if(countyCode != null &&  !"".equals(countyCode)){
    		condition.append(" AND COUNTY_CODE='"+countyCode+"'");
    	}
    	if(orgId != null &&  !"".equals(orgId)){
    		condition.append(" AND ORG_ID='"+orgId+"'");
    	}
    	if(ordcategoryId != null &&  !"".equals(ordcategoryId)){
    		condition.append(" AND ORDCATEGORY_ID='"+ordcategoryId+"'");
    	}
    	condition.append(" ORDER BY ORDCATEGORY_DESC ASC");
    	QueryAiscOrdSubCategoryBean[] beans = QueryAiscOrdSubCategoryEngine.getBeans(condition.toString(), map);
        List<Map> list = new ArrayList();
        for(QueryAiscOrdSubCategoryBean ordCategoryBean:beans){
            list.add(ServiceUtil.transerBeanToMap(ordCategoryBean));
        }
        return list;
    }
	@Override
	public boolean chekcAiscOrdCate(long ordcategoryId,String ordcategoryCode,String orgId) throws Exception {
		 StringBuffer condition = new StringBuffer();
	        condition.append(" 1=1");
	        if(ordcategoryId!=0){
	        	condition.append(" and ordcategory_id<>"+ordcategoryId);
	        }
	        if(isNotBlank(ordcategoryCode)) {
	            condition.append(" and ordcategory_code = '"+ordcategoryCode.trim()+"' ");
	        }
	        if(isNotBlank(ordcategoryCode)) {
	            condition.append(" and org_id = '"+orgId+"' ");
	        }
	        return AiscOrdCategoryEngine.getBeansCount(condition.toString(), null)==0;
	}

}

