package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
public class MiniApp extends Resource {

    private String qrCode;
    private String developer;
    private List<MiniApp> miniApps;


    public List<MiniApp> getMiniApps() {
        return miniApps;
    }

    public void setMiniApps(List<MiniApp> miniApps) {
        this.miniApps = miniApps;
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

