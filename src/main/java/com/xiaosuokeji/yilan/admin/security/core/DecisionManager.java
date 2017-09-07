package com.xiaosuokeji.yilan.admin.security.core;

import com.xiaosuokeji.yilan.admin.security.constant.SecurityConstant;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
@Service("accessDecisionManager")
public class DecisionManager implements AccessDecisionManager {

	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		//若用户拥有任意一种该请求需要的角色则可以继续访问该请求
		for (ConfigAttribute ca : configAttributes) {
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				if (ca.getAttribute().equals(ga.getAuthority())) return;
			}
		}
		throw new AccessDeniedException(SecurityConstant.ACCESS_DENY.getMsg());
	}

	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
}
