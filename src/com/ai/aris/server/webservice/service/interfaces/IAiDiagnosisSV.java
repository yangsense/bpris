package com.ai.aris.server.webservice.service.interfaces;

import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisBean;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisDetailBean;
import com.ai.aris.server.interfacereal.model.AisAiDiagnosisModel;

import java.util.List;

/**
 * Created by lenovo on 2017/9/27.
 */
public interface IAiDiagnosisSV {
    /**
     * 查询报告
     * @param model
     * @return
     * @throws Exception
     */
    public AisAiDiagnosisBean queryAiDiagnosis(AisAiDiagnosisModel model) throws Exception ;

    /**
     * 新增报告
     * @param diagnosisBean
     * @return
     * @throws Exception
     */
    public Boolean saveAiDiagnosis(AisAiDiagnosisBean diagnosisBean) throws Exception ;


    /**
     * 更新报告
     * @param diagnosisBean
     * @param aisAiDiagnosisDetailBeans
     * @return
     * @throws Exception
     */
    public void updateAiDiagnosis(AisAiDiagnosisBean diagnosisBean) throws Exception ;
    
    public void updateAidiagnoDetail(AisAiDiagnosisDetailBean detailBean) throws Exception;

    public void deleteAiDiagnosisDetail(long globalStudyId) throws Exception;
    
    public void updateFilmStatus(String accNumber) throws Exception;
}
