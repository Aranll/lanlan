package com.xiaosuokeji.yilan.admin.controller.system;

/**
 * Created by Aranl_lin on 2017/8/17.
 */

import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.system.Property;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller("adminSystemPropertyController")
@RequestMapping("/admin/system/security/property")
public class PropertyController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @RequestMapping(value = "", method = RequestMethod.GET)
    @Security(pKey = "system", key = "system_property")
    public String index(Model model, HttpServletRequest request, Property property) {
        JSONObject getSystemProperty = WebUtils.doRawRequest(API.SYSTEM_PROPERTY_GET, property);
        if (getSystemProperty.getBoolean("status"))
            model.addAttribute("property", GsonUtils.fromJson(getSystemProperty.getJSONObject("data").toString(),Property.class));
        model.addAttribute("webName",property.getWebName());

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "admin/system/security/property";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Property property) {
        return WebUtils.doRawRequest(API.SYSTEM_PROPERTY_SAVE, property).toString();
    }

}
