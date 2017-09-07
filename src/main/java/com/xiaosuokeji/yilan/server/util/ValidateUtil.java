package com.xiaosuokeji.yilan.server.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * 模型验证器<br/>
 * Created by xuxiaowei on 2017/8/10.
 */
public class ValidateUtil {

    private static final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static Validator getValidator() {
        return validatorFactory.getValidator();
    }
}
