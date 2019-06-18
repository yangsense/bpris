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
import com.ai.aris.server.basecode.bean.AiscRoomBean;
import com.ai.aris.server.basecode.model.AiscRoom;
import com.ai.aris.server.basecode.service.interfaces.IAiscRoomSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;


@Controller
@RequestMapping("/basecode")
public class AiscRoomController {
	private IAiscRoomSV sv = (IAiscRoomSV) ServiceFactory.getService(IAiscRoomSV.class);
	/**
	 * 房间设置信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscRoomInit")
    public String aiscRoomInit() {
        return "basecode/aiscRoom/aiscRoomList";
    }
	
	/**
	 * 房间设置信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscRoomList.ajax")
    @ResponseBody
    public JSONObject queryAiscRoomList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscRoom model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 房间设置信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscRoomDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscRoomBean aiscRoomBean = null;
		if(id!=0){
			 aiscRoomBean = sv.getAiscRoom(id);
		}
		model.addAttribute("aiscRoomBean",aiscRoomBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscRoom/aiscRoomDetail";
    }
	
	/**
	 * 删除房间设置信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisRoom.ajax")
	@ResponseBody
    public Map deleteAisRoom(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscRoom(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改房间设置信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscRoom")
	@ResponseBody
    public Map saveAiscRoom(@RequestBody AiscRoomBean bean) throws Exception {
		sv.saveAiscRoom(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
}
