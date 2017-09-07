package com.xiaosuokeji.yilan.web.model.base;

import com.xiaosuokeji.yilan.web.model.resource.Category;

import java.util.List;
import java.util.Map;

/**
 * Created by Aranl_lin on 2017/8/11.
 */
public class Resource extends PaginationModel {

    private Long id;
    private String name;
    private Integer status;
    private String statusDesc;
    private Integer hot;
    private Long seq;
    private String hotDesc;
    private Integer recommend;
    private String recommendDesc;
    private String createTime;
    private String updateTime;
    private Category category;
    private Map<String,String> dynamic;
    private List<Category> categories;

    public Map<String, String> getDynamic() {
        return dynamic;
    }

    public void setDynamic(Map<String, String> dynamic) {
        this.dynamic = dynamic;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public String getHotDesc() {
        return hotDesc;
    }

    public void setHotDesc(String hotDesc) {
        this.hotDesc = hotDesc;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public String getRecommendDesc() {
        return recommendDesc;
    }

    public void setRecommendDesc(String recommendDesc) {
        this.recommendDesc = recommendDesc;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
