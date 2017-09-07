package com.xiaosuokeji.yilan.server.model.security;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统资源<br/>
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecResource extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 父级id
     */
    private Long pid;

    /**
     * 父级名称
     */
    private String pName;

    /**
     * 键值
     */
    @NotNull(message = "键不能为空", groups = Create.class)
    @Length(min = 1, max = 50, message = "键长度为1-50个字符", groups = {Create.class, Update.class})
    private String key;

    /**
     * 类型，0菜单，2功能，3url
     */
    @AutoDesc("secResourceType")
    private Integer type;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Create.class)
    @Length(min = 1, max = 50, message = "名称长度为1-50个字符", groups = {Create.class, Update.class})
    private String name;

    /**
     * url
     */
    @Length(max = 100, message = "url长度为1-100个字符", groups = {Create.class, Update.class})
    private String url;

    /**
     * 请求方法
     */
    @Length(max = 10, message = "方法长度为1-10个字符", groups = {Create.class, Update.class})
    private String method;

    /**
     * 图标
     */
    private String icon;

    /**
     * 顺序，越大优先级越高
     */
    @NotNull(message = "顺序不能为空", groups = Create.class)
    @Range(message = "顺序范围为非负整数", groups = {Create.class, Update.class})
    private Long seq;

    /**
     * 记录日志，0否，1是
     */
    private Integer log;

    /**
     * 子级资源列表
     */
    private List<SecResource> sons;

    /**
     * 角色列表
     */
    private List<SecRole> roles;

    /**
     * 拥有，0否，1是
     */
    private Integer checked;

    public void addSon(SecResource resource) {
        if (this.sons == null) this.sons = new ArrayList<>();
        this.sons.add(resource);
    }

    public interface Create {}

    public interface Update {}

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

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
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

    public Integer getLog() {
        return log;
    }

    public void setLog(Integer log) {
        this.log = log;
    }

    public List<SecResource> getSons() {
        return sons;
    }

    public void setSons(List<SecResource> sons) {
        this.sons = sons;
    }

    public List<SecRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SecRole> roles) {
        this.roles = roles;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }
}
