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
import com.ai.aris.server.statanalysis.model.QryEquiWorkloadModel;
import com.ai.aris.server.statanalysis.service.interfaces.IEquiWorkloadListSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.ExcelUtil;


@Controller
@RequestMapping("/equiWorkload")
public class EquiWorkloadController {
	
	private Log logger = LogFactory.getLog(EquiWorkloadController.class);
    private IEquiWorkloadListSV sv = (IEquiWorkloadListSV) ServiceFactory.getService(IEquiWorkloadListSV.class);
    private QryEquiWorkloadModel model = new QryEquiWorkloadModel();
	
    //设备工作量首面初始化
	@RequestMapping("equiInit.html")
	public String equiInit(HttpServletRequest request,Model model){		
		return "statanalysis/equiWorkload";
	}
	
	//查询设备工作量列表记录
	@RequestMapping("/queryEquiWorklistList.ajax")
    @ResponseBody
    public JSONObject queryEquiWorklistList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       QryEquiWorkloadModel model) throws Exception {
		//取员工组织机构	
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	
	/**
	 * 获取设备工作量图表数据
	 */
	@RequestMapping("getEquiWorkCharts.ajax")
	@ResponseBody
	public JSONObject getEquiWorkCharts(QryEquiWorkloadModel searchModel) {
		JSONObject jsonObject = new JSONObject();
		try {
			QryEquiWorkChartsBean[] beans = sv.getEquiWorkCharts(searchModel);
			int leng = beans.length;
			String[] workX = new String[leng];
			float[] useCount = new float[leng];
			float[] useFee = new float[leng];
			
			for (int i = 0; i < leng; i++) {
				QryEquiWorkChartsBean bean = beans[i];
				workX[i] = bean.getEquipmentDesc();
				useCount[i] = bean.getStudyitemNumber();
				useFee[i] = bean.getStudyitemPrice();
			}
			//设备使用量
			jsonObject.put("workX", workX);		 	//X轴
			jsonObject.put("useCount", useCount);		//检查数量
			jsonObject.put("useFee", useFee);	//检查费用
			
		} catch (Exception e) {
			logger.error("EquiWorkloadController----------->>getEquiWorkCharts:",e);
		}
		return jsonObject;
	}
	
	
	public QryEquiWorkloadModel getModel() {
		return model;
	}

	public void setModel(QryEquiWorkloadModel model) {
		this.model = model;
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
	
	//导出
	@RequestMapping(value="exportEquiWork.ajax")
    public String exportEquiWork(QryEquiWorkloadModel model,HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="设备工作量统计";
        //填充projects数据
        ByteArrayOutputStream os = new ByteArrayOutputStream();
    	short widths[] = {20,20,20,20,20};//列宽
    	List<Map<String,Object>> list = sv.getExportEquiWork(model);
    	String[] columnNames = {"检查日期","设备类型","检查数量","总费用","阳性率"};//列名
    	String[] keys = {"C_STUDY_DATETIME","MODALITY_TYPE","STUDYITEM_NUMBER","STUDYITEM_PRICE","ZB"};//map中的key
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
