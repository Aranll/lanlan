package com.xiaosuokeji.yilan.server.manager.impl;

import cn.beecloud.BCCache;
import cn.beecloud.BCEumeration;
import cn.beecloud.BCPay;
import cn.beecloud.BeeCloud;
import cn.beecloud.bean.BCOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.xiaosuokeji.framework.xsjframework.json.JsonUtil;
import com.xiaosuokeji.framework.xsjframework.util.Md5Util;
import com.xiaosuokeji.yilan.server.constant.goods.OrderConsts;
import com.xiaosuokeji.yilan.server.constant.goods.PaymentConsts;
import com.xiaosuokeji.yilan.server.dao.goods.OrderDao;
import com.xiaosuokeji.yilan.server.manager.intf.PaymentService;
import com.xiaosuokeji.yilan.server.model.goods.Order;
import com.xiaosuokeji.yilan.server.service.intf.goods.OrderService;
import com.xiaosuokeji.yilan.server.service.intf.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Beecloud支付ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/11.<br/>
 * Beecloud支付平台流控规则<br/>
 * 接口调用次数5000/月，并发数5次/秒
 */
@Service
public class BeecloudServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(BeecloudServiceImpl.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserService userService;

    @Value("${bee.appId}")
    private String appId;

    @Value("${bee.testSecret}")
    private String testSecret;

    @Value("${bee.appSecret}")
    private String appSecret;

    @Value("${bee.masterSecret}")
    private String masterSecret;

    @Value("${bee.callbackUrl}")
    private String callbackUrl;

    @Value("${bee.test}")
    private Boolean test;

    @PostConstruct
    public void init() {
        BeeCloud.registerApp(appId, testSecret, appSecret, masterSecret);
        BeeCloud.setSandbox(test);
    }

    @Override
    public String pay(Order order) throws Exception {
        Integer[] channel = new Integer[2];
        channel[0] = order.getPlatform();
        channel[1] = order.getChannel();
        BCOrder bcOrder = new BCOrder(convertChannel(channel), convertFee(order.getFee()), order.getId(),
                order.getGoods().getDesc());
        bcOrder.setBillTimeout(360);
        bcOrder.setReturnUrl(order.getReturnUrl());
        bcOrder.setNotifyUrl(callbackUrl);
        bcOrder = BCPay.startBCPay(bcOrder);
        return bcOrder.getHtml();
    }

    @Override
    public String callback(String message) {
        try {
            JsonNode jsonNode = JsonUtil.toJsonNode(message);
            String signature = jsonNode.get("signature").asText();
            String transactionId = jsonNode.get("transaction_id").asText();
            String transactionType = jsonNode.get("transaction_type").asText();
            String channelType = jsonNode.get("channel_type").asText();
            String subChannelType = jsonNode.get("sub_channel_type").asText();
            Integer transactionFee = jsonNode.get("transaction_fee").asInt();

            Order order = new Order(transactionId);
            Integer[] channel = convertChannel(subChannelType);
            order.setPlatform(channel[0]);
            order.setChannel(channel[1]);
            order.setCallbackTime(new Date());

            StringBuffer mySign = new StringBuffer();
            mySign.append(BCCache.getAppID()).append(transactionId).append(transactionType).append(channelType)
                    .append(transactionFee).append(masterSecret);
            boolean status = verifySign(mySign.toString(), signature);
            if (status) {
                Order existent = orderService.get(new Order(transactionId));
                if (convertFee(existent.getFee()).equals(transactionFee)) {
                    order.setStatus(OrderConsts.SUCCESS);
                    int rows = orderDao.payCallback(order);
                    if (rows > 0) {
                        userService.buyVip(existent.getUser(), existent.getGoods());
                    }
                    return "success";
                }
                else {
                    order.setStatus(OrderConsts.FAILURE);
                    order.setFailureReason("Beecloud支付回调金额与订单金额不一致！");
                }
            } else {
                order.setStatus(OrderConsts.FAILURE);
                order.setFailureReason("Beecloud支付签名校验失败！");
            }
            orderDao.payCallback(order);
            return "fail";
        } catch (Exception e) {
            logger.error("error : ", e);
            return "fail";
        }
    }

    private boolean verifySign(String mySign, String signature) throws UnsupportedEncodingException {
        if (Md5Util.encode(mySign).equals(signature)) return true;
        else return false;
    }

    /**
     * 将金额单位由元转化为分
     * @param fee 金额，以元为单位
     * @return 金额，以分为单位
     */
    private Integer convertFee(BigDecimal fee) {
        return Integer.valueOf(String.valueOf(Math.round(fee.doubleValue() * 100)));
    }

    /**
     * 将渠道特有类型标识符转化为程序中定义的平台和渠道标志数组
     * @param channelSign 渠道标识符
     * @return 平台和渠道标志数组，下标0表示平台，下标1表示渠道
     */
    private Integer[] convertChannel(String channelSign) {
        Integer[] channel = new Integer[2];
        if ("WX_NATIVE".equals(channelSign)) {
            channel[0] = 0;
            channel[1] = 0;
        }
        else if ("WX_JSAPI".equals(channelSign)) {
            channel[0] = 1;
            channel[1] = 0;
        }
        else if ("ALI_WEB".equals(channelSign)) {
            channel[0] = 0;
            channel[1] = 1;
        }
        else if ("ALI_WAP".equals(channelSign)) {
            channel[0] = 1;
            channel[1] = 1;
        }
        else if ("UN_WEB".equals(channelSign)) {
            channel[0] = 0;
            channel[1] = 2;
        }
        else {
            channel[0] = 1;
            channel[1] = 2;
        }
        return channel;
    }

    /**
     * 将程序中定义的平台和渠道标志数组转化为渠道特有类型标识符
     * @param channel 平台和渠道标志数组，下标0表示平台，下标1表示渠道
     * @return 渠道标识符
     */
    private BCEumeration.PAY_CHANNEL convertChannel(Integer[] channel) {
        BCEumeration.PAY_CHANNEL channelSign;
        if (PaymentConsts.WX.equals(channel[1])) {
            if (PaymentConsts.WEB.equals(channel[0])) {
                channelSign = BCEumeration.PAY_CHANNEL.WX_NATIVE;
            }
            else {
                channelSign = BCEumeration.PAY_CHANNEL.WX_JSAPI;
            }
        }
        else if (PaymentConsts.ALI.equals(channel[1])) {
            if (PaymentConsts.WEB.equals(channel[0])) {
                channelSign = BCEumeration.PAY_CHANNEL.ALI_WEB;
            }
            else {
                channelSign = BCEumeration.PAY_CHANNEL.ALI_WAP;
            }
        }
        else {
            if (PaymentConsts.WEB.equals(channel[1])) {
                channelSign = BCEumeration.PAY_CHANNEL.UN_WEB;
            }
            else {
                channelSign = BCEumeration.PAY_CHANNEL.UN_WAP;
            }
        }
        return channelSign;
    }
}
