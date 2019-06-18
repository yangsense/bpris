package com.ai.aris.server.workstation.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.model.QryPacsInfoModel;
import com.ai.aris.server.workstation.model.QryRisInfoModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QryStudyPatientModel;
import com.ai.common.domain.ResultDTO;

public interface IWorkListSV {

	public ResultDTO queryPageList(QryStudyInfoListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public QryStudyInfoListBean getWorkListDetail(String stydyinfoId) throws Exception;
	
	public List<Map>  getLocInfo(String orgId,String operatorId)throws Exception;
	
	public AiscLocBean[]  getLocInfos(String orgId)throws Exception;
	
	public Map getMessage(String orgId,String locId,String userId) throws Exception;
	
	public void saveReprtStatus(String orgId,String locId,String userId) throws Exception;
	
	public void saveReportStutusVerrify(String orgId,String locId,String userId) throws Exception;
	
	public ResultDTO queryRisInfoList(QryRisInfoModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryPacsInfoList(QryPacsInfoModel model, ResultDTO resultDTO) throws Exception;
	
	public void updateControlInfo(long  patientkey,String accessionnumber,String patientid) throws Exception;
	
	public List<Map> queryRecords(QryStudyInfoListModel model) throws Exception;

	public Map queryPrintRecords(QryStudyInfoListModel model) throws Exception;
	
	public ResultDTO queryPagePrintList(QryStudyInfoListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public List<Map> getAiscLocStatus(String locId,String orgId) throws Exception;
	
	public ResultDTO queryStudyList(QryStudyPatientModel model, ResultDTO resultDTO) throws Exception;
	
	public String getLocType(long locId) throws Exception;
	
	public ResultDTO queryStudyRightDownList(QryStudyInfoListModel model, ResultDTO resultDTO) throws Exception;
	
	public String getLoginloc(String operatorId,String orgId) throws Exception;
}
