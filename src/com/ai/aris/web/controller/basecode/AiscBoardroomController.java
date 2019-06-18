package com.ai.aris.web.controller.basecode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscBoardroomBean;
import com.ai.aris.server.basecode.model.AiscBoardroomModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscBoardroomSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room;
import com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.JsonUtil;


@Controller
@RequestMapping("/basecode")
public class AiscBoardroomController {
	private Log logger = LogFactory.getLog(AiscBoardroomController.class);
	
	private IAiscBoardroomSV boardroomSv = (IAiscBoardroomSV) ServiceFactory.getService(IAiscBoardroomSV.class);
	
	private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
	
	/**
	 * 会议室页面init
	 * @return
	 */
	@RequestMapping(value = "/boardroomInit")
    public String aiscLocInit() {
        return "basecode/boardroom/boardroomInit";
    }
	
	/**
	 * 会议室查询list
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/queryBoardroomList.ajax")
    @ResponseBody
    public JSONObject queryBoardroomList(@RequestParam(value = "page", defaultValue = "1") int page,
                                       @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       AiscBoardroomModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = boardroomSv.queryBoardroomList(model, result);
        return AjaxUtil.jqGridJson(result);
    }
	
	/**
	 * 新增或修改会议室
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBoardroom")
	@ResponseBody
    public Map saveBoardroom(@RequestBody AiscBoardroomBean bean) throws Exception {
		String roomId = boardroomSv.saveBoardroom(bean);
		Map<String,String> map = new HashMap();
		map.put("roomId", roomId);
		return map;
    }
	
	@RequestMapping("/boardUserAdd")
    @ResponseBody
    public JSONObject boardUserAdd(String userCode,String userName,String orgId,HttpServletResponse rsp) {
        JSONObject json = new JSONObject();
        try {
            byte[] b = Base64.decodeBase64(userName);
            String name1 = new String(b, "utf-8");
            String name2 = java.net.URLDecoder.decode(name1 ,"utf-8");
            //插入aisc_boarduser
            boardroomSv.boardUserAdd(userCode,name2,orgId);
            json.put("resultCode", "0");
            json.put("resultMessage", "success");
        } catch (Exception e) {
            e.printStackTrace();
            json.put("resultCode", "-1");
            json.put("resultMessage", e.getMessage());
        }
        return json;
    }
	/**
	 * 新增会议室后同步用户数据
	 * @param roomId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sysBoartUser")
	@ResponseBody
    public Map sysBoartUser(@RequestParam String roomId) throws Exception {
		boardroomSv.sysBoartUser(roomId);
		Map<String,String> map = new HashMap();
		return map;
    }
	
	/**
	 * 删除会议室信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleBoardroom.ajax")
	@ResponseBody
    public Map deleBoardroom(@RequestParam long id) throws Exception {
		Map map = new HashMap();
		String flag = boardroomSv.deleBoardroom(id);
        map.put("ERRCODE",flag);
        return map;
    }
	/**
	 * 会议室详情
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boardroomDetail.html")
    public String viewDetail(@RequestParam long id,@RequestParam String type,Model model) throws Exception {
		AiscBoardroomBean boardroomBean = null;
		if(id!=0){
			boardroomBean = boardroomSv.getBoardroom(id);
		}
		model.addAttribute("boardroomBean",boardroomBean);
        model.addAttribute("dataType",type);
        return "basecode/boardroom/boardroomDetail";
    }
	
	/**
	 * 用户会议授权
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boardroomDouser.html")
    public String boardroomDouserDetail(Model model) throws Exception {
		
		
        return "basecode/boardroom/boardroomDouser";
    }
	
	@RequestMapping("/getBoardroomList")
	@ResponseBody
    public Map selectDataset() throws Exception {
		Map map = new HashMap();
		//会议室列表查询
        List<Map> roomlist = boardroomSv.getBoardroomList();
        map.put("roomlist", JsonUtil.toJson(roomlist));
        return map;
    }
	
	/**
	 * 根据会议室查询已授权人员列表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getInBoardroom.ajax")
	@ResponseBody
    public Map getLocList(@RequestParam long roomId) throws Exception {
		Map map = new HashMap();
		List userlist = null;
		List notuserlist = null;
		if(roomId!=-1){
			//已授权人员列表
	        userlist = boardroomSv.getInBoardroom(roomId);
	        //未授权人员列表
	        notuserlist= boardroomSv.getNotBoardroom(roomId);
		}
        map.put("notuserlist", JsonUtil.toJson(notuserlist));
        map.put("userlist", JsonUtil.toJson(userlist));
        return map;
    }
	/**
	 * 人员授权
	 * @param roomId
	 * @param selectOption
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveDouser")
	@ResponseBody
    public Map saveDouser(@RequestParam String roomId,@RequestParam String selectOption) throws Exception {
		Map<String,String> map = new HashMap();
		try{
			//重新授权
			String user[] = selectOption.split(",");
			//查询已授权列表集合
			User users[] = boardroomSv.getAuthUserList(Long.parseLong(roomId));
			//user为空时，直接删除操作
			if(user[0]==""){
				for (int i = 0; i < users.length; i++) {
					boardroomSv.cancelDouser(users[i].getUserName(), roomId);
				}
			}else {
				String array1[] = new String[user.length];
				for (int i = 0; i < user.length; i++) {
					String userCode = user[i].split("\\|\\|")[1];
					array1[i] = userCode;
				}
				if (users != null && users.length > 0) {
					String array2[] = new String[users.length];

					for (int a = 0; a < users.length; a++) {
						array2[a] = users[a].getUserName();
					}
					//差集
					String[] chaji = substract(array1, array2);
					String[] chaji1 = substract(array2, array1);
					if (chaji1.length > 0) {
						//取消授权
						for (int d = 0; d < chaji1.length; d++) {
							boardroomSv.cancelDouser(chaji1[d], roomId);
						}
					}
					if (chaji.length > 0) {
						//新增授权
						for (int c = 0; c < chaji.length; c++) {
							boardroomSv.saveDouser(chaji[c], roomId);
						}
					}
				} else {
					//新增授权
					for (int e = 0; e < user.length; e++) {
						boardroomSv.saveDouser(user[e].split("\\|\\|")[1], roomId);
					}
				}
			}
			map.put("ERRCODE", "0");
			map.put("ERRMSG", "操作成功!");
		}catch(Exception e){
			e.printStackTrace();
			map.put("ERRCODE", "-1");
			map.put("ERRMSG", e.getMessage());
		}

        return map;
    }
	
	//求两个数组的差集   
    public static String[] substract(String[] arr1, String[] arr2) {   
        LinkedList<String> list = new LinkedList<String>();   
        for (String str : arr1) {   
            if(!list.contains(str)) {   
                list.add(str);   
            }   
        }   
        for (String str : arr2) {   
            if (list.contains(str)) {   
                list.remove(str);   
            } 
        }   
        String[] result = {};   
        return list.toArray(result);   
    }
	/**
	 * 获取用户有权限的会议室列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/boardroomList.html")
    public String boardroomList(@RequestParam String userCode,Model model) throws Exception {
		Room[] rooms = boardroomSv.boardroomList(userCode);
		model.addAttribute("rooms",rooms);
		model.addAttribute("userCode",userCode);
//		QryOperatorAscriptionBean operator = boardroomSv.getOperator(userCode);
		//model.addAttribute("userPwd","xypacs1234");
		model.addAttribute("userPwd","xypacs1234");
		DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
		model.addAttribute("Url",item.getItemNo());
        return "basecode/boardroom/boardroomList";
    }
}
