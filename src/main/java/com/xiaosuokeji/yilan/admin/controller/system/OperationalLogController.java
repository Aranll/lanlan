package com.xiaosuokeji.yilan.admin.controller.system;

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.system.OperationLog;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Aranl_lin on 2017/8/16.
 */
@Controller("adminOperationalLogController")
@RequestMapping(value = "/admin/system/security")
public class OperationalLogController {
    @RequestMapping(value = "/operationLog",method = RequestMethod.GET)
    @Pagination(items = "operationLogs",api= API.SECURITY_OPERATIONLOG_LIST, itemClass = OperationLog.class)
    public String index(Model model, HttpServletRequest request, OperationLog operationLog){
        model.addAttribute("operationLog",operationLog);
        return "admin/system/security/operationLog";
    }
}
