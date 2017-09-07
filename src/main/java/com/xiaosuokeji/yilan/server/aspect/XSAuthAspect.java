package com.xiaosuokeji.yilan.server.aspect;

import com.xiaosuokeji.framework.xsjframework.context.ServiceContext;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import com.xiaosuokeji.yilan.server.model.user.Token;
import com.xiaosuokeji.yilan.server.service.intf.user.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 令牌校验切面，该切面会在使用了XSAuth注解的方法之前进行令牌校验
 * Created by xuxiaowei on 2017/8/1.
 */
@Service
@Aspect
public class XSAuthAspect {

    private static final Logger logger = LoggerFactory.getLogger(XSAuthAspect.class);

    @Autowired
    private TokenService tokenService;

    @Before("@annotation(xsAuth)")
    public void doBefore(JoinPoint joinPoint, XSAuth xsAuth) throws BusinessException {
        String url = ServiceContext.get().getHttpServletRequest().getRequestURI();
        //如果请求url中包含"admin"则进行管理平台接口令牌校验，否则进行应用接口令牌校验
        if (url.contains("admin")) verifyAdminToken(joinPoint.getArgs());
        else verifyUserToken(joinPoint.getArgs());
    }

    private void verifyAdminToken(Object[] params) throws BusinessException {
        //遍历请求方法的所有参数，使用第一个继承BaseModel的请求参数进行管理平台接口令牌校验
        int i = 0;
        for (; i<params.length; ++i) {
            if (params[i] instanceof BaseModel) {
                tokenService.verifyAdminToken(new Token(((BaseModel) params[i]).getToken()));
                return;
            }
        }
        //若遍历了所有参数则必然是没有一个参数继承了BaseModel从而未触发校验
        if (i >= params.length) {
            logger.error("至少有一个请求参数继承了BaseModel才能触发令牌校验");
        }
    }

    private void verifyUserToken(Object[] params) throws BusinessException {
        String userId = null;
        //遍历请求方法的所有参数，使用第一个继承BaseModel的请求参数进行应用接口令牌校验
        for (Object param : params) {
            if (param instanceof BaseModel) {
                userId = tokenService.verifyUserToken(new Token(((BaseModel) param).getToken()));
                break;
            }
        }
        if (userId == null) {
            //若用户id仍为空则必然是没有一个参数继承了BaseModel从而未触发校验
            logger.error("至少有一个请求参数继承了BaseModel才能触发令牌校验");
        }
        else {
            //将用户id设置到所有继承BaseModel的参数中
            for (Object param : params) {
                if (param instanceof BaseModel) {
                    ((BaseModel) param).setUserId(userId);
                }
            }
        }
    }
}
