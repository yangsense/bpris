package com.ai.aris.web.controller.basecode;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscBodyPartBean;
import com.ai.aris.server.basecode.model.AiscBodyPart;
import com.ai.aris.server.basecode.service.interfaces.IAiscBodyPartSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang.StringUtils.isNotBlank;


@Controller
@RequestMapping("/basecode")
public class AiscBodyPartController {
	private IAiscBodyPartSV sv = (IAiscBodyPartSV) ServiceFactory.getService(IAiscBodyPartSV.class);

	/**
	 * 检查部位页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscBodyPartInit")
    public String aiscBodyPartInit() {
        return "basecode/aiscBodyPart/aiscBodyPartList";
    }
	
	/**
	 * 检查部位查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscBodyPartList.ajax")
    @ResponseBody
    public JSONObject queryAiscBodyPartList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscBodyPart model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 检查部位详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscBodyPartDetail.html")
    public String viewDetail(@RequestParam String id,@RequestParam String type,Model model) throws Exception {
		AiscBodyPartBean aiscBodyPartBean = null;
		if(isNotBlank(id)){
			 aiscBodyPartBean = sv.getAiscBodyPart(id);
		}
		model.addAttribute("aiscBodyPartBean",aiscBodyPartBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscBodyPart/aiscBodyPartDetail";
    }
	
	/**
	 * 部位大类下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getBodyparType.ajax")
    @ResponseBody
    public Map getBodypartype(String orgId) throws Exception {
		List<Map> bodypartype = sv.getBodyparType(orgId);
        Map map = new HashMap();
        map.put("BodyparType", JsonUtil.toJson(bodypartype));
        return map;
    }
	
	/**
	 * 删除检查部位
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisBodyPart.ajax")
	@ResponseBody
    public Map deleteAisBodyPart(@RequestParam String id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscBodyPart(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改检查部位
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscBodyPart")
	@ResponseBody
    public Map saveAiscBodyPart(@RequestBody AiscBodyPartBean bean) throws Exception {
		sv.saveAiscBodyPart(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }


	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkAiscBodyPart.ajax")
	@ResponseBody
	public Map checkAiscBodyPart(AiscBodyPartBean bean) throws Exception {
		Boolean flag  = sv.checkIsExist(bean);
		Map<String,String> map = new HashMap();
		map.put("result", flag+"");
		return map;
	}

	//导入公共检查部位
	@RequestMapping("partimport.ajax")
	@ResponseBody
	public JSONObject partimport(HttpServletRequest request) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("ERRCODE", "0");
		try {
			String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
			String result = sv.partimport(orgId);
			jsonRes.put("ERRCODE", result);
		} catch (Exception e) {
			jsonRes.put("ERRCODE", "1");
			jsonRes.put("ERRINFO", e.getMessage());
		}
		return jsonRes;
	}
}
