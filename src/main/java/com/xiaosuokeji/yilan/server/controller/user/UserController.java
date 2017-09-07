package com.xiaosuokeji.yilan.server.controller.user;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.user.User;
import com.xiaosuokeji.yilan.server.service.intf.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户Controller<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/v1/user/status/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateStatus(@RequestBody User user) {
        userService.updateStatus(user);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/user/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody User user) throws BusinessException {
        return ServiceResult.build().data(userService.get(user));
    }

    @RequestMapping(value = "/admin/v1/user/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCount(@RequestBody User user) {
        return ServiceResult.build().data(userService.listAndCount(user));
    }

    @RequestMapping(value = "/app/v1/user/register", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult register(@Validated(User.Save.class) @RequestBody User user) throws Exception {
        return ServiceResult.build().data(userService.save(user));
    }

    @RequestMapping(value = "/app/v1/user/password/forget/update", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult updatePasswordByMobile(
            @Validated({User.ForGetPassword.class}) @RequestBody User user) throws Exception {
        userService.updatePasswordByMobile(user);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/app/v1/user/login", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult login(@Validated(User.Login.class) @RequestBody User user) throws Exception {
        return ServiceResult.build().data(userService.login(user));
    }

    @RequestMapping(value = "/app/v1/user/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get4App(@RequestBody User user) throws BusinessException {
        User existent = userService.get(user);
        existent.setId(null);
        return ServiceResult.build().data(existent);
    }

    @RequestMapping(value = "/app/v1/user/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult update(@Validated(User.Update.class) @RequestBody User user) {
        userService.update(user);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/app/v1/user/password/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updatePassword(@Validated(User.Password.class) @RequestBody User user) throws Exception {
        userService.updatePassword(user);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/app/v1/user/vip/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getVip(@RequestBody User user) throws BusinessException {
        return ServiceResult.build().data(userService.getVip(user));
    }
}
