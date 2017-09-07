package com.xiaosuokeji.yilan.server.model.security;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统组织<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecOrganization extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 父级名称
     */
    private String pName;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Create.class)
    @Length(min = 1, max = 20, message = "名称长度为1-20个字符", groups = {Create.class, Update.class})
    private String name;

    /**
     * 描述
     */
    @Length(min = 0, max = 50, message = "描述长度为1-50个字符", groups = {Create.class, Update.class})
    private String desc;

    /**
     * 状态，0未启用，1启用
     */
    @AutoDesc("secOrgStatus")
    private Integer status;

    /**
     * 组织列表
     */
    private List<SecOrganization> sons;

    /**
     * 角色列表
     */
    private List<SecRole> roles;

    /**
     * 拥有，0否，1是
     */
    private Integer checked;

    public void addSon(SecOrganization organization) {
        if (this.sons == null) this.sons = new ArrayList<>();
        this.sons.add(organization);
    }

    public interface Create {}

    public interface Update {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public List<SecOrganization> getSons() {
        return sons;
    }

    public void setSons(List<SecOrganization> sons) {
        this.sons = sons;
    }

    public List<SecRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecRole> roles) {
        this.roles = roles;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
