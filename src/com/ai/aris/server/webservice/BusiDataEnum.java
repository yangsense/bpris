package com.ai.aris.server.webservice;

import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.webservice.bean.*;

/**
 * Created by lenovo on 2016/12/19.
 */
public enum BusiDataEnum {
    PATIENTINFO("病人信息同步",AisPatientInfoData.class, "PATIENTINFO"),
    STUDYINFO("检查登记信息同步",AisStudyInfoData.class, "STUDYINFO"),
    STUDYITEMINFO("检查项目同步",AisStudyItemInfoData.class, "STUDYITEMINFO"),
    STUDYREPORT("报告信息同步",AisStudyReportData.class, "STUDYREPORT");
    
        // 成员变量
        private String dataName;
//        private  Class beanClz;
        private Class dataClz;
        private String interfaceName;
        // 构造方法
        private BusiDataEnum(String dataName,Class dataClz,String interfaceName) {
        this.dataName = dataName;
        this.dataClz = dataClz;
        this.interfaceName = interfaceName;
      }
    
    public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public Class getDataClz() {
        return dataClz;
    }

    public void setDataClz(Class dataClz) {
        this.dataClz = dataClz;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }
}
