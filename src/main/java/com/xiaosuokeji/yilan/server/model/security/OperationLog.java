package com.xiaosuokeji.yilan.server.model.security;


import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;

/**
 * 操作日志<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
public class OperationLog extends BaseModel {

    /**
     * id
     */
    private Long id;

    /**
     * 操作者id
     */
    private String operatorId;

    /**
     * 操作者名称
     */
    private String operatorName;

    /**
     * 操作者类型，0员工，1用户
     */
    @AutoDesc("operatorType")
    private Integer operatorType;

    /**
     * ip
     */
    private String ip;

    /**
     * url
     */
    private String url;

    /**
     * 操作
     */
    private String operation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
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
