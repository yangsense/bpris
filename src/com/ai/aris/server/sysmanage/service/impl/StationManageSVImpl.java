package com.ai.aris.server.sysmanage.service.impl;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgEngine;
import com.ai.aris.server.sysmanage.bean.SysStationBean;
import com.ai.aris.server.sysmanage.bean.SysStationEngine;
import com.ai.aris.server.sysmanage.service.interfaces.IStationManageSV;

public class StationManageSVImpl implements IStationManageSV {


	
	public void addStationInfo(SysStationBean sysStationBean,String operatorCode,SysOperator2OrgBean sysOp2OrgBean) throws Exception {
		 
		//保存岗位
		SysStationEngine.save(sysStationBean);
		
		//保存岗位权限
		SysOperator2OrgBean sysOperator2OrgBean = new SysOperator2OrgBean();
		sysOperator2OrgBean.setOrgId(sysOp2OrgBean.getOrgId());
		sysOperator2OrgBean.setOperatorCode(operatorCode);
		sysOperator2OrgBean.setOrgType("0");
		sysOperator2OrgBean.setRemarks("所属机构分配");
		sysOperator2OrgBean.setStationCode(sysStationBean.getStationCode());		
		SysOperator2OrgEngine.save(sysOperator2OrgBean);
		
	}



	
	public void editStationInfo(SysStationBean sysStationBean) throws Exception {
		SysStationBean oldBean = new SysStationBean();
		oldBean.setStationCode(sysStationBean.getStationCode());
		oldBean.setStsToOld();
		DataContainerFactory.copyNoClearData(sysStationBean,
				oldBean); 
		SysStationEngine.save(oldBean);		
	}



}
