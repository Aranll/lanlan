package com.xiaosuokeji.yilan.server.model.resource;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 软件<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class App extends Resource {

    /**
     * 二维码
     */
    @NotNull(message = "二维码不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "二维码长度为1-200个字符", groups = {Save.class, Update.class})
    private String qrCode;

    /**
     * 开发者
     */
    @NotNull(message = "开发者不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "开发者长度为1-20个字符", groups = {Save.class, Update.class})
    private String developer;

    /**
     * 软件列表
     */
    private List<App> apps;

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

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
