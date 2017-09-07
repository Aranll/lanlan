package com.xiaosuokeji.yilan.admin.security.core;

import com.google.gson.reflect.TypeToken;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecResource;
import com.xiaosuokeji.yilan.admin.model.security.SecRole;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
@Service("securityMetadataSource")
public class InvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //获取请求的路径和方法
        String url = ((FilterInvocation) object).getRequestUrl();
        String method = ((FilterInvocation) object).getRequest().getMethod();
        //去除请求中的参数
        int firstQuestionMarkIndex = url.lastIndexOf("?");
        if (firstQuestionMarkIndex != -1) url = url.substring(0, firstQuestionMarkIndex);

        //查询该请求需要的角色
        SecResource resource = new SecResource();
        resource.setUrl(url);
        resource.setMethod(StringUtils.lowerCase(method));
        JSONObject result = WebUtils.doRawRequest(API.SECURITY_ROLEBSEQ, resource);
        Collection<ConfigAttribute> atts = new ArrayList<>();
        if (result.getBoolean("status")) {
            List<SecRole> list = GsonUtils.fromJson(result.getJSONArray("data").toString(), new TypeToken<List<SecRole>>() {
            }.getType());
            for (SecRole role : list) atts.add(new SecurityConfig("ROLE_" + String.valueOf(role.getId())));
        }
        //如果atts不存在元素，那么不会进入DecisionManager的decide方法，直接通过该过滤器
        return atts;
    }

    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
