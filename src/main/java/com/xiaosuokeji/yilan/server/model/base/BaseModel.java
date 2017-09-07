package com.xiaosuokeji.yilan.server.model.base;

import com.xiaosuokeji.framework.xsjframework.model.GenericModel;

/**
 * 基本模型，定义公共字段<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class BaseModel extends GenericModel {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 令牌
     */
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
