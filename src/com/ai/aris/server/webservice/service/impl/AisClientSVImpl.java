package com.ai.aris.server.webservice.service.impl;

import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.webservice.bean.AiscLocData;
import com.ai.aris.server.webservice.model.AiscSeachModel;
import com.ai.aris.server.webservice.service.interfaces.IAisClientSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AisClientSVImpl implements IAisClientSV {

	@Override
	public AiscLocBean[] getAiscLocBean(String sql) throws Exception {
		Map parameter = new HashMap();
		AiscLocBean[] beans = AiscLocEngine.getBeans(sql, parameter);
		if(beans.length>0){
			return beans;
		}
		return null;
	}
	
	public ResultDTO getAiscLocList(AiscSeachModel aiscSeachModel, ResultDTO resultDTO) throws Exception{
		AiscLocBean[] beans  = null ;
		StringBuffer condition = new StringBuffer() ;
		Map<String,String> map = new HashMap();
		condition.append(" 1=1 ");
		if (StringUtils.isNotEmpty(aiscSeachModel.getOrgId())){
			condition.append(" and ORG_ID = :ORG_ID" );
			map.put("ORG_ID",aiscSeachModel.getOrgId());
		}
		int count = AiscLocEngine.getBeansCount(condition.toString(),map);
		if (count==0) return null ;
		beans =  AiscLocEngine.getBeans(condition.toString(),map);
		resultDTO.setRows(BeanUtils.beanToList(beans,AiscLocData.class));
		resultDTO.setRecords(beans.length);
		return  resultDTO ;
	}
}
