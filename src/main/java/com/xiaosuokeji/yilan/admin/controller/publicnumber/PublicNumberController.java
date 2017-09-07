package com.xiaosuokeji.yilan.admin.controller.publicnumber;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
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
@Controller("adminPublicNumberManageController")
@RequestMapping("/admin/publicNumber")
public class PublicNumberController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @RequestMapping(value = "/publicNumber", method = RequestMethod.GET)
    @Pagination(items = "publicNumbers",api = API.PUBLICNUMBER_LIST,itemClass = PublicNumber.class)
    @Security(pKey = "publicNumber", key = "publicNumber_publicNumber")
    public String index(Model model, HttpServletRequest request,PublicNumber publicNumber) {
        model.addAttribute("id",publicNumber.getId());
        model.addAttribute("name",publicNumber.getName());
        model.addAttribute("hot",publicNumber.getHot());
        model.addAttribute("dynamic",publicNumber.getDynamic());
        model.addAttribute("recommend",publicNumber.getRecommend());
        model.addAttribute("category",publicNumber.getCategory());

        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);

        return "/admin/publicnumber/publicNumber";
    }

    @RequestMapping(value="/publicNumber/batchUpload",method = RequestMethod.GET)
    public String batchUpload(Model model){
        SecurityToken token=securityTokenServer.getToken();
        model.addAttribute("token",token);
        return "/admin/publicnumber/publicNumberBatchUpload";
    }


    @RequestMapping(value = "/publicNumber/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(PublicNumber publicNumber) {
        return WebUtils.doRawRequest(API.PUBLICNUMBER_SAVE, publicNumber).toString();
    }
    @RequestMapping(value = "/publicNumber/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(PublicNumber publicNumber) {
        return WebUtils.doRawRequest(API.PUBLICNUMBER_REMOVE,publicNumber).toString();
    }
    @RequestMapping(value = "/publicNumber/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(PublicNumber publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_UPDATE,publicNumber).toString();
    }
    @RequestMapping(value = "publicNumber/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(PublicNumber publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_GET,publicNumber).toString();
    }
    @RequestMapping(value = "publicNumber/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(PublicNumber publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_TREE,publicNumber).toString();
    }
}
