package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.model.AiscPeriodModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscPeriodSV;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;


@Controller
@RequestMapping("/basecode")
public class AiscPeriodController {
	private IAiscPeriodSV sv = (IAiscPeriodSV) ServiceFactory.getService(IAiscPeriodSV.class);
	
	//时段配置页面
	@RequestMapping("aiscPeriod.html")
    public String aiscPeriod() {
        return "basecode/aiscPeriod/aiscPeriod";
    }
	//获取时段配置集合
	@RequestMapping("/getAiscPeriodList.ajax")
    @ResponseBody
    public JSONObject getAiscPeriodList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscPeriodModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 时段配置详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("aiscPeriodDetail.html")
    public String aiscPeriodDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscPeriodBean bean = null;
		if(id!=0){
			bean = sv.getBean(id);
		}
		model.addAttribute("bean",bean);
		model.addAttribute("dataType",type);
        return "basecode/aiscPeriod/aiscPeriodDetail";
    }
	/**
	 * 删除时段配置
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAiscPeriod.ajax")
	@ResponseBody
    public Map deleteAiscPeriod(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		AiscPeriodBean bean = new AiscPeriodBean();
		bean.setId(id);
		bean.setPeriodId("delete");
		sv.save(bean);
        map.put("ERRCODE",0);
        return map;
    }
	/**
	 * 新增或修改时段配置
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscPeriod.ajax")
	@ResponseBody
    public Map saveAiscPeriod(@RequestBody AiscPeriodBean bean) throws Exception {
		sv.save(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
}
