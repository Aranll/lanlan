package com.xiaosuokeji.yilan.web.annotation;

import com.xiaosuokeji.yilan.web.enumeration.API;

import java.lang.annotation.*;

/**
 * Created by GustinLau on 2017-04-07.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Pagination {

    String limit() default "10";

    String order() default "";

    String sort() default "";

    String items();                                //注入到jsp模板的变量名

    Class itemClass();                              //类型

    API api();                                     //请求API

}
