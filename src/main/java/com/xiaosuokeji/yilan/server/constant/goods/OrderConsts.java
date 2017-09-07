package com.xiaosuokeji.yilan.server.constant.goods;

import com.xiaosuokeji.framework.xsjframework.model.StatusPair;

/**
 * 订单常量<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
public class OrderConsts {

    /**
     * 待支付
     */
    public static final Integer TO_BE_PAID = 0;

    /**
     * 已支付
     */
    public static final Integer SUCCESS = 1;

    /**
     * 支付失败
     */
    public static final Integer FAILURE = 2;

    /**
     * 取消支付
     */
    public static final Integer CANCEL = 3;

    public static final StatusPair ORDER_NOT_EXIST = StatusPair.build(1040, "订单不存在");
}
