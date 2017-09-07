package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.App;

import java.util.List;

/**
 * 软件Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface AppDao {

    /**
     * 保存软件
     * @param app 必填：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int save(App app);

    /**
     * 删除软件
     * @param app 必填：id
     * @return 受影响行数
     */
    int remove(App app);

    /**
     * 更新软件
     * @param app 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int update(App app);

    /**
     * 获取单个软件
     * @param app 必填：id
     * @return 软件，字段：所有，category.id，category.name
     */
    App get(App app);

    /**
     * 获取多个软件<br/>
     * 支持排序和分页
     * @param app 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 软件列表，字段：所有，category.id，category.name
     */
    List<App> list(App app);

    /**
     * 获取多个软件<br/>
     * 支持排序和分页
     * @param app 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 软件列表，字段：id，name，qrCode，category.id
     */
    List<App> listCombo(App app);

    /**
     * 统计软件数量
     * @param app 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 软件数量
     */
    Long count(App app);
}
