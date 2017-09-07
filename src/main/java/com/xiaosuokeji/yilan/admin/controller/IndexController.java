package com.xiaosuokeji.yilan.admin.controller;

import com.xiaosuokeji.yilan.admin.annotation.Index;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by GustinLau on 2017-05-11.
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
public class IndexController {

    @RequestMapping(value = {""},method = RequestMethod.GET)
    public String defaultIndex(HttpServletRequest request){
        return "redirect:/admin/index";
    }

    @RequestMapping(value = {"/index"},method = RequestMethod.GET)
    @Index
    public String index(HttpServletRequest request){
        return "admin/index";
    }
}
