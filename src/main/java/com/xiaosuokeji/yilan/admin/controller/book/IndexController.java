package com.xiaosuokeji.yilan.admin.controller.book;

import com.xiaosuokeji.yilan.admin.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/10.
 */
@Controller("adminBookIndexController")
@RequestMapping("/admin/book")
public class IndexController {
    @Security(pKey = "book",key = "")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        return "/admin/book/index";
    }
}


