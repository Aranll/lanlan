package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Picture;

import java.util.List;

/**
 * 图片Dao<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface PictureDao {

    /**
     * 保存图片
     * @param picture 必填：name，url，category.id，accessVipLevel
     * @return 受影响行数
     */
    int save(Picture picture);

    /**
     * 删除图片
     * @param picture 必填：id
     * @return 受影响行数
     */
    int remove(Picture picture);

    /**
     * 更新图片
     * @param picture 必填：id，可选更新字段：name，url，category.id，accessVipLevel
     * @return 受影响行数
     */
    int update(Picture picture);

    /**
     * 获取单个图片
     * @param picture 必填：id
     * @return 图片，字段：所有，category.id，category.name
     */
    Picture get(Picture picture);

    /**
     * 获取多个图片<br/>
     * 支持排序和分页
     * @param picture 可选条件：id，name（支持模糊），accessVipLevel，category.id，categories[i].id
     * @return 图片列表，字段：所有，category.id，category.name
     */
    List<Picture> list(Picture picture);

    /**
     * 获取多个图片<br/>
     * 支持排序和分页
     * @param picture 可选条件：name（模糊），category.id，categories[i].id
     * @return 图片列表，字段：id，name，url
     */
    List<Picture> listCombo(Picture picture);

    /**
     * 统计图片数量
     * @param picture 可选条件：id，name（支持模糊），accessVipLevel，category.id，categories[i].id
     * @return 图片数量
     */
    Long count(Picture picture);
}
