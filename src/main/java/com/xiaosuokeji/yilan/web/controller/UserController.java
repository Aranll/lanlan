package com.xiaosuokeji.yilan.web.controller;

import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.user.Captcha;
import com.xiaosuokeji.yilan.web.model.user.User;
import com.xiaosuokeji.yilan.web.service.intf.SysPropServer;
import com.xiaosuokeji.yilan.web.service.intf.WebPictureServer;
import com.xiaosuokeji.yilan.web.util.CaptchaUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import net.sf.json.JSON;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller("webUserController")
@RequestMapping("user")
public class UserController {

    @Resource
    private SysPropServer sysPropServer;

    @Resource
    private WebPictureServer webPictureServer;

    @Resource
    private CaptchaUtils captchaUtils;

    @RequestMapping("/center")
    @Index
    public String userCenter(Model model,HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        String token = (String)request.getSession().getAttribute("token");
        if (token==null || "".equals(token) ) {
            return "redirect:/index";
        }

        return "/web/user/UserCenter";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(Model model, User user, HttpServletRequest request){
        JSONObject rawResponse = WebUtils.doRawRequest(API.USER_LOGIN, user);
        if (rawResponse.getBoolean("status")) {
            request.getSession().setAttribute("token", rawResponse.getJSONObject("data").getString("token"));
            rawResponse.put("data","");
        }
        return rawResponse.toString();
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String loginPage(Model model){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        return "web/user/login";
    }

    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("token");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status",true);
        return jsonObject.toString();
    }

    @RequestMapping("register/page")
    public String registerPage(Model model){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        return "/web/user/register";
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public String register(User user){
        JSONObject response = WebUtils.doRawRequest(API.USER_REGISTER, user);
        if(response.getBoolean("status")){
            response.put("data","");
        }
        return response.toString();
    }

    @RequestMapping(value = "/register/captcha/get",method = RequestMethod.POST)
    @ResponseBody
    public String getRegisterCaptcha(User user,HttpServletRequest request){
        Captcha captcha = new Captcha();
        captcha.setContent(user.getTextCaptcha());
        JSONObject rawResponse = null ;
        if(captchaUtils.verify(request, captcha.getContent())){
            rawResponse = WebUtils.doRawRequest(API.USER_REGISTER_CAPTCHA_GET, user);
        }else {
            rawResponse = new JSONObject();
            rawResponse.put("status",false);
            rawResponse.put("code",777);
            rawResponse.put("msg","图片验证码错误");
        }
        return rawResponse.toString();
    }


    @RequestMapping(value = "/password/update",method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(User user,HttpServletRequest request){
        String token = (String)request.getSession().getAttribute("token");
        if(token==null || "".equals(token)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",false);
            jsonObject.put("code",778);
            jsonObject.put("msg","请先登录");
            return jsonObject.toString();
        }
        user.setToken(token);
        return WebUtils.doRawRequest(API.USER_PASSWORD_UPDATE, user).toString();
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(User user,HttpServletRequest request){
        String token = (String)request.getSession().getAttribute("token");
        if(token==null || "".equals(token)){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("status",false);
            jsonObject.put("code",778);
            jsonObject.put("msg","请先登录");
            return jsonObject.toString();
        }
        User user1 =new User();
        user1.setToken(token);
        user1.setEmail(user.getEmail());
        return WebUtils.doRawRequest(API.USER_UPDATE, user1).toString();
    }

    @RequestMapping(value = "/password/captcha/get",method = RequestMethod.POST)
    @ResponseBody
    public String getPasswordCaptcha(User user,HttpServletRequest request){
        Captcha captcha =new Captcha();
        captcha.setContent(user.getTextCaptcha());
        JSONObject rawResponse = null;
        if(captchaUtils.verify(request, captcha.getContent())){
            rawResponse = WebUtils.doRawRequest(API.USER_PASSWORD_FORGET_CAPTCHA_GET, user);
        }else {
            rawResponse = new JSONObject();
            rawResponse.put("status",false);
            rawResponse.put("code",777);
            rawResponse.put("msg","图片验证码错误");
        }
        return rawResponse.toString();
    }

    @RequestMapping(value = "/forgetPasswordPage",method = RequestMethod.GET)
    public String forgetPassword(Model model){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        return "/web/user/forgetPassword";

    }

    @RequestMapping(value = "/forgetPassword",method = RequestMethod.POST)
    @ResponseBody
    public String checkFP(User user,HttpServletRequest request,Model model){
        sysPropServer.getSystemProperty(model);
        webPictureServer.getPictureCategory(model);

        Map<String,String> map = new HashMap<String,String>();
        map.put("mobile",user.getMobile());
        map.put("content",user.getCaptcha().getContent());
        JSONObject jsonObject = WebUtils.doRawRequest(API.USER_CAPTCHA_VERIFY,map);
        if(jsonObject.getBoolean("status")){
            request.getSession().setAttribute("captcha",user.getCaptcha().getContent());
            request.getSession().setAttribute("mobile",user.getMobile());
        }
        return jsonObject.toString();
    }

    @RequestMapping("/forgetPasswordAndResetPage")
    public String resetPasswordPage(Model model,HttpServletRequest request){

        sysPropServer.getSystemProperty(model);

        webPictureServer.getPictureCategory(model);

        String content = (String)request.getSession().getAttribute("captcha");
        String mobile = (String)request.getSession().getAttribute("mobile");
        if (content==null || "".equals(content) || mobile==null || "".equals(mobile)) {
            return "redirect:/index";
        }
        return "/web/user/forgetPasswordAndResetPage";
    }

    @RequestMapping(value = "/resetPassword",method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(User user,HttpServletRequest request,Model model){

        sysPropServer.getSystemProperty(model);
        webPictureServer.getPictureCategory(model);

        String content = (String)request.getSession().getAttribute("captcha");
        String mobile = (String)request.getSession().getAttribute("mobile");
        JSONObject jsonObject = null;
        if (content!=null && !"".equals(content) && mobile!=null && !"".equals(mobile)){
            Captcha captcha =new Captcha();
            captcha.setContent(content);
            user.setCaptcha(captcha);
            user.setMobile(mobile);
            jsonObject = WebUtils.doRawRequest(API.USER_PASSWORD_FORGET_UPDATE,user);
            request.getSession().removeAttribute("captcha");
            request.getSession().removeAttribute("mobile");
        }else {
            jsonObject = new JSONObject();
            jsonObject.put("status",false);
            jsonObject.put("code",777);
            jsonObject.put("msg","请先验证手机号码");
        }

        return jsonObject.toString();
    }

}
