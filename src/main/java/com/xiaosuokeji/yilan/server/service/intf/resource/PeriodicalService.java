package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;

import java.util.List;

/**
 * 期刊Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface PeriodicalService {

    /**
     * 保存期刊
     * @param periodical 必填：name，url，developer，category.id，hot，recommend
     * @throws BusinessException 期刊已存在
     */
    void save(Periodical periodical) throws BusinessException;

    /**
     * 保存多个期刊
     * @param periodical 必填：periodicals[i].name，periodicals[i].url，periodicals[i].developer，
     *                   periodicals[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(Periodical periodical) throws BusinessException;

    /**
     * 删除期刊
     * @param periodical 必填：id
     */
    void remove(Periodical periodical);

    /**
     * 删除期刊分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新期刊
     * @param periodical 必填：id，可选更新字段：name，url，developer，category.id，hot，recommend
     * @throws BusinessException 期刊已存在
     */
    void update(Periodical periodical) throws BusinessException;

    /**
     * 获取单个期刊
     * @param periodical 必填：id
     * @return 期刊，字段：所有，category.id，category.name
     * @throws BusinessException 期刊不存在
     */
    Periodical get(Periodical periodical) throws BusinessException;

    /**
     * 获取并统计多个期刊<br/>
     * 支持排序和分页
     * @param periodical 可选条件：id，name（支持模糊），hot，recommend，category.id
     * @return 期刊列表和数量，字段：所有，category.id，category.name
     */
    PageModel<Periodical> listAndCount(Periodical periodical);

    /**
     * 获取热门期刊
     * @return 期刊列表，字段：id，name，url，category.id
     */
    List<Periodical> listHot();

    /**
     * 获取首页推荐期刊<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部期刊<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个期刊<br/>
     * 支持排序和分页
     * @param periodical 可选条件：category.id
     * @return 期刊列表，字段：id，name，url，category.id
     */
    List<Periodical> listCombo(Periodical periodical);
}
