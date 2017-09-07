package com.xiaosuokeji.yilan.web.controller;


import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.model.resource.SearchResource;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("webMiniApp")
@RequestMapping("/miniapp")
public class MinAppController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping("/all")
    @Index
    public String getAll(Category category, Model model, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("category",category);

        JSONObject allResponse = WebUtils.doRawRequest(API.MINAPP_ALL_LIST, "");
        if (allResponse.getBoolean("status")) {
            model.addAttribute("miniapps",
                    GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "/web/all/miniappAll";
    }

}
