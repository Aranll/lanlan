package com.xiaosuokeji.yilan.server.model.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 资源<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class Resource extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "名称长度为1-20个字符", groups = {Save.class, Update.class})
    private String name;

    /**
     * 类别
     */
    @NotNull(message = "类别不能为空", groups = Save.class)
    @Valid
    private Category category;

    /**
     * 热门，0否，1是
     */
    @NotNull(message = "热门不能为空", groups = Save.class)
    @AutoDesc("resourceHot")
    private Integer hot;

    /**
     * 首页推荐，0否，1是
     */
    @NotNull(message = "首页推荐不能为空", groups = Save.class)
    @AutoDesc("resourceRecommend")
    private Integer recommend;

    /**
     * 类别列表
     */
    private List<Category> categories;

    public interface Save{}

    public interface Update{}

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
