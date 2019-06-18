package com.ai.aris.server.workstation.service.interfaces;
 

import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.basecode.bean.QryBodyPartBean;
import com.ai.aris.server.basecode.bean.QryItemMastBean;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;

public interface ICommonDataSV {

	 
 	//根据科室类型取科室信息
	public AiscLocBean[] getLocInfo(String orgId, String locType) throws Exception;
	
	//医生
	public AiscCareProvBean[] getCareProv(String orgId, String locId,String CareProv_TypeId) throws Exception;
	
	//医生
	public AiscCareProvBean[] getCareProv(String orgId, String locId,String CareProv_TypeId,String keyword) throws Exception;
	
	//查检查设备信息
	public AiscEquipmentBean[] getEquipment(String orgId,String locId) throws Exception; 
	 
	//检查项目查询
	public QryItemMastBean[] getStudyItem(String orgId,String locId,String keyword,String exists,String equipmentId) throws Exception; 
	
	//检查部位查询
	public QryBodyPartBean[] getBodyPart(String orgId,String itemMastId,String partId) throws Exception; 
	
	//根据登录用户ID取医护人员编码
	public AiscUser2CareProvBean getCareProvByOperatorId(String operatorId)throws Exception;
	
	//时段下拉列表查询
	public AiscPeriodBean[] getPeriodInfo() throws Exception;


}
