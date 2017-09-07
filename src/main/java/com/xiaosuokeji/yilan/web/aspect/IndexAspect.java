package com.xiaosuokeji.yilan.web.aspect;

import com.xiaosuokeji.yilan.web.annotation.Index;
import com.xiaosuokeji.yilan.web.enumeration.API;
import com.xiaosuokeji.yilan.web.model.user.User;
import com.xiaosuokeji.yilan.web.util.GsonUtils;
import com.xiaosuokeji.yilan.web.util.WebUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONObject;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by gustinlau on 08/06/2017.
 */
@Component("webIndexAspect")
@Aspect
@Order(2)
public class IndexAspect {
    @Pointcut("execution(* com.xiaosuokeji.yilan.web.controller.*.*(..))||execution(* com.xiaosuokeji.yilan.web.controller.*.*.*(..))")
    public void aspect() {
    }

    @Around(value = "aspect()&&@annotation(index)")
    public Object index(ProceedingJoinPoint pjp, Index index) throws Throwable {

        Object result = pjp.proceed();

        HttpServletRequest request = null;

        Object[] args = pjp.getArgs();

        Model model = null;

        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                continue;
            }
            if (arg instanceof Model) {
                model = (Model) arg;
            }
        }
        if (request == null) {
            throw new Exception("方法使用了@Index注解却没有引入HttpServletRequest参数");
        }
        if (model == null) {
            throw new Exception("方法使用了@Index注解却没有引入Model参数");
        }

        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");
        if (token!=null) {
            User user = new User();
            user.setToken(token);
            JSONObject response = WebUtils.doRawRequest(API.USER_GET,user);
            if(response.getBoolean("status")){
                model.addAttribute("user", GsonUtils.fromJson(response.getJSONObject("data").toString(),User.class));
            }
        }
        return result;
    }
}
