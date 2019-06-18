package com.ai.aris.server.statanalysis.service.interfaces;
 
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.model.QryDoctorWorkloadModel;
import com.ai.common.domain.ResultDTO;

import java.util.List;
import java.util.Map;

public interface IDoctorWorkloadListSV {

	public ResultDTO queryPageList(QryDoctorWorkloadModel model, ResultDTO resultDTO) throws Exception;
	 
	public QryEquiWorkChartsBean[] getDocWorkCharts(QryDoctorWorkloadModel model)throws Exception;
	
	public  Map<String, String> getTotalInfo(QryDoctorWorkloadModel model) throws Exception;
	/**
	 * 获取检查明细
	 */
	public ResultDTO queryStudyInfoList(QryDoctorWorkloadModel model, ResultDTO resultDTO) throws Exception;
	
	public List<Map<String, Object>> getExportDcotorWork(QryDoctorWorkloadModel model) throws Exception;
}
