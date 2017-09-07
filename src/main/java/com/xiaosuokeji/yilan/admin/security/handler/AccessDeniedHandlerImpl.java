package com.xiaosuokeji.yilan.admin.security.handler;

import com.xiaosuokeji.yilan.admin.security.constant.SecurityConstant;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
@Service("accessDeniedHandler")
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //非Ajax请求
        if (request.getHeader("X-Requested-With") == null) {
            String referer = request.getHeader("Referer");
            if (referer == null || referer.endsWith("/admin/error/704")) {
                if (referer == null) {
                    response.sendRedirect(request.getContextPath() + "/admin/error/704");
                } else
                    response.sendRedirect(request.getContextPath() + "/admin/login");
            } else {
                response.setHeader("Referer", referer);
                response.sendRedirect(request.getContextPath() + "/admin/error/704");
            }
        } else {  //Ajax请求
            String errorMsg = "{\"status\":false,\"code\":" + SecurityConstant.ACCESS_DENY.getCode() + ",\"msg\":\"" + SecurityConstant.ACCESS_DENY.getMsg() + "\"}";
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            try {
                response.getWriter().write(errorMsg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
