package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscHisDatasourceBean;
import com.ai.aris.server.basecode.bean.AiscHisDatasourceEngine;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvEngine;
import com.ai.aris.server.basecode.bean.QueryDataBaseBean;
import com.ai.aris.server.basecode.bean.QueryDataBaseEngine;
import com.ai.aris.server.basecode.model.AiscHisDatasourceModel;
import com.ai.aris.server.basecode.model.QueryDataBaseModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscHisDatasourceSV;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.sysmanage.bean.SysOrgEngine;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;
 

public class AiscHisDatasourceSVImpl implements IAiscHisDatasourceSV {


	@Override
	public AiscHisDatasourceBean getHisDatasource(String orgId,String type)
			throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1"); 
        if(isNotBlank(orgId)) {
            condition.append(" and org_id = '"+orgId+"' ");
        }
        if(isNotBlank(type)) {
            condition.append(" and patient_typecode = '"+type+"' ");
        }else{
        	condition.append(" and (patient_typecode='-1' or patient_typecode<>'HP') ");
        }
        condition.append(" and status='0' ");
        AiscHisDatasourceBean[] beans = AiscHisDatasourceEngine.getBeans(condition.toString(), null);    
	    if(beans.length > 0){ 
	    	return beans[0]; 
	    }else{
	    	return null;
	    }  
	}
	
	public ResultDTO queryPageList(AiscHisDatasourceModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		condition.append(" status='0'");
		if (model != null) {
			if (StringUtils.isNotBlank(model.getOrgId())) {
				condition.append(" and Org_id = :Org_id");
				param.put("Org_id", model.getOrgId());
			}
			if (!"-1".equals(model.getPatientTypecode())&&StringUtils.isNotBlank(model.getPatientTypecode())) {
				condition.append(" and patient_typecode = :patient_typecode");
				param.put("patient_typecode",model.getPatientTypecode());
			}
		}
		condition.append(" order by SOURCE_ID");
		int total = AiscHisDatasourceEngine.getBeansCount(condition.toString(), param);
		AiscHisDatasourceBean[] beans = null;
		if (total > 0) {
			beans = AiscHisDatasourceEngine.getBeans(null, condition.toString(), param,
					resultDTO.getStart(), resultDTO.getEnd(),
					false);
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		dicts.put("patientTypecode", new DictTranslator("patientTypecode","PATIENT_TYPE","patientTypecode"));
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscHisDatasourceModel.class,dicts));
        resultDTO.setRecords(total);
		return resultDTO;
	}
	public ResultDTO queryPageList2(QueryDataBaseModel model, ResultDTO resultDTO) throws Exception{
		StringBuffer condition = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		condition.append(" status='0'");
		if (model != null) {
			if (!"-1".equals(model.getPatientTypecode())&&StringUtils.isNotBlank(model.getPatientTypecode())) {
				condition.append(" and patient_typecode = :patient_typecode");
				param.put("patient_typecode",model.getPatientTypecode());
			}
			if (!"-1".equals(model.getCityCode())&& StringUtils.isNotBlank(model.getCityCode())) {
				condition.append(" and city_code = :city_code");
				param.put("city_code",model.getCityCode());
			}
			if (!"-1".equals(model.getCountyCode())&& StringUtils.isNotBlank(model.getCountyCode())) {
				condition.append(" and county_code = :county_code");
				param.put("county_code",model.getCountyCode());
			}
			if (!"-1".equals(model.getOrgId())&& StringUtils.isNotBlank(model.getOrgId())) {
				condition.append(" and Org_id = :Org_id");
				param.put("Org_id", model.getOrgId());
			}
		}
		condition.append(" order by SOURCE_ID");
		int total = QueryDataBaseEngine.getBeansCount(condition.toString(), param);
		QueryDataBaseBean[] beans = null;
		if (total > 0) {
			beans = QueryDataBaseEngine.getBeans(null, condition.toString(), param,
					resultDTO.getStart(), resultDTO.getEnd(),
					false);
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
		dicts.put("patientTypecode", new DictTranslator("patientTypecode","PATIENT_TYPE","patientTypecode"));
        resultDTO.setRows(BeanUtils.beanToList(beans,QueryDataBaseModel.class,dicts));
        resultDTO.setRecords(total);
		return resultDTO;
	}
	public String queryCareprovId(String operatorId){
		StringBuffer condition = new StringBuffer(" 1=1");
		String careProvId = "";
		if(StringUtils.isNotBlank(operatorId)){
			condition.append(" AND OPERATOR_ID='"+operatorId+"'");
			AiscUser2CareProvBean [] aiscUser2CareProvBean = null;
			try {
				aiscUser2CareProvBean = AiscUser2CareProvEngine.getBeans(condition.toString(), null);
				if(aiscUser2CareProvBean!=null && aiscUser2CareProvBean.length>0){
					careProvId =  String.valueOf(aiscUser2CareProvBean[0].getCareprovId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return careProvId;
	}
	public SysOrgBean queryOrgMsg(String orgId){
		StringBuffer condition = new StringBuffer(" 1=1");
		if(StringUtils.isNotBlank(orgId)){
			condition.append(" AND ORG_ID='"+orgId+"'");
			SysOrgBean [] sysorgs = null;
			try {
				sysorgs = SysOrgEngine.getBeans(condition.toString(), null);
				if(sysorgs!=null && sysorgs.length>0){
					return sysorgs[0];
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	
	}
	public String queryOrgId(String careprovId){

		StringBuffer condition = new StringBuffer(" 1=1");
		String orgId = "";
		if(StringUtils.isNotBlank(careprovId)){
			condition.append(" AND CAREPROV_ID='"+careprovId+"'");
			AiscCareProvBean [] aiscCareProvBeans = null;
			try {
				aiscCareProvBeans = AiscCareProvEngine.getBeans(condition.toString(), null);
				if(aiscCareProvBeans!=null && aiscCareProvBeans.length>0){
					orgId =  String.valueOf(aiscCareProvBeans[0].getOrgId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orgId;
	
	}
	public AiscHisDatasourceBean getBean(long id) throws Exception{
		return AiscHisDatasourceEngine.getBean(id);
	}
    
    public void save(AiscHisDatasourceBean bean) throws Exception{
    	if (bean.getSourceId() == 0) {
			long id = ServiceUtil.getSequence("SEQ_AISC_HIS_DATASOURCE");
			bean.setSourceId(id);
			bean.setStatus("0");
			AiscHisDatasourceEngine.save(bean);
		} else {
			AiscHisDatasourceBean oldBean = new AiscHisDatasourceBean();
			oldBean.setSourceId(bean.getSourceId());
			oldBean.setStsToOld();
			if ("delete".equals(bean.getOrgId())) {
				oldBean.delete();
			}
			DataContainerFactory.copyNoClearData(bean, oldBean);
			AiscHisDatasourceEngine.save(oldBean);
		}
    }
}

