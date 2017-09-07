package com.xiaosuokeji.yilan.web.service.impl;

import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.system.SystemProperty;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SysPropServerImpl implements SysPropServer {

    @Override
    public void getSystemProperty(Model model){
        JSONObject sysPropResponse = WebUtils.doHttpRequest(API.SYSTEM_PROPERTY_GET);
        if (sysPropResponse.getBoolean("status"))
            model.addAttribute("sysProp",
                    GsonUtils.fromJson(sysPropResponse.getJSONObject("data").toString(), SystemProperty.class));
    }

}
