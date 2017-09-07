package com.xiaosuokeji.yilan.server.controller.goods;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.goods.Order;
import com.xiaosuokeji.yilan.server.service.intf.goods.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 订单Controller<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin/v1/order/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getOrder(@RequestBody Order order) throws BusinessException {
        return ServiceResult.build().data(orderService.get(order));
    }

    @RequestMapping(value = "/admin/v1/order/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listAndCount(@RequestBody Order order){
        return ServiceResult.build().data(orderService.listAndCount(order));
    }

//    @RequestMapping(value = "/app/v1/order/save", method = RequestMethod.POST)
//    @XSProxy
//    @XSAuth
//    public ServiceResult saveOrder(@Validated(Order.Save.class) @RequestBody Order order) throws Exception {
//        return ServiceResult.build().data(orderService.save(order));
//    }

    @RequestMapping(value = "/app/v1/order/save", method = {RequestMethod.POST, RequestMethod.GET})
    public void saveOrder(Order order, HttpServletResponse response) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(orderService.save(order));
    }

    @RequestMapping(value = "/app/v1/order/cancel", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult cancelOrder(@RequestBody Order order) {
        orderService.cancel(order);
        return ServiceResult.build();
    }
}
