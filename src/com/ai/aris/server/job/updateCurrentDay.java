package com.ai.aris.server.job;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.workstation.service.interfaces.IRisOrgSV;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by liym5 on 2018/6/5.
 */
public class updateCurrentDay {
    private Log logger = LogFactory.getLog(updateCurrentDay.class);
    private IRisOrgSV orgSV = (IRisOrgSV) ServiceFactory.getService(IRisOrgSV.class);

    // 更新当前时间
    public void updateCurrentTime(){
        try {
            orgSV.updateCurrentTime();
        } catch (Exception e) {
            logger.error("更新当前时间出错:", e);
        }
    }
}
