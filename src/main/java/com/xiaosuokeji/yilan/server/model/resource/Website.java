package com.xiaosuokeji.yilan.server.model.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 网站<br/>
 * Created by xuxiaowei on 2017/7/31.
 */
public class Website extends Resource {

    /**
     * 链接
     */
    @NotNull(message = "链接不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "链接长度为1-200个字符", groups = {Save.class, Update.class})
    private String url;

    /**
     * 开发者
     */
    @NotNull(message = "开发者不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "开发者长度为1-20个字符", groups = {Save.class, Update.class})
    private String developer;

    /**
     * 网站列表
     */
    private List<Website> websites;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }
}
