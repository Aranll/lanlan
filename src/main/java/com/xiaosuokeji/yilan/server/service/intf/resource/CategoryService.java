package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;

import java.util.List;
import java.util.Map;

/**
 * 分类Service<br/>
 * Created by xuxiaowei on 2017/7/25.
 */
public interface CategoryService {

    /**
     * 保存分类
     * @param category 必填：type，name，status，hot，seq，parent.id
     * @throws BusinessException 分类已存在
     */
    void save(Category category) throws BusinessException;

    /**
     * 删除分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void remove(Category category) throws BusinessException;

    /**
     * 更新分类
     * @param category 必填：id，可选更新字段：name，status，hot，seq，parent.id
     * @throws BusinessException 分类已存在
     */
    void update(Category category) throws BusinessException;

    /**
     * 获取单个分类
     * @param category 必填：id
     * @return 分类，字段：所有，parent.id，parent.name，parent.status
     * @throws BusinessException 分类不存在
     */
    Category get(Category category) throws BusinessException;

    /**
     * 获取并统计多个分类<br/>
     * 支持排序和分页
     * @param category 可选条件：id，type，name（支持模糊），status，hot，parent.id
     * @return 分类列表和数量，字段：所有，parent.id，parent.name
     */
    PageModel<Category> listAndCount(Category category);

    /**
     * 获取多个分类<br/>
     * 支持排序和分页，默认排序seq DESC
     * @param category 可选条件：type，status，hot，parent.id
     * @return 分类列表，字段：id，name
     */
    List<Category> listCombo(Category category);

    /**
     * 获取多个分类，以树状结构组织<br/>
     * 支持排序，默认排序seq DESC
     * @param category 可选条件：type，status，hot
     * @return 分类列表，字段：id，name，seq，parent.id
     */
    List<Category> listTreeCombo(Category category);

    /**
     * 获取多个分类，以树状结构组织，每个节点持有父指针<br/>
     * 支持排序，默认排序seq DESC
     * @param category 可选条件：type，status，hot
     * @return 分类哈希表，字段：id，name，seq，parent，children
     */
    Map<Long, Category> listTree(Category category);

    /**
     * 以深度搜索方式遍历分类树
     * @param node 根结点
     * @return 分类列表，按深度搜索顺序排列
     */
    List<Category> deepSearch(Category node);

    /**
     * 以深度搜索方式遍历分类树
     * @param tree 分类树
     * @param node 根结点
     * @return 分类列表，按深度搜索顺序排列
     */
    List<Category> deepSearch(Map<Long, Category> tree, Category node);

    /**
     * 获取从根结点到指定节点的完整路径
     * @param node 结点
     * @return 分类列表，按从根结点到指定节点顺序排列
     */
    List<Category> getPath(Category node);

    /**
     * 获取从根结点到指定节点的完整路径
     * @param tree 分类树
     * @param node 结点
     * @return 分类列表，按从根结点到指定节点顺序排列
     */
    List<Category> getPath(Map<Long, Category> tree, Category node);

    /**
     * 清除没有子分类和子资源的结点
     * @param tree 分类树
     */
    void clearBlankNode(Map<Long, Category> tree);
}
