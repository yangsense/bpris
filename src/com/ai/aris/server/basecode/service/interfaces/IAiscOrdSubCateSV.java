package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.aris.server.basecode.model.AiscOrdSubCategoryModel;
import com.ai.aris.server.basecode.model.QueryAiscOrdSubCategoryModel;
import com.ai.common.domain.ResultDTO;

public interface IAiscOrdSubCateSV {
	 public ResultDTO queryPageList(AiscOrdSubCategoryModel model, ResultDTO resultDTO) throws Exception;
	 
	 public ResultDTO queryPageList2(QueryAiscOrdSubCategoryModel model, ResultDTO resultDTO) throws Exception;
     
     public AiscOrdSubCategoryModel getAiscOrdSubCategory(long id) throws Exception;
     
     public boolean deleteAiscOrdSubCategory(long id) throws Exception;
     
     public void saveAiscOrdSubCategory(AiscOrdSubCategoryBean bean) throws Exception;
     
     public List<Map> getAiscOrdSubCategorySelect(String ordcategoryId,String orgId) throws Exception ;

	public boolean checkSubOrdcategoryCode(long ordsubcategoryd,String ordsubcategoryCode)throws Exception ;
}
