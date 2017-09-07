package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Website;

import java.util.List;

/**
 * 网站Dao<br/>
 * Created by xuxiaowei on 2017/7/31.
 */
public interface WebsiteDao {

    /**
     * 保存网站
     * @param website 必填：name，url，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int save(Website website);

    /**
     * 删除网站
     * @param website 必填：id
     * @return 受影响行数
     */
    int remove(Website website);

    /**
     * 更新网站
     * @param website 必填：id，可选更新字段：name，url，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int update(Website website);

    /**
     * 获取单个网站
     * @param website 必填：id
     * @return 网站，字段：所有，category.id，category.name
     */
    Website get(Website website);

    /**
     * 获取多个网站<br/>
     * 支持排序和分页
     * @param website 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 网站列表，字段：所有，category.id，category.name
     */
    List<Website> list(Website website);

    /**
     * 获取多个网站<br/>
     * 支持排序和分页
     * @param website 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 网站列表，字段：id，name，url，category.id
     */
    List<Website> listCombo(Website website);

    /**
     * 统计网站数量
     * @param website 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 网站数量
     */
    Long count(Website website);
}
