package com.xiaosuokeji.yilan.admin.model.security;


import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecResource extends PaginationModel {

    private Long id;

    private Long pid;

    private String key;

    //类型，0菜单，2功能，3url
    private Integer type;

    private String typeDesc;

    private String name;

    private String url;

    private String method;

    private String icon;

    private Long seq;

    private List<SecResource> sons;

    //拥有，0否，1是
    private Integer checked;

    //记录日志，0否，1是
    private Integer log;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public List<SecResource> getSons() {
        return sons;
    }

    public void setSons(List<SecResource> sons) {
        this.sons = sons;
    }

    public void addSon(SecResource resource) {
        if (this.sons == null) this.sons = new ArrayList<>();
        this.sons.add(resource);
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getLog() {
        return log;
    }

    public void setLog(Integer log) {
        this.log = log;
    }
}
