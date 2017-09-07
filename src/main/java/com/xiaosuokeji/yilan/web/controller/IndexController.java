package com.xiaosuokeji.yilan.web.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.model.resource.*;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import com.xiaosuokeji.yilan.web.enumeration.API;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("webIndexController")
public class IndexController {


    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @Index
    public String websiteIndex(Model model, HttpServletRequest request) {

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject websiteHotResponse = WebUtils.doRawRequest(API.WEBSITE_HOT_LIST,"");
        if (websiteHotResponse.getBoolean("status")) {
            model.addAttribute("hotWebsites",
                    GsonUtils.fromJson(websiteHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Website>>() {
                            }.getType()));
        }

        JSONObject websiteCategoriesResponse = WebUtils.doRawRequest(API.WEBSITE_CATEGORY_TREE,"");
        if (websiteCategoriesResponse.getBoolean("status"))
            model.addAttribute("websiteCategories",
                    GsonUtils.fromJson(websiteCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject websiteRecommendResponse = WebUtils.doRawRequest(API.WEBSITE_RECOMMEND_LIST,"");
        if (websiteRecommendResponse.getBoolean("status")) {
            model.addAttribute("websiteRecommends",
                    GsonUtils.fromJson(websiteRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        JSONObject publicNumberHotResponse = WebUtils.doRawRequest(API.PUBLICNUMBER_HOT_LIST,"");
        if (publicNumberHotResponse.getBoolean("status")) {
            model.addAttribute("hotPublicNumbers",
                    GsonUtils.fromJson(publicNumberHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<PublicNumber>>() {
                            }.getType()));
        }

        JSONObject publicNumberCategoriesResponse = WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_TREE,"");
        if (publicNumberCategoriesResponse.getBoolean("status"))
            model.addAttribute("publishNumberCategories",
                    GsonUtils.fromJson(publicNumberCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject publicNumberRecommendResponse = WebUtils.doRawRequest(API.PUBLICNUMBER_RECOMMEND_LIST,"");
        if (publicNumberRecommendResponse.getBoolean("status"))
            model.addAttribute("publishNumberRecommend",
                    GsonUtils.fromJson(publicNumberRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        return "web/index/index";
    }

    @RequestMapping(value = "/app")
    @Index
    public String appIndex(Model model, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject appHotResponse = WebUtils.doRawRequest(API.APP_HOT_LIST,"");
        if (appHotResponse.getBoolean("status")) {
            model.addAttribute("appHot",
                    GsonUtils.fromJson(appHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<App>>() {
                            }.getType()));
        }

        JSONObject appCategoriesResponse = WebUtils.doRawRequest(API.APP_CATEGORY_TREE,"");
        if (appCategoriesResponse.getBoolean("status"))
            model.addAttribute("appCategories",
                    GsonUtils.fromJson(appCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject appRecommendResponse = WebUtils.doRawRequest(API.APP_RECOMMEND_LIST,"");
        if (appRecommendResponse.getBoolean("status")) {
            model.addAttribute("appRecommends",
                    GsonUtils.fromJson(appRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }
        JSONObject miniAppHotResponse = WebUtils.doRawRequest(API.MINAPP_HOT_LIST,"");
        if (miniAppHotResponse.getBoolean("status")) {
            model.addAttribute("miniAppHot",
                    GsonUtils.fromJson(miniAppHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<MiniApp>>() {
                            }.getType()));
        }

        JSONObject miniAppCategoriesResponse = WebUtils.doRawRequest(API.MINAPP_CATEGORY_TREE,"");
        if (miniAppCategoriesResponse.getBoolean("status"))
            model.addAttribute("miniAppCategories",
                    GsonUtils.fromJson(miniAppCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject miniAppRecommendResponse = WebUtils.doRawRequest(API.MINAPP_RECOMMEND_LIST,"");
        if (miniAppRecommendResponse.getBoolean("status")) {
            model.addAttribute("miniAppRecommends",
                    GsonUtils.fromJson(miniAppRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }
        return "/web/index/appIndex";
    }

    @RequestMapping(value = "/search")
    @Index
    public String search(SearchResource searchResource,Model model, HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("name",searchResource.getName());

        JSONObject searchResponse = WebUtils.doRawRequest(API.RESOURCE_LIST,GsonUtils.toJson(searchResource));
        if (searchResponse.getBoolean("status")) {
            model.addAttribute("results",
                    GsonUtils.fromJson(searchResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "/web/index/search";
    }
}
