package com.ai.aris.server.sysmanage.service.interfaces;

import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysStationBean;

public interface IStationManageSV {
	void addStationInfo(SysStationBean sysStationBean, String operatorCode, SysOperator2OrgBean sysOperator2OrgBean)throws Exception ;

	void editStationInfo(SysStationBean sysStationBean)throws Exception;
}
