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
import com.ai.aris.server.basecode.bean.AiscTpcat2LocBean;
import com.ai.aris.server.basecode.model.AiscTpcat2Loc;
import com.ai.aris.server.basecode.service.interfaces.IAiscTpcat2LocSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;


@Controller
@RequestMapping("/basecode")
public class AiscTpcat2LocController {
	private IAiscTpcat2LocSV sv = (IAiscTpcat2LocSV) ServiceFactory.getService(IAiscTpcat2LocSV.class);

	/**
	 * 检查部位与科室关联页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscTpcat2LocInit")
    public String aiscTpcat2LocInit() {
        return "basecode/aiscTpcat2Loc/aiscTpcat2LocList";
    }
	
	/**
	 * 检查部位与科室关联查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscTpcat2LocList.ajax")
    @ResponseBody
    public JSONObject queryAiscTpcat2LocList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscTpcat2Loc model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 检查部位与科室关联详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscTpcat2LocDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscTpcat2LocBean aiscTpcat2LocBean = null;
		if(id!=0){
			 aiscTpcat2LocBean = sv.getAiscTpcat2Loc(id);
		}
		model.addAttribute("aiscTpcat2LocBean",aiscTpcat2LocBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscTpcat2Loc/aiscTpcat2LocDetail";
    }
	
	/**
	 * 删除检查部位与科室关联
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisTpcat2Loc.ajax")
	@ResponseBody
    public Map deleteAisTpcat2Loc(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscTpcat2Loc(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改检查部位与科室关联
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscTpcat2Loc")
	@ResponseBody
    public Map saveAiscTpcat2Loc(@RequestBody AiscTpcat2LocBean bean) throws Exception {
		sv.saveAiscTpcat2Loc(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

}
