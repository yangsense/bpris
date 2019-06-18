package com.ai.aris.web.controller.statanalysis;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
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
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.model.QryEquiWorkloadModel;
import com.ai.aris.server.statanalysis.model.QryMedicalCaseWorkloadModel;
import com.ai.aris.server.statanalysis.service.interfaces.IEquiWorkloadListSV;
import com.ai.aris.server.workstation.model.QryStudyInfoListModel;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.ExcelUtil;
import com.ai.common.util.JsonUtil;
import com.ai.common.util.ServiceUtil;


@Controller
@RequestMapping("/worklistCount")
public class WorkListCountController {
	
	private Log logger = LogFactory.getLog(WorkListCountController.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
    private IEquiWorkloadListSV sv = (IEquiWorkloadListSV) ServiceFactory.getService(IEquiWorkloadListSV.class);
	
    //设备工作量首面初始化
	@RequestMapping("init.html")
	public String init(HttpServletRequest request,Model model){		
		return "statanalysis/worklistCount";
	}
	
	//查询登记列表记录
	@RequestMapping("/queryWorklistCount.ajax")
    @ResponseBody
    public JSONObject queryWorklistCount(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryStudyInfoListModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryWorkListCountList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	//获取合计信息
	@RequestMapping("getTotalInfo.ajax")
	@ResponseBody
	public Map<String, String> getTotalInfo(QryEquiWorkloadModel searchModel) {
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
	
	//获取检查明细
	@RequestMapping("/queryStudyInfoList.ajax")
    @ResponseBody
    public JSONObject queryStudyInfoList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryEquiWorkloadModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryStudyInfoList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	//检查明细 
	@RequestMapping("studyInfoList.html")
	public String studyInfoList(HttpServletRequest request,Model model,QryEquiWorkloadModel searchModel,String nowequipmentId){
		model.addAttribute("orgId", searchModel.getOrgId());
		model.addAttribute("locId", searchModel.getLocId());
		if(nowequipmentId!=null&&!"".equals(nowequipmentId)){
			model.addAttribute("modalityId", nowequipmentId);
		}else{
			model.addAttribute("modalityId", searchModel.getModalityId());
		}
		model.addAttribute("studyDatetime", searchModel.getStudyDatetime());
		return "statanalysis/studyInfoList";
	}
	
	// 病人类型下拉列表加载
	@RequestMapping("/getPatientType.ajax")
	@ResponseBody
	public Map getPatientType() throws Exception {
		List<Map> status = dictSV.queryDict("PATIENT_TYPE");
		Map map = new HashMap();
		map.put("PatientType", JsonUtil.toJson(status));
		return map;
	}
	
	// 申请科室下拉列表加载
	@RequestMapping("/getLocApply.ajax")
	@ResponseBody
	public Map getLocApply(String orgId) throws Exception {
		AiscLocBean[] locBeans = commonDataSv.getLocInfo(orgId,"A");//Loc_Type=A 科室
		List<Map> list = new ArrayList();
		for(AiscLocBean bean:locBeans){
			list.add(ServiceUtil.transerBeanToMap(bean));
		}
		Map map = new HashMap();
		map.put("Loc_Apply", JsonUtil.toJson(list));
		return map;
	}
	//导出
	@RequestMapping(value="exportWorklistCount.ajax")
    public String exportWorklistCount(QryStudyInfoListModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="登记列表统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {10,10,10,10,10,20,10,10,10,10,10,20,10,20};//列宽
    	List<Map<String,Object>> list = sv.getEexportWorklistCount(model);
    	String[] columnNames = {"登记号","病人姓名","性别","年龄","病人类型","门诊号/住院号/体检号","设备名称","申请科室","申请医生","床号","检查号","检查项目","检查部位","登记时间"};//列名
    	String[] keys = {"STUDYINFO_ID","PATIENT_NAME","PATIENT_SEX","PATIENT_AGE","PATIENTTYPE_CODE","PATIENT_INPATIENTID","EQUIPMENT_DESC","LOC_DESC","STUDY_APPDOCNAME","STUDY_BEDNO","STUDY_ACCNUMBER","STUDY_ITEMDESC","BODYITEM","C_STUDY_OPERATIONTIME"};//map中的key
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
