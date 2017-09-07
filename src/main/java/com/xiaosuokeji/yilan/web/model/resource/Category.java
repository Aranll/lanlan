package com.xiaosuokeji.yilan.web.model.resource;

import com.xiaosuokeji.yilan.web.model.base.PaginationModel;

import java.util.List;
import java.util.Map;

/**
 * 分类<br/>
 * Created by xiaoran on 2017/7/25.
 */
public class Category extends PaginationModel {

    private Long id;

    private String name;

    private Integer hot;

    private String hotDesc;

    private Category parent;

    private List<Category> children;

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
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

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
