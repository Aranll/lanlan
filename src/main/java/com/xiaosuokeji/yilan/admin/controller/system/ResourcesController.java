package com.xiaosuokeji.yilan.admin.controller.system;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecResource;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统-资源管理
 * Created by gustinlau on 05/06/2017.
 */
@Controller("adminSystemResourcesController")
@RequestMapping("/admin/system/security/resource")
public class ResourcesController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Pagination(items = "resources", api = API.SECURITY_RESOURCE_LIST, itemClass = SecResource.class)
    @Security(pKey = "system", key = "system_resource")
    public String index(Model model, HttpServletRequest request, SecResource resource) {
        model.addAttribute("id", resource.getId());
        model.addAttribute("pid", resource.getPid());
        model.addAttribute("key", resource.getKey());
        model.addAttribute("type", resource.getType());
        model.addAttribute("name", resource.getName());
        model.addAttribute("url", resource.getUrl());
        model.addAttribute("method", resource.getMethod());


        JSONObject resourceTreeResponse = WebUtils.doRawRequest(API.SECURITY_RESOURCE_TREE, new SecResource());
        if (resourceTreeResponse.getBoolean("status"))
            model.addAttribute("resourceTree",resourceTreeResponse.getJSONArray("data").toString());

        return "admin/system/security/resource";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(SecResource resource) {
        return WebUtils.doRawRequest(API.SECURITY_RESOURCE_CREATE, resource).toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(SecResource resource) {
        return WebUtils.doRawRequest(API.SECURITY_RESOURCE_UPDATE, resource).toString();
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public String find(SecResource resource) {
        return WebUtils.doRawRequest(API.SECURITY_RESOURCE_FIND, resource).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SecResource resource) {
        return WebUtils.doRawRequest(API.SECURITY_RESOURCE_DELETE, resource).toString();
    }
}
