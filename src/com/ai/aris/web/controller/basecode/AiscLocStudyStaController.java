package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.service.interfaces.IAiscLocSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscRoomSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscServerInfoSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.webservice.service.interfaces.IOrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;

/**
 * 科室与检查状态关联
 * @author wuzheng
 *
 */
@Controller
@RequestMapping("/basecode")
public class AiscLocStudyStaController {
	private IAiscLocSV sv = (IAiscLocSV) ServiceFactory.getService(IAiscLocSV.class);
	private Log logger = LogFactory.getLog(AiscLocStudyStaController.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	
	@Autowired
    private IOrgSV orgSV;
	
	/**
	 * 科室与检查状态关联init
	 * @return
	 */
	@RequestMapping(value = "/aiscLocStudySta")
    public String aiscLocInit() {
        return "basecode/aiscLoc/aiscLocStudySta";
    }
	
	/**
	 * 科室信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryStudyStaLocList.ajax")
    @ResponseBody
    public JSONObject queryStudyStaLocList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscLoc model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryStudyStaLocList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 检查状态下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getStudyStatus.ajax")
    @ResponseBody
    public Map getStudyStatus() throws Exception {
		List<Map> studyStatus = dictSV.queryDict("STUDY_STATUS");
        Map map = new HashMap();
        map.put("STUDY_STATUS", JsonUtil.toJson(studyStatus));
        return map;
    }
	
	/**
	 * 操作员登录科室设置详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscLocStatusDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
//		AiscLoginLocBean aiscLoginLocBean = null;
//		if(id!=0){
//			 aiscLoginLocBean = sv.getAiscLocStatus(id);
//		}
//		model.addAttribute("aiscLoginLocBean",aiscLoginLocBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscLoc/aiscLocStatusDetail";
    }
	
	/**
	 * 删除科室与检查状态关联信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisLocStudySta.ajax")
	@ResponseBody
    public Map deleteAisLocStudySta(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAisLocStudySta(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	
	/**
	 * 新增或修改科室与检查状态关联设置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscStatusLoc")
	@ResponseBody
    public Map saveAiscStatusLoc(@RequestParam String locId,@RequestParam String orgId,@RequestParam String selectOption) throws Exception {
		String id = locId.substring(0,locId.indexOf("||"));
//		String operCode = locId.substring(locId.indexOf("||")+2);
		String selectOp = java.net.URLDecoder.decode(selectOption,"UTF-8");
		try{
			String status[] = selectOp.split(",");
			sv.deleLocStudySta(id,orgId);
			//插入科室与检查状态关联表AISC_LOC_STUDYSTA
			for(int i=0;i<status.length;i++){
				String sta = status[i].substring(0,status[i].indexOf("||"));
				String staName = status[i].substring(status[i].indexOf("||")+2);
				sv.saveLocStudySta(id,orgId,sta,staName);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
	
	/**
	 * 根据操作工号重新加载检查状态列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getLocStatusList.ajax")
	@ResponseBody
    public Map getLocStatusList(@RequestParam String locId,@RequestParam String orgId) throws Exception {
		Map map = new HashMap();
		//已加入检查状态列表查询
        List<AiscLoc> newStatusList = sv.getNewLocListStatus(locId,orgId);
        //未加入检查状态列表查询
        List<AiscLoc> notJoinStatus = sv.getNotJoinLocStatus(locId,orgId);
        map.put("NotJoinStatus", JsonUtil.toJson(notJoinStatus));
        map.put("newStatusList", JsonUtil.toJson(newStatusList));
        return map;
    }
}
