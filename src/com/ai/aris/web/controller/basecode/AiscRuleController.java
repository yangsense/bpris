package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscRuleBean;
import com.ai.aris.server.basecode.model.AiscRule;
import com.ai.aris.server.basecode.service.interfaces.IAiscRuleSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscRuleController {
	private IAiscRuleSV sv = (IAiscRuleSV) ServiceFactory.getService(IAiscRuleSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);

	/**
	 * 规则生成器页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscRuleInit")
    public String aiscRuleInit() {
        return "basecode/aiscRule/aiscRuleList";
    }
	
	/**
	 * 服务器类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscRuleType.ajax")
    @ResponseBody
    public Map getAiscRuleType() throws Exception {
		List<Map> aiscRuleType = dictSV.queryDict("RULE_TYPE");
        Map map = new HashMap();
        map.put("AiscRuleType", JsonUtil.toJson(aiscRuleType));
        return map;
    }
	
	/**
	 * 规则类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscRuleField.ajax")
    @ResponseBody
    public Map getAiscRuleField() throws Exception {
		List<Map> aiscRuleField = dictSV.queryDict("RULE_FIELD");
        Map map = new HashMap();
        map.put("AiscRuleField", JsonUtil.toJson(aiscRuleField));
        return map;
    }
	
	
	
	/**
	 * 规则生成器查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscRuleList.ajax")
    @ResponseBody
    public JSONObject queryAiscRuleList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscRule model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 规则生成器详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscRuleDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscRuleBean aiscRuleBean = null;
		if(id!=0){
			 aiscRuleBean = sv.getAiscRule(id);
		}
		model.addAttribute("aiscRuleBean",aiscRuleBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscRule/aiscRuleDetail";
    }
	
	/**
	 * 删除规则生成器
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisRule.ajax")
	@ResponseBody
    public Map deleteAisRule(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscRule(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改规则生成器
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscRule")
	@ResponseBody
    public Map saveAiscRule(@RequestBody AiscRuleBean bean) throws Exception {
		sv.saveAiscRule(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

}
