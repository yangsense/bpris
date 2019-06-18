package com.ai.aris.server.workstation.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.model.QueryCheckMasterModel;
import com.ai.common.domain.ResultDTO;

public interface ICheckConOrgSV {

	public ResultDTO queryPageList(QueryCheckMasterModel model,String orgId, ResultDTO resultDTO) throws Exception;
	
	public List<Map>  getLocInfo(String orgId)throws Exception;
	public  List queryStudyItem(QueryCheckMasterModel model) throws Exception;
	public void updateCheck(QueryCheckMasterModel model) throws Exception;
	public void updateRefuseStatus(AisStudyInfoBean bean) throws Exception;
}
