package com.xiaosuokeji.yilan.admin.util;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecRequest;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gustinlau on 08/06/2017.
 */
public class SecurityUtils {

    public static Map<String, String> getPermissions(String key) {
        JSONObject response = WebUtils.doRawRequest(API.SECURITY_ROLEBYRES, new SecRequest(key));
        Map map = new HashMap();
        if (response.getBoolean("status")) {
            map = GsonUtils.fromJson(response.getJSONObject("data").toString(), new TypeToken<Map<String, String>>() {
            }.getType());
        }
        return map;
    }
}
