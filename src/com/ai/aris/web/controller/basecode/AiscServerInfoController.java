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
import com.ai.aris.server.basecode.bean.AiscServerInfoBean;
import com.ai.aris.server.basecode.model.AiscServerInfo;
import com.ai.aris.server.basecode.service.interfaces.IAiscServerInfoSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscServerInfoController {
	private IAiscServerInfoSV sv = (IAiscServerInfoSV) ServiceFactory.getService(IAiscServerInfoSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);

	/**
	 * 服务器设置信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscServerInfoInit")
    public String aiscServerInfoInit() {
        return "basecode/aiscServer/aiscServerList";
    }
	
	/**
	 * 服务器类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscServerType.ajax")
    @ResponseBody
    public Map getAiscServerType() throws Exception {
		List<Map> aiscServerType = dictSV.queryDict("SERVER_TYPE");
        Map map = new HashMap();
        map.put("AiscServerType", JsonUtil.toJson(aiscServerType));
        return map;
    }
	
	/**
	 * 服务器设置信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscServerInfoList.ajax")
    @ResponseBody
    public JSONObject queryAiscServerInfoList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscServerInfo model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 服务器设置信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscServerInfoDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscServerInfoBean aiscServerInfoBean = null;
		if(id!=0){
			 aiscServerInfoBean = sv.getAiscServerInfo(id);
		}
		model.addAttribute("aiscServerInfoBean",aiscServerInfoBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscServer/aiscServerDetail";
    }
	
	/**
	 * 删除服务器设置信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisServerInfo.ajax")
	@ResponseBody
    public Map deleteAisServerInfo(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscServerInfo(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改服务器设置信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscServerInfo")
	@ResponseBody
    public Map saveAiscServerInfo(@RequestBody AiscServerInfoBean bean) throws Exception {
		sv.saveAiscServerInfo(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

}
