package com.xiaosuokeji.yilan.web.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.model.resource.SearchResource;
import com.xiaosuokeji.yilan.web.model.resource.Video;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("webVideoController")
@RequestMapping("/video")
public class VideoController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping("")
    @Index
    public String videoIndex(Model model, HttpServletRequest request) {

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject videoHotResponse = WebUtils.doRawRequest(API.VIDEO_HOT_LIST, "");
        if (videoHotResponse.getBoolean("status")) {
            model.addAttribute("videoHot",
                    GsonUtils.fromJson(videoHotResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Video>>() {
                            }.getType()));
        }

        JSONObject videoCategoriesResponse = WebUtils.doRawRequest(API.VIDEO_CATEGORY_TREE, "");
        if (videoCategoriesResponse.getBoolean("status"))
            model.addAttribute("videoCategories",
                    GsonUtils.fromJson(videoCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));

        JSONObject videoRecommendResponse = WebUtils.doRawRequest(API.VIDEO_RECOMMEND_LIST, "");
        if (videoRecommendResponse.getBoolean("status")) {
            model.addAttribute("videoRecommends",
                    GsonUtils.fromJson(videoRecommendResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "web/video/videoIndex";
    }

    @RequestMapping("/all")
    @Index
    public String videoAll(Category category, Model model, HttpServletRequest request) {

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("category", category);

        JSONObject allResponse = WebUtils.doRawRequest(API.VIDEO_ALL_LIST, "");
        if (allResponse.getBoolean("status")) {
            model.addAttribute("videos",
                    GsonUtils.fromJson(allResponse.getJSONArray("data").toString(),
                            new TypeToken<List<SearchResource>>() {
                            }.getType()));
        }

        return "web/video/videoAll";
    }


    @RequestMapping("/detail")
    @Index
    public String videoDetail(Video video, Model model, HttpServletRequest request) {

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        JSONObject videoResponse = WebUtils.doRawRequest(API.VIDEO_GET, video);
        if (videoResponse.getBoolean("status"))
            model.addAttribute("video",
                    GsonUtils.fromJson(videoResponse.getJSONObject("data").toString(), Video.class));

        return "/web/video/detail";
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST)
    @ResponseBody
    public String getVideo(HttpServletRequest request, Model model,@RequestBody Video video) {
        request.getSession().getAttribute("token");
        JSONObject videoResponse = WebUtils.doRawRequest(API.VIDEO_GET, video);
        if (videoResponse.getBoolean("status")) {
            Video v = GsonUtils.fromJson(videoResponse.getJSONObject("data").toString(), Video.class);
//            if( v.getAccessVipLevel() == 0 )
                return v.getUrl();
//            else {
//                request.getSession().getAttribute("token");
//            }
        }
        return "false";
    }

}
