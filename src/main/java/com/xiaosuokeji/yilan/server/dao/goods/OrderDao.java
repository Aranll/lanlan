package com.xiaosuokeji.yilan.server.dao.goods;

import com.xiaosuokeji.yilan.server.model.goods.Order;

import java.util.Date;
import java.util.List;

/**
 * 订单Dao<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
public interface OrderDao {

    /**
     * 保存订单
     * @param order 必填：id，userId，goods.id，fee
     * @return 受影响行数
     */
    int save(Order order);

    /**
     * 删除订单
     * @param order 必填：id
     * @return 受影响行数
     */
    int remove(Order order);

    /**
     * 通过支付回调更新订单，仅对状态为待支付的订单有效
     * @param order 必填：id，可选更新字段：channel，platform，status，callbackTime，failureReason
     * @return 受影响行数
     */
    int payCallback(Order order);

    /**
     * 取消订单，仅对状态为待支付的订单有效
     * @param order 必填：id，可选更新字段：status
     * @return 受影响行数
     */
    int cancel(Order order);

    /**
     * 取消超过有效期的订单
     * @param expireTime 超时时间
     * @return 受影响行数
     */
    int cancelExpire(Date expireTime);

    /**
     * 获取单个订单
     * @param order 必填：id
     * @return 订单，字段：所有
     */
    Order get(Order order);

    /**
     * 获取多个订单<br/>
     * 支持排序和分页
     * @param order 可选条件：id，userId，mobile（模糊），status，channel，platform，dynamic.startTime，dynamic.endTime
     * @return 订单列表，字段：所有
     */
    List<Order> list(Order order);

    /**
     * 统计订单数量
     * @param order 可选条件：id，userId，mobile（模糊），status，channel，platform，dynamic.startTime，dynamic.endTime
     * @return 订单数量
     */
    Long count(Order order);
}
