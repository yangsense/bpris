package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscMediumInfoBean;
import com.ai.aris.server.basecode.model.AiscMediumInfo;
import com.ai.common.domain.ResultDTO;

public interface IAiscMediumInfoSV {
	 public ResultDTO queryPageList(AiscMediumInfo model, ResultDTO resultDTO) throws Exception;

     public AiscMediumInfoBean getAiscMediumInfo(long id) throws Exception;
     
     public boolean deleteAiscMediumInfo(long id) throws Exception;
     
     public void saveAiscMediumInfo(AiscMediumInfoBean bean) throws Exception;
}
