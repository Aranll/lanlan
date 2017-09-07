package com.xiaosuokeji.yilan.admin.controller.system;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.security.SecStaff;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统-员工管理
 * Created by gustinlau on 05/06/2017.
 */
@Controller("adminSystemStaffController")
@RequestMapping("/admin/system/security/staff")
public class StaffController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    @Pagination(items = "staffs", api = API.SECURITY_STAFF_LIST, itemClass = SecStaff.class)
    @Security(pKey = "system", key = "system_staff")
    public String index(Model model, HttpServletRequest request, SecStaff staff) {
        model.addAttribute("id", staff.getId());
        model.addAttribute("name", staff.getName());
        model.addAttribute("username", staff.getUsername());
        model.addAttribute("mobile", staff.getMobile());
        model.addAttribute("status", staff.getStatus());


        return "admin/system/security/staff";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_CREATE, staff).toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_UPDATE, staff).toString();
    }

    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public String find(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_FIND, staff).toString();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_DELETE, staff).toString();
    }


    
    @RequestMapping(value = "/role/pair", method = RequestMethod.POST)
    @ResponseBody
    public String rolePair(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_ROLE_PAIR, staff).toString();
    }

    @RequestMapping(value = "/role/authorize", method = RequestMethod.POST)
    @ResponseBody
    public String roleAuth(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_ROLE_AUTHORIZE, staff).toString();
    }

    @RequestMapping(value = "/org/tree", method = RequestMethod.POST)
    @ResponseBody
    public String orgTree(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_ORG_TREE, staff).toString();
    }

    @RequestMapping(value = "/org/join", method = RequestMethod.POST)
    @ResponseBody
    public String orgJoin(SecStaff staff) {
        return WebUtils.doRawRequest(API.SECURITY_STAFF_ORG_JOIN, staff).toString();
    }
}
