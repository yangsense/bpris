package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscLoginLocBean;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.model.AiscLoginLoc;
import com.ai.common.domain.ResultDTO;

public interface IAiscLoginLocSV {
	 public ResultDTO queryPageList(AiscLoginLoc model, ResultDTO resultDTO) throws Exception;

     public AiscLoginLocBean getAiscLoginLoc(long id) throws Exception;
     
     public boolean deleteAiscLoginLoc(long id) throws Exception;
     
     public void saveAiscLoginLoc(AiscLoginLocBean bean) throws Exception;
     
     public List<Map> getCareprovlist(AiscLoginLoc model) throws Exception;
     
     public List<Map> getLocNameSelect(AiscLoginLoc model) throws Exception;
     
     public List<AiscLoc> getNewLocList(String operator_id,String orgId) throws Exception;
     
     public List<AiscLoc> getNotJoinLoc(String operator_id,String orgId) throws Exception;
     
     public void deleLoginLoc(String operId,String orgId) throws Exception;
     
     public void saveLoginLoc(String operId,String orgId,String locId) throws Exception;
     
}
