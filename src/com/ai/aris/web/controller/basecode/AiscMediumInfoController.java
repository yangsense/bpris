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
import com.ai.aris.server.basecode.bean.AiscMediumInfoBean;
import com.ai.aris.server.basecode.model.AiscMediumInfo;
import com.ai.aris.server.basecode.service.interfaces.IAiscMediumInfoSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscMediumInfoController {
	private IAiscMediumInfoSV sv = (IAiscMediumInfoSV) ServiceFactory.getService(IAiscMediumInfoSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);

	/**
	 * 存储介质信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscMediumInfoInit")
    public String aiscMediumInfoInit() {
        return "basecode/aiscMedium/aiscMediumList";
    }
	
	/**
	 * 介质类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscMediumType.ajax")
    @ResponseBody
    public Map getAiscMediumType() throws Exception {
		List<Map> aiscMediumType = dictSV.queryDict("MEDIUM_TYPE");
        Map map = new HashMap();
        map.put("AiscMediumType", JsonUtil.toJson(aiscMediumType));
        return map;
    }
	
	/**
	 * 存储介质信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscMediumInfoList.ajax")
    @ResponseBody
    public JSONObject queryAiscMediumInfoList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscMediumInfo model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 存储介质信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscMediumInfoDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscMediumInfoBean aiscMediumInfoBean = null;
		if(id!=0){
			 aiscMediumInfoBean = sv.getAiscMediumInfo(id);
		}
		model.addAttribute("aiscMediumInfoBean",aiscMediumInfoBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscMedium/aiscMediumDetail";
    }
	
	/**
	 * 删除存储介质信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisMediumInfo.ajax")
	@ResponseBody
    public Map deleteAisMediumInfo(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscMediumInfo(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改存储介质信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscMediumInfo")
	@ResponseBody
    public Map saveAiscMediumInfo(@RequestBody AiscMediumInfoBean bean) throws Exception {
		sv.saveAiscMediumInfo(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

}
