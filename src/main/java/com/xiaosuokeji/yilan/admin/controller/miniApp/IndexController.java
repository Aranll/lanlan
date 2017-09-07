package com.xiaosuokeji.yilan.admin.controller.miniApp;

import com.xiaosuokeji.yilan.admin.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
@Controller("adminMiniAppIndexController")
@RequestMapping("/admin/miniApp")
public class IndexController {

    @Security(pKey = "miniApp", key = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "/admin/miniapp/index";
    }
}


