package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.model.AiscEquipment;
import com.ai.common.domain.ResultDTO;

public interface IAiscEquipSV {
	 public ResultDTO queryPageList(AiscEquipment model, ResultDTO resultDTO) throws Exception;

     public List<Map> queryDict(String dictName) throws Exception;
     
     public AiscEquipmentBean getAiscEquipBean(long id) throws Exception;
     
     public boolean deleteAiscEquip(long id) throws Exception;
     
     public void saveAiscEquip(AiscEquipmentBean bean) throws Exception;

	 public boolean checkEquipmentCode(long equipmentId,String equipmentCode,String orgId) throws Exception;
}
