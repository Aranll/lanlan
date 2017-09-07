package com.xiaosuokeji.yilan.admin.security.constant;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * Created by xuxiaowei on 2017/5/30.
 */
public class SecurityConstant {

    public static final StatusPair SUCCESS = StatusPair.build(200, "操作成功");

    public static final StatusPair STAFF_NOT_EXIST = StatusPair.build(700, "用户名不存在");

    public static final StatusPair PASSWORD_ERROR = StatusPair.build(701, "密码错误");

    public static final StatusPair STAFF_NOT_ENABLED = StatusPair.build(702, "用户未启用");

    public static final StatusPair CONCURRENT_BEYOND = StatusPair.build(703, "该账号超出最大登录人数限制");

    public static final StatusPair ACCESS_DENY = StatusPair.build(704, "您的访问被拒绝");

    public static final StatusPair FAILURE = StatusPair.build(999, "系统错误");
}
