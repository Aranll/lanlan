package com.xiaosuokeji.yilan.admin.security.core;

import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecRole;
import com.xiaosuokeji.yilan.admin.model.security.SecStaff;
import com.xiaosuokeji.yilan.admin.security.constant.SecurityConstant;
import com.xiaosuokeji.yilan.admin.util.GsonUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by xuxiaowei on 2017/5/22.
 */
@Service("userDetailService")
public class DetailsService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询员工
        SecStaff staff = new SecStaff();
        staff.setUsername(username);
        JSONObject object = WebUtils.doRawRequest(API.SECURITY_LOGIN, staff);
        if(object.getBoolean("status")){
            staff = GsonUtils.fromJson(object.getJSONObject("data").toString(), SecStaff.class);
            //校验员工状态
            if (!staff.isEnabled()) throw new DisabledException(SecurityConstant.STAFF_NOT_ENABLED.getMsg());
            //校验员工组织状态，若员工不属于任何组织或只要其所处的任意一个组织可用即可
            if (staff.getOrganizations().size() > 0) {
                int i = 0;
                for (; i<staff.getOrganizations().size(); ++i) if (staff.getOrganizations().get(i).getStatus().equals(1)) break;
                if (i >= staff.getOrganizations().size()) throw new DisabledException(SecurityConstant.STAFF_NOT_ENABLED.getMsg());
            }
            //将员工具有的角色放到内存中
            Collection<GrantedAuthority> auths = new ArrayList<>();
            for (SecRole role : staff.getRoles()) auths.add(new SimpleGrantedAuthority("ROLE_"+String.valueOf(role.getId())));
            staff.setAuthorities(auths);
            return staff;
        }
        else throw new UsernameNotFoundException(SecurityConstant.STAFF_NOT_EXIST.getMsg());
    }
}
