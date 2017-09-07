package com.xiaosuokeji.yilan.server.service.intf.goods;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.goods.Goods;

import java.util.List;

/**
 * 商品Service<br/>
 * Created by xuxiaowei on 2017/8/6.
 */
public interface GoodsService {

    /**
     * 保存商品
     * @param goods 必填：name，price，status，seq 选填：desc
     * @throws BusinessException 商品已存在
     */
    void save(Goods goods) throws BusinessException;

    /**
     * 删除商品
     * @param goods 必填：id
     */
    void remove(Goods goods);

    /**
     * 更新商品
     * @param goods 必填：id，可选更新字段：name，price，status，desc，seq
     * @throws BusinessException 商品已存在
     */
    void update(Goods goods) throws BusinessException;

    /**
     * 获取单个商品
     * @param goods 必填：id
     * @return 商品，字段：所有
     * @throws BusinessException 商品不存在
     */
    Goods get(Goods goods) throws BusinessException;

    /**
     * 获取和统计多个商品<br/>
     * 支持排序和分页
     * @param goods 可选条件：id，name（支持模糊），status
     * @return 商品列表和总数，字段：所有
     */
    PageModel<Goods> listAndCount(Goods goods);

    /**
     * 获取多个商品<br/>
     * 支持排序和分页
     * @param goods 可选条件：status
     * @return 商品列表，字段：id，name，price
     */
    List<Goods> listCombo(Goods goods);
}
