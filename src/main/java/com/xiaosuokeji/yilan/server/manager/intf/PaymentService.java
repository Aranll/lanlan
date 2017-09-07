package com.xiaosuokeji.yilan.server.manager.intf;

import com.xiaosuokeji.yilan.server.model.goods.Order;

/**
 * 支付Service<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
public interface PaymentService {

    /**
     * 支付
     * @param order 必填：id，fee，channel，platform，returnUrl，goods.desc
     * @return 支付页面html代码
     * @throws Exception
     */
    String pay(Order order) throws Exception;

    /**
     * 渠道回调
     * @param message 报文
     * @return 结果标识
     * @throws Exception
     */
    String callback(String message);
}
