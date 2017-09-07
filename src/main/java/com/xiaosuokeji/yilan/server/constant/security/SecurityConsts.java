package com.xiaosuokeji.yilan.server.constant.security;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 系统安全常量<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
public class SecurityConsts {

    public static final StatusPair RESOURCE_EXIST = StatusPair.build(1031, "资源已存在");

    public static final StatusPair ROLE_EXIST = StatusPair.build(1032, "角色已存在");

    public static final StatusPair STAFF_EXIST = StatusPair.build(1033, "用户已存在");

    public static final StatusPair STAFF_NOT_EXIST = StatusPair.build(1034, "用户不存在");

    public static final StatusPair RESOURCE_USED = StatusPair.build(1035, "资源已被使用");

    public static final StatusPair ROLE_USED = StatusPair.build(1036, "角色已被使用");

    public static final StatusPair ORGANIZATION_EXIST = StatusPair.build(1037, "组织已存在");

    public static final StatusPair ORGANIZATION_USED = StatusPair.build(1038, "组织已被使用");
}
