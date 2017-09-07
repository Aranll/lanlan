package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Video;

import java.util.List;

/**
 * 视频Dao<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface VideoDao {

    /**
     * 保存视频
     * @param video 必填：name，author，origin，url，category.id，hot，recommend，accessVipLevel 选填：profile
     * @return 受影响行数
     */
    int save(Video video);

    /**
     * 删除视频
     * @param video 必填：id
     * @return 受影响行数
     */
    int remove(Video video);

    /**
     * 更新视频
     * @param video 必填：id，可选更新字段：name，author，origin，url，category.id，hot，recommend，accessVipLevel，profile
     * @return 受影响行数
     */
    int update(Video video);

    /**
     * 获取单个视频
     * @param video 必填：id
     * @return 视频，字段：所有，category.id，category.name
     */
    Video get(Video video);

    /**
     * 获取多个视频<br/>
     * 支持排序和分页
     * @param video 可选条件：id，name（支持模糊），hot，recommend，accessVipLevel，category.id，categories[i].id
     * @return 视频列表，字段：所有，category.id，category.name
     */
    List<Video> list(Video video);

    /**
     * 获取多个视频<br/>
     * 支持排序和分页
     * @param video 可选条件：name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 视频列表，字段：id，name，url，category.id
     */
    List<Video> listCombo(Video video);

    /**
     * 统计视频数量
     * @param video 可选条件：id，name（支持模糊），hot，recommend，category.id，categories[i].id
     * @return 视频数量
     */
    Long count(Video video);
}
