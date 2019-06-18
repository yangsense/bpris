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
import com.ai.aris.server.statanalysis.model.AiscPatientCheckCountyrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckLocrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckRpModel;
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
 * 机构会诊信息统计
 */
@Controller
@RequestMapping("/consultationCount")
public class ConsultationCountController {
	
	private Log logger = LogFactory.getLog(ConsultationCountController.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
    private IPatientWorkloadListSV sv = (IPatientWorkloadListSV) ServiceFactory.getService(IPatientWorkloadListSV.class);
	
    //机构会诊信息统计初始化
	@RequestMapping("/init.html")
	public String init(HttpServletRequest request,Model model){		
		return "statanalysis/consultationInit";
	}
	
	//机构会诊信息综合统计
	@RequestMapping("/initMultiple.html")
	public String initMultiple(HttpServletRequest request,Model model){		
		return "statanalysis/consultationMultiple";
	}
	
	/**
	 * 根据机构获取申请科室
	 * @param orgId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getsqLoc.ajax")
    @ResponseBody
	public JSONObject getConsultLoc(String orgId,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			QrySQLocBean[] locBeans = sv.getSQLoc(orgId);			
			jsonObj.put("locBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QrySQLocBean[0] : locBeans));
		    	
		} catch (Exception e) {
			logger.error("ConsultationCountController--getsqLoc:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
	}

	/**
	 * 根据机构、申请科室获取会诊机构
	 * @param orgId
	 * @param locId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getHzOrg.ajax")
    @ResponseBody
	public JSONObject getHzOrg(String orgId,String locId,String conorgId,HttpServletRequest request) {
		JSONObject jsonObj = new JSONObject();
		try {
			QryHzOrgBean[] locBeans = sv.getHzOrg(orgId,locId,conorgId);			
			jsonObj.put("orgBeans", com.ai.common.util.AppframeBeanToJsonUtil.getJsonStringFromBean(locBeans == null ? new QryHzOrgBean[0] : locBeans));
		    	
		} catch (Exception e) {
			logger.error("ConsultationCountController--getHzOrg:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
	}
	
	// 查询会诊统计记录
	@RequestMapping("/queryHzRecordList.ajax")
	@ResponseBody	
	public JSONObject queryHzRecordList(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "rows", defaultValue = "10") int rows,
			String sidx,String sord,
			QryHzRecordListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryHzRecordList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
	
	// 查询会诊综合统计记录
	@RequestMapping("/queryHzRecordMutiList.ajax")
	@ResponseBody	
	public JSONObject queryHzRecordMutiList(@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
		QryHzMultipleListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.queryHzRecordMutiList(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
	
	// 查询会诊综合统计详细记录
	@RequestMapping("/getDetailHzRecord.ajax")
	@ResponseBody	
	public JSONObject getDetailHzRecord(@RequestParam(value = "page", defaultValue = "1") int page,
		@RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
		QryHzMultipleListModel model, HttpServletRequest request,HttpServletResponse response)
			throws Exception { 
		ResultDTO result = new ResultDTO(page, rows);
		result = sv.getDetailHzRecord(model, result,sidx,sord);
		return AjaxUtil.jqGridJson(result);
	}
	
	//获取合计信息
	@RequestMapping("getTotalInfo.ajax")
	@ResponseBody
	public Map<String, String> getTotalInfo(QryHzMultipleListModel searchModel) {
		Map<String, String> map = new HashMap<String,String>();
		try {
			map = sv.getTotalInfo(searchModel);
			map.put("ERRCODE", "0");
		} catch (Exception e) {
			logger.error("获取合计信息：" , e);
			map.put("ERRCODE", "1");
			map.put("ERRINOFO", e.getMessage());
		}
		return map;
	}
	
	
	//--------------------------------------------------------统计分析-会诊信息统计-市级区县工号----------------------------------------------------
	//会诊统计首面初始化
	@RequestMapping("/cityandcounty.html")
	public String cityandcounty(HttpServletRequest request,Model model){
		return "statanalysis/cityandcounty";
	}

	//查询市级级患者检查信息汇总
	@RequestMapping("/queryCityPageList.ajax")
	@ResponseBody
	public JSONObject queryCityPageList(@RequestParam(value = "page", defaultValue = "1") int page,
										  @RequestParam(value = "rows", defaultValue = "10") int rows,String sidx,String sord,
										  QryHzMultipleListModel model) throws Exception {
		//取员工组织机构
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryHzCityPageList(model, result);
		return AjaxUtil.jqGridJson(result);
	}

	//查询区县级会诊信息汇总
	@RequestMapping("/queryCountyPageList.ajax")
    @ResponseBody
    public JSONObject queryCountyPageList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryHzMultipleListModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryHzCountyPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }

		
	//查询科室级别患者检查信息汇总
	@RequestMapping("/queryLocPageList.ajax")
    @ResponseBody
    public JSONObject queryLocPageList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryHzMultipleListModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryhZLocPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }

	//查询患者检查信息
	@RequestMapping("/queryPatientPageList.ajax")
	@ResponseBody
	public JSONObject queryPatientPageList(@RequestParam(value = "page", defaultValue = "1") int page,
										   @RequestParam(value = "rows", defaultValue = "10") int rows,
										   QryHzMultipleListModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryhZPatientPageList(model, result);
		return AjaxUtil.jqGridJson(result);
	}
	
	//新会诊统计导出
	@RequestMapping(value="exportHzStudyInfo.ajax")
    public String exportStudyInfo(QryHzMultipleListModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="会诊信息统计";
        //填充projects数据
        String exportMark = request.getParameter("exportMark");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        if(exportMark.equals("1")){
        	short widths[] = {20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getHzCityStudyinfoExcel(model);
        	String[] columnNames = {"城市名称","已申请数量","会诊数量"};//列名
        	String[] keys = {"CITY_DESC","HZ_COUNT","HZ_RESULTCOUNT"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("2")){
        	short widths[] = {20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getHzCountyStudyinfoExcel(model);
        	String[] columnNames = {"县区","已申请数量","会诊数量"};//列名
        	String[] keys = {"COUNTY_DESC","HZ_COUNT","HZ_RESULTCOUNT"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("3")){
        	short widths[] = {20,20,30,30,20,20};//列宽
        	List<Map<String,Object>> list = sv.getHzLocStudyinfoExcel(model);
        	String[] columnNames = {"县区","机构ID","机构名称","科室名称","已申请数量","会诊数量"};//列名
        	String[] keys = {"COUNTY_DESC","ORG_ID","ORG_NAME","LOC_DESC","HZ_COUNT","HZ_RESULTCOUNT"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("4")){
        	short widths[] = {20,30,20,20,20,20,20,20,20,20,20,20,20,30};//列宽
        	List<Map<String,Object>> list = sv.getHzPatientStudyinfoExcel(model);
        	String[] columnNames = {"县区","机构名称","科室名称","病人ID","姓名","年龄","性别","检查项目","申请医生","申请时间","会诊机构","会诊科室","会诊医生","会诊时间"};//列名
        	String[] keys = {"COUNTY_DESC","ORG_NAME","LOC_DESC","PATIENT_ID","PATIENT_NAME","PATIENT_AGE","PATIENT_SEX","CHECK_ITEM","CAREPROV_NAME","STUDY_APPDATE","STUDY_CONSULTORGNAME","STUDY_CONSULTLOCNAME","REPORT_DOC","REPORT_DATETIME"};//map中的key
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
	//机构会诊信息统计导出
	@RequestMapping(value="exportHzInit.ajax")
    public String exportHzInit(QryHzRecordListModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="机构会诊信息统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {20,20,20,20,20,20,20,20,20,20,20};//列宽
    	List<Map<String,Object>> list = sv.getExportHzInit(model);
    	String[] columnNames = {"姓名","性别","年龄","机构名称","申请科室","申请医生","申请时间","会诊机构","会诊科室","会诊医生","会诊时间（报告时间）"};//列名
    	String[] keys = {"PATIENT_NAME","PATIENT_SEX","PATIENT_AGE","ORG_NAME","LOC_DESC","OPERATOR_NAME","STUDY_DATETIME","STUDY_CONSULTORGNAME","STUDY_CONSULTLOCNAME","CAREPROV_NAME","REPORT_DATETIME"};//map中的key
    	try {
            ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
        } catch (IOException e) {
            e.printStackTrace();
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
	
	//机构会诊综合统计导出
	@RequestMapping(value="exportHzMultiple.ajax")
    public String exportHzMultiple(QryHzMultipleListModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="机构会诊综合信息统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {10,20,10,10};//列宽
    	List<Map<String,Object>> list = sv.getExportHzMultiple(model);
    	String[] columnNames = {"区县","日期","会诊申请数量","已会诊数量"};//列名
    	String[] keys = {"COUNTY_DESC","STUDY_DATETIME","HZSQSL","HZSL"};//map中的key
    	try {
            ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
        } catch (IOException e) {
            e.printStackTrace();
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
}
