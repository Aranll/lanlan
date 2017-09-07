package com.xiaosuokeji.yilan.admin.controller.periodical;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.resource.Periodical;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * CreatePERIODICAL_lin on 2017/8/13.
 */
@Controller("adminPeriodicalManageController")
@RequestMapping("/admin/periodical")
public class periodicalController {
    @Security(pKey = "periodical", key = "periodical_periodical")
    @RequestMapping(value = "/periodical",method = RequestMethod.GET)
    @Pagination(items = "periodicals",api = API.PERIODICAL_LIST,itemClass = Periodical.class)
    public String index(Model model, HttpServletRequest request, Periodical periodical){
        model.addAttribute("id",periodical.getId());
        model.addAttribute("hot",periodical.getHot());
        model.addAttribute("dynamic",periodical.getDynamic());
        model.addAttribute("recommend",periodical.getRecommend());
        model.addAttribute("category",periodical.getCategory());
        model.addAttribute("hotDesc",periodical.getHotDesc());
        return "admin/periodical/periodical";
    }


    @RequestMapping(value = "/periodical/batchUpload",method = RequestMethod.GET)
    public String batchUpload(){
        return "/admin/periodical/periodicalBatchUpload";
    }

    @RequestMapping(value="/periodical/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Periodical periodical){
        return WebUtils.doRawRequest(API.PERIODICAL_SAVE,periodical).toString();
    }

    @RequestMapping(value="/periodical/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Periodical periodical){
        return WebUtils.doRawRequest(API.PERIODICAL_REMOVE,periodical).toString();
    }

    @RequestMapping(value="/periodical/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Periodical periodical){
        return WebUtils.doRawRequest(API.PERIODICAL_UPDATE,periodical).toString();
    }

    @RequestMapping(value="/periodical/get",method = RequestMethod.POST)
    @ResponseBody
    public String tree(Periodical periodical){
        return WebUtils.doRawRequest(API.PERIODICAL_GET,periodical).toString();
    }
}
