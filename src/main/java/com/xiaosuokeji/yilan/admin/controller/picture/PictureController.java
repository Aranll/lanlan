package com.xiaosuokeji.yilan.admin.controller.picture;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Picture;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/14.
 */
@Controller("adminPictureManageController")
@RequestMapping("/admin/picture")
public class PictureController {

    @Autowired
    private SecurityTokenServer securityTokenServer;

    @Security(pKey = "picture", key = "picture_picture")
    @RequestMapping(value = "/picture",method = RequestMethod.GET)
    @Pagination(items = "pictures",api = API.PICTURE_LIST,itemClass = Picture.class)
    public String index(Model model, HttpServletRequest request, Picture picture){
        model.addAttribute("id",picture.getId());
        model.addAttribute("name",picture.getName());
        model.addAttribute("status",picture.getStatus());
        model.addAttribute("hot",picture.getHot());
        model.addAttribute("seq",picture.getSeq());
        model.addAttribute("dynamic",picture.getDynamic());
        model.addAttribute("url",picture.getUrl());
        model.addAttribute("accessVipLevel",picture.getAccessVipLevel());
        picture.setLimit(15L);

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);

        return "/admin/picture/picture";
    }

    @RequestMapping(value = "/picture/list",method = RequestMethod.POST)
    @ResponseBody
    public String page(Model model,Picture picture){
        picture.setLimit(15L);
        return WebUtils.doRawRequest(API.PICTURE_LIST,picture).toString();
    }

    @RequestMapping(value="/picture/batchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/picture/pictureBatchUpload";
    }



    @RequestMapping(value="/picture/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Picture picture){
        return WebUtils.doRawRequest(API.PICTURE_SAVE,picture).toString();
    }

    @RequestMapping(value="/picture/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Picture picture){
        return WebUtils.doRawRequest(API.PICTURE_REMOVE,picture).toString();
    }

    @RequestMapping(value="/picture/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Picture picture){
        return WebUtils.doRawRequest(API.PICTURE_UPDATE,picture).toString();
    }

    @RequestMapping(value="/picture/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(Picture picture){
        return WebUtils.doRawRequest(API.PICTURE_GET,picture).toString();
    }

    @RequestMapping(value="/picture/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Picture picture){
        return WebUtils.doRawRequest(API.PICTURE_CATEGORY_TREE,picture).toString();
    }
}
