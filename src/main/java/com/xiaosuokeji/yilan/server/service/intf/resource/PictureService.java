package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Picture;

/**
 * 图片Service<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface PictureService {

    /**
     * 保存图片
     * @param picture 必填：name，url，category.id，accessVipLevel
     */
    void save(Picture picture);

    /**
     * 保存多张图片
     * @param picture 必填：pictures[i].name，pictures[i].url，pictures[i].accessVipLevel，pictures[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(Picture picture) throws BusinessException;

    /**
     * 删除图片
     * @param picture 必填：id
     */
    void remove(Picture picture);

    /**
     * 删除图片分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新图片
     * @param picture 必填：id，可选更新字段：name，url，category.id，accessVipLevel
     */
    void update(Picture picture);

    /**
     * 获取单个图片
     * @param picture 必填：id
     * @return 图片，字段：所有，category.id，category.name
     * @throws BusinessException 图片不存在
     */
    Picture get(Picture picture) throws BusinessException;

    /**
     * 获取和统计多个图片<br/>
     * 支持排序和分页
     * @param picture 可选条件：id，name（支持模糊），accessVipLevel，category.id
     * @return 图片列表和数量，字段：所有，category.id，category.name
     */
     PageModel<Picture> listAndCount(Picture picture);

    /**
     * 获取和统计多个图片<br/>
     * 支持排序和分页
     * @param picture 可选条件：name（模糊），category.id
     * @return 图片列表和数量，字段：id，name，url，category.id
     */
     PageModel<Picture> listComboAndCount(Picture picture);
}
