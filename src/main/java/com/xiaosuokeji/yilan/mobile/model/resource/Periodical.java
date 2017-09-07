package com.xiaosuokeji.yilan.mobile.model.resource;

import com.xiaosuokeji.yilan.mobile.model.base.Resource;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/13.
 */
public class Periodical extends Resource {
    private String developer;
    private String url;
    private List<Periodical> periodicals;

    public String getDeveloper() {
        return developer;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
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
