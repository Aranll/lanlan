package com.xiaosuokeji.yilan.server.controller.goods;

import com.xiaosuokeji.framework.xsjframework.annotation.XSProxy;
import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.ServiceResult;
import com.xiaosuokeji.yilan.server.annotation.XSAuth;
import com.xiaosuokeji.yilan.server.model.goods.Goods;
import com.xiaosuokeji.yilan.server.service.intf.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商品Controller<br/>
 * Created by xuxiaowei on 2017/8/6.
 */
@Controller
@RequestMapping(value = "/api")
@XSProxy
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/admin/v1/goods/save", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult saveGoods(@Validated(Goods.Save.class) @RequestBody Goods goods) throws BusinessException {
        goodsService.save(goods);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/goods/remove", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult removeGoods(@RequestBody Goods goods) {
        goodsService.remove(goods);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/goods/update", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult updateGoods(@Validated(Goods.Update.class) @RequestBody Goods goods) throws BusinessException {
        goodsService.update(goods);
        return ServiceResult.build();
    }

    @RequestMapping(value = "/admin/v1/goods/get", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult getGoods(@RequestBody Goods goods) throws BusinessException {
        return ServiceResult.build().data(goodsService.get(goods));
    }

    @RequestMapping(value = "/admin/v1/goods/list", method = RequestMethod.POST)
    @XSProxy
    @XSAuth
    public ServiceResult listGoods(@RequestBody Goods goods) {
        return ServiceResult.build().data(goodsService.listAndCount(goods));
    }

    @RequestMapping(value = "/app/v1/goods/combo", method = RequestMethod.POST)
    @XSProxy
    public ServiceResult listGoodsCombo(@RequestBody Goods goods) {
        return ServiceResult.build().data(goodsService.listCombo(goods));
    }
}
