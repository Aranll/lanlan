package com.xiaosuokeji.yilan.admin.controller.website;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Website;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//import com.xiaosuokeji.yilan.admin.util.DictUtils;

/**
 * Created by Aranl_lin on 2017/8/7.
 */
@Controller("adminWebsiteManageController")
@RequestMapping("/admin/website")
public class WebsiteController {
    @Security(pKey = "website", key = "website_website")
    @RequestMapping(value = "/website",method = RequestMethod.GET)
    @Pagination(items = "websites",api = API.WEBSITE_LIST,itemClass = Website.class)
    public String index(Model model, HttpServletRequest request,Website website){
        model.addAttribute("id",website.getId());
        model.addAttribute("hot",website.getHot());
        model.addAttribute("dynamic",website.getDynamic());
        model.addAttribute("recommend",website.getRecommend());
        model.addAttribute("category",website.getCategory());
        model.addAttribute("hotDesc",website.getHotDesc());

        String redirectUrl = request.getRequestURI();
        String queryString = request.getQueryString();
        if (queryString != null) {
            redirectUrl += "?" + queryString;
        }
        model.addAttribute("redirectUrl", CodingUtils.urlEncode(redirectUrl));

        return "admin/website/website";
    }

    @RequestMapping("/website/batchUpload")
    public String bulkWebsite(Model model, HttpServletRequest request,String redirectUrl) {
        model.addAttribute("redirectUrl",redirectUrl);
        return "admin/website/websiteBatchUpload";
    }


    @RequestMapping(value="/website/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Website website){
        return WebUtils.doRawRequest(API.WEBSITE_SAVE,website).toString();
    }

    @RequestMapping(value="/website/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Website website){
        return WebUtils.doRawRequest(API.WEBSITE_REMOVE,website).toString();
    }

    @RequestMapping(value="/website/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Website website){
        return WebUtils.doRawRequest(API.WEBSITE_UPDATE,website).toString();
    }

    @RequestMapping(value="/website/get",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Website website){
        return WebUtils.doRawRequest(API.WEBSITE_GET,website).toString();
    }
}
