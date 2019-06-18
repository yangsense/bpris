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
import com.ai.aris.server.basecode.bean.AiscOrdSubCategoryBean;
import com.ai.aris.server.basecode.model.AiscOrdSubCategoryModel;
import com.ai.aris.server.basecode.model.QueryAiscOrdSubCategoryModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscHisDatasourceSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscOrdSubCateSV;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.Constants;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/10/16
 * Time: 14:05
 * Email:zhangfz3@asiainfo.com
 */
@Controller
@RequestMapping("/aiscOrdSubCate")
public class AiscOrdSubCateController {
    private IAiscOrdSubCateSV sv=(IAiscOrdSubCateSV) ServiceFactory.getService(IAiscOrdSubCateSV.class);
	private IAiscHisDatasourceSV dataSv = (IAiscHisDatasourceSV) ServiceFactory.getService(IAiscHisDatasourceSV.class);
	

    @RequestMapping(value = "/page/{one}")
    public String getPage(@PathVariable String one,@RequestParam(value="id",required=false) String id,
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
        return "basecode/aiscOrdSubCategory/"+one;
    }

    @RequestMapping("/getAiscOrdSubCateList.ajax")
    @ResponseBody
    public ResultDTO getAiscOrdSubCateList(@RequestParam(value = "page", defaultValue = "1") int page,
                                        @RequestParam(value = "rows", defaultValue = "10") int rows,
                                           QueryAiscOrdSubCategoryModel queryAiscOrdSubCategoryModel) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList2(queryAiscOrdSubCategoryModel, result);
        return result;
    }

    @RequestMapping("/saveAiscOrdSubCate")
    @ResponseBody
    public Map saveAiscOrdSubCate(@RequestBody AiscOrdSubCategoryBean bean,HttpServletRequest request) throws Exception {
        sv.saveAiscOrdSubCategory(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping("/getAiscOrdSubCate")
    @ResponseBody
    public AiscOrdSubCategoryModel getAiscOrdSubCate( @RequestParam long id) throws Exception {
        AiscOrdSubCategoryModel model = sv.getAiscOrdSubCategory(id);
        return model;
    }

    @RequestMapping("/deleteAiscOrdSubCate")
    @ResponseBody
    public Map deleteAiscOrdSubCate(@RequestParam long id) throws Exception{
        Map map = new HashMap();
        boolean flag = sv.deleteAiscOrdSubCategory(id);
        if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
    /**
	 * 检查子类下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscOrdSubCategory.ajax")
    @ResponseBody
    public Map getAiscOrdSubCategoryServer(String  ordcategoryId,String orgId) throws Exception {
		List<Map> aiscOrdSubCategoryName = sv.getAiscOrdSubCategorySelect(ordcategoryId,orgId);
        Map map = new HashMap();
        map.put("AiscOrdSubCategory", aiscOrdSubCategoryName);
        return map;
    }

	
	   
    //检测code码所对应的检查项目是否已经存在
    @RequestMapping("/checkSubOrdcategoryCode.ajax")
    @ResponseBody
    public boolean checkSubOrdcategoryCode(@RequestParam long ordsubcategoryId,@RequestParam String ordsubcategoryCode) throws Exception{
        return sv.checkSubOrdcategoryCode(ordsubcategoryId,ordsubcategoryCode);
    }
    
	
}
