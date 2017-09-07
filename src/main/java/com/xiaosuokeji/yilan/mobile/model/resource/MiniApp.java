package com.xiaosuokeji.yilan.mobile.model.resource;

import com.xiaosuokeji.yilan.mobile.model.base.Resource;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
public class MiniApp extends Resource {

    private String qrCode;
    private String developer;


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

