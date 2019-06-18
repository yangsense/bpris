package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.sysmanage.model.SysOrgModel;
import com.ai.common.domain.ResultDTO;

public interface IAiscAreaInstitutionSV {
    public ResultDTO queryPageList(SysOrgModel model, ResultDTO resultDTO) throws Exception;
//    public void importAreaToOrg() throws Exception;
    public void updateHasPacs(long id, int status)throws Exception;
}
