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
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.model.AiscEquipment;
import com.ai.aris.server.basecode.service.interfaces.IAiscEquipSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscEquipmentController {
	private IAiscEquipSV sv = (IAiscEquipSV) ServiceFactory.getService(IAiscEquipSV.class);
	
	 private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	/**
	 * 设备信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscEquipInit")
    public String aiscLocInit() {
        return "basecode/aiscEquip/aiscEquipmentList";
    }
	
	/**
	 * 设备信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscEquipList.ajax")
    @ResponseBody
    public JSONObject queryAiscEquipList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscEquipment model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 设备类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscEquipType.ajax")
    @ResponseBody
    public Map getAiscEquipType() throws Exception {
		List<Map> aiscEquipType = dictSV.queryDict("AISC_MODALITY");
        Map map = new HashMap();
        map.put("AiscEquipType", JsonUtil.toJson(aiscEquipType));
        return map;
    }
	/**
	 * 设备信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/equipmentDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscEquipmentBean aiscEquipBean = null;
		if(id!=0){
			aiscEquipBean = sv.getAiscEquipBean(id);
		}
		model.addAttribute("aiscEquipBean",aiscEquipBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscEquip/aiscEquipDetail";
    }
	
	/**
	 * 删除设备信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisEquip.ajax")
	@ResponseBody
    public Map deleteAiscEquip(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscEquip(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改设备信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscEquip")
	@ResponseBody
    public Map saveAiscEquip(@RequestBody AiscEquipmentBean bean) throws Exception {
		sv.saveAiscEquip(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

	//equipment_code  checkEquipmentCode.ajax
	@RequestMapping("/checkEquipmentCode.ajax")
    @ResponseBody
    public boolean checkLocCode(@RequestParam long equipmentId,@RequestParam String equipmentCode,String orgId) throws Exception{
        return sv.checkEquipmentCode(equipmentId,equipmentCode,orgId);
    }
	
}
