package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscRuleBean;
import com.ai.aris.server.basecode.model.AiscRule;
import com.ai.common.domain.ResultDTO;

public interface IAiscRuleSV {
	 public ResultDTO queryPageList(AiscRule model, ResultDTO resultDTO) throws Exception;

     public AiscRuleBean getAiscRule(long id) throws Exception;
     
     public boolean deleteAiscRule(long id) throws Exception;
     
     public void saveAiscRule(AiscRuleBean bean) throws Exception;
}
