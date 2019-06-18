package com.ai.aris.web.controller.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ai.aris.server.sysmanage.bean.IDictValue;
import com.ai.aris.util.cache.DictCache;
import com.ai.common.util.AppframeBeanToJsonUtil;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.bean.DictItemBean;
import com.ai.aris.server.common.model.DictItemModel;
import com.ai.aris.server.common.service.interfaces.IDictItemSV;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;

/**
 * Created by fins on 2015/10/14.
 */
@Controller
@RequestMapping("/dictItem")
public class DictItemController {
    private IDictItemSV sv=(IDictItemSV) ServiceFactory.getService(IDictItemSV.class);

    @RequestMapping(value = "/page/{one}")
    public String getPage(@PathVariable String one,@RequestParam(value="dictName",required=false) String dictName,
                          @RequestParam(value="itemNo",required=false) String itemNo,
                          @RequestParam(value="opType",required=false) String opType,
                          Map map){
        map.put("dictName",dictName);
        map.put("itemNo",itemNo);
        map.put("opType",opType);
        return "common/dictItem/"+one;
    }

    @RequestMapping("/getDictItemList.ajax")
    @ResponseBody
    public JSONObject getDictItemList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "rows", defaultValue = "10") int rows,
                                       DictItemModel dictItemModel) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        result = sv.queryListPage(dictItemModel, result);
        return AjaxUtil.jqGridJson(result);
    }

    @RequestMapping("/getDictItem")
    @ResponseBody
    public DictItemModel getDictItem( @RequestParam String dictName,@RequestParam String itemNo) throws Exception {
        DictItemModel dictItemModel = sv.getDictItem(dictName,itemNo);
        return dictItemModel;
    }

    @RequestMapping("/updateDictItem")
    @ResponseBody
    public Map updateDictItem(@RequestBody DictItemBean bean) throws Exception {
        sv.updateDictItem(bean, "");
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping("/addDictItem")
    @ResponseBody
    public Map addDictItem(@RequestBody DictItemBean bean) throws Exception {
        sv.addDictItem(bean, "");
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping("/deleteDictItem")
    @ResponseBody
    public Map deleteDictItem(@RequestParam String dictName,@RequestParam String itemNo) throws Exception{
        Map map = new HashMap();
        boolean flag = sv.deleteDictItem(dictName, itemNo);
        if(flag){
            map.put("ERRCODE",0);
        }else{
            map.put("ERRINFO","删除失败");
        }
        return map;
    }

    @RequestMapping("/getDictItemCondition")
    @ResponseBody
    public Map getQueryCondition(@RequestParam Map<String,String> dictMap) throws Exception {
        Map map = new HashMap();
        if(!dictMap.isEmpty()){
            for(Map.Entry<String,String> entry:dictMap.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                List<Map> keys = sv.queryDictListMap(value);
                map.put(key,keys);
            }
        }
        return map;
    }
    @RequestMapping("/getAreaInfo.ajax")
    @ResponseBody
    public String  getAreaInfo(String type,String currentSelectValue) throws Exception {
        List<IDictValue> areaBeans = null;
        //省
        if(type.equals("PROVINCE")){
            areaBeans = DictCache.getDictItemByDictNameAndAndLevel("GB/T2260", "1");
            /*if(areaBeans == null ){
                areaBeans.
            }*/
        }
        //市
        if(type.equals("CITY")){
            areaBeans = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("GB/T2260", currentSelectValue, "2");
        }
        //县
        if(type.equals("COUNTY")){
            areaBeans = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("GB/T2260", currentSelectValue, "3");
        }
        //乡
        if(type.equals("TOWN")){
            areaBeans = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("GB/T2260", currentSelectValue, "4");
        }
        //村
        if(type.equals("VILLAGE")){
            areaBeans = DictCache.getDictItemByDictNameAndParentItemNoAndLevel("GB/T2260", currentSelectValue, "5");
        }
        String json  = AppframeBeanToJsonUtil.getJsonStringFromBean(areaBeans);
        return  json;
    }
}
