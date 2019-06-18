package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.service.interfaces.IAiscAreaInstitutionSV;
import com.ai.aris.server.sysmanage.model.SysOrgModel;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;


/**
 *区域机构配置
 */
@Controller
@RequestMapping("/basecode/areaInstitution")
public class AiscAreaInstitutionController {
	private IAiscAreaInstitutionSV sv = (IAiscAreaInstitutionSV) ServiceFactory.getService(IAiscAreaInstitutionSV.class);

	/**
	 * 初始化
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aisAreaInstitutionInit.html")
    public String init() throws Exception {
        return "basecode/areaInstitution/list";
    }


	/**
	 * 区域机构配置信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPageList.ajax")
	@ResponseBody
	public JSONObject queryPageList(@RequestParam(value = "page", defaultValue = "1") int page,
											  @RequestParam(value = "rows", defaultValue = "10") int rows,
											  SysOrgModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryPageList(model, result);
		return AjaxUtil.jqGridJson(result);
	}



//	/**
//	 * 服务器设置信息详情
//	 * @param id
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/viewDetail.html")
//	public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
//		AisAreaInstitutionBean bean = null;
//		if(id!=0){
//			bean = sv.getBeanInfo(id);
//		}
//		model.addAttribute("bean",bean);
//		model.addAttribute("dataType",type);
//		return "basecode/areaInstitution/detail";
//	}

	/**
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/delete.ajax")
//	@ResponseBody
//	public Map deletBeanInfo(@RequestParam long id) throws Exception {
//		Map map = new HashMap();
//		boolean flag = sv.deletBeanInfo(id);
//		if(flag){
//			map.put("ERRCODE",0);
//		}else{
//			map.put("ERRINFO","删除失败");
//		}
//		return map;
//	}

	/**
	 * 新增或修改服务器设置信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/saveBeanInfo")
//	@ResponseBody
//	public Map saveBeanInfo(@RequestBody AisAreaInstitutionBean bean) throws Exception {
//		sv.saveBeanInfo(bean);
//		Map<String,String> map = new HashMap();
//		map.put("ERRCODE", "0");
//		return map;
//	}


	/**
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/importOrgToArea.ajax")
//	@ResponseBody
//	public Map importOrgToArea(){
//		Map map = new HashMap();
//		try {
//			map.put("ERRCODE",0);
//			sv.importAreaToOrg();
//		} catch (Exception e) {
//			map.put("ERRINFO","导入异常");
//
//			e.printStackTrace();
//		}
//		return map;
//	}

	/**
	 * @param id
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateHasPacs.ajax")
	@ResponseBody
	public Map updateHasPacs(long id,int status){
		Map map = new HashMap();
		try {
			map.put("ERRCODE",0);
			sv.updateHasPacs(id,status);
		} catch (Exception e) {
			map.put("ERRINFO","删除失败");
			e.printStackTrace();
		}
		return map;
	}

}
