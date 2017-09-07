package com.xiaosuokeji.yilan.server.model.security;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统角色<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecRole extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Create.class)
    @Length(min = 1, max = 20, message = "名称长度为1-20个字符", groups = {Create.class, Update.class})
    private String name;

    /**
     * 描述
     */
    @Length(max = 50, message = "描述长度为1-50个字符", groups = {Create.class, Update.class})
    private String desc;

    /**
     * 状态，0未启用，1启用
     */
    @AutoDesc("secRoleStatus")
    private Integer status;

    /**
     * 超级管理员角色，0否，1是
     */
    private Integer superior;

    /**
     * 资源列表
     */
    private List<SecResource> resources;

    /**
     * 拥有，0否，1是
     */
    private Integer checked;

    public SecRole() {}

    public SecRole(Long id) {
        this.id = id;
    }

    public interface Create {}

    public interface Update {}

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<SecResource> getResources() {
        return resources;
    }

    public void setResources(List<SecResource> resources) {
        this.resources = resources;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getSuperior() {
        return superior;
    }

    public void setSuperior(Integer superior) {
        this.superior = superior;
    }
}
