package com.xiaosuokeji.yilan.admin.model.user;

import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by gustinlau on 04/08/2017.
 */
public class User extends PaginationModel {

    private String id;
    private String mobile;
    private String email;
    private Integer status;
    private String statusDesc;
    private Integer vipLevel;
    private String vipLevelDesc;
    private String vipExpire;
    private String lastLoginTime;
    private String createTime;
    private String updateTime;
    private Dynamic dynamic;
    private String dynamicId;
    private String dynamicMobile;

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

    public Dynamic getDynamic() {
        return dynamic;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
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

    public String getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(String dynamicId) {
        this.dynamicId = dynamicId;
        if (StringUtils.isNotBlank(dynamicId)) {
            if (this.dynamic == null) {
                this.dynamic = new Dynamic();
            }
            this.dynamic.setId(dynamicId);
        }
    }

    public String getDynamicMobile() {
        return dynamicMobile;
    }

    public void setDynamicMobile(String dynamicMobile) {
        this.dynamicMobile = dynamicMobile;
        if (StringUtils.isNotBlank(dynamicMobile)) {
            if (this.dynamic == null) {
                this.dynamic = new Dynamic();
            }
            this.dynamic.setMobile(dynamicMobile);
        }
    }

    public static class Dynamic {
        private String id;
        private String mobile;

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
    }
}
