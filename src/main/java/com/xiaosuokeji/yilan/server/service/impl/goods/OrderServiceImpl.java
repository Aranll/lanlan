package com.xiaosuokeji.yilan.server.service.impl.goods;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.framework.xsjframework.util.UuidUtil;
import com.xiaosuokeji.yilan.server.constant.goods.OrderConsts;
import com.xiaosuokeji.yilan.server.dao.goods.OrderDao;
import com.xiaosuokeji.yilan.server.manager.intf.PaymentService;
import com.xiaosuokeji.yilan.server.model.goods.Order;
import com.xiaosuokeji.yilan.server.service.intf.goods.GoodsService;
import com.xiaosuokeji.yilan.server.service.intf.goods.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 订单ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private GoodsService goodsService;

    @Override
    @Transactional
    public String save(Order order) throws Exception {
        order.setGoods(goodsService.get(order.getGoods()));
        order.setId(UuidUtil.generate());
        order.setFee(order.getGoods().getPrice());
        orderDao.save(order);
        return paymentService.pay(order);
    }

    @Override
    public void remove(Order order) {
        orderDao.remove(order);
    }

    @Override
    public void cancel(Order order) {
        orderDao.cancel(order);
    }

    @Override
    @Scheduled(cron = "0 0 * * * ?")
    public void cancelExpire() {
        //使用定时任务在每个整点取消超过有效期的订单
        Long st = System.currentTimeMillis();
        logger.info("执行定时任务：取消超过有效期的订单...");
        try {
            orderDao.cancelExpire(new Date());
            logger.info("执行定时任务：取消超过有效期的订单，成功！执行时间：" + String.valueOf(System.currentTimeMillis() - st) + "毫秒");
        } catch (Exception e) {
            logger.error("执行定时任务：取消超过有效期的订单，失败！");
        }
    }

    @Override
    public Order get(Order order) throws BusinessException {
        Order existent = orderDao.get(order);
        if (existent == null) throw new BusinessException(OrderConsts.ORDER_NOT_EXIST);
        return existent;
    }

    @Override
    public PageModel<Order> listAndCount(Order order) {
        return PageModel.build(orderDao.list(order), orderDao.count(order));
    }
}
