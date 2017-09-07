package com.xiaosuokeji.yilan.server.model.system;

import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 文章<br/>
 * Created by xuxiaowei on 2017/4/20.
 */
public class Article extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    @NotNull(message = "标题不能为空", groups = Save.class)
    @Length(min = 1, max = 50, message = "标题长度为1-50个字符", groups = {Save.class, Update.class})
    private String title;

    /**
     * 内容
     */
    @NotNull(message = "内容不能为空", groups = Save.class)
    @Length(min = 1, message = "内容长度至少为1个字符", groups = {Save.class, Update.class})
    private String content;

    /**
     * 链接
     */
    @Length(max = 200, message = "链接长度至多200个字符", groups = {Save.class, Update.class})
    private String url;

    /**
     * 发布者
     */
    @NotNull(message = "发布者不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "发布者长度为1-20个字符", groups = {Save.class, Update.class})
    private String publisher;

    public interface Save {}

    public interface Update {}

    public Article() {}

    public Article(Long id) {
        this.id = id;
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
