package com.xiaosuokeji.yilan.server.model.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 分类<br/>
 * Created by xuxiaowei on 2017/7/25.
 */
public class Category extends BaseModel {

    /**
     * id
     */
    @NotNull(message = "分类id不能为空", groups = Resource.Save.class)
    private Long id;

    /**
     * 类型，0网站，1公众号，2app，3小程序，4期刊，5图片，6图书，7视频
     */
    private Integer type;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "名称长度为1-20个字符", groups = {Save.class, Update.class})
    private String name;

    /**
     * 状态，0关闭，1启用
     */
    @NotNull(message = "状态不能为空", groups = Save.class)
    @AutoDesc("categoryStatus")
    private Integer status;

    /**
     * 热门，0否，1是
     */
    @NotNull(message = "热门不能为空", groups = Save.class)
    @AutoDesc("categoryHot")
    private Integer hot;

    /**
     * 顺序，越大优先级越高
     */
    @NotNull(message = "顺序不能为空", groups = Save.class)
    @Range(message = "顺序范围为非负整数", groups = {Save.class, Update.class})
    private Long seq;

    /**
     * 父级分类，父级id为0表示无父级
     */
    private Category parent;

    /**
     * 分类列表
     */
    private List<Category> categories;

    /**
     * 子级分类列表
     */
    private List<Category> children;

    /**
     * 属于该分类的资源列表
     */
    private List<Resource> resources;

    public Category() {}

    public Category(Long id) {
        this.id = id;
    }

    public void addChild(Category category) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(category);
    }

    public void removeChild(Category category) {
        if (this.children != null) {
            this.children.remove(category);
        }
    }

    public void addResource(Resource resource) {
        if (this.resources == null) {
            this.resources = new ArrayList<>();
        }
        this.resources.add(resource);
    }

    public interface Save{}

    public interface Update{}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Category> getChildren() {
        return children;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
