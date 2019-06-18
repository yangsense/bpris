package com.ai.aris.server.basecode.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.aris.server.basecode.model.AiscPeriodModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscPeriodSV;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.aris.server.workstation.bean.AiscPeriodEngine;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.ServiceUtil;

public class AiscPeriodSVImpl implements IAiscPeriodSV {

	@Override
	public ResultDTO queryPageList(AiscPeriodModel model, ResultDTO resultDTO)
			throws Exception {
		StringBuffer condition = new StringBuffer();
		Map<String, Object> param = new HashMap<String, Object>();
		condition.append(" 1=1");
		if (model != null) {
			if (StringUtils.isNotBlank(model.getPeriodDesc())) {
				condition.append(" and Period_Desc like :Period_Desc");
				param.put("Period_Desc", "%" + model.getPeriodDesc() + "%");
			}
		}
		condition.append(" order by ID");
		int total = AiscPeriodEngine.getBeansCount(condition.toString(), param);
		AiscPeriodBean[] beans = null;
		if (total > 0) {
			beans = AiscPeriodEngine.getBeans(null, condition.toString(), param,
					resultDTO.getStart(), resultDTO.getEnd(),
					false);
		}
		Map<String,DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans,AiscPeriodModel.class,dicts));
        resultDTO.setRecords(total);
		return resultDTO;
	}

	@Override
	public AiscPeriodBean getBean(long id) throws Exception {
		return AiscPeriodEngine.getBean(id);
	}

	@Override
	public void save(AiscPeriodBean bean) throws Exception {
		if (bean.getId() == 0) {
			long id = ServiceUtil.getSequence("SEQ_AISC_PERIOD");
			bean.setId(id);
			bean.setPeriodId(String.valueOf(id));
			AiscPeriodEngine.save(bean);
		} else {
			AiscPeriodBean oldBean = new AiscPeriodBean();
			oldBean.setId(bean.getId());
			oldBean.setStsToOld();
			if ("delete".equals(bean.getPeriodId())) {
				oldBean.delete();
			}
			DataContainerFactory.copyNoClearData(bean, oldBean);
			AiscPeriodEngine.save(oldBean);
		}
	}

}
