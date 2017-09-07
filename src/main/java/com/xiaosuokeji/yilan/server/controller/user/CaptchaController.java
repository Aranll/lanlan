package com.xiaosuokeji.yilan.server.controller.user;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.constant.user.CaptchaConsts;
import com.xiaosuokeji.yilan.server.model.user.Captcha;
import com.xiaosuokeji.yilan.server.service.intf.user.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 验证码Controller<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @RequestMapping(value = {"/admin/v1/user/register/captcha/get", "/app/v1/user/register/captcha/get"}, method = RequestMethod.POST)
    @XSProxy
    public ServiceResult getRegisterCaptcha(@Validated(Captcha.Save.class) @RequestBody Captcha captcha) throws Exception {
        captcha.setType(CaptchaConsts.REGISTER);
        captchaService.save(captcha);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/app/v1/user/password/forget/captcha/get", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult getForGetPasswordCaptcha(@Validated(Captcha.Save.class) @RequestBody Captcha captcha) throws Exception {
        captcha.setType(CaptchaConsts.FORGET_PASSWORD);
        captchaService.save(captcha);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/app/v1/user/password/forget/captcha/verify", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult verifyCaptcha(@Validated(Captcha.Verify.class) @RequestBody Captcha captcha) throws Exception {
        captcha.setType(CaptchaConsts.FORGET_PASSWORD);
        captchaService.verify(captcha, false);
        return ServiceResult.build();
    }
}
