package com.xiaosuokeji.yilan.server.controller.security;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.security.OperationLog;
import com.xiaosuokeji.yilan.server.service.intf.security.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 操作日志Controller<br/>
 * Created by xuxiaowei on 2017/8/8.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @RequestMapping(value = "/admin/v1/security/operationLog/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listOperationLog(@RequestBody OperationLog operationLog) {
        return ServiceResult.build().data(operationLogService.listAndCount(operationLog));
    }
}
