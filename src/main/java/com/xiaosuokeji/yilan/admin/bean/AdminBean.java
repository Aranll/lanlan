package com.xiaosuokeji.yilan.admin.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by GustinLau on 2017-05-02.
 */
@Component("adminBean")
public class AdminBean {
    @Value("${admin.token}")
    private String token;

    @Value("${admin.domain}")
    private String domain;

    public String getToken() {
        return token;
    }

    public String getDomain() {
        return domain;
    }
}
