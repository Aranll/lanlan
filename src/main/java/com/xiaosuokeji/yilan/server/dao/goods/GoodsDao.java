package com.xiaosuokeji.yilan.server.dao.goods;

import com.xiaosuokeji.yilan.server.model.goods.Goods;

import java.util.List;

/**
 * 商品Dao<br/>
 * Created by xuxiaowei on 2017/8/6.
 */
public interface GoodsDao {

    /**
     * 保存商品
     * @param goods 必填：name，price，duration，status，seq 选填：desc
     * @return 受影响行数
     */
    int save(Goods goods);

    /**
     * 删除商品
     * @param goods 必填：id
     * @return 受影响行数
     */
    int remove(Goods goods);

    /**
     * 更新商品
     * @param goods 必填：id，可选更新字段：name，price，duration，status，desc，seq
     * @return 受影响行数
     */
    int update(Goods goods);

    /**
     * 获取单个商品
     * @param goods 必填：id
     * @return 商品，字段：所有
     */
    Goods get(Goods goods);

    /**
     * 获取多个商品<br/>
     * 支持排序和分页
     * @param goods 可选条件：id，name（支持模糊），status
     * @return 商品列表，字段：所有
     */
    List<Goods> list(Goods goods);

    /**
     * 获取多个商品<br/>
     * 支持排序和分页
     * @param goods 可选条件：status
     * @return 商品列表，字段：id，name，price
     */
    List<Goods> listCombo(Goods goods);

    /**
     * 统计商品数量
     * @param goods 可选条件：name（支持模糊），status
     * @return 商品数量
     */
    Long count(Goods goods);
}
