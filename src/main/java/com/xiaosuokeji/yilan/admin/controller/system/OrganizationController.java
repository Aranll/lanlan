package com.xiaosuokeji.yilan.admin.controller.system;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecOrganization;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统-组织管理
 * Created by gustinlau on 05/06/2017.
 */
@Controller("adminSystemOrgController")
@RequestMapping("/admin/system/security/organization")
public class OrganizationController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Pagination(items = "orgs", api = API.SECURITY_ORGANIZATION_LIST, itemClass = SecOrganization.class)
    @Security(pKey = "system", key = "system_org")
    public String index(Model model, HttpServletRequest request, SecOrganization organization) {
        model.addAttribute("id", organization.getId());
        model.addAttribute("pid", organization.getPid());
        model.addAttribute("name", organization.getName());
        model.addAttribute("status", organization.getStatus());


        JSONObject resourceTreeResponse = WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_TREE, new SecOrganization());
        if (resourceTreeResponse.getBoolean("status"))
            model.addAttribute("orgTree",resourceTreeResponse.getJSONArray("data").toString());

        return "admin/system/security/organization";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_CREATE, organization).toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_UPDATE, organization).toString();
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public String find(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_FIND, organization).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_DELETE, organization).toString();
    }

    
    @RequestMapping(value = "/role/pair", method = RequestMethod.POST)
    @ResponseBody
    public String rolePair(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_ROLE_PAIR, organization).toString();
    }

    @RequestMapping(value = "/role/authorize", method = RequestMethod.POST)
    @ResponseBody
    public String roleAuth(SecOrganization organization) {
        return WebUtils.doRawRequest(API.SECURITY_ORGANIZATION_ROLE_AUTHORIZE, organization).toString();
    }

}
