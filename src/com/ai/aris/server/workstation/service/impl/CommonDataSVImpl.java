package com.ai.aris.server.workstation.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscCareProvEngine;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscItemMastEngine;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscLocEngine;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvEngine;
import com.ai.aris.server.basecode.bean.QryBodyPartBean;
import com.ai.aris.server.basecode.bean.QryBodyPartEngine;
import com.ai.aris.server.basecode.bean.QryItemMastBean;
import com.ai.aris.server.basecode.bean.QryItemMastEngine;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.aris.server.workstation.bean.AiscPeriodEngine;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
 

public class CommonDataSVImpl implements ICommonDataSV{
 

	//科室下拉列表查询
	public AiscLocBean[] getLocInfo(String orgId, String locType) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(locType) && !"undefined".equals(locType) && isNotBlank(orgId) && !"undefined".equals(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
            condition.append(" AND LOC_TYPE = '" + locType + "'");
        }
	    condition.append(" ORDER BY LOC_ID ASC ");
		AiscLocBean[] locBeans = AiscLocEngine.getBeans(condition.toString(), null); 
		if(locBeans!=null&&locBeans.length>0){
			return locBeans;
		}
        return null;
	}

	//医生
	public AiscCareProvBean[] getCareProv(String orgId, String locId,String CareProv_TypeId) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"undefined".equals(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
        } 
	    if (isNotBlank(locId) && !"undefined".equals(locId)&&!"-1".equals(locId)) {
            condition.append(" AND LOC_ID = " + locId );
        }
	    if (isNotBlank(CareProv_TypeId)) {
	    	condition.append(" AND CareProv_TypeId = '" + CareProv_TypeId +"'"); 
        }
	    AiscCareProvBean[] careProvBeans = AiscCareProvEngine.getBeans(condition.toString(), null); 
		
        return careProvBeans; 
	}

	//医生
	public AiscCareProvBean[] getCareProv(String orgId, String locId,String CareProv_TypeId,String keyword) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"undefined".equals(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
        } 
	    if (isNotBlank(locId) && !"undefined".equals(locId)) {
            condition.append(" AND LOC_ID = " + locId );
        }
	    if (isNotBlank(CareProv_TypeId)) {
	    	condition.append(" AND CareProv_TypeId = '" + CareProv_TypeId +"'"); 
        }
	    if (isNotBlank(keyword)) {
            condition.append(" AND (CareProv_ID LIKE '%" + keyword + "%' OR CareProv_DESC LIKE '%" + keyword + "%') ");
        }
	    
	    AiscCareProvBean[] careProvBeans = AiscCareProvEngine.getBeans(condition.toString(), null); 
		
        return careProvBeans; 
	}
	
	//检查设备
	public AiscEquipmentBean[] getEquipment(String orgId,String locId) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
        }
	    if (isNotBlank(locId) && !"-1".equals(locId)) {
            condition.append(" AND LOC_ID = " + locId );
        }
	    condition.append(" AND MODALITY_ENABLED = 1 " );
	    AiscEquipmentBean[] equipmentBeans = AiscEquipmentEngine.getBeans(condition.toString(), null); 
		
        return equipmentBeans; 
	}

	//检查项目
	public QryItemMastBean[] getStudyItem(String orgId,String locId,String keyword,String exists,String equipmentId) throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
        }
	    if (isNotBlank(locId) && !"-1".equals(locId)) {
            condition.append(" AND LOC_ID = " + locId );
        }
	    if (isNotBlank(keyword)) {
	    	String cond = java.net.URLDecoder.decode(keyword, "UTF-8");
	    	
            condition.append(" AND (ITEMMAST_ID LIKE '%" + cond + "%' OR ITEMMAST_DESC LIKE '%" + cond + "%'  OR ITEMMAST_ENDESC LIKE '%" + cond + "%' OR PY_INITIAL LIKE '%" + cond.toUpperCase() + "%' ) ");
        }
	    if (isNotBlank(exists)&&!"undefined".equals(exists)) {
	        condition.append(" AND itemmast_id not in (" + exists +")");
	    }
	    if (isNotBlank(equipmentId)&&!"-1".equals(equipmentId)) {
	    	AiscEquipmentBean bean = AiscEquipmentEngine.getBean(Long.parseLong(equipmentId));
	        condition.append(" AND OrdSubCategory_ID=" + bean.getOrdsubcategoryId());
	    }
	    QryItemMastBean[] equipmentBeans = QryItemMastEngine.getBeans(condition.toString(), null); 
		
        return equipmentBeans;
	}

	//检查部位查询
	public QryBodyPartBean[] getBodyPart(String orgId, String itemMastId,String partId)
			throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(orgId) && !"undefined".equals(orgId)) {
	    	condition.append(" AND ORG_ID = " + orgId );
        }
	    if (isNotBlank(itemMastId) && !"undefined".equals(itemMastId)) {
            condition.append(" AND ITEMMAST_ID = " + itemMastId );
        }
	    if (isNotBlank(partId) && !"undefined".equals(partId)) {
            condition.append(" AND bodypart_code not in (" + partId+")");
        }
	    condition.append("  order by bodypart_order ");
	    QryBodyPartBean[] bodyPartBeans = QryBodyPartEngine.getBeans(condition.toString(), null); 
        return bodyPartBeans;
	}

	@Override
	public AiscUser2CareProvBean getCareProvByOperatorId(String operatorId)
			throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    if (isNotBlank(operatorId) ) {
	    	condition.append(" AND OPERATOR_ID = " + operatorId ); 
        }
	    AiscUser2CareProvBean[] locBeans = AiscUser2CareProvEngine.getBeans(condition.toString(), null); 
		
	    if(locBeans.length>0){
	    	return locBeans[0]; 
	    }else{
	    	return null;
	    }
        
	}
	
	//时段下拉列表查询
	public AiscPeriodBean[] getPeriodInfo() throws Exception {
		StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    condition.append(" ORDER BY ID ASC ");
	    AiscPeriodBean[] periodBeans = AiscPeriodEngine.getBeans(condition.toString(), null); 
		
        return periodBeans;
	}




}
