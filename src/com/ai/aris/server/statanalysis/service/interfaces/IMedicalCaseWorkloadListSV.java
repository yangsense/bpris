package com.ai.aris.server.statanalysis.service.interfaces;
  
import java.util.List;
import java.util.Map;

import com.ai.aris.server.statanalysis.model.QryMedicalCaseWorkloadModel;
import com.ai.common.domain.ResultDTO;

public interface IMedicalCaseWorkloadListSV {

	public ResultDTO queryPageList(QryMedicalCaseWorkloadModel model, ResultDTO resultDTO) throws Exception;
	public  Map<String, String> getTotalInfo(QryMedicalCaseWorkloadModel model) throws Exception;
	public List<Map<String, Object>> getEexportMedicalCase(QryMedicalCaseWorkloadModel model) throws Exception;
}
