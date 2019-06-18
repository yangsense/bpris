package com.ai.aris.server.basecode.service.impl;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.*;
import com.ai.aris.server.basecode.model.QryOrdCat2LocModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdCat2LocSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

import static org.apache.commons.lang.StringUtils.isNotBlank;

public class AiscOrdCat2LocSVImpl implements IAiscOrdCat2LocSV {

	@Override
	public ResultDTO queryPageList(QryOrdCat2LocModel model, ResultDTO resultDTO)
			throws Exception {
		 StringBuffer condition = new StringBuffer();
	        condition.append(" 1=1");
	        if(-1!=model.getLocId()) {
	            condition.append(" and loc_Id like '%"+model.getLocId()+"%' ");
	        }
	        if(isNotBlank(model.getOrdcategoryDesc())) {
	            condition.append(" and Ordcategory_Desc like '%"+model.getOrdcategoryDesc()+"%' ");
	        }
	        if(isNotBlank(model.getOrgId())&&!"-1".equals(model.getOrgId())) {
	            condition.append(" and org_Id like '%"+model.getOrgId()+"%' ");
	        }

	        int total = QryOrdCat2LocEngine.getBeansCount(condition.toString(), null);
	        QryOrdCat2LocBean[] beans = null;

	        if (total > 0) {
	            condition.append(" ORDER BY ordcat2loc_id desc");
	            beans = QryOrdCat2LocEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false) ;
	        }
	        resultDTO.setRows(BeanUtils.beanToList(beans,QryOrdCat2LocModel.class));
	        resultDTO.setRecords(total);
	        return resultDTO;
	}

	@Override
	public AiscOrdCat2LocBean getQryOrdCat2LocBean(long id) throws Exception {
		return AiscOrdCat2LocEngine.getBean(id);
	}

	@Override
	public boolean deleteOrdCat2Loc(long id) throws Exception {
		AiscOrdCat2LocBean bean = AiscOrdCat2LocEngine.getBean(id);
	        bean.setStsToOld();
	    	 bean.delete();
	    	 try {
	    		 AiscOrdCat2LocEngine.save(bean);
	    		 return true;
	         }catch (Exception e){
	             return false;
	         }
	}

	@Override
	public void saveOrdCat2Loc(AiscOrdCat2LocBean bean) throws Exception {
		// SEQORDCAT2LOC  序列
		if (bean.getOrdcat2locId() == 0) {
	    	long id = ServiceUtil.getSequence("SEQORDCAT2LOC");
	    	bean.setOrdcat2locId(id);
	    	AiscOrdCat2LocEngine.save(bean);
    	}else {
    		AiscOrdCat2LocBean oldBean = AiscOrdCat2LocEngine.getBean(bean.getOrdcat2locId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscOrdCat2LocEngine.save(oldBean);
        }
	}

	public Boolean checkIsExist(AiscOrdCat2LocBean bean) throws Exception{
		StringBuffer condition = new StringBuffer();
		condition.append(" 1=1");
		if(bean.getOrdcat2locId()!=0&&isNotBlank(String.valueOf(bean.getOrdcat2locId()))) {
			condition.append(" and ordcat2loc_id <> "+bean.getOrdcat2locId());
		}
		if(-1!=bean.getLocId()) {
			condition.append(" and loc_Id = "+bean.getLocId());
		}
		if(isNotBlank(String.valueOf(bean.getOrdcatId()))&&!"0".equals(bean.getOrdcatId())) {
			condition.append(" and ORDCAT_ID = "+bean.getOrdcatId());
		}
		if(isNotBlank(bean.getOrgId())&&!"-1".equals(bean.getOrgId())) {
			condition.append(" and org_Id ="+bean.getOrgId());
		}

		int count = AiscOrdCat2LocEngine.getBeansCount(condition.toString(), null);
		if (count>0){
			return  true ;
		}else return  false ;
	}


	//获取检查科室Bean
	@Override
	public AiscLocBean[] getLocItem(String keyword) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(keyword)) {
            condition.append(" AND (loc_ID LIKE '%" + keyword + "%' OR loc_DESC LIKE '%" + keyword + "%') ");
        }
	    AiscLocBean[] beans = AiscLocEngine.getBeans(condition.toString(), null); 
        return beans; 
	}
	
	//获取检查大类Bean
	@Override
	public AiscOrdCategoryBean[] getOrdCategoryItem(String keyword) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(keyword)) {
            condition.append(" AND (loc_ID LIKE '%" + keyword + "%' OR loc_DESC LIKE '%" + keyword + "%') ");
        }
	    AiscOrdCategoryBean[] beans = AiscOrdCategoryEngine.getBeans(condition.toString(), null); 
        return beans; 
	}
}

