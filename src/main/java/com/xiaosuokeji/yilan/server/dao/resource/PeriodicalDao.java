package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Periodical;

import java.util.List;

/**
 * 期刊Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface PeriodicalDao {

    /**
     * 保存期刊
     * @param periodical 必填：name，url，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int save(Periodical periodical);

    /**
     * 删除期刊
     * @param periodical 必填：id
     * @return 受影响行数
     */
    int remove(Periodical periodical);

    /**
     * 更新期刊
     * @param periodical 必填：id，可选更新字段：name，url，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int update(Periodical periodical);

    /**
     * 获取单个期刊
     * @param periodical 必填：id
     * @return 期刊，字段：所有，category.id，category.name
     */
    Periodical get(Periodical periodical);

    /**
     * 获取多个期刊<br/>
     * 支持排序和分页
     * @param periodical 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 期刊列表，字段：所有，category.id，category.name
     */
    List<Periodical> list(Periodical periodical);

    /**
     * 获取多个期刊<br/>
     * 支持排序和分页
     * @param periodical 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 期刊列表，字段：id，name，url，category.id
     */
    List<Periodical> listCombo(Periodical periodical);

    /**
     * 统计期刊数量
     * @param periodical 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 期刊数量
     */
    Long count(Periodical periodical);
}
