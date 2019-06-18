package com.ai.aris.web.controller.common;

import com.ai.common.util.AjaxUtil;
import com.ai.common.util.HttpClientUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-9-1
 * Time: 上午10:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/common/tree")
public class TreeSelectController {


    @Value("${orgPath}")
    private String url;


    @RequestMapping("index.html")
    public String index(){
        return "index2";
    }


    @RequestMapping("menuTree.html")
    @ResponseBody
    public JSONObject menuTree(){

        try {

            String result = HttpClientUtil.sendGetRequest(url,"utf-8",200);

        }catch (Exception ex){
            ex.printStackTrace();
            return AjaxUtil.failure("");
        }
        return AjaxUtil.success("");
    }

}
