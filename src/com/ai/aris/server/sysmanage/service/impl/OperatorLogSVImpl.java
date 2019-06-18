package com.ai.aris.server.sysmanage.service.impl;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.aris.server.sysmanage.bean.SysOperatorLogBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorLogEngine;
import com.ai.aris.server.sysmanage.service.interfaces.IOperatorLogSV;

public class OperatorLogSVImpl implements IOperatorLogSV {
	/**
	 * 保存操作员日志
	 * @param operatorCode 操作员工号
	 * @param operatorType 操作类型
	 * @param operatorDesc 操作描述
	 * @throws Exception
	 */
	public void saveOperatorLog(String operatorCode ,String operatorType ,String operatorDesc) throws Exception{
		//创建一个日志bean
		SysOperatorLogBean logBean = new SysOperatorLogBean();
		
		//设置传递的参数
		logBean.setOperatorCode(operatorCode);
		logBean.setOperatorType(operatorType);
		logBean.setOperatorDesc(operatorDesc);
		
		// TODO 设置ID和时间 ，先注释
		logBean.setLogId(SysOperatorLogEngine.getNewId().longValue());
		logBean.setOperatorDate(SysdateManager.getSysTimestamp());
		
		//保存 TODO，先注释
		SysOperatorLogEngine.save(logBean);
		
	}
	
	

}
