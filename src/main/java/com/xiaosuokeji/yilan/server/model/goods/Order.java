package com.xiaosuokeji.yilan.server.model.goods;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import com.xiaosuokeji.yilan.server.model.user.User;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单<br/>
 * Created by xuxiaowei on 2017/8/11.
 */
public class Order extends BaseModel {

    /**
     * id
     */
    private String id;

    /**
     * 用户
     */
    private User user;

    /**
     * 商品
     */
    @NotNull(message = "商品不能为空", groups = Save.class)
    @Valid
    private Goods goods;

    /**
     * 金额，以元为单位
     */
    private BigDecimal fee;

    /**
     * 渠道，0微信，1支付宝，2银联
     */
    @NotNull(message = "渠道不能为空", groups = Save.class)
    @AutoDesc("orderChannel")
    private Integer channel;

    /**
     * 平台，0网页，1移动网页
     */
    @NotNull(message = "平台不能为空", groups = Save.class)
    @AutoDesc("orderPlatform")
    private Integer platform;

    /**
     * 状态，0待支付，1已支付，2支付失败，3取消支付
     */
    @AutoDesc("orderStatus")
    private Integer status;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 渠道回调时间
     */
    private Date callbackTime;

    /**
     * 失败原因
     */
    private String failureReason;

    /**
     * 支付跳转链接
     */
    @NotNull(message = "跳转链接不能为空", groups = Save.class)
    @Length(min = 1, max = 200, message = "跳转链接长度为1-200个字符", groups = Save.class)
    private String returnUrl;

    public Order() {}

    public Order(String id) {
        this.id = id;
    }

    public interface Save {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCallbackTime() {
        return callbackTime;
    }

    public void setCallbackTime(Date callbackTime) {
        this.callbackTime = callbackTime;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
