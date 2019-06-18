package com.ai.aris.web.controller.sysmanage;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.common.service.interfaces.ICommonSV;
import com.ai.aris.server.sysmanage.bean.SysOperator2OrgBean;
import com.ai.aris.server.sysmanage.bean.SysStationBean;
import com.ai.aris.server.sysmanage.model.SysStationModel;
import com.ai.aris.server.sysmanage.service.interfaces.IStationManageSV;
import com.ai.aris.server.sysmanage.service.interfaces.ISysOperator2OrgSV;
import com.ai.aris.server.webservice.bean.User;
import com.ai.aris.server.webservice.service.HttpsRestClientConfig;
import com.ai.common.domain.ResultDTO;
import com.ai.common.util.AjaxUtil;
import com.ai.common.util.Constants;
import com.ai.common.util.ServiceUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ZhangFengZhou
 * Date:  2015/8/17
 * Time: 11:13
 * Email:zhangfengzhou@asiainfo.com
 */
@Controller
@RequestMapping("/sysmanage/sysstation")
public class StationController {
    private ICommonSV commonSV = (ICommonSV)ServiceFactory.getService(ICommonSV.class);
    private ISysOperator2OrgSV operator2orgSV = (ISysOperator2OrgSV) ServiceFactory.getService(ISysOperator2OrgSV.class);
    private IStationManageSV stationManageSV = (IStationManageSV) ServiceFactory.getService(IStationManageSV.class);
    @RequestMapping(value = "/page/{one}.html")
    public String getPage(@PathVariable String one, @RequestParam(value="id",required=false) String id, Map map){
        map.put("id",id);
        return "sysmanage/sysstation/"+one;
    }

    @RequestMapping("/getStationList.ajax")
    @ResponseBody
    public JSONObject getStationList(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "rows", defaultValue = "10") int rows,
                                         SysStationModel model) throws Exception {
        ResultDTO result = new ResultDTO(page,rows);
        StringBuffer sb = new StringBuffer (" 1=1 ");
        if(StringUtils.isNotBlank(model.getStationName())){
            sb.append(" AND STATION_NAME like '"+model.getStationName()+"%'");
        }
        if(StringUtils.isNotBlank(model.getStationCode())){
            sb.append(" AND STATION_CODE like '"+model.getStationCode()+"%'");
        }

        result = commonSV.queryPageList(sb.toString(),sb.toString()+" order by STATION_CODE ", result,null,SysStationModel.class,SysStationBean.class);
        return AjaxUtil.jqGridJson(result);
    }

    @RequestMapping("/insertStation")
    @ResponseBody
    public Map insertSation(@RequestBody SysStationBean bean, HttpServletRequest request) throws Exception {
        User operator = (User) request.getSession().getAttribute(Constants.SESSION_USER_OBJ);
        SysOperator2OrgBean sysOperator2Org = operator2orgSV.queryOperatorBelongOrg(operator.getOperatorCode());
        stationManageSV.addStationInfo(bean ,operator.getOperatorCode(),sysOperator2Org);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
    @RequestMapping("/updateStation")
    @ResponseBody
    public Map updateSation(@RequestBody SysStationBean bean) throws Exception {
        commonSV.save(bean);
        Map<String,String> map = new HashMap();
        map.put("ERRCODE", "0");
        return map;
    }
    @RequestMapping("/getStation")
    @ResponseBody
    public Map getStation( @RequestParam String id) throws Exception {
        Map map = ServiceUtil.transerBeanToMap(commonSV.getBean(id,SysStationBean.class));
        map.put("ERRCODE", "0");
        return map;
    }

    @RequestMapping("/deleteStations")
    @ResponseBody
    public Map deleteStations(@RequestParam String stationCodes) throws Exception {
        Map map = new HashMap();
        try{
        commonSV.deleteBatch(stationCodes, SysStationBean.class);
            map.put("ERRCODE",0);
         }catch (Exception e){
            map.put("ERRINFO","删除失败");
        }
        return map;
    }

}
