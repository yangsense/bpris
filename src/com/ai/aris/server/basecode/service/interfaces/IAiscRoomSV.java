package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.model.AiscRoom;
import com.ai.common.domain.ResultDTO;

public interface IAiscRoomSV {
	 public ResultDTO queryPageList(AiscRoom model, ResultDTO resultDTO) throws Exception;

     public AiscRoomBean getAiscRoom(long id) throws Exception;
     
     public boolean deleteAiscRoom(long id) throws Exception;
     
     public void saveAiscRoom(AiscRoomBean bean) throws Exception;
     
     public List<Map> getRoomSelect(String orgId,long locId) throws Exception;
}
