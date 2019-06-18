package com.ai.aris.web.controller.workstation;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscEquipmentBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.workstation.bean.AiscPeriodBean;
import com.ai.aris.server.workstation.bean.QryStudyInfoListBean;
import com.ai.aris.server.workstation.model.QryPacsInfoModel;
import com.ai.aris.server.workstation.model.QryRisInfoModel;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.model.QryStudyPatientModel;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyLockSV;
import com.ai.aris.server.workstation.service.interfaces.IWorkListSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.DateUtils;
import com.ai.common.util.JsonUtil;

@Controller
@RequestMapping("/workList")
public class WorkListController {
	private Log logger = LogFactory.getLog(WorkListController.class);
	private IWorkListSV sv = (IWorkListSV) ServiceFactory
			.getService(IWorkListSV.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	private IStudyLockSV studyLockSV = (IStudyLockSV) ServiceFactory.getService(IStudyLockSV.class);
	private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
	private QryStudyInfoListModel model = new QryStudyInfoListModel();
	@Value("${pacsViewPath}")
    private String pacsViewPath;
	
	// 工作列表首面初始化
	@RequestMapping("init.html")
	public String workListInit(HttpServletRequest request,Model model){
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		SysOrgBean org = (SysOrgBean) (request.getSession().getAttribute(Constants.SESSION_ORG_OBJ));
		//查科室
		//String orgId = String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID"));
		try {
			//TODO 自动解锁相关记录
			studyLockSV.autoUnLockStudyReport(user.getOperatorId());
			//查科室
			//AiscLocBean[] locBeans = sv.getLocInfos(orgId);
			//model.addAttribute("locBeans", locBeans);
			//远程会诊是否需要审核开关
            DictItemBean[] dictBean = dictSV.queryDictItem("HZ_IS_FLAG");
            if (dictBean != null && dictBean.length == 1) {
                model.addAttribute("HZ_IS_FLAG", dictBean[0].getItemNo());
            }
            //是否显示医院源病人ID
            DictItemBean[] YPatientIdFlag = dictSV.queryDictItem("PATIENTID_FLAG");
            if (YPatientIdFlag != null && YPatientIdFlag.length == 1) {
                model.addAttribute("PATIENTID_FLAG", dictBean[0].getItemNo());
            }
			//从cookie中取科室信息
	        String cookie_locId = "";
	        Cookie[] cookies = request.getCookies();
	        for(Cookie c :cookies ){
	            if("cookie_locId".equals(c.getName())){
	            	cookie_locId = c.getValue();
	            }
	            if(c.getName().equals("oldstarttime")){
            		model.addAttribute("oldstarttime",c.getValue().trim());
            	}
            	if(c.getName().equals("oldendtime")){
            		model.addAttribute("oldendtime",c.getValue().trim()); 
            	}
            	if(c.getName().equals("oldstudyAccnum")){
            		model.addAttribute("oldstudyAccnum",c.getValue().trim());
            	}
            	if(c.getName().equals("oldstudystutascode")){
            		model.addAttribute("oldstudystutascode",c.getValue().trim().replace("'", "")); 
            	}
            	if(c.getName().equals("oldstudytype")){
            		model.addAttribute("oldstudytype",c.getValue().trim());
            	}
            	if(c.getName().equals("oldqType")){
            		model.addAttribute("oldqType",c.getValue().trim());
            	}
            	if(c.getName().equals("oldqValue")){
            		model.addAttribute("oldqValue",URLDecoder.decode(c.getValue().trim(),"UTF-8"));
            	}
            	if(c.getName().equals("oldModalityId")){
            		model.addAttribute("oldModalityId",c.getValue().trim());
            	}
            	if(c.getName().equals("oldCheckTimeMark")){
            		model.addAttribute("oldCheckTimeMark",c.getValue().trim());
            	}
	        }
	        model.addAttribute("cookie_locId", cookie_locId);
	        long loginlocId = Long.parseLong(sv.getLoginloc(user.getOperatorId(),org.getOrgId()));
	        //取cookie科室，如果为空，则取默认登录科室
	        String locType = ("".equals(cookie_locId)||"undefined".equals(cookie_locId))==true?sv.getLocType(loginlocId):sv.getLocType(Long.parseLong(cookie_locId));
	        model.addAttribute("locType", locType);
	        
	        //按钮权限控制
			SysMenuBean[] buttons = (SysMenuBean[])request.getSession().getAttribute(Constants.SESSION_BUTTON);
	        for(SysMenuBean m : buttons){
            	if("AUDIT_REPORT".equals(m.getMenuUrl())){
            		model.addAttribute("AUDIT_REPORT_BUTTON", "y");
            	}else if("WRITING_REPORT".equals(m.getMenuUrl())){
            		model.addAttribute("WRITING_REPORT_BUTTON", "y");
            	}else if("CONSULT_ROLE".equals(m.getMenuUrl())){
            		model.addAttribute("CONSULT_ROLE", "y");
            	}else if("DIAGNOSE_ROLE".equals(m.getMenuUrl())){
            		model.addAttribute("DIAGNOSE_ROLE", "y");
            	}else if("CANCELREG".equals(m.getMenuUrl())){
            		model.addAttribute("CANCELREG", "y");
            	}else if("STUDY_REG".equals(m.getMenuUrl())){
            		model.addAttribute("STUDY_REG", "y");
            	}else if("VIEW_IMAGE".equals(m.getMenuUrl())){
            		model.addAttribute("VIEW_IMAGE", "y");
            	}else if("MY_ALLOT_REG".equals(m.getMenuUrl())){
            		model.addAttribute("MY_ALLOT_REG", "y");
            	}else if("READ_CARD".equals(m.getMenuUrl())){
            		model.addAttribute("READ_CARD", "y");
            	}else if("IMAGE_QC".equals(m.getMenuUrl())){
            		model.addAttribute("IMAGE_QC", "y");
            	}else if("QUERY_CHECK".equals(m.getMenuUrl())){
            		model.addAttribute("QUERY_CHECK", "y");
            	}else if("VIDEO_CONSULTATION".equals(m.getMenuUrl())){
            		model.addAttribute("VIDEO_CONSULTATION", "y");
            	}else if("HZSQ_ROLE".equals(m.getMenuUrl())){
            		model.addAttribute("HZSQ_ROLE", "y");
            	}               	
            }
	        
	        model.addAttribute("pacsViewPath", pacsViewPath);
		} catch (Exception e) {
			logger.error("WorkListController->workListInit error!" + e.getMessage());			 
		} 		




		String userId = user.getOperatorId();
		model.addAttribute("userId", userId);
		model.addAttribute("careId", user.getCareProvId());
		return "workstation/worklist";
	}

	// 查询工作列表记录
	@RequestMapping("/queryWorkList.ajax")
	@ResponseBody	
	public JSONObject queryWorkList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String sidx,String sord,
			QryStudyInfoListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		model.setDoctorId(user.getCareProvId());
		// 取员工组织机构
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryPageList(model, result,sidx,sord);
		//记录上一次查询时间
		Cookie c1 = new Cookie("oldstarttime",model.getStartTime());
		Cookie c2 = new Cookie("oldendtime",model.getEndTime());
		Cookie c3 = new Cookie("oldstudyAccnum",model.getStudyAccnumber());
		Cookie c4 = new Cookie("oldstudystutascode",model.getStudystatusCode());
		Cookie c5 = new Cookie("oldstudytype",model.getStudyType()+"");
		Cookie c6 = new Cookie("oldqType",model.getqType());
		Cookie c7 = new Cookie("oldqValue",URLEncoder.encode(model.getqValue(),"UTF-8"));
		Cookie c8 = new Cookie("oldModalityId",String.valueOf(model.getModalityId()));
		Cookie c9 = new Cookie("oldCheckTimeMark",model.getIsDate());
		c1.setMaxAge(-1);
		c1.setPath(request.getContextPath());
		c2.setMaxAge(-1);
		c2.setPath(request.getContextPath());
		c3.setMaxAge(-1);
		c3.setPath(request.getContextPath());
		c4.setMaxAge(-1);
		c4.setPath(request.getContextPath());
		c5.setMaxAge(-1);
		c5.setPath(request.getContextPath());
		c6.setMaxAge(-1);
		c6.setPath(request.getContextPath());
		c7.setMaxAge(-1);
		c7.setPath(request.getContextPath());
		c8.setMaxAge(-1);
		c8.setPath(request.getContextPath());
		c9.setMaxAge(-1);
		c9.setPath(request.getContextPath());
		response.addCookie(c1);
		response.addCookie(c2);
		response.addCookie(c3);
		response.addCookie(c4);
		response.addCookie(c5);
		response.addCookie(c6);
		response.addCookie(c7);
		response.addCookie(c8);
		response.addCookie(c9);
		return AjaxUtil.jqGridJson(result);
	}
	
	// 工作列表2（乡镇使用）
	@RequestMapping("villageTownInit.html")
	public String villageTownInit(HttpServletRequest request,Model model) throws Exception{
		AiscPeriodBean[] periods = commonDataSv.getPeriodInfo();
		//判断当前时间
		Date date = new Date();
		String periodStartTime = ""; 
		for(int i=0;i<periods.length;i++){
			if(DateUtils.isInDate(date,periods[i].getPeriodStarttime(),periods[i].getPeriodEndtime())){
				periodStartTime = periods[i].getPeriodStarttime();
			}
		}
		model.addAttribute("periods", periods);
		model.addAttribute("periodStartTime",periodStartTime);
		//病人类型
		DictItemBean[] patientTypes = dictSV.queryDictItem("PATIENT_TYPE");
		SysOrgBean org = (SysOrgBean) (request.getSession().getAttribute(Constants.SESSION_ORG_OBJ));
		//申请科室
		AiscLocBean[] locBeans = commonDataSv.getLocInfo(org.getOrgId(),"A");//Loc_Type=A 科室
		if(locBeans==null){
			locBeans = new AiscLocBean[1];
			locBeans[0] = new AiscLocBean();
		}
		//病区
		AiscLocBean[] studyWards = commonDataSv.getLocInfo(org.getOrgId(),"B");//Loc_Type=B 病区
		//检查设备
		AiscLocBean[] loc = commonDataSv.getLocInfo(org.getOrgId(),"E");//Loc_Type=E 执行科室
		if(loc==null){
			loc = new AiscLocBean[1];
			loc[0] = new AiscLocBean();
		}
		long locId = loc[0].getLocId();
		AiscEquipmentBean[] equipmentBeans = commonDataSv.getEquipment(org.getOrgId(),String.valueOf(locId));
		//检查等级
		DictItemBean[] studyLevels = dictSV.queryDictItem("STUDY_LEVEL");
		//收费类型
		DictItemBean[] paymentTypes = dictSV.queryDictItem("PAYMENT_TYPE");
		model.addAttribute("paymentTypes", paymentTypes);
		model.addAttribute("studyLevels", studyLevels);
		model.addAttribute("equipmentBeans", equipmentBeans);
		model.addAttribute("studyWards", studyWards);
		model.addAttribute("studyApplocs", locBeans);
		model.addAttribute("patientTypes", patientTypes); 
//		model.addAttribute("isNew", isNew);
		model.addAttribute("locId", locId); 
//		model.addAttribute("queryKey", jcHisQryType); 
		DictItemBean[] dictBean = dictSV.queryDictItem("HZ_IS_FLAG");
        if (dictBean != null && dictBean.length == 1) {
            model.addAttribute("HZ_IS_FLAG", dictBean[0].getItemNo());
        }
		SysMenuBean[] buttons = (SysMenuBean[])request.getSession().getAttribute(Constants.SESSION_BUTTON);
        for(SysMenuBean m : buttons){
	     	if("DIAGNOSE_ROLE".equals(m.getMenuUrl())){
	     		model.addAttribute("DIAGNOSE_ROLE", "y");
	     	}else if("CONSULT_ROLE".equals(m.getMenuUrl())){
        		model.addAttribute("CONSULT_ROLE", "y");
        	}
        }
		return "workstation/villageTownInit";
	}
	
	// 工作列表2（左下查询列表）
	@RequestMapping("/queryStudyList.ajax")
	@ResponseBody
	public JSONObject queryStudyList(@RequestParam(value = "page", defaultValue = "1") int page,
										  @RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
										  QryStudyPatientModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryStudyList(model, result);
		return AjaxUtil.jqGridJson(result);
	}
	
	// 工作列表2（右下查询列表）
	@RequestMapping("/queryStudyRightDownList.ajax")
	@ResponseBody
	public JSONObject queryStudyRightDownList(@RequestParam(value = "page", defaultValue = "1") int page,
										  @RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
										  QryStudyInfoListModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryStudyRightDownList(model, result);
		return AjaxUtil.jqGridJson(result);
	}
	
	// 查询列表打印记录
	@RequestMapping("/queryPrintWorkList.ajax")
	@ResponseBody	
	public JSONObject queryPrintWorkList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String sidx,String sord,
			QryStudyInfoListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		model.setDoctorId(user.getCareProvId());
		// 取员工组织机构
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryPagePrintList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
		
	/**
	 * 统计记录数
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryRecords.ajax")
	@ResponseBody	
	public JSONObject queryRecords(QryStudyInfoListModel model) throws Exception{
		JSONObject jsonObj = new JSONObject();
		List<Map> list = sv.queryRecords(model);
		int total = 0 ;
		for(Map map:list){
			if(map.get("studystatus_code").equals("ARRIVE")){
				jsonObj.put("ARRIVE", map.get("record_num"));
			}
			if(map.get("studystatus_code").equals("VERIFY")){
				jsonObj.put("VERIFY", map.get("record_num"));
			}
			if(map.get("studystatus_code").equals("HAVERPT")){
				jsonObj.put("HAVERPT", map.get("record_num"));
			}
			if(map.get("studystatus_code").equals("APPConsult")){
				jsonObj.put("APPConsult", map.get("record_num"));
			}
			total+=Integer.parseInt(map.get("record_num").toString());
		}
		Map printMap = sv.queryPrintRecords(model);
		jsonObj.put("PRINT", printMap.get("print_num"));
		jsonObj.put("total", total);
		return jsonObj;
	}
	
	// 查询工作列表消息，右下角
	@RequestMapping("/getMessage.ajax")
	@ResponseBody	
	public JSONObject getMessage(String orgId,String locId,String userId,HttpServletRequest request)throws Exception { 
		JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try { 
			//User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
			//String userId = user.getOperatorId();
		    Map map = sv.getMessage(orgId,locId,userId); 			
		    jsonObj.put("diagnoseTotal",  map.get("diagnoseTotal"));
		    jsonObj.put("consultTotal",  map.get("consultTotal"));		
		    jsonObj.put("diagnoseVerifyTotal",  map.get("diagnoseVerifyTotal"));
		    jsonObj.put("consultVerifyTotal",  map.get("consultVerifyTotal"));	
		} catch (Exception e) {
			jsonObj.put("success", false);
			logger.error("WorkListController--getMessage.ajax:", e);
			e.printStackTrace();
		}       
	    return jsonObj; 
	}	
	//点击会诊申请时修改报告状态为已阅读
	@RequestMapping("/saveReprtStatus.ajax")
	@ResponseBody	
	public JSONObject saveReprtStatus(String orgId,String locId,String userId,HttpServletRequest request)throws Exception { 
		JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try { 
		   sv.saveReprtStatus(orgId,locId,userId); 			
		} catch (Exception e) {
			jsonObj.put("success", false);
			e.printStackTrace();
		}       
	    return jsonObj; 
	}
	//点击已审核报告时修改状态为已阅读
	@RequestMapping("/saveReportStutusVerrify.ajax")
	@ResponseBody	
	public JSONObject saveReportStutusVerrify(String orgId,String locId,String userId,HttpServletRequest request)throws Exception { 
		JSONObject jsonObj = new JSONObject();
    	jsonObj.put("success", true);
		try { 
		   sv.saveReportStutusVerrify(orgId,locId,userId); 			
		} catch (Exception e) {
			jsonObj.put("success", false);
			e.printStackTrace();
		}       
	    return jsonObj; 
	}	
	 
	// 查看工作列表详情
	@RequestMapping("/getWorkListDetail.html")
	public String getWorkListDetail(@RequestParam String studyinfoId,
			Model model) throws Exception {

		QryStudyInfoListBean studyInfoBean = sv.getWorkListDetail(studyinfoId);
//		studyInfoBean.setReportDatetime(studyInfoBean.getReportDatetime().equals("a")?"":studyInfoBean.getReportDatetime());
//		studyInfoBean.setReportDoctorname(studyInfoBean.getReportDoctorname().equals("a")?"":studyInfoBean.getReportDoctorname());
//		studyInfoBean.setReportVerifydoctorname(studyInfoBean.getReportVerifydoctorname().equals("a")?"":studyInfoBean.getReportVerifydoctorname());
//		studyInfoBean.setReportFinaldoctorname(studyInfoBean.getReportFinaldoctorname().equals("a")?"":studyInfoBean.getReportFinaldoctorname());
		model.addAttribute("studyInfoBean", studyInfoBean);
		return "workstation/worklistDetail";
	}

	// 检查状态下拉列表加载
	@RequestMapping("/getStudyStatus.ajax")
	@ResponseBody
	public Map getStudyStatus(String orgId,String locId) throws Exception {
//		List<Map> status = dictSV.queryDict("STUDY_STATUS");
		Map map = new HashMap();
		List<Map> status = sv.getAiscLocStatus(locId,orgId);
		map.put("STUDY_STATUS", JsonUtil.toJson(status));
		return map;
	}

	// 科室下拉列表加载 
	@RequestMapping("/getLocInfo.ajax")
	@ResponseBody
	public Map getLocInfo(String orgId, Model model, HttpServletRequest request)
			throws Exception {
		Map map = new HashMap();
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		String operatorId = user.getOperatorId();		
		List<Map> locInfo = sv.getLocInfo(orgId,operatorId);
		map.put("LOC_INFO", JsonUtil.toJson(locInfo));
		return map;
	}
	
	// 检查设备加载 
	@RequestMapping("/getEquimentByLocId.ajax")
	@ResponseBody
	public Map getEquimentByLocId(String orgId,String locId,Model model, HttpServletRequest request)
			throws Exception {
		List<Map> status = dictSV.queryDict("AISC_MODALITY");
		Map map = new HashMap();
		map.put("MODALITY_TYPE", JsonUtil.toJson(status));
		return map;
	}

	// 设置科室cookie
	@RequestMapping("/setLocCookie.ajax")
	@ResponseBody
	public Map setLocCookie(String locId, HttpServletResponse response,
			HttpServletRequest request, HttpSession httpSession, Model model)
			throws Exception {
		Map map = new HashMap();
		if (!"-1".equals(locId) && locId != null&& !"undefined".equals(locId)) {
			// 放cookie
			Cookie c2 = new Cookie("cookie_locId", locId);
			response.addCookie(c2);
			httpSession.setAttribute("all_cookie_locId", locId);
			String locType = sv.getLocType(Long.parseLong(locId));
			map.put("locType", locType);
		}
		return map;
	}
	
	// 工作列表首面初始化
	@RequestMapping("imageControl.html")
	public String imageControl(HttpServletRequest request,Model model,HttpSession httpSession){
		return "workstation/imageControl";
	}
	
	/**
	 * 设备类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscEquipType.ajax")
    @ResponseBody
    public Map getAiscEquipType() throws Exception {
		List<Map> aiscEquipType = dictSV.queryDict("AISC_MODALITY");
        Map map = new HashMap();
        map.put("AiscEquipType", JsonUtil.toJson(aiscEquipType));
        return map;
    }
	
	// 查询Ris列表记录
	@RequestMapping("/queryRisList.ajax")
	@ResponseBody	
	public JSONObject queryRisList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,String orgId,QryRisInfoModel model, HttpServletRequest request)throws Exception { 
		ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryRisInfoList(model, result);
        return AjaxUtil.jqGridJson(result);
	}
	
	
	// 查询PACS列表记录
	@RequestMapping("/queryPacsList.ajax")
	@ResponseBody	
	public JSONObject queryPacsList(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,QryPacsInfoModel model, HttpServletRequest request)
			throws Exception { 
		ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPacsInfoList(model, result);
        return AjaxUtil.jqGridJson(result);
	}
	
	/**
	 * 更改影像匹配信息
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateControlInfo.ajax")
	@ResponseBody
    public Map updateControlInfo(long  patientkey,String accessionnumber,String patientid) throws Exception {
		sv.updateControlInfo(patientkey,accessionnumber,patientid);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

	public QryStudyInfoListModel getModel() {
		return model;
	}

	public void setModel(QryStudyInfoListModel model) {
		this.model = model;
	}

}
