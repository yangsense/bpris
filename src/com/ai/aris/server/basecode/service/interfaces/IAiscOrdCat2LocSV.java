package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.model.QryOrdCat2LocModel;
import com.ai.common.domain.ResultDTO;

public interface IAiscOrdCat2LocSV {
	 public ResultDTO queryPageList(QryOrdCat2LocModel model, ResultDTO resultDTO) throws Exception;

     public AiscOrdCat2LocBean getQryOrdCat2LocBean(long id) throws Exception;
     
     public boolean deleteOrdCat2Loc(long id) throws Exception;
     
     public void saveOrdCat2Loc(AiscOrdCat2LocBean bean) throws Exception;
     public Boolean checkIsExist(AiscOrdCat2LocBean bean) throws Exception;

	AiscLocBean[] getLocItem(String keyword) throws Exception;

	AiscOrdCategoryBean[] getOrdCategoryItem(String keyword) throws Exception;
     
     
}
