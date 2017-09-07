package com.xiaosuokeji.yilan.admin.model.security;


import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
public class SecRole extends PaginationModel {

    private Long id;

    private String name;

    private String desc;

    //状态，0未启用，1启用
    private Integer status;

    private String statusDesc;

    private List<SecResource> resources;

    private Long[] resourceIds;

    //拥有，0否，1是
    private Integer checked;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public List<SecResource> getResources() {
        return resources;
    }

    public void setResources(List<SecResource> resources) {
        this.resources = resources;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Long[] getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Long[] resourceIds) {
        this.resourceIds = resourceIds;
        if (resourceIds.length > 0) {
            if (resources == null)
                resources = new ArrayList<>(resourceIds.length);
            for (Long resourceId : resourceIds) {
                SecResource resource = new SecResource();
                resource.setId(resourceId);
                resources.add(resource);
            }
        }
    }
}
