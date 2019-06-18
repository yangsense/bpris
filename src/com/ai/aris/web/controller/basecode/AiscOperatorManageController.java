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
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscOperator2CareProvBean;
import com.ai.aris.server.basecode.model.AiscLoginLoc;
import com.ai.aris.server.basecode.model.AiscOperator2CareProvModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscCareProvSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscLoginLocSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscOperator2CareProvSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscOperatorManageController {
	private  IAiscOperator2CareProvSV sv = (IAiscOperator2CareProvSV) ServiceFactory.getService(IAiscOperator2CareProvSV.class);
	private IAiscCareProvSV careprovSv = (IAiscCareProvSV) ServiceFactory.getService(IAiscCareProvSV.class);
	private IAiscLoginLocSV loginSv = (IAiscLoginLocSV) ServiceFactory.getService(IAiscLoginLocSV.class);

	/**
	 * 操作员登录科室设置页面init  basecode/ 
	 * @return
	 */
	@RequestMapping(value = "/aiscOperatorManageInit")
    public String aiscOperatorManageInit() {
        return "basecode/aiscOperatorManage/aiscOperatorManage";
    }
	/**
	 * 操作员登陆科室相关信息查询list
	 * @return  basecode/queryAiscOperator2CareProvList
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscOperator2CareProvList.ajax")
    @ResponseBody
    public JSONObject queryAiscOperator2CareProvList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscOperator2CareProvModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
    @RequestMapping("/saveAiscOperator2CareProv.ajax")
    @ResponseBody
    public Map saveAiscOperator2CareProv(@RequestParam String operatorId,@RequestParam String careprovId) throws Exception {
		String operId = operatorId.substring(0,operatorId.indexOf("||"));
		String operCode = operatorId.substring(operatorId.indexOf("||")+2);
		try{
			String careprovIds[] = careprovId.split(",");
			sv.deleUser2careprov(operId);
			//插入权限配置表aisc_user2careprov
			for(int i=0;i<careprovIds.length;i++){
				sv.saveUser2careprov(operId,operCode,careprovIds[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }



    @RequestMapping("/deleteAiscOperator2CareProv.ajax")
    @ResponseBody
    public Map deleteAiscOperator2CareProv(@RequestParam long id) throws Exception{
        Map map = new HashMap();
        boolean flag = sv.deleteOperator2CareProv(id);
        if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	
	
	/**
	 * 信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscOperator2CareProvdetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscOperator2CareProvBean aiscOperator2CareProvBean = null;
		AiscCareProvBean careProvBean = null;
		if(id!=0){
			aiscOperator2CareProvBean = sv.getAiscOperator2CareProv(id);
			careProvBean = careprovSv.getAiscCareProv(aiscOperator2CareProvBean.getCareprovId());
		}
		model.addAttribute("aiscOperator2CareProvBean",aiscOperator2CareProvBean);
		model.addAttribute("careProvBean",careProvBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscOperatorManage/aiscOperator2CareProvdetail";
    }
    
    //获取医护人员
    @RequestMapping("getMedicalStaff.ajax")
    @ResponseBody
    public Map getMedicalStaff(AiscLoginLoc model) throws Exception {
    	Map map = new HashMap();
    	//医护人员查询
		List<Map> careprovBean = loginSv.getCareprovlist(model);
        map.put("CareprovBean", JsonUtil.toJson(careprovBean));
        return map;
    }
    
    /**
	 * 根据操作工号重新加载医护人员列表
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getCareProvList.ajax")
	@ResponseBody
    public Map getCareProvList(@RequestParam long operator_id,String orgId) throws Exception {
		Map map = new HashMap();
		//已加入医护人员查询
        List<AiscOperator2CareProvModel> newCareList = sv.getNewCareList(operator_id,orgId);
        //未加入医护人员列表查询
        List<AiscOperator2CareProvModel> notJoinCare = sv.getNotJoinCare(operator_id,orgId);
        map.put("NotJoinCare", JsonUtil.toJson(notJoinCare));
        map.put("NewCareList", JsonUtil.toJson(newCareList));
        return map;
    }
}
