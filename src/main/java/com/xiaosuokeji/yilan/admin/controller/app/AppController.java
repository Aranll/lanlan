package com.xiaosuokeji.yilan.admin.controller.app;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.App;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ArAPP_lin on 2017/8/13.
 */
@Controller("adminAppManageController")
@RequestMapping("/admin/app")
public class AppController {

    @Autowired
    private SecurityTokenServer securityTokenServer;

    @RequestMapping(value = "/app", method = RequestMethod.GET)
    @Pagination(items = "apps",api = API.APP_LIST,itemClass = App.class)
    @Security(pKey = "app", key = "app_app")
    public String index(Model model, HttpServletRequest request, App app) {
        model.addAttribute("id",app.getId());
        model.addAttribute("name",app.getName());
        model.addAttribute("hot",app.getHot());
        model.addAttribute("dynamic",app.getDynamic());
        model.addAttribute("recommend",app.getRecommend());
        model.addAttribute("category",app.getCategory());
        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl", CodingUtils.urlEncode(redirectUrl));

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);

        return "/admin/app/app";
    }

    @RequestMapping(value="/app/batchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/app/appBatchUpload";
    }


    @RequestMapping(value = "/app/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(App app) {
        return WebUtils.doRawRequest(API.APP_SAVE, app).toString();
    }
    @RequestMapping(value = "/app/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(App app) {
        return WebUtils.doRawRequest(API.APP_REMOVE,app).toString();
    }
    @RequestMapping(value = "/app/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(App app){
        return WebUtils.doRawRequest(API.APP_UPDATE,app).toString();
    }
    @RequestMapping(value = "app/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(App app){
        return WebUtils.doRawRequest(API.APP_GET,app).toString();
    }
    @RequestMapping(value = "app/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(App app){
        return WebUtils.doRawRequest(API.APP_CATEGORY_TREE,app).toString();
    }
}
