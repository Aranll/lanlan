package com.xiaosuokeji.yilan.admin.controller.user;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.user.User;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//import com.xiaosuokeji.yilan.admin.util.DictUtils;

/**
 * Created by GustinLau on 2017-05-12.
 */
@Controller("adminUserManageController")
@RequestMapping("/admin/user/user")
public class UserController {

    @Security(pKey = "user", key = "user_user")
    @RequestMapping(value = "",method = RequestMethod.GET)
    @Pagination(items = "users",api= API.USER_LIST,itemClass = User.class)
    public String index(Model model, HttpServletRequest request, User user){

        model.addAttribute("id",user.getId());
        model.addAttribute("mobile",user.getMobile());
        model.addAttribute("email",user.getEmail());
        model.addAttribute("status",user.getStatus());
        model.addAttribute("statusDesc",user.getStatusDesc());
        model.addAttribute("vipLevel",user.getVipLevel());
        model.addAttribute("vipLevelDesc",user.getVipLevelDesc());
        model.addAttribute("createTime",user.getCreateTime());
        model.addAttribute("dynamic",user.getDynamic());
        model.addAttribute("dynamicId",user.getDynamicId());
        model.addAttribute("dynamicMobile",user.getDynamicMobile());

        return "admin/user/user";
    }
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(User user,HttpServletRequest request){
        return WebUtils.doRawRequest(API.USER_GET,user).toString();
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(User user,HttpServletRequest request){
        return WebUtils.doRawRequest(API.USER_UPDATE,user).toString();
    }
}
