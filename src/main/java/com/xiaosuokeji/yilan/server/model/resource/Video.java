package com.xiaosuokeji.yilan.server.model.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 视频<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public class Video extends Resource {

    /**
     * 作者
     */
    @NotNull(message = "作者不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "作者长度为1-20个字符", groups = {Save.class, Update.class})
    private String author;

    /**
     * 来源，0本站，1外站
     */
    @NotNull(message = "来源不能为空", groups = Save.class)
    @AutoDesc("videoOrigin")
    private Integer origin;

    /**
     * 封面图
     */
    @NotNull(message = "封面图不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "封面图长度为1-200个字符", groups = {Save.class, Update.class})
    private String cover;

    @NotNull(message = "链接不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "链接长度为1-200个字符", groups = {Save.class, Update.class})
    private String url;

    /**
     * 简介
     */
    @Length(max = 200, message = "简介长度为1-200个字符", groups = {Save.class, Update.class})
    private String profile;

    /**
     * 可访问的会员级别，0普通，1会员
     */
    @NotNull(message = "级别不能为空", groups = Save.class)
    @AutoDesc("resourceAccess")
    private String accessVipLevel;

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

    public String getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(String accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}
