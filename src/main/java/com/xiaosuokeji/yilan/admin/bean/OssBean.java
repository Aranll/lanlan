package com.xiaosuokeji.yilan.admin.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by GustinLau on 2017-05-05.
 */
@Component("ossBean")
public class OssBean {
    @Value("${oss.accessKeyId}")
    private String accessKeyId;
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;
    @Value("${oss.bucketName}")
    private String bucketName;
    @Value("${oss.endPoint}")
    private String endPoint;
    @Value("${oss.domain}")
    private String domain;

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public String getDomain() {
        return domain;
    }
}
