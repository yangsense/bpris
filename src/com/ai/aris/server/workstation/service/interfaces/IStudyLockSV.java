package com.ai.aris.server.workstation.service.interfaces;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscEquipmentEngine;
import com.ai.aris.server.workstation.bean.AiscStudyLockBean;
import com.ai.common.util.ServiceUtil;

/**
 * Created by Walter on 2017/7/31.
 */
public interface IStudyLockSV {
    /**
     * 保存上锁记录
     * @param bean
     * @param isNew
     * @throws Exception
     */
    public void saveAiscStudyLock(AiscStudyLockBean bean,boolean isNew) throws Exception;

    /**
     * 获取
     * @param studyInfoId
     * @return
     * @throws Exception
     */
    public AiscStudyLockBean getBeanById(long studyInfoId)throws Exception;

    /**
     * 自动解锁相关的记录信息
     * @param userId
     * @throws Exception
     */
    public void autoUnLockStudyReport(String userId)throws Exception;
    
    public AiscStudyLockBean[] getBeanByIdSelf(long studyInfoId,String userId)throws Exception;
}
