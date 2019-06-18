package com.ai.aris.server.workstation.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.common.bean.CommonEngine;
import com.ai.aris.server.workstation.bean.AiscStudyLockBean;
import com.ai.aris.server.workstation.bean.AiscStudyLockEngine;
import com.ai.aris.server.workstation.service.interfaces.IStudyLockSV;
import com.ai.common.util.ServiceUtil;

import org.apache.commons.lang.StringUtils;

/**
 * Created by Walter on 2017/7/31.
 * 报告上锁
 */
public class StudyLockSVImpl implements IStudyLockSV{
    @Override
    public void saveAiscStudyLock(AiscStudyLockBean bean,boolean isNew) throws Exception{
        if (isNew) {
            AiscStudyLockEngine.save(bean);
        }else {
            AiscStudyLockBean oldBean = AiscStudyLockEngine.getBean(bean.getStudyinfoId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscStudyLockEngine.save(oldBean);
        }
    }

    @Override
    public AiscStudyLockBean getBeanById(long studyInfoId)throws Exception{
        return AiscStudyLockEngine.getBean(studyInfoId);
    }
    
    @Override
    public AiscStudyLockBean[] getBeanByIdSelf(long studyInfoId,String userId)throws Exception{
    	StringBuffer condition = new StringBuffer();
	    condition.append(" 1=1");
	    condition.append(" and studyinfo_id = " + studyInfoId );
	    condition.append(" and lock_status ='1'");
	    if (isNotBlank(userId)) {
            condition.append(" and user_id <> '" + userId +"'" );
        }
	    AiscStudyLockBean[] beans = AiscStudyLockEngine.getBeans(condition.toString(),null);
	    if(beans!=null&&beans.length>0){
	    	return beans;
	    }
        return null;
    }

    @Override
    public void autoUnLockStudyReport(String userId)throws Exception{
        CommonEngine.execute("UPDATE AISC_STUDY_LOCK SET LOCK_STATUS = '0' WHERE USER_ID = '"+userId+"'",null);
    }

}
