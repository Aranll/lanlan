package com.xiaosuokeji.yilan.admin.model.resource;

import com.xiaosuokeji.yilan.admin.model.base.Resource;

import java.util.List;

public class Book extends Resource {

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * 出版时间
     */
    private String publishDate;

    /**
     * 封面图
     */
    private String cover;

    /**
     * 链接
     */
    private String pdf;

    /**
     * 简介
     */
    private String profile;

    /**
     * 可访问的会员级别，0普通，1会员
     */
    private Integer accessVipLevel;

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

    public Integer getAccessVipLevel() {
        return accessVipLevel;
    }

    public void setAccessVipLevel(Integer accessVipLevel) {
        this.accessVipLevel = accessVipLevel;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
