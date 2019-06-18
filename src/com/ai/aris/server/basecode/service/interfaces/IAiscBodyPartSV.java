package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.model.AiscBodyPart;
import com.ai.common.domain.ResultDTO;

public interface IAiscBodyPartSV {
	 public ResultDTO queryPageList(AiscBodyPart model, ResultDTO resultDTO) throws Exception;

     public AiscBodyPartBean getAiscBodyPart(String id) throws Exception;
     
     public boolean deleteAiscBodyPart(String id) throws Exception;
     
     public void saveAiscBodyPart(AiscBodyPartBean bean) throws Exception;

     public Boolean checkIsExist(AiscBodyPartBean bean) throws Exception ;
     
     public List<Map> getBodyparType(String orgId) throws Exception;
     
     public String partimport(String orgId) throws Exception;
}
