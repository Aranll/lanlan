package com.xiaosuokeji.yilan.admin.model.security;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecStaff extends PaginationModel implements UserDetails {

    private Long id;

    private String username;

    private String password;

    //0未启用，1启用
    private Integer status;

    private String statusDesc;

    private String name;

    private String mobile;

    private String email;

    private String qq;

    private List<SecRole> roles;

    private Long[] roleIds;

    private List<SecOrganization> organizations;

    private Long[] organizationIds;

    private Collection<GrantedAuthority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUn(String un) {
        this.username = un;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPw(String pw) {
        if (StringUtils.isNotBlank(pw))
            this.password = pw;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SecRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecRole> roles) {
        this.roles = roles;
    }

    public List<SecOrganization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<SecOrganization> organizations) {
        this.organizations = organizations;
    }

    public Long[] getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(Long[] organizationIds) {
        this.organizationIds = organizationIds;
        if (organizationIds.length > 0) {
            if (organizations == null)
                organizations = new ArrayList<>(organizationIds.length);
            for (Long organizationId : organizationIds) {
                SecOrganization organization = new SecOrganization();
                organization.setId(organizationId);
                organizations.add(organization);
            }
        }
    }

    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status.equals(1);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SecStaff) {
            return this.username.equals(((SecStaff) obj).username);
        }
        return false;
    }
}
