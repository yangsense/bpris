package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;

import com.ai.aris.server.basecode.bean.AiscOperator2CareProvBean;
import com.ai.aris.server.basecode.model.AiscOperator2CareProvModel;
import com.ai.common.domain.ResultDTO;

public interface IAiscOperator2CareProvSV {
	 public ResultDTO queryPageList(AiscOperator2CareProvModel model, ResultDTO resultDTO) throws Exception;

     public AiscOperator2CareProvBean getAiscOperator2CareProv(long id) throws Exception;
     
     public boolean deleteOperator2CareProv(long id) throws Exception;
     
     public void saveAiscOperator2CareProv(AiscOperator2CareProvBean bean) throws Exception;
     
     public List<AiscOperator2CareProvModel> getNewCareList(long operator_id,String orgId) throws Exception;
     
     public List<AiscOperator2CareProvModel> getNotJoinCare(long operator_id,String orgId) throws Exception;
     
     public void deleUser2careprov(String operId) throws Exception;
     
     public void saveUser2careprov(String operId,String operCode,String careprovId) throws Exception;
}
