package com.xiaosuokeji.yilan.web.model.user;

import com.xiaosuokeji.yilan.web.model.base.PaginationModel;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gustinlau on 04/08/2017.
 */
public class User extends PaginationModel {


    private String id;
    private String mobile;
    private String email;
    private String password;
    private String oldPassword;
    private Integer status;
    private String statusDesc;
    private Integer vipLevel;
    private String vipLevelDesc;
    private String vipExpire;
    private String lastLoginTime;
    private String createTime;
    private String updateTime;
    private Captcha captcha;
    private String textCaptcha;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getTextCaptcha() {
        return textCaptcha;
    }

    public void setTextCaptcha(String textCaptcha) {
        this.textCaptcha = textCaptcha;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

    public void setCaptcha(Captcha captcha) {
        this.captcha = captcha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public String getVipLevelDesc() {
        return vipLevelDesc;
    }

    public void setVipLevelDesc(String vipLevelDesc) {
        this.vipLevelDesc = vipLevelDesc;
    }

    public String getVipExpire() {
        return vipExpire;
    }

    public void setVipExpire(String vipExpire) {
        this.vipExpire = vipExpire;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
