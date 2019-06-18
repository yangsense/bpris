package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.basecode.model.AiscServerInfo;
import com.ai.common.domain.ResultDTO;

public interface IAiscServerInfoSV {
	 public ResultDTO queryPageList(AiscServerInfo model, ResultDTO resultDTO) throws Exception;

     public AiscServerInfoBean getAiscServerInfo(long id) throws Exception;
     
     public boolean deleteAiscServerInfo(long id) throws Exception;
     
     public void saveAiscServerInfo(AiscServerInfoBean bean) throws Exception;
     
     public List<Map> getServerSelect() throws Exception;
}
