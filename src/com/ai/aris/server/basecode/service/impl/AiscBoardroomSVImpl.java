package com.ai.aris.server.basecode.service.impl;

import static org.apache.commons.lang.StringUtils.isNotBlank;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.ai.appframe2.bo.DataContainerFactory;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscBoardUserBean;
import com.ai.aris.server.basecode.bean.AiscBoardUserEngine;
import com.ai.aris.server.basecode.bean.AiscBoardroomBean;
import com.ai.aris.server.basecode.bean.AiscBoardroomEngine;
import com.ai.aris.server.basecode.model.AiscBoardroomModel;
import com.ai.aris.server.basecode.service.interfaces.IAiscBoardroomSV;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.bean.QryOperatorAscriptionBean;
import com.ai.aris.server.common.bean.QryOperatorAscriptionEngine;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Room;
import com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.Response;
import com.ai.aris.server.webservice.model.hst.getUserinfoListByRoomId.User;
import com.ai.common.domain.DictTranslator;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.BeanUtils;
import com.ai.common.util.Constants;
import com.ai.common.util.ServiceUtil;
import com.ai.common.util.XmlTransUtil;
import com.ai.common.util.XmlUtil;

public class AiscBoardroomSVImpl implements IAiscBoardroomSV {
	ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);
	IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    @Override
    public ResultDTO queryBoardroomList(AiscBoardroomModel model,ResultDTO resultDTO) throws Exception {
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(model.getBoardroomName())) {
            condition.append(" and boardroom_name like '%"+model.getBoardroomName()+"%' ");
        }
        if(!"-1".equals(model.getVerifymode())) {
            condition.append(" and verifyMode = '"+model.getVerifymode()+"' ");
        }
        int total = AiscBoardroomEngine.getBeansCount(condition.toString(), null);
        AiscBoardroomBean[] beans = null;

        if (total > 0) {
            beans = AiscBoardroomEngine.getBeans(null, condition.toString(), null, resultDTO.getStart(), resultDTO.getEnd(), false);
        }
        Map<String, DictTranslator> dicts = new HashMap<String, DictTranslator>();
        resultDTO.setRows(BeanUtils.beanToList(beans, AiscBoardroomModel.class, dicts));
        resultDTO.setRecords(total);
        return resultDTO;
    }
    @Override
    public AiscBoardroomBean getBoardroom(long id) throws Exception{
       return AiscBoardroomEngine.getBean(id);
    }
    
    @Override
	public List<Map> getBoardroomList() throws Exception {
		Map map = new HashMap();
		AiscBoardroomBean[] beans = AiscBoardroomEngine.getBeans(null, map);
        List<Map> list = new ArrayList<Map>();
        for(AiscBoardroomBean boardBean:beans){
            list.add(ServiceUtil.transerBeanToMap(boardBean));
        }
        return list;
	}
    
    public Room[] boardroomList(String userCode)throws Exception {
    	List list = new ArrayList();
    	Room[] room;
    	//好视通接口调用--获取用户有权限的会议室列表
        String responseXml = getRoominfoByUserName(userCode,Constants.KEY_CODE);
        com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response re = XmlTransUtil.getBeanFromXml(responseXml,com.ai.aris.server.webservice.model.hst.getRoominfoByUserName.Response.class);
        if("0000".equals(re.getCode())){
        	room = re.getData().getRoom();
        	if(room.length>0){
        		return room;
        	}
        }
        return null;
    }
    
    public List getInBoardroom(long roomId) throws Exception{
    	
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(roomId!=0&&!"".equals(String.valueOf(roomId))){
        	condition.append(" and ROOM_ID= "+roomId);
        }
        condition.append(" and AUTH_LEVEL in ('1','2','3') ");
    	List userlist = new ArrayList();
    	AiscBoardUserBean[] beans = AiscBoardUserEngine.getBeans(condition.toString(), null);
    	if(beans!=null&&beans.length>0){
    		for(AiscBoardUserBean bean:beans){
    			userlist.add(ServiceUtil.transerBeanToMap(bean));
    		}
    	}
        return userlist;
    }
    
    public User[] getAuthUserList(long roomId) throws Exception{
    	//好视通接口调用--获取会议室授权的用户列表
      String responseXml = getUserinfoListByRoomId(Integer.parseInt(String.valueOf(roomId)),0,0,Constants.KEY_CODE);
      Response re = XmlTransUtil.getBeanFromXml(responseXml,Response.class);
      if(re.getCode().equals("0000")){
      	User[] users = re.getData().getUser();
      	if(users!=null&&users.length>0){
      		return users;
      	}
      }
      return null;
    }
    
    public List getNotBoardroom(long roomId) throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(roomId!=0&&!"".equals(String.valueOf(roomId))){
        	condition.append(" and ROOM_ID= "+roomId);
        }
        condition.append(" and (AUTH_LEVEL is null or AUTH_LEVEL = '0') ");
    	List notuserlist = new ArrayList();
    	AiscBoardUserBean[] beans = AiscBoardUserEngine.getBeans(condition.toString(), null);
    	if(beans!=null&&beans.length>0){
    		for(AiscBoardUserBean bean:beans){
    			notuserlist.add(ServiceUtil.transerBeanToMap(bean));
    		}
    	}
        return notuserlist;
    }
    
    public void sysBoartUser(String roomId) throws Exception{
    	Map map = new HashMap();
    	QryOperatorAscriptionBean[] beans = QryOperatorAscriptionEngine.getBeans(null, map);
    	if(beans!=null&&beans.length>0){
    		for(QryOperatorAscriptionBean bean:beans){
    			AiscBoardUserBean userBean = new AiscBoardUserBean();
            	long id = ServiceUtil.getSequence("SEQBOARDUSERID");
            	userBean.setUserId(id);
            	userBean.setUserCode(bean.getOperatorCode());
            	userBean.setUserName(bean.getOperatorName());
            	userBean.setRoomId(Long.parseLong(roomId));
            	userBean.setStatus("0");
            	userBean.setOrgId(bean.getOrgId());
            	userBean.setCreateTime(new Timestamp(new Date().getTime()));
            	AiscBoardUserEngine.save(userBean);
    		}
    	}
        return ;
    }
    
    public QryOperatorAscriptionBean getOperator(String userCode)throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if (isNotBlank(userCode)) {
            condition.append(" and OPERATOR_CODE = '" +userCode.toUpperCase()+ "'");
        } 
    	QryOperatorAscriptionBean[] beans = QryOperatorAscriptionEngine.getBeans(condition.toString(),null);
    	if(beans!=null&&beans.length>0){
    		return beans[0];
    	}
    	return null;
    }
    
   /* public void saveDouser(String userCode,String roomId)throws Exception{
    	String userPwd = "xypacs1234";
    	userCode = "CWZD01";
        roomId  =  "10034";
    	AiscBoardUserBean userBean = getAiscBoardUser(userCode,roomId);
    	//好视通接口调用--新增用户基本信息
    	if(userBean.getAuthLevel()==null||"".equals(userBean.getAuthLevel())){
    		boolean flag = getIsBoardUser(userCode);
    		if(flag){
    		    //添加用户
	    		String responseXml = addUserinfo(userCode,userPwd,userBean.getUserName(),0,"","","","","",Constants.KEY_CODE,0);
	            com.ai.aris.server.webservice.model.hst.addUserinfo.Response re = XmlTransUtil.getBeanFromXml(responseXml,com.ai.aris.server.webservice.model.hst.addUserinfo.Response.class);
	            if("0000".equals(re.getCode())){
	            	//用户授权
	            	String resxml = doRoomRightByUserName(userCode,userBean.getRoomId()+",2",Constants.KEY_CODE);
	            	com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response response = XmlTransUtil.getBeanFromXml(resxml,com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response.class);
	            	if("0000".equals(response.getCode())){
	            		//更新会议用户表状态
	            		AiscBoardUserBean oldBean = AiscBoardUserEngine.getBean(userBean.getUserId());
	            		oldBean.setStsToOld();
	            		oldBean.setAuthLevel("2");
	            		oldBean.setAuthTimme(new Timestamp(new Date().getTime()));
	            		AiscBoardUserEngine.save(oldBean);
	            	}else{
                        throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(re.getCode()));
	            	}
	            }else{
	            	throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(re.getCode()));
	            }
    		}else{
    			//授权
            	String resxml = doRoomRightByUserName(userCode,userBean.getRoomId()+",2",Constants.KEY_CODE);
            	com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response response = XmlTransUtil.getBeanFromXml(resxml,com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response.class);
            	if("0000".equals(response.getCode())){
            		//更新会议用户表状态
            		AiscBoardUserBean oldBean = AiscBoardUserEngine.getBean(userBean.getUserId());
            		oldBean.setStsToOld();
            		oldBean.setAuthLevel("2");
            		oldBean.setAuthTimme(new Timestamp(new Date().getTime()));
            		AiscBoardUserEngine.save(oldBean);
            	}else{
                    throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(response.getCode()));
            	}
    		}    
    	}else{
    		//授权
        	String resxml = doRoomRightByUserName(userCode,userBean.getRoomId()+",2",Constants.KEY_CODE);
        	com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response response = XmlTransUtil.getBeanFromXml(resxml,com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response.class);
        	if("0000".equals(response.getCode())){
        		//更新会议用户表状态
        		AiscBoardUserBean oldBean = AiscBoardUserEngine.getBean(userBean.getUserId());
        		oldBean.setStsToOld();
        		oldBean.setAuthLevel("2");
        		oldBean.setAuthTimme(new Timestamp(new Date().getTime()));
        		AiscBoardUserEngine.save(oldBean);
        	}else{
                throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(response.getCode()));
        	}
    	}
        
    }*/


    public void saveDouser(String userCode,String roomId)throws Exception{
        String userPwd = "xypacs1234";
        //userCode = "CWZD01";
        //roomId  =  "10039";
        AiscBoardUserBean userBean = getAiscBoardUser(userCode,roomId);
        //好视通接口调用--新增用户基本信息
        if(userBean.getAuthLevel()==null||"".equals(userBean.getAuthLevel())){
            boolean flag = getIsBoardUser(userCode);
            if(flag){
                //添加用户
                String responseXml = addUserinfo(userCode,userPwd,userBean.getUserName(),0,"","","","","",Constants.KEY_CODE,0);
                com.ai.aris.server.webservice.model.hst.addUserinfo.Response re = XmlTransUtil.getBeanFromXml(responseXml,com.ai.aris.server.webservice.model.hst.addUserinfo.Response.class);
                if("0000".equals(re.getCode())){
                    roomAuth(userCode,userBean.getRoomId(),userBean.getUserId());
                }else if("1001".equals(re.getCode())){
                    //用户已在好视通存在时，先删除再添加
                    //删除用户
                    String delXml = deleteHstUser(userBean.getUserCode(),Constants.KEY_CODE);
                    //添加用户
                    String addXml = addUserinfo(userCode,userPwd,userBean.getUserName(),0,"","","","","",Constants.KEY_CODE,0);
                    //授权用户
                    roomAuth(userCode,userBean.getRoomId(),userBean.getUserId());
                }
                else{
                    throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(re.getCode()));
                }
            }else{
                roomAuth(userCode,userBean.getRoomId(),userBean.getUserId());
            }
        }else{
            roomAuth(userCode,10039,userBean.getUserId());
        }
    }

    //会议室授权
    public void roomAuth(String userCode,long roomId,long userId) throws Exception{
            String resxml = doRoomRightByUserName(userCode,roomId+",2",Constants.KEY_CODE);
            com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response response = XmlTransUtil.getBeanFromXml(resxml,com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response.class);
            if("0000".equals(response.getCode())){
                //更新会议用户表状态
                AiscBoardUserBean oldBean = AiscBoardUserEngine.getBean(userId);
                oldBean.setStsToOld();
                oldBean.setAuthLevel("2");
                oldBean.setAuthTimme(new Timestamp(new Date().getTime()));
                AiscBoardUserEngine.save(oldBean);
            }else{
                throw new Exception("用户:"+userCode+"操作失败,失败原因："+getHstStatusMsg(response.getCode()));
            }
    }
    
    public void cancelDouser(String userCode,String roomId)throws Exception{
    	AiscBoardUserBean userBean = getAiscBoardUser(userCode,roomId);
    	//取消授权
    	String resxml = doRoomRightByUserName(userCode,userBean.getRoomId()+",0",Constants.KEY_CODE);
    	com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response response = XmlTransUtil.getBeanFromXml(resxml,com.ai.aris.server.webservice.model.hst.doRoomRightByUserName.Response.class);
    	if("0000".equals(response.getCode())){
    		//更新会议用户表状态
    		AiscBoardUserBean oldBean = AiscBoardUserEngine.getBean(userBean.getUserId());
    		oldBean.setStsToOld();
    		oldBean.setAuthLevel("0");
    		oldBean.setAuthTimme(new Timestamp(new Date().getTime()));
    		AiscBoardUserEngine.save(oldBean);
    	}else{
    		throw new Exception(userCode+"取消授权用户---取消授权失败");
    	}
    }
    
    public boolean getIsBoardUser(String userCode)throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(userCode)){
        	condition.append(" and USER_CODE= '"+userCode+"'");
        }
        condition.append(" and AUTH_LEVEL in ('1','2','3','0') ");
    	AiscBoardUserBean[] beans = AiscBoardUserEngine.getBeans(condition.toString(), null);
    	if(beans!=null&&beans.length>0){
    		return false;
    	}
        return true;
    }
    
    public AiscBoardUserBean getAiscBoardUser(String userCode,String roomId)throws Exception{
    	StringBuffer condition = new StringBuffer();
        condition.append(" 1=1");
        if(isNotBlank(userCode)){
        	condition.append(" and USER_CODE= '"+userCode+"'");
        }
        if(isNotBlank(roomId)){
        	condition.append(" and ROOM_ID= "+roomId);
        }
    	AiscBoardUserBean[] beans = AiscBoardUserEngine.getBeans(condition.toString(), null);
    	if(beans!=null&&beans.length>0){
    		return beans[0];
    	}
        return null;
    }
    
    public String saveBoardroom(AiscBoardroomBean bean) throws Exception{
    	bean.setKeycode(Constants.KEY_CODE);
    	if (bean.getBoardroomId() == 0) {
    		long id = -1;
            //好视通接口调用
            String responseXml = addRoominfo(bean.getBoardroomName(),bean.getVerifymode(),bean.getPassword(),Integer.parseInt(String.valueOf(bean.getMaxusercount())),bean.getEnablechairpwd(),bean.getChairpassword(),bean.getKeycode());
            Map map =XmlUtil.xml2Map(responseXml);  
            if(map.get("code").equals("0000")){
               id = Long.parseLong(String.valueOf(map.get("roomId")));	
               bean.setBoardroomId(id);
               AiscBoardroomEngine.save(bean);
               return String.valueOf(id)+"||0";
            }else{
            	return String.valueOf(id)+"||0";
            }
        } else {
        	AiscBoardroomBean oldBean = AiscBoardroomEngine.getBean(bean.getBoardroomId());
            DataContainerFactory.copyNoClearData(bean, oldBean);
            AiscBoardroomEngine.save(oldBean);
            //好视通接口调用--修改会议室基本信息
            String responseXml = editRoominfo(Integer.parseInt(String.valueOf(bean.getBoardroomId())),oldBean.getBoardroomName(),oldBean.getVerifymode(),oldBean.getPassword(),Integer.parseInt(String.valueOf(oldBean.getMaxusercount())),oldBean.getEnablechairpwd(),oldBean.getChairpassword(),oldBean.getKeycode());
            Map map =XmlUtil.xml2Map(responseXml);  
            if(map.get("code").equals("0000")){
               return String.valueOf(bean.getBoardroomId())+"||1";
            }else{
            	return "-1"+"||1";
            }
        }
    }
    
    public void boardUserAdd(String userCode,String userName,String orgId) throws Exception{
    	StringBuffer condition = new StringBuffer();
    	AiscBoardroomBean[] roomBeans = AiscBoardroomEngine.getBeans(condition.toString(), null);
    	if(roomBeans!=null&&roomBeans.length>0){
    		for(AiscBoardroomBean bean:roomBeans){
    			if(getIsexist(userCode,userName,orgId,bean.getBoardroomId())){
	    			AiscBoardUserBean boarduser = new AiscBoardUserBean();
	    	    	boarduser.setUserId(ServiceUtil.getSequence("SEQBOARDUSERID"));
	    	    	boarduser.setUserCode(userCode);
	    	    	boarduser.setUserName(userName);
	    	    	boarduser.setStatus("0");
	    	    	boarduser.setRoomId(bean.getBoardroomId());
	    	    	boarduser.setOrgId(orgId);
	    	    	boarduser.setCreateTime(new Timestamp(new Date().getTime()));
	    	    	AiscBoardUserEngine.save(boarduser);
	    	    }
    		}
    	}
    }
    
    private boolean getIsexist(String userCode,String userName,String orgId,long roomId)throws Exception{
    	StringBuffer condition = new StringBuffer();
    	condition.append(" 1=1");
    	condition.append(" and USER_CODE= '"+userCode+"'");
    	condition.append(" and USER_NAME= '"+userName+"'");
    	condition.append(" and ORG_ID= '"+orgId+"'");
    	condition.append(" and ROOM_ID= "+roomId);
    	AiscBoardUserBean [] userbean = AiscBoardUserEngine.getBeans(condition.toString(),null);
    	if(userbean!=null&&userbean.length>0){
    		return false;
    	}
    	return true;
    }
    
    public String deleBoardroom(long id) throws Exception{
	    //好视通接口调用--修改会议室基本信息
	    String responseXml = delRoominfo(Integer.parseInt(String.valueOf(id)),Constants.KEY_CODE);
	    Map map =XmlUtil.xml2Map(responseXml);  
	    if(map.get("code").equals("0000")){
	    	AiscBoardroomBean bean = AiscBoardroomEngine.getBean(id);
	        bean.setStsToOld();
	        bean.delete();
	    	AiscBoardroomEngine.save(bean);
	    	//删除该会议室下的所有用户
	    	String delSql = "delete from aisc_boarduser where room_id="+id;
			commonSV.execute(delSql,null);
	        return "0";
	    }else{
	    	return "-1";
	    }
        
    }
    
    /**
     * 添加会议室，好视通接口
     * @param roomName
     * @param verifyMode
     * @param password
     * @param maxUserCount
     * @param enableChairPwd
     * @param chairPassword
     * @param keyCode
     * @return
     * @throws Exception
     */
    public String addRoominfo(String roomName, String verifyMode, String password, Integer maxUserCount, 
    	       String enableChairPwd, String chairPassword, String keyCode) throws Exception {
       //调用时改为自己服务器地址端口 
       DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
       String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
       String method = "addRoominfo"; 
       JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
       Client client =  dcf.createClient(new URL(wsUrl));  
       Object[] object ={roomName,verifyMode,password,maxUserCount,enableChairPwd,chairPassword,keyCode}; 
       Object[] res = client.invoke(method, object); 
       return res[0].toString() + ""; 
   }
    
    /**
     * 修改会议室，好视通接口
     * @param roomName
     * @param verifyMode
     * @param password
     * @param maxUserCount
     * @param enableChairPwd
     * @param chairPassword
     * @param keyCode
     * @return
     * @throws Exception
     */
    public String editRoominfo(Integer roomId, String roomName, String verifyMode, String password,Integer maxUserCount, String enableChairPwd, String chairPassword, String keyCode) throws Exception {
       //调用时改为自己服务器地址端口 
       DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
       String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
       String method = "editRoominfo"; 
       JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
       Client client =  dcf.createClient(new URL(wsUrl));  
       Object[] object ={roomId,roomName,verifyMode,password,maxUserCount,enableChairPwd,chairPassword,keyCode}; 
       Object[] res = client.invoke(method, object); 
       return res[0].toString() + ""; 
   } 
    
    /**
     * 删除会议室，好视通接口
     * @param roomId
     * @param keyCode
     * @return
     * @throws Exception
     */
    public String delRoominfo(Integer roomId, String keyCode) throws Exception {
       //调用时改为自己服务器地址端口 
       DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];	
       String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
       String method = "delRoominfo"; 
       JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
       Client client =  dcf.createClient(new URL(wsUrl));  
       Object[] object ={roomId,keyCode}; 
       Object[] res = client.invoke(method, object); 
       return res[0].toString() + ""; 
   } 
    
    /**
     * 获取会议室授权的用户列表，好视通接口
     * @param roomid
     * @param currPage
     * @param pageSize
     * @param keyCode
     * @return
     * @throws Exception
     */
    public String getUserinfoListByRoomId(Integer roomid,Integer currPage, Integer pageSize, String keyCode) throws Exception {
       //调用时改为自己服务器地址端口 
       DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];	
       String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
       String method = "getUserinfoListByRoomId"; 
       JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
       Client client =  dcf.createClient(new URL(wsUrl));  
       Object[] object ={roomid,currPage,pageSize,keyCode}; 
       Object[] res = client.invoke(method, object); 
       return res[0].toString() + ""; 
   } 
    
    /**
     * 注册用户，好视通接口
     * @param userName
     * @param passpwd
     * @param nickName
     * @param departID
     * @param adminRole
     * @param sex
     * @param mobile
     * @param telePhone
     * @param email
     * @param keyCode
     * @param password_type
     * @return
     * @throws Exception
     */
    public String addUserinfo(String userName, String passpwd, String nickName, Integer departID, String adminRole, 
    		String sex, String mobile, String telePhone, String email, String keyCode, Integer password_type ) throws Exception {
       //调用时改为自己服务器地址端口 
       DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
       String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
       String method = "addUserinfo"; 
       JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
       Client client =  dcf.createClient(new URL(wsUrl));  
       Object[] object ={userName,passpwd, nickName,departID, adminRole, sex, mobile,telePhone,email,keyCode,password_type}; 
       Object[] res = client.invoke(method, object); 
       return res[0].toString() + ""; 
   }

    //删除好视通用户
    public String deleteHstUser(String userName,String keyCode) throws Exception {
        //调用时改为自己服务器地址端口
    	DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
        String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl";
        String method = "delUser";
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client =  dcf.createClient(new URL(wsUrl));
        Object[] object ={userName,keyCode};
        Object[] res = client.invoke(method, object);
        return res[0].toString() + "";
    }
    /**
     * 授权用户
     * @param userName
     * @param roomuserStr
     * @param keyCode
     * @return
     * @throws Exception
     */
    public String doRoomRightByUserName(String userName, String roomuserStr, String keyCode) throws Exception{
    	//调用时改为自己服务器地址端口
    	DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
        String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
        String method = "doRoomRightByUserName"; 
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
        Client client =  dcf.createClient(new URL(wsUrl));  
        Object[] object ={userName,roomuserStr,keyCode}; 
        Object[] res = client.invoke(method, object); 
        return res[0].toString() + ""; 
    }
    
    public String getRoominfoByUserName(String userName,String keyCode) throws Exception{
    	//调用时改为自己服务器地址端口
    	DictItemBean item = dictSV.queryDictItem("HST_URLPORT")[0];
        String wsUrl = "http://"+item.getItemNo()+"/fmapi/webservice/jaxws?wsdl"; 
        String method = "getRoominfoByUserName"; 
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();     
        Client client =  dcf.createClient(new URL(wsUrl));  
        Object[] object ={userName,keyCode}; 
        Object[] res = client.invoke(method, object); 
        return res[0].toString() + ""; 
    }

    //好视通状态码
    public String getHstStatusMsg(String returnCode){
        String resultMsg = "处理失败";
        switch (returnCode) {
            case "0000"  :resultMsg="操作成功";break;
            case "9999"  :resultMsg="系统错误";break;
            case "0001"  :resultMsg="ID和名称不能同时为空";break;
            case "0002"  :resultMsg="不存在该数据信息或用户操作失误";break;
            case "0003"  :resultMsg="密钥错误";break;
            case "0004"  :resultMsg="密码不是MD5密码字符串";break;
            case "1001"  :resultMsg="用户名已存在或者用户参数不全";break;
            case "1002"  :resultMsg="用户名为空或格式不正确，包括长度";break;
            case "1003"  :resultMsg="密码为空或格式不正确，包括长度";break;
            case "1004"  :resultMsg="昵称不为空时格式不正确，包括长度，支持空格等";break;
            case "1005"  :resultMsg="组织不为空时格式不正确，包括长度，整数";break;
            case "1006"  :resultMsg="角色不为空时格式不正确，包括长度，整数";break;
            case "1007"  :resultMsg="性别不为空时格式不正确，包括长度，整数";break;
            case "1008"  :resultMsg="电话号码格式不对";break;
            case "1009"  :resultMsg="手机号码格式不对";break;
            case "1010"  :resultMsg="邮箱格式不对";break;
            case "1011"  :resultMsg="用户数量错误";break;
            case "1012"  :resultMsg="此用户名的用户不存在";break;
            case "1013"  :resultMsg="组织不存在";break;
            case "1014"  :resultMsg="角色不存在";break;
            case "1015"  :resultMsg="用户删除错误";break;
            case "1016"  :resultMsg="查询类型为空或者错误";break;
            case "1017"  :resultMsg="用户查询分页超过最大条数";break;
            case "1018"  :resultMsg="用户查询传入房间ID为空";break;
            case "1019"  :resultMsg="授权会议室名单字符串为空";break;
            case "1020"  :resultMsg="授权会议室名单字符串为空或格式不对";break;
            case "1021"  :resultMsg="会议室不存在";break;
            case "1022"  :resultMsg="会议室删除错误";break;
            case "1023"  :resultMsg="传入会议室密码类型错误";break;
            case "1024"  :resultMsg="用户保存错误";break;
            case "1025"  :resultMsg="用户属性类型只能是1或者2";break;
            case "1026"  :resultMsg="不能对系统用户admin、recordclient进行操作";break;
            case "2000"  :resultMsg="会议ID为空或格式不正确，包括长度应大于3并小于11位";break;
            case "2001"  :resultMsg="会议室名为空或长度错误";break;
            case "2002"  :resultMsg="传入会议室登录校验模型类型不存在";break;
            case "2003"  :resultMsg="会议室名最大数量为空或错误";break;
            case "2004"  :resultMsg="是否启用主席密码类型值错误";break;
            case "2005"  :resultMsg="主席密码为空或格式不正确";break;
            case "2006"  :resultMsg="房间信息保存错误";break;
            case "2007"  :resultMsg="房间类型修改错误";break;
            case "2008"  :resultMsg="日期格式错误错误";break;
            case "2009"  :resultMsg="日期开始时间与结束时间不符合常理错误(开始时间晚于结束时间,开始时间早于当前时间)";break;
            case "2010"  :resultMsg="时间格式错误错误";break;
            case "2011"  :resultMsg="开始时间与结束时间不符合常理错误(开始时间晚于结束时间)";break;
            case "2012"  :resultMsg="周例会议循环模式类型值错误";break;
            case "2013"  :resultMsg="周例会为每周时，传入的周字符串错误或为空";break;
            case "2014"  :resultMsg="周例会为每月时，传入的每天字符串错误或为空";break;
            case "2015"  :resultMsg="传入的是否启用默认配置值错误或为空";break;
            case "2016"  :resultMsg="传入的视频编码器值错误或为空";break;
            case "2017"  :resultMsg="传入的视频码流数值错误或为空";break;
            case "2018"  :resultMsg="传入的默认视频分辩率值错误或为空或无权限";break;
            case "2019"  :resultMsg="授权者用户名单为空或格式不正确，包括长度";break;
            case "2020"  :resultMsg="会议室状态为空或者不在有效值(0、1)";break;
            case "3001"  :resultMsg="组织架构名称为空或格式不正确，包括长度";break;
            case "3002"  :resultMsg="顶级组织只能存在一个";break;
            case "3003"  :resultMsg="上级组织架构ID不能为当前组织的子级";break;
            case "3004"  :resultMsg="上级组织架构ID不存在";break;
            case "3005"  :resultMsg="上级组织架构ID不为空时格式不正确，包括长度，整数";break;
            case "3006"  :resultMsg="组织架构ID不存在";break;
            case "4001"  :resultMsg="应用返回提示不能为空或长度不能超过64";break;
            case "4002"  :resultMsg="消息格式错误";break;
            case "4003"  :resultMsg="服务器消息处理超时";break;
            case "4004"  :resultMsg="请求消息未找到";break;
            case "4005"  :resultMsg="服务器消息处理异常";break;
            case "4006"  :resultMsg="会议室已过期";break;
            case "4007"  :resultMsg="会议室已关闭";break;
            case "4008"  :resultMsg="会议室所在节点服务器离线";break;
            case "4009"  :resultMsg="用户属性值长度不能超过64*1000";break;
            case "4010"  :resultMsg="用户属性值不能为空";break;
            case "4011"  :resultMsg="无法连接服务器";break;
            case "4012"  :resultMsg="操作者长度不能超过64";break;
        }
        return resultMsg;
    }
}
   

