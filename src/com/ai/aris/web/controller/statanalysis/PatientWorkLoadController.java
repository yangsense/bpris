package com.ai.aris.web.controller.statanalysis;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckCountyrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckLocrpModel;
import com.ai.aris.server.statanalysis.model.AiscPatientCheckRpModel;
import com.ai.aris.server.statanalysis.service.interfaces.IPatientWorkloadListSV;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.ExcelUtil;

/**
 * 机构病人检查信息统计
 */
@Controller
@RequestMapping("/patientWorkLoad")
public class PatientWorkLoadController {
	
	private Log logger = LogFactory.getLog(PatientWorkLoadController.class);
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory
			.getService(IDictItemSV.class);
	private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
    private IPatientWorkloadListSV sv = (IPatientWorkloadListSV) ServiceFactory.getService(IPatientWorkloadListSV.class);
	
    //设备工作量首面初始化
	@RequestMapping("/init.html")
	public String init(HttpServletRequest request,Model model){
//		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));

		return "statanalysis/PatientWorkLoad";
	}

	//查询市级级患者检查信息汇总
	@RequestMapping("/queryCityPageList.ajax")
	@ResponseBody
	public JSONObject queryCityPageList(@RequestParam(value = "page", defaultValue = "1") int page,
										  @RequestParam(value = "rows", defaultValue = "10") int rows,
										  AiscPatientCheckCountyrpModel model) throws Exception {
		//取员工组织机构
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryCityPageList(model, result);
		return AjaxUtil.jqGridJson(result);
	}

	//查询区县级患者检查信息汇总
	@RequestMapping("/queryCountyPageList.ajax")
    @ResponseBody
    public JSONObject queryCountyPageList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscPatientCheckCountyrpModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryCountyPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }

	
	//查询科室级别患者检查信息汇总
	@RequestMapping("/queryLocPageList.ajax")
    @ResponseBody
    public JSONObject queryLocPageList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
										 AiscPatientCheckLocrpModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryLocPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }

	//查询患者检查信息
	@RequestMapping("/queryPatientPageList.ajax")
	@ResponseBody
	public JSONObject queryPatientPageList(@RequestParam(value = "page", defaultValue = "1") int page,
										   @RequestParam(value = "rows", defaultValue = "10") int rows,
										   AiscPatientCheckRpModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryPatientPageList(model, result);
		return AjaxUtil.jqGridJson(result);
	}

	//导出
	@RequestMapping(value="exportStudyInfo.ajax")
    public String exportStudyInfo(AiscPatientCheckCountyrpModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="机构病人检查信息统计";
        //填充projects数据
        String exportMark = request.getParameter("exportMark");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        if(exportMark.equals("1")){
        	short widths[] = {20,20,20,20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getCityStudyinfoExcel(model);
        	String[] columnNames = {"城市名称","登记数量","采集人次","图像数量","报告数量","审核数量"};//列名
        	String[] keys = {"CITY_DESC","REGIST_NUM","COLLECT_NUM","IMAGE_NUM","REPORT_NUM","CHECKED_NUM"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("2")){
        	short widths[] = {20,20,20,20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getCountyStudyinfoExcel(model);
        	String[] columnNames = {"县区","登记数量","采集人次","图像数量","报告数量","审核数量"};//列名
        	String[] keys = {"COUNTY_DESC","REGIST_NUM","COLLECT_NUM","IMAGE_NUM","REPORT_NUM","CHECKED_NUM"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("3")){
        	short widths[] = {20,20,30,30,20,20,20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getLocStudyinfoExcel(model);
        	String[] columnNames = {"县区","机构ID","机构名称","科室名称","登记数量","采集人次","图像数量","报告数量","审核数量"};//列名
        	String[] keys = {"COUNTY_DESC","ORG_ID","ORG_DESC","LOC_DESC","REGIST_NUM","COLLECT_NUM","IMAGE_NUM","REPORT_NUM","CHECKED_NUM"};//map中的key
        	try {
                ExcelUtil.createWorkBook(list,keys,columnNames,widths).write(os);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(exportMark.equals("4")){
        	short widths[] = {20,20,20,20,20,20,20,20,20,20,20};//列宽
        	List<Map<String,Object>> list = sv.getPatientStudyinfoExcel(model);
        	String[] columnNames = {"县区","机构名称","科室名称","登记号","姓名","年龄","性别","检查项目","图像数量","报告医生","审核医生"};//列名
        	String[] keys = {"COUNTY_DESC","ORG_DESC","LOC_DESC","PATIENT_ID","PATIENT_NAME","PATIENT_AGE","PATIENT_SEX","CHECK_ITEM","STUDY_IMAGECOUNT","REPORT_DOC","CHECK_DOC"};//map中的key
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

}
