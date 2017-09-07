package com.xiaosuokeji.yilan.admin.oss.model;

/**
 * Created by gustinlau on 24/07/2017.
 */
public class SecurityToken {
    private String status;
    private String AccessKeyId;
    private String AccessKeySecret;
    private String SecurityToken;
    private String Expiration;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccessKeyId() {
        return AccessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        AccessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return AccessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        AccessKeySecret = accessKeySecret;
    }

    public String getSecurityToken() {
        return SecurityToken;
    }

    public void setSecurityToken(String securityToken) {
        SecurityToken = securityToken;
    }

    public String getExpiration() {
        return Expiration;
    }

    public void setExpiration(String expiration) {
        Expiration = expiration;
    }

    @Override
    public String toString() {
        return "SecurityToken{" +
                "status='" + status + '\'' +
                ", AccessKeyId='" + AccessKeyId + '\'' +
                ", AccessKeySecret='" + AccessKeySecret + '\'' +
                ", SecurityToken='" + SecurityToken + '\'' +
                ", Expiration='" + Expiration + '\'' +
                '}';
    }
}
