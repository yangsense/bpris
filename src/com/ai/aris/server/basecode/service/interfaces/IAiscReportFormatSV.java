package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.model.AiscReportFormat2Loc;
import com.ai.common.domain.ResultDTO;

public interface IAiscReportFormatSV {
	 public ResultDTO queryPageList(AiscReportFormat2Loc model, ResultDTO resultDTO) throws Exception;

     public AiscReportFormat2Loc getAiscReport(long id) throws Exception;
     
     public boolean deleteAiscReport(long id) throws Exception;
     
     public void saveAiscReport(AiscReportFormat2Loc bean) throws Exception;
    public Boolean checkIsExist(AiscReportFormat2Loc bean) throws Exception ;
}
