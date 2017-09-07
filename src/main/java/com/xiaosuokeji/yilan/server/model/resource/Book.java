package com.xiaosuokeji.yilan.server.model.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 图书<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public class Book extends Resource {

    /**
     * 作者
     */
    @NotNull(message = "作者不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "作者长度为1-20个字符", groups = {Save.class, Update.class})
    private String author;

    /**
     * 出版社
     */
    @NotNull(message = "出版社不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "出版社长度为1-20个字符", groups = {Save.class, Update.class})
    private String publisher;

    /**
     * 出版时间
     */
    @NotNull(message = "出版时间不能为空", groups = Save.class)
    @Length(min = 1, max = 20, message = "出版时间长度为1-20个字符", groups = {Save.class, Update.class})
    private String publishDate;

    /**
     * 封面图
     */
    @NotNull(message = "封面图不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "封面图长度为1-200个字符", groups = {Save.class, Update.class})
    private String cover;

    /**
     * 链接
     */
    @NotNull(message = "链接图不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "链接图长度为1-200个字符", groups = {Save.class, Update.class})
    private String pdf;

    /**
     * 简介
     */
    @Length(max = 200, message = "简介长度为1-200个字符", groups = {Save.class, Update.class})
    private String profile;

    /**
     * 可访问的会员级别，0普通，1会员
     */
    @NotNull(message = "级别不能为空", groups = Save.class)
    @AutoDesc("resourceAccess")
    private String accessVipLevel;

    /**
     * 图书列表
     */
    private List<Book> books;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(String accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
