package com.xiaosuokeji.yilan.server.model.goods;

import com.xiaosuokeji.framework.xsjframework.annotation.AutoDesc;
import com.xiaosuokeji.yilan.server.model.base.BaseModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 商品<br/>
 * Created by xuxiaowei on 2017/8/6.
 */
public class Goods extends BaseModel {

    /**
     * id
     */
    @NotNull(message = "商品id不能为空", groups = Order.Save.class)
    private Long id;

    /**
     * 名称
     */
    @NotNull(message = "名称不能为空", groups = Category.Save.class)
    @Length(min = 1, max = 20, message = "名称长度为1-20个字符", groups = {Save.class, Update.class})
    private String name;

    /**
     * 价格
     */
    @DecimalMin(value = "0.01", message = "价格范围为0.01-9999.99，至多2位小数", groups = {Save.class, Update.class})
    @Digits(integer = 4, fraction = 2, message = "价格范围为0.01-9999.99，至多2位小数", groups = {Save.class, Update.class})
    private BigDecimal price;

    /**
     * 时长，以天为单位
     */
    @NotNull(message = "时长不能为空", groups = Save.class)
    @Range(min = 1, max = 3650, message = "时长范围为1-3650天", groups = {Save.class, Update.class})
    private Integer duration;

    /**
     * 状态，0下架，1上架
     */
    @NotNull(message = "状态不能为空", groups = Save.class)
    @AutoDesc("goodsStatus")
    private Integer status;

    /**
     * 描述
     */
    @Length(max = 50, message = "描述长度为1-50个字符", groups = {Save.class, Update.class})
    private String desc;

    /**
     * 顺序，越大优先级越高
     */
    @NotNull(message = "顺序不能为空", groups = Save.class)
    @Range(message = "顺序范围为非负整数", groups = {Save.class, Update.class})
    private Long seq;

    public Goods() {}

    public Goods(Long id) {
        this.id = id;
    }

    public interface Save{}

    public interface Update{}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
