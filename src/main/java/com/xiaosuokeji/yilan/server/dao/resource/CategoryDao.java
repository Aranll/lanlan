package com.xiaosuokeji.yilan.server.dao.resource;

import com.xiaosuokeji.yilan.server.model.resource.Category;

import java.util.List;

/**
 * 分类Dao<br/>
 * Created by xuxiaowei on 2017/7/25.
 */
public interface CategoryDao {

    /**
     * 保存分类
     * @param category 必填：type，name，status，hot，seq，parent.id
     * @return 受影响行数
     */
    int save(Category category);

    /**
     * 删除分类
     * @param category 必填：id
     * @return 受影响行数
     */
    int remove(Category category);

    /**
     * 更新分类
     * @param category 必填：id，可选更新字段：name，status，hot，seq，parent.id
     * @return 受影响行数
     */
    int update(Category category);

    /**
     * 更新分类状态
     * @param category 可选更新字段：status，可选条件：categories[i].id
     * @return 受影响行数
     */
    int updateStatus(Category category);

    /**
     * 更新分类热门
     * @param category 可选更新字段：hot，可选条件：categories[i].id
     * @return 受影响行数
     */
    int updateHot(Category category);

    /**
     * 获取单个分类
     * @param category 必填：id
     * @return 分类，字段：所有，父级id，name，status
     */
    Category get(Category category);

    /**
     * 获取多个分类<br/>
     * 支持排序和分页
     * @param category 可选条件：id，type，name（支持模糊），status，hot，parent.id
     * @return 区域列表，字段：所有，父级id，name
     */
    List<Category> list(Category category);

    /**
     * 获取多个分类<br/>
     * 支持排序和分页
     * @param category 可选条件：type，status，hot，parent.id
     * @return 分类列表，字段：id，name
     */
    List<Category> listCombo(Category category);

    /**
     * 获取多个分类<br/>
     * 支持排序
     * @param category 可选条件：type，status，hot
     * @return 分类列表，字段：id，name，seq，parent.id
     */
    List<Category> listTree(Category category);

    /**
     * 统计分类数量
     * @param category 可选条件：id，type，name（支持模糊），status，hot，parent.id
     * @return 分类数量
     */
    Long count(Category category);
}
