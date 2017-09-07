package com.xiaosuokeji.yilan.server.service.impl.security;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.security.OperationLogConsts;
import com.xiaosuokeji.yilan.server.dao.security.OperationLogDao;
import com.xiaosuokeji.yilan.server.model.security.OperationLog;
import com.xiaosuokeji.yilan.server.service.intf.security.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    private OperationLogDao operationLogDao;

    @Override
    public void save(OperationLog operationLog) {
        operationLogDao.save(operationLog);
    }

    @Override
    public void remove(OperationLog operationLog) {
        operationLogDao.remove(operationLog);
    }

    @Override
    public OperationLog get(OperationLog operationLog) throws BusinessException {
        OperationLog existent = operationLogDao.get(operationLog);
        if (existent == null) throw new BusinessException(OperationLogConsts.OPERATIONLOG_NOT_EXIST);
        return existent;
    }

    @Override
    public PageModel<OperationLog> listAndCount(OperationLog operationLog) {
        operationLog.setDefaultSort("id", "DESC");
        return PageModel.build(operationLogDao.list(operationLog), operationLogDao.count(operationLog));
    }
}
