package com.xiaosuokeji.yilan.admin.model.base;

import com.xiaosuokeji.yilan.admin.constant.CommonConstants;

/**
 * Created by gustinlau on 04/08/2017.
 */
public abstract class BaseModel {
    private String token = CommonConstants.ADMIN_TOKEN;

    private Long userId;

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
