package com.xiaosuokeji.yilan.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/28.
 */
@Controller("mobileIndexController")
@RequestMapping(value = "/mobile")
public class IndexController {
    @RequestMapping(value = {"", "/index"}, method = RequestMethod.GET)
    public String defaultIndex() {
        return "redirect:/mobile/website";
    }
}
