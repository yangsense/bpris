package com.ai.aris.web.controller.basecode;

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
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscCareProvBean;
import com.ai.aris.server.basecode.bean.AiscConorganizationBean;
import com.ai.aris.server.basecode.bean.AiscLocBean;
import com.ai.aris.server.basecode.bean.QueryOrgLocBean;
import com.ai.aris.server.basecode.model.AiscConsult;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.service.interfaces.IAiscLocSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscRoomSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscServerInfoSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.webservice.bean.SysOrg;
import com.ai.aris.server.webservice.service.interfaces.IOrgSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.ExcelUtil;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscLocController {
	private IAiscLocSV sv = (IAiscLocSV) ServiceFactory.getService(IAiscLocSV.class);
	private Log logger = LogFactory.getLog(AiscLocController.class);
	private IAiscServerInfoSV serverSv = (IAiscServerInfoSV) ServiceFactory.getService(IAiscServerInfoSV.class);
	
	private IAiscRoomSV roomSv = (IAiscRoomSV) ServiceFactory.getService(IAiscRoomSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	
	@Autowired
    private IOrgSV orgSV;
	
	/**
	 * 机构列表加载（系统管理可公用）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOrgListByOper.ajax")
    @ResponseBody
    public String getOrgListByOper(HttpSession httpSession) throws Exception {
		String orgs = (String)httpSession.getAttribute(Constants.SESSION_MANAGE_ORG);
        return orgs;
    }
	
	/**
	 * 科室信息页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscLocInit")
    public String aiscLocInit() {
        return "basecode/aiscLoc/aiscLocList";
    }
	
	/**
	 * 科室信息查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscLocList.ajax")
    @ResponseBody
    public JSONObject queryAiscLocList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscLoc model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 科室类型下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscLocType.ajax")
    @ResponseBody
    public Map getAiscLocType() throws Exception {
		List<Map> aiscLocType = dictSV.queryDict("LOC_TYPE");
        Map map = new HashMap();
        map.put("AiscLocType", JsonUtil.toJson(aiscLocType));
        return map;
    }
	
	/**
	 * 科室名称下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscLocName.ajax")
    @ResponseBody
    public Map getAiscLocName(@RequestParam String orgId) throws Exception {
		List<Map> aiscLocName = sv.getLocNameSelect(orgId);
        Map map = new HashMap();
        map.put("AiscLocName", JsonUtil.toJson(aiscLocName));
        return map;
    }
	
	/**
	 * 根据科室类型查询科室下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscLocByType.ajax")
    @ResponseBody
    public Map getAiscLocByType(@RequestParam String orgId,String locType) throws Exception {
		List<Map> aiscLocName = sv.getAiscLocByType(orgId,locType);
        Map map = new HashMap();
        map.put("AiscLocName", JsonUtil.toJson(aiscLocName));
        return map;
    }
	
	/**
	 * 房间名称下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getRoomSelect.ajax")
    @ResponseBody
    public Map getRoomSelect(@RequestParam String orgId,@RequestParam long locId) throws Exception {
		List<Map> roomSelect = roomSv.getRoomSelect(orgId,locId);
        Map map = new HashMap();
        map.put("RoomName", JsonUtil.toJson(roomSelect));
        return map;
    }
	
	/**
	 * 服务器下拉加载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAiscServer.ajax")
    @ResponseBody
    public Map getAiscServer() throws Exception {
		List<Map> aiscServer = serverSv.getServerSelect();
        Map map = new HashMap();
        map.put("AiscServer", JsonUtil.toJson(aiscServer));
        return map;
    }
	/**
	 * 科室信息详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscLocDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscLocBean aiscLocBean = null;
		if(id!=0){
			 aiscLocBean = sv.getAiscLoc(id);
		}
		model.addAttribute("aiscLocBean",aiscLocBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscLoc/aiscLocDetail";
    }
	
	/**
	 * 会诊机构设置init
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/consultationOrgSet.html")
    public String consultationOrgSet(@RequestParam String locId,@RequestParam String orgId,Model model) throws Exception {
		//查询当前机构和科室名称
		QueryOrgLocBean bean = sv.getOrgLoc(orgId,locId);
		//查询会诊机构配置信息
//		queryAiscConorganizationBean [] queryAiscConorganization = sv.getOrgLoc(orgId,locId);
//		if(id!=0){
//			 aiscLocBean = sv.getAiscLoc(id);
//		}
//		model.addAttribute("aiscLocBean",aiscLocBean);
        model.addAttribute("QueryOrgLocBean",bean);
        return "basecode/aiscLoc/consultationOrgSet";
    }
	
	/**
	 * 会诊机构配置list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscConsult.ajax")
    @ResponseBody
    public JSONObject queryAiscConsult(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscConsult model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryAiscConsultList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	//会诊机构
    @RequestMapping("getHuizhenOrg.ajax")
    @ResponseBody
    public Map getHuizhenOrg(String orgId,String locId,String keyword) {    	   
    	Map map = new HashMap();
    	List<SysOrg>  huizhenOrgList = null;
		try {
			huizhenOrgList = sv.getHuizhenOrg(orgId,locId,keyword);
		} catch (Exception e) {
			logger.error("AiscLocController--getHuizhenOrg:", e);
			e.printStackTrace();
		}       
		map.put("huizhenOrgList", JsonUtil.toJson(huizhenOrgList));
        return map;
    }
    
    //根据会诊机构查会诊科室
    @RequestMapping("getLocForOrg.ajax")
    @ResponseBody
    public Map getLocForOrg(String orgId) throws Exception {    	   
    	List<Map> conlocBeans = sv.getLocForOrg(orgId);
        Map map = new HashMap();
        map.put("conlocBeans", JsonUtil.toJson(conlocBeans));
        return map;
    }
    
    /**
	 * 保存会诊机构信息设置
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscConOrg")
	@ResponseBody
    public Map saveAiscConOrg(@RequestBody AiscConorganizationBean bean) throws Exception {
		sv.saveConorganization(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
	
	/**
	 * 删除会诊机构信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisConorg.ajax")
	@ResponseBody
    public Map deleteAisConorg(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAisConorg(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	
	/**
	 * 删除科室信息
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisLoc.ajax")
	@ResponseBody
    public Map deleteAisLoc(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscLoc(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改科室信息
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscLoc")
	@ResponseBody
    public Map saveAiscLoc(@RequestBody AiscLocBean bean) throws Exception {
		sv.saveAiscLoc(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }


	/**
	 * 检查机构和科室是否存在
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkAiscLoc.ajax")
	@ResponseBody
	public Map checkAiscLoc(AiscLocBean bean) throws Exception {
		Boolean flag  = sv.checkIsExist(bean);
		Map<String,String> map = new HashMap();
		map.put("result", flag+"");
		return map;
	}


	//	checkLocCode
	  //检测code码所对应的检查项目是否已经存在
    @RequestMapping("/checkLocCode.ajax")
    @ResponseBody
    public boolean checkLocCode(@RequestParam long locId,@RequestParam String locCode,@RequestParam String orgId) throws Exception{
        return  sv.checkLocCode(locId,locCode,orgId);
    }
    
    @RequestMapping("/getLocId.ajax")
    @ResponseBody
    public JSONObject getLocId(@RequestParam String orgId,@RequestParam String locCode) throws Exception{
       
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscLocBean locBean = sv.getAiscLoc(orgId,locCode);			
			jsonObj.put("LOC_ID", locBean.getLocId());	
		} catch (Exception e) {
			logger.error("AiscLocController--getLocId:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
    } 
    //根据人员code和orgId获取人员ID
    @RequestMapping("/getCareprovId.ajax")
    @ResponseBody
    public JSONObject getCareprovId(@RequestParam String orgId,@RequestParam String careprovCode) throws Exception{
       
    	JSONObject jsonObj = new JSONObject();
		try {
			AiscCareProvBean careBean = sv.getCareprovId(orgId,careprovCode);			
			jsonObj.put("CAREPROV_ID", careBean.getCareprovId());	
		} catch (Exception e) {
			logger.error("AiscLocController--getCareprovId:", e);
			e.printStackTrace();
		}       
	     return jsonObj; 
    } 
    //导入科室
	@RequestMapping("importLoc.ajax")
	@ResponseBody
	public JSONObject importLoc(HttpServletRequest request) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("ERRCODE", "0");
		try {
			String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
			String result = sv.importLoc(orgId);
			jsonRes.put("ERRCODE", result);
		} catch (Exception e) {
			logger.error("导入科室importLoc：", e);
			jsonRes.put("ERRCODE", "1");
			jsonRes.put("ERRINFO", e.getMessage());
		}
		return jsonRes;
	}
	
	//导出前检查数据源
	@RequestMapping("exportCheck.ajax")
	@ResponseBody
    public JSONObject exportCheck(HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("ERRCODE", "0");
		try {
			String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
	        //填充projects数据
	        List<Map<String,Object>> list = sv.getHisLocList(orgId);
		} catch (Exception e) {
			jsonRes.put("ERRCODE", "1");
			jsonRes.put("ERRINFO", e.getMessage());
		}
		return jsonRes;
	}
	
	//导出科室
	@RequestMapping(value="exportLoc.ajax")
    public String exportLoc(HttpServletRequest request,HttpServletResponse response) throws IOException,Exception{
        String fileName="科室信息";
        String orgId = request.getSession().getAttribute(Constants.SESSION_MANAGE_ORG_ID).toString();
        //填充projects数据
        List<Map<String,Object>> list = sv.getHisLocList(orgId);
        String columnNames[] = {"科室代码","科室名称","机构代码"};//列名
        String keys[] = {"locCode","locDesc","orgCode"};//map中的key
        short widths[] = {20,10,20};//列宽
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
