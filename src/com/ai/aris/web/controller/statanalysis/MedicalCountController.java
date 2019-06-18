package com.ai.aris.web.controller.statanalysis;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.QryConsultLocBean;
import com.ai.aris.server.basecode.bean.QryConsultOrgBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.statanalysis.bean.QryHzOrgBean;
import com.ai.aris.server.statanalysis.bean.QrySQLocBean;
import com.ai.aris.server.statanalysis.model.QryDoctorWorkloadModel;
import com.ai.aris.server.statanalysis.model.QryHzMultipleListModel;
import com.ai.aris.server.statanalysis.model.QryHzRecordListModel;
import com.ai.aris.server.statanalysis.service.interfaces.IPatientWorkloadListSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.ExcelUtil;
import com.ai.common.util.JsonUtil;
import com.ai.common.util.ServiceUtil;

/**
 * 病例统计分析统计--按照市县统计总的病例情况
 */
@Controller
@RequestMapping("/meidcalCount")
public class MedicalCountController {
	
	private Log logger = LogFactory.getLog(MedicalCountController.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
    private IPatientWorkloadListSV sv = (IPatientWorkloadListSV) ServiceFactory.getService(IPatientWorkloadListSV.class);
	
    //病例统计分析统计初始化
	@RequestMapping("/init.html")
	public String init(HttpServletRequest request,Model model){		
		return "statanalysis/medicalInit";
	}
	
	// 查询病例统计分析统计记录
	@RequestMapping("/queryMedicalCountList.ajax")
	@ResponseBody	
	public JSONObject queryHzRecordMutiList(@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
		QryHzMultipleListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryMedicalCountList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
	
	@RequestMapping("/queryMedicalCountDetailList.ajax")
	@ResponseBody	
	public JSONObject queryMedicalCountDetailList(@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
		QryHzMultipleListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryMedicalCountDetailList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
	
	//病案按地域导出
	@RequestMapping(value="exportMedicalCount.ajax")
    public String exportMedicalCount(QryHzMultipleListModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="病案统计按地域分析";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String exportMark = request.getParameter("exportMark");
        if(exportMark.equals("1")){
        	short widths[] = {20,20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getMeidcalCountExcel(model);
        	String[] columnNames = {"区县","开始日期","结束日期","数量"};//列名
        	String[] keys = {"COUNTY_DESC","START_TIME","END_TIME","HZSL"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("2")){
        	short widths[] = {20,20,20,20,20,20,20,20,40,40};//列宽
        	List<Map<String,Object>> list = sv.getMeidcalCountDetailExcel(model);
        	String[] columnNames = {"县区","机构名称","日期","病人姓名","登记号","年龄","性别","检查号","诊断意见","诊断结果"};//列名
        	String[] keys = {"COUNTY_DESC","ORG_NAME","SDATETIME","PATIENT_NAME","STUDYINFO_ID","PATIENT_AGE","PATIENT_SEX","STUDY_ACCNUMBER","REPORT_EXAM","REPORT_RESULT"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes("gb2312"), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;
    }
	
//	
//	//机构会诊信息综合统计
//	@RequestMapping("/initMultiple.html")
//	public String initMultiple(HttpServletRequest request,Model model){		
//		return "statanalysis/consultationMultiple";
//	}
//	
//	/**
//	 * 根据机构获取申请科室
//	 * @param orgId
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getsqLoc.ajax")
//    @ResponseBody
//	public JSONObject getConsultLoc(String orgId,HttpServletRequest request) {
//		JSONObject jsonObj = new JSONObject();
//		try {
//			QrySQLocBean[] locBeans = sv.getSQLoc(orgId);			
//			jsonObj.put("locBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QrySQLocBean[0] : locBeans));
//		    	
//		} catch (Exception e) {
//			logger.error("ConsultationCountController--getsqLoc:", e);
//			e.printStackTrace();
//		}       
//	     return jsonObj; 
//	}
//
//	/**
//	 * 根据机构、申请科室获取会诊机构
//	 * @param orgId
//	 * @param locId
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/getHzOrg.ajax")
//    @ResponseBody
//	public JSONObject getHzOrg(String orgId,String locId,String conorgId,HttpServletRequest request) {
//		JSONObject jsonObj = new JSONObject();
//		try {
//			QryHzOrgBean[] locBeans = sv.getHzOrg(orgId,locId,conorgId);			
//			jsonObj.put("orgBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QryHzOrgBean[0] : locBeans));
//		    	
//		} catch (Exception e) {
//			logger.error("ConsultationCountController--getHzOrg:", e);
//			e.printStackTrace();
//		}       
//	     return jsonObj; 
//	}
//	
//	// 查询会诊统计记录
//	@RequestMapping("/queryHzRecordList.ajax")
//	@ResponseBody	
//	public JSONObject queryHzRecordList(
//			@RequestParam(value = "page", defaultValue = "1") int page,
//			@RequestParam(value = "rows", defaultValue = "10") int rows,
//			String sidx,String sord,
//			QryHzRecordListModel model, HttpServletRequest request,HttpServletResponse response)
//			throws Exception { 
//		ResultDTO result = new ResultDTO(page, rows);
//		result = sv.queryHzRecordList(model, result,sidx,sord);
//		return AjaxUtil.jqGridJson(result);
//	}
//	
//	
//	// 查询会诊综合统计详细记录
//	@RequestMapping("/getDetailHzRecord.ajax")
//	@ResponseBody	
//	public JSONObject getDetailHzRecord(@RequestParam(value = "page", defaultValue = "1") int page,
//		@RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
//		QryHzMultipleListModel model, HttpServletRequest request,HttpServletResponse response)
//			throws Exception { 
//		ResultDTO result = new ResultDTO(page, rows);
//		result = sv.getDetailHzRecord(model, result,sidx,sord);
//		return AjaxUtil.jqGridJson(result);
//	}
//	
//	//获取合计信息
//	@RequestMapping("getTotalInfo.ajax")
//	@ResponseBody
//	public Map<String, String> getTotalInfo(QryHzMultipleListModel searchModel) {
//		Map<String, String> map = new HashMap<String,String>();
//		try {
//			map = sv.getTotalInfo(searchModel);
//			map.put("ERRCODE", "0");
//		} catch (Exception e) {
//			logger.error("获取合计信息：" , e);
//			map.put("ERRCODE", "1");
//			map.put("ERRINOFO", e.getMessage());
//		}
//		return map;
//	}
}
