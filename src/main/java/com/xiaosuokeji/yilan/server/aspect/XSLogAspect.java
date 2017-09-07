package com.xiaosuokeji.yilan.server.aspect;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.util.IpUtil;
import com.xiaosuokeji.yilan.server.constant.security.OperationLogConsts;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import com.xiaosuokeji.yilan.server.model.security.OperationLog;
import com.xiaosuokeji.yilan.server.model.security.SecResource;
import com.xiaosuokeji.yilan.server.model.security.SecStaff;
import com.xiaosuokeji.yilan.server.model.user.User;
import com.xiaosuokeji.yilan.server.service.intf.security.OperationLogService;
import com.xiaosuokeji.yilan.server.service.intf.security.SecurityService;
import com.xiaosuokeji.yilan.server.service.intf.user.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 记录操作日志切面
 * Created by xuxiaowei on 2017/8/8.
 */
@Service
@Aspect
public class XSLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(XSLogAspect.class);

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    @After("@annotation(xsProxy)")
    public void doAfter(JoinPoint joinPoint, XSProxy xsProxy) {
        try {
            //获取请求的相对url
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                    .getRequest();
            String path = request.getContextPath();
            String url = request.getRequestURI().substring(path.length());
            //获取url对应的系统资源
            SecResource resource = new SecResource();
            resource.setUrl(url);
            resource.setMethod("post");
            resource = securityService.findResourceByReq(resource);
            if (resource != null && resource.getLog().equals(1)) {
                if (url.contains("admin")) {
                    /*
                     * 请求管理平台接口
                     * 遍历请求方法的所有参数，从第一个继承BaseModel的请求参数中获取用户id
                     * 要求管理平台请求每个接口必须带上员工id
                     */
                    Object[] params = joinPoint.getArgs();
                    int i = 0;
                    for (; i<params.length; ++i) {
                        if (params[i] instanceof BaseModel) {
                            OperationLog log = new OperationLog();
                            String userId = ((BaseModel) params[i]).getUserId();
                            if (userId != null) {
                                SecStaff staff = securityService.findStaff(new SecStaff(Long.valueOf(userId)));
                                log.setOperatorId(String.valueOf(staff.getId()));
                                log.setOperatorName(staff.getName());
                            }
                            log.setOperatorType(OperationLogConsts.STAFF);
                            log.setIp(IpUtil.getLocalHostIp());
                            log.setUrl(url);
                            log.setOperation(resource.getName());
                            operationLogService.save(log);
                            break;
                        }
                    }
                }
                else if (url.contains("app")) {
                    /*
                     * 请求应用接口
                     * 遍历请求方法的所有参数，从第一个继承BaseModel的请求参数中获取用户id
                     * 因为在XSAuthAspect前置切面中已经将用户令牌换取的用户id设置到所有请求参数中
                     */
                    Object[] params = joinPoint.getArgs();
                    int i = 0;
                    for (; i<params.length; ++i) {
                        if (params[i] instanceof BaseModel) {
                            OperationLog log = new OperationLog();
                            String userId = ((BaseModel) params[i]).getUserId();
                            if (userId != null) {
                                User user = userService.get(new User(userId));
                                log.setOperatorId(String.valueOf(user.getId()));
                                log.setOperatorName(user.getMobile());
                            }
                            else {
                                if (params[i] instanceof User) {
                                    String mobile = ((User) params[i]).getMobile();
                                    if (mobile != null) {
                                        log.setOperatorName(mobile);
                                    }
                                }
                            }
                            log.setOperatorType(OperationLogConsts.USER);
                            log.setIp(IpUtil.getLocalHostIp());
                            log.setUrl(url);
                            log.setOperation(resource.getName());
                            operationLogService.save(log);
                            break;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("error : ", e);
        }
    }
}
