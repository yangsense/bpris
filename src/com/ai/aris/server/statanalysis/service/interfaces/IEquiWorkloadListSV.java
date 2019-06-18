package com.ai.aris.server.statanalysis.service.interfaces;
 
import java.util.List;
import java.util.Map;

import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.model.QryEquiWorkloadModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.common.domain.ResultDTO;

public interface IEquiWorkloadListSV {

	public ResultDTO queryPageList(QryEquiWorkloadModel model, ResultDTO resultDTO) throws Exception;
	
	public QryEquiWorkChartsBean[] getEquiWorkCharts(QryEquiWorkloadModel model)throws Exception;
	
	/**
	 * 获取合计信息
	 */
	public  Map<String, String> getTotalInfo(QryEquiWorkloadModel model) throws Exception;
	/**
	 * 获取检查明细
	 */
	public ResultDTO queryStudyInfoList(QryEquiWorkloadModel model, ResultDTO resultDTO) throws Exception;
	
	public ResultDTO queryWorkListCountList(QryStudyInfoListModel model, ResultDTO resultDTO) throws Exception;
	
	public List<Map<String, Object>> getExportEquiWork(QryEquiWorkloadModel model) throws Exception;
	
	public List<Map<String, Object>> getEexportWorklistCount(QryStudyInfoListModel model) throws Exception;
}
