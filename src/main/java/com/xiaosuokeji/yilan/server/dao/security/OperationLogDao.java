package com.xiaosuokeji.yilan.server.dao.security;

import com.xiaosuokeji.yilan.server.model.security.OperationLog;

import java.util.List;

/**
 * 操作日志Dao<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
public interface OperationLogDao {

    /**
     * 保存操作日志
     * @param operationLog 必填：operatorId，operatorName，operatorType，ip，url，operation
     * @return 受影响函数
     */
    int save(OperationLog operationLog);

    /**
     * 删除操作日志
     * @param operationLog 必填：id
     * @return 受影响函数
     */
    int remove(OperationLog operationLog);

    /**
     * 获取单个操作日志
     * @param operationLog 必填：id
     * @return 操作日志，字段：所有
     */
    OperationLog get(OperationLog operationLog);

    /**
     * 获取多个操作日志
     * @param operationLog 可选条件：operatorId（模糊），operatorName（模糊），operatorTyp，dynamic.startTime，
     *                     dynamic.endTime
     * @return 操作日志列表，字段：所有
     */
    List<OperationLog> list(OperationLog operationLog);

    /**
     * 统计操作日志数量
     * @param operationLog 可选条件：operatorId（模糊），operatorName（模糊），operatorTyp，dynamic.startTime，
     *                     dynamic.endTime
     * @return 操作日志数量
     */
    Long count(OperationLog operationLog);
}
