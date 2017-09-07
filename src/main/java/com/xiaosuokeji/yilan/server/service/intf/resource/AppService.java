package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.App;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Website;

import java.util.List;

/**
 * 软件Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface AppService {

    /**
     * 保存软件
     * @param app 必填：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 软件已存在
     */
    void save(App app) throws BusinessException;

    /**
     * 保存多个软件
     * @param app 必填：apps[i].name，apps[i].qrCode，apps[i].developer，apps[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(App app) throws BusinessException;

    /**
     * 删除软件
     * @param app 必填：id
     */
    void remove(App app);

    /**
     * 删除软件分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新软件
     * @param app 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 软件已存在
     */
    void update(App app) throws BusinessException;

    /**
     * 获取单个软件
     * @param app 必填：id
     * @return 软件，字段：所有，category.id，category.name
     * @throws BusinessException 软件不存在
     */
    App get(App app) throws BusinessException;

    /**
     * 获取并统计多个软件<br/>
     * 支持排序和分页
     * @param app 可选条件：id，name（支持模糊），hot，recommend，category.id
     * @return 软件列表和数量，字段：所有，category.id，category.name
     */
    PageModel<App> listAndCount(App app);

    /**
     * 获取热门软件
     * @return 软件列表，字段：id，name，qrCode，category.id
     */
    List<App> listHot();

    /**
     * 获取首页推荐软件<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部软件<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个软件<br/>
     * 支持排序和分页
     * @param app 可选条件：category.id
     * @return 网站列表，字段：id，name，url，category.id
     */
    List<App> listCombo(App app);
}
