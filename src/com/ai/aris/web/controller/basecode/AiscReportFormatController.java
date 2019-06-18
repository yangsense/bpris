package com.ai.aris.web.controller.basecode;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.model.AiscReportFormat2Loc;
import com.ai.aris.server.basecode.service.interfaces.IAiscReportFormatSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.webservice.service.interfaces.IOrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/basecode")
public class AiscReportFormatController {
	private IAiscReportFormatSV sv = (IAiscReportFormatSV) ServiceFactory.getService(IAiscReportFormatSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	
	@Autowired
    private IOrgSV orgSV;
	/**
	 * 打印模板配置
	 * @return
	 */
	@RequestMapping(value = "/aiscReportFormatInit")
    public String aiscLocInit() {
        return "basecode/aiscReportFormat/aiscReportFormatList";
    }
	
	/**
	 * 打印模板类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscPrintType.ajax")
    @ResponseBody
    public Map getAiscEquipType() throws Exception {
		List<Map> aiscModelType = dictSV.queryDict("AISC_MODELTYPE");
        Map map = new HashMap();
        map.put("AiscModelType", JsonUtil.toJson(aiscModelType));
        return map;
    }
	
	/**
	 * 打印模板查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryPrintTemplateList.ajax")
    @ResponseBody
    public JSONObject queryPrintTemplateList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscReportFormat2Loc model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 打印模板详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscReportFormatDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscReportFormat2Loc aiscReportBean = null;
		if(id!=0){
			aiscReportBean = sv.getAiscReport(id);
		}
		model.addAttribute("aiscReportBean",aiscReportBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscReportFormat/aiscReportFormatDetail";
    }
	/**
	 * 删除打印模板
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisReport.ajax")
	@ResponseBody
    public Map deleteAisReport(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscReport(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改打印模板
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscReport")
	@ResponseBody
    public Map saveAiscReport(@RequestBody AiscReportFormat2Loc bean) throws Exception {
		sv.saveAiscReport(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }


	/**
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkAiscReportFormat2Loc.ajax")
	@ResponseBody
	public Map checkAiscReportFormat2Loc(AiscReportFormat2Loc bean) throws Exception {
		Boolean flag  = sv.checkIsExist(bean);
		Map<String,String> map = new HashMap();
		map.put("result", flag+"");
		return map;
	}

}
