package com.xiaosuokeji.yilan.admin.controller.system;

/**
 * Created by Aranl_lin on 2017/8/22.
 */

import com.xiaosuokeji.yilan.admin.annotation.Pagination;
import com.xiaosuokeji.yilan.admin.annotation.Security;
import com.xiaosuokeji.yilan.admin.enumeration.API;
import com.xiaosuokeji.yilan.admin.model.system.Goods;
import com.xiaosuokeji.yilan.admin.oss.model.SecurityToken;
import com.xiaosuokeji.yilan.admin.oss.server.SecurityTokenServer;
import com.xiaosuokeji.yilan.admin.util.CodingUtils;
import com.xiaosuokeji.yilan.admin.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Aranl_lin on 2017/8/10.
 */
@Controller("adminGoodsManageController")
@RequestMapping("/admin/system/security")
public class GoodsController {
    @Autowired
    private SecurityTokenServer securityTokenServer;
    @Security(pKey = "system", key = "system_goods")
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @Pagination(items = "goods",api = API.GOODS_LIST,itemClass = Goods.class)
    public String index(Model model, HttpServletRequest request, Goods goods) {
        model.addAttribute("id",goods.getId());
        model.addAttribute("name",goods.getName());
        model.addAttribute("dynamic",goods.getDynamic());
        model.addAttribute("status",goods.getStatus());

        return "admin/system/security/goods";
    }



    @RequestMapping(value = "/goods/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(Goods goods) {
        return WebUtils.doRawRequest(API.GOODS_SAVE, goods).toString();
    }

    @RequestMapping(value = "/goods/remove",method = RequestMethod.POST)
    @ResponseBody
    public String remove(Goods goods) {
        return WebUtils.doRawRequest(API.GOODS_REMOVE,goods).toString();
    }

    @RequestMapping(value = "/goods/update",method = RequestMethod.POST)
    @ResponseBody
    public String update(Goods goods){
        return WebUtils.doRawRequest(API.GOODS_UPDATE,goods).toString();
    }

    @RequestMapping(value = "/goods/get",method = RequestMethod.POST)
    @ResponseBody
    public String get(Goods goods){
        return WebUtils.doRawRequest(API.GOODS_GET,goods).toString();
    }
}
