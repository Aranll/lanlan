package com.xiaosuokeji.yilan.web.model.resource;

import java.util.List;

public class SearchResource {

    private Long id;

    private String name;

    private Category category;

    private String developer;

    private String cover;

    private String url;

    private String qrCode;

    private String pdf;

    private String author;

    private String publisher;

    private String publishDate;

    private String profile;

    private Integer accessVipLevel;

    private Integer origin;

    private Integer hot;

    private String hotDesc;

    private Category parent;

    private List<SearchResource> children;

    private List<SearchResource> resources;

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public String getHotDesc() {
        return hotDesc;
    }

    public void setHotDesc(String hotDesc) {
        this.hotDesc = hotDesc;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public List<SearchResource> getChildren() {
        return children;
    }

    public void setChildren(List<SearchResource> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

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

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public List<SearchResource> getResources() {
        return resources;
    }

    public void setResources(List<SearchResource> resources) {
        this.resources = resources;
    }
}
