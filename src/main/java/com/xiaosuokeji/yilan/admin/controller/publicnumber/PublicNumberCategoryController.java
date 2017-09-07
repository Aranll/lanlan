package com.xiaosuokeji.yilan.admin.controller.publicnumber;

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
@Controller("adminPublicNumberCategoryManageController")
@RequestMapping("/admin/publicNumber")
public class PublicNumberCategoryController {
    @Security(pKey = "publicNumber", key = "publicNumber_category")
    @RequestMapping(value = "/publicNumberCategory",method = RequestMethod.GET)
    @Pagination(items = "publicNumbers",api = API.PUBLICNUMBER_CATEGORY_LIST,itemClass = Category.class)
    public String index(Model model, HttpServletRequest request, Category publicNumber){
        model.addAttribute("id",publicNumber.getId());
        model.addAttribute("status",publicNumber.getStatus());
        model.addAttribute("hot",publicNumber.getHot());
        model.addAttribute("seq",publicNumber.getSeq());
        model.addAttribute("dynamic",publicNumber.getDynamic());
        model.addAttribute("parent",publicNumber.getParent());
        return "/admin/publicnumber/publicNumberCategory";
    }

    @RequestMapping(value="/publicNumberCategory/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Category publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_SAVE,publicNumber).toString();
    }

    @RequestMapping(value="/publicNumberCategory/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Category publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_REMOVE,publicNumber).toString();
    }

    @RequestMapping(value="/publicNumberCategory/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Category publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_UPDATE,publicNumber).toString();
    }

    @RequestMapping(value="/publicNumberCategory/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(Category publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_GET,publicNumber).toString();
    }

    @RequestMapping(value="/publicNumberCategory/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Category publicNumber){
        return WebUtils.doRawRequest(API.PUBLICNUMBER_CATEGORY_TREE,publicNumber).toString();
    }
}
