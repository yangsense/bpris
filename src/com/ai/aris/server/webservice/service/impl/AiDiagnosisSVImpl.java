package com.ai.aris.server.webservice.service.impl;

import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisBean;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisDetailBean;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisDetailEngine;
import com.ai.aris.server.interfacereal.bean.AisAiDiagnosisEngine;
import com.ai.aris.server.interfacereal.model.AisAiDiagnosisModel;
import com.ai.aris.server.webservice.service.interfaces.IAiDiagnosisSV;
import com.ai.common.util.ServiceUtil;

/**
 * Created by lenovo on 2017/9/27.
 */
public class AiDiagnosisSVImpl implements IAiDiagnosisSV {


    @Override
    public AisAiDiagnosisBean queryAiDiagnosis(AisAiDiagnosisModel model) throws Exception {
        AisAiDiagnosisBean[] been = null ;
        StringBuffer condition = new StringBuffer() ;
        condition.append( " 1=1 ") ;
        if (model!=null){
            if (StringUtils.isNotEmpty(model.getOrgId())){
                condition.append(" AND  ORG_ID= '"+ model.getOrgId()+"' ") ;
            }
            if (StringUtils.isNotEmpty(model.getStudyId())){
                condition.append("  AND STUDY_ID= '"+ model.getStudyId()+"' ") ;
            }
        }
         been = AisAiDiagnosisEngine.getBeans(condition.toString(),null) ;
        if (been!=null&&been.length>0){
            return  been[0] ;
        }

        return null;
    }

    @Override
    public Boolean saveAiDiagnosis(AisAiDiagnosisBean diagnosisBean) throws Exception {
        long new_id = ServiceUtil.getSequence("SEQ_AIS_AI_DIAGNOSIS");
        if (diagnosisBean!=null){
            diagnosisBean.setGlobalStudyId(new_id);
            AisAiDiagnosisEngine.save(diagnosisBean);
            return  true ;
        }

        return null;
    }

    @Override
    public void updateAiDiagnosis(AisAiDiagnosisBean diagnosisBean) throws Exception {
            AisAiDiagnosisBean oldBean = new  AisAiDiagnosisBean() ;
            oldBean.setGlobalStudyId(diagnosisBean.getGlobalStudyId());
            oldBean.setStsToOld();
            DataContainerFactory.copyNoClearData(diagnosisBean,oldBean);
            AisAiDiagnosisEngine.save(oldBean);
//            for(AisAiDiagnosisDetailBean bean:aisAiDiagnosisDetailBeans){
//            	AisAiDiagnosisDetailEngine.save(bean);
//            }
    }
    
    public void updateAidiagnoDetail(AisAiDiagnosisDetailBean detailBean) throws Exception {
    	detailBean.setStudyDetailId(ServiceUtil
				.getSequence("SQE_AIS_AI_DIAGNOSIS_DETAIL"));
    	AisAiDiagnosisDetailEngine.save(detailBean);
    }
    
    public void deleteAiDiagnosisDetail(long globalStudyId) throws Exception{
    	Statement stmt = null;
    	try {
            stmt = ServiceManager.getSession().getConnection().createStatement();
            String sql = " delete from  AIS_AI_DIAGNOSIS_DETAIL where GLOBAL_STUDY_ID='"+globalStudyId+"'";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.release(null, stmt, null);
            ServiceManager.getSession().getConnection().close();
        }
    }
    
    public void updateFilmStatus(String accNumber) throws Exception{
		Statement stmt = null;
		try{
    		stmt = ServiceManager.getSession().getConnection().createStatement();
    		String sql = "update ais_studyinfo set study_filmprinted='1' where study_accnumber='"+accNumber+"'";
    		stmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.release(null, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
	}
}
