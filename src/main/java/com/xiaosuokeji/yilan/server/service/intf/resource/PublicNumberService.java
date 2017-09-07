package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Category;
import com.xiaosuokeji.yilan.server.model.resource.Periodical;
import com.xiaosuokeji.yilan.server.model.resource.PublicNumber;

import java.util.List;

/**
 * 公众号Service<br/>
 * Created by xuxiaowei on 2017/8/1.
 */
public interface PublicNumberService {

    /**
     * 保存公众号
     * @param publicNumber 必填：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 公众号已存在
     */
    void save(PublicNumber publicNumber) throws BusinessException;

    /**
     * 保存多个公众号
     * @param publicNumber 必填：publicNumbers[i].name，publicNumbers[i].qrCode，publicNumbers[i].developer，
     *                     publicNumbers[i].category.id
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(PublicNumber publicNumber) throws BusinessException;

    /**
     * 删除公众号
     * @param publicNumber 必填：id
     */
    void remove(PublicNumber publicNumber);

    /**
     * 删除公众号分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新公众号
     * @param publicNumber 必填：id，可选更新字段：name，qrCode，developer，category.id，hot，recommend
     * @throws BusinessException 公众号已存在
     */
    void update(PublicNumber publicNumber) throws BusinessException;

    /**
     * 获取单个公众号
     * @param publicNumber 必填：id
     * @return 公众号，字段：所有，category.id，category.name
     */
    PublicNumber get(PublicNumber publicNumber) throws BusinessException;

    /**
     * 获取并统计多个公众号<br/>
     * 支持排序和分页
     * @param publicNumber 可选条件：id，name（支持模糊），hot，recommend，category.id
     * @return 公众号列表和数量，字段：所有，category.id，category.name
     */
    PageModel<PublicNumber> listAndCount(PublicNumber publicNumber);

    /**
     * 获取热门公众号
     * @return 公众号列表，字段：id，name，qrCode，category.id
     */
    List<PublicNumber> listHot();

    /**
     * 获取首页推荐公众号<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部公众号<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].qrCode，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个公众号<br/>
     * 支持排序和分页
     * @param publicNumber 可选条件：category.id
     * @return 公众号列表，字段：id，name，url，category.id
     */
    List<PublicNumber> listCombo(PublicNumber publicNumber);
}
