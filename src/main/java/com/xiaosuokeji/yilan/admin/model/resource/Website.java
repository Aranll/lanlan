package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;

import java.util.List;


/**
 * Created by Aranl_lin on 2017/8/7.
 */
public class Website extends Resource {

    private String developer;
    private String url;
    private List<Website> websites;

    public List<Website> getWebsites() {
        return websites;
    }

    public void setWebsites(List<Website> websites) {
        this.websites = websites;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
