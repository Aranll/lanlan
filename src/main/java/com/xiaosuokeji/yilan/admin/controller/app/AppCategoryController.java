package com.xiaosuokeji.yilan.admin.controller.app;

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
@Controller("adminAppCategoryManageController")
@RequestMapping("/admin/app")
public class AppCategoryController {
    @RequestMapping(value = "/appCategory",method = RequestMethod.GET)
    @Pagination(items = "apps",api = API.APP_CATEGORY_LIST,itemClass = Category.class)
    @Security(pKey = "app", key = "app_category")
    public String index(Model model, HttpServletRequest request, Category category){
        model.addAttribute("id",category.getId());
        model.addAttribute("name",category.getName());
        model.addAttribute("status",category.getStatus());
        model.addAttribute("hot",category.getHot());
        model.addAttribute("seq",category.getSeq());
        model.addAttribute("dynamic",category.getDynamic());
        model.addAttribute("parent",category.getParent());

        return "/admin/app/appCategory";
    }

    @RequestMapping(value="/appCategory/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Category category){
        return WebUtils.doRawRequest(API.APP_CATEGORY_SAVE,category).toString();
    }

    @RequestMapping(value="/appCategory/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Category category){
        return WebUtils.doRawRequest(API.APP_CATEGORY_REMOVE,category).toString();
    }

    @RequestMapping(value="/appCategory/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Category category){
        return WebUtils.doRawRequest(API.APP_CATEGORY_UPDATE,category).toString();
    }

    @RequestMapping(value="/appCategory/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(Category category){
        return WebUtils.doRawRequest(API.APP_CATEGORY_GET,category).toString();
    }

    @RequestMapping(value="/appCategory/tree",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Category category){
        return WebUtils.doRawRequest(API.APP_CATEGORY_TREE,category).toString();
    }
}
