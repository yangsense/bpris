package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.common.domain.ResultDTO;

public interface IAiscOrdCateSV {
	 public ResultDTO queryPageList(AiscOrdCategoryModel model, ResultDTO resultDTO) throws Exception;
	 
     public AiscOrdCategoryModel getAiscOrdCategory(long id) throws Exception;
     
     public boolean deleteAiscOrdCategory(long id) throws Exception;
     
     public void saveAiscOrdCategory(AiscOrdCategoryBean bean) throws Exception;
     
     public List<Map> getAiscOrdCategorySelect() throws Exception ;
     
     public List<Map> getAiscOrdCategorySelect2(String orgId) throws Exception ;
     
     public List<Map> getAiscOrdCategorySelect2(String cityCode,String countyCode,String orgId) throws Exception ;
     
     public List<Map> getAiscOrdCategorySelect3(String cityCode,String countyCode,String orgId ,String ordcategoryId) throws Exception ;

	public boolean chekcAiscOrdCate(long ordcategoryId,String ordcategoryCode,String orgId)throws Exception;
}
