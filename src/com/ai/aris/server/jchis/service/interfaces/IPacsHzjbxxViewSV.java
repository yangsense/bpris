package com.ai.aris.server.jchis.service.interfaces;

import com.ai.aris.server.jchis.model.PacsHzjbxxViewModel;
import com.ai.common.domain.ResultDTO;

public interface IPacsHzjbxxViewSV {
	
	public int getHisStudyInfoCount(PacsHzjbxxViewModel searchModel)	throws Exception; 
	
	public ResultDTO getHisStudyInfo(PacsHzjbxxViewModel searchModel,ResultDTO resultDTO) throws Exception;

}
