package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.MiniApp;

import java.util.List;

/**
 * 小程序Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface MiniAppService {

    /**
     * 保存小程序
     * @param miniApp 必填：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 小程序已存在
     */
    void save(MiniApp miniApp) throws BusinessException;

    /**
     * 保存多个小程序
     * @param miniApp 必填：miniApps[i].name，miniApps[i].qrCode，miniApps[i].developer，miniApps[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(MiniApp miniApp) throws BusinessException;

    /**
     * 删除小程序
     * @param miniApp 必填：id
     */
    void remove(MiniApp miniApp);

    /**
     * 删除小程序分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新小程序
     * @param miniApp 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 小程序已存在
     */
    void update(MiniApp miniApp) throws BusinessException;

    /**
     * 获取单个小程序
     * @param miniApp 必填：id
     * @return 小程序，字段：所有，category.id，category.name
     * @throws BusinessException 小程序不存在
     */
    MiniApp get(MiniApp miniApp) throws BusinessException;

    /**
     * 获取并统计多个小程序<br/>
     * 支持排序和分页
     * @param miniApp 可选条件：id，name（支持模糊），hot，recommend，category.id
     * @return 小程序列表和数量，字段：所有，category.id，category.name
     */
    PageModel<MiniApp> listAndCount(MiniApp miniApp);

    /**
     * 获取热门小程序
     * @return 小程序列表，字段：id，name，qrCode，category.id
     */
    List<MiniApp> listHot();

    /**
     * 获取首页推荐小程序<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部小程序<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个小程序<br/>
     * 支持排序和分页
     * @param miniApp 可选条件：category.id
     * @return 小程序列表，字段：id，name，url，category.id
     */
    List<MiniApp> listCombo(MiniApp miniApp);
}
