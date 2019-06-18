package com.ai.aris.server.basecode.service.interfaces;


import com.ai.aris.server.basecode.bean.AiscHisDatasourceBean;
import com.ai.aris.server.basecode.model.AiscHisDatasourceModel;
import com.ai.aris.server.basecode.model.QueryDataBaseModel;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.common.domain.ResultDTO;

public interface IAiscHisDatasourceSV {
	 	
	public AiscHisDatasourceBean getHisDatasource(String orgId,String type)throws Exception;
	
	public ResultDTO queryPageList(AiscHisDatasourceModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryPageList2(QueryDataBaseModel model, ResultDTO resultDTO) throws Exception;
	
	public String queryCareprovId(String operatorId);
	
	public SysOrgBean queryOrgMsg(String orgId);
	
	public String queryOrgId(String careprovId);
	
	public AiscHisDatasourceBean getBean(long id) throws Exception;
    
    public void save(AiscHisDatasourceBean bean) throws Exception;
}
