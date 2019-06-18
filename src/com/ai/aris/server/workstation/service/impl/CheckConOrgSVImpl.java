package com.ai.aris.server.workstation.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoEngine;
import com.ai.aris.server.workstation.bean.AisStudyReportBean;
import com.ai.aris.server.workstation.bean.AisStudyReportEngine;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListEngine;
import com.ai.aris.server.workstation.bean.QueryPatientInfoHZBean;
import com.ai.aris.server.workstation.bean.QueryPatientInfoHZEngine;
import com.ai.aris.server.workstation.bean.QueryStudyItemBean;
import com.ai.aris.server.workstation.bean.QueryStudyItemEngine;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QueryCheckMasterModel;
import com.ai.aris.server.workstation.service.interfaces.ICheckConOrgSV;
import com.ai.aris.server.workstation.service.interfaces.ICheckMasterSV;
import com.ai.aris.server.workstation.service.interfaces.IWorkListSV;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
 

public class CheckConOrgSVImpl implements ICheckConOrgSV{
	
	//工作列表查询
    /* (non-Javadoc)
     * @see com.ai.aris.server.workstation.service.interfaces.IWorkListSV#queryPageList(com.ai.aris.server.workstation.model.QryStudyInfoListModel, com.ai.common.domain.ResultDTO)
     */
    public ResultDTO queryPageList(QueryCheckMasterModel model,String orgId, ResultDTO resultDTO) throws Exception {
        StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
         
        if (isNotBlank(orgId)) {
            condition.append(" and study_consultorg = " + orgId);
        }else{
        	return null;
        }
        if (isNotBlank(model.getStudystatusCode())&&!"-1".equals(model.getStudystatusCode())) {//检查状态
            condition.append(" and  studystatus_code= '" +model.getStudystatusCode()+"'");
        }
        if ( isNotBlank(String.valueOf(model.getLocId()))&&model.getLocId() != -1) {
            condition.append(" and study_consultloc = " + model.getLocId() );
        }
        if(isNotBlank(model.getStartTime())){ 
			condition.append(" and Study_DateTime >= to_date('"+model.getStartTime()+ " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
		}
        if(isNotBlank(model.getEndTime())){
			condition.append(" and Study_DateTime <= to_date('"+model.getEndTime()+" 23:59:59' ,'yyyy-mm-dd hh24:mi:ss')");
		} 
        //condition.append(" and studystatus_code in ('ARRIVE','StartStudy') " );
        condition.append(" and study_type=1 " );
        condition.append(" and studystatus_code ='APPConsult' " );
        int total = QueryPatientInfoHZEngine.getBeansCount(condition.toString(), null);
        QueryPatientInfoHZBean[] beans = null;

        if (total > 0) {
            condition.append(" ORDER BY studyinfo_id desc");
            beans = QueryPatientInfoHZEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
        }
        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        //ZYWDO KJ-字典翻译
        dicts.put("studystatusCode", new DictTranslator("studystatusCode","STUDY_STATUS","studystatusCode"));
        dicts.put("patienttypeCode", new DictTranslator("patienttypeCode","PATIENT_TYPE","patienttypeCode"));
        dicts.put("patientSex", new DictTranslator("patientSex","SEX","patientSex")); 
        dicts.put("studylevelId", new DictTranslator("studylevelId","CHECK_PRIORITY","studylevelIdDesc"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QueryCheckMasterModel.class,dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    /**
     * 根据studyinfoId修改会诊退回申请状态
     */
    public void updateRefuseStatus(AisStudyInfoBean bean) throws Exception{
    	AisStudyInfoBean oldBean = AisStudyInfoEngine.getBean(bean.getStudyinfoId());
        DataContainerFactory.copyNoClearData(bean, oldBean);
        AisStudyInfoEngine.save(oldBean);
    }

    /**
     * 根据检查id获取检查信息
     * @param model
     * @return
     * @throws Exception
     */
    public  List queryStudyItem(QueryCheckMasterModel model) throws Exception{
    	 StringBuffer condition = new StringBuffer();
         condition.append(" 1=1");
         if (model.getStudyinfoId() >0) {//检查状态
             condition.append(" and  studyinfo_id= '" +model.getStudyinfoId()+"'");
         }
         int total = QueryStudyItemEngine.getBeansCount(condition.toString(), null);
         QueryStudyItemBean[] beans = null;

         if (total > 0) {
             beans = QueryStudyItemEngine.getBeans(null, condition.toString(), null, 0,1, false) ;
         }
         return BeanUtils.beanToList(beans,QueryCheckMasterModel.class,null);
    }
    
	//科室下拉列表查询
	public List<Map> getLocInfo(String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
            condition.append(" and org_id = " + orgId );
            condition.append(" and loc_type = 'C'" );
        }
		AiscLocBean[] locBeans = AiscLocEngine.getBeans(condition.toString(), null); 
        List<Map> list = new ArrayList();
        for(AiscLocBean locBean:locBeans){
            list.add(ServiceUtil.transerBeanToMap(locBean));
        }
        return list;
	}
	
	//根据医生编码查医姓名
	public String getCareProvInfo(String careProvCode) throws Exception{
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(careProvCode)) {
            condition.append(" and careprov_code = '" + careProvCode + "'");
        }else{
        	return "";
        }	    
	    AiscCareProvBean[] beans = AiscCareProvEngine.getBeans(condition.toString(),null) ;
	    if(beans.length>0){
	    	 return beans[0].getCareprovName();
	    }else{
	    	return "";
	    }
	   
	}
	
	public void updateCheck(QueryCheckMasterModel model) throws Exception{
		AisStudyInfoBean  oldBean =AisStudyInfoEngine.getBean(model.getStudyinfoId());
		if(oldBean!=null){
			oldBean.setStudyDoctorid(model.getStudyDoctorid());
			oldBean.setAidDoctorid(model.getAidDoctorid());
			oldBean.setStudyExposurecount(model.getStudyExposurecount());
			oldBean.setStudyFilmcount(model.getStudyFilmcount());
			oldBean.setStudyExposurecount(model.getStudyExposurecount());
			
			if("1".equals(model.getActionType())){
				oldBean.setStudystatusCode("StartStudy");
				oldBean.setStudyStarttime(new Timestamp(System.currentTimeMillis()));
			}else if("2".equals(model.getActionType())){
				oldBean.setStudystatusCode("STUDYED");
				oldBean.setStudyEndtime(new Timestamp(System.currentTimeMillis()));
			}
			AisStudyInfoEngine.save(oldBean);
			
		}
		
	}

}
