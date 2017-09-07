package com.xiaosuokeji.yilan.admin.annotation;

import java.lang.annotation.*;

/**
 * Created by gustinlau on 08/06/2017.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Security {
    String pKey();

    String key();
}
