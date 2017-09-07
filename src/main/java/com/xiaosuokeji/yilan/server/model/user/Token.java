package com.xiaosuokeji.yilan.server.model.user;

import com.xiaosuokeji.yilan.server.model.base.BaseModel;

/**
 * 令牌<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class Token extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 内容
     */
    private String content;

    public Token() {}

    public Token(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
