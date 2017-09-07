package com.xiaosuokeji.yilan.admin.controller.miniApp;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.MiniApp;
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
 * Created by Aranl_lin on 2017/8/10.
 */
@Controller("adminMiniAppManageController")
@RequestMapping("/admin/miniApp")
public class MiniAppController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @Security(pKey = "miniApp", key = "miniApp_miniApp")
    @RequestMapping(value = "/miniApp", method = RequestMethod.GET)
    @Pagination(items = "miniApps",api = API.MINIAPP_LIST,itemClass = MiniApp.class)
    public String index(Model model, HttpServletRequest request,MiniApp miniApp) {
        model.addAttribute("id",miniApp.getId());
        model.addAttribute("name",miniApp.getName());
        model.addAttribute("hot",miniApp.getHot());
        model.addAttribute("dynamic",miniApp.getDynamic());
        model.addAttribute("recommend",miniApp.getRecommend());
        model.addAttribute("category",miniApp.getCategory());
        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl", CodingUtils.urlEncode(redirectUrl));

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);

        return "/admin/miniapp/miniApp";
    }

    @RequestMapping(value="/miniApp/batchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/miniapp/miniAppBatchUpload";
    }


    @RequestMapping(value = "/miniApp/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(MiniApp miniApp) {
        return WebUtils.doRawRequest(API.MINIAPP_SAVE, miniApp).toString();
    }
    @RequestMapping(value = "/miniApp/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(MiniApp miniApp) {
        return WebUtils.doRawRequest(API.MINIAPP_REMOVE,miniApp).toString();
    }
    @RequestMapping(value = "/miniApp/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(MiniApp miniApp){
        return WebUtils.doRawRequest(API.MINIAPP_UPDATE,miniApp).toString();
    }
    @RequestMapping(value = "miniApp/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(MiniApp miniApp){
        return WebUtils.doRawRequest(API.MINIAPP_GET,miniApp).toString();
    }
    @RequestMapping(value = "miniApp/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(MiniApp miniApp){
        return WebUtils.doRawRequest(API.MINIAPP_CATEGORY_TREE,miniApp).toString();
    }
}
