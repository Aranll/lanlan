package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;

import java.util.List;

/**
 * Created by Aranl_lin on 2017/8/14.
 */
public class Picture extends Resource {
    private String url;
    private String accessVipLevel;
    private String accessVipLevelDesc;

    private List<Picture> pictures;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(String accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public String getAccessVipLevelDesc() {
        return accessVipLevelDesc;
    }

    public void setAccessVipLevelDesc(String accessVipLevelDesc) {
        this.accessVipLevelDesc = accessVipLevelDesc;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
