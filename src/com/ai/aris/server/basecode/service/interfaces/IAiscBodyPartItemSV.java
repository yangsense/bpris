package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.model.AiscBodyPartItem;
import com.ai.common.domain.ResultDTO;

public interface IAiscBodyPartItemSV {
	 public ResultDTO queryPageList(AiscBodyPartItem model, ResultDTO resultDTO) throws Exception;

     public AiscBodyPart2ItemBean getAiscBodyPartItem(long id) throws Exception;
     
     public boolean deleteAiscBodyPartItem(long id) throws Exception;
     
     public void saveAiscBodyPartItem(long itemmastId,String orgId,String bodypartCode) throws Exception;
     public void deleAiscBodyPartItem(String orgId,long itemmastId) throws Exception;
     public List<Map> getAiscBodyPartUnSelect(List<Map> itemMastList,long itemmastId,String orgId,String bodypartType,long bodypartPid) throws Exception ;
     public List<Map> getAiscBodyPartSelected(List<Map> itemMastList,long itemmastId,String orgId,String bodypartType,long bodypartPid) throws Exception ;

}
