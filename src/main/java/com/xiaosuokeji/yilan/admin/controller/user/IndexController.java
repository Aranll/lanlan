package com.xiaosuokeji.yilan.admin.controller.user;

import com.xiaosuokeji.yilan.admin.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gustinlau on 04/08/2017.
 */
@Controller("adminUserIndexController")
@RequestMapping("/admin/user")
public class IndexController {

    @Security(pKey = "user", key = "")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String index(HttpServletRequest request){
        return "/admin/user/index";
    }
}
