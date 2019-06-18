package com.ai.aris.server.basecode.service.interfaces;

import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.model.AiscItemMastModel;
import com.ai.common.domain.ResultDTO;

import java.util.List;
import java.util.Map;

public interface IAiscItemMastSV {
	 public ResultDTO queryPageList(AiscItemMastModel model, ResultDTO resultDTO) throws Exception;
     
     public AiscItemMastModel getAiscItemMast(long id) throws Exception;
     
     public boolean deleteAiscItemMast(long id) throws Exception;
     
     public void saveAiscItemMast(AiscItemMastBean bean) throws Exception;

	 public Boolean checkIsExist(AiscItemMastBean bean) throws Exception ;

     public List<Map> getAiscItemMastSelect(long ordcategoryId , long ordsubcategoryId,String orgId) throws Exception ;

	public boolean checkItemmastCode(long itemmastId,String itemmastCode,String orgId)throws Exception;
	
	/**
	 * 导入项目
	 */
	public String importItem(String orgId,long ordcategoryId,long ordsubcategoryId,String locCode) throws Exception;
	/**
	 * 获取his项目
	 */
	public List<Map<String,Object>> getHisItem(String orgId,long ordcategoryId,long ordsubcategoryId,String locCode) throws Exception;
}
