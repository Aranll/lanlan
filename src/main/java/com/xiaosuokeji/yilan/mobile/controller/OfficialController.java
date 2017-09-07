package com.xiaosuokeji.yilan.mobile.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.mobile.model.resource.SearchResource;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by gustinlau on 01/09/2017.
 */
@Controller("mobileOfficialController")
@RequestMapping(value = "/mobile/official",method = RequestMethod.GET)
public class OfficialController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getOfficial( Model model){
        JSONObject allResponse = WebUtils.doRawRequest(API.PUBLICNUMBER_ALL_LIST,"");
        if(allResponse.getBoolean("status")){
            model.addAttribute("officialCategory", GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),new TypeToken<List<SearchResource>>() {}.getType()));
        }
        return "mobile/official/official";
    }

}
