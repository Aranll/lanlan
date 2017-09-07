package com.xiaosuokeji.yilan.mobile.controller;

import com.xiaosuokeji.yilan.common.SessionUtils;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.common.model.User;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * Created by Aranl_lin on 2017/8/29.
 */
@Controller("mobileUserController")
@RequestMapping(value = "/mobile/user")
public class UserController {
    @RequestMapping(value = {"", "center"}, method = RequestMethod.GET)
    public String getUser(Model model, HttpServletRequest request) {
        User user=SessionUtils.getUser(request);
        if(user!=null) {
            model.addAttribute("user",user );
            return "mobile/user/center";
        }else{
            return "redirect:/mobile/login";
        }
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model,User user) {
        model.addAttribute("user",user);
        return "mobile/user/change-password";
    }

    @RequestMapping(value = "/changePassword/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(HttpServletRequest request,User user,HttpSession session) {
        user.setToken(SessionUtils.getUserToken(request));
        return WebUtils.doRawRequest(API.USER_PASSWORD_UPDATE, user).toString();
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.GET)
    public String recharge(Model model) {
        return "mobile/user/cg-recharge";
    }
}
