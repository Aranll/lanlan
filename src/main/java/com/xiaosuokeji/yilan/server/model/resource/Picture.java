package com.xiaosuokeji.yilan.server.model.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 图片<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public class Picture extends Resource {

    /**
     * 链接
     */
    @NotNull(message = "链接不能为空", groups = PictureSave.class)
    @Length(min = 1, max = 200, message = "链接长度为1-200个字符", groups = {PictureSave.class, PictureUpdate.class})
    private String url;

    /**
     * 可访问原图的会员级别，0普通，1会员
     */
    @NotNull(message = "级别不能为空", groups = PictureSave.class)
    @AutoDesc("resourceAccess")
    private String accessVipLevel;

    /**
     * 图片列表
     */
    private List<Picture> pictures;

    public Picture() {}

    public Picture(Long id) {
        setId(id);
    }

    public interface PictureSave {}

    public interface PictureUpdate {}

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

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
