package com.ai.aris.server.statanalysis.service.interfaces;

import java.util.List;
import java.util.Map;

import com.ai.aris.server.statanalysis.bean.QryHzOrgBean;
import com.ai.aris.server.statanalysis.bean.QrySQLocBean;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckCountyrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckLocrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckRpModel;
import com.ai.aris.server.statanalysis.model.QryHzMultipleListModel;
import com.ai.aris.server.statanalysis.model.QryHzRecordListModel;
import com.ai.common.domain.ResultDTO;

public interface IPatientWorkloadListSV {

	/**
	 * 获取市级级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryCityPageList(AiscPatientCheckCountyrpModel model, ResultDTO resultDTO) throws Exception;

	/**
	 * 获取区县级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryCountyPageList(AiscPatientCheckCountyrpModel model, ResultDTO resultDTO) throws Exception;

	/**
	 * 获取科室级检查统计信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryLocPageList(AiscPatientCheckLocrpModel model, ResultDTO resultDTO) throws Exception;

	/**
	 * 获取患者检查信息
	 * @param model
	 * @param resultDTO
	 * @return
	 * @throws Exception
	 */
	public ResultDTO queryPatientPageList(AiscPatientCheckRpModel model, ResultDTO resultDTO) throws Exception;
	
	public QrySQLocBean[] getSQLoc(String orgId)throws Exception;
	
	public QryHzOrgBean[] getHzOrg(String orgId,String locId,String conorgId)throws Exception;
	
	public ResultDTO queryHzRecordList(QryHzRecordListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public ResultDTO queryHzRecordMutiList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public  Map<String, String> getTotalInfo(QryHzMultipleListModel model) throws Exception;
	
	public ResultDTO getDetailHzRecord(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public ResultDTO queryMedicalCountList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public ResultDTO queryMedicalCountDetailList(QryHzMultipleListModel model, ResultDTO resultDTO,String sidx,String sord) throws Exception;
	
	public ResultDTO queryHzCityPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryHzCountyPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryhZLocPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryhZPatientPageList(QryHzMultipleListModel model, ResultDTO resultDTO) throws Exception;
	
	public List<Map<String, Object>> getCityStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception;
	
	public List<Map<String, Object>> getCountyStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception;
	
	public List<Map<String, Object>> getLocStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception;
	
	public List<Map<String, Object>> getPatientStudyinfoExcel(AiscPatientCheckCountyrpModel model) throws Exception;
	
	public List<Map<String, Object>> getHzCityStudyinfoExcel(QryHzMultipleListModel model) throws Exception;
	
	public List<Map<String, Object>> getHzCountyStudyinfoExcel(QryHzMultipleListModel model) throws Exception;
	
	public List<Map<String, Object>> getHzLocStudyinfoExcel(QryHzMultipleListModel model) throws Exception;
	
	public List<Map<String, Object>> getHzPatientStudyinfoExcel(QryHzMultipleListModel model) throws Exception;
	
	public List<Map<String, Object>> getExportHzInit(QryHzRecordListModel model) throws Exception;
	
	public List<Map<String, Object>> getExportHzMultiple(QryHzMultipleListModel model) throws Exception;
	
	public List<Map<String, Object>> getMeidcalCountExcel(QryHzMultipleListModel model) throws Exception;

	public List<Map<String, Object>> getMeidcalCountDetailExcel(QryHzMultipleListModel model) throws Exception;
}
