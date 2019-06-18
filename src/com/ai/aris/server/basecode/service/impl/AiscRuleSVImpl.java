package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.HashMap;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscRuleBean;
import com.ai.aris.server.basecode.bean.AiscRuleEngine;
import com.ai.aris.server.basecode.bean.QryAiscRuleBean;
import com.ai.aris.server.basecode.bean.QryAiscRuleEngine;
import com.ai.aris.server.basecode.model.AiscRule;
import com.ai.aris.server.basecode.service.interfaces.IAiscRuleSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscRuleSVImpl implements IAiscRuleSV {

    @Override
    public ResultDTO queryPageList(AiscRule model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(model.getRuleId()!=null&&!String.valueOf(model.getRuleId()).equals("")) {
            condition.append(" and Rule_id like '%"+model.getRuleId()+"%' ");
        }
        if (isNotBlank(String.valueOf(model.getRulePrix()))) {
            condition.append(" and Rule_Prix like '%"+model.getRulePrix()+"%' ");
        }
        if(model.getRuleType()!=-1&&isNotBlank(String.valueOf(model.getRuleType()))){
            condition.append(" and Rule_type = '"+model.getRuleType()+"' ");
        }
        if(!model.getOrgId().equals("-1")&&isNotBlank(model.getOrgId())){
            condition.append(" and org_id = '"+model.getOrgId()+"' ");
        }
        if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))) {
        	condition.append(" and loc_id = '"+model.getLocId()+"' ");
        }

        int total = QryAiscRuleEngine.getBeansCount(condition.toString(), null);
        QryAiscRuleBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY Rule_ID desc");
            beans = QryAiscRuleEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("ruleType", new DictTranslator("ruleType","RULE_TYPE","ruleTypeDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscRule.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public AiscRuleBean getAiscRule(long id) throws Exception {
        return AiscRuleEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscRule(long id) throws Exception{
    	 AiscRuleBean bean = getAiscRule(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscRuleEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscRule(AiscRuleBean bean) throws Exception{
    	if (bean.getRuleId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQRULEID");
	    	bean.setRuleId(id);
	    	bean.setOrgId(bean.getOrgId().equals("-1")?"":bean.getOrgId());
	    	AiscRuleEngine.save(bean);
    	}else {
    		AiscRuleBean oldBean = AiscRuleEngine.getBean(bean.getRuleId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscRuleEngine.save(oldBean);
        }
    }
}

