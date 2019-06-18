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
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.model.AiscCareProv;
import com.ai.aris.server.basecode.service.interfaces.IAiscCareProvSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscCareProvController {
	private IAiscCareProvSV sv = (IAiscCareProvSV) ServiceFactory.getService(IAiscCareProvSV.class);
	
	 private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	/**
	 * 人员信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscCareProvInit")
    public String aiscCareProvInit() {
        return "basecode/aiscCareProv/aiscCareProvList";
    }
	
	/**
	 * 人员信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscCareProvList.ajax")
    @ResponseBody
    public JSONObject queryAiscCareProvList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscCareProv model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 人员类别下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscCareProvType.ajax")
    @ResponseBody
    public Map getAiscCareProvType() throws Exception {
		List<Map> aiscCareProvType = dictSV.queryDict("CAREPROV_TYPE");
        Map map = new HashMap();
        if (aiscCareProvType!=null && aiscCareProvType.size()>0) {
        	map.put("AiscCareProvType", JsonUtil.toJson(aiscCareProvType));
		}        
        return map;
    }
	/**
	 * 人员信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscCareProvDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscCareProvBean aiscCareProvBean = null;
		if(id!=0){
			 aiscCareProvBean = sv.getAiscCareProv(id);
		}
		model.addAttribute("aiscCareProvBean",aiscCareProvBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscCareProv/aiscCareProvDetail";
    }
	
	/**
	 * 删除人员信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAiscCareProv.ajax")
	@ResponseBody
    public Map deleteAiscCareProv(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscCareProv(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改人员信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscCareProv")
	@ResponseBody
    public Map saveAiscCareProv(@RequestBody AiscCareProvBean bean) throws Exception {
		sv.saveAiscCareProv(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

	//检测code码所对应的检查项目是否已经存在
    @RequestMapping("/checkCareProvCode.ajax")
    @ResponseBody
    
    public boolean chekcAiscOrdCateCode(@RequestParam long careprovId,@RequestParam String careprovCode,String orgId) throws Exception{
        return  sv.checkCareprovCode(careprovId,careprovCode,orgId);
    }
}
