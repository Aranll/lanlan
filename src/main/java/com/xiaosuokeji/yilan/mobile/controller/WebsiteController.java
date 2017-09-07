package com.xiaosuokeji.yilan.mobile.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.mobile.model.resource.SearchResource;
import com.xiaosuokeji.yilan.mobile.model.resource.Website;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/28.
 */
@Controller("mobileWebsiteController")
@RequestMapping(value = "/mobile/website",method = RequestMethod.GET)
public class WebsiteController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getWebsites( Model model){
        JSONObject  allResponse = WebUtils.doRawRequest(API.WEBSITE_ALL_LIST,"");
        if(allResponse.getBoolean("status")){
            model.addAttribute("websiteCategory", GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),new TypeToken<List<SearchResource>>() {}.getType()));
        }
        return "mobile/website/website";
    }


    @RequestMapping(value = "/category/{id}",method = RequestMethod.POST)
    public String getCategory(Model model,@PathVariable String id){
        JSONObject  result = WebUtils.doRawRequest(API.WEBSITE_LIST,"{\"category\": {\"id\":"+id+"}}");
        if(result.getBoolean("status")){
            model.addAttribute("webSite",GsonUtils.listFromJson(result.get("data").toString(), Website.class));
        }
        return "mobile/website/category";
    }

}
