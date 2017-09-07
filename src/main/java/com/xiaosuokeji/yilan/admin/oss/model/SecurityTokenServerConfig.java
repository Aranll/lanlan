package com.xiaosuokeji.yilan.admin.oss.model;


/**
 * Created by gustinlau on 24/07/2017.
 */
public class SecurityTokenServerConfig {

    private String AccessKeyID;
    private String AccessKeySecret;
    private String RoleArn;
    private long TokenExpireTime;       //单位s 最少900
    private Policy Policy;

    public String getAccessKeyID() {
        return AccessKeyID;
    }

    public void setAccessKeyID(String AccessKeyID) {
        this.AccessKeyID = AccessKeyID;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String AccessKeySecret) {
        this.AccessKeySecret = AccessKeySecret;
    }

    public String getRoleArn() {
        return RoleArn;
    }

    public void setRoleArn(String RoleArn) {
        this.RoleArn = RoleArn;
    }

    public long getTokenExpireTime() {
        return TokenExpireTime;
    }

    public void setTokenExpireTime(long TokenExpireTime) {
        this.TokenExpireTime = TokenExpireTime;
    }

    public Policy getPolicy() {
        return Policy;
    }

    public void setPolicy(Policy policy) {
        Policy = policy;
    }

}
