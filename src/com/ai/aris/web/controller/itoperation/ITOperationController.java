package com.ai.aris.web.controller.itoperation;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import com.ai.aris.server.sysmanage.bean.SysOrgBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ai.aris.server.webservice.bean.SysOrg;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.web.controller.workstation.StudyReportController;
import com.ai.common.util.Constants;
import com.ai.common.util.PropertiesUtils;

@Controller
@RequestMapping("/ITOperation")
public class ITOperationController {
	private Log logger = LogFactory.getLog(StudyReportController.class);
	@RequestMapping("init.html")
	public String init(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
		User user = (User) (request.getSession().getAttribute(Constants.SESSION_USER_OBJ));
		SysOrgBean org = (SysOrgBean)(request.getSession().getAttribute(Constants.SESSION_ORG_OBJ));
		String userId = user.getOperatorCode();
		String userName = URLEncoder.encode(URLEncoder.encode(user.getOperatorName(),"UTF-8"));
		String orgName = URLEncoder.encode(URLEncoder.encode(org.getOrgName(),"UTF-8"));
		String orgCode = org.getOrgCode();
		String param = "?userID="+userId+"&appId=PACS&userName="+userName+"&orgName="+orgName+"&orgCode="+orgCode+"&addressCityCode=1";
		logger.error("ITOperationPath=::"+PropertiesUtils.getContextProperty("ITOperationPath"));
		String ITOperationPath = PropertiesUtils.getContextProperty("ITOperationPath")+param;
		model.addAttribute("ITOperationPath",ITOperationPath);
		return "itoperation/itoperation";
	}
}
