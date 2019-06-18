package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.model.AiscPeriodModel;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.common.domain.ResultDTO;

public interface IAiscPeriodSV {
	public ResultDTO queryPageList(AiscPeriodModel model, ResultDTO resultDTO) throws Exception;
	
	public AiscPeriodBean getBean(long id) throws Exception;
    
    public void save(AiscPeriodBean bean) throws Exception;
}
