package com.xiaosuokeji.yilan.server.service.intf.resource;

import com.xiaosuokeji.framework.xsjframework.exception.BusinessException;
import com.xiaosuokeji.framework.xsjframework.model.PageModel;
import com.xiaosuokeji.yilan.server.model.resource.Book;
import com.xiaosuokeji.yilan.server.model.resource.Category;

import java.util.List;

/**
 * 图书Service<br/>
 * Created by xuxiaowei on 2017/8/7.
 */
public interface BookService {

    /**
     * 保存图书
     * @param book 必填：name，author，publisher，publishDate，cover，pdf，category.id，hot，recommend，accessVipLevel
     *             选填：profile
     * @throws BusinessException 图书已存在
     */
    void save(Book book) throws BusinessException;

    /**
     * 保存多个图书
     * @param book 必填：books[i].name，books[i].author，books[i].publisher，books[i].publishDate，books[i].cover，
     *             books[i].pdf，books[i].accessVipLevel，books[i].category.id，选填：books[i].profile
     * @throws BusinessException 导入资源失败
     */
    void saveMulti(Book book) throws BusinessException;

    /**
     * 删除图书
     * @param book 必填：id
     */
    void remove(Book book);

    /**
     * 删除图书分类
     * @param category 必填：id
     * @throws BusinessException 分类已被使用
     */
    void removeCategory(Category category) throws BusinessException;

    /**
     * 更新图书
     * @param book 必填：id，可选更新字段：name，author，publisher，publishDate，cover，pdf，category.id，hot，recommend，
     *             profile，accessVipLevel
     * @throws BusinessException 图书已存在
     */
    void update(Book book) throws BusinessException;

    /**
     * 获取单个图书
     * @param book 必填：id
     * @return 图书，字段：所有，category.id，category.name
     * @throws BusinessException 图书不存在
     */
    Book get(Book book) throws BusinessException;

    /**
     * 获取并统计多个图书<br/>
     * 支持排序和分页
     * @param book 可选条件：id，name（支持模糊），hot，recommend，accessVipLevel，category.id
     * @return 图书列表和数量，字段：所有，category.id，category.name
     */
    PageModel<Book> listAndCount(Book book);

    /**
     * 获取热门图书
     * @return 图书列表，字段：id，name，url，category.id
     */
    List<Book> listHot();

    /**
     * 获取首页推荐图书<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listRecommend();

    /**
     * 获取全部图书<br/>
     * 按分类分组
     * @return 分类列表，字段：id，name，seq，category.children，
     * resources[i].id，resources[i].name，resources[i].url，resources[i].category.id
     */
    List<Category> listAll();

    /**
     * 获取多个图书<br/>
     * 支持排序和分页
     * @param book 可选条件：category.id
     * @return 图书列表，字段：id，name，url，category.id
     */
    List<Book> listCombo(Book book);
}
