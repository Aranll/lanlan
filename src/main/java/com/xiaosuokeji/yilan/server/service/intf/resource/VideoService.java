package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;
import com.xiaosuokeji.yilan.server.model.resource.Video;

import java.util.List;

/**
 * 视频Service<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface VideoService {

    /**
     * 保存视频
     * @param video 必填：name，author，origin，url，category.id，hot，recommend，accessVipLevel 选填：profile
     * @throws BusinessException 视频已存在
     */
    void save(Video video) throws BusinessException;

    /**
     * 保存多个视频
     * @param video 必填：videos[i].name，videos[i].author，videos[i].origin，videos[i].url，videos[i].accessVipLevel，
     *              videos[i].category.id，选填：videos[i].profile
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(Video video) throws BusinessException;

    /**
     * 删除视频
     * @param video 必填：id
     */
    void remove(Video video);

    /**
     * 删除视频分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新视频
     * @param video 必填：id，可选更新字段：name，author，origin，url，category.id，hot，recommend，accessVipLevel，profile
     * @throws BusinessException 视频已存在
     */
    void update(Video video) throws BusinessException;

    /**
     * 获取单个视频
     * @param video 必填：id
     * @return 视频，字段：所有，category.id，category.name
     * @throws BusinessException 视频不存在
     */
    Video get(Video video) throws BusinessException;

    /**
     * 获取并统计多个视频<br/>
     * 支持排序和分页
     * @param video 可选条件：id，name（支持模糊），hot，recommend，accessVipLevel，category.id
     * @return 视频列表和数量，字段：所有，category.id，category.name
     */
    PageModel<Video> listAndCount(Video video);

    /**
     * 获取热门视频
     * @return 视频列表，字段：id，name，url，category.id
     */
    List<Video> listHot();

    /**
     * 获取首页推荐视频<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部视频<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个视频<br/>
     * 支持排序和分页
     * @param video 可选条件：category.id
     * @return 视频列表，字段：id，name，url，category.id
     */
    List<Video> listCombo(Video video);
}
