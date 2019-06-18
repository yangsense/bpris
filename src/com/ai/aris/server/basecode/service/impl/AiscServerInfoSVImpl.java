package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.basecode.bean.AiscServerInfoEngine;
import com.ai.aris.server.basecode.model.AiscServerInfo;
import com.ai.aris.server.basecode.service.interfaces.IAiscServerInfoSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscServerInfoSVImpl implements IAiscServerInfoSV {

    @Override
    public ResultDTO queryPageList(AiscServerInfo model,ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(model.getServerId()!=null&&!String.valueOf(model.getServerId()).equals("")) {
            condition.append(" and server_id like '%"+model.getServerId()+"%' ");
        }
        if (isNotBlank(String.valueOf(model.getServerName()))) {
            condition.append(" and server_name like '%"+model.getServerName()+"%' ");
        }
        if(model.getServerType()!=-1&&isNotBlank(String.valueOf(model.getServerType()))){
            condition.append(" and server_type = '"+model.getServerType()+"' ");
        }

        int total = AiscServerInfoEngine.getBeansCount(condition.toString(), null);
        AiscServerInfoBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY Server_ID desc");
            beans = AiscServerInfoEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        dicts.put("serverType", new DictTranslator("serverType","SERVER_TYPE","serverTypeDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscServerInfo.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }

    @Override
    public AiscServerInfoBean getAiscServerInfo(long id) throws Exception {
        return AiscServerInfoEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscServerInfo(long id) throws Exception{
    	 AiscServerInfoBean bean = getAiscServerInfo(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscServerInfoEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscServerInfo(AiscServerInfoBean bean) throws Exception{
    	if (bean.getServerId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQSERVERINFOID");
	    	bean.setServerId(id);
	    	AiscServerInfoEngine.save(bean);
    	}else {
    		AiscServerInfoBean oldBean = AiscServerInfoEngine.getBean(bean.getServerId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscServerInfoEngine.save(oldBean);
        }
    }
    
    @Override
    public List<Map> getServerSelect() throws Exception {
        Map map = new HashMap();
        map.put("serverEnabled", "1");
        AiscServerInfoBean[] beans = AiscServerInfoEngine.getBeans("server_enabled=:serverEnabled ORDER BY server_id ASC", map);
        List<Map> list = new ArrayList();
        for(AiscServerInfoBean aiscServerBean:beans){
            list.add(ServiceUtil.transerBeanToMap(aiscServerBean));
        }
        return list;
    }
}

