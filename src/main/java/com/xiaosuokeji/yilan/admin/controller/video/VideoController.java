package com.xiaosuokeji.yilan.admin.controller.video;


import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Video;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
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

@Controller("adminVideoManageController")
@RequestMapping("/admin/video/video")
public class VideoController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @Security(pKey = "video", key = "video_video")
    @RequestMapping("")
    @Pagination(items = "videos", api = API.VIDEO_LIST, itemClass = Video.class)
    public String videoList(Model model, HttpServletRequest request, Video video) {
        model.addAttribute("id", video.getId());
        model.addAttribute("hot", video.getHot());
        model.addAttribute("dynamic", video.getDynamic());
        model.addAttribute("recommend", video.getRecommend());
        model.addAttribute("category", video.getCategory());
        model.addAttribute("accessVipLevel",video.getAccessVipLevel());
        model.addAttribute("origin",video.getOrigin());
        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl",CodingUtils.urlEncode(redirectUrl));
        return "admin/video/video";
    }

    @RequestMapping(value="/videoBatchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/video/videoBatchUpload";
    }

    @RequestMapping("/new")
    public String saveVideo(Model model, HttpServletRequest request,String redirectUrl) {
        model.addAttribute("redirectUrl",redirectUrl);

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "admin/video/videoNew";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public String save(Video video) {
        return WebUtils.doRawRequest(API.VIDEO_SAVE, video).toString();
    }

    @RequestMapping("/edit")
    public String editVideo(Model model, HttpServletRequest request, String redirectUrl,Video video) {
        model.addAttribute("redirectUrl", redirectUrl);
        JSONObject response = WebUtils.doRawRequest(API.VIDEO_GET, video);
        model.addAttribute("video", GsonUtils.fromJson(response.get("data").toString(), Video.class));
        return "admin/video/videoEdit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String updateVideo(Video video) {
        return WebUtils.doRawRequest(API.VIDEO_UPDATE, video).toString();
    }

    @RequestMapping("/get")
    public String getVideo(Model model, HttpServletRequest request,String redirectUrl, Video video) {
        model.addAttribute("redirectUrl",redirectUrl);
        JSONObject response = WebUtils.doRawRequest(API.VIDEO_GET, video);
        model.addAttribute("video", GsonUtils.fromJson(response.get("data").toString(), Video.class));
        return "admin/video/videoDetails";
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    @ResponseBody
    public String remove(Video video) {
        return WebUtils.doRawRequest(API.VIDEO_REMOVE, video).toString();
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public String tree(Video video) {
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_TREE, video).toString();
    }

}
