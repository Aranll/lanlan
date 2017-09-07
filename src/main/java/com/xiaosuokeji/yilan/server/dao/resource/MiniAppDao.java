package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.MiniApp;

import java.util.List;

/**
 * 小程序Dao<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface MiniAppDao {

    /**
     * 保存小程序
     * @param miniApp 必填：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int save(MiniApp miniApp);

    /**
     * 删除小程序
     * @param miniApp 必填：id
     * @return 受影响行数
     */
    int remove(MiniApp miniApp);

    /**
     * 更新小程序
     * @param miniApp 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @return 受影响行数
     */
    int update(MiniApp miniApp);

    /**
     * 获取单个小程序
     * @param miniApp 必填：id
     * @return 小程序，字段：所有，category.id，category.name
     */
    MiniApp get(MiniApp miniApp);

    /**
     * 获取多个小程序<br/>
     * 支持排序和分页
     * @param miniApp 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 小程序列表，字段：所有，category.id，category.name
     */
    List<MiniApp> list(MiniApp miniApp);

    /**
     * 获取多个小程序<br/>
     * 支持排序和分页
     * @param miniApp 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 小程序列表，字段：id，name，qrCode，category.id
     */
    List<MiniApp> listCombo(MiniApp miniApp);

    /**
     * 统计小程序数量
     * @param miniApp 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 小程序数量
     */
    Long count(MiniApp miniApp);
}
