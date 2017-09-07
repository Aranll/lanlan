package com.xiaosuokeji.yilan.admin.controller.video;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Category;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
@Controller("adminVideoCategoryManageController")
@RequestMapping("/admin/video")
public class VideoCategoryController {
    @Security(pKey = "video", key = "video_category")
    @RequestMapping(value = "/videoCategory",method = RequestMethod.GET)
    @Pagination(items = "videos",api = API.VIDEO_CATEGORY_LIST,itemClass = Category.class)
    public String index(Model model, HttpServletRequest request, Category category){
        model.addAttribute("id",category.getId());
        model.addAttribute("status",category.getStatus());
        model.addAttribute("hot",category.getHot());
        model.addAttribute("dynamic",category.getDynamic());
        model.addAttribute("parent",category.getParent());

        return "/admin/video/videoCategory";
    }

    @RequestMapping(value="/videoCategory/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Category category){
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_SAVE,category).toString();
    }

    @RequestMapping(value="/videoCategory/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Category category){
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_REMOVE,category).toString();
    }

    @RequestMapping(value="/videoCategory/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Category category){
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_UPDATE,category).toString();
    }

    @RequestMapping(value="/videoCategory/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(Category category){
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_GET,category).toString();
    }

    @RequestMapping(value="/videoCategory/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Category category){
        return WebUtils.doRawRequest(API.VIDEO_CATEGORY_TREE,category).toString();
    }
}
