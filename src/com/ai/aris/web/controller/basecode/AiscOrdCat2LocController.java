package com.ai.aris.web.controller.basecode;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCat2LocBean;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.aris.server.basecode.model.QryOrdCat2LocModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscLocSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdCat2LocSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdCateSV;
import com.ai.aris.server.webservice.service.interfaces.IOrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


/**
 * 科室与检查大类关联
 */
@Controller
@RequestMapping("/basecode")
public class AiscOrdCat2LocController {
	private IAiscOrdCat2LocSV sv = (IAiscOrdCat2LocSV) ServiceFactory.getService(IAiscOrdCat2LocSV.class);
	private IAiscLocSV locSV = (IAiscLocSV) ServiceFactory.getService(IAiscLocSV.class);
	private IAiscOrdCateSV ordCateSV = (IAiscOrdCateSV) ServiceFactory.getService(IAiscOrdCateSV.class);
	private IOrgSV orgSV = (IOrgSV) ServiceFactory.getService(IOrgSV.class);
	/**
	 * 科室与检查大类关联页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscOrdCat2LocInit")
    public String aiscLoginLocInit() {
        return "basecode/aiscOrdCat2Loc/aiscOrdCat2LocList";
    }
	
	/**
	 * 操作员登录科室设置查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscOrdCat2LocList.ajax")
    @ResponseBody
    public JSONObject queryAiscOrdCat2LocList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryOrdCat2LocModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
    //获取科室
    @RequestMapping("/getLocItem.ajax")
    @ResponseBody
    public JSONObject getLocItem(String key,HttpServletRequest request) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscLocBean[] locBeans = sv.getLocItem(key);			
			jsonObj.put("dataList", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new AiscLocBean[0] : locBeans));
		} catch (Exception e) {
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
	
    //获取检查大类
    @RequestMapping("/getOrdcategoryItem.ajax")
    @ResponseBody
    public JSONObject getOrdcategoryItem(String key,HttpServletRequest request) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscOrdCategoryBean[] ordCategoryBeans = sv.getOrdCategoryItem(key);			
			jsonObj.put("dataList", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(ordCategoryBeans == null ? new AiscOrdCategoryBean[0] : ordCategoryBeans));
		} catch (Exception e) {
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
    
	
	
	/**
	 * 操作员登录科室设置详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/viewDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscOrdCat2LocBean bean = null;
		AiscLocBean locBean = null;
		AiscOrdCategoryModel ordCategoryBean = null;
		if(id!=0){
			bean = sv.getQryOrdCat2LocBean(id);
			locBean = locSV.getAiscLoc(bean.getLocId());
			ordCategoryBean = ordCateSV.getAiscOrdCategory(bean.getOrdcatId());
		}
		model.addAttribute("aiscOrdCat2LocBean",bean);
		model.addAttribute("locBean",locBean);
		model.addAttribute("ordCategoryBean",ordCategoryBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscOrdCat2Loc/aiscOrdCat2Locdetail";
    }
	
	/**
	 * 删除操作员登录科室设置
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAiscOrdCat2Loc.ajax")
	@ResponseBody
    public Map deleteAiscOrdCat2Loc(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteOrdCat2Loc(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscOrdCat2Loc.ajax")
	@ResponseBody
    public Map saveAiscOrdCat2Loc(@RequestBody AiscOrdCat2LocBean bean) throws Exception {
		sv.saveOrdCat2Loc(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

  /**
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkAiscOrdCat2Loc.ajax")
	@ResponseBody
    public Map checkAiscOrdCat2Loc(AiscOrdCat2LocBean bean) throws Exception {
		Boolean flag  = sv.checkIsExist(bean);
        Map<String,String> map = new HashMap();
        map.put("result", flag+"");
        return map;
    }

}
