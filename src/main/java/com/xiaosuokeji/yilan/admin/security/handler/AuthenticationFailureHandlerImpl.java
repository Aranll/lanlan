package com.xiaosuokeji.yilan.admin.security.handler;

import com.xiaosuokeji.yilan.admin.security.constant.SecurityConstant;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
@Service("authenticationFailureHandler")
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		String exceptionMsg = exception.getMessage();
		Integer code = SecurityConstant.FAILURE.getCode();
		String msg = SecurityConstant.FAILURE.getMsg();

		if (exception instanceof UsernameNotFoundException) {
			code = SecurityConstant.STAFF_NOT_EXIST.getCode();
			msg = SecurityConstant.STAFF_NOT_EXIST.getMsg();
		}
		else if (exception instanceof BadCredentialsException) {
			code = SecurityConstant.PASSWORD_ERROR.getCode();
			msg = SecurityConstant.PASSWORD_ERROR.getMsg();
		}
		else if (exception instanceof InternalAuthenticationServiceException) {
			code = SecurityConstant.STAFF_NOT_ENABLED.getCode();
			msg = SecurityConstant.STAFF_NOT_ENABLED.getMsg();
		}
		else if (exceptionMsg.contains("principal exceeded")) {
			code = SecurityConstant.CONCURRENT_BEYOND.getCode();
			msg = SecurityConstant.CONCURRENT_BEYOND.getMsg();
		}
		String errorMsg = "{\"status\":false,\"code\":" + code + ",\"msg\":\"" + msg + "\"}";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().write(errorMsg);
	}
}
