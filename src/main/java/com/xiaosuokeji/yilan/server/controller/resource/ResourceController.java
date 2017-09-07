package com.xiaosuokeji.yilan.server.controller.resource;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.model.resource.Resource;
import com.xiaosuokeji.yilan.server.service.intf.resource.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 资源Controller<br/>
 * Created by xuxiaowei on 2017/8/4.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @RequestMapping(value = "/app/v1/resource/list", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult list(@RequestBody(required = false) Resource resource) {
        return ServiceResult.build().data(resourceService.list(resource));
    }
}
