package com.xiaosuokeji.yilan.server.constant.user;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 用户常量<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class UserConsts {

    public static final StatusPair USER_EXIST = StatusPair.build(1001, "用户已存在");

    public static final StatusPair USER_NOT_EXIST = StatusPair.build(1002, "用户不存在");

    public static final StatusPair PASSWORD_ERROR = StatusPair.build(1003, "密码错误");

    public static final StatusPair USER_NOT_ENABLE = StatusPair.build(1019, "用户被冻结");
}
