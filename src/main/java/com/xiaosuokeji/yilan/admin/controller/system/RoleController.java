package com.xiaosuokeji.yilan.admin.controller.system;


import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecRole;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统-角色管理
 * Created by gustinlau on 05/06/2017.
 */
@Controller("adminSystemRoleController")
@RequestMapping("/admin/system/security/role")
public class RoleController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Pagination(items = "roles", api = API.SECURITY_ROLE_LIST, itemClass = SecRole.class)
    @Security(pKey = "system", key = "system_role")
    public String index(Model model, HttpServletRequest request, SecRole role) {
        model.addAttribute("id", role.getId());
        model.addAttribute("name", role.getName());
        model.addAttribute("status", role.getStatus());

        return "admin/system/security/role";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_CREATE, role).toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_UPDATE, role).toString();
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public String find(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_FIND, role).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_DELETE, role).toString();
    }


    @RequestMapping(value = "/res/tree", method = RequestMethod.POST)
    @ResponseBody
    public String resTree(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_RES_TREE, role).toString();
    }

    @RequestMapping(value = "/res/authorize", method = RequestMethod.POST)
    @ResponseBody
    public String auth(SecRole role) {
        return WebUtils.doRawRequest(API.SECURITY_ROLE_RES_AUTHORIZE, role).toString();
    }

}
