package com.xiaosuokeji.yilan.server.service.impl.goods;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.constant.goods.GoodsConsts;
import com.xiaosuokeji.yilan.server.dao.goods.GoodsDao;
import com.xiaosuokeji.yilan.server.model.goods.Goods;
import com.xiaosuokeji.yilan.server.service.intf.goods.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品ServiceImpl<br/>
 * Created by xuxiaowei on 2017/8/6.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    @Override
    public void save(Goods goods) throws BusinessException {
        Goods existent = new Goods();
        existent.setName(goods.getName());
        if (goodsDao.count(existent).compareTo(0L) > 0) {
            throw new BusinessException(GoodsConsts.GOODS_EXIST);
        }
        goodsDao.save(goods);
    }

    @Override
    public void remove(Goods goods) {
        goodsDao.remove(goods);
    }

    @Override
    public void update(Goods goods) throws BusinessException {
        if (goods.getName() != null) {
            Goods existent = new Goods();
            existent.setName(goods.getName());
            List<Goods> existents = goodsDao.list(existent);
            if (existents.size() > 0) {
                boolean isSelf = existents.get(0).getId().equals(goods.getId());
                if (!isSelf) {
                    throw new BusinessException(GoodsConsts.GOODS_EXIST);
                }
            }
        }
        goodsDao.update(goods);
    }

    @Override
    public Goods get(Goods goods) throws BusinessException {
        Goods existent = goodsDao.get(goods);
        if (existent == null) {
            throw new BusinessException(GoodsConsts.GOODS_NOT_EXIST);
        }
        return existent;
    }

    @Override
    public PageModel<Goods> listAndCount(Goods goods) {
        return PageModel.build(goodsDao.list(goods), goodsDao.count(goods));
    }

    @Override
    public List<Goods> listCombo(Goods goods) {
        return goodsDao.listCombo(goods);
    }
}