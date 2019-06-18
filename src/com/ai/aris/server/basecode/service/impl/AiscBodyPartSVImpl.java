package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.bean.AiscBodyPartEngine;
import com.ai.aris.server.basecode.bean.QryAiscBodyPartBean;
import com.ai.aris.server.basecode.bean.QryAiscBodyPartEngine;
import com.ai.aris.server.basecode.model.AiscBodyPart;
import com.ai.aris.server.basecode.service.interfaces.IAiscBodyPartSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscBodyPartSVImpl implements IAiscBodyPartSV {

    @Override
    public ResultDTO queryPageList(AiscBodyPart model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getBodypartCode())) {
            condition.append(" and BodyPart_Code like '%"+model.getBodypartCode()+"%' ");
        }
        if (isNotBlank(model.getBodypartDesc())) {
            condition.append(" and BodyPart_Desc like '%"+model.getBodypartDesc()+"%' ");
        }
        if(isNotBlank(model.getBodypartEndesc())){
            condition.append(" and BodyPart_EnDesc like '%"+model.getBodypartEndesc()+"%' ");
        }
        if(isNotBlank(model.getOrgId())){
            condition.append(" and org_id='"+model.getOrgId()+"' ");
        }
        if(!"-1".equals(model.getBodypartType())&&isNotBlank(model.getBodypartType())){
            condition.append(" and bodypart_type='"+model.getBodypartType()+"' ");
        }
        if(model.getBodypartPid()!=-1){
            condition.append(" and bodypart_pid="+model.getBodypartPid()+" ");
        }

        int total = QryAiscBodyPartEngine.getBeansCount(condition.toString(), null);
        QryAiscBodyPartBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY bodypart_type ");
            beans = QryAiscBodyPartEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscBodyPart.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public AiscBodyPartBean getAiscBodyPart(String id) throws Exception {
        return AiscBodyPartEngine.getBean(id);
    }
    
    public List<Map> getBodyparType(String orgId) throws Exception {
        Map map = new HashMap();
        map.put("orgId", orgId);
        AiscBodyPartBean[] beans = AiscBodyPartEngine.getBeans(" org_id = :orgId and bodypart_type='1'", map);
        List<Map> list = new ArrayList();
        for (AiscBodyPartBean bodypartBean : beans) {
            list.add(ServiceUtil.transerBeanToMap(bodypartBean));
        }
        return list;
    }
    
    @Override
    public boolean deleteAiscBodyPart(String id) throws Exception{
    	 AiscBodyPartBean bean = getAiscBodyPart(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscBodyPartEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscBodyPart(AiscBodyPartBean bean) throws Exception{
    	if (bean.getBodypartCode()==null) {
	    	String id = String.valueOf(ServiceUtil.getSequence("SEQBODYPARTID"));
	    	bean.setBodypartCode(id);
	    	AiscBodyPartBean pbean = AiscBodyPartEngine.getBean(String.valueOf(bean.getBodypartPid()));
	    	bean.setBodypartPidname(pbean.getBodypartDesc());
	    	AiscBodyPartEngine.save(bean);
    	}else {
    		AiscBodyPartBean oldBean = AiscBodyPartEngine.getBean(bean.getBodypartCode());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscBodyPartBean pbean = AiscBodyPartEngine.getBean(String.valueOf(bean.getBodypartPid()));
            oldBean.setBodypartPidname(pbean.getBodypartDesc());
            AiscBodyPartEngine.save(oldBean);
        }
    }

    public Boolean checkIsExist(AiscBodyPartBean bean) throws Exception{
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(bean.getBodypartCode())&&!"0".equals(bean.getBodypartCode())) {
        	if(isNotBlank(bean.getBodypartType())&&"1".equals(bean.getBodypartType())){
        		condition.append(" and BODYPART_TYPE = '"+bean.getBodypartType()+"'");
            }
        	condition.append(" and BODYPART_CODE <>"+bean.getBodypartCode());
        }
        if(isNotBlank(bean.getBodypartDesc())&&!"-1".equals(bean.getBodypartDesc())) {
            condition.append(" and BODYPART_DESC = '"+bean.getBodypartDesc()+"'");
        }
        if(isNotBlank(bean.getOrgId())&&!"-1".equals(bean.getOrgId())) {
            condition.append(" and org_id = '"+bean.getOrgId()+"'");
        }

        int count = AiscBodyPartEngine.getBeansCount(condition.toString(), null);
        if (count>0){
            return  true ;
        }else return  false ;
    }
    
    public String partimport(String orgId) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1 and org_id='-999' ");
        AiscBodyPartBean[] publicParts = AiscBodyPartEngine.getBeans(condition.toString(), null);
        for(AiscBodyPartBean bean:publicParts){
        	bean.setOrgId(orgId);
        	if(!checkIsImport(bean)){
	        	String id = String.valueOf(ServiceUtil.getSequence("SEQBODYPARTID"));
	        	bean.setBodypartCode(id);
	        	bean.setStsToNew();
	        	AiscBodyPartEngine.save(bean);
        	}
        }
        return "0";
    }
    
    public Boolean checkIsImport(AiscBodyPartBean bean) throws Exception{
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        condition.append(" and BODYPART_TYPE = '"+bean.getBodypartType()+"'");
        condition.append(" and BODYPART_DESC = '"+bean.getBodypartDesc()+"'");
        condition.append(" and org_id = '"+bean.getOrgId()+"'");

        int count = AiscBodyPartEngine.getBeansCount(condition.toString(), null);
        if (count>0){
            return  true ;
        }else return  false ;
    }
}

