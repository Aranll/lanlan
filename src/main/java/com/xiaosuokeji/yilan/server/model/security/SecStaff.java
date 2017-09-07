package com.xiaosuokeji.yilan.server.model.security;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * 系统用户<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecStaff extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    @NotNull(message = "用户名不能为空", groups = Create.class)
    @Length(min = 1, max = 20, message = "用户名长度为1-20个字符", groups = {Create.class, Update.class})
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空", groups = Create.class)
    @Length(min = 6, max = 20, message = "密码长度为6-20个字符", groups = {Create.class, Update.class})
    private String password;

    /**
     * 状态，0未启用，1启用
     */
    @AutoDesc("secStaffStatus")
    private Integer status;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空", groups = Create.class)
    @Length(min = 1, max = 20, message = "姓名长度为1-20个字符", groups = {Create.class, Update.class})
    private String name;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = {Create.class,Update.class})
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式为11位数字且第一位为1", groups = {Create.class, Update.class})
    private String mobile;

    /**
     * 邮箱
     */
    @Length(max = 30, message = "邮箱长度为1-30个字符", groups = {Create.class, Update.class})
    @Email(message = "请输入正确的邮箱", groups = {Create.class, Update.class})
    private String email;

    /**
     * qq号
     */
    @Length(max = 20, message = "qq长度为1-20个字符", groups = {Create.class, Update.class})
    private String qq;

    /**
     * 超级管理员，0否，1是
     */
    private Integer superior;

    /**
     * 角色列表
     */
    private List<SecRole> roles;

    /**
     * 组织列表
     */
    private List<SecOrganization> organizations;

    public interface Create {}

    public interface Update {}

    public SecStaff() {}

    public SecStaff(Long id) {
        this.id = id;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getSuperior() {
        return superior;
    }

    public void setSuperior(Integer superior) {
        this.superior = superior;
    }
}
