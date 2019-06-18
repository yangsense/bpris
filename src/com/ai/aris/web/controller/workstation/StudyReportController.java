package com.ai.aris.web.controller.workstation;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ai.appframe2.bo.SysdateManager;
import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscUserPhraseBean;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.workstation.bean.AisFilesInfoBean;
import com.ai.aris.server.workstation.bean.AisKnowledgebaseBean;
import com.ai.aris.server.workstation.bean.AisPatientInfoBean;
import com.ai.aris.server.workstation.bean.AisReportUploadBean;
import com.ai.aris.server.workstation.bean.AisStudyItemInfoBean;
import com.ai.aris.server.workstation.bean.AisStudyReportBean;
import com.ai.aris.server.workstation.bean.AisStudyreportTempBean;
import com.ai.aris.server.workstation.bean.AiscStudyLockBean;
import com.ai.aris.server.workstation.bean.QryRegInfoBean;
import com.ai.aris.server.workstation.bean.QryReportBrowseBean;
import com.ai.aris.server.workstation.bean.QryReportFileBean;
import com.ai.aris.server.workstation.bean.QryReportHBean;
import com.ai.aris.server.workstation.bean.QryTemplateTreeBean;
import com.ai.aris.server.workstation.model.AisKnowledgebaseModel;
import com.ai.aris.server.workstation.model.TemplateContent;
import com.ai.aris.server.workstation.service.interfaces.IPatientRegSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyLockSV;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.FTPSUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/studyReport")
public class StudyReportController {

    private Log logger = LogFactory.getLog(StudyReportController.class);
    private IStudyReportSV sv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
    private IPatientRegSV patientSV = (IPatientRegSV) ServiceFactory.getService(IPatientRegSV.class);
	private IStudyLockSV studyLockSV = (IStudyLockSV) ServiceFactory.getService(IStudyLockSV.class);
    private ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
            .getService(IDictItemSV.class);
    @Value("${pacsViewPath}")
    private String pacsViewPath;

    @Value("${webservice.host}")
    private String healthHost;
    @Value("${webservice.port}")
    private String healthPort;


    @RequestMapping("test.ht")
    public String test(String studyinfoId, String patientGlobalid, String locId, String rtype, String isReadonly, Model model, HttpServletRequest request) {

        return "test";
    }

    @RequestMapping("init.html")
    public String patientRegInit(String studyinfoId, String patientGlobalid, String locId, String rtype, String isReadonly, String modalityId, Model model,String orgId, HttpServletRequest request) {
        try {
        	//检查登记信息
            QryRegInfoBean regInfoBean = patientSV.getRegInfo(studyinfoId);
        	//操作记录
            User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        	//TODO 打开报告时候上锁
			AiscStudyLockBean studyLockBean = studyLockSV.getBeanById(regInfoBean.getStudyinfoId());
			boolean isNew = false;
			if(null==studyLockBean){
				isNew = true;
				studyLockBean = new AiscStudyLockBean();
				studyLockBean.setStudyinfoId(regInfoBean.getStudyinfoId());
			}
			studyLockBean.setLockIp(request.getRemoteAddr());
			studyLockBean.setLockStatus("1");//锁定
			studyLockBean.setUserId(user.getOperatorId());
			studyLockBean.setUserName(user.getOperatorName());
			studyLockBean.setStartTime(new Timestamp(System.currentTimeMillis()));
			studyLockSV.saveAiscStudyLock(studyLockBean,isNew);
        	
        	//通过书写报告进来，如果是会诊申请，需要修改报告状态status_code
        	/**
        	 *通过病人 patientGlobalid，查询病人studyinfo_id,并修改ais_studymessage表状态
        	 */
        	boolean isSaveSuccess = patientSV.updateAisStudyMessage(patientGlobalid,orgId);
        	if(!isSaveSuccess){
                logger.error("保存ais_studymessage出错");
        	}
            //是否阴阳性开关提示
            DictItemBean[] dictBean = dictSV.queryDictItem("ON_OFF");
            if (dictBean != null && dictBean.length == 1) {
                model.addAttribute("ON_OFF", dictBean[0].getItemNo());
            }
            model.addAttribute("regInfoBean", regInfoBean);
            model.addAttribute("studyinfoId", studyinfoId);
            model.addAttribute("patientGlobalid", patientGlobalid);
            model.addAttribute("rtype", rtype);
            model.addAttribute("locId", locId);
            model.addAttribute("modalityId", modalityId);
            Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("oldstarttime")) {
                        model.addAttribute("oldstarttime", cookie.getValue().trim());
                    }
                    if (cookie.getName().equals("oldendtime")) {
                        model.addAttribute("oldendtime", cookie.getValue().trim());
                    }
                }
            }
            
            //如果状态是审核通过并且是远程会诊，则更新状态为会诊开始
            if("AppVerify".equals(regInfoBean.getStudystatusCode())&&regInfoBean.getStudyType()==1){
            	sv.updateStudyInfo("Consulting",Long.parseLong(studyinfoId));
            }
            
            //查询相关检查，供所有影像按钮使用
            String patientIds = "";
            QryRegInfoBean[] regInfoBeans = sv.getRelCheck(patientGlobalid, studyinfoId,regInfoBean.getPatientInpatientid(),regInfoBean.getPatientIdnumber());
            if(regInfoBeans!=null&&regInfoBeans.length>0){
	            for(QryRegInfoBean regInfo:regInfoBeans){
	            	patientIds += regInfo.getPatientId()+"^";
	            }
	            model.addAttribute("patientIds", patientIds.substring(0,patientIds.length()-1));
            }else{
            	model.addAttribute("patientIds", patientIds);
            }
            
            //查询部位信息
            AisStudyItemInfoBean[] itemBean = patientSV.getItemInfo(studyinfoId);
            String str = "";
            if (itemBean != null && itemBean.length > 0) {
                for (int a = 0; a < itemBean.length; a++) {
                    str += itemBean[a].getStudyitemBodyinfo() == null ? "" : itemBean[a].getStudyitemBodyinfo();
                }
                model.addAttribute("itemBodyInfo", str);
            }

            //有报告，需要查出来
            if (regInfoBean.getStudyHavereport() == 1) {
                AisStudyReportBean reportBean = sv.getReport(studyinfoId);
                model.addAttribute("reportBean", reportBean);
                model.addAttribute("reportDocId", reportBean.getReportDoctorid());
                model.addAttribute("reportVerifydocId", reportBean.getReportVerifydoctorid());
                model.addAttribute("reportFinalDocId", reportBean.getReportFinaldoctorid());
                model.addAttribute("careId", user.getCareProvId());
                patientSV.saveStudyhistoryinfo(String.valueOf(reportBean.getStudyinfoId()), user.getOperatorId(), "Consulted");
                
                model.addAttribute("oldreportRemark", reportBean.getReportRemark());
                model.addAttribute("ispositive", reportBean.getReportIspositive());

                String status = "0";
                if (reportBean.getReportFinaldoctorid() != null && !"".equals(reportBean.getReportFinaldoctorid())) {
                    status = "1";
                }
                QryReportFileBean reportFileBean = sv.getReportPath(String.valueOf(reportBean.getReportId()), status);
                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + (reportFileBean == null ? "" : reportFileBean.getFilePath());
                model.addAttribute("filePath", contextPath);

            }

            //按钮权限控制
            SysMenuBean[] buttons = (SysMenuBean[]) request.getSession().getAttribute(Constants.SESSION_BUTTON);
            for (SysMenuBean m : buttons) {
                if ("AUDIT_REPORT".equals(m.getMenuUrl())) {
                    model.addAttribute("AUDIT_REPORT_BUTTON", true);
                } else if ("WRITING_REPORT".equals(m.getMenuUrl())) {
                    model.addAttribute("WRITING_REPORT_BUTTON", true);
                } else if ("DIAGNOSE_ROLE".equals(m.getMenuUrl())) {
                    model.addAttribute("DIAGNOSE_ROLE", true);
                } else if ("CONSULT_ROLE".equals(m.getMenuUrl())) {
                    model.addAttribute("CONSULT_ROLE", true);
                } else if ("PUBLIC_TEMPLATE_MANAGE".equals(m.getMenuUrl())) {
                    model.addAttribute("PUBLIC_TEMPLATE_MANAGE", true);
                } else if ("VIEW_IMAGE".equals(m.getMenuUrl())) {
                    model.addAttribute("VIEW_IMAGE", true);
                } else if ("GET_HEALTH".equals(m.getMenuUrl())) {
                    model.addAttribute("GET_HEALTH", true);
                } else if ("VIEW_APPLY".equals(m.getMenuUrl())) {
                    model.addAttribute("VIEW_APPLY", true);
                } else if ("VIEW_IMGPRINT".equals(m.getMenuUrl())) {
                    model.addAttribute("VIEW_IMGPRINT", true);
                } else if ("FINAL_AUDIT_REPORT".equals(m.getMenuUrl())) {
                    model.addAttribute("FINAL_AUDIT_BUTTON", true);
                } else if ("PRIVATE_TEMPLATE_MANAGE".equals(m.getMenuUrl())) {
                    model.addAttribute("PRIVATE_TEMPLATE_MANAGE", true);
                } else if ("PRINT_VIEW".equals(m.getMenuUrl())) {
                    model.addAttribute("PRINT_VIEW", true);
                } else if ("STRAIGHT_PRINT".equals(m.getMenuUrl())) {
                    model.addAttribute("STRAIGHT_PRINT", true);
                }else if ("AI_MANAGE".equals(m.getMenuUrl())) {
                    model.addAttribute("AI_MANAGE", true);
                }else if ("FILE_UPLOAD".equals(m.getMenuUrl())) {
                    model.addAttribute("FILE_UPLOAD", true);
                }
                
            }

            model.addAttribute("pacsViewPath", pacsViewPath);
            model.addAttribute("studyConsultorg", regInfoBean.getStudyConsultorg());
            model.addAttribute("studyConsultloc", regInfoBean.getStudyConsultloc());

            model.addAttribute("appLoc", regInfoBean.getApplocname());
            model.addAttribute("appOrg", regInfoBean.getOrgName());
            model.addAttribute("isReadonly", isReadonly);
            //阅读报告
            if (null != isReadonly && isReadonly.equals("y")) {
                //消息队列记录
                patientSV.saveStudyMessage(String.valueOf(regInfoBean.getStudyinfoId()), user.getOperatorId(), "READ", regInfoBean.getStudyType());
            }

            //健康档案调阅URL
            String healthIp = "";
            if (null != healthPort) {
                healthIp += healthHost + ":" + healthPort;
            }
            model.addAttribute("healthIp", healthIp);

            //读取影像目录下的影像图片
//            String imgUpPath = AIConfigManager.getConfigItem("ImgUpPath");
//	        String prePath = com.ai.common.util.WebUtils.getBasePath(request) ;
//	        prePath = prePath.replace("Aris", "FileServer/aris/imgUpload/") ;
//	        String contextPath = prePath +reportFileBean.getFilePath();

            //获取病人ID+检查号作为目录
//            String dir = "C:/Windows/AIPACS/Forumpath/" + regInfoBean.getPatientId() + "_" + regInfoBean.getStudyAccnumber() + "/";
//            File file = new File(dir);
//            List list = new ArrayList();

//	        String path="C:/Windows/AIPACS/Forumpath/"+dir+"/";
//	        File file=new File(path);
//            String url = com.ai.common.util.WebUtils.getBasePath(request) + "/studyReport/showImg?imgFile=";

//	        if((!file .exists()  && !file .isDirectory())){
//	        	file.mkdirs();
//	        	//本地影像不存在，从服务器读取
//				FTPSUtil ftpUtil =  new FTPSUtil();
//				try { 
//					QryReportFileBean[] reportFileBean = sv.getReportImgupload(studyinfoId);
//					if(regInfoBean!=null&&reportFileBean.length>0){
//						for(int c=0;c<reportFileBean.length;c++){
//							if(reportFileBean != null){
//								ftpUtil.connect(reportFileBean[c].getServerIp(),Integer.parseInt(String.valueOf(reportFileBean[c].getServerPort())),reportFileBean[c].getServerUser(), reportFileBean[c].getServerPassword());
//								//下载文件 
//								ftpUtil.downloadReport("STUDY_REPORT_IMGFILE/"+dir+"/", reportFileBean[c].getFilePath(), path);
//								list.add(url+reportFileBean[c].getFilePath()+"&imgPath="+path);
//							}else{
//								model.addAttribute("errorMsg","影像未上传服务器，请上传...");
//							}
//						}
//					}
//				} catch (Exception e) {				
//					model.addAttribute("errorMsg","连接影像服务器异常，请稍后再试！"); 
//					e.printStackTrace();
//				} 
//				
//				
//	        }else{
//            logger.debug("111111111111111111111111111111111");
//            if ((file.exists() && file.isDirectory())) {
//                File[] tempList = file.listFiles();
//                logger.debug("该目录下对象个数：" + tempList.length);
//                for (int i = 0; i < tempList.length; i++) {
//                    if (tempList[i].isFile()) {
//                        System.out.println(tempList[i].toString());
//                        String imgName = tempList[i].toString().substring(tempList[i].toString().lastIndexOf("\\") + 1);
//                        list.add(url + imgName + "&imgPath=" + dir);
//                    }
//                }
//            }
//            model.addAttribute("imgList", list);
//	        DownloadFileThread thred = new DownloadFileThread(studyinfoId);
//	        thred.start();
        } catch (Exception e) {
            logger.error("StudyReportController-patientRegInit:", e);
        }
        return "workstation/studyReport";
    }

	/**
	 * 检测该报告是否被锁定
	 * @param studyInfoId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkReportIsLock.ajax")
	@ResponseBody
	public Map checkReportIsLock(String studyInfoId,HttpServletRequest request) throws Exception {
		Map<String,String> map = new HashMap();
		map.put("ERRCODE", "0");
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		AiscStudyLockBean[] bean = studyLockSV.getBeanByIdSelf(Long.parseLong(studyInfoId),user.getOperatorId());
		if(bean!=null&&bean.length>0){
			map.put("ERRCODE", "1");
			map.put("ERRINFO", bean[0].getUserName()+"正在书写报告！");
		}
		return map;
	}


	/**
	 * 解锁当前
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/unStudyLock.ajax")
	@ResponseBody
	public Map unStudyLock(String studyInfoId) throws Exception {
		Map<String,String> map = new HashMap();
		map.put("ERRCODE", "1");
		AiscStudyLockBean bean = studyLockSV.getBeanById(Long.parseLong(studyInfoId));
		if(bean!=null&&"1".equals(bean.getLockStatus())){
			bean.setLockStatus("0");
			bean.setEndTime(new Timestamp(System.currentTimeMillis()));
			studyLockSV.saveAiscStudyLock(bean,false);
			map.put("ERRCODE", "0");
		}
		return map;
	}


	/**
	 * 解锁所有报告
	 * @param
	 * @param
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/autoUnLockStudyReport.ajax")
	@ResponseBody
	public Map autoUnLockStudyReport(HttpServletRequest request){
		Map<String,String> map = new HashMap();
		map.put("ERRCODE", "1");
		try {
			User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
			studyLockSV.autoUnLockStudyReport(user.getOperatorId());
			map.put("ERRCODE", "0");
		} catch (Exception e) {
            map.put("ERRCODE", "1");
			logger.error("切换顶部菜单自动解锁报告异常"+e.getMessage());
		}
		return map;
	}

	// 修改病人信息
	@RequestMapping("/patientInfo.html")
	public String updatePatientInfo(@RequestParam String patientGlobalid,
			Model model) throws Exception {

        AisPatientInfoBean patBean = patientSV.getPatientInfo(patientGlobalid);
        model.addAttribute("patBean", patBean);
        return "workstation/updatePatientInfo";
    }

    @RequestMapping("/updatePatient.ajax")
    @ResponseBody
    public Map updatePatient(@RequestBody AisPatientInfoBean bean) throws Exception {
        patientSV.updatePatient(bean);
        Map<String, String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping(value = "showImg")
    public void ShowImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String imgFile = request.getParameter("imgFile"); //文件名
        String path = request.getParameter("imgPath");//这里是存放图片的文件夹地址
        FileInputStream fileIs = null;
        try {
            fileIs = new FileInputStream(path + "/" + imgFile);
        } catch (Exception e) {
//         log.error("系统找不到图像文件："+path+"/"+imgFile);        
            return;
        }
        int i = fileIs.available(); //得到文件大小
        byte data[] = new byte[i];
        fileIs.read(data);  //读数据
        response.setContentType("image/*"); //设置返回的文件类型
        OutputStream outStream = response.getOutputStream(); //得到向客户端输出二进制数据的对象
        outStream.write(data);  //输出数据
        outStream.flush();
        outStream.close();
        fileIs.close();
    }

    //模板树初始化
    @RequestMapping("/initTree.ajax")
    @ResponseBody
    public String initTree(String id, String flag, String orgId, String locId, String operatorId, String modalityId, HttpServletRequest request) throws Exception {
        JSONArray orgArrays = new JSONArray();
        //0为公共模板，1为私有模板
        try {
            QryTemplateTreeBean[] temps = null;
            if ("public".equals(flag)) {
                temps = sv.getTemplateTree(id, orgId, locId, operatorId, modalityId);
            } else {
                User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
                operatorId = user.getOperatorId();
                temps = sv.getTemplateTree(id, orgId, locId, operatorId, modalityId);
            }

            //操作员拥有的组织机构
            if (temps != null && temps.length > 0) {
                for (int i = 0; i < temps.length; i++) {
                    JSONObject m = new JSONObject();
                    m.put("id", temps[i].getTemplatedirId());
                    m.put("pid", temps[i].getTemplatedirPdirid());
                    m.put("name", temps[i].getTemplatedirDesc());
                    //模板浏览
                    Map map = sv.getTempInfo(String.valueOf(temps[i].getTemplatedirId()));
                    m.put("templateExam", map.get("templateExam"));
                    m.put("templateResult", map.get("templateResult"));
                    //m.put("orgLevel", temps[i].getOrgId());
                    //m.put("parentOrgId", temps[i].getTemplatedirPdirid());
                    if (temps[i].getIsDirectory() > 0) {
                        m.put("isParent", true);
                    } else {
                        m.put("isParent", false);
                    }
                    orgArrays.add(m);
                }
            }
        } catch (Exception e) {
            logger.error("StudyReportController-initTree:", e);
        }

        return orgArrays.toString();
    }

    //模板详情查询
    @RequestMapping("getTempInfo.ajax")
    @ResponseBody
    public JSONObject getTempInfo(String templatedirId) {

        JSONObject jsonObj = new JSONObject();
        try {
            Map map = sv.getTempInfo(templatedirId);
            jsonObj.put("contentId", map.get("contentId"));
            jsonObj.put("ispositive", map.get("ispositive"));
            jsonObj.put("templateExam", map.get("templateExam"));
            jsonObj.put("templateResult", map.get("templateResult"));
        } catch (Exception e) {
            logger.error("PatientRegController--getLocInfo:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //会诊开始
    @RequestMapping("consultStart.ajax")
    @ResponseBody
    public JSONObject consultStart(String studyinfoId, String reportId, HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        try {
            sv.consultStart(studyinfoId, user.getOperatorId(), reportId);
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--consultStart:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }
    /**
     * 检查意见及诊断回填
     * @param studyinfoId
     * @return
     */
    @RequestMapping("setReportExamResult.ajax")
    @ResponseBody
    public JSONObject setReportExamResult(String studyinfoId) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
        	AisStudyReportBean reportBean = sv.getReport(studyinfoId);
        	jsonObj.put("oldreportExam", reportBean==null?"":reportBean.getReportExam());
        	jsonObj.put("oldreportResult", reportBean==null?"":reportBean.getReportResult());
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--setReportExamResult:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }
    
    /**
     * ai数据结果查询------pacs调用
     * @param studyinfoId
     * @param orgId
     * @return
     */
    @RequestMapping("aiDataResult.ajax")
    @ResponseBody
    public JSONObject aiDataResult(String studyinfoId,String orgId) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
        	jsonObj = sv.aiDataResult(studyinfoId,orgId);
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--aiDataResult:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    @RequestMapping("/getReportSelect.ajax")
    @ResponseBody
    public Map getReportSelect(@RequestParam String studyInfoId) throws Exception {
        List<Map> reportSelect = sv.getReportSelect(studyInfoId);
        Map map = new HashMap();
        map.put("ReportSelect", JsonUtil.toJson(reportSelect));
        return map;
    }

    @RequestMapping("/getReportDetail.ajax")
    @ResponseBody
    public JSONObject getReportDetail(@RequestParam String reportHistoryId) throws Exception {
        JSONObject jsonObj = new JSONObject();
        try {
            QryReportHBean detailBean = sv.getReportH(reportHistoryId);
            jsonObj.put("reportResult", detailBean.getReportResult().replace("<br type=\"_moz\" />", ""));
            jsonObj.put("reportRemark", detailBean.getReportRemark());
            jsonObj.put("reportExam", detailBean.getReportExam().replace("<br type=\"_moz\" />", ""));
            jsonObj.put("status", detailBean.getStatus());
            jsonObj.put("reportDoc", detailBean.getReportDoctorname());
            jsonObj.put("reportVerifyDoc", detailBean.getReportVerifydoctorname());
            jsonObj.put("reportFinalDoc", detailBean.getReportFinaldoctorname());
            jsonObj.put("reportDateTime", String.valueOf(detailBean.getReportDatetime() == null ? "" : detailBean.getReportDatetime()));
            jsonObj.put("reportVerifyTime", String.valueOf(detailBean.getReportVerifydatetime() == null ? "" : detailBean.getReportVerifydatetime()));
            jsonObj.put("reportFinalTime", String.valueOf(detailBean.getReportFinaldatetime() == null ? "" : detailBean.getReportFinaldatetime()));
        } catch (Exception e) {
            logger.error("StudyReportController--getReportDetail:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //对比报告
    @RequestMapping("reportContrast.html")
    public String reportContrast(HttpServletRequest request, Model model, String studyInfoId) {
        try {
            if ((isNotBlank(studyInfoId))) {
                QryReportBrowseBean reportBean = sv.getReportContrast(studyInfoId);
                QryReportHBean reportHBean = sv.getReportH(studyInfoId);
                model.addAttribute("reportBean", reportBean);
                model.addAttribute("reportHBean", reportHBean);
            }

        } catch (Exception e) {
            logger.error("StudyReportController->reportContrast error!" + e.getMessage());
        }
        model.addAttribute("studyInfoId", studyInfoId);
        return "workstation/reportContrast";
    }

    //挂起报告
    @RequestMapping("saveUpReport.ajax")
    @ResponseBody
    public JSONObject saveUpReport(@RequestBody AisStudyReportBean reportBean, String studyConsultorg, String studyConsultloc, String reportId, String imgArr, String studyinfoId,String orgId, HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        try {
        	String result = sv.saveUpReport(reportBean, user, studyConsultorg, studyConsultloc, reportId,request,imgArr,orgId);
    		if(result.indexOf("||")!=-1){
    			jsonObj.put("reportId", result.substring(0,result.indexOf("||")));
                jsonObj.put("result", result.substring(result.indexOf("||")+2));
    		}
            //直接打印报告
            QryReportFileBean reportFileBean = sv.getReportPath(reportId, "0");
            if (reportFileBean != null) {

                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + reportFileBean.getFilePath();

                jsonObj.put("filePath", contextPath);
                logger.error("filePath-----------------------------:" + contextPath);
            }

            //同时保存影像上传到服务器上
            //sv.uploadImg(studyConsultloc,request,newRreportId,reportBean,imgArr,studyinfoId);
            jsonObj.put("studystatusCode", "UnCompleted");
        } catch (Exception e) {
        	jsonObj.put("result", e.getMessage());
            jsonObj.put("success", false);
            logger.error("StudyReportController--saveUpReport:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //保存报告
    @RequestMapping("saveReport.ajax")
    @ResponseBody
    public JSONObject saveReport(@RequestBody AisStudyReportBean reportBean, String studyConsultorg, String studyConsultloc, String reportId, String imgArr, String studyinfoId,String orgId,HttpServletRequest request) throws Exception {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        /*try {
        	String result = sv.saveReport(reportBean, user, studyConsultorg, studyConsultloc, reportId,request,imgArr,orgId);
    		if(result.indexOf("||")!=-1){
    			jsonObj.put("reportId", result.substring(0,result.indexOf("||")));
                jsonObj.put("result", result.substring(result.indexOf("||")+2));
    		}

            //直接打印报告
            QryReportFileBean reportFileBean = sv.getReportPath(reportId, "0");
            if (reportFileBean != null) {
                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + reportFileBean.getFilePath();

                jsonObj.put("filePath", contextPath);
                logger.error("filePath-----------------------------:" + contextPath);
            }

            //同时保存影像上传到服务器上
            //sv.uploadImg(studyConsultloc,request,newRreportId,reportBean,imgArr,studyinfoId);
            jsonObj.put("studystatusCode", "HAVERPT");
            
        } catch (Exception e) {
        	jsonObj.put("result", e.getMessage());
            jsonObj.put("success", false);
            logger.error("StudyReportController--saveReport:", e);
            //e.printStackTrace();
        }*/
        
        
        String result = sv.saveReport(reportBean, user, studyConsultorg, studyConsultloc, reportId,request,imgArr,orgId);
		if(result.indexOf("||")!=-1){
			jsonObj.put("reportId", result.substring(0,result.indexOf("||")));
            jsonObj.put("result", result.substring(result.indexOf("||")+2));
		}

        //直接打印报告
        QryReportFileBean reportFileBean = sv.getReportPath(reportId, "0");
        if (reportFileBean != null) {
            String prePath = com.ai.common.util.WebUtils.getBasePath(request);
            prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
            String contextPath = prePath + reportFileBean.getFilePath();

            jsonObj.put("filePath", contextPath);
            logger.error("filePath-----------------------------:" + contextPath);
        }

        //同时保存影像上传到服务器上
        //sv.uploadImg(studyConsultloc,request,newRreportId,reportBean,imgArr,studyinfoId);
        jsonObj.put("studystatusCode", "HAVERPT");
        
        
        return jsonObj;
    }

    //自动保存报告
    @RequestMapping("preSaveReport.ajax")
    @ResponseBody
    public JSONObject preSaveReport(@RequestBody AisStudyreportTempBean tempBean) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", "error");
        try {

            tempBean.setReportDatetime(SysdateManager.getSysTimestamp());
            if ("".equals(tempBean.getStudyinfoId())) return jsonObj;
            commonSV.execute(" delete from AIS_STUDYREPORT_TEMP t where t.studyinfo_id = " + tempBean.getStudyinfoId(), null);
            commonSV.insert(tempBean);
            jsonObj.put("result", "success");
        } catch (Exception e) {
            jsonObj.put("success", "error");
            logger.error("StudyReportController--preSaveReport:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //审核报告
    @RequestMapping("checkReport.ajax")
    @ResponseBody
    //@RequestBody 这个很重要，不然值传不过来。 locId不在bean里面，所以要这样写?locId="+locId
    public JSONObject checkReport(@RequestBody AisStudyReportBean reportBean, String locId, String studyConsultorg, String studyConsultloc, String imgAddrArray,String myStudyType,String orgId,String consultRole,HttpServletRequest request) {
        //修改检查单状态，并生成html文件上传服务器
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
            //更新状态,生成报告文件上传服务器
            String result = sv.checkReport(reportBean, request, locId, studyConsultorg, studyConsultloc, imgAddrArray,orgId,consultRole);

            //直接打印报告
            QryReportFileBean reportFileBean = sv.getReportPath(String.valueOf(reportBean.getReportId()), "0");
            if (reportFileBean != null) {
                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + reportFileBean.getFilePath();

                jsonObj.put("filePath", contextPath);
                logger.error("filePath-----------------------------:" + contextPath);
              /*  //更新审核状态到ais_studymessage表
    			String reportId = String.valueOf(reportBean.getReportId());
    			AisStudyInfoBean aisStudyInfoBean = sv.getAisStudyInfoBean(reportId);
    			String messageDestuerId=null;
    			String statusCode=null;
    			if(aisStudyInfoBean != null){
    				boolean saveResult = sv.saveAisStudyMessage(aisStudyInfoBean, messageDestuerId, myStudyType, statusCode);
    				if(!saveResult){
    					result="false";
    				}
    			}*/
    			/************************更新审核状态到ais_studymessage表结束******************************************/
            }

            jsonObj.put("success", result);
        } catch (Exception e) {
        	jsonObj.put("result", e.getMessage());
            jsonObj.put("success", false);
            logger.error("StudyReportController--checkReport:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //二次审核报告
    @RequestMapping("checkFinalReport.ajax")
    @ResponseBody
    //@RequestBody 这个很重要，不然值传不过来。 locId不在bean里面，所以要这样写?locId="+locId
    public JSONObject checkFinalReport(@RequestBody AisStudyReportBean reportBean, String locId, String studyConsultorg, String studyConsultloc, String imgAddrArray,String orgId, HttpServletRequest request) {
        //修改检查单状态，并生成html文件上传服务器
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
            //更新状态,生成报告文件上传服务器
            String result = sv.checkFinalReport(reportBean, request, locId, studyConsultorg, studyConsultloc, imgAddrArray,orgId);

            //直接打印报告
            QryReportFileBean reportFileBean = sv.getReportPath(String.valueOf(reportBean.getReportId()), "1");
            if (reportFileBean != null) {

                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + reportFileBean.getFilePath();

                jsonObj.put("filePath", contextPath);
                logger.error("filePath-----------------------------:" + contextPath);
            }
            User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
            jsonObj.put("finalDoc", user.getCareProvId());
            jsonObj.put("success", result);
        } catch (Exception e) {
        	jsonObj.put("result", e.getMessage());
            jsonObj.put("success", false);
            logger.error("StudyReportController--checkReport:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //报告打印
    @RequestMapping("reportPrint.html")
    public String reportPrint(String reportId, String finalDocId, Model model, HttpServletRequest request) {
        String returnPage = "print/reportPrint";
//		try {

        //FTP://administrator:zhxy@2015_pacs@10.63.90.29:21/STUDY_REPORT_FILE/201512_08162635_808.html
//				filePath = "FTP://"+reportFileBean.getServerUser()
//							     +":"+reportFileBean.getServerPassword()
//							     +"@"+reportFileBean.getServerIp()
//							     +":"+reportFileBean.getServerPort()
//							     +"/"+reportFileBean.getMediumPath()
//							     +"/"+reportFileBean.getFilePath();		 
//			}	

//			model.addAttribute("filePath", filePath);    
//			if(reportFileBean == null){
//				model.addAttribute("errorMsg","报告未上传服务器，请上传后再打印...");
//				returnPage = "common/error";	 			
//            } 

        //文件大小
        long fileSize = 0l;
        FTPSUtil ftpUtil = new FTPSUtil();
        try {
            String status = "0";
            if (finalDocId != null && !"".equals(finalDocId)) {
                status = "1";
            }
            QryReportFileBean reportFileBean = sv.getReportPath(reportId, status);
            if (reportFileBean != null) {
//					ftpUtil.connect(reportFileBean.getServerIp(),Integer.parseInt(String.valueOf(reportFileBean.getServerPort())),reportFileBean.getServerUser(), reportFileBean.getServerPassword());
//					fileSize = ftpUtil.getFileSize(reportFileBean.getMediumPath()+"/"+reportFileBean.getFilePath());
////					
////					//下载文件 
//					String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
//					ftpUtil.downloadReport(reportFileBean.getMediumPath()+"/", reportFileBean.getFilePath(), reportUpPath);

                String prePath = com.ai.common.util.WebUtils.getBasePath(request);
                prePath = prePath.replace("Aris", "FileServer/aris/reportUpload/");
                String contextPath = prePath + reportFileBean.getFilePath();

                //model.addAttribute("filePath", contextPath);
                returnPage = contextPath;
                logger.error("filePath-----------------------------:" + contextPath);
            } else {
                model.addAttribute("errorMsg", "报告未上传服务器，请上传后再打印...");
                returnPage = "common/error";
            }


        } catch (Exception e) {
            model.addAttribute("errorMsg", "连接服务器查报告单异常，请稍后再试！");
            e.printStackTrace();
            model.addAttribute("reportId", reportId);
            returnPage = "common/error";
            return returnPage;
        }
//			if(fileSize == 0){
//				model.addAttribute("errorMsg","报告文件不存在，请上传后再打印...");
//				returnPage = "common/error";	
//			}	

//		} catch (Exception e) {	
//			model.addAttribute("errorMsg","系统异常，请稍后再试...");
//			returnPage = "common/error";
//			logger.error("StudyReportController-patientRegInit:", e);
//		} 
        //model.addAttribute("reportId", reportId);

        return "redirect:" + returnPage;
//		return returnPage;
    }

    //影像打印
    @RequestMapping("imgPrint.html")
    public String imgPrint(String imgArr, long studyinfoId, Model model, HttpServletRequest request) {
        String returnPage = "print/imgPrint";
        //获取影像打印模板html
        String imgHtml = "";
        try {
            imgHtml = sv.getImgPrintTemp(imgArr, studyinfoId);
        } catch (Exception e) {
            model.addAttribute("errorMsg", "连接服务器查异常，请稍后再试！");
            e.printStackTrace();
            returnPage = "common/error";
            return returnPage;
        }
        model.addAttribute("imgHtml", imgHtml);
        return returnPage;
    }

    //取报告存放地址
    @RequestMapping("setReportPrintInfo.ajax")
    @ResponseBody
    public JSONObject setReportPrintInfo(String reportId, HttpServletRequest request) {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        try {
            //更新状态,生成报告文件上传服务器
            sv.setReportPrintInfo(reportId, user);
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--setReportPrintInfo:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }


    //监测文件是否存在
    private static boolean isConnection(String url) {
        boolean flag = false;
        try {
            java.net.URL u = new java.net.URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setConnectTimeout(5000);
            int state = conn.getResponseCode();
            if (state == 200 || state == 400) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (IOException e) {
            flag = false;
        }
        return flag;
    }

    //用户短语查询
    @RequestMapping("getPhrase.ajax")
    @ResponseBody
    public JSONObject getPhrase(HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject();
        try {
            User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
            String operatorId = user.getOperatorId();
            AiscUserPhraseBean[] phraseBeans = sv.getPhrase(operatorId);
            jsonObj.put("phraseBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(phraseBeans == null ? new AiscUserPhraseBean[0] : phraseBeans));

        } catch (Exception e) {
            logger.error("StudyReportController--getPhrase:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //用户短语增删改
    @RequestMapping("upPhrase.ajax")
    @ResponseBody
    public JSONObject upPhrase(String opflag, String pInfo, String pId, HttpServletRequest request) {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("success", true);
        try {
            User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
            String operatorId = user.getOperatorId();
            if ("add".equals(opflag)) {
                sv.addPhrase(operatorId, java.net.URLDecoder.decode(pInfo, "UTF-8"));
            } else if ("del".equals(opflag)) {
                sv.delPhrase(pId);
            } else if ("update".equals(opflag)) {
                sv.updatePhrase(pId, java.net.URLDecoder.decode(pInfo, "UTF-8"));
            }
        } catch (Exception e) {
            jsonObj.put("success", false);
            logger.error("StudyReportController--upPhrase:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    //相关检查
    @RequestMapping("getRelCheck.ajax")
    @ResponseBody
    public JSONObject getRelCheck(String patientGlobalid, String studyinfoId,String patientInpatientId,String patientIdnumber) {
        JSONObject jsonObj = new JSONObject();
        try {
            QryRegInfoBean[] regInfoBeans = sv.getRelCheck(patientGlobalid, studyinfoId,patientInpatientId,patientIdnumber);
            String beans = com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(regInfoBeans == null ? new QryRegInfoBean[0] : regInfoBeans);
            //替掉换行符，不然前台eval转换报错
            jsonObj.put("regInfoBeans", beans.replaceAll("\r|\n", ""));

        } catch (Exception e) {
            logger.error("StudyReportController--getPhrase:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    /**
     * 收藏病例界面
     * @return
     * @throws Exception
     */
    @RequestMapping("/collectionCase.html")
    public String viewCollectionCase(@RequestParam String reportId, Model model,HttpServletRequest request) throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        model.addAttribute("operatorId",user.getOperatorId());
        model.addAttribute("reportId", reportId);
        String forward = "workstation/collectionCase";
        return forward;
    }

    /**
     * 模板维护界面
     *
     * @param type
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/template.html")
    public String viewDetail(@RequestParam String type, Model model) throws Exception {
        model.addAttribute("templateType", type);
        String forward = "";
        if (type.equals("1")) {
            forward = "workstation/publicTemplate";
        } else {
            forward = "workstation/privateTemplate";
        }
        return forward;
    }

    /**
     * 模板维护界面
     *
     * @param studyinfoId
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/preEdit.html")
    public String preEdit(@RequestParam String studyinfoId, Model model) throws Exception {
        //取自动保存的数据
        String sql = " studyinfo_id = " + studyinfoId;
        AisStudyreportTempBean tempBean = new AisStudyreportTempBean();
        tempBean = (AisStudyreportTempBean) commonSV.getBean(sql, null, AisStudyreportTempBean.class);
        if (tempBean != null) {
            if (StringUtils.isNotEmpty(tempBean.getReportExam()))
                model.addAttribute("oldreportExam", tempBean.getReportExam().replace("<br type=\"_moz\" />", ""));
            if (StringUtils.isNotEmpty(tempBean.getReportResult()))
                model.addAttribute("oldreportResult", tempBean.getReportResult().replace("<br type=\"_moz\" />", ""));
            model.addAttribute("oldreportRemark", tempBean.getReportRemark());
        }
        model.addAttribute("studyinfoId", studyinfoId);
        return "workstation/preEdit";
    }
    
    /**
     * 病例修改界面
     *
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/bingliUpdate.html")
    public String bingliUpdate(Model model) throws Exception {
        return "workstation/bingliUpdate";
    }

    /**
     *
     * @param patientId
     * @param patientName
     * @param reportId
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/reportFileUpdate.html")
    public String reportFileUpdate(@RequestParam String patientId, String patientName,String reportId,Model model, HttpServletRequest request) throws Exception {
        AisReportUploadBean [] aisReportUploadBean = sv.queryFileUpload(reportId,patientId,java.net.URLDecoder.decode(patientName, "UTF-8"));
        String fileUploadPath = AIConfigManager.getConfigItem("reportFileUploadPath");
        model.addAttribute("patientId", patientId);
        model.addAttribute("patientName", java.net.URLDecoder.decode(patientName, "UTF-8"));
        model.addAttribute("reportId", reportId);
        model.addAttribute("fileUploadPath", fileUploadPath);
        model.addAttribute("aisReportUploadBean", aisReportUploadBean);
        return "workstation/reportFileUpload";
    }
    //文件上传
    @RequestMapping("/saveUploadFiles.ajax")
    @ResponseBody
    public JSONObject saveUploadPdf(String patientId,String reportId,String patientName,HttpServletRequest request)
            throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        JSONObject json = new JSONObject();
        json.put("ERRORCODE", "0");
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file1");
        String fileUploadPath = AIConfigManager.getConfigItem("reportFileUploadPath");
        fileUploadPath+=(java.net.URLDecoder.decode(patientName, "UTF-8")+"_"+patientId+"_"+reportId+"/");
        for (int i =0; i< files.size(); i++) {
            MultipartFile file = (MultipartFile)files.get(i);
            String name = file.getOriginalFilename();
            if (!file.isEmpty()) {
                String result="";
                result = sv.saveReportFileUpload(file,fileUploadPath,name,reportId,user,patientId,java.net.URLDecoder.decode(patientName, "UTF-8"));
                json.put("RESULT", result);
            } else {
                json.put("ERRORCODE", "-1");
            }
        }
        return json;
    }
    /**
     * 新增或修改树节点信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/editNodes.ajax")
    @ResponseBody
    public Map editNodes(@RequestBody TemplateContent temp) throws Exception {
        boolean flag = sv.editNodes(temp.getTemplatecontentId(), temp.getTemplateName());
        Map<String, String> map = new HashMap();
        if (flag) {
            map.put("ERRCODE", "0");
        } else {
            map.put("ERRINFO", "修改失败");
        }
        return map;
    }
    
    /**
     * 新增或修改病例树节点信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/editbingliNodes.ajax")
    @ResponseBody
    public Map editbingliNodes(@RequestBody TemplateContent temp) throws Exception {
        boolean flag = sv.editbingliNodes(temp.getKnowledgebaseid(), temp.getCasegroupdesc());
        Map<String, String> map = new HashMap();
        if (flag) {
            map.put("ERRCODE", "0");
        } else {
            map.put("ERRINFO", "修改失败");
        }
        return map;
    }

    /**
     * 删除树节点信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletezNodes.ajax")
    @ResponseBody
    public Map deletezNodes(@RequestParam long id, @RequestParam String childs) throws Exception {
        Map map = new HashMap();
        boolean flag = false;
        try {
            if (!"".equals(childs)) {
                String childss[] = childs.split(",");
                for (int i = 0; i < childss.length; i++) {
                    flag = sv.deleteNodes(Long.parseLong(childss[i]));
                }
            }
            //
            flag = sv.deleteNodes(id);
        } catch (Exception e) {
            flag = false;
            logger.error("deleteNodes error:", e);
            e.printStackTrace();
        }
        if (flag) {
            map.put("ERRCODE", 0);
        } else {
            map.put("ERRINFO", "删除失败");
        }
        return map;
    }
    /**
     * 删除病例树节点
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/deletebingliNodes.ajax")
    @ResponseBody
    public Map deletebingliNodes(@RequestParam long id) throws Exception {
        Map map = new HashMap();
        boolean flag = false;
        try {
            flag = sv.deletebingliNodes(id);
        } catch (Exception e) {
            flag = false;
            logger.error("deleteNodes error:", e);
            e.printStackTrace();
        }
        if (flag) {
            map.put("ERRCODE", 0);
        } else {
            map.put("ERRINFO", "删除失败");
        }
        return map;
    }

    /**
     * 模板列表list
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryTempContentList.ajax")
    @ResponseBody
    public JSONObject queryTempContentList(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "rows", defaultValue = "10") int rows,
                                           TemplateContent model) throws Exception {
        ResultDTO result = new ResultDTO(page, rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }

    /**
     * 获取节点seq
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/getNodeIndex")
    @ResponseBody
    public Map getNodeIndex() throws Exception {
        Map map = new HashMap();
        long nodeId = sv.getNodeIndex();
        map.put("nodeId", nodeId);
        return map;
    }

    /**
     * 增加树节点信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/addNodes.ajax")
    @ResponseBody
    public Map addNodes(@RequestBody TemplateContent temp, String dataType, HttpServletRequest request) throws Exception {
        //判断节点是否存在
        Map map = new HashMap();
        boolean flag = false;
        long templatedir_flag = dataType.equals("1") ? 1 : 2;
        try {
            long operatorId = 0;
            if (dataType.equals("2")) {
                User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
                operatorId = Long.parseLong(user.getOperatorId());
            }
            flag = sv.saveNode(temp.getTemplatedirId(), temp.getTemplatedirPdirid(), temp.getTemplateName(), temp.getLocId(), temp.getOrgId(), temp.getIsDirectory(), operatorId, templatedir_flag, temp.getModalityId());
        } catch (Exception e) {
            flag = false;
            logger.error("deleteNodes error:", e);
            e.printStackTrace();
        }
        if (flag) {
            map.put("ERRCODE", 0);
        } else {
            map.put("ERRINFO", "节点保存失败");
        }
        return map;
    }

    /**
     * 模板信息详情
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/aiscTemplateDetail")
    @ResponseBody
    public Map aiscTemplateDetail(long dirId) throws Exception {
        Map map = new HashMap();
        TemplateContent templateBean = sv.getAiscTemplateDetail(dirId);
        map.put("templateBean", templateBean);
        return map;
    }

    /**
     * 新增或修改模板详情
     *
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveTemplateDetail")
    @ResponseBody
    public Map saveTemplateDetail(@RequestBody TemplateContent bean) throws Exception {
        sv.saveTemplateDetail(bean);
        Map<String, String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
    
    /**
     * 修改病例收藏
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping("/updateKnowledgebase")
    @ResponseBody
    public Map updateKnowledgebase(@RequestBody TemplateContent bean) throws Exception {
        sv.updateKnowledgebase(bean);
        Map<String, String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    /**
     * 删除模板信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteTemplate.ajax")
    @ResponseBody
    public Map deleteTemplate(@RequestParam long id) throws Exception {
        Map map = new HashMap();
        boolean flag = sv.deleteTemplate(id);
        if (flag) {
            map.put("ERRCODE", 0);
        } else {
            map.put("ERRINFO", "删除失败");
        }
        return map;
    }

    //相关检查
    @RequestMapping("getStudyReport.ajax")
    @ResponseBody
    public JSONObject getStudyReport(String studyinfoId) {
        JSONObject jsonObj = new JSONObject();
        try {
            AisStudyReportBean reportBean = sv.getReport(studyinfoId);
            jsonObj.put("reportBean", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(reportBean == null ? new AisStudyReportBean() : reportBean));

        } catch (Exception e) {
            logger.error("StudyReportController--getPhrase:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }

    @RequestMapping("relReportView.html")
    public String relReportView(String studyinfoId, Model model) {
        try {
            AisStudyReportBean reportBean = sv.getReport(studyinfoId);
            model.addAttribute("reportBean", reportBean);
            QryRegInfoBean regInfoBean = sv.getRegInfo(studyinfoId);
            model.addAttribute("regInfoBean", regInfoBean);
        } catch (Exception e) {
            logger.error("StudyReportController-patientRegInit:", e);
        }
        return "workstation/relReportView";
    }

    public static void main(String[] args) {

        String host = "10.63.90.29";
        String path = "STUDY_REPORT_FILE";
        String username = "administrator";
        String password = "zhxy@2015_pacs";

        FTPSUtil ftpUtil = new FTPSUtil();
        try {
        	try {
				String reportUpPath = AIConfigManager.getConfigItem("ReportUpPath");
				ftpUtil.connect("10.63.89.238", 21, "ftp123", "Ftp_123*456AB");
				System.out.println(ftpUtil.getFileSize("Images/00000C64.DCM"));;
	            ftpUtil.downloadReport("Images/","00000C64.DCM",reportUpPath);
	            ftpUtil.closeChannel();
//	            File localFile = new File(reportUpPath+"/00000C64.DCM"); 
//	            FileInputStream in = new FileInputStream(localFile);  
//	            byte[] buffer = new byte[(int) localFile.length()];
//	            in.read(buffer);
//	            in.close();
        	 } catch (SocketException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block
                 e.printStackTrace();
             }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
    }

    @RequestMapping("/templateImport.html")
    public String templateImport(@RequestParam String type, String locId, String orgId, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("importType", type);
        model.addAttribute("locId", locId);
        model.addAttribute("orgId", orgId);
        String forward = "";
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        long operatorId = Long.parseLong(user.getOperatorId());
        model.addAttribute("operatorId", operatorId);
        forward = "workstation/publicTemplateImport";
        return forward;
    }
    
    @RequestMapping("/medicalRecordView.html")
    public String medicalRecordView(@RequestParam String studyinfoId,Model model, HttpServletRequest request) throws Exception {
    	AisFilesInfoBean[] fileBean = sv.getFileInfo(studyinfoId);
    	//从226获取文件到共享目录下
    	for(AisFilesInfoBean bean:fileBean){
    	FTPSUtil ftpUtil = new FTPSUtil();
     	try {
     		ftpUtil.connect("10.63.89.238", 21, "ftp123", "Ftp_123*456AB");
            ftpUtil.download("/medicalRecordPdf/",bean.getFilePath(),"/home/devweb/apache-tomcat-7.0.55/webapps/FileServer/aris/downloadPDF/");
            ftpUtil.closeChannel();
     	 } catch (SocketException e) {
              e.printStackTrace();
         } catch (IOException e) {
              e.printStackTrace();
         }
    	}
		String prePath = com.ai.common.util.WebUtils.getBasePath(request);
	    prePath = prePath.replace("Aris", "FileServer/aris/downloadPDF/");
    	model.addAttribute("fileBean", fileBean);
    	model.addAttribute("prePath", prePath);
        return "workstation/medicalRecordView";
    }
    
    /*
     * 获取知识库表中的部位信息
     * */
    @RequestMapping("/getCasegroup")
    @ResponseBody
    public Map getCasegroup(HttpServletRequest request) throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        List<AisKnowledgebaseModel> lists = sv.getAisKnowledgebase(user.getOperatorId());
        Map map = new HashMap();
        map.put("casegroup", JsonUtil.toJson(lists));
        return map;
    }

    /*
 * 获取知识库表中的部位信息
 * */
    @RequestMapping("/getKeydesc")
    @ResponseBody
    public Map getKeydesc(String dataSore) throws Exception {

        List<AisKnowledgebaseModel> lists = sv.getAisKnowledgebaseKeyDesc(dataSore);
        Map map = new HashMap();
        map.put("keydesc", JsonUtil.toJson(lists));
        return map;
    }

    /**
     * 保存
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveAisKnowledgebase.ajax")
    @ResponseBody
    public Map saveAisKnowledgebase(String reportId, String operatorId,String keydesc) throws Exception {
        String keydesc1 = URLDecoder.decode(keydesc,"UTF-8");
        Map map = new HashMap();
        map.put("ERRCODE", 0);
        map.put("ERRINFO", "添加病例成功");
        int num = 0;
        int keydescNum = 0;
        try {
            num = sv.checkReportIsRepeat(reportId);
            if(num > 0){
                map.put("ERRCODE", 1);
                map.put("ERRINFO", "该病例已添加，不能重复添加");
            }else{
                sv.saveAisKnowledgebase(reportId,operatorId,keydesc1);
            }
        } catch (Exception e) {
            map.put("ERRCODE", 1);
            map.put("ERRINFO", "添加病例失败");
            logger.error("添加病例失败:", e);
            e.printStackTrace();
        }
        return map;
    }


    //模板树初始化
    @RequestMapping("/initKnowledgeTree.ajax")
    @ResponseBody
    public String initKnowledgeTree(String id,HttpServletRequest request) throws Exception {
        User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
        JSONArray orgArrays = new JSONArray();
        try {
            AisKnowledgebaseBean[] temps = sv.getKnowledgeTree(id,user.getOperatorId());

            //操作员拥有的组织机构
            if (temps != null && temps.length > 0) {
                for (int i = 0; i < temps.length; i++) {
                    if(temps[i].getCasegroupdesc()!= null){
	                    JSONObject m = new JSONObject();
	                    m.put("id", temps[i].getKnowledgebaseid());
	                    m.put("pid", temps[i].getCollectorCode());
	                    m.put("name", temps[i].getCasegroupdesc());
	                    if (temps[i].getCasegroupdesc() != null) {
	                        m.put("isParent", true);
	                    } else {
	                        m.put("isParent", false);
	                    }
	                    orgArrays.add(m);
	                }else{
	                        JSONObject m = new JSONObject();
	                        m.put("id", temps[i].getKnowledgebaseid());
	                        m.put("pid", temps[i].getCollectorCode());
	                        m.put("name", temps[i].getKeydesc());
	                        m.put("isParent", false);
	                        orgArrays.add(m);
	                }
                }
            }
        } catch (Exception e) {
            logger.error("StudyReportController-initTree:", e);
        }

        return orgArrays.toString();
    }
    @RequestMapping("/fileDelete.ajax")
    @ResponseBody
    public Map fileDelete(HttpServletRequest request) throws Exception {
        String fileName = request.getParameter("fileName");
        String patientName = request.getParameter("patientName");
        String patientId = request.getParameter("patientId");
        String reportId = request.getParameter("reportId");
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        map.put("MESSAGE", "成功");
        try {
            sv.fileDelete(fileName,patientName,patientId,reportId);
        }catch (Exception e){
            map.put("ERRCODE", "1");
            map.put("msg", e.getMessage());
            logger.info("fileDelete.ajax error:",e);
        }
        return map;
    }


    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fileDownload")
    public String fileDownload(HttpServletRequest request,
                              HttpServletResponse response) {
        String fileName = request.getParameter("fileName");
        String fileInFolder = request.getParameter("fileInFolder");
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    +  new String( fileName.getBytes("gb2312"), "ISO8859-1" ));

            String path = AIConfigManager.getConfigItem("reportFileUploadPath");

            InputStream inputStream = new FileInputStream(new File(path
                    + fileInFolder+"/"+fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            os.close();

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/downloadPdf")
    public String downloadPdf(HttpServletRequest request,
            HttpServletResponse response) {
		String fileName = request.getParameter("fileName");
		String oldName = request.getParameter("oldName");
		try {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                +  new String( oldName.getBytes("gb2312"), "ISO8859-1" ));
 
            String path = AIConfigManager.getConfigItem("PdfUpPath");
            
            InputStream inputStream = new FileInputStream(new File(path
            		+ fileName));
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
 
            os.close();
 
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        return null;
    }

    //病例库中关联的报表查询
    @RequestMapping("getKnowLedgeReportInfo.ajax")
    @ResponseBody
    public JSONObject getKnowLedgeReportInfo(long id) throws Exception{
        JSONObject jsonObj = new JSONObject();
        try {
            Map map = sv.getKnowLedgeReportInfo(id);
            jsonObj.put("templateExam", map.get("templateExam"));
            jsonObj.put("templateResult", map.get("templateResult"));
        } catch (Exception e) {
            logger.error("PatientRegController--getLocInfo:", e);
            e.printStackTrace();
        }
        return jsonObj;
    }
    private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
    private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
    
    public static String delHTMLTag(String htmlStr) {  
        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
        Matcher m_script = p_script.matcher(htmlStr);  
        htmlStr = m_script.replaceAll(""); // 过滤script标签  
  
        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
        Matcher m_style = p_style.matcher(htmlStr);  
        htmlStr = m_style.replaceAll(""); // 过滤style标签  
  
//        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
//        Matcher m_html = p_html.matcher(htmlStr);  
//        htmlStr = m_html.replaceAll(""); // 过滤html标签  
  
//        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
//        Matcher m_space = p_space.matcher(htmlStr);  
//        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
        return htmlStr; // 返回文本字符串  
    }  
}
