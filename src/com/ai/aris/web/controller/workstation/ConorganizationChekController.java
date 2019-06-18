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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.workstation.bean.AisStudyInfoBean;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QueryCheckMasterModel;
import com.ai.aris.server.workstation.service.interfaces.ICheckConOrgSV;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/conorgCheck")
public class ConorganizationChekController {
	private Log logger = LogFactory.getLog(ConorganizationChekController.class);
	 //private IWorkListSV sv = (IWorkListSV) ServiceFactory.getService(IWorkListSV.class);
	 private ICheckConOrgSV sv = (ICheckConOrgSV) ServiceFactory.getService(ICheckConOrgSV.class);
	 private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	 private QryStudyInfoListModel model = new QryStudyInfoListModel();
	 private ICommonDataSV commsv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class); 
    //会诊审核界面初始化
	@RequestMapping("conorganizationCheck.html")
	public String conorganizationCheck(Model model,HttpServletRequest request){	
		//从cookie中取科室信息
        String cookie_locId = "";
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
        	for(Cookie c :cookies ){
                if("cookie_locId".equals(c.getName())){
                	cookie_locId = c.getValue();
                	break;
                }
            } 
        }        
        model.addAttribute("cookie_locId", cookie_locId);
		//按钮权限控制
		SysMenuBean[] buttons = (SysMenuBean[])request.getSession().getAttribute(Constants.SESSION_BUTTON);
        for(SysMenuBean m : buttons){
        	if("VERIFYROLE".equals(m.getMenuUrl())){
        		model.addAttribute("VERIFYROLE", "y");
        	}
        }
		return "workstation/conorganizationCheck";
	}	
	//会诊申请记录退回
	@RequestMapping("conorgCheckrefuse.html")
	public String conorganizationCheck(Model model,String studyinfoId){	
		model.addAttribute("studyinfoId",studyinfoId);
		return "workstation/conorgCheckrefuse";
	}
	
	@RequestMapping("/refuseApply")
	@ResponseBody
    public Map refuseApply(@RequestBody AisStudyInfoBean bean) throws Exception {
		Map map = new HashMap();
		try {
			bean.setStudystatusCode("RejectApp");
			sv.updateRefuseStatus(bean);
			map.put("ERRCODE",0);
		} catch (Exception e) {
			map.put("ERRINFO","退回申请失败");
		}
        return map;
    }
	
	@RequestMapping("/hzApply.ajax")
	@ResponseBody
    public JSONObject hzApply(@RequestBody AisStudyInfoBean bean) throws Exception {
		JSONObject jsonObj = new JSONObject();
		try {
			bean.setStudystatusCode("AppVerify");
			sv.updateRefuseStatus(bean);
			jsonObj.put("result", "success");
			//短信发送
			//。。。。
			
		} catch (Exception e) {
			jsonObj.put("result","error");
		}
        return jsonObj;
    }
	
	//查询会诊记录列表
	@RequestMapping("/conorganizationCheckList.ajax")
    @ResponseBody
    public JSONObject conorganizationCheckList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QueryCheckMasterModel model,String orgId) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model,orgId,result);
        return AjaxUtil.jqGridJson(result);
    }
	
	//检查状态下拉列表加载
	@RequestMapping("/getStudyStatus.ajax")
    @ResponseBody
    public Map getStudyStatus() throws Exception {
		List<Map> status = dictSV.queryDict("STUDY_STATUS");
        Map map = new HashMap();
        map.put("STUDY_STATUS", JsonUtil.toJson(status));
        return map;
    }

	//检查状态下拉列表加载
	@RequestMapping("/queryStudyItem.ajax")
    @ResponseBody
    public Map queryStudyItem(QueryCheckMasterModel model) throws Exception {
		Map map=new HashMap();
		List list= sv.queryStudyItem(model);
        map.put("itemInfo", list.size()!=0?JsonUtil.toJson(list.get(0)):"");
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
