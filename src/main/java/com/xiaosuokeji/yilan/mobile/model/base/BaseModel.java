package com.xiaosuokeji.yilan.mobile.model.base;

/**
 * Created by gustinlau on 04/08/2017.
 */
public abstract class BaseModel {
    private String token;

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
