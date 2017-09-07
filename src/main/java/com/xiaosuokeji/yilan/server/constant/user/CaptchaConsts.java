package com.xiaosuokeji.yilan.server.constant.user;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 验证码常量<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
public class CaptchaConsts {

    /**
     * 注册
     */
    public static final Integer REGISTER = 0;

    /**
     * 忘记密码
     */
    public static final Integer FORGET_PASSWORD = 1;

    public static final StatusPair CAPTCHA_NOT_EXPIRE = StatusPair.build(1004, "验证码未过期，剩余{}秒");

    public static final StatusPair CAPTCHA_INVALID = StatusPair.build(1005, "验证码无效");
}
