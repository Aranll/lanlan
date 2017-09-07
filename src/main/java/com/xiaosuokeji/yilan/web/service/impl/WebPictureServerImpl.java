package com.xiaosuokeji.yilan.web.service.impl;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.resource.Category;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class WebPictureServerImpl implements WebPictureServer{

    @Override
    public void getPictureCategory(Model model){
        JSONObject pictureCategoriesResponse = WebUtils.doRawRequest(API.PICTURE_CATEGORY_TREE,"");
        if (pictureCategoriesResponse.getBoolean("status"))
            model.addAttribute("pictureCategories",
                    GsonUtils.fromJson(pictureCategoriesResponse.getJSONArray("data").toString(),
                            new TypeToken<List<Category>>() {
                            }.getType()));
    }

}
