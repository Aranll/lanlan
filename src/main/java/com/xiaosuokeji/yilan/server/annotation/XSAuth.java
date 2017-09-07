package com.xiaosuokeji.yilan.server.annotation;

import java.lang.annotation.*;

/**
 * 令牌校验注解
 * Created by xuxiaowei on 2017/8/1.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface XSAuth {}
