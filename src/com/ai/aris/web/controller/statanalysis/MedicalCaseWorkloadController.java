package com.ai.aris.web.controller.statanalysis;
 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.statanalysis.model.QryMedicalCaseWorkloadModel;
import com.ai.aris.server.statanalysis.service.interfaces.IMedicalCaseWorkloadListSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.ExcelUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/medicalCaseWorkload")
public class MedicalCaseWorkloadController {
	
	private Log logger = LogFactory.getLog(MedicalCaseWorkloadController.class);
    private IMedicalCaseWorkloadListSV sv = (IMedicalCaseWorkloadListSV) ServiceFactory.getService(IMedicalCaseWorkloadListSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    
    //设备工作量首面初始化
	@RequestMapping("init.html")
	public String init(){		
		return "statanalysis/medicalCaseWorkload";
	}
	
	//查询设备工作量列表记录
	@RequestMapping("/queryMedicalCaseList.ajax")
    @ResponseBody
    public JSONObject queryMedicalCaseList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryMedicalCaseWorkloadModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	//阴阳性下拉列表加载
	@RequestMapping("/getIspositive.ajax")
    @ResponseBody
    public Map getStudyStatus() throws Exception {
		List<Map> status = dictSV.queryDict("ISPOSITIVE");
        Map map = new HashMap();
        map.put("ISPOSITIVE", JsonUtil.toJson(status));
        return map;
    }
	
	//获取合计信息
	@RequestMapping("getTotalInfo.ajax")
	@ResponseBody
	public Map<String, String> getTotalInfo(QryMedicalCaseWorkloadModel searchModel) {
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
	
	//导出
	@RequestMapping(value="exportMedicalCase.ajax")
    public String exportMedicalCase(QryMedicalCaseWorkloadModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="病案统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {10,10,10,20,50,50,20,20,20};//列宽
    	List<Map<String,Object>> list = sv.getEexportMedicalCase(model);
    	String[] columnNames = {"姓名","性别","年龄","检查项目","检查结果","诊断意见","报告医生","报告日期","审核医生"};//列名
    	String[] keys = {"PATIENT_NAME","PATIENT_SEX","PATIENT_AGE","STUDY_ITEMDESC","REPORT_EXAM","REPORT_RESULT","REPORT_DOCTORNAME","REPORT_DATETIME","VERIFYDOCTOR_NAME"};//map中的key
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
