package com.xiaosuokeji.yilan.web.model.user;

import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import com.xiaosuokeji.yilan.server.model.user.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Captcha {

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
