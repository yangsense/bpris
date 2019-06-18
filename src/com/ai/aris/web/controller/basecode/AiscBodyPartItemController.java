package com.ai.aris.web.controller.basecode;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscBodyPart2ItemBean;
import com.ai.aris.server.basecode.model.AiscBodyPart;
import com.ai.aris.server.basecode.model.AiscBodyPartItem;
import com.ai.aris.server.basecode.model.AiscItemMastModel;
import com.ai.aris.server.basecode.model.AiscLoc;
import com.ai.aris.server.basecode.model.AiscLoginLoc;
import com.ai.aris.server.basecode.service.interfaces.IAiscBodyPartItemSV;
import com.ai.aris.server.basecode.service.interfaces.IAiscItemMastSV;
import com.ai.aris.server.webservice.bean.SysOperator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscBodyPartItemController {
	private IAiscBodyPartItemSV sv = (IAiscBodyPartItemSV) ServiceFactory.getService(IAiscBodyPartItemSV.class);

	/**
	 * 检查部位项目对应页面init
	 * @return
	 */
	@RequestMapping(value = "/aiscBodyPartItemInit")
    public String aiscBodyPartItemInit() {
        return "basecode/aiscBodyPartItem/aiscBodyPartItemList";
    }
	
	/**
	 * 检查部位项目对应查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryAiscBodyPartItemList.ajax")
    @ResponseBody
    public JSONObject queryAiscBodyPartItemList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscBodyPartItem model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryPageList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	/**
	 * 检查部位项目对应详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aiscBodyPartItemDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscBodyPart2ItemBean aiscBodyPartItemBean = null;
		if(id!=0){
			 aiscBodyPartItemBean = sv.getAiscBodyPartItem(id);
		}
		model.addAttribute("aiscBodyPartItemBean",aiscBodyPartItemBean);
        model.addAttribute("dataType",type);
        return "basecode/aiscBodyPartItem/aiscBodyPartItemDetail";
    }
	
	/**
	 * 删除检查部位项目对应
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAisBodyPartItem.ajax")
	@ResponseBody
    public Map deleteAisBodyPartItem(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		boolean flag = sv.deleteAiscBodyPartItem(id);
		if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }
	/**
	 * 新增或修改检查部位项目对应
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAiscBodyPartItem")
	@ResponseBody
    public Map saveAiscBodyPartItem(@RequestParam String orgId,@RequestParam long itemmastId,@RequestParam String selectOption) throws Exception {
		try{
			sv.deleAiscBodyPartItem(orgId,itemmastId);
//			//插入检查项目与部位大类关联表aisc_bodypart2item
//			if(isNotBlank(selectBigOption)){
//				String bodyBigPartId[] = selectBigOption.split(",");
//				for(int i=0;i<bodyBigPartId.length;i++){
//					sv.saveAiscBodyPartItem(itemmastId,orgId,bodyBigPartId[i]);
//				}
//			}
//			//插入检查项目与部位关联表aisc_bodypart2item
			String bodyPartId[] = selectOption.split(",");
			for(int i=0;i<bodyPartId.length;i++){
				sv.saveAiscBodyPartItem(itemmastId,orgId,bodyPartId[i]);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
//		for(int i=0;i<bodyPartId.length;i++){
//			AiscBodyPart2ItemBean bean =new AiscBodyPart2ItemBean() ;
//			bean.setOrgId(orgId) ;
//			bean.setItemmastId(itemmastId) ;
//			bean.setBodypartCode(bodyPartId[i]) ;
//			sv.saveAiscBodyPartItem(bean);
//		}
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

	
	/**
	 * 新增时加载列表
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getBodyPartList.ajax")
	@ResponseBody
    public Map getBodyPartList(@RequestParam long ordcategoryId,@RequestParam long ordsubcategoryId,@RequestParam long itemmastId,@RequestParam String orgId,@RequestParam long bodypartPid) throws Exception {
		Map map = new HashMap();
		IAiscItemMastSV iAiscItemMastSV=(IAiscItemMastSV) ServiceFactory.getService(IAiscItemMastSV.class);

		//医嘱列表查询
		List<Map> itemMastList = iAiscItemMastSV.getAiscItemMastSelect(ordcategoryId,ordsubcategoryId,orgId);
        map.put("itemMastList", JsonUtil.toJson(itemMastList));
		//未选部位列表查询
//        List<Map> bodyPartUnSelect =null ;
//        if (ordcategoryId!=-1||ordsubcategoryId!=-1) {
//        	bodyPartUnSelect = sv.getAiscBodyPartUnSelect(itemMastList ,itemmastId,orgId,"2",bodypartPid);
//		}else {
//			bodyPartUnSelect = sv.getAiscBodyPartUnSelect(null ,itemmastId,orgId,"2",bodypartPid);
//		}
//        map.put("bodyPartUnSelect", JsonUtil.toJson(bodyPartUnSelect));
//    	//已选部位列表查询
//        List<Map> bodyPartSelected =null ;
//        if (ordcategoryId!=-1||ordsubcategoryId!=-1) {
//        	bodyPartSelected = sv.getAiscBodyPartSelected(itemMastList ,itemmastId,orgId,"2",bodypartPid);
//		}else {
//			bodyPartSelected = sv.getAiscBodyPartSelected(null ,itemmastId,orgId,"2",bodypartPid);
//		}
//        map.put("bodyPartSelected", JsonUtil.toJson(bodyPartSelected));
        return map;
        
    }
	
	/**
	 * 加载部位列表
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getBodyPartDetailList.ajax")
	@ResponseBody
    public Map getBodyPartDetailList(@RequestParam long ordcategoryId,@RequestParam long ordsubcategoryId,@RequestParam long itemmastId,@RequestParam String orgId,@RequestParam long bodypartPid) throws Exception {
		Map map = new HashMap();
		//未选部位列表查询
        List<Map> bodyPartUnSelect =null ;
        if (ordcategoryId!=-1||ordsubcategoryId!=-1) {
        	bodyPartUnSelect = sv.getAiscBodyPartUnSelect(null ,itemmastId,orgId,"2",bodypartPid);
		}else {
			bodyPartUnSelect = sv.getAiscBodyPartUnSelect(null ,itemmastId,orgId,"2",bodypartPid);
		}
        map.put("bodyPartUnSelect", JsonUtil.toJson(bodyPartUnSelect));
    	//已选部位列表查询
        List<Map> bodyPartSelected =null ;
        if (ordcategoryId!=-1||ordsubcategoryId!=-1) {
        	bodyPartSelected = sv.getAiscBodyPartSelected(null ,itemmastId,orgId,"2",bodypartPid);
		}else {
			bodyPartSelected = sv.getAiscBodyPartSelected(null ,itemmastId,orgId,"2",bodypartPid);
		}
        map.put("bodyPartSelected", JsonUtil.toJson(bodyPartSelected));
        return map;
        
    }
	
}
