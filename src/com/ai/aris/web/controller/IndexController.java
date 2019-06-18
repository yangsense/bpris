package com.ai.aris.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-27
 * Time: 下午3:35
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/")
public class IndexController{
	private Log logger = LogFactory.getLog(IndexController.class);

    @RequestMapping("index.html")
    public String index() throws Exception{
        return "index";
    }

    @RequestMapping("index2.html")
    public String index2() throws Exception{
        return "index2";
    }
    
    @RequestMapping("header.html")
    public String header() throws Exception{
        return "header";
    }
    
    @RequestMapping("menu.html")
    public String menu() throws Exception{
        return "menu";
    }


}
