package com.xiaosuokeji.yilan.web.model.resource;

import com.xiaosuokeji.yilan.web.model.base.PaginationModel;

import java.util.List;

/**
 * 分类<br/>
 * Created by xiaoran on 2017/7/25.
 */
public class PictureCategory extends PaginationModel {

    private Long id;

    private String name;

    private Integer hot;

    private String hotDesc;

    private PictureCategory parent;

    private List<Picture> resources;

    public List<Picture> getResources() {
        return resources;
    }

    public void setResources(List<Picture> children) {
        this.resources = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getHotDesc() {
        return hotDesc;
    }

    public void setHotDesc(String hotDesc) {
        this.hotDesc = hotDesc;
    }

    public PictureCategory getParent() {
        return parent;
    }

    public void setParent(PictureCategory parent) {
        this.parent = parent;
    }
}
