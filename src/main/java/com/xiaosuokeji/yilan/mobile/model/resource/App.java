package com.xiaosuokeji.yilan.mobile.model.resource;

import com.xiaosuokeji.yilan.mobile.model.base.Resource;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/13.
 */
public class App extends Resource {

    private String qrCode;
    private String developer;
    private List<App> apps;

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }
}
