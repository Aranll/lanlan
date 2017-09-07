package com.xiaosuokeji.yilan.web.model.system;

import com.xiaosuokeji.yilan.web.model.base.PaginationModel;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

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

}

