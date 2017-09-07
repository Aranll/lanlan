package com.xiaosuokeji.yilan.mobile.controller;

import com.xiaosuokeji.yilan.common.SessionUtils;
import com.xiaosuokeji.yilan.mobile.enumeration.API;
import com.xiaosuokeji.yilan.common.model.User;
import com.xiaosuokeji.yilan.mobile.util.GsonUtils;
import com.xiaosuokeji.yilan.mobile.util.WebUtils;
import com.xiaosuokeji.yilan.web.util.CaptchaUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Aranl_lin on 2017/8/28.
 */
@Controller("mobileLoginController")
@RequestMapping(value = "/mobile")
public class LoginController {
    @Autowired
    CaptchaUtils captchaUtils;

    //region 登录

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "mobile/login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginAction(HttpServletRequest request, User user) {
        JSONObject result = WebUtils.doRawRequest(API.USER_LOGIN, user);
        if (result.getBoolean("status")) {
            SessionUtils.setUser(request, GsonUtils.fromJson(result.getJSONObject("data").toString(), User.class));
        }
        return result.toString();
    }

    //endregion

    //region 注册

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "mobile/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String registerAction(HttpServletRequest request, User user) {
        JSONObject result = WebUtils.doRawRequest(API.USER_REGISTER, user);
        if (result.getBoolean("status")) {
            SessionUtils.setUser(request, GsonUtils.fromJson(result.get("data").toString(), User.class));
        }
        return result.toString();
    }

    @RequestMapping(value = "/register/captcha", method = RequestMethod.POST)
    @ResponseBody
    public String registerCaptcha(HttpServletRequest request, String mobile, String code) {
        if (captchaUtils.verify(request, code)) {
            return WebUtils.doRawRequest(API.USER_REGISTER_CAPTCHA_GET, "{\"mobile\":\"" + mobile + "\"}").toString();
        } else {
            Map errorMap = new HashMap();
            errorMap.put("msg", "验证码错误");
            errorMap.put("code", "1001");
            errorMap.put("status", false);
            return GsonUtils.toJson(errorMap);
        }
    }

    //endregion

    @RequestMapping(value = "/forgetPwd", method = RequestMethod.GET)
    public String forgetPwd() {
        return "mobile/user/forget-password";
    }


    @RequestMapping(value = "/forgetPwd/captcha", method = RequestMethod.POST)
    @ResponseBody
    public String forgetPwdCaptcha(HttpServletRequest request, String mobile, String code) {
        if (captchaUtils.verify(request, code)) {
            return WebUtils.doRawRequest(API.USER_PASSWORD_FORGET_CAPTCHA_GET, "{\"mobile\":\"" + mobile + "\"}").toString();
        } else {
            Map errorMap = new HashMap();
            errorMap.put("code", "1001");
            errorMap.put("status", false);
            return GsonUtils.toJson(errorMap);
        }
    }

    @RequestMapping(value = "/forgetPwd/verifyCaptcha", method = RequestMethod.POST)
    @ResponseBody
    public String verifyCaptcha(User user) {
        Map<String,String> map = new HashMap<>();
        map.put("mobile",user.getMobile());
        map.put("content",user.getCaptcha().getContent());
        return WebUtils.doRawRequest(API.USER_PASSWORD_FORGET_CAPTCHA_VERIFY, map).toString();
    }

    @RequestMapping(value = "/forgetPwd/resetPwdPage", method = RequestMethod.GET)
    public String resetPwdPage(Model model,User user) {
        model.addAttribute("mobile",user.getMobile());
        model.addAttribute("content",user.getCaptcha().getContent());
        return "mobile/user/reset-password";
    }


    @RequestMapping(value = "/forgetPwd/resetPwd", method = RequestMethod.POST)
    @ResponseBody
    public String resetPwd(User user) {
        return WebUtils.doRawRequest(API.USER_PASSWORD_FORGET_UPDATE, user).toString();
    }
}