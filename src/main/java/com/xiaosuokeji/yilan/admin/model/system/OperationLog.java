package com.xiaosuokeji.yilan.admin.model.system;

import com.xiaosuokeji.yilan.admin.model.Dynamic;
import com.xiaosuokeji.yilan.admin.model.base.PaginationModel;

import java.util.Map;


/**
 * Created by Aranl_lin on 2017/8/16.
 */
public class OperationLog extends PaginationModel{

    private Long id;
    private String createTime;
    private String updateTime;
    private String operatorName;
    private Integer operatorType;
    private String operatorTypeDesc;
    private String ip;
    private String url;
    private String operation;
    private Map<String, String> dynamic;

    public void setDynamic(Map<String, String> dynamic) {
        this.dynamic = dynamic;
    }

    public Map<String, String> getDynamic() {
        return dynamic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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





    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Integer getOperatorType() {
        return operatorType;
    }

    public void setOperatorType(Integer operatorType) {
        this.operatorType = operatorType;
    }


    public String getOperatorTypeDesc() {
        return operatorTypeDesc;
    }

    public void setOperatorTypeDesc(String operatorTypeDesc) {
        this.operatorTypeDesc = operatorTypeDesc;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
