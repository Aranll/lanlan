package com.xiaosuokeji.yilan.mobile.model.resource;

import com.xiaosuokeji.yilan.mobile.model.base.Resource;

public class Picture extends Resource {
    private String url;
    private Integer accessVipLevel;
    private String accessVipLevelDesc;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(Integer accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public String getAccessVipLevelDesc() {
        return accessVipLevelDesc;
    }

    public void setAccessVipLevelDesc(String accessVipLevelDesc) {
        this.accessVipLevelDesc = accessVipLevelDesc;
    }
}
