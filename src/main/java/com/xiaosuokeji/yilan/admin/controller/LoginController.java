package com.xiaosuokeji.yilan.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by gustinlau on 04/08/2017.
 */
@Controller("adminLoginController")
@RequestMapping("/admin")
public class LoginController {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {

        return "admin/login";
    }
}
