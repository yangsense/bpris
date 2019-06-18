package com.ai.aris.web.controller.basecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscItemMastBean;
import com.ai.aris.server.basecode.model.AiscItemMastModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscItemMastSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.Constants;
import com.ai.common.util.ExcelUtil;
import com.ai.common.util.JsonUtil;
import com.ai.common.util.PyFirstLetter;

/**
 * Created by fins on 2015/10/16.
 */
@Controller
@RequestMapping("/aiscItemMast")
public class AiscItemMastController {
    private IAiscItemMastSV sv=(IAiscItemMastSV) ServiceFactory.getService(IAiscItemMastSV.class);
    private IDictItemSV dictItemSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    private Log logger = LogFactory.getLog(AiscItemMastController.class);

    @RequestMapping(value = "/page/{one}")
    public String getPage(@PathVariable String one,@RequestParam(value="id",required=false) String id,
                          @RequestParam(value="opType",required=false) String opType,Map map) throws NumberFormatException, Exception{
        map.put("id",id);
        map.put("opType",opType);
        
        if(id!=null&&!id.equals("")){
        	AiscItemMastModel model = sv.getAiscItemMast(Long.parseLong(id));
        	 map.put("AiscItemMastModel",model);
        }
        
        return "basecode/aiscItemMast/"+one;
    }

    @RequestMapping("/getQueryCondition")
    @ResponseBody
    public Map getQueryCondition() throws Exception {
        List<Map> service = dictItemSV.queryDict("ITEMMAST_SERVICE");
        List<Map> isEnhanced = dictItemSV.queryDict("ITEMMAST_ISENHANCED");
        Map map = new HashMap();
        map.put("service", JsonUtil.toJson(service));
        map.put("isEnhanced", JsonUtil.toJson(isEnhanced));
        return map;
    }

    @RequestMapping("/getAiscItemMastList.ajax")
    @ResponseBody
    public ResultDTO getAiscItemMastList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "rows", defaultValue = "10") int rows,
                                         AiscItemMastModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return result;
    }

    @RequestMapping("/saveAiscItemMast")
    @ResponseBody
    public Map saveAiscItemMast(@RequestBody AiscItemMastBean bean,HttpServletRequest request) throws Exception {
        sv.saveAiscItemMast(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
    
    @RequestMapping("/getPym.ajax")
    @ResponseBody
    public Map getPym(String itemmastName) throws Exception {
    	Map map = new HashMap();
    	String pymName = "";
        try {
			PyFirstLetter py = new PyFirstLetter();
			String name = URLDecoder.decode(itemmastName,"UTF-8"); 
			pymName = py.getUpEname(name);
        } catch (Exception e) {
			e.printStackTrace();
        }
        map.put("pymName", pymName);
        return map;
    }

    /**
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping("/checkAiscItemMast.ajax")
    @ResponseBody
    public Map checkAiscItemMast(AiscItemMastBean bean) throws Exception {
        Boolean flag  = sv.checkIsExist(bean);
        Map<String,String> map = new HashMap();
        map.put("result", flag+"");
        return map;
    }
    @RequestMapping("/getAiscItemMast")
    @ResponseBody
    public AiscItemMastModel getAiscItemMast( @RequestParam long id) throws Exception {
        AiscItemMastModel model = sv.getAiscItemMast(id);
        return model;
    }

    
    //checkItemmastCode
    
    //检测code码所对应的检查项目是否已经存在
    @RequestMapping("/checkItemmastCode.ajax")
    @ResponseBody
    public boolean checkItemmastCode(@RequestParam long itemmastId,@RequestParam String itemmastCode,String orgId) throws Exception{
        return sv.checkItemmastCode(itemmastId,itemmastCode,orgId);
    }
    
    
    @RequestMapping("/deleteAiscItemMast")
    @ResponseBody
    public Map deleteAiscItemMast(@RequestParam long id) throws Exception{
        Map map = new HashMap();
        boolean flag = sv.deleteAiscItemMast(id);
        if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
    //导入项目
  	@RequestMapping("importItem.ajax")
  	@ResponseBody
  	public JSONObject importItem(HttpServletRequest request,long ordcategoryId,long ordsubcategoryId,String locCode) {
  		JSONObject jsonRes = new JSONObject();
  		jsonRes.put("ERRCODE", "0");
  		try {
  			String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
  			String result = sv.importItem(orgId,ordcategoryId, ordsubcategoryId,locCode);
  			jsonRes.put("ERRCODE", result);
  		} catch (Exception e) {
  			logger.error("导入项目importLoc：", e);
  			jsonRes.put("ERRCODE", "1");
  			jsonRes.put("ERRINFO", e.getMessage());
  		}
  		return jsonRes;
  	}
  	
  	//导出前检查数据源
	@RequestMapping("exportCheckMask.ajax")
	@ResponseBody
	  public JSONObject exportCheckMask(HttpServletRequest request,HttpServletResponse response,long ordcategoryId,long ordsubcategoryId,String locCode) throws IOException,Exception{
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("ERRCODE", "0");
		try {
			String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
	        //填充projects数据
			List<Map<String,Object>> list = sv.getHisItem(orgId, ordcategoryId, ordsubcategoryId, locCode);
		} catch (Exception e) {
			jsonRes.put("ERRCODE", "1");
			jsonRes.put("ERRINFO", e.getMessage());
		}
		return jsonRes;
	}
  	
  	//导出项目
  	@RequestMapping(value="exportItem.ajax")
    public String exportItem(HttpServletRequest request,HttpServletResponse response,long ordcategoryId,long ordsubcategoryId,String locCode) throws IOException,Exception{
        String fileName="检查项目";
        String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
        //填充projects数据
        List<Map<String,Object>> list = sv.getHisItem(orgId, ordcategoryId, ordsubcategoryId, locCode);
        String columnNames[] = {"项目代码","项目名称","机构代码","执行科室代码"};//列名
        String keys[] = {"itemmastCode","itemmastDesc","orgCode","zxksdm"};//map中的key
        short widths[] = {38,38,20,13};//列宽
        ByteArrayOutputStream os = new ByteArrayOutputStream();
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
