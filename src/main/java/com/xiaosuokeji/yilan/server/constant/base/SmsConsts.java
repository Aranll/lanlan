package com.xiaosuokeji.yilan.server.constant.base;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 短信常量<br/>
 * Created by xuxiaowei on 2017/8/2.
 */
public class SmsConsts {

    /**
     * 登录短信模板代号
     */
    public static final String SMS_TEMPLATE_LOGIN = "SMS_67200876";

    public static final StatusPair SMS_ERROR = StatusPair.build(1006, "验证码发送失败{}");
}
