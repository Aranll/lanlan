package com.xiaosuokeji.yilan.admin.aspect;

import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by gustinlau on 08/06/2017.
 */
@Component
@Aspect
@Order(1)
public class SecurityAspect {

    @Pointcut("execution(* com.xiaosuokeji.yilan.admin.controller.*.*(..))||execution(* com.xiaosuokeji.yilan.admin.controller.*.*.*(..))")
    public void aspect() {
    }

    @After(value = "aspect()&&@annotation(security)")
    public void security(JoinPoint jp, Security security) throws Throwable {

        HttpServletRequest request = null;

        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }
        if (request == null) {
            throw new Exception("方法使用了@Security注解却没有引入HttpServletRequest参数");
        }

        HttpSession session = request.getSession();
        if(session.getAttribute("session_staff")!=null) {
            String pKey = security.pKey();
            if (StringUtils.isNotBlank(pKey))
                //pKey不一致，重新发起请求
                if (!StringUtils.equals(pKey, String.valueOf(session.getAttribute("sec_pKey")))) {
                    session.setAttribute("sec_left_menu", SecurityUtils.getPermissions(pKey));
                    session.setAttribute("sec_pKey", pKey);
                }

            String key = security.key();
            if (StringUtils.isNotBlank(key))
                //key不一致，重新发起请求
                if (!StringUtils.equals(key, String.valueOf(session.getAttribute("sec_key")))) {
                    session.setAttribute("sec_op", SecurityUtils.getPermissions(key));
                    session.setAttribute("sec_key", key);
                }
        }

    }
}
