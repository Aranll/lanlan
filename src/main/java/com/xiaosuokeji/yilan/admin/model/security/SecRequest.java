package com.xiaosuokeji.yilan.admin.model.security;


import com.xiaosuokeji.yilan.admin.model.base.BaseModel;

/**
 * Created by gustinlau on 08/06/2017.
 */
public class SecRequest extends BaseModel {
    private String key;

    public SecRequest(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
