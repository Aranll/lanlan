package com.xiaosuokeji.yilan.admin.model.system;

import com.xiaosuokeji.yilan.admin.model.Dynamic;
import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by GustinLau on 2017-05-12.
 */
public class Article extends PaginationModel {

    private String createTime;
    private String updateTime;
    private Long id;
    private String title;
    private String content;
    private String url;
    private String publisher;

    private Dynamic dynamic;

    private String dynamicStartTime;
    private String dynamicEndTime;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Dynamic getDynamic() {
        return dynamic;
    }

    public void setDynamic(Dynamic dynamic) {
        this.dynamic = dynamic;
    }

    public String getDynamicStartTime() {
        return dynamicStartTime;
    }

    public void setDynamicStartTime(String dynamicStartTime) {
        this.dynamicStartTime = dynamicStartTime;
        if (StringUtils.isNotBlank(dynamicStartTime)) {
            if (this.dynamic == null) {
                this.dynamic = new Dynamic();
            }
            this.dynamic.setStartTime(dynamicStartTime + " 00:00:00");
        }
    }

    public String getDynamicEndTime() {
        return dynamicEndTime;
    }

    public void setDynamicEndTime(String dynamicEndTime) {
        this.dynamicEndTime = dynamicEndTime;
        if (StringUtils.isNotBlank(dynamicEndTime)) {
            if (this.dynamic == null) {
                this.dynamic = new Dynamic();
            }
            this.dynamic.setEndTime(dynamicEndTime + " 00:00:00");
        }
    }
}

