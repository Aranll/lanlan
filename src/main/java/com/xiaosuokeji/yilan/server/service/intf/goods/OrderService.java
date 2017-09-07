package com.xiaosuokeji.yilan.server.service.intf.goods;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.goods.Order;

/**
 * 订单Service<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
public interface OrderService {

    /**
     * 保存订单
     * @param order 必填：userId，goods.id，channel，platform，returnUrl
     * @return 支付页面html代码
     * @throws Exception 商品不存在
     */
    String save(Order order) throws Exception;

    /**
     * 删除订单
     * @param order 必填：id
     */
    void remove(Order order);

    /**
     * 取消订单
     * @param order 必填：id
     */
    void cancel(Order order);

    /**
     * 取消超过有效期的订单
     * @return 受影响行数
     */
    void cancelExpire();

    /**
     * 获取单个订单
     * @param order 必填：id
     * @return 订单，字段：所有
     * @throws BusinessException 订单不存在
     */
    Order get(Order order) throws BusinessException;

    /**
     * 获取和统计多个订单<br/>
     * 支持排序和分页
     * @param order 可选条件：id，userId，mobile（模糊），status，channel，platform，dynamic.startTime，dynamic.endTime
     * @return 订单列表和数量，字段：所有
     */
    PageModel<Order> listAndCount(Order order);
}
