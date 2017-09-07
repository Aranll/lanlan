package com.xiaosuokeji.yilan.server.constant.security;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 操作日志常量<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
public class OperationLogConsts {

    /**
     * 员工
     */
    public static final Integer STAFF = 0;

    /**
     * 用户
     */
    public static final Integer USER = 1;

    public static final StatusPair OPERATIONLOG_NOT_EXIST = StatusPair.build(1041, "资源已存在");
}
