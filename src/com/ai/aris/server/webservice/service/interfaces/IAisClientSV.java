package com.ai.aris.server.webservice.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.webservice.model.AiscSeachModel;
import com.ai.common.domain.ResultDTO;

public interface IAisClientSV {
    public AiscLocBean[] getAiscLocBean(String sql) throws Exception;
    public ResultDTO getAiscLocList(AiscSeachModel aiscSeachModel, ResultDTO resultDTO) throws Exception ;
}
