package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.QryCareProvBean;
import com.ai.aris.server.basecode.model.AiscCareProv;
import com.ai.common.domain.ResultDTO;

public interface IAiscCareProvSV {
	 public ResultDTO queryPageList(AiscCareProv model, ResultDTO resultDTO) throws Exception;

     public List<Map> queryDict(String dictName) throws Exception;
     
     public AiscCareProvBean getAiscCareProv(long id) throws Exception;
     
     public boolean deleteAiscCareProv(long id) throws Exception;
     
     public void saveAiscCareProv(AiscCareProvBean bean) throws Exception;

	public boolean checkCareprovCode(long careprovId,String careprovCode,String orgId) throws Exception;
	
	public QryCareProvBean getCareprovByOperatorId(String orgId,String locId,String operator_id) throws Exception;
     
}
