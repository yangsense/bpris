package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscTpcat2LocBean;
import com.ai.aris.server.basecode.model.AiscTpcat2Loc;
import com.ai.common.domain.ResultDTO;

public interface IAiscTpcat2LocSV {
	 public ResultDTO queryPageList(AiscTpcat2Loc model, ResultDTO resultDTO) throws Exception;

     public AiscTpcat2LocBean getAiscTpcat2Loc(long id) throws Exception;
     
     public boolean deleteAiscTpcat2Loc(long id) throws Exception;
     
     public void saveAiscTpcat2Loc(AiscTpcat2LocBean bean) throws Exception;
}
