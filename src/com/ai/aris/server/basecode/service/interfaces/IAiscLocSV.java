package com.ai.aris.server.basecode.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscConorganizationBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocStudyStaBean;
import com.ai.aris.server.basecode.bean.QueryOrgLocBean;
import com.ai.aris.server.basecode.model.AiscConsult;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.webservice.bean.SysOrg;
import com.ai.common.domain.ResultDTO;

public interface IAiscLocSV {
	 public ResultDTO queryPageList(AiscLoc model, ResultDTO resultDTO) throws Exception;

     public List<Map> queryDict(String dictName) throws Exception;
     
     public AiscLocBean getAiscLoc(long id) throws Exception;
     
     public boolean deleteAiscLoc(long id) throws Exception;
     
     public void saveAiscLoc(AiscLocBean bean) throws Exception;

	public Boolean checkIsExist(AiscLocBean bean) throws Exception;

	public List<Map> getLocNameSelect(String orgId) throws Exception;
	
	public List<Map> getAiscLocByType(String orgId,String locType) throws Exception;

	public boolean checkLocCode(long locId,String locCode,String orgId)throws Exception;
	
	public AiscLocBean getAiscLoc(String orgId,String locCode)throws Exception;
	
	public AiscLocBean getAiscLocById(String orgId,String locId)throws Exception;
	
	/**
	 * 导入科室
	 */
	public String importLoc(String orgId) throws Exception;
	
	/**
	 * 获取his科室
	 */
	public List<Map<String,Object>> getHisLocList(String orgId) throws Exception;
	
	public QueryOrgLocBean getOrgLoc(String orgId, String locId)
	throws Exception;
	public ResultDTO queryAiscConsultList(AiscConsult model,ResultDTO resultDTO) throws Exception ;
	public List<SysOrg> getHuizhenOrg(String orgId,String locId,String keyword) throws Exception ;
	public List<Map> getLocForOrg(String orgId) throws Exception ;
	public void saveConorganization(AiscConorganizationBean bean) throws Exception;
	public boolean deleteAisConorg(long id) throws Exception;
	public AiscCareProvBean getCareprovId(String orgId,String careprovCode)throws Exception;
	
	public ResultDTO queryStudyStaLocList(AiscLoc model,ResultDTO resultDTO) throws Exception;
	
	public void deleLocStudySta(String locId,String orgId) throws Exception;
     
    public void saveLocStudySta(String locId,String orgId,String status,String staName) throws Exception;
    
    public AiscLocStudyStaBean getAiscLocStatus(long id) throws Exception;
    
    public boolean deleteAisLocStudySta(long id) throws Exception;
    
    public List<AiscLoc> getNewLocListStatus(String locId,String orgId) throws Exception;
    
    public List<AiscLoc> getNotJoinLocStatus(String locId,String orgId) throws Exception;
}
