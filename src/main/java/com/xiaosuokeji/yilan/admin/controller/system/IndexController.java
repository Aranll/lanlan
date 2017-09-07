package com.xiaosuokeji.yilan.admin.controller.system;

import com.xiaosuokeji.yilan.admin.annotation.Index;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统
 * Created by GustinLau on 2017-05-02.
 */
@Controller("adminSystemIndexController")
@RequestMapping("/admin/system")
public class IndexController {

    @RequestMapping(value = "",method = RequestMethod.GET)
    @Security(pKey = "system",key = "")
    @Index
    public String index(HttpServletRequest request){
        return "admin/system/index";
    }

}
