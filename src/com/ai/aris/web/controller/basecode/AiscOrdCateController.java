package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscOrdCategoryBean;
import com.ai.aris.server.basecode.model.AiscOrdCategoryModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscHisDatasourceSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdCateSV;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;

/**
 * Created by fins on 2015/10/16.
 */
@Controller
@RequestMapping("/aiscOrdCate")
public class AiscOrdCateController {
    private IAiscOrdCateSV sv=(IAiscOrdCateSV) ServiceFactory.getService(IAiscOrdCateSV.class);
	private IAiscHisDatasourceSV dataSv = (IAiscHisDatasourceSV) ServiceFactory.getService(IAiscHisDatasourceSV.class);


    @RequestMapping(value = "/page/{one}")
    public String getPage(@PathVariable String one,@RequestParam(value="id",required=false) String id ,
                          @RequestParam(value="opType",required=false) String opType,Map map, HttpServletRequest request,Model model){
        SysOrgBean org = (SysOrgBean) request.getSession().getAttribute(Constants.SESSION_ORG_OBJ);//获取org信息；
        SysOrgBean  sysorg = dataSv.queryOrgMsg(org.getOrgId());
	    if(sysorg != null){
			model.addAttribute("cityCode",sysorg.getCityCode());
			model.addAttribute("countyCode",sysorg.getDistrictCode());
			model.addAttribute("orgId",sysorg.getOrgId());
	    }
        map.put("id",id);
        map.put("opType",opType);
        return "basecode/aiscOrdCategory/"+one;
    }

    @RequestMapping("/getAiscOrdCateList.ajax")
    @ResponseBody
    public ResultDTO getAiscOrdCateList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "rows", defaultValue = "10") int rows,
                                         AiscOrdCategoryModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model,result);
        return result;
    }

    @RequestMapping("/saveAiscOrdCate")
    @ResponseBody
    public Map saveAiscOrdCate(@RequestBody AiscOrdCategoryBean bean,HttpServletRequest request) throws Exception {
        sv.saveAiscOrdCategory(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping("/getAiscOrdCate")
    @ResponseBody
    public AiscOrdCategoryModel getAiscOrdCate( @RequestParam long id) throws Exception {
        AiscOrdCategoryModel model = sv.getAiscOrdCategory(id);
        return model;
    }

    @RequestMapping("/deleteAiscOrdCate")
    @ResponseBody
    public Map deleteAiscOrdCate(@RequestParam long id) throws Exception{
        Map map = new HashMap();
        boolean flag = sv.deleteAiscOrdCategory(id);
        if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
    
    //检测code码所对应的检查项目是否已经存在
    @RequestMapping("/chekcAiscOrdCateCode.ajax")
    @ResponseBody
    public boolean chekcAiscOrdCateCode(@RequestParam long ordcategoryId,@RequestParam String ordcategoryCode,@RequestParam String orgId) throws Exception{
        return  sv.chekcAiscOrdCate(ordcategoryId,ordcategoryCode,orgId);
    }
    
	/**
	 * 检查大类下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscOrdCategory.ajax")
    @ResponseBody
    public Map getAiscOrdCategoryServer(String orgId) throws Exception {
		List<Map> aiscOrdCategoryName = sv.getAiscOrdCategorySelect2(orgId);
        Map map = new HashMap();
        map.put("AiscOrdCategoryName", JsonUtil.toJson(aiscOrdCategoryName));
        return map;
    }
	@RequestMapping("/getAiscOrdCategoryBypara.ajax")
    @ResponseBody
    public Map getAiscOrdCategoryBypara(String cityCode,String countyCode,String orgId) throws Exception {
		List<Map> aiscOrdCategoryName = sv.getAiscOrdCategorySelect2(cityCode, countyCode, orgId);
        Map map = new HashMap();
        map.put("AiscOrdCategoryName", JsonUtil.toJson(aiscOrdCategoryName));
        return map;
    }
	//检查子类下拉
	@RequestMapping("/getSmallSortSelect.ajax")
    @ResponseBody
    public Map getSmallSortSelect(String cityCode,String countyCode,String orgId, String ordcategoryId) throws Exception {
		List<Map> aiscOrdCategoryName = sv.getAiscOrdCategorySelect3(cityCode, countyCode, orgId, ordcategoryId);
        Map map = new HashMap();
        map.put("AiscOrdCategoryName", JsonUtil.toJson(aiscOrdCategoryName));
        return map;
    }

}
