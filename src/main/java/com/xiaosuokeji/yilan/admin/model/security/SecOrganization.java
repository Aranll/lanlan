package com.xiaosuokeji.yilan.admin.model.security;


import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecOrganization extends PaginationModel {

    private Long id;

    private Long pid;

    private String name;

    private String desc;

    //状态，0未启用，1启用
    private Integer status;

    private String statusDesc;

    private List<SecOrganization> sons;

    private List<SecRole> roles;

    private Long[] roleIds;

    //拥有，0否，1是
    private Integer checked;

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

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<SecOrganization> getSons() {
        return sons;
    }

    public void setSons(List<SecOrganization> sons) {
        this.sons = sons;
    }

    public void addSon(SecOrganization organization) {
        if (this.sons == null) this.sons = new ArrayList<>();
        this.sons.add(organization);
    }

    public List<SecRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecRole> roles) {
        this.roles = roles;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
        if (roleIds.length > 0) {
            if (roles == null)
                roles = new ArrayList<>(roleIds.length);
            for (Long roleId : roleIds) {
                SecRole role = new SecRole();
                role.setId(roleId);
                roles.add(role);
            }
        }
    }


    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
