package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.common.ServiceManager;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscItemMastEngine;
import com.ai.aris.server.basecode.bean.QryCareProvBean;
import com.ai.aris.server.basecode.bean.QryCareProvEngine;
import com.ai.aris.server.basecode.model.AiscCareProv;
import com.ai.aris.server.basecode.service.interfaces.IAiscCareProvSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.DictItemEngine;
import com.ai.aris.server.common.util.DBUtil;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListEngine;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscCareProvSVImpl implements IAiscCareProvSV {


    @Override
    public ResultDTO queryPageList(AiscCareProv model,ResultDTO resultDTO) throws Exception {
    	Statement stmt = null;
    	ResultSet rs = null;
    	ResultSet rsCount =null;
    	try {
	    	stmt = ServiceManager.getSession().getConnection().createStatement();   
	    	String sql = " select * from (select B.*,rownum as row_index from ( select a.careprov_id,a.careprov_code,a.careprov_name," +
	    			"(select item_name from sys_dictitem where dict_name='CAREPROV_TYPE' and item_no=a.careprov_typeid) CAREPROV_TYPE," +
	    			"b.loc_desc,c.org_name from Aisc_Careprov a,aisc_loc b,sys_org c where a.loc_id=b.loc_id and c.org_id=a.org_id ";
	    	String sqlParam ="";
	    	if(isNotBlank(String.valueOf(model.getCareprovCode()))) {
	            sqlParam+=" and a.CareProv_Code like '%"+model.getCareprovCode()+"%' ";
	        }
	        if (isNotBlank(String.valueOf(model.getCareprovName()))) {
	        	sqlParam+=" and a.CareProv_Name like '%"+model.getCareprovName()+"%' ";
	        }
	        if(model.getCareprovTypeid()!=-1&&isNotBlank(String.valueOf(model.getCareprovTypeid()))){
	        	sqlParam+=" and a.CareProv_TypeID = '"+model.getCareprovTypeid()+"' ";
	        }
	        if(model.getLocId()!=-1&&isNotBlank(String.valueOf(model.getLocId()))){
	        	sqlParam+=" and a.loc_id = '"+model.getLocId()+"' ";
	        }
	        if(!model.getOrgId().equals("-1")&&isNotBlank(String.valueOf(model.getOrgId()))){
	        	sqlParam+=" and a.org_id = '"+model.getOrgId()+"' ";
	        }
	        int number = resultDTO.getLimit()*resultDTO.getPage();
	        int rowindex = 1+(number-resultDTO.getLimit());
	        String sqlOrder =" ORDER BY a.CareProv_ID,a.CareProv_Name asc ) B where rownum<="+number+") where  row_index >="+rowindex+" and row_index <= "+number+" ";
	    	rs = stmt.executeQuery(sql+sqlParam+sqlOrder);
	        List<AiscCareProv> list = new ArrayList();
	        AiscCareProv aiscCare = null;
			while(rs.next()){
				aiscCare = new AiscCareProv();
				aiscCare.setCareprovId(rs.getLong("CAREPROV_ID"));
				aiscCare.setOrgName(rs.getString("ORG_NAME")==null?"":rs.getString("ORG_NAME"));
				aiscCare.setLocName(rs.getString("LOC_DESC")==null?"":rs.getString("LOC_DESC"));
				aiscCare.setCareprovTypeDesc(rs.getString("CAREPROV_TYPE")==null?"":rs.getString("CAREPROV_TYPE"));
				aiscCare.setCareprovName(rs.getString("CAREPROV_NAME")==null?"":rs.getString("CAREPROV_NAME"));
				aiscCare.setCareprovCode(rs.getString("CAREPROV_CODE")==null?"":rs.getString("CAREPROV_CODE"));
				list.add(aiscCare);
			}		
			String sqlCount = "select count(*) COUNT from Aisc_Careprov a,aisc_loc b,sys_org c where  a.loc_id=b.loc_id and c.org_id=a.org_id"+sqlParam;
	        int total = 0;
	        rsCount = stmt.executeQuery(sqlCount);
	        if(rsCount.next()){
	        	total = rsCount.getInt("COUNT");
	        }
	        AiscCareProv [] loclist = new AiscCareProv[list.size()];
	        if(list!=null&&list.size()>0){
	        	for(int i=0;i<list.size();i++){
	        		loclist[i] = list.get(i);
	        	}
	        }
	        Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
	        resultDTO.setRows(BeanUtils.beanToList(loclist,AiscCareProv.class,dicts));
//	        dicts.put("locType", new DictTranslator("locType","LOC_TYPE","locTypeDesc"));
	        resultDTO.setRecords(total);
    	} catch (Exception e) {
			// TODO: handle exception
		}finally{
			DBUtil.release(rs, stmt, null);
			DBUtil.release(rsCount, stmt, null);
			ServiceManager.getSession().getConnection().close();
		}
        return resultDTO;
    }



    @Override
    public List<Map> queryDict(String dictName) throws Exception {
        Map map = new HashMap();
        map.put("dictName", dictName);
        DictItemBean[] dictItemBeans = DictItemEngine.getBeans("DICT_NAME=:dictName", map);
        List<Map> list = new ArrayList();
        for(DictItemBean dictItemBean:dictItemBeans){
            list.add(ServiceUtil.transerBeanToMap(dictItemBean));
        }
        return list;
    }
    
    @Override
    public AiscCareProvBean getAiscCareProv(long id) throws Exception {
        return AiscCareProvEngine.getBean(id);
    }
    @Override
    public boolean deleteAiscCareProv(long id) throws Exception{
    	 AiscCareProvBean bean = getAiscCareProv(id);
    	 bean.setStsToOld();
    	 bean.delete();
    	 try {
    		 AiscCareProvEngine.save(bean);
    		 return true;
         }catch (Exception e){
             return false;
         }
    }
    @Override
    public void saveAiscCareProv(AiscCareProvBean bean) throws Exception{
    	System.out.println(bean.getLocId());
    	if (bean.getCareprovId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQ_AISC_CareProv");
	    	bean.setCareprovId(id);
	    	AiscCareProvEngine.save(bean);
    	}else {
    		AiscCareProvBean oldBean = AiscCareProvEngine.getBean(bean.getCareprovId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscCareProvEngine.save(oldBean);
        }
    }

	@Override
	public boolean checkCareprovCode(long careprovId,String careprovCode,String orgId) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(careprovId!=0){
        	condition.append(" and careprov_id <> "+careprovId);
        }
        if(isNotBlank(careprovCode)) {
            condition.append(" and careprov_code = '"+careprovCode+"' ");
        }
        if(isNotBlank(orgId)){
        	condition.append(" and org_Id = "+orgId);
        }
        return AiscCareProvEngine.getBeansCount(condition.toString(), null)==0;
	}
	
	public QryCareProvBean getCareprovByOperatorId(String orgId,String locId,String operator_id) throws Exception {
		StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(orgId)){
        	condition.append(" and org_Id = "+orgId);
        }
        if(isNotBlank(locId)&& !"undefined".equals(locId)) {
            condition.append(" and loc_Id = "+locId);
        }
        if(isNotBlank(operator_id)) {
            condition.append(" and operator_id = "+operator_id);
        }
        QryCareProvBean[] beans = QryCareProvEngine.getBeans(condition.toString(), null);
        if(beans.length>0){
        	return beans[0];
        }else{
        	return new QryCareProvBean();
        }
	}
	
}

