package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscHisDatasourceBean;
import com.ai.aris.server.basecode.model.QueryDataBaseModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscHisDatasourceSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscHisDatasourceController {
	private IAiscHisDatasourceSV sv = (IAiscHisDatasourceSV) ServiceFactory.getService(IAiscHisDatasourceSV.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	//数据源配置页面
	@RequestMapping("aiscHisDatasource.html")
    public String AiscHisDatasource( HttpServletRequest request,Model model) {
		SysOrgBean org = (SysOrgBean) request.getSession().getAttribute(Constants.SESSION_ORG_OBJ);//获取org信息；
	    if(org != null){
			model.addAttribute("cityCodeValue",org.getCityCode());
			model.addAttribute("countyCodeValue",org.getDistrictCode());
			model.addAttribute("orgIdValue",org.getOrgId());
	    }
        return "basecode/aiscHisDatasource/aiscHisDatasource";
    }
	//数据源配置集合
	@RequestMapping("/getAiscHisDatasourceList.ajax")
    @ResponseBody
    public JSONObject getAiscHisDatasourceList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QueryDataBaseModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList2(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 数据源类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscSourceType.ajax")
    @ResponseBody
    public Map getAiscSourceType() throws Exception {
		List<Map> aiscSourceType = dictSV.queryDict("PATIENT_TYPE");
        Map map = new HashMap();
        map.put("AiscSourceType", JsonUtil.toJson(aiscSourceType));
        return map;
    }
	/**
	 * 城市下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCitys.ajax")
    @ResponseBody
    public Map getCitys() throws Exception {
		List<Map> citys = dictSV.queryAreaById("GB/T2260", "2");
        Map map = new HashMap();
        map.put("cityCode", JsonUtil.toJson(citys));
        return map;
    }
	/**
	 *区县下拉 
	 */
	@RequestMapping("/getCountys.ajax")
    @ResponseBody
    public Map getCountys(String cityCode) throws Exception {
		List<Map> citys = dictSV.queryAreaById("GB/T2260", "3");
        Map map = new HashMap();
        map.put("countyMsg", JsonUtil.toJson(citys));
        return map;
    }
	/**
	 *机构下拉 
	 */
	@RequestMapping("/getOrgSelects.ajax")
    @ResponseBody
    public Map getOrgSelects(String cityCode,String countyCode) throws Exception {
		List<Map> citys = dictSV.queryOrg(cityCode,countyCode);
        Map map = new HashMap();
        map.put("orgMsg", JsonUtil.toJson(citys));
        return map;
    }
	/**
	 *机构下拉 ,无条件
	 */
	@RequestMapping("/getOrgSelects2.ajax")
    @ResponseBody
    public Map getOrgSelects2() throws Exception {
		String cityCode="";
		String countyCode="";
		List<Map> citys = dictSV.queryOrg(cityCode,countyCode);
        Map map = new HashMap();
        map.put("orgMsg", JsonUtil.toJson(citys));
        return map;
    }
	/**
	 *根据机构ID获取机构信息
	 */
	@RequestMapping("/getOrgMsgById.ajax")
    @ResponseBody
    public Map getOrgMsgById(String orgId) throws Exception {
//		List<Map> orgMsg = dictSV.queryOrgMsgById(orgId);
        Map map = new HashMap();
//        map.put("orgMsg", JsonUtil.toJson(orgMsg));
        return map;
    }
	/**
	 * 数据源配置详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("aiscHisDatasourceDetail.html")
    public String aiscHisDatasourceDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscHisDatasourceBean bean = null;
		if(id!=0){
			bean = sv.getBean(id);
		}
		model.addAttribute("bean",bean);
		model.addAttribute("dataType",type);
        return "basecode/aiscHisDatasource/aiscHisDatasourceDetail";
    }
	/**
	 * 删除数据源配置
	 * @param id
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAiscHisDatasource.ajax")
	@ResponseBody
    public Map deleteAiscHisDatasource(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		AiscHisDatasourceBean bean = new AiscHisDatasourceBean();
		bean.setSourceId(id);
		bean.setStatus("1");
		sv.save(bean);
        map.put("ERRCODE",0);
        return map;
    }
	/**
	 * 新增或修改数据源配置
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscHisDatasource.ajax")
	@ResponseBody
    public Map saveAiscHisDatasource(@RequestBody AiscHisDatasourceBean bean) throws Exception {
		Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        try{
        	sv.save(bean);
		}catch(Exception ex){
			map.put("ERRCODE", "1");
			map.put("ERRINFO", ex.getMessage());
			throw ex;
		}        
        return map;
    }
}
