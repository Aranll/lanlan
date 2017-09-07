package com.xiaosuokeji.yilan.web.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.model.resource.Periodical;
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

@Controller("webPeriodicalController")
@RequestMapping("/periodical")
public class PeriodicalController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping(value = "")
    @Index
    public String periodicalIndex(Model model,HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject periodicalHotResponse = WebUtils.doRawRequest(API.PERIODICAL_HOT_LIST,"");
        if (periodicalHotResponse.getBoolean("status")) {
            model.addAttribute("periodicalHot",
                    GsonUtils.fromJson(periodicalHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Periodical>>() {
                            }.getType()));
        }

        JSONObject periodicalCategoriesResponse = WebUtils.doRawRequest(API.PERIODICAL_CATEGORY_TREE,"");
        if (periodicalCategoriesResponse.getBoolean("status"))
            model.addAttribute("periodicalCategories",
                    GsonUtils.fromJson(periodicalCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject periodicalRecommendResponse = WebUtils.doRawRequest(API.PERIODICAL_RECOMMEND_LIST,"");
        if (periodicalRecommendResponse.getBoolean("status")) {
            model.addAttribute("periodicalRecommends",
                    GsonUtils.fromJson(periodicalRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "/web/index/periodicalIndex";
    }


    @RequestMapping("/all")
    @Index
    public String periodicalAll(Model model, Category category, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("category",category);

        JSONObject allResponse = WebUtils.doRawRequest(API.PERIODICAL_ALL_LIST, "");
        if (allResponse.getBoolean("status")) {
            model.addAttribute("periodicals",
                    GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "/web/all/periodicalAll";
    }
}
