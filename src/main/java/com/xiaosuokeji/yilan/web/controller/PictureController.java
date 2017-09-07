package com.xiaosuokeji.yilan.web.controller;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.annotation.Pagination;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Book;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.model.resource.Picture;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.OssUtils;
import com.xiaosuokeji.yilan.web.util.UserUtils;
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
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller("webPictureController")
@RequestMapping("/picture")
public class PictureController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @RequestMapping("")
    @Index
    public String pictureIndex(Model model, HttpServletRequest request, Picture picture) {

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        model.addAttribute("picture", picture);

        List<Category> categories = null;

        Category c1 = new Category();
        Category c2 = new Category();

        JSONObject picturePathResponse = WebUtils.doRawRequest(API.PICTURE_CATEGORY_PATH, picture.getCategory());
        if (picturePathResponse.getBoolean("status")) {
            categories = GsonUtils.fromJson(picturePathResponse.getJSONArray("data").toString(),
                    new TypeToken<List<Category>>() {
                    }.getType());
            model.addAttribute("picturePath", categories);
        }

        if (categories != null && categories.size() < 3 && categories.size() >= 1) {
            c2.setId(categories.get(categories.size() - 1).getId());
        } else if (categories != null && categories.size() == 3) {
            c2.setId(categories.get(1).getId());
        }
        if (c2.getId() != null) {
            c1.setParent(c2);
            JSONObject pictureHotResponse = WebUtils.doRawRequest(API.PICTURE_CATEGORY_COMBO, c1);
            if (pictureHotResponse.getBoolean("status")) {
                model.addAttribute("pictureCategory",
                        GsonUtils.fromJson(pictureHotResponse.getJSONArray("data").toString(),
                                new TypeToken<List<Category>>() {
                                }.getType()));
            }
        }

        JSONObject jsonObject = WebUtils.doRawRequest(API.PICTURE_LIST, picture);
        if (jsonObject.getBoolean("status")) {
            model.addAttribute("pictures",
                    GsonUtils.fromJson(jsonObject.getJSONObject("data").getJSONArray("list").toString(),
                            new TypeToken<List<Picture>>() {
                            }.getType()));
            model.addAttribute("total",
                    jsonObject.getJSONObject("data").getInt("total"));
        }

        return "/web/picture/index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public String morePicture(Picture picture) {
        return WebUtils.doRawRequest(API.PICTURE_LIST, picture).toString();
    }

    @RequestMapping(value = "check", method = RequestMethod.POST)
    @ResponseBody
    public String checkPicture(Picture picture, HttpServletRequest request) {
        JSONObject bookResponse = WebUtils.doRawRequest(API.PICTURE_GET, picture);
        JSONObject result = new JSONObject();
        if (bookResponse.getBoolean("status")) {
            Picture b = GsonUtils.fromJson(bookResponse.getJSONObject("data").toString(), Picture.class);
            if (b.getAccessVipLevel() != 0) {
                if (!UserUtils.checkLogin(request)) {
                    result.put("status", false);
                    result.put("code", 700);
                } else if (!UserUtils.checkUserVip(request)) {
                    result.put("status", false);
                    result.put("code", 701);
                } else {
                    result.put("status", true);
                    result.put("code", 200);
                }
            } else {
                result.put("status", true);
                result.put("code", 200);
            }
        }

        return result.toString();
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public void getPicture(Picture picture, HttpServletRequest request, HttpServletResponse response) {
        JSONObject bookResponse = WebUtils.doRawRequest(API.PICTURE_GET, picture);
        JSONObject result = new JSONObject();
        if (bookResponse.getBoolean("status")) {
            Picture b = GsonUtils.fromJson(bookResponse.getJSONObject("data").toString(), Picture.class);
            if ((b.getAccessVipLevel() != 0 && UserUtils.checkLogin(request) && UserUtils.checkUserVip(request))
                    || (b.getAccessVipLevel() == 0)) {
                String url=CodingUtils.urlDecode(b.getUrl());
                url = url.substring(b.getUrl().indexOf(".com/")+5);
                if(url.endsWith("/watermark")){
                    url = url.replace("/watermark","");
                }
                try {
                    OutputStream outputStream = response.getOutputStream();
                    OssUtils.fileDownload(url,outputStream);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
    }

}
