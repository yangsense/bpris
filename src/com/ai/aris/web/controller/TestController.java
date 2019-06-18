package com.ai.aris.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-13
 * Time: 下午7:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aris/test/")
public class TestController {

//    private IPublicUserSV publicUserSV = (IPublicUserSV) ServiceFactory.getService(IPublicUserSV.class);


    @RequestMapping("test.html")
    public String test(){
		//String update = (String)request.getSession().getAttribute("update");
        return "test";
    }
   
}
