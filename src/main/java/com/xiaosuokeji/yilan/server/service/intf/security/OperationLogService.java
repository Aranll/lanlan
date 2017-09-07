package com.xiaosuokeji.yilan.server.service.intf.security;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.security.OperationLog;

/**
 * 操作日志Service<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
public interface OperationLogService {

    /**
     * 保存操作日志
     * @param operationLog 必填：operatorId，operatorName，operatorType，ip，url，operation
     */
    void save(OperationLog operationLog);

    /**
     * 删除操作日志
     * @param operationLog 必填：id
     */
    void remove(OperationLog operationLog);

    /**
     * 获取单个操作日志
     * @param operationLog 必填：id
     * @return 操作日志，字段：所有
     * @throws BusinessException 操作日志不存在
     */
    OperationLog get(OperationLog operationLog) throws BusinessException;

    /**
     * 获取和统计多个操作日志<br/>
     * 支持排序和分页
     * @param operationLog 可选条件：operatorId（模糊），operatorName（模糊），operatorTyp，dynamic.startTime，
     *                     dynamic.endTime
     * @return 操作日志列表和数量，字段：所有
     */
    PageModel<OperationLog> listAndCount(OperationLog operationLog);
}
