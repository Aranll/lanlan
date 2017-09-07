package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;

import java.util.List;


public class Video extends Resource {

    /**
     * 作者
     */
    private String author;

    /**
     * 来源，0本站，1外站
     */
    private Integer origin;

    /**
     * 封面图
     */
    private String cover;

    private String url;

    /**
     * 简介
     */
    private String profile;

    /**
     * 可访问的会员级别，0普通，1会员
     */
    private Integer accessVipLevel;

    /**
     * 视频列表
     */
    private List<Video> videos;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Integer getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(Integer accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
