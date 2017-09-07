package com.xiaosuokeji.yilan.server.controller.system;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.system.Article;
import com.xiaosuokeji.yilan.server.model.system.SystemProperty;
import com.xiaosuokeji.yilan.server.service.intf.system.ArticleService;
import com.xiaosuokeji.yilan.server.service.intf.system.SystemPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 系统属性Controller<br/>
 * Created by xuxiaowei on 2017/8/14.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class SystemPropertyController {

    @Autowired
    private SystemPropertyService systemPropertyService;

    @RequestMapping(value = "/admin/v1/system/property/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult save(@Validated(SystemProperty.Save.class) @RequestBody SystemProperty systemProperty) {
        systemPropertyService.save(systemProperty);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/system/property/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult get(@RequestBody SystemProperty systemProperty) {
        return ServiceResult.build().data(systemPropertyService.get());
    }

    @RequestMapping(value = "/app/v1/system/property/get", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult get() {
        return ServiceResult.build().data(systemPropertyService.get());
    }
}
