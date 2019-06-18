package com.ai.aris.web.controller.workstation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QueryCheckMasterModel;
import com.ai.aris.server.workstation.service.interfaces.ICheckMasterSV;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/checkMaster")
public class CheckMasterController {
	private Log logger = LogFactory.getLog(CheckMasterController.class);
	 private ICheckMasterSV sv = (ICheckMasterSV) ServiceFactory.getService(ICheckMasterSV.class);
	 private QryStudyInfoListModel model = new QryStudyInfoListModel();
	 private ICommonDataSV commsv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class); 
    //病人登记首面初始化
	@RequestMapping("checkMasterInit.html")
	public String checkMasterInit(Model model,HttpServletRequest request){	
		//检查设备
		try {
			String orgId = String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID"));
			String cookie_locId = "";
	        Cookie[] cookies = request.getCookies();
	        for(Cookie c :cookies ){
	            if("cookie_locId".equals(c.getName())){
	            	cookie_locId = c.getValue();
	            	break;
	            }
	        }
			String locId = cookie_locId;
		} catch (Exception e) {
			logger.error("CheckMasterController--checkMasterInit:", e); 
		}
		
		return "workstation/checkMasterInit";
	}	
	//查询检查列表记录
	@RequestMapping("/queryCheckList.ajax")
    @ResponseBody
    public JSONObject queryCheckList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QueryCheckMasterModel model,String orgId) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model,orgId,result);
        return AjaxUtil.jqGridJson(result);
    }

	//下拉列表加载
	@RequestMapping("/queryStudyItem.ajax")
    @ResponseBody
    public Map queryStudyItem(QueryCheckMasterModel model) throws Exception {
		Map map=new HashMap();
		List list= sv.queryStudyItem(model);
        map.put("itemInfo", list.size()!=0?JsonUtil.toJson(list.get(0)):"");
        return map;
    }
	
	//更新检查状态
	@RequestMapping(value="/updateCheck.ajax",method=RequestMethod.POST)
    @ResponseBody
    public Map updateCheck(QueryCheckMasterModel model){
		Map map=new HashMap();
	    try {
			sv.updateCheck(model);
		} catch (Exception e) {
			map.put("result", "failed");
			e.printStackTrace();
		}
        map.put("result", "success");
        return map;
    }
	
	
	 //申请医生列表查询
    @RequestMapping("getCareProv.ajax")
    @ResponseBody
    public JSONObject getCareProv(String orgId,String locId,String doctype) {    	   
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscCareProvBean[] careProvBeans = commsv.getCareProv(orgId,locId,doctype);			
			jsonObj.put("careProvBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(careProvBeans == null ? new AiscCareProvBean[0] : careProvBeans));
		    	
		} catch (Exception e) {
			logger.error("PatientRegController--getCareProv:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
    }
	
	//科室下拉列表加载
	@SuppressWarnings("unchecked")
	@RequestMapping("/getLocInfo.ajax")
    @ResponseBody
    public Map getLocInfo(String orgId) throws Exception {
		Map map = new HashMap();
		List<Map> locInfo = sv.getLocInfo(orgId);         
        map.put("LOC_INFO", JsonUtil.toJson(locInfo));
        return map;
    }		
	 
	
	public QryStudyInfoListModel getModel() {
		return model;
	}

	public void setModel(QryStudyInfoListModel model) {
		this.model = model;
	}
    
}
