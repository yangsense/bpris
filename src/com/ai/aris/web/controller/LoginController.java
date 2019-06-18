package com.ai.aris.web.controller;

import com.ai.appframe2.common.AIConfigManager;
import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.basecode.bean.AiscUser2CareProvBean;
import com.ai.aris.server.common.bean.AisRightControlBean;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.aris.server.sysmanage.bean.ISysOrgValue;
import com.ai.aris.server.sysmanage.bean.SysMenuBean;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysOperatorBean;
import com.ai.aris.server.sysmanage.service.interfaces.ISysManageSV;
import com.ai.aris.server.webservice.bean.OrgsResponse;
import com.ai.aris.server.webservice.bean.SysOrg;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.service.RestClient;
import com.ai.aris.server.webservice.service.interfaces.IAuthorOrgDataSV;
import com.ai.aris.server.webservice.service.interfaces.IAuthorSV;
import com.ai.aris.server.webservice.service.interfaces.IOperatorSV;
import com.ai.aris.server.workstation.service.interfaces.ICommonDataSV;
import com.ai.aris.server.workstation.service.interfaces.IRisOrgSV;
import com.ai.aris.server.workstation.service.interfaces.RightControlConstant;
import com.ai.aris.util.UserUtil;
import com.ai.common.util.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-13
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/bpris/")
public class LoginController {
    @Autowired
	private IAuthorSV authorSV;
	@Autowired
	private IAuthorOrgDataSV authorOrgDataSV;
   
    private IRisOrgSV orgSV = (IRisOrgSV)ServiceFactory.getService(IRisOrgSV.class);
    private IDictItemSV dictSV = (IDictItemSV) ServiceFactory.getService(IDictItemSV.class);
    private ICommonDataSV commonDataSv = (ICommonDataSV) ServiceFactory.getService(ICommonDataSV.class);
	private ISysManageSV sysManageSV = (ISysManageSV) ServiceFactory.getService(ISysManageSV.class);
	private ICommonSV commonSV = (ICommonSV) ServiceFactory.getService(ICommonSV.class);

    @Autowired
    private IOperatorSV operatorSV;
    private Log logger = LogFactory.getLog(LoginController.class);

//    private IPublicUserSV publicUserSV = (IPublicUserSV) ServiceFactory.getService(IPublicUserSV.class);
    @Value("${appCode}")
    private String appCode;
    @Value("${aris.domain.name}")
    private String domain;
    @Value("${aris.cookie.domain}")
    private String cookieDomain;  
    @Value("${retriveOrgIdPath}")
    private String retriveOrgIdPath;
    @Value("${org.root.code}")
    private String rootOrgCode;
    @Autowired
    private RestClient restClient;
    @Value("${sysOrgManagePath}")
    private String sysOrgManagePath;
    @Value("${getOperatorPath}")
    private String getOperatorPath;
    
    @RequestMapping("login.html")
    public String login(String update ,String registName,Model model,String errorCode,String errorMessage, HttpServletRequest request) throws Exception{
		//String update = (String)request.getSession().getAttribute("update");
        model.addAttribute("update",update);
        model.addAttribute("registName",registName);
		model.addAttribute("errorCode",errorCode);
        model.addAttribute("errorMessage",errorMessage);
        DictItemBean[] dict= dictSV.queryDictItem("SYS_PREFIX");
		String versionNo= dictSV.queryQueryVersionNo();
		if(dict!=null&&dict.length==1){
        	model.addAttribute("system",dict[0].getItemName());
        }else{
        	model.addAttribute("system","-1");
        }
		model.addAttribute("versionNo",versionNo!=""?versionNo:"暂无");
		return "login";
    }
	@RequestMapping("initUseRight.html")
	public String initUseRight(Model model,String errorCode,String errorMessage) throws Exception{
		model.addAttribute("errorCode",errorCode);
		model.addAttribute("errorMessage",errorMessage);
		return "initUseRight";
	}
	/**
	 *
	 * @param secretKey
	 * @return
	 */
	@RequestMapping("updateExpirationDte.html")
	public String  updateExpirationDte(String secretKey,Model model) throws Exception {
		Map<String, String> map =  new HashMap<>();
		String errorCode="0";
		String errorMsg="秘钥验证成功";
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String decodeStr = DESUtil.decrypt(secretKey, RightControlConstant.Right_control_DesKey);
			String [] splitStr = decodeStr.split("\\|\\|");
			AisRightControlBean aisRightControlBean = new AisRightControlBean();
			aisRightControlBean.setOrgCode(DESUtil.encrypt(splitStr[0], RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setOrgName(DESUtil.encrypt(splitStr[1], RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setInstallTime(DESUtil.encrypt(splitStr[2],RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setCurrentTime(DESUtil.encrypt(splitStr[3],RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setExpirationTime(DESUtil.encrypt(splitStr[4],RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setUpdateTime(DESUtil.encrypt(sdf.format(new Date()),RightControlConstant.Right_control_DesKey));
			aisRightControlBean.setServerMac(DESUtil.encrypt(splitStr[7],RightControlConstant.Right_control_DesKey));
			orgSV.saveAisRightControlBean(aisRightControlBean);
		}catch(Exception e){
			errorCode="1";
			errorMsg="秘钥验证失败";
			logger.info("LoginController->updateExpirationDte.html error!" + e.getMessage());
		}
		model.addAttribute("errorCode",errorCode);
		model.addAttribute("errorMessage",errorMsg);
		return "redirect:/bpris/initUseRight.html";
	}
    /**
     * 用户登录，此方法目前只支持普通登录，不支持手机验证码登录
     * TODO 1、前台需要对密码需要加密处理
     * TODO 2、将用户名及密码写入Cookie
     * @param accountName
     * @param passwd
     * @param loginType
     * @param model
     * @return
     */
    @RequestMapping("index.html")
    public String loginCheck(String update ,String accountName, String passwd, String loginType,
                             Model model,
                             HttpServletResponse response,
                             HttpServletRequest request,HttpSession httpSession){
        String errorCode= "0";
        String errorMessage = ""; 
        
        //操作员默认组织机构获取处理
        //从cookie中
        String c_currentOrgId = "";
        Cookie[] cookies = request.getCookies();
        
        //当cookies为空的时候直接返回到登陆页
        if(cookies==null){
        	return "redirect:/bpris/login.html";
        }
        
        for(Cookie c :cookies ){
            if("c_currentOrgId".equals(c.getName())){
            	c_currentOrgId = c.getValue();
            	break;
            }
        }
        //当前请求中orgId不为空时写入cookie，为空时从cookie中取
        String currentOrgId =  request.getParameter("orgId") ;
        if(!"".equals(currentOrgId) && currentOrgId != null){
        	//放cookie        
            Cookie c2 = new Cookie("c_currentOrgId",currentOrgId);
            response.addCookie(c2);
            model.addAttribute("currentOrgId",currentOrgId);    	
        }else{
        	model.addAttribute("currentOrgId",c_currentOrgId);       
        }
        //将当前组织机构放入session中
        request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID,currentOrgId==null?c_currentOrgId:currentOrgId); 	
         
      try {    	  
    	   		          
	            if(request.getSession().getAttribute(Constants.SESSION_USER_OBJ)!=null&&!(request.getSession().getAttribute(Constants.SESSION_USER_OBJ).equals("null"))){
	              return "index";
	            }
    	  
	            if("".equals(passwd)){
	            	errorCode = "-1"; 
                    model.addAttribute("errorCode",errorCode); 
                    return "redirect:/bpris/login.html"; 
	            }
	            DictItemBean[] dict= dictSV.queryDictItem("SYS_PREFIX");
	            String userPrefix = "";
	            if(dict!=null&&dict.length==1){
	            	if(dict[0].getItemNo().equals("null")){
	            		userPrefix = "";
	            	}else if("ADMIN".equals(accountName.toUpperCase())){
	            		userPrefix = "";
	            	}else{
	            		userPrefix = dict[0].getItemNo();
	            	}
	            }else{
	            	throw new Exception("医院名称及工号前缀配置错误");
	            }	            
	    	    Map map = new HashMap();
	            map.put("operatorCode",userPrefix+accountName);
//	            map.put("operatorCode",accountName);
	            map.put("password",passwd);
	            map.put("appType","airis");
	            map.put("secureDesc","123456");               
                //验证码校验
//                String sessionCheckCode = (String)httpSession.getAttribute("sessionCheckCode_login_mail");
//                logger.info("sessionCheckCode:"+sessionCheckCode);
//                logger.info("checkCode:"+request.getParameter("imageCode"));
//                if(sessionCheckCode == null || !sessionCheckCode.toLowerCase().equals(request.getParameter("imageCode").toLowerCase())){
//                	errorCode = "-2";
//                	model.addAttribute("errorCode",errorCode);
//                	return "redirect:/aris/login.html";
//                }
//                
               /* AuthorDataResponse authorDataResponse;
                try {
                     authorDataResponse = authorSV.retrieveData(map);
                     if("-1".equals(authorDataResponse.getCode())){
                    	 errorCode = "-1"; 
                         model.addAttribute("errorCode",errorCode); 
                         return "redirect:/aris/login.html"; 
                     }                     
                }catch (IOException e){
                    errorCode = "-3";
                    errorMessage = "Webservice Server can't response correctly";
                    model.addAttribute("errorCode",errorCode);
                    model.addAttribute("errorMessage",errorMessage);
                    return "redirect:/aris/login.html";
                }
                errorCode = authorDataResponse.getCode();
                errorMessage = authorDataResponse.getMessage();*/

                //修改 by caorj 用户验证
                //User user = authorDataResponse.getUser();
		        String operatorCode = (userPrefix+accountName).toUpperCase();
		        String ip = WebUtils.getRemoteAddr(request);
		        SysOperatorBean userBean = null;
		        if(StringUtils.isNotBlank(passwd)) {
					userBean = (SysOperatorBean) sysManageSV.login(operatorCode,passwd, ip);
				}else{
					userBean = (SysOperatorBean) sysManageSV.login(operatorCode, ip);
				}
			   if(userBean==null){
				   model.addAttribute("errorCode",-1);
				   model.addAttribute("errorMessage","用户名不存在！");
				   return "redirect:/bpris/login.html";
			   }
			   if(userBean.getOperatorState()!=1){
				   model.addAttribute("errorCode",-6);
				   model.addAttribute("errorMessage","用户失效！");
				   return "redirect:/bpris/login.html";
			   }
		       User user  = new User();
			   user.setOperatorId(String.valueOf(userBean.getOperatorId()));
		       user.setOperatorPsw(userBean.getOperatorPsw());
		  	   user.setOperatorCode(userBean.getOperatorCode());
		       user.setOperatorName(userBean.getOperatorName());

		       // user.setOperatorCode(userPrefix+user.getOperatorCode());

		        //修改 by caorj
		        //List<Menu> menus =authorDataResponse.getMenuBeans();

                SysMenuBean[] menus = sysManageSV.queryMenuList(operatorCode,"airis");
		        //修改 by caorj
                //List<Menu> buttons = authorDataResponse.getButtonBeans();
                SysMenuBean[] buttons = sysManageSV.queryButtonList(operatorCode,"airis");
//                String strButtons = "";
//                for(Menu m : buttons){
//                	strButtons+=strButtons==""?m.getMenuUrl():","+m.getMenuUrl();
//                }
                //根据登录工号取工号对应的医生编码
                AiscUser2CareProvBean user2careProv = commonDataSv.getCareProvByOperatorId(user.getOperatorId());
                if(user2careProv != null){
					user.setCareProvId(String.valueOf(user2careProv.getCareprovId()));
                }else{
                	user.setCareProvId(user.getOperatorId()); 
                }                
                
                //归属组织机构   修改 by caorj
                //SysOrg org = authorDataResponse.getSysOrgBean();
                ISysOrgValue[] orgList =  sysManageSV.getBelongOrgByOprCode(operatorCode);
                com.ai.aris.server.sysmanage.bean.SysOrgBean org = null;
		        if(orgList.length>0){
					org = (com.ai.aris.server.sysmanage.bean.SysOrgBean)orgList[0];
		        }

                //管理组织机构
                //List<SysOrg> orgManageBeans = authorDataResponse.getSysOrgManageBeans();
                //取当前员工归属组织下的员工信息
                //List<SysOperator> SysOperatorBeans = authorDataResponse.getSysOperatorBeans();

		        //修改 by caorj
		        //List<SysOperator2Org> sysOperator2OrgBeans = authorDataResponse.getSysOperator2OrgBeans();
                SysOperator2OrgBean[] sysOperator2OrgBeans = sysManageSV.queryOperator2OrgBeans(operatorCode);

		        /*for(SysOperator2Org sysOperator2Org:sysOperator2OrgBeans) {
					if (sysOperator2Org.getStationCode().startsWith(Constants.STATION_CODE_TJ)) {
						request.getSession().setAttribute(Constants.SESSION_OPERATOR_STATION,sysOperator2Org.getStationCode());
						Map map2 = new HashMap();
						map2.put("orgId",sysOperator2Org.getOrgId());
						AuthorOrgDataResponse authorOrgDataResponse = authorOrgDataSV.retrieveOrgData(map2);
						SysOrgBean sysOrgBean = authorOrgDataResponse.getSysOrgBean();
						request.getSession().setAttribute(Constants.SESSION_SYS_ORG,JsonUtil.toJson(sysOrgBean));
						request.getSession().setAttribute(Constants.SESSION_SYS_ORG_OBJ,sysOrgBean);
						break;
					}
				}*/

				  for(SysOperator2OrgBean sysOperator2Org:sysOperator2OrgBeans) {
					  if (sysOperator2Org.getStationCode().startsWith(Constants.STATION_CODE_TJ)) {
						  request.getSession().setAttribute(Constants.SESSION_OPERATOR_STATION,sysOperator2Org.getStationCode());
						  com.ai.aris.server.sysmanage.bean.SysOrgBean sysOrgBean = sysManageSV.getOrgBeanByOrgId(sysOperator2Org.getOrgId());
						  request.getSession().setAttribute(Constants.SESSION_SYS_ORG,AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(sysOrgBean == null ? new com.ai.aris.server.sysmanage.bean.SysOrgBean() : sysOrgBean));
						  request.getSession().setAttribute(Constants.SESSION_SYS_ORG_OBJ,sysOrgBean);
						  break;
					  }
				  }

				//系统使用到期时间验证
			  	String isWarn = "";
			  	long residueTime=0L;
			  	String loginSecretKeyVerify = AIConfigManager.getConfigItem("LoginSecretKeyVerify");
		        if(loginSecretKeyVerify.equals("DoLoginSecretKeyVerify")){
						String localMac = getLocalMac();

						Boolean checkPass = false;
						Long sysTime = System.currentTimeMillis();
						//Date currentDate = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String time = sdf.format(sysTime);
						AisRightControlBean [] beans = null;
						if(StringUtils.isNotBlank(org.getOrgCode() )){
							beans = orgSV.getBeasByOrgCode(DESUtil.encrypt(org.getOrgCode(), RightControlConstant.Right_control_DesKey));
							for(AisRightControlBean bean:beans){
								if(DESUtil.decrypt(bean.getOrgCode(), RightControlConstant.Right_control_DesKey).equals(org.getOrgCode()) && DESUtil.decrypt(bean.getOrgName(), RightControlConstant.Right_control_DesKey).equals(org.getOrgName())){
									if(sdf.parse(time).before(sdf.parse(DESUtil.decrypt(bean.getExpirationTime(), RightControlConstant.Right_control_DesKey)))){
										if(sdf.parse(DESUtil.decrypt(bean.getInstallTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(time))){
											if(sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(time))){
												if(sdf.parse(DESUtil.decrypt(bean.getInstallTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey))) &&  sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(DESUtil.decrypt(bean.getExpirationTime(),RightControlConstant.Right_control_DesKey)))){
													String [] macAll = localMac.split(",");
													for(int macCount = 0;macCount<macAll.length;macCount++){
														String eachMacMw = DESUtil.decrypt(bean.getServerMac(),RightControlConstant.Right_control_DesKey).toUpperCase();
														if(eachMacMw.equals(macAll[macCount].toUpperCase())){
															long expirationTime = sdf.parse(DESUtil.decrypt(bean.getExpirationTime(),RightControlConstant.Right_control_DesKey)).getTime();
															long countResult = expirationTime-sysTime;
															System.out.println(RightControlConstant.WARNTIME);
															if(countResult<=RightControlConstant.WARNTIME){
																residueTime = countResult/(60*60*24*1000);
																isWarn="isWarn";
															}
															checkPass=true;
															break;
														}
													}
												}
											}
										}
									}
								}
							}

						}else{
							com.ai.aris.server.sysmanage.bean.SysOrgBean[] orgManageBeans = null;
							orgManageBeans = sysManageSV.querySysOrgMangeBeans(operatorCode,"25",null);
							//seesion为空时写入
							if("".equals(String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID")))){
								if(orgManageBeans !=null && orgManageBeans.length >0){
									request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID, orgManageBeans[0].getOrgId());
								}
							}
							for(int i=0;i<orgManageBeans.length;i++){
								org.setOrgName(orgManageBeans[i].getOrgName()) ;
								org.setOrgCode(orgManageBeans[i].getOrgCode());
								beans = orgSV.getBeasByOrgCode(DESUtil.encrypt(orgManageBeans[i].getOrgCode(), RightControlConstant.Right_control_DesKey));
								for(AisRightControlBean bean:beans){
									if(DESUtil.decrypt(bean.getOrgCode(), RightControlConstant.Right_control_DesKey).equals(org.getOrgCode()) && DESUtil.decrypt(bean.getOrgName(), RightControlConstant.Right_control_DesKey).equals(org.getOrgName())){
										if(sdf.parse(time).before(sdf.parse(DESUtil.decrypt(bean.getExpirationTime(), RightControlConstant.Right_control_DesKey)))){
											if(sdf.parse(DESUtil.decrypt(bean.getInstallTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(time))){
												if(sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(time))){
													if(sdf.parse(DESUtil.decrypt(bean.getInstallTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey))) &&  sdf.parse(DESUtil.decrypt(bean.getCurrentTime(),RightControlConstant.Right_control_DesKey)).before(sdf.parse(DESUtil.decrypt(bean.getExpirationTime(),RightControlConstant.Right_control_DesKey)))){
														String [] macAll = localMac.split(",");
														for(int macCount = 0;macCount<macAll.length;macCount++){
															String eachMacMw = DESUtil.decrypt(bean.getServerMac(),RightControlConstant.Right_control_DesKey).toUpperCase();
															System.out.println(eachMacMw);
															if(eachMacMw.equals(macAll[macCount].toUpperCase())){
																long expirationTime = sdf.parse(DESUtil.decrypt(bean.getExpirationTime(),RightControlConstant.Right_control_DesKey)).getTime();
																long countResult = expirationTime-sysTime;
																System.out.println(RightControlConstant.WARNTIME);
																if(countResult<=RightControlConstant.WARNTIME){
																	isWarn="isWarn";
																}
																checkPass=true;
																break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
						if (!checkPass){
						  errorCode = "-5";
						  model.addAttribute("errorCode",errorCode);
						  return "redirect:/bpris/login.html";
						}
		        }
		        request.getSession().setAttribute(Constants.SESSION_USER, JsonUtil.toJson(user));
                request.getSession().setAttribute(Constants.SESSION_USER_OBJ, user); 
                request.getSession().setAttribute(Constants.SESSION_MENU, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(menus == null ? new SysMenuBean[0] : menus));
                request.getSession().setAttribute(Constants.SESSION_BUTTON, buttons);
                request.getSession().setAttribute(Constants.SESSION_STATION_BUTTON_RESOURCES, UserUtil.getStationBtnRes(buttons));
                request.getSession().setAttribute(Constants.SESSION_ORG, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(org == null ? new com.ai.aris.server.sysmanage.bean.SysOrgBean() : org));
                request.getSession().setAttribute(Constants.SESSION_ORG_OBJ, org);
		  		request.getSession().setAttribute("residueTime", residueTime);
		  		request.getSession().setAttribute("isWarn", isWarn);
			    String VERSION = DateFormatUtils.format(new Date(System.currentTimeMillis()), "yyyyMMddHHmmss");
			   request.getSession().setAttribute("staticVersion",VERSION);
		  //request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG, JsonUtil.toJson(orgManageBeans));
                //request.getSession().setAttribute(Constants.SESSION_OPERATORS_ORG,SysOperatorBeans);
        } catch (Exception ex) {
        	errorMessage = "登录异常！";
        	errorCode = "-4";
        	ex.printStackTrace();
            logger.error("LoginController->loginAjax error!" + ex);
        }
        if("0".equals(errorCode)){        	
            return "index";
        }        
        model.addAttribute("errorCode",errorCode);
        model.addAttribute("errorMessage",errorMessage);
        return "redirect:/bpris/login.html";      //增加错误参数
    }
	//获取服务器mac地址
	public  String  getLocalMac() throws UnknownHostException {
		Set<NetworkInterface> sets = NetworkUtil.getPhysicalNICs();
		Iterator<NetworkInterface> itx = sets.iterator();
		String mac="";
		while(itx.hasNext()){
			NetworkInterface networkInterface = itx.next();
			mac += ","+NetworkUtil.getMacAddress(networkInterface,"-");
			if("00-00-00-00-00-00-00-e0".equalsIgnoreCase(mac)){
				continue;
			}
			System.out.println("**************************************************************************************************"+mac.toUpperCase()+"**************************************************************************************************");
		}
		System.out.println("**************************************************************************************************"+mac.toUpperCase()+"**************************************************************************************************");
		return  mac;
	}
   /* //获取服务器mac地址
	public  String  getLocalMac() throws Exception {
		// TODO Auto-generated method stub
		//获取网卡，获取地址
		System.out.println("mac数组长1111111：");
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println("mac数组长2222223"+ia);
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		System.out.println("mac数组长度："+mac.length);
		StringBuffer sb = new StringBuffer("");
		for(int i=0; i<mac.length; i++) {
			if(i!=0) {
				sb.append("-");
			}
			//字节转换为整数
			int temp = mac[i]&0xff;
			String str = Integer.toHexString(temp);
			System.out.println("每8位:"+str);
			if(str.length()==1) {
				sb.append("0"+str);
			}else {
				sb.append(str);
			}
		}
		System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
		return  sb.toString();
	}*/
    @RequestMapping("ssoLogin")
   	public void ssoLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
   			// 先获取当前登陆用户
       		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
   			
   			response.setContentType("text/html;charset=UTF-8"); 
   			PrintWriter out = response.getWriter();
   			out.println("<script>");
   			
   			// 获取单点登陆串并用DES解密
   			String ssoLoginStr = request.getParameter("ssoLoginStr");
//   			ssoLoginStr = URLDecoder.decode(ssoLoginStr,"utf-8");
   			System.out.println("encode:  " + ssoLoginStr);
   			ssoLoginStr = DESUtil.decrypt(ssoLoginStr, "asiainfoSSOlogin");
   			
   			//获取用户名和时间
   			Pattern pattern = Pattern.compile("^timeMills=(\\d+)*&userCode=(\\w+)*$");
   			Matcher matcher = pattern.matcher(ssoLoginStr);
   			String accountName = null;
   			long timeMills = -1;
   			
   			if (matcher.find()) {
   				accountName = matcher.group(2);
   				timeMills = Long.valueOf(matcher.group(1));
   			}
   			
   			//没有传递单点登陆串，并且之前用户没登陆过，则重新登陆
   			else if(user == null) {
   				out.print("alert('未找到通行证标识参数，参数名称为ssoLoginStr!');");
   			}
   			
   			// 判断timeMills是否还在生效期内
   			if (timeMills != -1 && (System.currentTimeMillis() - timeMills) / 1000 > 20 && user == null) {
   				out.print("alert('该连接串已失效!');");
   			}
   			
   			//模拟登陆
   			if (timeMills != -1 && (System.currentTimeMillis() - timeMills) / 1000 <= 20){
	    	    /*Map map = new HashMap();
	            map.put("operatorCode",accountName);
//	            map.put("password",passwd);
	            map.put("appType","airis");
	            map.put("secureDesc","123456");               
//                
                AuthorDataResponse authorDataResponse = null;
                try {
                     authorDataResponse = authorSV.retrieveData(map);
                }catch (IOException e){
                    out.print("alert('smartcard登录服务异常!');");
                }
                if(!"0".equals(authorDataResponse.getCode())){
                    out.print("用户名或密码错误!');");
                }*/

                //user = authorDataResponse.getUser();

				//修改 by caorj 用户验证
				//User user = authorDataResponse.getUser();
				String operatorCode = accountName.toUpperCase();
				String ip = WebUtils.getRemoteAddr(request);
				SysOperatorBean userBean = (SysOperatorBean) sysManageSV.login(operatorCode, ip);
				if(userBean==null){
					out.print("用户名不存在！');");
				}
				if(userBean.getOperatorState()!=1){
					out.print("用户失效！');");
				}
				user  = new User();
				user.setOperatorId(String.valueOf(userBean.getOperatorId()));
				user.setOperatorPsw(userBean.getOperatorPsw());
				user.setOperatorCode(userBean.getOperatorCode());
				user.setOperatorName(userBean.getOperatorName());

                //List<Menu> menus = authorDataResponse.getMenuBeans();
				SysMenuBean[] menus = sysManageSV.queryMenuList(operatorCode,"airis");

                //List<Menu> buttons = authorDataResponse.getButtonBeans();
				SysMenuBean[] buttons = sysManageSV.queryButtonList(operatorCode,"airis");

                //根据登录工号取工号对应的医生编码
                AiscUser2CareProvBean user2careProv = commonDataSv.getCareProvByOperatorId(user.getOperatorId());
                if(user2careProv != null){
                	user.setCareProvId(String.valueOf(user2careProv.getCareprovId())); 
                }else{
                	user.setCareProvId(user.getOperatorId()); 
                }                
                
                //归属组织机构
                //SysOrg org = authorDataResponse.getSysOrgBean();
				ISysOrgValue[] orgList =  sysManageSV.getBelongOrgByOprCode(operatorCode);
                com.ai.aris.server.sysmanage.bean.SysOrgBean org = null;
				if(orgList.length>0){
					org = (com.ai.aris.server.sysmanage.bean.SysOrgBean)orgList[0];
				}

                //管理组织机构
                //List<SysOrg> orgManageBeans = authorDataResponse.getSysOrgManageBeans();
                //取当前员工归属组织下的员工信息
                //List<SysOperator> SysOperatorBeans = authorDataResponse.getSysOperatorBeans();

				request.getSession().setAttribute(Constants.SESSION_USER, JsonUtil.toJson(user));
				request.getSession().setAttribute(Constants.SESSION_USER_OBJ, user);
				request.getSession().setAttribute(Constants.SESSION_MENU, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(menus == null ? new SysMenuBean[0] : menus));
				request.getSession().setAttribute(Constants.SESSION_BUTTON, buttons);
				request.getSession().setAttribute(Constants.SESSION_ORG, AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(org == null ? new com.ai.aris.server.sysmanage.bean.SysOrgBean() : org));
				request.getSession().setAttribute(Constants.SESSION_ORG_OBJ, org);
   		    	
   			}		
   			out.println("window.location='" + request.getContextPath() + "/index.html'");
   			out.println("</script>");
   			out.flush();
   			out.close();
   	}

    private boolean setCookie(HttpServletResponse response,HttpServletRequest request,String accountName,String password){
        // 3、种我们自己的cookie
//        String remYes = "true";
//        int remYesTime = 30;
//        try {
//            int maxAge = 0;//设置超时时间
//            if (StringUtils.equals(remYes, "true")) {
//                maxAge = remYesTime;
//                CookieUtil.setCookie(response, cookieDomain, UserHolder.COOKIE_KEY_OUT_TIME, "true", maxAge);
//                String _erp_descd = CookieUtil.getCookie(request, UserHolder.COOKIE_KEY_USERNAME);//从Cookie中获取erp信息（编码后的）
//                String _pwd_descd = CookieUtil.getCookie(request, UserHolder.COOKIE_KEY_PWD);
//
//            } else {
//                CookieUtil.removeCookie(request, response, cookieDomain, UserHolder.COOKIE_KEY_OUT_TIME);
//            }
//            String k = DESUtil.encrypt(accountName);
//            String v = DESUtil.encrypt(password);
//            CookieUtil.setCookie(response, cookieDomain, UserHolder.COOKIE_KEY_USERNAME, k, maxAge);
//            CookieUtil.setCookie(response, cookieDomain, UserHolder.COOKIE_KEY_PWD, v, maxAge);
//        } catch (Exception e) {
//            logger.error("设置Cookie失败，请重新输入！", e);
//            return false;
//        }
        return true;
    }

    /**
     * 退出
     *
     * @return
     * @throws Exception 
     */
    @RequestMapping("logout.html")
	public String loginOut(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception {
		String versionNo= dictSV.queryQueryVersionNo();
		DictItemBean[] dict= dictSV.queryDictItem("SYS_PREFIX");
        if(dict!=null&&dict.length==1){
        	model.addAttribute("system",dict[0].getItemName());
        }
        //清除cookie
        Cookie[] cookies = request.getCookies();
        for(Cookie c :cookies ){
            if(c.getName().equals("oldstarttime")){
            	c.setValue(null);
                c.setMaxAge(0);// 立即销毁cookie
                c.setPath(request.getContextPath());
                response.addCookie(c);
        	}
        	if(c.getName().equals("oldendtime")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldstudyAccnum")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldstudystutascode")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldstudytype")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldqType")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldqValue")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldModalityId")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("oldCheckTimeMark")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        	if(c.getName().equals("c_currentOrgId")){
        		c.setValue(null);
        		c.setMaxAge(0);
        		c.setPath(request.getContextPath());
        		response.addCookie(c);
        	}
        }
		request.getSession().invalidate();
		String isSSOLogout = PropertiesUtils.getContextProperty("isSSOLogout");
		if("true".equals(isSSOLogout)) {
			response.sendRedirect(PropertiesUtils.getContextProperty("casServerLogoutUrl")
					+ "?service=" + PropertiesUtils.getContextProperty("serverName") + "/Aris");
			return null;
		}else {
			model.addAttribute("versionNo", versionNo != "" ? versionNo : "暂无");
			return "login";
		}
	}
    
    @RequestMapping("getMenus.ajax")
    @ResponseBody
    public Map validate(HttpServletResponse response,
            HttpServletRequest request){
    	String menus = (String) request.getSession().getAttribute(Constants.SESSION_MENU);
		Map map = new HashMap();
	    map.put("menus",menus);
        if(menus != null && map.size()>0){
            return map;
        }
        return null;
    }
    
    //查询操员作组织机构 
    public com.ai.aris.server.sysmanage.bean.SysOrgBean[] getOrgs(String operatorCode,String sysType,HttpServletRequest request) throws Exception {
    	   
    	//List<SysOrg> orgManageBeans = null;
        com.ai.aris.server.sysmanage.bean.SysOrgBean[] sysOrgMangeBeans = null;
		try {			 
			/*Map<String,String> map = new HashMap<String, String>();
	        map.put("operatorCode", operatorCode);
	        map.put("sysType", sysType); 
	        map.put("filterInfo", "");
	        OrgsResponse orgs = restClient.retrieveData(sysOrgManagePath,map,OrgsResponse.class);
			//seesion为空时写入
			if("".equals(String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID")))){
				if(orgs.getSysOrgManageBeans() !=null && orgs.getSysOrgManageBeans().size() >0){
					request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID, orgs.getSysOrgManageBeans().get(0).getOrgId());
				}				
			}			
			orgManageBeans = orgs.getSysOrgManageBeans();*/

			sysOrgMangeBeans = sysManageSV.querySysOrgMangeBeans(operatorCode,sysType,null);

			//seesion为空时写入
			if("".equals(String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID")))){
				if(sysOrgMangeBeans !=null && sysOrgMangeBeans.length >0){
					request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID, sysOrgMangeBeans[0].getOrgId());
				}
			}

		} catch (Exception e) {
			logger.error("LoginController--getOrgs:", e);
			e.printStackTrace();
		}  
	    return sysOrgMangeBeans;
    }
    
    //查询操作员管理组织机构--ccrcc
    @RequestMapping("getOrgs.ajax")
    @ResponseBody
    public String getOrgs(String operatorCode,String sysType,String filterInfo,HttpServletRequest request) throws Exception {
    	   
    	//List<SysOrg> orgManageBeans = null;
        com.ai.aris.server.sysmanage.bean.SysOrgBean[] sysOrgMangeBeans = null;
		try {			 
			/*Map<String,String> map = new HashMap<String, String>();
	        map.put("operatorCode", operatorCode);
	        map.put("sysType", sysType);
	        map.put("filterInfo", filterInfo);
	        OrgsResponse orgs = restClient.retrieveData(sysOrgManagePath,map,OrgsResponse.class);*/

            sysOrgMangeBeans = sysManageSV.querySysOrgMangeBeans(operatorCode,sysType,filterInfo);

			//seesion为空时写入
			if("".equals(String.valueOf(request.getSession().getAttribute("_USER_MANAGE_ORG_ID")))){
				if(sysOrgMangeBeans !=null && sysOrgMangeBeans.length >0){
					request.getSession().setAttribute(Constants.SESSION_MANAGE_ORG_ID, sysOrgMangeBeans[0].getOrgId());
				}				
			}			
			//orgManageBeans = orgs.getSysOrgManageBeans();
			 
		} catch (Exception e) {
			logger.error("LoginController--getOrgs:", e);
			e.printStackTrace();
		} 
		System.out.println(AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(sysOrgMangeBeans == null ? new com.ai.aris.server.sysmanage.bean.SysOrgBean[0] : sysOrgMangeBeans) );
        return AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(sysOrgMangeBeans == null ? new com.ai.aris.server.sysmanage.bean.SysOrgBean[0] : sysOrgMangeBeans);
    }
    
    //查询组织机构下所有人员--ccrcc
    @RequestMapping("getOperatorBeansByOp.ajax")
    @ResponseBody
    public String getOperatorBeansByOp(String orgId) throws Exception {
    	   
    	//List<SysOperator> sysOperators = null;
        SysOperatorBean[] sysOperatorsBean = null;
		try {			 
			/*Map<String,String> map = new HashMap<String, String>();
	        map.put("orgId", orgId); 
	        SysOperatorsResponse orgs = restClient.retrieveData(getOperatorPath,map,SysOperatorsResponse.class);
	        sysOperators = orgs.getSysOperatorBeans();*/
            sysOperatorsBean = sysManageSV.queryOperatorBeans(orgId);

		} catch (Exception e) {
			logger.error("LoginController--getOrgs:", e);
			e.printStackTrace();
		}
        return AppframeBeanToJsonUtil.getJsonStringFromBeanWithOutNull(sysOperatorsBean == null ? new SysOperatorBean[0] : sysOperatorsBean);
    }
    

    @RequestMapping("getRootOrgId.ajax")
    @ResponseBody
    public String getRootOrgId() throws IOException {
        /*Map<String,String> map = new HashMap<String, String>();
        map.put("orgCode", rootOrgCode);
        OrgResponse result = restClient.retrieveData(retriveOrgIdPath,map,OrgResponse.class);
        return result.getSysOrgBean().getOrgId();*/

         String orgId = "";
        try {
            com.ai.aris.server.sysmanage.bean.SysOrgBean sysOrgBean = sysManageSV.getOrgBeanByOrgCode(rootOrgCode);
            if(sysOrgBean != null){
                orgId = sysOrgBean.getOrgId();
            }
        } catch (Exception e) {
            logger.error("LoginController--getOrgs:", e);
            e.printStackTrace();
        }
        return orgId;

    }
}
