package com.ai.common.util;

import com.ai.common.json.WebJsonConfig;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * 为了给移动转用的ajax工具类
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public class MobileAjaxUtil {

    private static Log logger = LogFactory.getLog(MobileAjaxUtil.class);



    @SuppressWarnings({"rawtypes", "unchecked"})
    public static JSONObject output(boolean success, String msg, JSONObject extData) {
        Map map = new HashMap();
        map.put("_success", success);
        map.put("success", success);

        map.put("code", success ? 0 : 1);
        map.put("msg", msg);
        map.put("errorMessage", msg);

        if (extData == null || extData.equals("null")) {
            extData = new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        jsonObject
                .put("data", extData);

        return jsonObject;
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static JSONObject output(boolean success, String msg, int code, JSONObject extData) {
        Map map = new HashMap();
        map.put("_success", success);
        map.put("success", success);

        map.put("code", code);
        map.put("msg", msg);
        map.put("errorMessage", msg);

        if (extData == null || extData.equals("null")) {
            extData = new JSONObject();
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        jsonObject
                .put("data", extData);

        return jsonObject;
    }


    public static JSONObject failure(String msg) {
        return output(false, msg, null);
    }
    public static JSONObject failureCode(String msg,int code) {
        return output(false, msg,code, null);
    }
    public static JSONObject failure(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(false, msg, json);
    }


    public static JSONObject success(String msg) {
        return output(true, msg, null);
    }

    public static JSONObject success(String msg, Object object) {
        JSONObject json = JSONObject.fromObject(object, WebJsonConfig.getInstance());
        return output(true, msg, json);
    }

}
