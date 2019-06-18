package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryEngine;
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryEngine;
import com.ai.aris.server.basecode.bean.QryAiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.QryAiscOrdSubCategoryEngine;
import com.ai.aris.server.basecode.bean.QryAiscOrdsubcateBean;
import com.ai.aris.server.basecode.bean.QryAiscOrdsubcateEngine;
import com.ai.aris.server.basecode.bean.QueryAiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.bean.QueryAiscOrdSubCategoryEngine;
import com.ai.aris.server.basecode.model.AiscOrdSubCategoryModel;
import com.ai.aris.server.basecode.model.QueryAiscOrdSubCategoryModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdSubCateSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscOrdSubCateSVImpl implements IAiscOrdSubCateSV {

    @Override
    public ResultDTO queryPageList(AiscOrdSubCategoryModel model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getOrdsubcategoryDesc())) {
            condition.append(" and OrdSubCategory_Desc  like '%"+model.getOrdsubcategoryDesc()+"%' ");
        }
        if (isNotBlank(model.getOrdsubcategoryEndesc())) {
            condition.append(" and OrdSubCategory_EnDesc like '%"+model.getOrdsubcategoryEndesc()+"%' ");
        }
        if(isNotBlank(model.getOrdsubcategoryCode())){
            condition.append(" and OrdSubCategory_Code like '%"+model.getOrdsubcategoryCode()+"%' ");
        }
        if(isNotBlank(model.getOrdcategoryDesc())){
            condition.append(" and Ordcategory_Desc like '%"+model.getOrdcategoryDesc()+"%' ");
        }
        if(model.getOrdcategoryId()>0&&!"-1".equals(model.getOrdcategoryId())){
            condition.append(" and Ordcategory_Id = '"+model.getOrdcategoryId()+"' ");
        }
        if(model.getOrdsubcategoryId()>0&&!"-1".equals(model.getOrdsubcategoryId())){
            condition.append(" and OrdSubCategory_Id = '"+model.getOrdsubcategoryId()+"' ");
        }
        
        int total = QryAiscOrdSubCategoryEngine.getBeansCount(condition.toString(), null);
        QryAiscOrdSubCategoryBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY OrdSubCategory_ID desc");
            beans = QryAiscOrdSubCategoryEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscOrdSubCategoryModel.class));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    public ResultDTO queryPageList2(QueryAiscOrdSubCategoryModel model, ResultDTO resultDTO) throws Exception{

	        StringBuffer condition = new StringBuffer();
	        condition.append(" 1=1");
	        if(model != null){
	        		if(isNotBlank(model.getOrdsubcategoryDesc())) {
	     	            condition.append(" and OrdSubCategory_Desc  like '%"+model.getOrdsubcategoryDesc()+"%' ");
	     	        }
	     	        if (isNotBlank(model.getOrdsubcategoryEndesc())) {
	     	            condition.append(" and OrdSubCategory_EnDesc like '%"+model.getOrdsubcategoryEndesc()+"%' ");
	     	        }
	     	        if(isNotBlank(model.getOrdsubcategoryCode())){
	     	            condition.append(" and OrdSubCategory_Code like '%"+model.getOrdsubcategoryCode()+"%' ");
	     	        }
	     	        if(isNotBlank(model.getOrdcategoryDesc())){
	     	            condition.append(" and Ordcategory_Desc like '%"+model.getOrdcategoryDesc()+"%' ");
	     	        }
	     	        if(model.getOrdsubcategoryId()>0&&!"-1".equals(model.getOrdsubcategoryId())){
	     	            condition.append(" and OrdSubCategory_Id = '"+model.getOrdsubcategoryId()+"' ");
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
	     			if (!"-1".equals(String.valueOf(model.getOrdcategoryId()))&& StringUtils.isNotBlank(String.valueOf(model.getOrdcategoryId()))) {
	     				condition.append(" and ORDCATEGORY_ID ='"+model.getOrdcategoryId()+"'");
	     			}
	        }
	        int total = QueryAiscOrdSubCategoryEngine.getBeansCount(condition.toString(), null);
	        QueryAiscOrdSubCategoryBean[] beans = null;

	        if (total > 0) {
	            condition.append(" ORDER BY OrdSubCategory_ID desc");
	            beans = QueryAiscOrdSubCategoryEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
	        }
	        resultDTO.setRows(BeanUtils.beanToList(beans,QueryAiscOrdSubCategoryModel.class));
	        resultDTO.setRecords(total);
	        return resultDTO;
	    
	 }
    @Override
    public AiscOrdSubCategoryModel getAiscOrdSubCategory(long id) throws Exception {
        return BeanUtils.beanToModel(AiscOrdSubCategoryEngine.getBean(id),AiscOrdSubCategoryModel.class);
    }
    @Override
    public boolean deleteAiscOrdSubCategory(long id) throws Exception{
        AiscOrdSubCategoryBean bean = AiscOrdSubCategoryEngine.getBean(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
             AiscOrdSubCategoryEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscOrdSubCategory(AiscOrdSubCategoryBean bean) throws Exception{
    	if (bean.getOrdsubcategoryId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQ_OrdSubCategory");
	    	bean.setOrdsubcategoryId(id);
            AiscOrdSubCategoryEngine.save(bean);
    	}else {
            AiscOrdSubCategoryBean oldBean = AiscOrdSubCategoryEngine.getBean(bean.getOrdsubcategoryId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscOrdSubCategoryEngine.save(oldBean);
        }
    }
    @Override
    public List<Map> getAiscOrdSubCategorySelect(String ordcategoryId,String orgId) throws Exception {
    	Map map = new HashMap();
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 ");
        if(isNotBlank(ordcategoryId)) {
            condition.append(" and OrdCategory_Id  = '"+ordcategoryId+"' ");
        }
        if(isNotBlank(orgId)) {
            condition.append(" and org_id  = '"+orgId+"' ");
        }
        condition.append(" ORDER BY ORDSUBCATEGORY_DESC ASC") ;
        QryAiscOrdsubcateBean[] beans = QryAiscOrdsubcateEngine.getBeans(condition.toString(), map);
        List<Map> list = new ArrayList();
        for(QryAiscOrdsubcateBean ordSubCategoryBean:beans){
            list.add(ServiceUtil.transerBeanToMap(ordSubCategoryBean));
        }
        return list;
    }

	@Override
	public boolean checkSubOrdcategoryCode(long ordsubcategoryd,String ordsubcategoryCode)
			throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(ordsubcategoryd!=0){
        	condition.append(" and ordsubcategory_id<>"+ordsubcategoryd);
        }
        if(isNotBlank(ordsubcategoryCode)) {
            condition.append(" and ordsubcategory_code = '"+ordsubcategoryCode.trim()+"' ");
        }
        return AiscOrdSubCategoryEngine.getBeansCount(condition.toString(), null)==0;
	}
}

