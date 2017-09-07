package com.xiaosuokeji.yilan.admin.aspect;

import com.xiaosuokeji.yilan.admin.annotation.Index;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by gustinlau on 08/06/2017.
 */
@Component("adminIndexAspect")
@Aspect
@Order(2)
public class IndexAspect {
    @Pointcut("execution(* com.xiaosuokeji.yilan.admin.controller.*.*(..))||execution(* com.xiaosuokeji.yilan.admin.controller.*.*.*(..))")
    public void aspect() {
    }

    @Around(value = "aspect()&&@annotation(index)")
    public Object index(ProceedingJoinPoint pjp, Index index) throws Throwable {

        Object result = pjp.proceed();

        HttpServletRequest request = null;

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }
        if (request == null) {
            throw new Exception("方法使用了@Index注解却没有引入HttpServletRequest参数");
        }

        HttpSession session = request.getSession();
        if (session.getAttribute("session_staff") == null) {
            return "redirect:/admin/login";
        } else {
            return result;
        }
    }
}
