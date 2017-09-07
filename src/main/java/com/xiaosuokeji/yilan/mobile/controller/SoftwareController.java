package com.xiaosuokeji.yilan.mobile.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.mobile.annotation.Pagination;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.mobile.model.resource.App;

import com.xiaosuokeji.yilan.mobile.model.resource.Category;
import com.xiaosuokeji.yilan.mobile.model.resource.SearchResource;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/28.
 */
@Controller("mobileSoftwareController")

@RequestMapping(value = "/mobile/software",method = RequestMethod.GET)
public class SoftwareController {

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public String getAll(Category category, Model model, HttpServletRequest request){
        model.addAttribute("category",category);
        JSONObject allResponse = WebUtils.doRawRequest(API.APP_CATEGORY_TREE, "");
        if (allResponse.getBoolean("status")) {
            model.addAttribute("softwares",
                    GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {}.getType()));
        }
        return "mobile/software/cg-medicine-software";
    }

    @RequestMapping(value = "/category",method = RequestMethod.POST)
    @Pagination(items = "apps",api = API.APP_LIST,itemClass = App.class)
    @ResponseBody
    public String getCategory(@RequestParam("page") Long page, Model model, App app){
        app.setLimit(5L);
        app.setPage(page);
        model.addAttribute("apps",app.getApps());
        return WebUtils.doRawRequest(API.APP_LIST,app).toString();
    }
}
