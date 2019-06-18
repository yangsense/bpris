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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.statanalysis.bean.QryEquiWorkChartsBean;
import com.ai.aris.server.statanalysis.model.QryDoctorWorkloadModel;
import com.ai.aris.server.statanalysis.service.interfaces.IDoctorWorkloadListSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.ExcelUtil;


@Controller
@RequestMapping("/doctorWorkload")
public class DoctorWorkloadController {
	
	private Log logger = LogFactory.getLog(DoctorWorkloadController.class);
    private IDoctorWorkloadListSV sv = (IDoctorWorkloadListSV) ServiceFactory.getService(IDoctorWorkloadListSV.class);
 	
    //医生工作量首面初始化
	@RequestMapping("init.html")
	public String init(){		
		return "statanalysis/doctorWorkload";
	}
	
	//查询医生工作量列表记录
	@RequestMapping("/queryDoctorWorklistList.ajax")
    @ResponseBody
    public JSONObject queryDoctorWorklistList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryDoctorWorkloadModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 获取医生工作量图表数据
	 */
	@RequestMapping("getDocWorkCharts.ajax")
	@ResponseBody
	public JSONObject getDocWorkCharts(QryDoctorWorkloadModel searchModel) {
		JSONObject jsonObject = new JSONObject();
		try {
			QryEquiWorkChartsBean[] beans = sv.getDocWorkCharts(searchModel);
			int leng = beans.length;
			String[] workX = new String[leng];
			float[] useCount = new float[leng];
			float[] useFee = new float[leng];
			
			for (int i = 0; i < leng; i++) {
				QryEquiWorkChartsBean bean = beans[i];
				workX[i] = bean.getCareprovName()==null?"":bean.getCareprovName();
				useCount[i] = bean.getReportId();
				useFee[i] = bean.getStudyitemPrice();
			}
			//医生工作量
			jsonObject.put("workX", workX);		 	//X轴
			jsonObject.put("useCount", useCount);		//报告数量
			jsonObject.put("useFee", useFee);	//总费用
			
		} catch (Exception e) {
			logger.error("DoctorWorkloadController----------->>getDocWorkCharts:",e);
		}
		return jsonObject;
	}
	
	//获取合计信息
	@RequestMapping("getTotalInfo.ajax")
	@ResponseBody
	public Map<String, String> getTotalInfo(QryDoctorWorkloadModel searchModel) {
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
										 QryDoctorWorkloadModel model) throws Exception {
		ResultDTO result = new ResultDTO(page,rows);
		result = sv.queryStudyInfoList(model, result);
		return AjaxUtil.jqGridJson(result);
	}
	//检查明细
	@RequestMapping("studyInfoList.html")
	public String studyInfoList(HttpServletRequest request, Model model, QryDoctorWorkloadModel searchModel, String reportDoctorid, String reportVerifydoctorid){
		model.addAttribute("studystatusCode", searchModel.getStudystatusCode());
		model.addAttribute("reportDate", searchModel.getReportDate());

		if(reportDoctorid!=null&&!"".equals(reportDoctorid)){
			model.addAttribute("reportDoctorid", reportDoctorid);
		}else{
			model.addAttribute("reportDoctorid", searchModel.getReportDoctorid());
		}
		if(reportVerifydoctorid!=null&&!"".equals(reportVerifydoctorid)){
			model.addAttribute("reportVerifydoctorid", reportVerifydoctorid);
		}else{
			model.addAttribute("reportVerifydoctorid", searchModel.getReportVerifydoctorid());
		}
 		return "statanalysis/doctor_studyInfoList";
	}
	
	//导出
	@RequestMapping(value="exportDoctorWork.ajax")
    public String exportDoctorWork(QryDoctorWorkloadModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="医生工作量统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {20,20,20,20,20,20,20};//列宽
    	List<Map<String,Object>> list = sv.getExportDcotorWork(model);
    	String[] columnNames = {"报告日期","报告医生","审核医生","报告状态","报告数量","总费用","阳性率"};//列名
    	String[] keys = {"REPORT_DATE","REPORT_DOCTORNAME","REPORT_VERIFYDOCTORNAME","STUDYSTATUS_CODE","REPORT_ID","STUDYITEM_PRICE","ZB"};//map中的key
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
