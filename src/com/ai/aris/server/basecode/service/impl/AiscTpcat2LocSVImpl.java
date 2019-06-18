package com.ai.aris.server.basecode.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscTpcat2LocBean;
import com.ai.aris.server.basecode.bean.AiscTpcat2LocEngine;
import com.ai.aris.server.basecode.model.AiscTpcat2Loc;
import com.ai.aris.server.basecode.service.interfaces.IAiscTpcat2LocSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscTpcat2LocSVImpl implements IAiscTpcat2LocSV {

    @Override
    public ResultDTO queryPageList(AiscTpcat2Loc model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(model.getModalityId()!=null&&!String.valueOf(model.getModalityId()).equals("")) {
            condition.append(" and Modality_ID like '%"+model.getModalityId()+"%' ");
        }
        if (model.getLocId()!=null&&!String.valueOf(model.getLocId()).equals("")) {
            condition.append(" and Loc_ID like '%"+model.getLocId()+"%' ");
        }
        if(model.getTemplatecatId()!=null&&!String.valueOf(model.getTemplatecatId()).equals("")){
            condition.append(" and TemplateCat_ID like '%"+model.getTemplatecatId()+"%' ");
        }

        int total = AiscTpcat2LocEngine.getBeansCount(condition.toString(), null);
        AiscTpcat2LocBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY TplCat2Loc_ID desc");
            beans = AiscTpcat2LocEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscTpcat2Loc.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public AiscTpcat2LocBean getAiscTpcat2Loc(long id) throws Exception {
        return AiscTpcat2LocEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscTpcat2Loc(long id) throws Exception{
    	 AiscTpcat2LocBean bean = getAiscTpcat2Loc(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscTpcat2LocEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscTpcat2Loc(AiscTpcat2LocBean bean) throws Exception{
    	if (bean.getTplcat2locId()==0) {
	    	long id = ServiceUtil.getSequence("SEQTPCAT2LOCID");
	    	bean.setTplcat2locId(id);
	    	AiscTpcat2LocEngine.save(bean);
    	}else {
    		AiscTpcat2LocBean oldBean = AiscTpcat2LocEngine.getBean(bean.getTplcat2locId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscTpcat2LocEngine.save(oldBean);
        }
    }
}

