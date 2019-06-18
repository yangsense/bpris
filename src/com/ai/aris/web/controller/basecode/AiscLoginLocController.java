package com.ai.aris.web.controller.basecode;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscLoginLocBean;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.model.AiscLoginLoc;
import com.ai.aris.server.basecode.service.interfaces.IAiscLoginLocSV;
import com.ai.aris.server.cornerstone.bean.BaseResponse;
import com.ai.aris.server.webservice.service.RestClient;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/basecode")
public class AiscLoginLocController {
	private IAiscLoginLocSV sv = (IAiscLoginLocSV) ServiceFactory.getService(IAiscLoginLocSV.class);
	@Autowired
	private RestClient restClient;
	@Value("${changeUserPath}")
	private String changeUserPath;
	/**
	 * 操作员登录科室设置页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscLoginLocInit")
    public String aiscLoginLocInit() {
        return "basecode/aiscLoginLoc/aiscLoginLocList";
    }
	
	/**
	 * 操作员登录科室设置查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscLoginLocList.ajax")
    @ResponseBody
    public JSONObject queryAiscLoginLocList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscLoginLoc model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 操作员登录科室权限设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/selectDataset")
	@ResponseBody
    public Map selectDataset(AiscLoginLoc model) throws Exception {
		Map map = new HashMap();
		//科室列表查询
        List<Map> AiscLoc = sv.getLocNameSelect(model);
        map.put("AiscLoc", JsonUtil.toJson(AiscLoc));
        return map;
    }
	
	/**
	 * 根据操作工号重新加载科室列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getLocList.ajax")
	@ResponseBody
    public Map getLocList(@RequestParam String operator_id,@RequestParam String orgId) throws Exception {
		Map map = new HashMap();
		//已加入科室列表查询
        List<AiscLoc> newLocList = sv.getNewLocList(operator_id,orgId);
        //未加入科室列表查询
        List<AiscLoc> notJoinLoc = sv.getNotJoinLoc(operator_id,orgId);
        map.put("NotJoinLoc", JsonUtil.toJson(notJoinLoc));
        map.put("NewLocList", JsonUtil.toJson(newLocList));
        return map;
    }
	
	/**
	 * 操作员登录科室设置详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscLoginLocDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscLoginLocBean aiscLoginLocBean = null;
		if(id!=0){
			 aiscLoginLocBean = sv.getAiscLoginLoc(id);
		}
		model.addAttribute("aiscLoginLocBean",aiscLoginLocBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscLoginLoc/aiscLoginLocDetail";
    }

	/**
	 * 操作员修改密码
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeUserPd.html")
	public String changeUserPd(@RequestParam String operatorName,@RequestParam String operatorCode,Model model, HttpServletRequest request) throws Exception {
		
		//解决前台传过来operatorName为乱码
		String bname = request.getParameter("operatorName"); 
		bname = new String(bname .getBytes("iso8859-1"),"utf-8");
		System.out.print(bname);
		model.addAttribute("operatorName",bname);
		model.addAttribute("operatorCode",operatorCode);
		return "basecode/aiscLoginLoc/changeUserPd";
	}

	/**
	 * 保存修改密码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUserPd")
	@ResponseBody
	public Map saveUserPd( String operatorCode,String password,String newPassword) throws Exception {
		Map<String,String> map = new HashMap();
		map.put("ERRCODE", "0");
		try {
			map.put("password", password);
			map.put("operatorCode", operatorCode);
			map.put("newPassword", newPassword);
			BaseResponse response = restClient.retrieveData(changeUserPath,map,BaseResponse.class);
			map.put("ERRCODE", response.getCode());
			map.put("ERRMSG", response.getMessage());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return map;
	}
	/**
	 * 新增或修改操作员登录科室设置
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscLoginLoc")
	@ResponseBody
    public Map saveAiscLoginLoc(@RequestParam String operatorId,@RequestParam String orgId,@RequestParam String selectOption) throws Exception {
		String operId = operatorId.substring(0,operatorId.indexOf("||"));
		String operCode = operatorId.substring(operatorId.indexOf("||")+2);
		try{
			String loc[] = selectOption.split(",");
			sv.deleLoginLoc(operId,orgId);
			//插入登录科室表aisc_loginloc
			for(int i=0;i<loc.length;i++){
				sv.saveLoginLoc(operId,orgId,loc[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

	/**
	 * 删除操作员与科室关联
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisLoginLoc.ajax")
	@ResponseBody
    public Map deleteAisLoginLoc(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscLoginLoc(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	
}
