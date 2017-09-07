package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.Map;

/**
 * 分类<br/>
 * Created by xiaoran on 2017/7/25.
 */
public class Category extends PaginationModel {

    /**
     * id
     */
    private Long id;


    /**
     * 名称
     */
    private String name;

    /**
     * 状态，0关闭，1启用
     */
    private Integer status;

    private String statusDesc;

    private Map<String,String> dynamic;

    /**
     * 热门，0否，1是
     */
    private Integer hot;

    private String hotDesc;

    /**
     * 顺序，越大优先级越高
     */
    private Long seq;

    /**
     * 父级分类，父级id为0表示无父级
     */
    private Category parent;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Map<String, String> getDynamic() {
        return dynamic;
    }

    public void setDynamic(Map<String, String> dynamic) {
        this.dynamic = dynamic;
    }

    public String getHotDesc() {
        return hotDesc;
    }

    public void setHotDesc(String hotDesc) {
        this.hotDesc = hotDesc;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
