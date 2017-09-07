package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Picture;
import com.xiaosuokeji.yilan.server.model.resource.Website;

import java.util.List;

/**
 * 网站Service<br/>
 * Created by xuxiaowei on 2017/7/31.
 */
public interface WebsiteService {

    /**
     * 保存网站
     * @param website 必填：name，url，developer，category.id，hot，recommend
     * @throws BusinessException 网站已存在
     */
    void save(Website website) throws BusinessException;

    /**
     * 保存多个网站
     * @param website 必填：websites[i].name，websites[i].url，websites[i].developer，websites[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(Website website) throws BusinessException;

    /**
     * 删除网站
     * @param website 必填：id
     */
    void remove(Website website);

    /**
     * 删除网站分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新网站
     * @param website 必填：id，可选更新字段：name，url，developer，category.id，hot，recommend
     * @throws BusinessException 网站已存在
     */
    void update(Website website) throws BusinessException;

    /**
     * 获取单个网站
     * @param website 必填：id
     * @return 网站，字段：所有，category.id，category.name
     * @throws BusinessException 网站不存在
     */
    Website get(Website website) throws BusinessException;

    /**
     * 获取并统计多个网站<br/>
     * 支持排序和分页
     * @param website 可选条件：id，name（支持模糊），hot，recommend，category.id
     * @return 网站列表和数量，字段：所有，category.id，category.name
     */
    PageModel<Website> listAndCount(Website website);

    /**
     * 获取热门网站
     * @return 网站列表，字段：id，name，url，category.id
     */
    List<Website> listHot();

    /**
     * 获取首页推荐网站<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部网站<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个网站<br/>
     * 支持排序和分页
     * @param website 可选条件：category.id
     * @return 网站列表，字段：id，name，url，category.id
     */
    List<Website> listCombo(Website website);
}
