package com.xiaosuokeji.yilan.server.model.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * 用户<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public class User extends BaseModel {

    /**
     * id
     */
    private String id;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空", groups = {Save.class, Login.class, ForGetPassword.class})
    @Pattern(regexp = "^1[0-9]{10}$", message = "手机号格式为11位数字且第一位为1",
            groups = {Save.class, Login.class, ForGetPassword.class})
    private String mobile;

    /**
     * 密码
     */
    @NotNull(message = "密码不能为空", groups = {Save.class, Login.class, ForGetPassword.class, Password.class})
    @Length(min = 6, max = 20 , message = "密码长度为6-20个字符",
            groups = {Save.class, Login.class, ForGetPassword.class, Password.class})
    private String password;

    /**
     * 旧密码
     */
    @NotNull(message = "旧密码不能为空", groups = Password.class)
    @Length(min = 6, max = 20 , message = "旧密码长度为6-20个字符", groups = Password.class)
    private String oldPassword;

    /**
     * 邮箱
     */
    @NotNull(message = "邮箱不能为空", groups = Save.class)
    @Length(min = 1, max = 50, message = "邮箱长度为1-50个字符", groups = {Save.class, Update.class})
    @Email(message = "请输入正确的邮箱", groups = {Save.class, Update.class})
    private String email;

    /**
     * 状态，0冻结，1正常
     */
    @AutoDesc("userStatus")
    private Integer status;

    /**
     * 会员级别，0普通，1会员
     */
    @AutoDesc("vipLevel")
    private Integer vipLevel;

    /**
     * 会员过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date vipExpire;

    /**
     * 最近登录时间
     */
    private Date lastLoginTime;

    /**
     * 验证码
     */
    @Valid
    private Captcha captcha;

    public User() {}

    public User(String id) {
        this.id = id;
    }

    public interface Save{}

    public interface Update{}

    public interface Login {}

    public interface Password {}

    public interface ForGetPassword {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Date getVipExpire() {
        return vipExpire;
    }

    public void setVipExpire(Date vipExpire) {
        this.vipExpire = vipExpire;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
    }
}
